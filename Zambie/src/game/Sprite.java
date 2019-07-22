package game;

public class Sprite {
	private String name;
	private int topX;
	private int topY;
	private int width;
	private int height;
	
	
	/**
	 * Returns info of the sprite
	 * @param name
	 * @param topX
	 * @param topY
	 * @param width
	 * @param height
	 */
	public Sprite(String name, int topX, int topY, int width, int height) {
		super();
		this.topX = topX;
		this.topY = topY;
		this.width = width;
		this.height = height;
		this.name = name;
	}

	/**
	 * Returns top x value for the sprite
	 * @return topX
	 */
	public int getTopX() {
		return topX;
	}

	/**
	 * returns the topY
	 * @return topY
	 */
	public int getTopY() {
		return topY;
	}

	/**
	 * Returns the width of the animation
	 * @return width
	 */
	public int getWidth() {
		return width;
	}

	/**
	 * Returns the height of the animation.
	 * @return height
	 */
	public int getHeight() {
		return height;
	}

	/**
	 * Returns the name of the animation
	 * @return name
	 */
	public String getName() {
		return name;
	}
	
	

}
