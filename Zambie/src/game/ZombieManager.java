package game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.StateBasedGame;

/**
 * Manages the zommbies on the play screen.
 * @author Davey Turner
 * @author Andy Humphries
 *
 */
public class ZombieManager {

	List<Zombie> zombies = new LinkedList<Zombie>();
	private Image map;
	
	/**
	 * Constructor for zombie manager
	 * @param map
	 */
	public ZombieManager(Image map) {
		this.map = map;
	}
	
	/**
	 * Renders the zombies for the play screen
	 * @param gc
	 * @param sbg
	 * @param g
	 * @throws SlickException
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		Iterator<Zombie> iter = zombies.iterator();
		while (iter.hasNext()) {
			Zombie zombie = iter.next();
			zombie.render(gc, sbg, g);
		}
	}

	/**
	 * Updates the zombies. If dead remove it, else update the position.
	 * @param gc
	 * @param delta
	 * @param shiftX
	 * @param shiftY
	 * @throws SlickException
	 */
	public void update(GameContainer gc, int delta, float shiftX, float shiftY, Player player) throws SlickException {
		Iterator<Zombie> iter = zombies.iterator();
		while (iter.hasNext()) {
			Zombie zombie = iter.next();
			
			if (zombie.dead) {
				iter.remove();
			} else {
				zombie.update(gc, delta, shiftX, shiftY);
				
				if (zombie.canHit() && zombie.collidesWith(player)) {
					player.takeDamage(1);
					zombie.hit();
				}
			}
		}		
	}
	
	/**
	 * Add zombies to the screen
	 * @param player
	 * @param animations
	 * @throws SlickException
	 */
	public void addZombie(Player player, GameAnimations animations) throws SlickException {
		int[] corner = getRandomCorner();
		Zombie zombie = new Zombie(corner[0], corner[1], animations, player, this);
		zombies.add(zombie);
	}
	
	/**
	 * REturns a list of zombies
	 * @return zombies   list of zombies
	 */
	public List<Zombie> getZombies() {
		return zombies;
	}
	
	/**
	 * Gets the location for random corners of the map
	 * to spawn zombies
	 * @return int array for corner location.
	 */
	private int[] getRandomCorner() {
		int[][] corners = new int[4][2];
		
		int offset = 20;
		
		corners[0] = new int[] {-offset, -offset};
		corners[1] = new int[] {-offset, map.getHeight() - offset};
		corners[2] = new int[] {map.getWidth() - offset, -offset};
		corners[3] = new int[] {map.getWidth() - offset, map.getHeight() - offset};
		
		Random rand = new Random();
		return corners[rand.nextInt(corners.length)];
	}
}
