package game;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import org.newdawn.slick.Animation;
import org.newdawn.slick.Image;
import org.newdawn.slick.SlickException;

/**
 * Controls the different animations in the game.
 * Player, zombie
 * @author Davey Turner
 * @author Andy Humphries
 *
 */
public class GameAnimations {
	
	private static final int PLAYER_ANIMATION_SPEED = 50;
	private static final int ZOMBIE_ANIMATION_SPEED = 100;
	
	List<Sprite> playerSprites;
	List<Sprite> zombieSprites;
	private Image playerImage;
	private Image zombieImage;

	
	/**
	 * Constructor for gameAnimations
	 * @throws SlickException
	 */
	public GameAnimations() throws SlickException {
		playerImage = new Image("res/player.png");
		zombieImage = new Image("res/whiteZombie.png");
		playerSprites = new ArrayList<>();
		zombieSprites = new ArrayList<>();
		loadSprites("res/playerAnimation.txt", playerSprites);
		loadSprites("res/zombieAnimation.txt", zombieSprites);
	}
	
	/**
	 * Loads the sprites from the external sprite sheet for animation.
	 * @param filename
	 * @param spriteArray
	 */
	private void loadSprites(String filename, List<Sprite> spriteArray) {
		
		try {
			Scanner lineScan = new Scanner(new File(filename));
			while (lineScan.hasNextLine()) {
				
				String line = lineScan.nextLine();
				Scanner scan = new Scanner(line);
				scan.useDelimiter(",");
				
				String name = scan.next();
				int topX = scan.nextInt();
				int topY = scan.nextInt();
				int width = scan.nextInt();
				int height = scan.nextInt();
				scan.close();
				
				Sprite sprite = new Sprite(name, topX, topY, width, height);
				spriteArray.add(sprite);
			}
			lineScan.close();
			
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		}
		
	}		

	/**
	 * Loads frames from spriteArray and stores them
	 * in an image array.
	 * @param spriteArray
	 * @param image
	 * @param ids
	 * @return images  images array
	 */
	private Image[] loadFrames(List<Sprite> spriteArray, Image image, int ... ids) {   // array of ints
		
		Image[] images = new Image[ids.length];
		int i = 0;
		for  (int id : ids) {
			Sprite sprite = spriteArray.get(id - 1);
			Image croppedImage = image.getSubImage(sprite.getTopX(), sprite.getTopY(), sprite.getWidth(), sprite.getHeight());
			images[i] = croppedImage;
			i++;
		}
		return images;
		
	}
	
	/**
	 * Returns animation for player pistol up
	 * @return animation
	 */
	public Animation getPlayerPistolUp() {
		return new Animation(loadFrames(playerSprites, playerImage, 191, 192, 197, 193, 198, 194, 199, 200), PLAYER_ANIMATION_SPEED);
	}
	
	/**
	 * Returns animation for player pistol down
	 * @return animation
	 */ 
	public Animation getPlayerPistolDown() {
		return new Animation(loadFrames(playerSprites, playerImage, 158, 159, 160, 161, 162, 163, 164, 165), PLAYER_ANIMATION_SPEED);
	}
	
	/**
	 * Returns animation for player pistol left
	 * @return animation
	 */
	public Animation getPlayerPistolLeft() {
		return new Animation(loadFrames(playerSprites, playerImage, 176, 177, 178, 179, 196, 180, 181, 182), PLAYER_ANIMATION_SPEED);
	}
	
	/**
	 * Returns animation for player pistol right
	 * @return animation
	 */
	public Animation getPlayerPistolRight() {
		return new Animation(loadFrames(playerSprites, playerImage, 145, 146, 147, 169, 148, 170, 149, 150), PLAYER_ANIMATION_SPEED);
	}
	
	/**
	 * Returns animation for player pistol down right
	 * @return animation
	 */
	public Animation getPlayerPistolDownRight() {
		return new Animation(loadFrames(playerSprites, playerImage, 171, 151, 152, 153, 154, 155, 156, 157), PLAYER_ANIMATION_SPEED);
	}
	
	/**
	 * Returns animation for player pistol down left
	 * @return animation
	 */
	public Animation getPlayerPistolDownLeft() {
		return new Animation(loadFrames(playerSprites, playerImage, 166, 167, 172, 168, 173, 174, 195, 175), PLAYER_ANIMATION_SPEED);
	}
	/**
	 * Returns animation for player pistol up left
	 * @return animation
	 */
	public Animation getPlayerPistolUpLeft() {
		return new Animation(loadFrames(playerSprites, playerImage, 183, 184, 185, 186, 187, 188, 189, 190), PLAYER_ANIMATION_SPEED);
	}
	/**
	 * Returns animation for player pistol up right
	 * @return animation
	 */
	public Animation getPlayerPistolUpRight() {
		return new Animation(loadFrames(playerSprites, playerImage,201, 202, 203, 223, 204, 224, 205, 206), PLAYER_ANIMATION_SPEED);
	}
	/**
	 * Returns animation for zombie up
	 * @return animation
	 */
	public Animation getZombieUp() {
		return new Animation(loadFrames(zombieSprites, zombieImage, 125, 139, 126, 140, 127, 141, 128, 129), ZOMBIE_ANIMATION_SPEED);
	}
	/**
	 * Returns animation for zombie down
	 * @return animation
	 */
	public Animation getZombieDown() {
		return new Animation(loadFrames(zombieSprites, zombieImage, 90, 91, 92, 96, 115, 116, 97, 98), ZOMBIE_ANIMATION_SPEED);
	}
	/**
	 * Returns animation for zombie left
	 * @return animation
	 */
	public Animation getZombieLeft() {
		return new Animation(loadFrames(zombieSprites, zombieImage, 106, 118, 119, 107, 108, 109, 110, 111), ZOMBIE_ANIMATION_SPEED);
	}
	/**
	 * Returns animation for zombie right
	 * @return animation
	 */
	public Animation getZombieRight() {
		return new Animation(loadFrames(zombieSprites, zombieImage, 76, 77, 78, 79, 80, 81, 82, 83), ZOMBIE_ANIMATION_SPEED);
	}
	/**
	 * Returns animation for zombie up right
	 * @return animation
	 */
	public Animation getZombieUpRight() {
		return new Animation(loadFrames(zombieSprites, zombieImage, 130, 131, 132, 133, 142, 134, 135, 136), ZOMBIE_ANIMATION_SPEED);
	}
	/**
	 * Returns animation for zombie up left
	 * @return animation
	 */
	public Animation getZombieUpLeft() {
		return new Animation(loadFrames(zombieSprites, zombieImage, 112, 113, 114, 120, 121, 122, 123, 124), ZOMBIE_ANIMATION_SPEED);
	}
	/**
	 * Returns animation for zombie down right
	 * @return animation
	 */
	public Animation getZombieDownRight() {
		return new Animation(loadFrames(zombieSprites, zombieImage, 84, 85, 86, 87, 94, 88, 95, 89), ZOMBIE_ANIMATION_SPEED);
	}
	/**
	 * Returns animation for zombie down left
	 * @return animation
	 */
	public Animation getZombieDownLeft() {
		return new Animation(loadFrames(zombieSprites, zombieImage, 99, 100, 101, 102, 103, 104, 117, 105), ZOMBIE_ANIMATION_SPEED);
	}
}
