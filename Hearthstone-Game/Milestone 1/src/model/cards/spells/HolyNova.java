package model.cards.spells;

import java.util.ArrayList;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class HolyNova extends Spell implements AOESpell{
	public HolyNova() {
		super("Holy Nova",5,Rarity.BASIC);
	}
	

    public void performAction(ArrayList<Minion> oppField, ArrayList<Minion> curField) {
        int i;
        Minion m;
        for(i = 0; i < oppField.size(); ++i) {
            m = (Minion)oppField.get(i);
            if (m.isDivine()) {
                m.setDivine(false);
            } else {
                m.setCurrentHP(m.getCurrentHP() - 2);
                if (m.getCurrentHP() == 0) {
                    --i;
                }
            }
        }

        for(i = 0; i < curField.size(); ++i) {
            m = (Minion)curField.get(i);
            m.setCurrentHP(m.getCurrentHP() + 2);
        }
}
}