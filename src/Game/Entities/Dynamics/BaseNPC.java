package Game.Entities.Dynamics;

import Game.GameStates.FightState;
import Game.World.InWorldAreas.InsideTower;
import Game.World.InWorldAreas.TownArea;
import Main.GameSetUp;
import Main.Handler;
import Resources.Images;

import java.awt.*;
import java.awt.event.KeyEvent;
import java.awt.image.BufferedImage;

public class BaseNPC extends BaseNeutralEntity{

    Rectangle mewtwoNPC;
    Rectangle arceusNPC;
    int width, height;
    public static boolean towerClue = false;
    public static boolean questStarted = false; // To know if quest is active or not.
    public static boolean questCompleted = false; // To know if quest has been completed or not.
    public static boolean arceusBlessing = false; // To know if Pikachu received the Arceus blessing.


    public BaseNPC(Handler handler, int xPosition, int yPosition,String area, String state, String name, BufferedImage[] animFrames) {
        super(handler, xPosition, yPosition,name, area, state,animFrames);
        width = 30;
        height = 30;
        speed = 1;
        type="BaseNPC";
        this.setXOffset(xPosition);
        this.setYOffset(yPosition);

        this.foundState = state;
        mewtwoNPC = new Rectangle();
        arceusNPC = new Rectangle();
    }

    @Override
    public void tick() {

        if(!Player.isinArea)super.tick();

    }

    @Override
    public void render(Graphics g) {
        super.render(g);

        Graphics2D g2 = (Graphics2D) g;


        // Execute this block if the player is in the town.
        if(handler.getArea().equals(this.Area)) {
            // Draw the NPC on the given coordinates depending on the area.
            if (TownArea.isInTown) {
                g.setColor(Color.BLACK);
                g.drawString("Press (E) to interact" ,handler.getXInWorldDisplacement() + 990, handler.getYInWorldDisplacement() + 930);
                // Rectangle that by width and radius detect if player is close to initialize quest dialogue.
                mewtwoNPC = new Rectangle(handler.getXInWorldDisplacement() + 980,
                        handler.getYInWorldDisplacement() + 950, 200, 200);
                // Draw mewtwo.
                g.drawImage(Images.mewtwo_front,handler.getXInWorldDisplacement() + 990,handler.getYInWorldDisplacement() + 950
                        ,115,115 ,null);
            }

            g2.setColor(Color.black);

            // Check if player is inside the tower and draw tower NPC.
            if (InsideTower.isInTower) {
                arceusNPC = new Rectangle(handler.getXInWorldDisplacement() + 1700,
                        handler.getYInWorldDisplacement() + 1800, 200, 200);
                // Draw Arceus.
                g.drawImage(Images.arceus,handler.getXInWorldDisplacement() + 1740,handler.getYInWorldDisplacement() + 1800
                        ,115,115 ,null);
            }

            // Execute the following ONLY if the quest hasn't been accepted yet.
            if (!questStarted) {
                if (handler.getEntityManager().getPlayer().getCollision().intersects(mewtwoNPC)) {

                    if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
                        handler.getGame().getMusicHandler().playEffect("res/music/selectBeep.wav",0);
                        GameSetUp.DIALOGUEMODE = true; // Show the dialog box.
                    }
                }
                if (GameSetUp.DIALOGUEMODE) {
                    // Draw quest dialogue that shows the quest instructions to the player and ask if he wants to accept or decline.
                    g.drawImage(Images.questStartedDialogBox,670,700,handler.getWidth() / 2, handler.getHeight()/3 , null);
                    if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_R)) {
                        handler.getGame().getMusicHandler().playEffect("res/music/enterSelect.wav",0);
                        GameSetUp.DIALOGUEMODE = false; // Cancel the dialogue.
                    }
                    if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)) {
                        handler.getGame().getMusicHandler().playEffect("res/music/enterSelect.wav",0);
                        GameSetUp.DIALOGUEMODE = false;
                        questStarted = true; // This means we accepted the quest and the quest is active.
                        handler.getWorldManager().spawnQuestTarget(); // Spawn in the MapState; the target for the quest.
                    }
                }

            }
            // ONLY execute this block when the player accepts the quest and kills its target enemy.
            if (FightState.questTargetIsDead) {
                if (handler.getEntityManager().getPlayer().getCollision().intersects(mewtwoNPC)) {

                    if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
                        handler.getGame().getMusicHandler().playEffect("res/music/selectBeep.wav",0);
                        GameSetUp.DIALOGUEMODE = true; // Shows the dialog box.
                    }
                }
                if (GameSetUp.DIALOGUEMODE) {
                    // Draw the Congratulatory quest dialogue.
                    g.drawImage(Images.questCompletedDialogBox,670,700,handler.getWidth() / 2, handler.getHeight()/3 , null);
                    if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_R)) {
                        handler.getGame().getMusicHandler().playEffect("res/music/enterSelect.wav",0);
                        GameSetUp.DIALOGUEMODE = false; // Cancel the dialogue.
                    }
                    if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)) {
                        handler.getGame().getMusicHandler().playEffect("res/music/enterSelect.wav",0);
                        //handler.getGame().getMusicHandler().playEffect("res/icons/clips/FCTM.wav",1);
                        GameSetUp.DIALOGUEMODE = false;
                        questCompleted = true; // The player accepted the quest rewards.
                        towerClue = true;
                        FightState.questTargetIsDead = false; // Let everyone else know that the quest target is dead.
                    }
                }
            }

            if (questCompleted && !arceusBlessing) {
                if (handler.getEntityManager().getPlayer().getCollision().intersects(arceusNPC)) {

                    if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_E)) {
                        handler.getGame().getMusicHandler().playEffect("res/music/ArceusSFX.wav",0);
                        GameSetUp.DIALOGUEMODE = true; // Show the dialog box.
                    }
                }
                if (GameSetUp.DIALOGUEMODE) {
                    // Draw quest dialogue that shows the quest instructions to the player and ask if he wants to accept or decline.
                    g.drawImage(Images.arceuDialogBox,670,700,handler.getWidth() / 2, handler.getHeight()/3 , null);
                    if(handler.getKeyManager().keyJustPressed(KeyEvent.VK_F)) {
                        handler.getGame().getMusicHandler().playEffect("res/music/enterSelect.wav",0);
                        GameSetUp.DIALOGUEMODE = false; // Cancel the dialogue.
                        arceusBlessing = true;

                    }
                }

            }
        }
    }

}
