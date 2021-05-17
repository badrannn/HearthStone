package model.heroes;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.CurseOfWeakness;
import model.cards.spells.SiphonSoul;
import model.cards.spells.TwistingNether;

public class Warlock extends Hero {
    public Warlock() throws IOException, CloneNotSupportedException {
        super("Gul'dan");
    }

    public void buildDeck() throws IOException, CloneNotSupportedException {
        ArrayList<Minion> neutrals = getNeutralMinions(getAllNeutralMinions("C:\\Users\\H.Maher\\Desktop\\GUC\\Hearthstone Game\\Milestone 1\\src\\neutral_minions.csv"), 13);
        this.getDeck().addAll(neutrals);

        for(int i = 0; i < 2; ++i) {
            this.getDeck().add(new CurseOfWeakness());
            this.getDeck().add(new SiphonSoul());
            this.getDeck().add(new TwistingNether());
        }

        Minion wilfred = new Minion("Wilfred Fizzlebang", 6, Rarity.LEGENDARY, 4, 4, false, false, false);
        this.getDeck().add(wilfred);
        this.listenToMinions();
        Collections.shuffle(this.getDeck());
    }

    public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, CloneNotSupportedException, FullFieldException {
        super.useHeroPower();
        this.setCurrentHP(this.getCurrentHP() - 2);
        Card c = this.drawCard();
        if (this.fieldContains("Wilfred Fizzlebang") && c instanceof Minion) {
            c.setManaCost(0);
            if (this.fieldContains("Chromaggus")) {
                ((Card)this.getHand().get(this.getHand().size() - 1)).setManaCost(0);
            }
        }

    }
}
