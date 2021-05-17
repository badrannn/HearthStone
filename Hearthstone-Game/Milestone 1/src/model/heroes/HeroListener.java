package model.heroes;

import exceptions.FullHandException;

public interface HeroListener {
    void onHeroDeath();

    void damageOpponent(int var1);

    void endTurn() throws FullHandException, CloneNotSupportedException;
}
