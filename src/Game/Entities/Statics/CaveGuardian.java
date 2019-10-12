package Game.Entities.Statics;

import Game.Entities.Dynamics.BaseNPC;
import Main.Handler;
import Resources.Images;

import java.awt.*;

public class CaveGuardian extends BaseStaticEntity {

    Rectangle collision;
    int width, height;

    public CaveGuardian(Handler handler, int xPosition, int yPosition) {
        super(handler, xPosition, yPosition);
        width = 100;
        height = 100;

        this.setXOffset(xPosition);
        this.setYOffset(yPosition);


        collision = new Rectangle();
    }


    @Override
    public void render(Graphics g) {
        // Draw Cave Guardian in front of the cave when the quest hasn't been completed.
        // Draw him besides the cave entrance when the main quest has been completed.
        if (!BaseNPC.questCompleted) {
            g.drawImage(Images.caveGuardian, (int)(handler.getXDisplacement() + xPosition),(int)( handler.getYDisplacement() + yPosition), width, height, null);
            g.setFont(new Font("Serif",Font.PLAIN,12));
            g.setColor(Color.WHITE);
            g.drawString("Zzzzz...", (int)(handler.getXDisplacement() + xPosition + 10), (int)(handler.getYDisplacement() + yPosition + 20));

        } else {
            // Woke Snorlax
            g.drawImage(Images.caveGuardian, (int)(handler.getXDisplacement() + xPosition + 160),(int)( handler.getYDisplacement() + yPosition), width, height, null);
            g.setFont(new Font("Serif",Font.PLAIN,12));

        }
    }

    @Override
    public Rectangle getCollision() {
        return collision;
    }

    @Override
    public double getXOffset() {
        return xPosition;
    }


}
