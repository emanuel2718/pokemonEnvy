package Game.Entities.Statics;

import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;

public class SmokeHouse extends BaseStaticEntity{

	Rectangle collision;
	Animation smoke;
	int width, height;
	
	public SmokeHouse(Handler handler, int xPosition, int yPosition) {
		super(handler, xPosition, yPosition);
		
		width = 130;
		height = 200;
		

		this.setXOffset(xPosition);
		this.setYOffset(yPosition);
		
		
		smoke = new Animation(200, Images.smokeHouse);
		collision = new Rectangle();
	}
	
	@Override
	public void tick() {
		smoke.tick();
	}
	
	@Override
	public void render(Graphics g) {
		g.drawImage(smoke.getCurrentFrame(), (int)(handler.getXDisplacement() + xPosition),(int)( handler.getYDisplacement() + yPosition), width, height, null);
	}
	
	@Override
	public Rectangle getCollision() {
		return collision;
	}
	

}
