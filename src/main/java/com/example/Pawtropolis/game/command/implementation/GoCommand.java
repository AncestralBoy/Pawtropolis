package com.example.Pawtropolis.game.command.implementation;

import com.example.Pawtropolis.game.command.ParametrizedCommand;
import com.example.Pawtropolis.game.model.Direction;
import com.example.Pawtropolis.game.model.Item;
import com.example.Pawtropolis.game.service.GameManager;
import com.example.Pawtropolis.game.service.console.InputReader;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
@Component
public class GoCommand extends ParametrizedCommand {
    @Autowired
    private GoCommand(GameManager gameManager, ArrayList<String> parameter){
        super(gameManager, parameter);
    }

    @Override
    public void execute() {
        if(getParameter().size() != 1){
            System.out.println("Go command needs a direction as a parameter!");
        } else {
            String direction = getParameter().getFirst();
            if (getGameManager().getMapManager().getRoomByDirection(direction) != null) {
                tryToMoveInGivenDirection(direction);
            }
        }
    }

    private boolean checkLockedDoor(String direction) {
        return getGameManager().getMapManager().getCurrentRoom().isLockedDoor(direction);
    }

    private void tryToMoveInGivenDirection(String direction) {
        if (checkLockedDoor(direction)) {
            System.out.println("The door is locked: would you like to use an item to unlock it? (y/n)");
            String answer = InputReader.readString();
            switch (answer.toLowerCase()) {
                case "y":
                    System.out.println("Type the name of the chosen item");
                    String itemName = InputReader.readString();
                    checkKeyItem(itemName, direction);
                    break;
                case "n":
                    return;
                default:
                    System.out.println("Incorrect choice");
                    break;
            }
        } else {
            getGameManager().getMapManager().changeCurrentRoom(direction);
        }
    }

    private void checkKeyItem(String itemName, String direction) {
        Item item = getGameManager().getPlayer().getItemInBagByString(itemName);
        if (item != null) {
            if (getGameManager().getMapManager().getCurrentRoom().getKeyItemOfDoor(direction).equals(item)) {
                unlockRoom(direction, item);
            } else {
                System.out.println("This is not the right item");
            }
        } else {
            System.out.println("You don't have this item");
        }
    }

    private void unlockRoom(String direction, Item item) {
        getGameManager().getMapManager().getCurrentRoom().unlockDoor(direction);
        getGameManager().getMapManager().getRoomByDirection(direction).unlockDoor(Direction.getOppositeDirection(direction));
        System.out.println("You unlocked the door!");
        getGameManager().getPlayer().removeItemFromBag(item);
        getGameManager().getMapManager().changeCurrentRoom(direction);
    }
}
