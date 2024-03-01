package com.example.Pawtropolis.game.model;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Door {
    private  boolean isLocked;
    private final Item key;

    public String getDoorStatus() {
        return isLocked ? "closed" : "open";
    }
}
