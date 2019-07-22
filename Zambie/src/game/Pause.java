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
 * This class controls the pause state of the game.
 */
public class Pause extends BasicGameState{
	
	Image background;
	Image resume;
	Image mainMenu;
	private float shiftX;
	private float shiftY;
	
	public String mouse = "No input yet";
	public Pause(int state) {
		
	}
	
	/**
	 * Initialize the pause screen
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("res/menu_background.jpg");
		resume = new Image("res/resume_button.png");
		mainMenu = new Image("res/main_menu.png");
		
	}
	/**
	 * Render the pause screen
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(-shiftX, -shiftY);
		g.drawString("Can You Survive?", (gc.getWidth() / 2), 100);
		resume.draw(550,150);
		mainMenu.draw(550,250);
		g.drawString(mouse, 0, 0);
	}

	/**
	 * Update the pause screen
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		int x = Mouse.getX();
		int y = Mouse.getY();
		
		mouse = "Mouse pos x: " + x + " Mouse pos y: " + y;
		Input input = gc.getInput();

		if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_P)){
			sbg.enterState(1);
		}
		if ((x > 550) && (x < 870) && (y > 520 && y < 563)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(1);
			}
		}
		
		if ((x > 550) && (x < 870) && (y > 420 && y < 465)) {
			if (input.isMouseButtonDown(0)) {
				sbg.enterState(0);
			}
		}
		
	}
	
	/**
	 * Return the state id
	 * @return 
	 */
	public int getID() {
		return 2;
	}
}



