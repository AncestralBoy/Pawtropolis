package com.example.Pawtropolis;

import com.example.Pawtropolis.game.service.GameManager;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.context.support.AbstractApplicationContext;

@SpringBootApplication
@RequiredArgsConstructor
public class PawtropolisApplication implements CommandLineRunner {
	private final GameManager gameManager;
	public static void main(String[] args) {
		SpringApplication.run(PawtropolisApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		gameManager.runGame();
	}
}
