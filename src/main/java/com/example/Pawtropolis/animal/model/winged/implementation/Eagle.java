package com.example.Pawtropolis.animal.model.winged.implementation;

import com.example.Pawtropolis.animal.model.winged.AnimalWithWings;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.time.LocalDate;

@ToString
@EqualsAndHashCode(callSuper = true)
public class Eagle extends AnimalWithWings {
    public Eagle(String name, String favouriteFood, int age, LocalDate arrivalDate, double weight, double height, double wingspan) {
        super(name, favouriteFood, age, arrivalDate, weight, height, wingspan);
    }
}
