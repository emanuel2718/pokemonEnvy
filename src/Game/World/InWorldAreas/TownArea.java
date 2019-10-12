package Game.World.InWorldAreas;

import Game.Entities.Dynamics.BaseNPC;
import Game.Entities.EntityManager;
import Game.World.Walls;
import Main.GameSetUp;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;
import java.util.Random;

public class TownArea extends BaseArea {

    Rectangle exit;
    Rectangle playerRect;
    public static boolean isInTown = false;
    private boolean clue =false;
    private int clueCounter=0;

    // Need to change this
    public final static int playerTownAreaXSpawn = -565, playerTownAreaYSpawn = -2200;

    private Rectangle background = new Rectangle(3000, 3000);

    public static ArrayList<InWorldWalls> townWalls;

    public TownArea(Handler handler, EntityManager entityManager) {
        super(handler, entityManager);
        name="Town";
        handler.setXInWorldDisplacement(playerTownAreaXSpawn);
        handler.setYInWorldDisplacement(playerTownAreaYSpawn);

        playerRect = new Rectangle((int) handler.getWidth() / 2 - 5, (int) (handler.getHeight() / 2) + 300, 70, 70);

        this.entityManager = entityManager;



        // Mewtwo
        this.entityManager.AddEntity(handler.newNPC(Images.PEnemyIdleTrainer,handler,handler.getXInWorldDisplacement()+ 1710, handler.getYInWorldDisplacement() + 2250, "Mewtwo", "Town", "BaseNPC"));


        townWalls = new ArrayList<>();
        AddWalls();

    }

    public void tick() {
        super.tick();

        for (Walls w : townWalls) {
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

        g.drawImage(Images.ScaledTown, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

        if (GameSetUp.DEBUGMODE) {
            for (Walls w : townWalls) {

                if (w.getType().equals("Wall"))
                    g2.setColor(Color.RED);
                else
                    g2.setColor(Color.PINK);

                w.render(g2);
            }
        }


        entityManager.render(g);

        // Show Arceus clue only if the Mewtwo quest is completed and player doesn't have Arceus blessing yet.
        if (BaseNPC.towerClue && !BaseNPC.arceusBlessing) {
            showClue(g);
        }

    }

    private void AddWalls() {


        /*
         * Bottom side walls
         */
        townWalls.add(new InWorldWalls(handler, 0,2875, 1700, 145, "Wall")); // Bottom horizontal left wall
        townWalls.add(new InWorldWalls(handler, 2163,2875, 1677, 145, "Wall")); // Bottom horizontal right wall

        townWalls.add(new InWorldWalls(handler, 1730,2988, 382, 30, "Town Bottom Exit")); // Bottom exit

        /*
         * Right side walls
         */
        townWalls.add(new InWorldWalls(handler, 3524,686, 316, 2332, "Wall")); // Right Big Vertical wall
        townWalls.add(new InWorldWalls(handler, 3357,1042, 165, 164, "Wall")); // Sign front of Tower
        townWalls.add(new InWorldWalls(handler, 3043,966, 574, 87, "Wall")); // Front of tower; right side
        townWalls.add(new InWorldWalls(handler, 2087,729, 793, 327, "Wall")); // Front of tower; left side
        townWalls.add(new InWorldWalls(handler, 2087,0, 214, 823, "Wall")); // Left side of tower; vertical
        townWalls.add(new InWorldWalls(handler, 1912,601, 196, 154, "Wall")); // Sign in left side of Tower

        townWalls.add(new InWorldWalls(handler, 2885,967, 148, 73, "Door Tower")); // Tower door


        /*
         * Left side walls
         */
        townWalls.add(new InWorldWalls(handler, 1172,0, 90, 259, "Wall")); // Top left vertical small wall
        townWalls.add(new InWorldWalls(handler, 636,245, 800, 648, "Wall")); // PokeCenter wall
        townWalls.add(new InWorldWalls(handler, 0,537, 756, 216, "Wall")); // Horizontal wall left of PokeCenter

        townWalls.add(new InWorldWalls(handler, 0,647, 150, 2536, "Wall")); // Left wall tiny.


        /*
         * Middle Walls
         */
        townWalls.add(new InWorldWalls(handler, 2875,1829, 705, 589, "Wall")); // PokeMart
        townWalls.add(new InWorldWalls(handler, 474,2035, 1610, 534, "Wall")); // Bottom 2 buildings
        townWalls.add(new InWorldWalls(handler, 1276,1280, 811, 528, "Wall")); // Middle Building
        townWalls.add(new InWorldWalls(handler, 1113,1656, 187, 153, "Wall")); // Middle Building left side sign
        townWalls.add(new InWorldWalls(handler, 2397,1657, 166, 155, "Wall")); // Random middle sign


        /*
         * Top wall
         */
        townWalls.add(new InWorldWalls(handler, 1274,249, 1034, 168, "Wall")); // Top horizontal wall





        /*
         * NPC Wall: Mewtwo
         */
        townWalls.add(new InWorldWalls(handler, 992,963, 109, 128, "NPC")); // Mewtwo Wall






    }
    /*
     * This will show a clue to the player so he can enter the tower and retrieve the item he needs
     * in order to defeat the Elite Four inside the Pokemon League.
     * If the player doesn't notice this "Clue", he will not be able to defeat the Elite Four, as they are REALLY strong.
     */
    private void showClue(Graphics g){
        if(!clue){
            int x = new Random().nextInt(1000);
            if (x <= 5) {
                clue=true;
                handler.getGame().getMusicHandler().playEffect("res/icons/clips/FCTM.wav",1);
            }
        }else{
            clueCounter++;
            if(clueCounter>=60){
                clueCounter=0;
                clue=false;
            }
            if (clueCounter%2 == 0) {
                g.drawImage(Images.StaticScaledTown, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

            }
        }
    }

    @Override
    public ArrayList<InWorldWalls> getWalls() {
        return townWalls;
    }
}
