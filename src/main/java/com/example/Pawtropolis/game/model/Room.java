package com.example.Pawtropolis.game.model;

import com.example.Pawtropolis.animal.model.Animal;
import lombok.Getter;

import java.util.ArrayList;
import java.util.EnumMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

public class Room {
    @Getter
    private final String name;
    @Getter
    private final String description;

    private final Map<Direction, Room> connectedRooms;
    private final List<Item> items;
    private final List<Animal> npc;

    public Room(String name, String description) {
        this.name = name;
        this.description = description;
        connectedRooms = new EnumMap<>(Direction.class);
        items = new ArrayList<>();
        npc = new ArrayList<>();
    }

    public void addItem(Item item){
        items.add(item);
    }

    public void removeItem(Item item){ items.remove(item);}

    public void addNpc(Animal npc){
        this.npc.add(npc);
    }

    public void removeNpc(Animal npc) {
        this.npc.remove(npc);
    }

    public Room getConnectedRoomByDirection(Direction direction){
        return connectedRooms.get(direction);
    }

    public void addConnectedRoom(Direction direction, Room room){
        connectedRooms.put(direction, room);
    }

    public String getNpcNames() {
        String npcDescription;
        if(!npc.isEmpty()){
            npcDescription = npc.stream()
                    .map(a -> a.getName() + "(" + a.getClass().getSimpleName() +"), ")
                    .collect(Collectors.joining());
            npcDescription = npcDescription.substring(0,npcDescription.length() - 2);
        }
        else {
            npcDescription = "No Npc in this room";
        }
        return npcDescription;
    }

    public String getItemsNames() {
        String itemsDescription;
        if(!items.isEmpty()){
            itemsDescription = items.stream()
                    .map(i -> i.getName() +", ")
                    .collect(Collectors.joining());
            itemsDescription = itemsDescription.substring(0, itemsDescription.length() - 2);
        }
        else {
            itemsDescription = "No items in this room";
        }
        return itemsDescription;
    }

    public String getConnectedRoomsNames() {
        String connectedRoom;
        connectedRoom = connectedRooms.entrySet()
                .stream()
                .map(e -> e.getKey().getName() + ": " + e.getValue().getName() + ", " )
                .collect(Collectors.joining());
        connectedRoom = connectedRoom.substring(0,connectedRoom.length() - 2);
        return connectedRoom;
    }

    public String look(){
        return "You are in " + getName() +  "\n" + getDescription() + "\nItems:\n" + getItemsNames()
                +"\nNCP:\n" + getNpcNames() + "\n" + "Connected rooms: \n" + getConnectedRoomsNames() + "\n";

    }

    public Item getItemByString(String itemName) {
        String nameToLowerCase = itemName.trim();
        return items.stream()
                .filter(i -> i.getName().equalsIgnoreCase(nameToLowerCase))
                .findAny()
                .orElse(null);
    }
}
