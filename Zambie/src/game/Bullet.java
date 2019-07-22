package game;

/**
 * The bullet object for the game.
 * @author Andy Humphries
 * @author Davey Turner
 */
public class Bullet extends GameObject {
		
	/**
	 * Constructor for a Bullet
	 * @param image
	 * @param x
	 * @param y
	 * @param dx
	 * @param dy
	 */
	public Bullet(String image, float x, float y, float dx, float dy) {
		super();
		setImage(image);
		setX(x);
		setY(y);
		setXVelocity(dx);
		setYVelocity(dy);
		setScale(1.5f);
	}

	/**
	 * Updates the movement of the bullet
	 * on screen.
	 * @param xOffset
	 * @param yOffset
	 */
	public void move(float xOffset, float yOffset) {
		x += this.getXVelocity() + xOffset;
		y += this.getYVelocity() + yOffset;
	}
	
	/**
	 * calls the move method with 0,0 
	 * as a parameter.
	 */
	public void move() {
		move(0, 0);
	}
	
}
