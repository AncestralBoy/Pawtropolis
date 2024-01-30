package com.example.Pawtropolis.game.service.console;

import lombok.extern.java.Log;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
@Slf4j
public class InputHandler {
    private InputHandler() {
    }

    public static List<String> processInput(String input){
        List<String> wordList = null;
        String lowerCaseString = input.trim().toLowerCase();

        if (lowerCaseString.isEmpty()){
            log.warn("You must enter a command");
        } else {
            wordList = splitWords(lowerCaseString);
        }
        return wordList;
    }

    private static List<String> splitWords(String input){
        String delimiters = "[ \t,.:;?!\"']+";
        String[] words = input.split(delimiters, 2);

        return new ArrayList<>(Arrays.asList(words));
    }
}
