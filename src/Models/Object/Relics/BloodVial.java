package Models.Object.Relics;

import Models.Creatures.AbstractCharacter;
import Controller.Dungeon.Room.Fight;
import Models.Object.AbstractRelic;
import Models.Object.RelicClass;
import Models.Object.RelicRarity;

public class BloodVial extends AbstractRelic {

    public BloodVial(){
        name = "BloodVial";
        description = "At the start of each combat, heal 2 HP.";
        rarity = RelicRarity.COMMON;
        rClass = RelicClass.ANY;
        amount = 2;
    }

    @Override
    public AbstractRelic makeCopy(){
        return new BloodVial();
    }

    @Override
    public void onFightStart(Fight f, AbstractCharacter c) {
        c.changeHealth(amount);
    }

}
