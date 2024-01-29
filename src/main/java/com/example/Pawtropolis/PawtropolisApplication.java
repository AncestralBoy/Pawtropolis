package com.example.Pawtropolis;

import com.example.Pawtropolis.game.service.GameManager;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import org.springframework.context.support.AbstractApplicationContext;

@SpringBootApplication
public class PawtropolisApplication {

	public static void main(String[] args) {
		AbstractApplicationContext ctx = new AnnotationConfigApplicationContext(PawtropolisApplication.class);
		ctx.getBean(GameManager.class).runGame();
		ctx.close();
	}
}
