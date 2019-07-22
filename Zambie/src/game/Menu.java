package game;

import org.newdawn.slick.*;
import org.newdawn.slick.state.*;
import org.lwjgl.input.Mouse;

/**
 * The menu screen for the game.
 * Select play now or exit
 * @author Davey Turner
 * @author Andy Humphries
 *
 */
public class Menu extends BasicGameState {

	public String mouse = "No input yet";
	
	Image playNow;
	Image exitGame;
	Image background;

	float shiftX;
	float shiftY;
	public Menu(int state) {

	}

	/**
	 * Initialize the menu.
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		background = new Image("res/menu_background.jpg");
		playNow = new Image("res/play_button.png");
		exitGame = new Image("res/exit_button.png");
	}

	/**
	 * Render the menu
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		background.draw(-shiftX, -shiftY);
		g.drawString("Can You Survive?", (gc.getWidth() / 2), 100);
		playNow.draw(100,150);
		exitGame.draw(100,250);
	}

	/**
	 * Update the menu depending on the option chosen
	 * Play game or exit game
	 */	
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		int x = Mouse.getX();
		int y = Mouse.getY();
		mouse = "Mouse pos x: " + x + " Mouse pos y: " + y;

		Input input = gc.getInput();
		
		if ((x > 170) && (x < 360) && (y > 520 && y < 563)) {
			if (input.isMouseButtonDown(0)) {
				sbg.getState(1).init(gc, sbg);
				sbg.enterState(1);
			}
		}
		
		if ((x > 170) && (x < 360) && (y > 420 && y < 465)) {
			if (input.isMouseButtonDown(0)) {
				System.exit(0);
			}
		}


	}

	/**
	 * return the state id
	 * @return 0, state id
	 */
	public int getID() {
		return 0;
	}
}
