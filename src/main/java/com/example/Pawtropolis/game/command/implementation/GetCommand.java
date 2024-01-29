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
public class GetCommand extends ParametrizedCommand<Void> {
    @Autowired
    private GetCommand(GameManager gameManager, ArrayList<String> parameter){
        super(gameManager, parameter);
    }

    private GetCommand(GameManager gameManager){
        super(gameManager);
    }
    @Override
    public Void execute() {
        Item item = getGameManager().getMapManager().getChosenItemInRoom(getParameter().getFirst());

        if(getParameter().size() != 1){
            log.log(Level.WARNING, "Incorrect parameter for get command!");
            return null;
        }

        if (item == null) {
            log.log(Level.WARNING,"no {0} in room", getParameter());
        } else if (!getGameManager().getPlayer().addItemInBag(item)) {
            log.info("no enough space in bag");
        } else {
            getGameManager().getMapManager().removeItemInRoom(item);
        }
        return null;
    }
}
