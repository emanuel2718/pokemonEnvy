package Game.World.InWorldAreas;

import Game.Entities.EntityManager;
import Game.World.Walls;
import Main.GameSetUp;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.util.ArrayList;

/*
 * Created by Emanuel Ramirez on 03/15/2019
 */
public class PokemonLeagueArea extends BaseArea {

    Rectangle exit;
    Rectangle playerRect;
    public static boolean isInPokemonLeagueArea = false;

    // Need to change this
    public final static int playerPLAreaXSpawn = -520, playerPLAreaYSpawn = -2450;

    private Rectangle background = new Rectangle(3000, 3000);

    public static ArrayList<InWorldWalls> pokemonLeagueAreaWalls;

    public PokemonLeagueArea(Handler handler, EntityManager entityManager) {
        super(handler, entityManager);
        name="PokemonLeagueArea";
        handler.setXInWorldDisplacement(playerPLAreaXSpawn);
        handler.setYInWorldDisplacement(playerPLAreaYSpawn);

        playerRect = new Rectangle((int) handler.getWidth() / 2 - 5, (int) (handler.getHeight() / 2) + 300, 70, 70);

        this.entityManager = entityManager;




        //this.entityManager.AddEntity(new LightStatue (handler, 2080, 1770));

        pokemonLeagueAreaWalls = new ArrayList<>();
        AddWalls();

    }

    public void tick() {
        super.tick();

        for (Walls w : pokemonLeagueAreaWalls) {
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

        g.drawImage(Images.ScaledPokemonLeagueArea, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

        if (GameSetUp.DEBUGMODE) {
            for (Walls w : pokemonLeagueAreaWalls) {

                if (w.getType().equals("Wall"))
                    g2.setColor(Color.BLACK);
                else
                    g2.setColor(Color.PINK);

                w.render(g2);
            }
        }


        entityManager.render(g);


    }

    private void AddWalls() {

        /*
         * Top side
         */
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 1279,831, 490, 302, "Wall")); // Pokemon League front building left side wall
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 1916,837, 497, 295, "Wall")); // Pokemon League front building right side
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 944,957, 502, 331, "Wall")); // Pokemon League front left statue
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 2234,923, 531, 362, "Wall")); // Pokemon League front right statue

        /*
         * Pokemon League entrance
         */
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 1761,1024, 160, 69, "PL First Room Entrance")); // Pokemon League Entrance


        /*
         * Left side
         */
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 950,1276, 168, 644, "Wall")); // Left vertical 4 round objects
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 437,1876, 527, 516, "Wall")); // Left ^ shaped cave call
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 893,2238, 445, 167, "Wall")); // Left side horizontal bottom cave wall
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 1113,1925, 318, 165, "Wall")); // Left side round object and top half of statue
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 1280,2069, 163, 216, "Wall")); // Left side bottom half of statue
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 1421,2081, 175, 1109, "Wall")); // Left side stairs wall


        /*
         * Right side
         */
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 2098,2076, 145, 1114, "Wall")); // Right side stairs wall
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 2238,1923, 163, 316, "Wall")); // Right side statue
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 2283,1929, 167, 144, "Wall")); // Right side round object next to statue
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 2540,1918, 828, 416, "Wall")); // Right side cave wall pocket
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 2574,1283, 212, 636, "Wall")); // Right side 4 vertical round objects
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 2331,1931, 578, 150, "Wall")); // Right side 4 vertical round objects

        /*
         * Middle Statues
         */
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 1289,1443, 147, 314, "Wall")); // Left side statue
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 2240,1446, 160, 320, "Wall")); // Right side statue

        /*
         * Bottom Wall
         */
        pokemonLeagueAreaWalls.add(new InWorldWalls(handler, 1560,3120, 583, 70, "Wall")); // Bottom wall. Block exit back to the cave.


    }

    @Override
    public ArrayList<InWorldWalls> getWalls() {
        return pokemonLeagueAreaWalls;
    }
}
