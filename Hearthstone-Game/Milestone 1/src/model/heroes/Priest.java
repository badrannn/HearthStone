package model.heroes;

import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.NotEnoughManaException;
import exceptions.NotYourTurnException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import model.cards.Rarity;
import model.cards.minions.Minion;
import model.cards.spells.DivineSpirit;
import model.cards.spells.HolyNova;
import model.cards.spells.ShadowWordDeath;

public class Priest extends Hero {
    public Priest() throws IOException, CloneNotSupportedException {
        super("Anduin Wrynn");
    }

    public void buildDeck() throws IOException, CloneNotSupportedException {
        ArrayList<Minion> neutrals = getNeutralMinions(getAllNeutralMinions("C:\\Users\\H.Maher\\Desktop\\GUC\\Hearthstone Game\\Milestone 1\\src\\neutral_minions.csv"), 13);
        this.getDeck().addAll(neutrals);

        for(int i = 0; i < 2; ++i) {
            this.getDeck().add(new DivineSpirit());
            this.getDeck().add(new HolyNova());
            this.getDeck().add(new ShadowWordDeath());
        }

        Minion velen = new Minion("Prophet Velen", 7, Rarity.LEGENDARY, 7, 7, false, false, false);
        this.getDeck().add(velen);
        this.listenToMinions();
        Collections.shuffle(this.getDeck());
    }

    public void useHeroPower(Minion m) throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, CloneNotSupportedException, FullFieldException {
        super.useHeroPower();
        if (this.fieldContains("Prophet Velen")) {
            m.setCurrentHP(m.getCurrentHP() + 8);
        } else {
            m.setCurrentHP(m.getCurrentHP() + 2);
        }

    }

    public void useHeroPower(Hero h) throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, CloneNotSupportedException, FullFieldException {
        super.useHeroPower();
        if (this.fieldContains("Prophet Velen")) {
            h.setCurrentHP(h.getCurrentHP() + 8);
        } else {
            h.setCurrentHP(h.getCurrentHP() + 2);
        }

    }
}
