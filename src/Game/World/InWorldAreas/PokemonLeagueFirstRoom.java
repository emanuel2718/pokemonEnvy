package Game.World.InWorldAreas;

import Game.Entities.EntityManager;
import Game.GameStates.FightState;
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
public class PokemonLeagueFirstRoom extends BaseArea {

    Rectangle exit;
    Rectangle playerRect;
    public static boolean isInPokemonLeagueFirstRoom = false;

    // Need to change this
    public final static int playerPLFirstRoomXSpawn = 270, playerPLFirstRoomYSpawn = -1250;
    //public final static int playerXSpawn = -565, playerYSpawn = -2200;

    private Rectangle background = new Rectangle(3000, 3000);

    public static ArrayList<InWorldWalls> pokemonLeagueFirstRoomWalls;

    public PokemonLeagueFirstRoom(Handler handler, EntityManager entityManager) {
        super(handler, entityManager);
        name="PokemonLeagueFirstRoom";
        handler.setXInWorldDisplacement(playerPLFirstRoomXSpawn);
        handler.setYInWorldDisplacement(playerPLFirstRoomYSpawn);

        playerRect = new Rectangle((int) handler.getWidth() / 2 - 5, (int) (handler.getHeight() / 2) + 300, 70, 70);

        this.entityManager = entityManager;




        if (!FightState.ethanIsDead) {
            this.entityManager.AddEntity(handler.newEnemy(Images.PEnemyIdleEthan,handler,700, 2000,"InWorldState","Ethan","PokemonLeagueFirstRoom","EnemyOne",100,25,40,88,8,12,20,10,20,10,1,5,"Trainer","Poke Ball",null,null)); //lvl 80 difficulty

        }

        pokemonLeagueFirstRoomWalls = new ArrayList<>();
        AddWalls();

    }

    public void tick() {
        super.tick();

        for (Walls w : pokemonLeagueFirstRoomWalls) {
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

        g.drawImage(Images.ScaledPokemonLeagueFirstRoom, handler.getXInWorldDisplacement(), handler.getYInWorldDisplacement(), null);

        if (GameSetUp.DEBUGMODE) {
            for (Walls w : pokemonLeagueFirstRoomWalls) {

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
         * Top Walls:
         */
        pokemonLeagueFirstRoomWalls.add(new InWorldWalls(handler, 0,320, 891, 100, "Wall")); // Top wall left side
        pokemonLeagueFirstRoomWalls.add(new InWorldWalls(handler, 887,195, 73, 262, "Wall")); // Top wall left side column
        pokemonLeagueFirstRoomWalls.add(new InWorldWalls(handler, 1126,206, 59, 246, "Wall")); // Top wall right side column
        pokemonLeagueFirstRoomWalls.add(new InWorldWalls(handler, 1166,293, 914, 127, "Wall")); // Top wall right side

        /*
         * Second Room Entrance:
         */
        pokemonLeagueFirstRoomWalls.add(new InWorldWalls(handler, 946,310, 189, 93, "PL Second Room Entrance")); // Second Room Entrance.


        /*
         * Bottom Walls:
         */
        pokemonLeagueFirstRoomWalls.add(new InWorldWalls(handler, 6,1685, 918, 345, "Wall")); // Bottom left wall
        pokemonLeagueFirstRoomWalls.add(new InWorldWalls(handler, 1158,1689, 922, 341, "Wall")); // Bottom right wall

        pokemonLeagueFirstRoomWalls.add(new InWorldWalls(handler, 910,1998, 265, 32, "Wall")); // Bottom entrance blocking wall


        /*
         * Left Walls:
         */
        //pokemonLeagueFirstRoomWalls.add(new InWorldWalls(handler, 0,0, 6, 2028, "Wall")); // Left vertical wall

        /*
         * Right Walls:
         */
        pokemonLeagueFirstRoomWalls.add(new InWorldWalls(handler, 2075,0, 6, 2028, "Wall")); // Right vertical wall

        /*
         * Middle Columns:
         */
        pokemonLeagueFirstRoomWalls.add(new InWorldWalls(handler, 318,292, 161, 774, "Wall")); // Top Left columns
        pokemonLeagueFirstRoomWalls.add(new InWorldWalls(handler, 1606,302, 155, 767, "Wall")); // Top Right columns
        pokemonLeagueFirstRoomWalls.add(new InWorldWalls(handler, 322,1158, 154, 397, "Wall")); // Bottom Left columns
        pokemonLeagueFirstRoomWalls.add(new InWorldWalls(handler, 1608,1163, 144, 391, "Wall")); // Bottom Right columns


    }

    @Override
    public ArrayList<InWorldWalls> getWalls() {
        return pokemonLeagueFirstRoomWalls;
    }
}
