package com.example.Pawtropolis.game.command.implementation;

import com.example.Pawtropolis.game.command.ParametrizedCommand;
import com.example.Pawtropolis.game.service.GameManager;
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
    public String execute(){
        if(getParameter().size() != 1){
            log.log(Level.WARNING, "Incorrect parameter for go command!");
            return null;
        }
        return getGameManager().getMapManager().changeCurrentRoom(getParameter().getFirst());
    }

}
