package com.example.Pawtropolis.game.command.implementation;


import com.example.Pawtropolis.game.command.Command;
import com.example.Pawtropolis.game.service.GameManager;
import org.springframework.stereotype.Component;

@Component
public class QuitCommand extends Command<String> {
    private QuitCommand(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public String execute() {
        return "quit";
    }
}
