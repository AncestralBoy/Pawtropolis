
package com.example.Pawtropolis.game.service.console;

import lombok.extern.java.Log;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.logging.Level;

@Log
public class InputReader {
    private InputReader() {
    }

    public static String readString() {
        InputStreamReader input = new InputStreamReader(System.in);
        BufferedReader inputReader = new BufferedReader(input);
        try {
            return inputReader.readLine();
        } catch (IOException e) {
            log.log(Level.WARNING,"Error while reading user input");
            return "";
        }
    }
}
