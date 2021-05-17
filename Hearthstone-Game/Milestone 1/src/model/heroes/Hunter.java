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
import model.cards.spells.KillCommand;
import model.cards.spells.MultiShot;

public class Hunter extends Hero {
    public Hunter() throws IOException, CloneNotSupportedException {
        super("Rexxar");
    }

    public void buildDeck() throws IOException, CloneNotSupportedException {
        ArrayList<Minion> neutrals = getNeutralMinions(getAllNeutralMinions("C:\\Users\\H.Maher\\Desktop\\GUC\\Hearthstone Game\\Milestone 1\\src\\neutral_minions.csv"), 15);
        this.getDeck().addAll(neutrals);

        for(int i = 0; i < 2; ++i) {
            this.getDeck().add(new KillCommand());
            this.getDeck().add(new MultiShot());
        }

        Minion krush = new Minion("King Krush", 9, Rarity.LEGENDARY, 8, 8, false, false, true);
        this.getDeck().add(krush);
        this.listenToMinions();
        Collections.shuffle(this.getDeck());
    }

    public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, CloneNotSupportedException, FullFieldException {
        super.useHeroPower();
        this.getListener().damageOpponent(2);
    }
}
