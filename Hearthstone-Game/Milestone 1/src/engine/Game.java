package engine;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import java.util.Iterator;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;
import model.heroes.HeroListener;

public class Game implements ActionValidator, HeroListener {
    private Hero firstHero;
    private Hero secondHero;
    private Hero currentHero;
    private Hero opponent;
    private GameListener listener;

    public Game(Hero p1, Hero p2) throws FullHandException, CloneNotSupportedException {
        this.firstHero = p1;
        this.secondHero = p2;
        this.firstHero.setListener(this);
        this.secondHero.setListener(this);
        this.firstHero.setValidator(this);
        this.secondHero.setValidator(this);
        int coin = (int)(Math.random() * 2.0D);
        this.currentHero = coin == 0 ? this.firstHero : this.secondHero;
        this.opponent = this.currentHero == this.firstHero ? this.secondHero : this.firstHero;
        this.currentHero.setCurrentManaCrystals(1);
        this.currentHero.setTotalManaCrystals(1);

        int i;
        for(i = 0; i < 3; ++i) {
            this.currentHero.drawCard();
        }

        for(i = 0; i < 4; ++i) {
            this.opponent.drawCard();
        }

    }

    public void validateTurn(Hero user) throws NotYourTurnException {
        if (user == this.opponent) {
            throw new NotYourTurnException("You can not do any action in your opponent's turn");
        }
    }

    public void validateAttack(Minion a, Minion t) throws TauntBypassException, InvalidTargetException, NotSummonedException, CannotAttackException {
        if (a.getAttack() <= 0) {
            throw new CannotAttackException("This minion Cannot Attack");
        } else if (a.isSleeping()) {
            throw new CannotAttackException("Give this minion a turn to get ready");
        } else if (a.isAttacked()) {
            throw new CannotAttackException("This minion has already attacked");
        } else if (!this.currentHero.getField().contains(a)) {
            throw new NotSummonedException("You can not attack with a minion that has not been summoned yet");
        } else if (this.currentHero.getField().contains(t)) {
            throw new InvalidTargetException("You can not attack a friendly minion");
        } else if (!this.opponent.getField().contains(t)) {
            throw new NotSummonedException("You can not attack a minion that your opponent has not summoned yet");
        } else {
            if (!t.isTaunt()) {
                for(int i = 0; i < this.opponent.getField().size(); ++i) {
                    if (((Minion)this.opponent.getField().get(i)).isTaunt()) {
                        throw new TauntBypassException("A minion with taunt is in the way");
                    }
                }
            }

        }
    }

    public void validateAttack(Minion m, Hero t) throws TauntBypassException, NotSummonedException, InvalidTargetException, CannotAttackException {
        if (m.getAttack() <= 0) {
            throw new CannotAttackException("This minion Cannot Attack");
        } else if (m.isSleeping()) {
            throw new CannotAttackException("Give this minion a turn to get ready");
        } else if (m.isAttacked()) {
            throw new CannotAttackException("This minion has already attacked");
        } else if (!this.currentHero.getField().contains(m)) {
            throw new NotSummonedException("You can not attack with a minion that has not been summoned yet");
        } else if (t.getField().contains(m)) {
            throw new InvalidTargetException("You can not attack yourself with your minions");
        } else {
            for(int i = 0; i < this.opponent.getField().size(); ++i) {
                if (((Minion)this.opponent.getField().get(i)).isTaunt()) {
                    throw new TauntBypassException("A minion with taunt is in the way");
                }
            }

        }
    }

    public void validateManaCost(Card c) throws NotEnoughManaException {
        if (this.currentHero.getCurrentManaCrystals() < c.getManaCost()) {
            throw new NotEnoughManaException("I don't have enough mana !!");
        }
    }

    public void validatePlayingMinion(Minion m) throws FullFieldException {
        if (this.currentHero.getField().size() == 7) {
            throw new FullFieldException("No space for this minion");
        }
    }

    public void validateUsingHeroPower(Hero h) throws NotEnoughManaException, HeroPowerAlreadyUsedException {
        if (h.getCurrentManaCrystals() < 2) {
            throw new NotEnoughManaException("I don't have enough mana !!");
        } else if (h.isHeroPowerUsed()) {
            throw new HeroPowerAlreadyUsedException(" I already used my hero power");
        }
    }

    public void onHeroDeath() {
        this.listener.onGameOver();
    }

    public void damageOpponent(int amount) {
        this.opponent.setCurrentHP(this.opponent.getCurrentHP() - amount);
    }

    public Hero getCurrentHero() {
        return this.currentHero;
    }

    public void setListener(GameListener listener) {
        this.listener = listener;
    }

    public void endTurn() throws FullHandException, CloneNotSupportedException {
        Hero temp = this.currentHero;
        this.currentHero = this.opponent;
        this.opponent = temp;
        this.currentHero.setTotalManaCrystals(this.currentHero.getTotalManaCrystals() + 1);
        this.currentHero.setCurrentManaCrystals(this.currentHero.getTotalManaCrystals());
        this.currentHero.setHeroPowerUsed(false);
        Iterator var3 = this.currentHero.getField().iterator();

        while(var3.hasNext()) {
            Minion m = (Minion)var3.next();
            m.setAttacked(false);
            m.setSleeping(false);
        }

        this.currentHero.drawCard();
    }

    public Hero getOpponent() {
        return this.opponent;
    }
}
