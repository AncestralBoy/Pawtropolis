package com.example.Pawtropolis.game.command.implementation;

import com.example.Pawtropolis.game.command.Command;
import com.example.Pawtropolis.game.service.GameManager;
import lombok.extern.java.Log;
import org.springframework.stereotype.Component;

import java.util.logging.Level;

@Log
@Component
public class UnknownCommand extends Command {
    private UnknownCommand(GameManager gameManager){
        super(gameManager);
    }

    public Void execute(){
        log.log(Level.WARNING, "Unknown command!");
        return null;
    }
}
