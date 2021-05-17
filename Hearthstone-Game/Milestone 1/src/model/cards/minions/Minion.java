package model.cards.minions;

import exceptions.InvalidTargetException;
import model.cards.Card;
import model.cards.Rarity;
import model.heroes.Hero;

public class Minion extends Card implements Cloneable {
    private int attack;
    private int maxHP;
    private int currentHP;
    private boolean taunt;
    private boolean divine;
    private boolean sleeping;
    private boolean attacked;
    private MinionListener listener;

    public Minion(String name, int manaCost, Rarity rarity, int attack, int maxHP, boolean taunt, boolean divine, boolean charge) {
        super(name, manaCost, rarity);
        this.attack = attack;
        this.maxHP = maxHP;
        this.currentHP = maxHP;
        this.taunt = taunt;
        this.divine = divine;
        if (!charge) {
            this.sleeping = true;
        }

    }

    public void attack(Minion target) {
        if (this.divine && target.divine) {
            if (target.attack > 0) {
                this.divine = false;
            }

            if (this.attack > 0) {
                target.divine = false;
            }
        } else if (this.divine) {
            target.setCurrentHP(target.currentHP - this.attack);
            if (target.getAttack() > 0) {
                this.divine = false;
            }
        } else if (target.divine) {
            this.setCurrentHP(this.currentHP - target.attack);
            if (this.attack > 0) {
                target.divine = false;
            }
        } else {
            target.setCurrentHP(target.currentHP - this.attack);
            this.setCurrentHP(this.currentHP - target.attack);
        }

        this.attacked = true;
    }

    public void attack(Hero target) throws InvalidTargetException {
        this.attacked = true;
        target.setCurrentHP(target.getCurrentHP() - this.attack);
    }

    public boolean isTaunt() {
        return this.taunt;
    }

    public int getMaxHP() {
        return this.maxHP;
    }

    public void setMaxHP(int maxHp) {
        this.maxHP = maxHp;
    }

    public int getCurrentHP() {
        return this.currentHP;
    }

    public void setCurrentHP(int currentHP) {
        this.currentHP = currentHP;
        if (this.currentHP > this.maxHP) {
            this.currentHP = this.maxHP;
        } else if (this.currentHP <= 0) {
            this.currentHP = 0;
            this.listener.onMinionDeath(this);
        }

    }

    public void setListener(MinionListener listener) {
        this.listener = listener;
    }

    public int getAttack() {
        return this.attack;
    }

    public void setAttack(int attack) {
        this.attack = attack;
        if (this.attack <= 0) {
            this.attack = 0;
        }

    }

    public String tostring(){
    	String s= "Name: "+ this.getName() +", ManaCost: "+this.getManaCost()+", Type: "+this.getRarity()+", Attack: "+this.getAttack()+", Hp "+this.getCurrentHP();
    	if(this.isTaunt())
    		s=s+", taunt";
    	else
    		s=s +", non-taunt";
    	if(this.isDivine())
    		s=s+", Divine";
    	else
    		s=s+", non-Divine";
    	if(this.isSleeping())
    		s=s+", non-charge";
    	else
    		s=s+", Charge!";
    	if(!this.isAttacked() && !this.isSleeping()){
    		s=s+", Awake and ready to Attack!";
    	}
    	else { 
    		s=s+", sleeping";
    	}
    	
    		
    	
    	return s;
    }
    public void setTaunt(boolean isTaunt) {
        this.taunt = isTaunt;
    }

    public void setDivine(boolean divine) {
        this.divine = divine;
    }

    public boolean isAttacked() {
        return this.attacked;
    }

    public void setAttacked(boolean attacked) {
        this.attacked = attacked;
    }

    public boolean isSleeping() {
        return this.sleeping;
    }

    public void setSleeping(boolean sleeping) {
        this.sleeping = sleeping;
    }

    public boolean isDivine() {
        return this.divine;
    }

    public Minion clone() throws CloneNotSupportedException {
        return (Minion)super.clone();
    }
}
