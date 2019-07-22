package game;

import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.geom.Rectangle;
import org.newdawn.slick.geom.Vector2f;

/**
 * This is the template file for anything that is rendered graphically to the
 * screen
 * 
 * @author Chris Mays
 *@author Davey Turner
 *@author Andy Humphries
 */
public class GameObject {
	protected float x = 0;// The x of the game object
	protected float y = 0; // The y of the game object
	private float xVelocity = 0; // The xVelocity of the gameobject
	private float yVelocity = 0; // The yVelocity of the gameobject
	private float scale = 1.0f; // The scale of the game object
	private Image objImage; // The image of the object

	public float getX() {
		return x;
	}

	/**
	 * Gets the y value of the object
	 * 
	 * @return the y
	 */
	public float getY() {
		return y;
	}

	/**
	 * Gets the x velocity
	 * 
	 * @return the x velocity
	 */
	public float getXVelocity() {
		return xVelocity;
	}

	/**
	 * Gets the y velocity
	 * 
	 * @return the y velocity
	 */
	public float getYVelocity() {
		return yVelocity;
	}

	/**
	 * Gets the width of the game object
	 * 
	 * @return the width of the object
	 */
	public float getWidth() {
		if (objImage != null) {
			return objImage.getWidth() * getScale();
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
		if (objImage != null) {
			return objImage.getHeight() * getScale();
		} else {
			return 0;
		}
	}

	/**
	 * gets the image
	 * 
	 * @return image
	 */
	public Image getImage() {
		return objImage;
	}

	/**
	 * Sets the x of the object
	 * 
	 * @param x
	 *            the x of the object
	 */
	public void setX(float x) {
		this.x = x;
	}

	/**
	 * Sets the y of the object
	 * 
	 * @param y
	 *            the y of the object
	 */
	public void setY(float y) {
		this.y = y;
	}

	/**
	 * Sets the xvelocity of the object
	 * 
	 * @param xVelocity
	 *            the xvelocity of the object
	 */
	public void setXVelocity(float xVelocity) {
		this.xVelocity = xVelocity;
	}

	/**
	 * Sets the y velocity of the gameobject
	 * 
	 * @param yVelocity
	 *            the yvelocity of the
	 */
	public void setYVelocity(float yVelocity) {
		this.yVelocity = yVelocity;
	}

	/**
	 * Sets the image of the game object
	 * 
	 * @param filename
	 *            the filename of the image
	 */
	public void setImage(String filename) {
		try {
			objImage = new Image("res/" + filename);

		} catch (SlickException e) {
			System.err.print("error" + e);
		}

	}

	/**
	 * gets the rectangle of the object
	 * 
	 * @return rectangle of the bounds
	 */
	public Rectangle getRect() {
		return new Rectangle(getX(), getY(), getWidth(), getHeight());
	}

	/**
	 * Gets the scale of the object
	 * 
	 * @return the scale
	 */
	public float getScale() {
		return scale;
	}

	/**
	 * Sets the scale of the object
	 * 
	 * @param scale
	 *            the scale of the object
	 */
	public void setScale(float scale) {
		this.scale = scale;
	}
	
	/**
	 * 
	 * @param go
	 * @return
	 */
	public double findDistance(GameObject go) {
		float dx = this.getX() - go.getX();
		float dy = this.getY() - go.getY();
		
		return Math.sqrt(dx*dx + dy*dy);	
	}
	
	/**
	 * 
	 * @param go
	 * @return
	 */
	public double getTheta(GameObject go) {
		float dx = go.getX() - this.getX();
		float dy = go.getY() - this.getY();
		Vector2f vector = new Vector2f(dx,dy);
		
		return Math.toRadians(vector.getTheta() - 90);
	}
	
	/**
	 * Checks if objects are intersecting.
	 * @param go
	 * @return boolean  intersecting or not.
	 */
	public boolean collidesWith(GameObject go) {
		
		return this.getRect().intersects(go.getRect()) || this.getRect().contains(go.getRect());
	}
}