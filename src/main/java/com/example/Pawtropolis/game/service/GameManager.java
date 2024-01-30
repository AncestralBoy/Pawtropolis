package com.example.Pawtropolis.game.service;

import com.example.Pawtropolis.game.command.Command;
import com.example.Pawtropolis.game.service.console.InputHandler;
import com.example.Pawtropolis.game.service.console.InputReader;
import com.example.Pawtropolis.game.model.Player;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Slf4j
@Service
public class GameManager {
    private final Player player;
    private final MapManager mapManager;
    private final CommandFactory commandFactory;
    @Setter
    private boolean gameEnded;

    @Autowired
    private GameManager(Player player, CommandFactory commandFactory, MapManager mapManager) {
        this.player = player;
        this.mapManager = mapManager;
        this.commandFactory = commandFactory;
        gameEnded = false;
    }

    public void runGame() {
        String input;
        setPlayerName();
        System.out.println("Hey " + player.getName() + "! Welcome to Pawtropolis!");
        while (!gameEnded){
            System.out.println("What do you want to do?");
            System.out.print(">");
            input = InputReader.readString();
            List<String> readCommand = InputHandler.processInput(input);
            Command currentCommand = (Command) commandFactory.getInstance(readCommand);
            currentCommand.execute();
        }
    }

    public void setPlayerName(){
        System.out.println("Hi! What's your name?");
        player.setName(InputReader.readString());
    }
}
