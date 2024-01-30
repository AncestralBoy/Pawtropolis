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
public class GetCommand extends ParametrizedCommand {
    @Autowired
    private GetCommand(GameManager gameManager, ArrayList<String> parameter){
        super(gameManager, parameter);
    }

    private GetCommand(GameManager gameManager){
        super(gameManager);
    }
    @Override
    public void execute() {
        Item item = getGameManager().getMapManager().getChosenItemInRoom(getParameter().getFirst());

        if(getParameter().size() != 1){
            log.warn("Incorrect parameter for get command!");
        }

        if (item == null) {
            log.warn("no {0} in room", getParameter());
        } else if (!getGameManager().getPlayer().addItemInBag(item)) {
            log.info("no enough space in bag");
        } else {
            getGameManager().getMapManager().removeItemInRoom(item);
        }
    }
}
