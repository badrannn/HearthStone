package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class LevelUp extends Spell implements FieldSpell {
    public LevelUp(){
        super("Level Up!",6, Rarity.EPIC);
    }
   
	
	public void performAction(ArrayList<Minion> field) {
		for(int x =0;x<field.size();x++) {
			if(field.get(x).getName().equalsIgnoreCase("Silver Hand Recruit")) {
				Minion m =field.get(x);
				m.setAttack(m.getAttack()+1);
				m.setMaxHP(m.getMaxHP()+1);
				m.setCurrentHP(m.getCurrentHP()+1);
				
			}
		}
		
	}
}
