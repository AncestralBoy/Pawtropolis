package com.example.Pawtropolis.game.command.implementation;

import com.example.Pawtropolis.game.command.Command;
import com.example.Pawtropolis.game.service.GameManager;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Slf4j
@Component
public class UnknownCommand extends Command {
    @Autowired
    private UnknownCommand(GameManager gameManager){
        super(gameManager);
    }

    @Override
    public void execute(){
        log.warn("Unknown command!");
    }
}
