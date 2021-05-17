package model.cards.spells;

import exceptions.InvalidTargetException;
import model.cards.Rarity;
import model.cards.minions.Minion;

public class DivineSpirit extends Spell implements MinionTargetSpell {
    public DivineSpirit(){
        super("Divine Spirit",3, Rarity.BASIC);
    }

   
	public void performAction(Minion m) throws InvalidTargetException {
		int cp=m.getCurrentHP()*2;
		int mp=m.getMaxHP()*2;
		m.setMaxHP(mp);
		m.setCurrentHP(cp);
	}
}
