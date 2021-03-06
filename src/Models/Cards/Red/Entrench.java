package Models.Cards.Red;

import Models.Cards.*;
import Models.Creatures.AbstractCharacter;
import Controller.Dungeon.Room.Fight;

public class Entrench extends AbstractCard {

    public Entrench(){
        name = "Entrench";
        description = "Double your current block.";
        cost = 2;
        type = CardType.SKILL;
        color = CardColor.RED;
        rarity = CardRarity.UNCOMMON;
        target = CardTarget.SELF;
        baseAttr = new BaseCardAttributes();
        usable = true;
        upgradable = false;
    }

    @Override
    public boolean use(Fight f, AbstractCharacter player) {
        selected = false;
        if (!player.changeEnergy(-cost)) return false;
        player.changeBlock(player.getBlock());
        return true;
    }

    @Override
    public void upgrade() {
        if (upgradable) {
            cost = 1;
            upgradable = false;
            this.name = this.name + "+";

        }
    }

    @Override
    public AbstractCard makeCopy() {
        Entrench copy = new Entrench();
        copy.upgradable = this.upgradable;
        copy.cost = this.cost;
        return copy;
    }
}
