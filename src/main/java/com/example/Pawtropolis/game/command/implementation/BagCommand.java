package com.example.Pawtropolis.game.command.implementation;

import com.example.Pawtropolis.game.command.Command;
import com.example.Pawtropolis.game.service.GameManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
public class BagCommand extends Command {
    @Autowired
    private BagCommand(GameManager gameManager){
        super(gameManager);
    }

    @Override
    public void execute(){
        getGameManager().getPlayer().geItemsInBag();
    }
}
