package com.mcnz.spring;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class SpringFramwork {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		ApplicationContext spring = new AnnotationConfigApplicationContext(Score.class); // IoC
		Game game = spring.getBean(Game.class);
		
		game.playTheGame();
		game.playTheGame();
		
		System.out.print(game.score);
	}

}

class Score {
	int wins, losses, ties;
	
}

class Game {
	
	@Autowired //DI
	Score score;
	
	public void playTheGame() {
		score.wins++;
	}
}