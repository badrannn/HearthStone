package engine;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;


import model.cards.minions.*;
import model.cards.spells.*;
import model.cards.*;
import model.heroes.*;

public class GameController implements ActionListener,GameListener {
	private static Game model;
	private preGameView view;
	private static GameView gameview;
	private static Hero firstplayer;
	private static Hero secplayer;
	private static Minion minionInAttack;
	private static Hero herotobeattacked;
	private static Spell spelltobeused;
	private static Hero herotousepower;
	
	
	public GameController(){
		firstplayer=null;
		secplayer=null;
		minionInAttack=null;
		herotobeattacked=null;
		spelltobeused=null;
		herotousepower=null;
		view=new preGameView(this);
	}


	@Override
	public void onGameOver() {
		if(firstplayer.getCurrentHP()==0){
			gameview.setVisible(false);
			gameview.removeAll();
			gameview.dispose();
			gameview.revalidate();
			gameview.repaint();
			new PopUpp("2nd PLAYER WINS!!",1,this);
		}
		else if(secplayer.getCurrentHP()==0){
			gameview.setVisible(false);
			gameview.removeAll();
			gameview.dispose();
			gameview.revalidate();
			gameview.repaint();
			new PopUpp("1st PLAYER WINS!!",1,this);
		}
	}


	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getActionCommand().equals("chosen the heros lets play")){
			if(secplayer==null || firstplayer==null){
				new PopUpp("each of the 2 players must choose their heros");
			}
			else{
			Game g;
			try {
				g = new Game(firstplayer, secplayer);
				model=g;
				gameview =new GameView(this,model);
				model.setListener(this);
				view.removeAll();
				view.dispose();
			} catch (FullHandException e1) {
				new PopUpp("Sorry, Your Hand is Full");
				e1.printStackTrace();
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			}
		}
		if(e.getActionCommand().equals("chose mage 1")){
			
			try {
				Mage m=new Mage();
				firstplayer=m;
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		
		if(e.getActionCommand().equals("chose priest 1")){
			
			try {
				Priest m=new Priest();
				firstplayer=m;
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}

		if(e.getActionCommand().equals("chose hunter 1")){
			
			try {
				Hunter m=new Hunter();
				firstplayer=m;
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		

		if(e.getActionCommand().equals("chose paladin 1")){
			
			try {
				Paladin m=new Paladin();
				firstplayer=m;
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		

		if(e.getActionCommand().equals("chose warlock 1")){
			
			try {
				Warlock m=new Warlock();
				firstplayer=m;
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		

		if(e.getActionCommand().equals("chose mage 2")){
			
			try {
				Mage m=new Mage();
				secplayer=m;
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		
		if(e.getActionCommand().equals("chose priest 2")){
			
			try {
				Priest m=new Priest();
				secplayer=m;
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		
		if(e.getActionCommand().equals("chose hunter 2")){
			
			try {
				Hunter m=new Hunter();
				secplayer=m;
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		
		if(e.getActionCommand().equals("chose paladin 2")){
			
			try {
				Paladin m=new Paladin();
				secplayer=m;
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		
		if(e.getActionCommand().equals("chose warlock 2")){
			
			try {
				Warlock m=new Warlock();
				secplayer=m;
			} catch (IOException | CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		
		}
		
		
		if(e.getActionCommand().equals("current hero used herpower")){
			if(model.getCurrentHero() instanceof Mage){
				herotousepower=model.getCurrentHero();
				spelltobeused=null;
				minionInAttack=null;
			}
			if(model.getCurrentHero() instanceof Hunter){
				try {
					model.getCurrentHero().useHeroPower();
					gameview.refresh(this, model);
				} catch (NotEnoughManaException e1) {
					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					new PopUpp("Sorry, Your Hero Power is Already Used");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (FullHandException e1) {
					new PopUpp("Sorry, Your Hand is Full");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(model.getCurrentHero() instanceof Paladin){
				try {
					model.getCurrentHero().useHeroPower();
					gameview.refresh(this, model);
				} catch (NotEnoughManaException e1) {
					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					new PopUpp("Sorry, Your Hero Power is Already Used");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (FullHandException e1) {
					new PopUpp("Sorry, Your Hand is Full");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			if(model.getCurrentHero() instanceof Priest){
				herotousepower=model.getCurrentHero();
				spelltobeused=null;
				minionInAttack=null;
			}
			if(model.getCurrentHero() instanceof Warlock){
				try {
					model.getCurrentHero().useHeroPower();
					gameview.refresh(this, model);
				} catch (NotEnoughManaException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					new PopUpp("Sorry, Your Hero Power is Already Used");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (FullHandException e1) {
					new PopUpp("Sorry, Your Hand is Full");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		
		
		
		
		if(e.getActionCommand().equals("0card in hand")){
			Card card1=model.getCurrentHero().getHand().get(0);
			if(card1 instanceof Minion){
				try {
					model.getCurrentHero().playMinion((Minion) card1);
					gameview.refresh(this,model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				}
				gameview.refresh(this,model);
				
			}
			else {
				if(card1 instanceof AOESpell){
					try {
						model.getCurrentHero().castSpell((AOESpell)card1, model.getOpponent().getField());
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof FieldSpell){
					try {
						model.getCurrentHero().castSpell((FieldSpell) card1);
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(card1 instanceof HeroTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(0);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof LeechingSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(0);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof MinionTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(0);
					herotousepower=null;
					minionInAttack=null;
				}
			
		}
			
			
			}
		
		
		if(e.getActionCommand().equals("1card in hand")){
			Card card1=model.getCurrentHero().getHand().get(1);
			if(card1 instanceof Minion){
				try {
					model.getCurrentHero().playMinion((Minion) card1);
					gameview.refresh(this,model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				}
				gameview.refresh(this,model);
				
			}
			else {
				if(card1 instanceof AOESpell){
					try {
						model.getCurrentHero().castSpell((AOESpell)card1, model.getOpponent().getField());
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				}
				if(card1 instanceof FieldSpell){
					try {
						model.getCurrentHero().castSpell((FieldSpell) card1);
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof HeroTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(1);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof LeechingSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(1);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof MinionTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(1);
					herotousepower=null;
					minionInAttack=null;
				}
			
		}
			
			
			}
		
		if(e.getActionCommand().equals("2card in hand")){
			Card card1=model.getCurrentHero().getHand().get(2);
			if(card1 instanceof Minion){
				try {
					model.getCurrentHero().playMinion((Minion) card1);
					gameview.refresh(this,model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field Is Full");
					e1.printStackTrace();
				}
				gameview.refresh(this,model);
				
			}
			else {
				if(card1 instanceof AOESpell){
					try {
						model.getCurrentHero().castSpell((AOESpell)card1, model.getOpponent().getField());
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						e1.printStackTrace();
						new PopUpp("Sorry, No Enough Mana");
					}
				}
				if(card1 instanceof FieldSpell){
					try {
						model.getCurrentHero().castSpell((FieldSpell) card1);
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof HeroTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(2);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof LeechingSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(2);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof MinionTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(2);
					herotousepower=null;
					minionInAttack=null;
				}
			
		}
			
			
			}
		
		if(e.getActionCommand().equals("3card in hand")){
			Card card1=model.getCurrentHero().getHand().get(3);
			if(card1 instanceof Minion){
				try {
					model.getCurrentHero().playMinion((Minion) card1);
					gameview.refresh(this,model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				}
				gameview.refresh(this,model);
				
			}
			else {
				if(card1 instanceof AOESpell){
					try {
						model.getCurrentHero().castSpell((AOESpell)card1, model.getOpponent().getField());
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof FieldSpell){
					try {
						model.getCurrentHero().castSpell((FieldSpell) card1);
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof HeroTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(3);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof LeechingSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(3);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof MinionTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(3);
					herotousepower=null;
					minionInAttack=null;
				}
			
		}
			
			
			}
		
		if(e.getActionCommand().equals("4card in hand")){
			Card card1=model.getCurrentHero().getHand().get(4);
			if(card1 instanceof Minion){
				try {
					model.getCurrentHero().playMinion((Minion) card1);
					gameview.refresh(this,model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				}
				gameview.refresh(this,model);
				
			}
			else {
				if(card1 instanceof AOESpell){
					try {
						model.getCurrentHero().castSpell((AOESpell)card1, model.getOpponent().getField());
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof FieldSpell){
					try {
						model.getCurrentHero().castSpell((FieldSpell) card1);
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof HeroTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(4);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof LeechingSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(4);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof MinionTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(4);
					herotousepower=null;
					minionInAttack=null;
				}
			
		}
			
			
			}
		
		if(e.getActionCommand().equals("5card in hand")){
			Card card1=model.getCurrentHero().getHand().get(5);
			if(card1 instanceof Minion){
				try {
					model.getCurrentHero().playMinion((Minion) card1);
					gameview.refresh(this,model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				}
				gameview.refresh(this,model);
				
			}
			else {
				if(card1 instanceof AOESpell){
					try {
						model.getCurrentHero().castSpell((AOESpell)card1, model.getOpponent().getField());
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof FieldSpell){
					try {
						model.getCurrentHero().castSpell((FieldSpell) card1);
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof HeroTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(5);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof LeechingSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(5);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof MinionTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(5);
					herotousepower=null;
					minionInAttack=null;
				}
			
		}
			
			
			}
		
		
		if(e.getActionCommand().equals("6card in hand")){
			Card card1=model.getCurrentHero().getHand().get(6);
			if(card1 instanceof Minion){
				try {
					model.getCurrentHero().playMinion((Minion) card1);
					gameview.refresh(this,model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				}
				gameview.refresh(this,model);
				
			}
			else {
				if(card1 instanceof AOESpell){
					try {
						model.getCurrentHero().castSpell((AOESpell)card1, model.getOpponent().getField());
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof FieldSpell){
					try {
						model.getCurrentHero().castSpell((FieldSpell) card1);
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof HeroTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(6);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof LeechingSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(6);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof MinionTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(6);
					herotousepower=null;
					minionInAttack=null;
				}
			
		}
			
			
			}
		
		if(e.getActionCommand().equals("7card in hand")){
			Card card1=model.getCurrentHero().getHand().get(7);
			if(card1 instanceof Minion){
				try {
					model.getCurrentHero().playMinion((Minion) card1);
					gameview.refresh(this,model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				}
				gameview.refresh(this,model);
				
			}
			else {
				if(card1 instanceof AOESpell){
					try {
						model.getCurrentHero().castSpell((AOESpell)card1, model.getOpponent().getField());
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof FieldSpell){
					try {
						model.getCurrentHero().castSpell((FieldSpell) card1);
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof HeroTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(7);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof LeechingSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(7);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof MinionTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(7);
					herotousepower=null;
					minionInAttack=null;
				}
			
		}
			
			
			}
		
		if(e.getActionCommand().equals("8card in hand")){
			Card card1=model.getCurrentHero().getHand().get(8);
			if(card1 instanceof Minion){
				try {
					model.getCurrentHero().playMinion((Minion) card1);
					gameview.refresh(this,model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				}
				gameview.refresh(this,model);
				
			}
			else {
				if(card1 instanceof AOESpell){
					try {
						model.getCurrentHero().castSpell((AOESpell)card1, model.getOpponent().getField());
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof FieldSpell){
					try {
						model.getCurrentHero().castSpell((FieldSpell) card1);
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof HeroTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(8);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof LeechingSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(8);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof MinionTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(8);
					herotousepower=null;
					minionInAttack=null;
				}
			
		}
			
			
			}
		
		if(e.getActionCommand().equals("9card in hand")){
			Card card1=model.getCurrentHero().getHand().get(9);
			if(card1 instanceof Minion){
				try {
					model.getCurrentHero().playMinion((Minion) card1);
					gameview.refresh(this,model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				}
				gameview.refresh(this,model);
				
			}
			else {
				if(card1 instanceof AOESpell){
					try {
						model.getCurrentHero().castSpell((AOESpell)card1, model.getOpponent().getField());
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof FieldSpell){
					try {
						model.getCurrentHero().castSpell((FieldSpell) card1);
						gameview.refresh(this, model);
					} catch (NotYourTurnException e1) {
						new PopUpp("Sorry, Not Your Turn");
						e1.printStackTrace();
					} catch (NotEnoughManaException e1) {
						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					}
				}
				if(card1 instanceof HeroTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(9);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof LeechingSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(9);
					herotousepower=null;
					minionInAttack=null;
				}
				if(card1 instanceof MinionTargetSpell){
					spelltobeused=(Spell) model.getCurrentHero().getHand().get(9);
					herotousepower=null;
					minionInAttack=null;
				}
			
		}
			
			
			}
		
		if(e.getActionCommand().equals("hero0")){
			if(herotousepower instanceof Priest && spelltobeused==null && minionInAttack==null){
				try {
					((Priest)herotousepower).useHeroPower(model.getCurrentHero());
					gameview.refresh(this, model);
					herotousepower=null;
					herotobeattacked=null;
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullHandException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		if(e.getActionCommand().equals("hero to be attacked")){
			if(spelltobeused==null && herotousepower==null && minionInAttack!=null){
				try {
					model.getCurrentHero().attackWithMinion(minionInAttack, model.getOpponent());
					minionInAttack=null;
					gameview.refresh(this, model);
				} catch (CannotAttackException e1) {
					new PopUpp("Sorry,This Minion Cannot Attack");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (TauntBypassException e1) {
					new PopUpp("Sorry, You Must Attack the Taunt Minion First");
					e1.printStackTrace();
				} catch (NotSummonedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					new PopUpp("Sorry, You Cannot Attack This Target");
					e1.printStackTrace();
				}
			}
			else if(spelltobeused!=null && herotousepower==null && minionInAttack!=null && spelltobeused instanceof HeroTargetSpell){
		try {
			model.getCurrentHero().castSpell((HeroTargetSpell)spelltobeused, model.getOpponent());
			gameview.refresh(this,model);
		} catch (NotYourTurnException e1) {
			new PopUpp("Sorry, Not Your Turn");
			e1.printStackTrace();
		} catch (NotEnoughManaException e1) {
			new PopUpp("Sorry, No Enough Mana");
			e1.printStackTrace();
		}
		
			}
			else if(spelltobeused==null && minionInAttack==null && herotousepower instanceof Mage){
				try {
					((Mage)herotousepower).useHeroPower(model.getOpponent());
					herotousepower=null;
					herotobeattacked=null;
					gameview.refresh(this, model);
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullHandException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(spelltobeused==null && minionInAttack==null && herotousepower instanceof Priest){
				try {
					((Priest)herotousepower).useHeroPower(model.getOpponent());
					herotousepower=null;
					herotobeattacked=null;
					gameview.refresh(this, model);
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullHandException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
		}
		
		if(e.getActionCommand().equals("Minion on field to attack number0")){
			if(herotousepower!=null && herotousepower instanceof Priest){
			try {
				((Priest)herotousepower).useHeroPower(model.getCurrentHero().getField().get(0));
				gameview.refresh(this,model);
				spelltobeused=null;
				herotousepower=null;
				minionInAttack=null;
			} catch (NotEnoughManaException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (HeroPowerAlreadyUsedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (NotYourTurnException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FullHandException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (FullFieldException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (CloneNotSupportedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}	
			gameview.refresh(this, model);
			spelltobeused=null;
			herotousepower=null;
			minionInAttack=null;
			}
			else if(spelltobeused!=null && spelltobeused instanceof MinionTargetSpell){
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell) spelltobeused, model.getCurrentHero().getField().get(0));
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gameview.refresh(this, model);
				spelltobeused=null;
				herotousepower=null;
				minionInAttack=null;
			}
			else{
			minionInAttack=model.getCurrentHero().getField().get(0);
			spelltobeused=null;
			herotousepower=null;
			}
		}
		if(e.getActionCommand().equals("Minion on field to attack number1")){
			if(herotousepower!=null && herotousepower instanceof Priest){
				try {
					((Priest)herotousepower).useHeroPower(model.getCurrentHero().getField().get(1));
					gameview.refresh(this,model);
					spelltobeused=null;
					herotousepower=null;
					minionInAttack=null;
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullHandException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				spelltobeused=null;
				herotousepower=null;
				minionInAttack=null;
				gameview.refresh(this, model);
				}
			else if(spelltobeused!=null && spelltobeused instanceof MinionTargetSpell){
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell) spelltobeused, model.getCurrentHero().getField().get(1));
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gameview.refresh(this, model);
				spelltobeused=null;
				herotousepower=null;
				minionInAttack=null;
			}
				else{
			minionInAttack=model.getCurrentHero().getField().get(1);
			spelltobeused=null;
			herotousepower=null;
		}
		}
		if(e.getActionCommand().equals("Minion on field to attack number2")){
			if(herotousepower!=null && herotousepower instanceof Priest){
				try {
					((Priest)herotousepower).useHeroPower(model.getCurrentHero().getField().get(2));
					gameview.refresh(this,model);
					spelltobeused=null;
					herotousepower=null;
					minionInAttack=null;
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullHandException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				spelltobeused=null;
				herotousepower=null;
				minionInAttack=null;
				gameview.refresh(this, model);
				}
			else if(spelltobeused!=null && spelltobeused instanceof MinionTargetSpell){
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell) spelltobeused, model.getCurrentHero().getField().get(2));
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gameview.refresh(this, model);
				spelltobeused=null;
				herotousepower=null;
				minionInAttack=null;
			}
				else{
			
			minionInAttack=model.getCurrentHero().getField().get(2);
			spelltobeused=null;
			herotousepower=null;
		}
		}
		if(e.getActionCommand().equals("Minion on field to attack number3")){
			if(herotousepower!=null && herotousepower instanceof Priest){
				try {
					((Priest)herotousepower).useHeroPower(model.getCurrentHero().getField().get(3));
					gameview.refresh(this,model);
					spelltobeused=null;
					herotousepower=null;
					minionInAttack=null;
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullHandException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				spelltobeused=null;
				herotousepower=null;
				minionInAttack=null;
				gameview.refresh(this,model);
			}
			else if(spelltobeused!=null && spelltobeused instanceof MinionTargetSpell){
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell) spelltobeused, model.getCurrentHero().getField().get(3));
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gameview.refresh(this, model);
				spelltobeused=null;
				herotousepower=null;
				minionInAttack=null;
			}
				else{
			
			minionInAttack=model.getCurrentHero().getField().get(3);
			spelltobeused=null;
			herotousepower=null;
		}
		}
		if(e.getActionCommand().equals("Minion on field to attack number4")){
			if(herotousepower!=null && herotousepower instanceof Priest){
				try {
					((Priest)herotousepower).useHeroPower(model.getCurrentHero().getField().get(4));
					gameview.refresh(this,model);
					spelltobeused=null;
					herotousepower=null;
					minionInAttack=null;
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullHandException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				spelltobeused=null;
				herotousepower=null;
				minionInAttack=null;
				gameview.refresh(this, model);
			}
			else if(spelltobeused!=null && spelltobeused instanceof MinionTargetSpell){
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell) spelltobeused, model.getCurrentHero().getField().get(4));
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gameview.refresh(this, model);
				spelltobeused=null;
				herotousepower=null;
				minionInAttack=null;
			}
				else{
			
			minionInAttack=model.getCurrentHero().getField().get(4);
			spelltobeused=null;
			herotousepower=null;
		}
		}
		if(e.getActionCommand().equals("Minion on field to attack number5")){
			if(herotousepower!=null && herotousepower instanceof Priest){
				try {
					((Priest)herotousepower).useHeroPower(model.getCurrentHero().getField().get(5));
					gameview.refresh(this,model);
					spelltobeused=null;
					herotousepower=null;
					minionInAttack=null;
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullHandException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				gameview.refresh(this, model);
				spelltobeused=null;
				herotousepower=null;
				minionInAttack=null;
				}
			else if(spelltobeused!=null && spelltobeused instanceof MinionTargetSpell){
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell) spelltobeused, model.getCurrentHero().getField().get(5));
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gameview.refresh(this, model);
				spelltobeused=null;
				herotousepower=null;
				minionInAttack=null;
			}
				else{
			
			minionInAttack=model.getCurrentHero().getField().get(5);
			spelltobeused=null;
			herotousepower=null;
		}
		}
		if(e.getActionCommand().equals("Minion on field to attack number6")){
			if(herotousepower!=null && herotousepower instanceof Priest){
				try {
					((Priest)herotousepower).useHeroPower(model.getCurrentHero().getField().get(6));
					gameview.refresh(this,model);
					spelltobeused=null;
					herotousepower=null;
					minionInAttack=null;
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullHandException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}	
				spelltobeused=null;
				herotousepower=null;
				minionInAttack=null;
				}
			else if(spelltobeused!=null && spelltobeused instanceof MinionTargetSpell){
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell) spelltobeused, model.getCurrentHero().getField().get(6));
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				gameview.refresh(this, model);
				spelltobeused=null;
				herotousepower=null;
				minionInAttack=null;
			}
				else{
			
			minionInAttack=model.getCurrentHero().getField().get(6);
			spelltobeused=null;
			herotousepower=null;
		}
		}
		
		if(e.getActionCommand().equals("wants to attack the minion number 0")){
			if(herotousepower==null && spelltobeused==null && minionInAttack!=null){
				try {
					model.getCurrentHero().attackWithMinion(minionInAttack,(model.getOpponent().getField().get(0)));
					gameview.refresh(this, model);
					minionInAttack=null;
				} catch (CannotAttackException e1) {
					new PopUpp("Sorry,This Minion Cannot Attack");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (TauntBypassException e1) {
					new PopUpp("Sorry, You Must Attack the Taunt Minion First");
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					new PopUpp("Sorry, You Cannot Attack This Target");
					e1.printStackTrace();
				} catch (NotSummonedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
			else if(herotousepower==null && minionInAttack==null && spelltobeused!=null && spelltobeused instanceof MinionTargetSpell){
			try {
				model.getCurrentHero().castSpell((MinionTargetSpell) spelltobeused, model.getOpponent().getField().get(0));
				gameview.refresh(this, model);
				spelltobeused=null;
			} catch (NotYourTurnException e1) {
				new PopUpp("Sorry, Not Your Turn");
				e1.printStackTrace();
			} catch (NotEnoughManaException e1) {

				new PopUpp("Sorry, No Enough Mana");
				e1.printStackTrace();
			} catch (InvalidTargetException e1) {
				new PopUpp("Sorry, You Cannot Attack This Target");
				e1.printStackTrace();
			}
		}
			else if(herotousepower==null && minionInAttack==null && spelltobeused!=null && spelltobeused instanceof LeechingSpell){
				try {
					model.getCurrentHero().castSpell((LeechingSpell)spelltobeused, model.getOpponent().getField().get(0));
					spelltobeused=null;
					gameview.refresh(this, model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");					e1.printStackTrace();
				}
			}
			else if(herotousepower!=null && minionInAttack==null && spelltobeused==null && (herotousepower instanceof Mage || herotousepower instanceof Priest)){
				if(herotousepower instanceof Mage){
				try {
					((Mage)herotousepower).useHeroPower((Minion)model.getOpponent().getField().get(0));
					herotousepower=null;
					gameview.refresh(this, model);
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					new PopUpp("Sorry, Your Hero Power is Already Used");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (FullHandException e1) {
					new PopUpp("Sorry, Your Hand is Full");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else if(herotousepower instanceof Priest){
					try {
						((Priest)herotousepower).useHeroPower((Minion)model.getOpponent().getField().get(0));
						herotousepower=null;
						gameview.refresh(this, model);
					} catch (NotEnoughManaException e1) {

						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					} catch (HeroPowerAlreadyUsedException e1) {
						new PopUpp("Sorry, Your Hero Power is Already Used");
						e1.printStackTrace();
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FullHandException e1) {
						new PopUpp("Sorry, Your Hand is Full");
						e1.printStackTrace();
					} catch (FullFieldException e1) {
						new PopUpp("Sorry, Your Field is Full");
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
	}
		if(e.getActionCommand().equals("wants to attack the minion number 1")){
			if(herotousepower==null && spelltobeused==null && minionInAttack!=null){
				try {
					model.getCurrentHero().attackWithMinion(minionInAttack,(model.getOpponent().getField().get(1)));
					gameview.refresh(this, model);
					minionInAttack=null;
				} catch (CannotAttackException e1) {
					new PopUpp("Sorry,This Minion Cannot Attack");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TauntBypassException e1) {
					new PopUpp("Sorry, You Must Attack the Taunt Minion First");
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					new PopUpp("Sorry, You Cannot Attack This Target");
					e1.printStackTrace();
				} catch (NotSummonedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
			else if(herotousepower==null && minionInAttack==null && spelltobeused!=null && spelltobeused instanceof MinionTargetSpell){
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell) spelltobeused, model.getOpponent().getField().get(1));
					gameview.refresh(this, model);
					spelltobeused=null;
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					new PopUpp("Sorry, You Cannot Attack This Target");
					e1.printStackTrace();
				}
			}	
			else if(herotousepower==null && minionInAttack==null && spelltobeused!=null && spelltobeused instanceof LeechingSpell){
				try {
					model.getCurrentHero().castSpell((LeechingSpell)spelltobeused, model.getOpponent().getField().get(1));
					spelltobeused=null;
					gameview.refresh(this, model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");					e1.printStackTrace();
				}
			}
			else if(herotousepower!=null && minionInAttack==null && spelltobeused==null && (herotousepower instanceof Mage || herotousepower instanceof Priest)){
				if(herotousepower instanceof Mage){
				try {
					((Mage)herotousepower).useHeroPower((Minion)model.getOpponent().getField().get(1));
					herotousepower=null;
					gameview.refresh(this, model);
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					new PopUpp("Sorry, Your Hero Power is Already Used");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (FullHandException e1) {
					new PopUpp("Sorry, Your Hand is Full");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else if(herotousepower instanceof Priest){
					try {
						((Priest)herotousepower).useHeroPower((Minion)model.getOpponent().getField().get(1));
						herotousepower=null;
						gameview.refresh(this, model);
					} catch (NotEnoughManaException e1) {

						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					} catch (HeroPowerAlreadyUsedException e1) {
						new PopUpp("Sorry, Your Hero Power is Already Used");
						e1.printStackTrace();
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FullHandException e1) {
						new PopUpp("Sorry, Your Hand is Full");
						e1.printStackTrace();
					} catch (FullFieldException e1) {
						new PopUpp("Sorry, Your Field is Full");
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
		}
		
		if(e.getActionCommand().equals("wants to attack the minion number 2")){
			if(herotousepower==null && spelltobeused==null && minionInAttack!=null){
			try {
				model.getCurrentHero().attackWithMinion(minionInAttack,(model.getOpponent().getField().get(2)));
				gameview.refresh(this, model);
				minionInAttack=null;
			} catch (CannotAttackException e1) {
				new PopUpp("Sorry,This Minion Cannot Attack");
				e1.printStackTrace();
			} catch (NotYourTurnException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			} catch (TauntBypassException e1) {
				new PopUpp("Sorry, You Must Attack the Taunt Minion First");
				e1.printStackTrace();
			} catch (InvalidTargetException e1) {
				new PopUpp("Sorry, You Cannot Attack This Target");
				e1.printStackTrace();
			} catch (NotSummonedException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
		}
	
			else if(herotousepower==null && minionInAttack==null && spelltobeused!=null && spelltobeused instanceof MinionTargetSpell){
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell) spelltobeused, model.getOpponent().getField().get(2));
					gameview.refresh(this, model);
					spelltobeused=null;
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					new PopUpp("Sorry, You Cannot Attack This Target");
					e1.printStackTrace();
				}
			}
			else if(herotousepower==null && minionInAttack==null && spelltobeused!=null && spelltobeused instanceof LeechingSpell){
				try {
					model.getCurrentHero().castSpell((LeechingSpell)spelltobeused, model.getOpponent().getField().get(2));
					spelltobeused=null;
					gameview.refresh(this, model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");					e1.printStackTrace();
				}
			}
			else if(herotousepower!=null && minionInAttack==null && spelltobeused==null && (herotousepower instanceof Mage || herotousepower instanceof Priest)){
				if(herotousepower instanceof Mage){
				try {
					((Mage)herotousepower).useHeroPower((Minion)model.getOpponent().getField().get(2));
					herotousepower=null;
					gameview.refresh(this, model);
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					new PopUpp("Sorry, Your Hero Power is Already Used");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (FullHandException e1) {
					new PopUpp("Sorry, Your Hand is Full");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else if(herotousepower instanceof Priest){
					try {
						((Priest)herotousepower).useHeroPower((Minion)model.getOpponent().getField().get(2));
						herotousepower=null;
						gameview.refresh(this, model);
					} catch (NotEnoughManaException e1) {

						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					} catch (HeroPowerAlreadyUsedException e1) {
						new PopUpp("Sorry, Your Hero Power is Already Used");
						e1.printStackTrace();
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FullHandException e1) {
						new PopUpp("Sorry, Your Hand is Full");
						e1.printStackTrace();
					} catch (FullFieldException e1) {
						new PopUpp("Sorry, Your Field is Full");
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
		}
		if(e.getActionCommand().equals("wants to attack the minion number 3")){
			if(herotousepower==null && spelltobeused==null && minionInAttack!=null){
				try {
					model.getCurrentHero().attackWithMinion(minionInAttack,(model.getOpponent().getField().get(3)));
					gameview.refresh(this, model);
					minionInAttack=null;
				} catch (CannotAttackException e1) {
					new PopUpp("Sorry,This Minion Cannot Attack");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TauntBypassException e1) {
					new PopUpp("Sorry, You Must Attack the Taunt Minion First");
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					new PopUpp("Sorry, You Cannot Attack This Target");
					e1.printStackTrace();
				} catch (NotSummonedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
			else if(herotousepower==null && minionInAttack==null && spelltobeused!=null && spelltobeused instanceof MinionTargetSpell){
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell) spelltobeused, model.getOpponent().getField().get(3));
					gameview.refresh(this, model);
					spelltobeused=null;
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					new PopUpp("Sorry, You Cannot Attack This Target");
					e1.printStackTrace();
				}
			}
			else if(herotousepower==null && minionInAttack==null && spelltobeused!=null && spelltobeused instanceof LeechingSpell){
				try {
					model.getCurrentHero().castSpell((LeechingSpell)spelltobeused, model.getOpponent().getField().get(3));
					spelltobeused=null;
					gameview.refresh(this, model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");					e1.printStackTrace();
				}
			}
			else if(herotousepower!=null && minionInAttack==null && spelltobeused==null && (herotousepower instanceof Mage || herotousepower instanceof Priest)){
				if(herotousepower instanceof Mage){
				try {
					((Mage)herotousepower).useHeroPower((Minion)model.getOpponent().getField().get(3));
					herotousepower=null;
					gameview.refresh(this, model);
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					new PopUpp("Sorry, Your Hero Power is Already Used");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (FullHandException e1) {
					new PopUpp("Sorry, Your Hand is Full");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else if(herotousepower instanceof Priest){
					try {
						((Priest)herotousepower).useHeroPower((Minion)model.getOpponent().getField().get(3));
						herotousepower=null;
						gameview.refresh(this, model);
					} catch (NotEnoughManaException e1) {

						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					} catch (HeroPowerAlreadyUsedException e1) {
						new PopUpp("Sorry, Your Hero Power is Already Used");
						e1.printStackTrace();
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FullHandException e1) {
						new PopUpp("Sorry, Your Hand is Full");
						e1.printStackTrace();
					} catch (FullFieldException e1) {
						new PopUpp("Sorry, Your Field is Full");
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
	}
		if(e.getActionCommand().equals("wants to attack the minion number 4")){
			if(herotousepower==null && spelltobeused==null && minionInAttack!=null){
				try {
					model.getCurrentHero().attackWithMinion(minionInAttack,(model.getOpponent().getField().get(4)));
					gameview.refresh(this, model);
					minionInAttack=null;
				} catch (CannotAttackException e1) {
					new PopUpp("Sorry,This Minion Cannot Attack");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TauntBypassException e1) {
					new PopUpp("Sorry, You Must Attack the Taunt Minion First");
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					new PopUpp("Sorry, You Cannot Attack This Target");
					e1.printStackTrace();
				} catch (NotSummonedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
		
			else if(herotousepower==null && minionInAttack==null && spelltobeused!=null && spelltobeused instanceof MinionTargetSpell){
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell) spelltobeused, model.getOpponent().getField().get(4));
					gameview.refresh(this, model);
					spelltobeused=null;
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					new PopUpp("Sorry, You Cannot Attack This Target");
					e1.printStackTrace();
				}
			}
			else if(herotousepower==null && minionInAttack==null && spelltobeused!=null && spelltobeused instanceof LeechingSpell){
				try {
					model.getCurrentHero().castSpell((LeechingSpell)spelltobeused, model.getOpponent().getField().get(4));
					spelltobeused=null;
					gameview.refresh(this, model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");					e1.printStackTrace();
				}
			}
			else if(herotousepower!=null && minionInAttack==null && spelltobeused==null && (herotousepower instanceof Mage || herotousepower instanceof Priest)){
				if(herotousepower instanceof Mage){
				try {
					((Mage)herotousepower).useHeroPower((Minion)model.getOpponent().getField().get(4));
					herotousepower=null;
					gameview.refresh(this, model);
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					new PopUpp("Sorry, Your Hero Power is Already Used");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (FullHandException e1) {
					new PopUpp("Sorry, Your Hand is Full");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else if(herotousepower instanceof Priest){
					try {
						((Priest)herotousepower).useHeroPower((Minion)model.getOpponent().getField().get(4));
						herotousepower=null;
						gameview.refresh(this, model);
					} catch (NotEnoughManaException e1) {

						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					} catch (HeroPowerAlreadyUsedException e1) {
						new PopUpp("Sorry, Your Hero Power is Already Used");
						e1.printStackTrace();
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FullHandException e1) {
						new PopUpp("Sorry, Your Hand is Full");
						e1.printStackTrace();
					} catch (FullFieldException e1) {
						new PopUpp("Sorry, Your Field is Full");
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
		}
		
		if(e.getActionCommand().equals("wants to attack the minion number 5")){
			if(herotousepower==null && spelltobeused==null && minionInAttack!=null){
				try {
					model.getCurrentHero().attackWithMinion(minionInAttack,(model.getOpponent().getField().get(5)));
					gameview.refresh(this, model);
					minionInAttack=null;
				} catch (CannotAttackException e1) {
					new PopUpp("Sorry,This Minion Cannot Attack");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TauntBypassException e1) {
					new PopUpp("Sorry, You Must Attack the Taunt Minion First");
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					new PopUpp("Sorry, You Cannot Attack This Target");
					e1.printStackTrace();
				} catch (NotSummonedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			}
			else if(herotousepower==null && minionInAttack==null && spelltobeused!=null && spelltobeused instanceof MinionTargetSpell){
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell) spelltobeused, model.getOpponent().getField().get(5));
					gameview.refresh(this, model);
					spelltobeused=null;
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					new PopUpp("Sorry, You Cannot Attack This Target");
					e1.printStackTrace();
				}
			}
			else if(herotousepower==null && minionInAttack==null && spelltobeused!=null && spelltobeused instanceof LeechingSpell){
				try {
					model.getCurrentHero().castSpell((LeechingSpell)spelltobeused, model.getOpponent().getField().get(5));
					spelltobeused=null;
					gameview.refresh(this, model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");					e1.printStackTrace();
				}
			}
			else if(herotousepower!=null && minionInAttack==null && spelltobeused==null && (herotousepower instanceof Mage || herotousepower instanceof Priest)){
				if(herotousepower instanceof Mage){
				try {
					((Mage)herotousepower).useHeroPower((Minion)model.getOpponent().getField().get(5));
					herotousepower=null;
					gameview.refresh(this, model);
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					new PopUpp("Sorry, Your Hero Power is Already Used");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (FullHandException e1) {
					new PopUpp("Sorry, Your Hand is Full");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else if(herotousepower instanceof Priest){
					try {
						((Priest)herotousepower).useHeroPower((Minion)model.getOpponent().getField().get(5));
						herotousepower=null;
						gameview.refresh(this, model);
					} catch (NotEnoughManaException e1) {

						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					} catch (HeroPowerAlreadyUsedException e1) {
						new PopUpp("Sorry, Your Hero Power is Already Used");
						e1.printStackTrace();
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FullHandException e1) {
						new PopUpp("Sorry, Your Hand is Full");
						e1.printStackTrace();
					} catch (FullFieldException e1) {
						new PopUpp("Sorry, Your Field is Full");
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
		}
		
		if(e.getActionCommand().equals("wants to attack the minion number 6")){
			if(herotousepower==null && spelltobeused==null && minionInAttack!=null){
				try {
					model.getCurrentHero().attackWithMinion(minionInAttack,(model.getOpponent().getField().get(6)));
					gameview.refresh(this, model);
					minionInAttack=null;
				} catch (CannotAttackException e1) {
					new PopUpp("Sorry,This Minion Cannot Attack");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (TauntBypassException e1) {
					new PopUpp("Sorry, You Must Attack the Taunt Minion First");
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					new PopUpp("Sorry, You Cannot Attack This Target");
					e1.printStackTrace();
				} catch (NotSummonedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
		}
			else if(herotousepower==null && minionInAttack==null && spelltobeused!=null && spelltobeused instanceof MinionTargetSpell){
				try {
					model.getCurrentHero().castSpell((MinionTargetSpell) spelltobeused, model.getOpponent().getField().get(6));
					gameview.refresh(this, model);
					spelltobeused=null;
				} catch (NotYourTurnException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (InvalidTargetException e1) {
					new PopUpp("Sorry, You Cannot Attack This Target");
					e1.printStackTrace();
				}
			}	
			else if(herotousepower==null && minionInAttack==null && spelltobeused!=null && spelltobeused instanceof LeechingSpell){
				try {
					model.getCurrentHero().castSpell((LeechingSpell)spelltobeused, model.getOpponent().getField().get(6));
					spelltobeused=null;
					gameview.refresh(this, model);
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");					e1.printStackTrace();
				}
			}
			else if(herotousepower!=null && minionInAttack==null && spelltobeused==null && (herotousepower instanceof Mage || herotousepower instanceof Priest)){
				if(herotousepower instanceof Mage){
				try {
					((Mage)herotousepower).useHeroPower((Minion)model.getOpponent().getField().get(6));
					herotousepower=null;
					gameview.refresh(this, model);
				} catch (NotEnoughManaException e1) {

					new PopUpp("Sorry, No Enough Mana");
					e1.printStackTrace();
				} catch (HeroPowerAlreadyUsedException e1) {
					new PopUpp("Sorry, Your Hero Power is Already Used");
					e1.printStackTrace();
				} catch (NotYourTurnException e1) {
					new PopUpp("Sorry, Not Your Turn");
					e1.printStackTrace();
				} catch (FullHandException e1) {
					new PopUpp("Sorry, Your Hand is Full");
					e1.printStackTrace();
				} catch (FullFieldException e1) {
					new PopUpp("Sorry, Your Field is Full");
					e1.printStackTrace();
				} catch (CloneNotSupportedException e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				}
				else if(herotousepower instanceof Priest){
					try {
						((Priest)herotousepower).useHeroPower((Minion)model.getOpponent().getField().get(6));
						herotousepower=null;
						gameview.refresh(this, model);
					} catch (NotEnoughManaException e1) {

						new PopUpp("Sorry, No Enough Mana");
						e1.printStackTrace();
					} catch (HeroPowerAlreadyUsedException e1) {
						new PopUpp("Sorry, Your Hero Power is Already Used");
						e1.printStackTrace();
					} catch (NotYourTurnException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					} catch (FullHandException e1) {
						new PopUpp("Sorry, Your Hand is Full");
						e1.printStackTrace();
					} catch (FullFieldException e1) {
						new PopUpp("Sorry, Your Field is Full");
						e1.printStackTrace();
					} catch (CloneNotSupportedException e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
				}
	}
		if(e.getActionCommand().equals("turn ended")){
			try {
				model.endTurn();
				gameview.refresh(this, model);
				minionInAttack=null;
				herotobeattacked=null;
				spelltobeused=null;
				herotousepower=null;
				
			} catch (FullHandException | CloneNotSupportedException e1) {
				FullHandException ex=(FullHandException) e1;
				new PopUpp("Sorry, Your Hand is Full, this Card now will be burned",ex.getBurned());
				gameview.refresh(this, model);
				minionInAttack=null;
				herotobeattacked=null;
				spelltobeused=null;
				herotousepower=null;
			}
		}
		if(e.getActionCommand().equals("new game")){
			GameController n=new GameController();
		}
		
		
	}
	
	
	

	
	public static void main (String [] args){
		GameController c=new GameController();
		
	
	}
}