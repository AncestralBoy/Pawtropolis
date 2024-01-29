package com.example.Pawtropolis.animal.model;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode
public abstract class Animal {
    private final String name;
    private final String favouriteFood;
    private final int age;
    private final LocalDate arrivalDate;
    private final double weight;
    private final double height;

    protected Animal(String name, String favouriteFood, int age, LocalDate arrivalDate, double weight, double height) {
        this.name = name;
        this.favouriteFood = favouriteFood;
        this.age = age;
        this.arrivalDate = arrivalDate;
        this.weight = weight;
        this.height = height;
    }
}
