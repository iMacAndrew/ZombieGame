package game;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Controls the label in the game
 * @author Davey Turner
 * @author Andy Humphries
 *
 */
public class Label {
	private String text;
	private float x;
	private float y;
	
	/**
	 * Render the label
	 * @param gc
	 * @param sbg
	 * @param g
	 * @throws SlickException
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		float textLength = g.getFont().getWidth(text);
		g.drawString(text, x - textLength / 2, y);
	}
}