package model.cards.spells;
import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class TwistingNether extends Spell implements AOESpell{
	public TwistingNether() {
	
		super("Twisting Nether",8,Rarity.EPIC);
	}
	
	
	public void performAction(ArrayList<Minion> oppField,ArrayList<Minion> curField) {
		while(!oppField.isEmpty()) {
		Minion m=oppField.get(0);
		m.setCurrentHP(0);
		}
		while(!curField.isEmpty()){
			Minion m=curField.get(0);
			m.setCurrentHP(0);
		}
		
	}

}
