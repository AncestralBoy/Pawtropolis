package com.example.Pawtropolis.game.service;

import com.example.Pawtropolis.game.command.ParametrizedCommand;
import com.example.Pawtropolis.game.command.implementation.UnknownCommand;
import lombok.Getter;
import lombok.NonNull;
import org.springframework.beans.factory.NoSuchBeanDefinitionException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.stereotype.Service;

import java.util.List;

@Getter
@Service
public class CommandFactory{
    private final ApplicationContext context;

    @Autowired
    private CommandFactory(@NonNull ApplicationContext context) {
        this.context = context;
    }

    public Object getInstance(List<String> wordList) {
        try {
            Object commandInstance = this.context.getBean(wordList.getFirst() + "Command");
            if (commandInstance instanceof ParametrizedCommand) {
                wordList.removeFirst();
                ((ParametrizedCommand<?>) commandInstance).setParameter(wordList);
            } else if (wordList.size() > 1) {
                return this.context.getBean(UnknownCommand.class);
            }
            return commandInstance;
        }
        catch (NoSuchBeanDefinitionException e) {
            return this.context.getBean(UnknownCommand.class);
        }
    }
}
