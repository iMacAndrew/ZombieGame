package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class controls the player object in the game.
 * @author Davey Turner
 * @author Andy Humphries
 *
 */
public class Player extends GameObject {
	private Animation animation;
	Animation up, down, left, right, upLeft, upRight, downLeft, downRight;
	
	static final int MAX_HEALTH = 10;
	private int health = MAX_HEALTH;
	
	/**
	 * constructor
	 * @param x
	 * @param y
	 * @param animations
	 * @throws SlickException
	 */
	public Player(float x, float y, GameAnimations animations) throws SlickException {
		setX(x);
		setY(y);
		setScale(2);
		up = animations.getPlayerPistolUp();
		down = animations.getPlayerPistolDown();
		left = animations.getPlayerPistolLeft();
		right = animations.getPlayerPistolRight();
		upLeft = animations.getPlayerPistolUpLeft();
		upRight = animations.getPlayerPistolUpRight();
		downLeft = animations.getPlayerPistolDownLeft();
		downRight = animations.getPlayerPistolDownRight();
		animation = down;
	}
	
	/**
	 * render the player
	 * @param gc
	 * @param sbg
	 * @param g
	 * @throws SlickException
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		animation.getCurrentFrame().draw(getX(), getY(), getScale());
		
	}

	/**
	 * update the player and its animation
	 * @param gc
	 * @param sbg
	 * @param delta
	 * @throws SlickException
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {
		animation.update(delta);
	}
	
	/**
	 * Set animation
	 * @param _animation
	 */
	public void setAnimation(Animation _animation) {
		this.animation = _animation;
	}
	
	/**
	 * Get animation
	 * @return animation
	 */
	public Animation getAnimation() {
		return animation;
	}
	
	/**
	 * Stop animation
	 */
	public void stopAnimation() {
		this.animation.stop();
	}
	
	/**
	 * Start animation
	 */
	public void startAnimation() {
		this.animation.start();
	}
	
	/**
	 * Return width of the animation
	 * @return 0 or animation width
	 */
	public float getWidth() {
		if (animation != null) {
			return animation.getCurrentFrame().getWidth() * getScale();
		} else {
			return 0;
		}
	}

	/**
	 * Gets the height of the object
	 * 
	 * @return the height
	 */
	public float getHeight() {
		if (animation != null) {
			return animation.getCurrentFrame().getHeight() * getScale();
		} else {
			return 0;
		}
	}
	
	/**
	 * If zombie is on player the player
	 * wll take damage
	 * @param damage
	 */
	public void takeDamage(int damage) {
		health -= damage;
	}
	
	/**
	 * Get health, returns health
	 * @return health 
	 */
	public int getHealth() {
		return health;
	}
}
