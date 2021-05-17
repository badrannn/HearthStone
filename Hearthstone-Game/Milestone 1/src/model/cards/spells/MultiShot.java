package model.cards.spells;

import java.util.ArrayList;
import java.util.Random;

import model.cards.Rarity;
import model.cards.minions.Minion;

public class MultiShot extends Spell implements AOESpell{
	public MultiShot() {
		super("Multi-Shot",4,Rarity.BASIC);
	}
	
	
	public void performAction(ArrayList<Minion> oppField,ArrayList<Minion> curField) {
		int k=oppField.size();
		if(k==1){
			if(oppField.get(0).isDivine()) {
				oppField.get(0).setDivine(false);
			}
			else {
				oppField.get(0).setCurrentHP(oppField.get(0).getCurrentHP()-3);
			}
			
		}
		else if(k==2){
			Minion m=oppField.get(0);
			Minion n=oppField.get(1);
			if(m.isDivine()) {
				m.setDivine(false);
			}
			else {
				m.setCurrentHP(m.getCurrentHP()-3);
			}
			if(n.isDivine()) {
				n.setDivine(false);
			}
			else {
				n.setCurrentHP(n.getCurrentHP()-3);
			}
		
		
		}
		else if(k>2){
			Random ra0=new Random();
    		int ra= ra0.nextInt(k);
    		int ra1= ra0.nextInt(k);
			while(ra==ra1){
				ra= ra0.nextInt(k);
	    		ra1= ra0.nextInt(k);
			}
			Minion u=oppField.get(ra);
			Minion t=oppField.get(ra1);
			if(u.isDivine()) {
				u.setDivine(false);
			}
			else {
				u.setCurrentHP(u.getCurrentHP()-3);
			}
			if(t.isDivine()) {
				t.setDivine(false);
			}
			else {
				t.setCurrentHP(t.getCurrentHP()-3);
			}
			
			
		}
	}



public static void main(String[]args){

	int r=(int) Math.random()*100;
	System.out.print(r);
	
	
}


}