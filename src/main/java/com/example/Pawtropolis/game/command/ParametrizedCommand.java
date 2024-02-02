package com.example.Pawtropolis.game.command;

import com.example.Pawtropolis.game.service.GameManager;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public abstract class ParametrizedCommand extends Command {
    private List<String> parameter;

    protected ParametrizedCommand(GameManager gameManager, List<String> parameter) {
        super(gameManager);
        this.parameter = parameter;
    }
}
