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
public class PokemonLeagueSecondRoom extends BaseArea {

    Rectangle exit;
    Rectangle playerRect;
    public static boolean isInPokemonLeagueSecondRoom = false;

    // Need to change this
    public final static int playerPLSecondRoomXSpawn = 10, playerPLSecondRoomYSpawn = -3000;
    //public final static int playerXSpawn = -565, playerYSpawn = -2200;

    private Rectangle background = new Rectangle(3000, 3000);

    public static ArrayList<InWorldWalls> pokemonLeagueSecondRoomWalls;

    public PokemonLeagueSecondRoom(Handler handler, EntityManager entityManager) {
        super(handler, entityManager);
        name="PokemonLeagueSecondRoom";
        handler.setXInWorldDisplacement(playerPLSecondRoomXSpawn);
        handler.setYInWorldDisplacement(playerPLSecondRoomYSpawn);

        playerRect = new Rectangle((int) handler.getWidth() / 2 - 5, (int) (handler.getHeight() / 2) + 300, 70, 70);

        this.entityManager = entityManager;




        this.entityManager.AddEntity(handler.newEnemy(Images.PEnemyIdleRed,handler,700, 2000,"InWorldState","Red","PokemonLeagueSecondRoom","EnemyOne",100,100,100,100,100,25,50,25,25,80,3,10,"Trainer","Poke Ball",null,null)); //lvl 100 difficulty
        //this.entityManager.AddEntity(new LightStatue (handler, 2080, 1770));

        pokemonLeagueSecondRoomWalls = new ArrayList<>();
        AddWalls();

    }

    public void tick() {
        super.tick();

        for (Walls w : pokemonLeagueSecondRoomWalls) {
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

        g.drawImage(Images.ScaledPokemonLeagueSecondRoom, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

        if (GameSetUp.DEBUGMODE) {
            for (Walls w : pokemonLeagueSecondRoomWalls) {

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
         * Bottom Walls:
         */
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 390,3947, 1726, 14, "Wall")); // Bottom exit Blocking wall
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 1028,2722, 52, 1274, "Wall")); // Left yellow hallway wall
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 1539,2742, 70, 1247, "Wall")); // Right yellow hallway wall

        /*
         * Stage Walls:
         */
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler,442,2247, 188, 194, "Wall")); // Bottom Left; Bottom stage wall
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler,631,2353, 90, 190, "Wall")); // Bottom Left; Bottom stage wall
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 245,2766, 804, 14, "Wall")); // Left bottom stage wall
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 1590,2765, 686, 39, "Wall")); // Right bottom stage wall
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 566,2287, 71, 565, "Wall")); // Left the bottom left pillar
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 447,2171, 96, 631, "Wall")); // Left the bottom left pillar
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 444,1337, 95, 939, "Wall")); // Left side of stage
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 170,1087, 406, 258, "Wall")); // Left Top; bottom of top left pillar
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 564,841, 79, 444, "Wall")); // Left Top; TOP of top left pillar
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 648,774, 57, 460, "Wall")); // Left Top; TOP of top left pillar
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 666,1086, 454, 117, "Wall")); // Top of stage: Left side
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 1522,1097, 590, 106, "Wall")); // Top of stage: Right side
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 1947,1055, 206, 185, "Wall")); // Top of stage: Right side; Diagonal
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 1988,1188, 427, 94, "Wall")); // Top of stage: Right side; Diagonal
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 2021,1275, 566, 63, "Wall")); // Top of stage: Right side; Diagonal
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 2094,1329, 99, 899, "Wall")); // Right wall of stage: vertical wall
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 2006,2283, 197, 315, "Wall")); // Bottom Right; Diagonal stage
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 1931,2336, 99, 246, "Wall")); // Bottom Right; Diagonal stage
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 2051,2210, 192, 178, "Wall")); // Bottom Right; Diagonal stage
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler,1520,2373, 702, 36, "Wall")); // Bottom Right; Bottom stage wall
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler,442,2247, 188, 194, "Wall")); // Bottom Left; Bottom stage wall


        /*
         * Stage Pillar Walls:
         */
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 360,2286, 143, 291, "Wall")); // Left Bottom pillar
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 367,909, 120, 286, "Wall")); // Left Top pillar
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 2132,2290, 147, 281, "Wall")); // Right Bottom pillar
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 2132,907, 136, 281, "Wall")); // Right Bottom pillar

        /*
         * Stage Bench Walls:
         */
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 738,2402, 382, 283, "Wall")); // Left Bottom Benches
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 665,2516, 147, 153, "Wall")); // Left Bottom Benches
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 1520,2412, 400, 265, "Wall")); // Right Bottom Benches
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 1910,2505, 71, 165, "Wall")); // Right Bottom Benches

        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 2146,1336, 250, 900, "Wall")); // Right Middle Benches
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 253,1354, 235, 879, "Wall")); // Left Middle Benches
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 1024,898, 96, 274, "Wall")); // Left Middle Benches

        /*
         * Top stairs walls:
         */
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 1051,612, 123, 390, "Wall")); // Left stairs wall
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 1447,628, 74, 380, "Wall")); // Right stairs wall
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 1136,308, 77, 334, "Wall")); // Left Door Frame
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 1432,281, 69, 352, "Wall")); // Left side benches
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 1520,881, 78, 313, "Wall")); // Right side benches
        pokemonLeagueSecondRoomWalls.add(new InWorldWalls(handler, 820,436, 1087, 189, "Wall")); // Right side benches


    }

    @Override
    public ArrayList<InWorldWalls> getWalls() {
        return pokemonLeagueSecondRoomWalls;
    }
}
