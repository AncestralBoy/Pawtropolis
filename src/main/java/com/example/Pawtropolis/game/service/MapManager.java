package com.example.Pawtropolis.game.service;

import com.example.Pawtropolis.animal.service.ZooManager;
import com.example.Pawtropolis.animal.exception.AnimalNotFound;
import com.example.Pawtropolis.animal.model.Animal;
import com.example.Pawtropolis.game.model.Direction;
import com.example.Pawtropolis.game.model.Item;
import com.example.Pawtropolis.game.model.Room;
import jakarta.annotation.PostConstruct;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@Getter
@Service
public class MapManager {
    private final ZooManager zooManager;
    private Room currentRoom;

    @Autowired
    private MapManager(ZooManager zooManager){
        currentRoom = new Room("Basement", "a dark, cramped place");
        this.zooManager = zooManager;
    }

    @PostConstruct
    public void generateMap(){
        Room room2 = new Room("Thermal Baths", "a warm place, there is a lot of fog and hot water geyser");
        Room room3 = new Room("Cave", "a dark cave, you hear a lot of chilling noises");
        Room room4 = new Room("Mausoleum", "in front of a grave, but it’s too big to be a normal person");
        Room room5 = new Room("Temple", "an old temple, the stones are worn, there is moss everywhere, torches illuminate a flight of steps leading to a throne");
        Room room6 = new Room("Downpour", "you're soaked in dirty water");
        Room room7 = new Room("Mines", "you see hundreds of sparkles in the dark");
        Room room8 = new Room("Jungle", "you catch a glimpse of dense vegetation, it is pouring rain, and there is a herd of monkeys watching you");
        Room room9 = new Room("Desert", "the air is hot and the place seems hostile");
        Room room10 = new Room( "Mountain", "you are on the top of a very high mountain, there is snow everywhere, the air is fresh");
        Room room11 = new Room( "Treasure Room", "You've never seen so much wealth in one place");
        Room room12 = new Room( "Boss Room", "This place is unlike any you have encountered, the calmness that seems to be there snows you");

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
            log.warn("No Npc found!");
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
        connectRoomsWithOpenDoor(currentRoom, room3, Direction.WEST);
        connectRoomsWithLockedDoor(currentRoom, room6, item2, Direction.EAST);
        connectRoomsWithOpenDoor(currentRoom, room2, Direction.SOUTH);
        connectRoomsWithOpenDoor(room3, room4, Direction.NORTH);
        connectRoomsWithOpenDoor(room3, room5, Direction.WEST);
        connectRoomsWithOpenDoor(room6, room8, Direction.NORTH);
        connectRoomsWithLockedDoor(room8, room9, item4, Direction.NORTH);
        connectRoomsWithOpenDoor(room9, room10, Direction.EAST);
        connectRoomsWithLockedDoor(room10, room11, item6, Direction.EAST);
        connectRoomsWithLockedDoor(room11, room12, item7, Direction.SOUTH);

    }

    public void connectRoomsWithOpenDoor(Room entryRoom, Room exitRoom, Direction direction){
        switch (direction) {
            case NORTH -> {
                entryRoom.addOpenDoor(direction, entryRoom, exitRoom);
                exitRoom.addOpenDoor(Direction.SOUTH, exitRoom, entryRoom);
            }
            case SOUTH -> {
                entryRoom.addOpenDoor(direction, entryRoom, exitRoom);
                exitRoom.addOpenDoor(Direction.NORTH, exitRoom, entryRoom);
            }
            case EAST -> {
                entryRoom.addOpenDoor(direction, entryRoom, exitRoom);
                exitRoom.addOpenDoor(Direction.WEST, exitRoom, entryRoom);
            }
            case WEST -> {
                entryRoom.addOpenDoor(direction, entryRoom, exitRoom);
                exitRoom.addOpenDoor(Direction.EAST, exitRoom, entryRoom);
            }
        }
    }

    public void connectRoomsWithLockedDoor(Room entryRoom, Room exitRoom, Item key, Direction direction){
        switch (direction) {
            case NORTH -> {
                entryRoom.addLockedDoor(direction, key, entryRoom, exitRoom);
                exitRoom.addLockedDoor(Direction.SOUTH, key, exitRoom, entryRoom);
            }
            case SOUTH -> {
                entryRoom.addLockedDoor(direction, key, entryRoom, exitRoom);
                exitRoom.addLockedDoor(Direction.NORTH, key, exitRoom, entryRoom);
            }
            case EAST -> {
                entryRoom.addLockedDoor(direction, key, entryRoom, exitRoom);
                exitRoom.addLockedDoor(Direction.WEST, key, exitRoom, entryRoom);
            }
            case WEST -> {
                entryRoom.addLockedDoor(direction, key, entryRoom, exitRoom);
                exitRoom.addLockedDoor(Direction.EAST, key, exitRoom, entryRoom);
            }
        }
    }



    public void lookCurrentRoom() {
        getCurrentRoom().look();
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
            log.warn("Incorrect direction");
            return null;
        }
        else if (currentRoom.getConnectedRoomByDirection(currentDirection) != null) {
            return currentRoom.getConnectedRoomByDirection(currentDirection);
        }
        else {
            log.warn("There isn't a room in this direction");
            return null;
        }
    }

    public void changeCurrentRoom(String direction){
        currentRoom = getRoomByDirection(direction);
        getCurrentRoom().look();
    }

}
