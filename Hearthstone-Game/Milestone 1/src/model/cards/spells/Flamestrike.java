package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class Flamestrike extends Spell implements AOESpell {
	public Flamestrike() {
		super("Flamestrike",7,Rarity.BASIC);
	}
	
	
	public void performAction(ArrayList<Minion> oppField,ArrayList<Minion> curField) {
		int j=0;
		while(j<oppField.size()) {
			Minion m = oppField.get(j);
			if(m.isDivine()) 
				m.setDivine(false);
			
			else
				m.setCurrentHP((m.getCurrentHP()-4));
			
			if(m.getCurrentHP()!=0)
				j++;
		}
	}
	
}
