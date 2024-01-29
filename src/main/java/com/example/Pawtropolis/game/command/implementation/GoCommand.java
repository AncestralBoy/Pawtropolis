package com.example.Pawtropolis.game.command.implementation;

import com.example.Pawtropolis.game.command.ParametrizedCommand;
import com.example.Pawtropolis.game.model.Item;
import com.example.Pawtropolis.game.service.GameManager;
import com.example.Pawtropolis.game.service.console.InputReader;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.logging.Level;

@Log
@Component
public class GoCommand extends ParametrizedCommand<String> {
    @Autowired
    private GoCommand(GameManager gameManager, ArrayList<String> parameter){
        super(gameManager, parameter);
    }

    private GoCommand(GameManager gameManager){
        super(gameManager);
    }

    @Override
    public String execute() {
        String direction = getParameter().getFirst();
        if (getParameter().size() != 1) {
            log.log(Level.WARNING, "Incorrect parameter for go command!");
            return null;
        }
        if (getGameManager().getMapManager().getRoomByDirection(direction).isLocked()) {
            System.out.println("The door is locked: would you like to use an item to unlock it?");
            String answer = InputReader.readString();
            switch (answer.toLowerCase()) {
                case "y":
                    System.out.println("Type the name of the chosen item");
                    String itemName = InputReader.readString();
                    Item item = getGameManager().getPlayer().getItemInBagByString(itemName);
                    if (item != null) {
                        if (getGameManager().getMapManager().getLockedRoomByItem(item) != null) {
                            if (getGameManager().getMapManager().getLockedRoomByItem(item).equals(getGameManager().getMapManager().getRoomByDirection(direction))) {
                                getGameManager().getMapManager().getRoomByDirection(direction).setLocked(false);
                                System.out.println("You unlocked the door!");
                                getGameManager().getPlayer().removeItemFromBag(item);
                                return getGameManager().getMapManager().changeCurrentRoom(getParameter().getFirst());
                            } else {
                                return "This is not the right item";
                            }
                        }
                    } else {
                        return "You don't have this Item";
                    }
                case "n":
                    return null;
                default:
                    return "Incorrect choice";
            }
        }
        else {
            return getGameManager().getMapManager().changeCurrentRoom(getParameter().getFirst());
        }
    }
}
