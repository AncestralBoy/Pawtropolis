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

    public static Direction getOppositeDirection(Direction direction){
        switch (direction){
            case EAST -> {
                return WEST;
            }
            case WEST -> {
                return EAST;
            }
            case NORTH -> {
                return SOUTH;
            }
            case SOUTH -> {
                return NORTH;
            }
            default -> {
                return null;
            }
        }
    }

    public static String getOppositeDirection(String direction){
        switch (direction.toLowerCase()){
            case "east" -> {
                return "west";
            }
            case "west" -> {
                return "east";
            }
            case "north" -> {
                return "south";
            }
            case "south" -> {
                return "north";
            }
            default -> {
                return null;
            }
        }
    }
}
