package com.example.Pawtropolis.game.command.implementation;

import com.example.Pawtropolis.game.command.Command;
import com.example.Pawtropolis.game.service.GameManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class UnknownCommand extends Command {
    @Autowired
    private UnknownCommand(GameManager gameManager){
        super(gameManager);
    }

    @Override
    public void execute(){
        System.out.println("Unknown command!");
    }
}
