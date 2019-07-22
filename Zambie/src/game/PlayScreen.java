package game;

import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;

import org.newdawn.slick.Animation;
import org.newdawn.slick.GameContainer;
import org.newdawn.slick.Graphics;
import org.newdawn.slick.Image;
import org.newdawn.slick.Input;
import org.newdawn.slick.SlickException;
import org.newdawn.slick.state.BasicGameState;
import org.newdawn.slick.state.StateBasedGame;

/**
 * This class controls everything that happens on the play
 * screen. 
 * @author Andy Humphries
 * @author Davey Turner
 *
 */
public class PlayScreen extends BasicGameState {
	
	Player player;
	BulletManager bulletManager;
	ZombieManager zombieManager;
	
	
	final float PLAYER_SPEED = .2f;
	
	Image map;

	float shiftX = 1547;
	float shiftY = 907;
	private int zombieCount = 0;
	String zombieC = "Zombies Dead: 0";;
	public PlayScreen(int state) {

	}

	/**
	 * This method initializes the playing screen.
	 * @param gc
	 * @param sbg
	 * @throws SlickException
	 */
	public void init(GameContainer gc, StateBasedGame sbg) throws SlickException {
		GameAnimations animations = new GameAnimations();
		player = new Player(gc.getWidth() / 2, gc.getHeight()/2, animations);
		map = new Image("res/map.png");
		bulletManager = new BulletManager();
		zombieManager = new ZombieManager(map);
		

		zombieManager.addZombie(player,  animations);
	}

	/**
	 * Renders the objects for the play screen.
	 *  @param gc
	 * @param sbg
	 * @throws SlickException
	 */
	public void render(GameContainer gc, StateBasedGame sbg, Graphics g) throws SlickException {
		map.draw(-shiftX, -shiftY);
		bulletManager.render(gc, sbg, g);
		zombieManager.render(gc, sbg, g);
		player.render(gc, sbg, g);	
		g.drawString(zombieC, 400, 20);
		g.drawString("Health: " + player.getHealth(), 600, 20);
	}
	
	/**
	 * This method updates the objects for the playing screen
	 * before they get rendered.
	 * @param gc
	 * @param sbg
	 * @throws SlickException
	 */
	public void update(GameContainer gc, StateBasedGame sbg, int delta) throws SlickException {

		Input input = gc.getInput();
		
		//Allows the user to hit P or ESC to pause the game.
		if (input.isKeyPressed(Input.KEY_ESCAPE) || input.isKeyPressed(Input.KEY_P)){
			sbg.enterState(2);
		}
		

		// WASD && arrow key movement
		
		boolean up = input.isKeyDown(Input.KEY_W) || input.isKeyDown(Input.KEY_UP) && shiftY > -393;
		boolean down = input.isKeyDown(Input.KEY_S) || input.isKeyDown(Input.KEY_DOWN) && shiftY < 1420;
		boolean left = input.isKeyDown(Input.KEY_A) || input.isKeyDown(Input.KEY_LEFT) && shiftX > -640;
		boolean right = input.isKeyDown(Input.KEY_D) || input.isKeyDown(Input.KEY_RIGHT) && shiftX < 2454;
		
		player.startAnimation();
		
		float oldShiftX = shiftX;
		float oldShiftY = shiftY;
		
		//Controls the movement of the player.
		if (up && left) {
			shiftY -= delta * PLAYER_SPEED * Math.sin(45);
			shiftX -= delta * PLAYER_SPEED * Math.cos(45);
			player.setAnimation(player.upLeft);
		} else if (up && right) {
			shiftY -= delta * PLAYER_SPEED * Math.sin(45);		
			shiftX += delta * PLAYER_SPEED* Math.cos(45);
			player.setAnimation(player.upRight);
		} else if (down && left) {
			shiftY += delta * PLAYER_SPEED * Math.sin(45);
			shiftX -= delta * PLAYER_SPEED * Math.cos(45);
			player.setAnimation(player.downLeft);
		} else if (down && right) {
			shiftY += delta * PLAYER_SPEED * Math.sin(45);
			shiftX += delta * PLAYER_SPEED * Math.cos(45);
			player.setAnimation(player.downRight);
		} else if (up) {
			shiftY -= delta * PLAYER_SPEED;	
			player.setAnimation(player.up);
		} else if (left) {
			shiftX -= delta * PLAYER_SPEED;
			player.setAnimation(player.left);
		} else if (down) {
			shiftY += delta * PLAYER_SPEED;
			player.setAnimation(player.down);
		} else if (right) {
			shiftX += delta * PLAYER_SPEED;
			player.setAnimation(player.right);
		} else {
			player.stopAnimation();
		}
			
		if (input.isKeyPressed(Input.KEY_SPACE)) {
			bulletManager.addBullet(player);
		}
		
		
		player.update(gc, sbg, delta);

		bulletManager.update(gc, delta, oldShiftX - shiftX, oldShiftY - shiftY);
		zombieManager.update(gc, delta, oldShiftX - shiftX, oldShiftY - shiftY, player);
		
		//Iterator for the bullets
		Iterator<Bullet> bulletIter = bulletManager.getBullets().iterator();
		
		Boolean done = false;
		while (bulletIter.hasNext() && !done) {
			Bullet bullet = bulletIter.next();
			
			//when a zombie is hit by a bullet, remove zombie and add one to the count.
			for (int i = 0; i < zombieManager.getZombies().size() && !done; i++) {
				if (bullet.collidesWith(zombieManager.getZombies().get(i))) {
					bulletIter.remove();
					zombieManager.getZombies().get(i).die();
					zombieCount++;
					zombieC = "Zombies Dead: " + zombieCount;
					done = true;
				}
				
			}
		}
		
		//If the players health is 0 game is over
		if (player.getHealth() <= 0) {
			sbg.enterState(Game.gameOverScreen);
		}
		
	}
	
	
	/**
	 * returns the state number
	 * @return 1
	 */
	public int getID() {
		return 1;
	}

}
