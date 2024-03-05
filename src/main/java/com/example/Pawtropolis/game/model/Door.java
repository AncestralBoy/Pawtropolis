package com.example.Pawtropolis.game.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Door {
    private boolean isLocked;
    private final Item key;
    private final Room startRoom;
    private final Room arrivalRoom;

    public Door(boolean isLocked, Item key, Room startRoom, Room arrivalRoom) {
        this.isLocked = isLocked;
        this.key = key;
        this.startRoom = startRoom;
        this.arrivalRoom = arrivalRoom;
    }
    public Door(boolean isLocked, Room startRoom, Room arrivalRoom) {
        this.isLocked = isLocked;
        key = null;
        this.startRoom = startRoom;
        this.arrivalRoom = arrivalRoom;
    }


    public String getDoorStatus() {
        return isLocked ? "closed" : "open";
    }
}
