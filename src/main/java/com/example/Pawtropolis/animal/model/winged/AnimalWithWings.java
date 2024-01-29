package com.example.Pawtropolis.animal.model.winged;

import com.example.Pawtropolis.animal.model.Animal;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.ToString;

import java.time.LocalDate;

@Getter
@ToString
@EqualsAndHashCode(callSuper = false)
public abstract class AnimalWithWings extends Animal {
    private final double wingspan;

    protected AnimalWithWings(String name, String favouriteFood, int age, LocalDate arrivalDate, double weight, double height, double wingspan) {
        super(name, favouriteFood, age, arrivalDate, weight, height);
        this.wingspan = wingspan;
    }
}
