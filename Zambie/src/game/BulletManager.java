package game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Color;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class entails our linked list for the bullets, which helps us manage the
 * bullets on the screen.
 * 
 * @author Andy Humpheries
 * @author Davey Turner
 *
 */
public class BulletManager {

	List<Bullet> bullets = new LinkedList<Bullet>();

	private static final int SPEED = 10;

	/**
	 * Renders the bullet for the screen
	 * 
	 * @param gc
	 * @param sbg
	 * @param g
	 * @throws SlickException
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Iterator<Bullet> iter = bullets.iterator();
		while (iter.hasNext()) {
			Bullet bullet = iter.next();
			bullet.getImage().draw(bullet.getX(), bullet.getY(), bullet.getScale());
		}
	}

	/**
	 * updates the bullet information
	 * 
	 * @param gc
	 * @param delta
	 * @param shiftX
	 * @param shiftY
	 * @throws SlickException
	 */
	public void update(GameContainer gc, int delta, float shiftX, float shiftY) throws SlickException {
		Iterator<Bullet> iter = bullets.iterator();
		while (iter.hasNext()) {
			Bullet bullet = iter.next();
			if (bullet.getX() < 0 || bullet.getX() > gc.getWidth() || bullet.getY() < 0
					|| bullet.getY() > gc.getHeight()) {
				iter.remove();
			} else {
				bullet.move(shiftX, shiftY);
			}
		}

	}

	/**
	 * Add bullet to the screen
	 * 
	 * @param player
	 * @throws SlickException
	 */
	public void addBullet(Player player) throws SlickException {

		float dx = 0;
		float dy = SPEED;
		int x = 0;
		int y = 0;
		String imageName = "Bullet.png";

		if (player.getAnimation() == player.up) {
			dx = 0;
			dy = -SPEED;
			x = 40;
		}

		if (player.getAnimation() == player.down) {
			dx = 0;
			dy = SPEED;
			x = 2;
			y = 50;
			imageName = "BulletDown.png";
		}

		if (player.getAnimation() == player.left) {
			dx = -SPEED;
			dy = 0;
			x = -15;
			y = 25;
			imageName = "BulletLeft.png";
		}
		if (player.getAnimation() == player.right) {
			dx = SPEED;
			dy = 0;
			x = 53;
			y = 43;
			imageName = "BulletRight.png";
		}

		if (player.getAnimation() == player.upLeft) {
			dx = -SPEED;
			dy = -SPEED;
			x = -5;
			imageName = "BulletUpLeft.png";

		}

		if (player.getAnimation() == player.upRight) {
			dx = SPEED;
			dy = -SPEED;
			x = 70;
			y = 5;
			imageName = "BulletUpRight.png";
		}

		if (player.getAnimation() == player.downLeft) {
			dx = -SPEED;
			dy = SPEED;
			x = -12;
			y = 40;
			imageName = "BulletDownLeft.png";
		}

		if (player.getAnimation() == player.downRight) {
			dx = SPEED;
			dy = SPEED;
			x = 22;
			y = 50;
			imageName = "BulletDownRight.png";
		}

		Bullet bullet = new Bullet(imageName, player.getX() + x, player.getY() + y, dx, dy);
		bullets.add(bullet);

	}

	/**
	 * REturns a list of bullets
	 * 
	 * @return bullets list of bullets
	 */
	public List<Bullet> getBullets() {
		return bullets;
	}

}
