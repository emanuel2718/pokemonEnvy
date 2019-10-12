package Game.Entities.Statics;

import Game.World.InWorldAreas.CaveArea;
import Main.Handler;
import Resources.Animation;
import Resources.Images;

import java.awt.*;

public class LightStatue extends BaseStaticEntity {

	private Rectangle collision;
	private Animation light;
	private int width, height;

	public LightStatue(Handler handler, int xPosition, int yPosition) {
		super(handler, xPosition, yPosition);

		width = 16*10;
		height = 48*10;

		this.setXOffset(xPosition);
		this.setYOffset(yPosition);

		light = new Animation(200, Images.lightStatue);
		collision = new Rectangle();
	}


	@Override
	public void tick() {
		light.tick();
	}

	@Override
	public void render(Graphics g) {
	    // Now the Light statute will only appear inside the cave.
		if(CaveArea.isInCave)
			g.drawImage(light.getCurrentFrame(), (int)(handler.getXInWorldDisplacement() + xPosition),(int)( handler.getYInWorldDisplacement() + yPosition), width, height, null);
	}

	@Override
	public Rectangle getCollision() {
		return collision;
	}




}