package com.example.Pawtropolis.game.command.implementation;

import com.example.Pawtropolis.game.command.ParametrizedCommand;
import com.example.Pawtropolis.game.model.Item;
import com.example.Pawtropolis.game.service.GameManager;
import com.example.Pawtropolis.game.service.console.InputReader;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.logging.Level;

@Slf4j
@Component
public class GoCommand extends ParametrizedCommand {
    @Autowired
    private GoCommand(GameManager gameManager, ArrayList<String> parameter){
        super(gameManager, parameter);
    }

    private GoCommand(GameManager gameManager){
        super(gameManager);
    }

    @Override
    public void execute() {
        String direction = getParameter().getFirst();
        if (getParameter().size() != 1) {
            log.warn("Incorrect parameter for go command!");
        }
        if (getGameManager().getMapManager().getRoomByDirection(direction).isLocked()) {
            log.info("The door is locked: would you like to use an item to unlock it?");
            String answer = InputReader.readString();
            switch (answer.toLowerCase()) {
                case "y":
                    log.info("Type the name of the chosen item");
                    String itemName = InputReader.readString();
                    Item item = getGameManager().getPlayer().getItemInBagByString(itemName);
                    if (item != null) {
                        if (getGameManager().getMapManager().getLockedRoomByItem(item) != null) {
                            if (getGameManager().getMapManager().getLockedRoomByItem(item).equals(getGameManager().getMapManager().getRoomByDirection(direction))) {
                                getGameManager().getMapManager().getRoomByDirection(direction).setLocked(false);
                                log.info("You unlocked the door!");
                                getGameManager().getPlayer().removeItemFromBag(item);
                                getGameManager().getMapManager().changeCurrentRoom(getParameter().getFirst());
                            } else {
                                log.warn("This is not the right item");
                            }
                        }
                    } else {
                        log.warn("You don't have this Item");
                    }
                    break;
                case "n":
                    break;
                default:
                    log.warn("Incorrect choice");
                    break;
            }
        }
        else {
            getGameManager().getMapManager().changeCurrentRoom(getParameter().getFirst());
        }
    }
}
