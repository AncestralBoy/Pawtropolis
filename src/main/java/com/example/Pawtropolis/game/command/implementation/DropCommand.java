package com.example.Pawtropolis.game.command.implementation;

import com.example.Pawtropolis.game.command.ParametrizedCommand;
import com.example.Pawtropolis.game.service.GameManager;
import com.example.Pawtropolis.game.model.Item;
import lombok.extern.java.Log;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.logging.Level;

@Log
@Component
public class DropCommand extends ParametrizedCommand<Void> {
    private DropCommand(GameManager gameManager){
        super(gameManager);
    }
    @Autowired
    private DropCommand(GameManager gameManager, ArrayList<String> parameter){
        super(gameManager, parameter);
    }

    @Override
    public Void execute(){
        Item item = getGameManager().getPlayer().getItemInBagByString(getParameter().getFirst());

        if(getParameter().size() != 1){
            log.log(Level.WARNING, "Incorrect parameter for drop command!");
            return null;
        }

        if (item == null) {
            log.log(Level.WARNING, "no {0} in bag", getParameter());
        } else  {
            getGameManager().getPlayer().removeItemFromBag(item);
            getGameManager().getMapManager().addItemInRoom(item);
        }
        return null;
    }
}
