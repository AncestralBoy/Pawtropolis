package com.example.Pawtropolis.game.command;

import com.example.Pawtropolis.game.service.GameManager;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public abstract class ParametrizedCommand<T> extends Command<T> {
    private List<String> parameter;

    protected ParametrizedCommand(GameManager gameManager){
        super(gameManager);
        parameter = new ArrayList<>();
    }
    protected ParametrizedCommand(GameManager gameManager, List<String> parameter) {
        super(gameManager);
        this.parameter = parameter;
    }
}
