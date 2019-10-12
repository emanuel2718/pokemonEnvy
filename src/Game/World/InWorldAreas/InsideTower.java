package Game.World.InWorldAreas;

import Game.Entities.EntityManager;
import Game.World.Walls;
import Main.GameSetUp;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

public class InsideTower extends BaseArea {

    Rectangle exit;
    Rectangle playerRect;
    public static boolean isInTower = false;

    // Need to change this
    public final static int playerInsideTowerXSpawn = -475, playerInsideTowerYSpawn = -2950;

    private Rectangle background = new Rectangle(3000, 3000);

    public static ArrayList<InWorldWalls> towerWalls;

    public InsideTower(Handler handler, EntityManager entityManager) {
        super(handler, entityManager);
        name="Tower";
        handler.setXInWorldDisplacement(playerInsideTowerXSpawn);
        handler.setYInWorldDisplacement(playerInsideTowerYSpawn);

        playerRect = new Rectangle((int) handler.getWidth() / 2 - 5, (int) (handler.getHeight() / 2) + 300, 70, 70);

        this.entityManager = entityManager;



        // Arceus
        this.entityManager.AddEntity(handler.newNPC(Images.PEnemyIdleTrainer,handler,handler.getXInWorldDisplacement()+ 1710, handler.getYInWorldDisplacement() + 2250, "Arceus", "Tower", "BaseNPC"));


        towerWalls = new ArrayList<>();
        AddWalls();

    }

    public void tick() {
        super.tick();

        for (Walls w : towerWalls) {
            w.tick();
        }
        if(!GameSetUp.LOADING) {
            entityManager.tick();
        }

        /*
         * Add debug key that turns on and off Debug mode inside caves.
         */
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_F7)) {
            GameSetUp.DEBUGMODE = true;
        }
        if (handler.getKeyManager().keyJustPressed(KeyEvent.VK_F8)) {
            GameSetUp.DEBUGMODE = false;
        }
    }

    @Override
    public void render(Graphics g) {
        super.render(g);


        Graphics2D g2 = (Graphics2D) g;

        g2.setColor(Color.black);
        g2.fill(background);

        g.drawImage(Images.ScaledTower, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

        if (GameSetUp.DEBUGMODE) {
            for (Walls w : towerWalls) {

                if (w.getType().equals("Wall"))
                    g2.setColor(Color.RED);
                else
                    g2.setColor(Color.PINK);

                w.render(g2);
            }
        }


        entityManager.render(g);


    }

    private void AddWalls() {

        /*
         * TOP WALLS
         */
        towerWalls.add(new InWorldWalls(handler, 165,600, 3110, 182, "Wall")); // Top horizontal wall
        towerWalls.add(new InWorldWalls(handler, 369,747, 237, 195, "Wall")); // Top 1st wall; Left to right
        towerWalls.add(new InWorldWalls(handler, 1032,768, 219, 176, "Wall")); // Top 2nd wall
        towerWalls.add(new InWorldWalls(handler, 1671,764, 213, 169, "Wall")); // Top 3rd wall
        towerWalls.add(new InWorldWalls(handler, 2312,771, 211, 167, "Wall")); // Top 4th wall
        towerWalls.add(new InWorldWalls(handler, 2951,783, 282, 156, "Wall")); // Top 5th wall

        /*
         * RIGHT WALLS
         */
        towerWalls.add(new InWorldWalls(handler, 164,843, 261, 2907, "Wall")); // Right vertical wall

        /*
         * RIGHT WALLS
         */
        towerWalls.add(new InWorldWalls(handler, 3164,236, 116, 3514, "Wall")); // Left  vertical wall



    	/*
    	 * BOTTOM WALLS
    	 */
    	
    	 towerWalls.add(new InWorldWalls(handler, 266,3640, 1386, 93, "Wall")); // Bottom horizontal left wall
    	 towerWalls.add(new InWorldWalls(handler, 2000,3640, 1200, 93, "Wall"));//Bottom horizontal right wall
    	 towerWalls.add(new InWorldWalls(handler, 1650,3640, 350, 93, "Tower Exit"));//Bottom exit


        /*
         * MIDDLE WALLS
         */
        towerWalls.add(new InWorldWalls(handler, 1047,2466, 218, 978, "Wall")); // Left statue wall
        towerWalls.add(new InWorldWalls(handler, 2318,2457, 220, 978, "Wall")); // Right statue wall
        towerWalls.add(new InWorldWalls(handler, 518,2352, 1162, 176, "Wall")); // Stage bottom left wall
        towerWalls.add(new InWorldWalls(handler, 1931,2358, 1137, 152, "Wall")); // Stage bottom right wall


        /*
         * ROCK WALLS
         */
        towerWalls.add(new InWorldWalls(handler, 893,1299, 288, 91, "Wall")); // Top left rock wall
        towerWalls.add(new InWorldWalls(handler, 1067,1360, 174, 99, "Wall")); // Top left rock wall
        towerWalls.add(new InWorldWalls(handler, 1016,1251, 117, 69, "Wall")); // Top left rock wall

        towerWalls.add(new InWorldWalls(handler, 867,1594, 89, 69, "Wall")); // Bottom left rock wall

        towerWalls.add(new InWorldWalls(handler, 2030,1091, 118, 60, "Wall")); // Top right rock wall
        towerWalls.add(new InWorldWalls(handler, 2235,1155, 141, 68, "Wall")); // Top right rock wall
        towerWalls.add(new InWorldWalls(handler, 2384,1256, 48, 42, "Wall")); // Top right rock wall
        towerWalls.add(new InWorldWalls(handler, 2619,1302, 81, 71, "Wall")); // Top right rock wall
        towerWalls.add(new InWorldWalls(handler, 2432,1451, 162, 186, "Wall")); // Top right rock wall


        /*
         * NPC Wall: Arceus
         */
        towerWalls.add(new InWorldWalls(handler, 1740,1810, 108, 128, "NPC")); // Arceus Wall

    
      





    }

    @Override
    public ArrayList<InWorldWalls> getWalls() {
        return towerWalls;
    }
}
