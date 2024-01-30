package com.example.Pawtropolis.game.command.implementation;

import com.example.Pawtropolis.game.command.ParametrizedCommand;
import com.example.Pawtropolis.game.service.GameManager;
import com.example.Pawtropolis.game.model.Item;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.logging.Level;

@Slf4j
@Component
public class DropCommand extends ParametrizedCommand {
    private DropCommand(GameManager gameManager){
        super(gameManager);
    }
    @Autowired
    private DropCommand(GameManager gameManager, ArrayList<String> parameter){
        super(gameManager, parameter);
    }

    @Override
    public void execute(){
        Item item = getGameManager().getPlayer().getItemInBagByString(getParameter().getFirst());

        if(getParameter().size() != 1){
            log.warn("Incorrect parameter for drop command!");
        }
        if (item == null) {
            log.warn("no {0} in bag", getParameter());
        } else  {
            getGameManager().getPlayer().removeItemFromBag(item);
            getGameManager().getMapManager().addItemInRoom(item);
        }
    }
}
