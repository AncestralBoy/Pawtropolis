package com.example.Pawtropolis.animal.exception;

import com.example.Pawtropolis.animal.model.Animal;

public class SpeciesNotFound extends Exception{
    private final Class<? extends Animal> animal;

    public SpeciesNotFound(Class<? extends Animal> animal){
        this.animal = animal;
    }

    public String getSpeciesNotFound() {
        return animal.getName();
    }
}
