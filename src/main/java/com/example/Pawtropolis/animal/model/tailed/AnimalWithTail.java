package com.example.Pawtropolis.animal.model.tailed;

import com.example.Pawtropolis.animal.model.Animal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class AnimalWithTail extends Animal {
    private final double tailLength;

    protected AnimalWithTail(String name, String favouriteFood, int age, LocalDate arrivalDate, double weight, double height, double tailLength) {
        super(name, favouriteFood, age, arrivalDate, weight, height);
        this.tailLength = tailLength;
    }
}
