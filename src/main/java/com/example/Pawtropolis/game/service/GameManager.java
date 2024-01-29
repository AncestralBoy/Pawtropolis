package com.example.Pawtropolis.game.service;

import com.example.Pawtropolis.game.command.Command;
import com.example.Pawtropolis.game.service.console.InputHandler;
import com.example.Pawtropolis.game.service.console.InputReader;
import com.example.Pawtropolis.game.model.Player;

import lombok.Getter;
import lombok.extern.java.Log;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Log
@Service
public class GameManager {
    private final Player player;
    private final MapManager mapManager;
    private final CommandFactory commandFactory;

    private GameManager(Player player, CommandFactory commandFactory, MapManager mapManager) {
        this.player = player;
        this.mapManager = mapManager;
        this.commandFactory = commandFactory;
    }

    public void runGame() {
        boolean gameEnded = false;
        String input;
        String output;
        setPlayerName();
        System.out.println("Hey " + player.getName() + "! Welcome to Pawtropolis!");
        while (!gameEnded){
            System.out.println("What do you want to do?");
            System.out.print(">");
            input = InputReader.readString();
            List<String> readCommand = InputHandler.processInput(input);
            Command currentCommand = (Command) commandFactory.getInstance(readCommand);
            output = (String) currentCommand.execute();

            if (output != null) {
                if (!output.trim().equalsIgnoreCase("quit"))
                    System.out.println(output);
                else {
                    log.info(output);
                    gameEnded = true;
                }
            }
        }
    }

    public void setPlayerName(){
        System.out.println("Hi! What's your name?");
        player.setName(InputReader.readString());
    }
}
