package engine;

import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import model.cards.Card;
import model.cards.minions.Minion;
import model.heroes.Hero;

public interface ActionValidator {
    void validateTurn(Hero var1) throws NotYourTurnException;

    void validateAttack(Minion var1, Minion var2) throws TauntBypassException, InvalidTargetException, NotSummonedException, CannotAttackException;

    void validateAttack(Minion var1, Hero var2) throws TauntBypassException, NotSummonedException, InvalidTargetException, CannotAttackException;

    void validateManaCost(Card var1) throws NotEnoughManaException;

    void validatePlayingMinion(Minion var1) throws FullFieldException;

    void validateUsingHeroPower(Hero var1) throws NotEnoughManaException, HeroPowerAlreadyUsedException;
}