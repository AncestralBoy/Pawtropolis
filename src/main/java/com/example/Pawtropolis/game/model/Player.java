package com.example.Pawtropolis.game.model;

import lombok.Getter;
import lombok.Setter;
import org.springframework.stereotype.Component;

@Component
public class Player {
    @Setter
    @Getter
    private String name;
    @Getter
    @Setter
    private int healthPoints;
    private final Bag bag;

    private Player(){
        this.bag = new Bag();
        healthPoints = 10;
    }

    public boolean addItemInBag(Item item){
        return bag.addItem(item);
    }

    public void removeItemFromBag(Item item){
        bag.removeItem(item);
    }

    public void geItemsInBag(){
        bag.getItemsToString();
    }

    public Item getItemInBagByString(String string){
        return bag.getItemByString(string);
    }
}
