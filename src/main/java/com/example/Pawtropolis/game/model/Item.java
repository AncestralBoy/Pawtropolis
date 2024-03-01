package com.example.Pawtropolis.game.model;

import lombok.Data;

@Data
public class Item {
    private final String name;
    private final String description;
    private final int requiredSpace;
}
