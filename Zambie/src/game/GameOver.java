package game;

import org.lwjgl.input.Mouse;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/*
 * This class controls the game over state of the game.
 * @author Davey Turner
 * @author Andy Humphries
 */
public class GameOver extends BasicGameState{
	
	Image background;
	Image resume;
	Image mainMenu;
	private float shiftX;
	private float shiftY;
	
	public String mouse = "No input yet";
	public GameOver(int state) {
		
	}
	
	/**
	 * Initialize the screen
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("res/menu_background.jpg");
		mainMenu = new Image("res/main_menu.png");
		
	}

	/**
	 * Render the screen
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(-shiftX, -shiftY);
		g.drawString("Game Over!", (gc.getWidth() / 2), 100);
		mainMenu.draw(550,150);
		g.drawString(mouse, 0, 0);
	}

	/**
	 * Update the screen
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		int x = Mouse.getX();
		int y = Mouse.getY();
		
		mouse = "Mouse pos x: " + x + " Mouse pos y: " + y;
		Input input = gc.getInput();

		
		if ((x > 550) && (x < 870) && (y > 520 && y < 563)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(0);
			}
		}
		
		
	}
	
	/**
	 * Returns the state id
	 * @return id
	 */
	public int getID() {
		return 3;
	}
}