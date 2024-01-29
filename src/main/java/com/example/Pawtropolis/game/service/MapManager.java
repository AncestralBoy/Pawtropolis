package com.example.Pawtropolis.game.service;

import com.example.Pawtropolis.animal.service.ZooManager;
import com.example.Pawtropolis.animal.exception.AnimalNotFound;
import com.example.Pawtropolis.animal.model.Animal;
import com.example.Pawtropolis.game.model.Direction;
import com.example.Pawtropolis.game.model.Item;
import com.example.Pawtropolis.game.model.Room;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;

@Log
@Getter
@Service
public class MapManager {
    private final ZooManager zooManager;
    private Room currentRoom;
    private final Map<Item, Room> lockedRoomMap;

    @Autowired
    private MapManager(ZooManager zooManager){
        currentRoom = new Room("Basement", "a dark, cramped place");
        this.zooManager = zooManager;
        lockedRoomMap = new HashMap<>();
    }

    @PostConstruct
    public void generateMap(){
        Room room2 = new Room("Thermal Baths", "a warm place, there is a lot of fog and hot water gaiser");
        Room room3 = new Room("Cave", "a dark cave, you hear a lot of chilling noises");
        Room room4 = new Room("Mausoleum", "in front of a grave, but it’s too big to be a normal person");
        Room room5 = new Room("Temple", "an old temple, the stones are worn, there is moss everywhere, torches illuminate a flight of steps leading to a throne");
        Room room6 = new Room("Downpour", "you're soaked in dirty water");
        Room room7 = new Room("Mines", "you see hundreds of sparkles in the dark");
        Room room8 = new Room("Jungle", "you catch a glimpse of dense vegetation, it is pouring rain, and there is a herd of monkeys watching you");
        Room room9 = new Room("Desert", "the air is hot and the place seems hostile");
        Room room10 = new Room( "Mountain", "you are on the top of a very high mountain, there is snow everywhere, the air is fresh");
        Room room11 = new Room( "Treasure Room", "You've never seen so much wealth in one place");
        Room room12 = new Room( "Boos Room", "This place is unlike any you have encountered, the calmness that seems to be there snows you");

        Item item1 = new Item("Potion", "Heals 20 HP", 1);
        Item item2 = new Item("Penny", "A common coin", 2);
        Item item3 = new Item("Super Potion", "Heals 50 HP", 1);
        Item item4 = new Item("Key", "An old and rusty key", 2);
        Item item5 = new Item("Full Heal", "Heals all status conditions", 1);
        Item item6 = new Item("Bomb", "Can be dangerous", 2);
        Item item7 = new Item("Golden Key", "Opens a special door", 2);
        Item item8 = new Item("Sword", "+10 atk", 1);
        Item item9 = new Item("Shield", "+10 def", 1);
        Item item10 = new Item("Bow", "+10 sp.atk, +10speed", 1);

        //Aggiunta Npc nelle stanze
        zooManager.populateZoo();
        List<Animal> animals = new ArrayList<>();
        try{
            animals = zooManager.getAnimalsList();
        } catch (AnimalNotFound e){
            log.log(Level.WARNING,"No Npc found!!");
        }
        room2.addNpc(animals.get(0));
        room4.addNpc(animals.get(1));
        room5.addNpc(animals.get(2));
        room5.addNpc(animals.get(3));
        room6.addNpc(animals.get(4));
        room6.addNpc(animals.get(5));
        room8.addNpc(animals.get(6));
        room11.addNpc(animals.get(7));
        room12.addNpc(animals.get(8));

        //Aggiunta oggetti nelle stanze
        room3.addItem(item1);
        room3.addItem(item2);
        room6.addItem(item3);
        room6.addItem(item4);
        room7.addItem(item5);
        room8.addItem(item6);
        room9.addItem(item7);
        room10.addItem(item8);
        room11.addItem(item9);
        room11.addItem(item10);

        //Collegamento delle stanze
        connectRooms(currentRoom, room3, Direction.WEST);
        connectRooms(currentRoom, room6, Direction.EAST);
        connectRooms(currentRoom, room2, Direction.SOUTH);
        connectRooms(room3, room4, Direction.NORTH);
        connectRooms(room3, room5, Direction.WEST);
        connectRooms(room6, room8, Direction.NORTH);
        connectRooms(room8, room9, Direction.NORTH);
        connectRooms(room9, room10, Direction.EAST);
        connectRooms(room10, room11, Direction.EAST);
        connectRooms(room11, room12, Direction.SOUTH);

        setLockedRoom(room6);
        setLockedRoom(room9);
        setLockedRoom(room11);
        setLockedRoom(room12);

        setLockedRoomMap(item2, room6);
        setLockedRoomMap(item4, room9);
        setLockedRoomMap(item6, room11);
        setLockedRoomMap(item7, room12);
    }

    public void connectRooms(Room entryRoom, Room exitRoom, Direction direction){
        switch (direction) {
            case NORTH -> {
                entryRoom.addConnectedRoom(Direction.NORTH, exitRoom);
                exitRoom.addConnectedRoom(Direction.SOUTH, entryRoom);
            }
            case SOUTH -> {
                entryRoom.addConnectedRoom(Direction.SOUTH, exitRoom);
                exitRoom.addConnectedRoom(Direction.NORTH, entryRoom);
            }
            case EAST -> {
                entryRoom.addConnectedRoom(Direction.EAST, exitRoom);
                exitRoom.addConnectedRoom(Direction.WEST, entryRoom);
            }
            case WEST -> {
                entryRoom.addConnectedRoom(Direction.WEST, exitRoom);
                exitRoom.addConnectedRoom(Direction.EAST, entryRoom);
            }
        }
    }

    public void setLockedRoom(Room room) {
        room.setLocked(true);
    }

    public void setLockedRoomMap(Item keyItem, Room lockedRoom) {
        lockedRoomMap.put(keyItem, lockedRoom);
    }

    public Room getLockedRoomByItem(Item keyItem) {
        return lockedRoomMap.get(keyItem);
    }

    public String lookCurrentRoom() {
        return getCurrentRoom().look();
    }

    public void removeItemInRoom(Item item){
        getCurrentRoom().removeItem(item);
    }

    public void addItemInRoom(Item item) {
        getCurrentRoom().addItem(item);
    }

    public Item getChosenItemInRoom(String itemName) {
        return getCurrentRoom().getItemByString(itemName);
    }

    public Room getRoomByDirection(String direction) {
        Direction currentDirection = Direction.getDirectionByString(direction);

        if (currentDirection == null) {
            log.warning("Incorrect direction");
            return null;
        }
        else if (currentRoom.getConnectedRoomByDirection(currentDirection) != null) {
            return currentRoom.getConnectedRoomByDirection(currentDirection);
        }
        else {
            log.warning("No room in this direction");
            return null;
        }
    }

    public String changeCurrentRoom(String direction){
        Direction currentDirection = Direction.getDirectionByString(direction);

        if (currentDirection == null)
            return ("Incorrect direction");

        else if(currentRoom.getConnectedRoomByDirection(currentDirection) != null){
            currentRoom = currentRoom.getConnectedRoomByDirection(currentDirection);
            return (getCurrentRoom().look());
        }
        else{
            return ("No room in this direction");
        }
    }
}
