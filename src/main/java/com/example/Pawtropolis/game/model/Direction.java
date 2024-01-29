package com.example.Pawtropolis.game.model;

import lombok.Getter;

import java.util.Arrays;

@Getter
public enum Direction {
    NORTH ("North"),
    SOUTH ("South"),
    EAST ("East"),
    WEST ("West");

    private final String name;

    Direction(String name) {
        this.name = name;
    }

    public static Direction getDirectionByString(String string){
        return Arrays.stream(Direction.values())
                .filter(d -> d.name.equalsIgnoreCase(string))
                .findFirst()
                .orElse(null);
    }
}
