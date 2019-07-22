package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;

/**
 * This is our main game class.
 * It has the main method which
 * launches the game and sets the screen size.
 * 
 * Throughout the game we received some help from 
 * the peer instructors to get it working.
 * @author Davey Turner
 * @author Andy Humphries
 *
 */
public class Game extends StateBasedGame {

	public static final String gameName = "Zambie";
	public static final int menu = 0;
	public static final int playScreen = 1;
	public static final int pauseScreen = 2;
	public static final int gameOverScreen = 3;
	/**
	 * Constructor for Game class
	 * @param gameName
	 */
	public Game(String gameName) {
		super(gameName);
		this.addState(new Menu(menu));
		this.addState(new PlayScreen(playScreen));
		this.addState(new Pause(pauseScreen));
		this.addState(new GameOver(gameOverScreen));
	}
	
	/**
	 * Initializes the states of the game and
	 * enters the user to the menu screen.
	 */
	public void initStatesList(GameContainer gc) throws SlickException {
		this.getState(menu).init(gc, this);
		this.getState(playScreen).init(gc, this);
		this.getState(gameOverScreen).init(gc, this);
		this.enterState(menu);
	}
	
	/**
	 * Main method
	 * launches the game and sets screen size.
	 * @param args
	 */
	public static void main(String[] args) {
		AppGameContainer appgc;
		try {
			appgc = new AppGameContainer(new Game(gameName));
			// true for full-screen
			appgc.setVSync(true);
			appgc.setDisplayMode(1280, 720, false);
			appgc.start();
		}catch(SlickException e) {
			e.printStackTrace();
		}

	}

}
