package com.example.Pawtropolis.game.command.implementation;

import com.example.Pawtropolis.game.command.ParametrizedCommand;
import com.example.Pawtropolis.game.service.GameManager;
import com.example.Pawtropolis.game.model.Item;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;

@Slf4j
@Component
public class DropCommand extends ParametrizedCommand {
    @Autowired
    private DropCommand(GameManager gameManager, ArrayList<String> parameter){
        super(gameManager, parameter);
    }

    @Override
    public void execute(){
        if(getParameter().size() != 1){
            log.warn("Incorrect parameter for drop command!");
        }
        else{
            Item item = getGameManager().getPlayer().getItemInBagByString(getParameter().getFirst());
            if (item == null) {
                log.warn("no {0} in bag", getParameter());
            } else  {
                getGameManager().getPlayer().removeItemFromBag(item);
                getGameManager().getMapManager().addItemInRoom(item);
            }
        }
    }
}
