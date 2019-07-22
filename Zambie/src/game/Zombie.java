package game;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Vector2f;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class entails all of the information
 * for the zombie objects.
 * @author Davey Turner
 * @author Andy Humphries
 *
 */
public class Zombie extends GameObject {
	float speed = 1 + (float)Math.random();
	private Animation animation;
	private GameAnimations animations;
	Animation up, down, left, right, upLeft, upRight, downLeft, downRight;
	private Player player;
	boolean dead;
	private ZombieManager zombieManager;
	
	private static final int HIT_RATE = 2000; // ms
	private int hitWait = 0;

	/**
	 * Constructor
	 * @param x
	 * @param y
	 * @param animations
	 * @param player
	 * @param zombieManager
	 * @throws SlickException
	 */
	public Zombie(float x, float y, GameAnimations animations, Player player, ZombieManager zombieManager) throws SlickException {
		this.player = player;
		this.zombieManager = zombieManager;
		this.animations = animations;
		setX(x);
		setY(y);
		setScale(2);
		up = animations.getZombieUp();
		down = animations.getZombieDown();
		left = animations.getZombieLeft();
		right = animations.getZombieRight();
		upLeft = animations.getZombieUpLeft();
		upRight = animations.getZombieUpRight();
		downLeft = animations.getZombieDownLeft();
		downRight = animations.getZombieDownRight();
		animation = down;
		dead = false;
	}

	/**
	 * Render the animation for the zombie if it is not dead
	 * @param gc
	 * @param sbg
	 * @param g
	 * @throws SlickException
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		if (!dead) {
			animation.getCurrentFrame().draw(getX(), getY(), getScale());
		}
	}

	/**
	 * Updates the information for the zombie if it is not dead.
	 * @param gc
	 * @param delta
	 * @param shiftX
	 * @param shiftY
	 * @throws SlickException
	 */
	public void update(GameContainer gc, int delta, float shiftX, float shiftY) throws SlickException {

		if (!dead) {
			hitWait += delta;
			animation.update(delta);

			double distance = this.findDistance(player);
			double theta = this.getTheta(player);
			
			if (distance < 50) {
				theta += Math.PI / 2;
			}
			
			double dx = distance * Math.sin(theta);
			double dy = distance * Math.cos(theta);
			Vector2f vector = new Vector2f((float) dx, (float) dy).normalise();

			animation = animationForAngle(Math.toDegrees(theta) + 90);

		
			y = y + (vector.y * speed) + shiftY;
			x = x - (vector.x * speed) + shiftX;
		}
	}

	/**
	 * Controls which animation to set the zombie to
	 * by what angle it is at.
	 * @param angle
	 * @return animation
	 */
	public Animation animationForAngle(double angle) {
		Animation anim = upRight;
		if (angle < 22.5 || angle > 337.5) {
			anim = right;
		} else if (angle >= 22.5 && angle < 67.5) {
			anim = downRight;
		} else if (angle >= 67.5 && angle < 112.5) {
			anim = down;
		} else if (angle >= 112.5 && angle < 157.5) {
			anim = downLeft;
		} else if (angle >= 157.5 && angle < 202.5) {
			anim = left;
		} else if (angle >= 202.5 && angle < 247.5) {
			anim = upLeft;
		} else if (angle >= 247.5 && angle < 292.5) {
			anim = up;
		}

		return anim;
	}

	/**
	 * Sets the zombie animation
	 * @param _animation
	 */
	public void setAnimation(Animation _animation) {
		this.animation = _animation;
	}

	/**
	 * Returns the zombie animation
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
	 * Returns animation width
	 * @return width or 0
	 */
	public float getWidth() {
		if (animation != null) {
			return animation.getCurrentFrame().getWidth() * getScale();
		} else {
			return 0;
		}
	}

	/**
	 * Returns the height of the animation
	 * @return height or 0
	 */
	public float getHeight() {
		if (animation != null) {
			return animation.getCurrentFrame().getHeight() * getScale();
		} else {
			return 0;
		}
	}
	
	public boolean canHit() {
		return hitWait > HIT_RATE;
	}
	
	public void hit() {
		hitWait = 0;
	}

	/**
	 * Controls what happens when the zombie dies.
	 */
	public void die() {
		if (!dead) {
			dead = true;
			try { 
				zombieManager.addZombie(player, animations);
				zombieManager.addZombie(player, animations);
			} catch (SlickException e) {
				e.printStackTrace();
			}
		}
	}
}
