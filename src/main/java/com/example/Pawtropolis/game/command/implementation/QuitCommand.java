package com.example.Pawtropolis.game.command.implementation;


import com.example.Pawtropolis.game.command.Command;
import com.example.Pawtropolis.game.service.GameManager;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class QuitCommand extends Command {
    @Autowired
    private QuitCommand(GameManager gameManager) {
        super(gameManager);
    }

    @Override
    public void execute() {
        log.info("quit");
        getGameManager().setGameEnded(true);
    }
}
