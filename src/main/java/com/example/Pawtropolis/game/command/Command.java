package com.example.Pawtropolis.game.command;

import com.example.Pawtropolis.game.service.GameManager;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;

@Getter
public abstract class Command {
    private final GameManager gameManager;

    protected Command(GameManager gameManager) {
        this.gameManager = gameManager;
    }

    public abstract void execute();
}
