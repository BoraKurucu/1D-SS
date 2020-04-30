package Models.Dungeon;

import Models.Dungeon.Room.Fight;
import Models.Dungeon.Room.Merchant;
import Models.Dungeon.Room.Treasure;
import sts.FightScene;
import sts.RoomScene;
import  sts.MerchantScene;

import static sts.Main.game;

public class Dungeon {
    private int act;
    private String name;

    private AbstractRoom root;
    private AbstractRoom currentRoom;


    public Dungeon() {
        this.act = 1;
        this.name = "The Exordium";
    }

    public void generate() {
        Merchant m = new Merchant(null);
        Treasure t = new Treasure(m);
        currentRoom = new Fight(t);
        game.currentScene = new FightScene();



    }

    public int getAct() {
        return act;
    }

    public String getName() {
        return name;
    }

    public AbstractRoom getCurrentRoom() {
        return currentRoom;
    }

    public boolean ascend() {

        if (this.currentRoom.getChildren() == null) return false;
        System.out.println("ASCEND IN");
        this.currentRoom = this.currentRoom.getChildren();
        this.currentRoom.start();
        return true;
    }
}
