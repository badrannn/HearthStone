package model.heroes;

import engine.ActionValidator;
import exceptions.CannotAttackException;
import exceptions.FullFieldException;
import exceptions.FullHandException;
import exceptions.HeroPowerAlreadyUsedException;
import exceptions.InvalidTargetException;
import exceptions.NotEnoughManaException;
import exceptions.NotSummonedException;
import exceptions.NotYourTurnException;
import exceptions.TauntBypassException;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import model.cards.Card;
import model.cards.Rarity;
import model.cards.minions.Icehowl;
import model.cards.minions.Minion;
import model.cards.minions.MinionListener;
import model.cards.spells.AOESpell;
import model.cards.spells.FieldSpell;
import model.cards.spells.HeroTargetSpell;
import model.cards.spells.LeechingSpell;
import model.cards.spells.MinionTargetSpell;
import model.cards.spells.Spell;

public abstract class Hero implements MinionListener {
    private String name;
    private int currentHP;
    private boolean heroPowerUsed;
    private int totalManaCrystals;
    private int currentManaCrystals;
    private ArrayList<Card> deck;
    private ArrayList<Minion> field;
    private ArrayList<Card> hand;
    private int fatigueDamage;
    private HeroListener listener;
    private ActionValidator validator;

    public Hero(String name) throws IOException, CloneNotSupportedException {
        this.name = name;
        this.currentHP = 30;
        this.deck = new ArrayList();
        this.field = new ArrayList(7);
        this.hand = new ArrayList(10);
        this.buildDeck();
    }

    public abstract void buildDeck() throws IOException, CloneNotSupportedException;

    public static final ArrayList<Minion> getAllNeutralMinions(String filePath) throws IOException {
        BufferedReader br = new BufferedReader(new FileReader(filePath));
        ArrayList<Minion> minions = new ArrayList();

        for(String current = br.readLine(); current != null; current = br.readLine()) {
            String[] line = current.split(",");
            Minion minion = null;
            String n = line[0];
            int m = Integer.parseInt(line[1]);
            Rarity r = null;
            String var9;
            switch((var9 = line[2]).hashCode()) {
            case 98:
                if (var9.equals("b")) {
                    r = Rarity.BASIC;
                }
                break;
            case 99:
                if (var9.equals("c")) {
                    r = Rarity.COMMON;
                }
                break;
            case 101:
                if (var9.equals("e")) {
                    r = Rarity.EPIC;
                }
                break;
            case 108:
                if (var9.equals("l")) {
                    r = Rarity.LEGENDARY;
                }
                break;
            case 114:
                if (var9.equals("r")) {
                    r = Rarity.RARE;
                }
            }

            int a = Integer.parseInt(line[3]);
            int p = Integer.parseInt(line[4]);
            boolean t = line[5].equals("TRUE");
            boolean d = line[6].equals("TRUE");
            boolean c = line[7].equals("TRUE");
            if (!n.equals("Icehowl")) {
                minion = new Minion(n, m, r, a, p, t, d, c);
            } else {
                minion = new Icehowl();
            }

            minions.add(minion);
        }

        br.close();
        return minions;
    }

    public static final ArrayList<Minion> getNeutralMinions(ArrayList<Minion> minions, int count) throws CloneNotSupportedException {
        ArrayList<Minion> res = new ArrayList();
        int i = 0;

        while(i < count) {
            int index = (int)(Math.random() * (double)minions.size());
            Minion minion = (Minion)minions.get(index);
            int occ = 0;

            for(int j = 0; j < res.size(); ++j) {
                if (((Minion)res.get(j)).getName().equals(minion.getName())) {
                    ++occ;
                }
            }

            if (occ == 0) {
                res.add(minion.clone());
                ++i;
            } else if (occ == 1 && minion.getRarity() != Rarity.LEGENDARY) {
                res.add(minion.clone());
                ++i;
            }
        }

        return res;
    }

    public void listenToMinions() {
        Iterator var2 = this.deck.iterator();

        while(var2.hasNext()) {
            Card c = (Card)var2.next();
            if (c instanceof Minion) {
                ((Minion)c).setListener(this);
            }
        }

    }

    public void useHeroPower() throws NotEnoughManaException, HeroPowerAlreadyUsedException, NotYourTurnException, FullHandException, CloneNotSupportedException, FullFieldException {
        this.validator.validateTurn(this);
        this.validator.validateUsingHeroPower(this);
        this.currentManaCrystals -= 2;
        this.heroPowerUsed = true;
    }

    public void playMinion(Minion m) throws NotYourTurnException, NotEnoughManaException, FullFieldException {
        this.validator.validateTurn(this);
        this.validator.validateManaCost(m);
        this.validator.validatePlayingMinion(m);
        this.currentManaCrystals -= m.getManaCost();
        this.hand.remove(m);
        this.field.add(m);
    }

    public void attackWithMinion(Minion attacker, Minion target) throws CannotAttackException, NotYourTurnException, TauntBypassException, InvalidTargetException, NotSummonedException {
        this.validator.validateTurn(this);
        this.validator.validateAttack(attacker, target);
        attacker.attack(target);
    }

    public void attackWithMinion(Minion attacker, Hero target) throws CannotAttackException, NotYourTurnException, TauntBypassException, NotSummonedException, InvalidTargetException {
        this.validator.validateTurn(this);
        this.validator.validateAttack(attacker, target);
        attacker.attack(target);
    }

    public void castSpell(FieldSpell s) throws NotYourTurnException, NotEnoughManaException {
        this.validator.validateTurn(this);
        this.validator.validateManaCost((Spell)s);
        s.performAction(this.field);
        this.castSpellCommons((Spell)s);
    }

    public void castSpell(MinionTargetSpell s, Minion m) throws NotYourTurnException, NotEnoughManaException, InvalidTargetException {
        this.validator.validateTurn(this);
        this.validator.validateManaCost((Spell)s);
        s.performAction(m);
        this.castSpellCommons((Spell)s);
    }

    public void castSpell(LeechingSpell s, Minion m) throws NotYourTurnException, NotEnoughManaException {
        this.validator.validateTurn(this);
        this.validator.validateManaCost((Spell)s);
        int v = s.performAction(m);
        this.setCurrentHP(this.currentHP + v);
        this.castSpellCommons((Spell)s);
    }

    public void castSpell(HeroTargetSpell s, Hero h) throws NotYourTurnException, NotEnoughManaException {
        this.validator.validateTurn(this);
        this.validator.validateManaCost((Spell)s);
        s.performAction(h);
        this.castSpellCommons((Spell)s);
    }

    public void castSpell(AOESpell s, ArrayList<Minion> oppField) throws NotYourTurnException, NotEnoughManaException {
        this.validator.validateTurn(this);
        this.validator.validateManaCost((Spell)s);
        s.performAction(oppField, this.field);
        this.castSpellCommons((Spell)s);
    }

    private void castSpellCommons(Spell s) {
        this.currentManaCrystals -= s.getManaCost();
        this.hand.remove(s);
    }

    public void endTurn() throws FullHandException, CloneNotSupportedException {
        this.listener.endTurn();
    }

    public Card drawCard() throws FullHandException, CloneNotSupportedException {
        if (this.fatigueDamage > 0) {
            this.setCurrentHP(this.currentHP - this.fatigueDamage);
            ++this.fatigueDamage;
            return null;
        } else {
            Card c = (Card)this.deck.remove(0);
            if (this.deck.size() == 0) {
                this.fatigueDamage = 1;
            }

            if (this.hand.size() == 10) {
                throw new FullHandException("My hand is too full !!!", c);
            } else {
                this.hand.add(c);
                if (this.fieldContains("Chromaggus") && this.hand.size() < 10) {
                    this.hand.add(c.clone());
                }

                return c;
            }
        }
    }

    public int getCurrentHP() {
        return this.currentHP;
    }

    public void setCurrentHP(int hp) {
        this.currentHP = hp;
        if (this.currentHP > 30) {
            this.currentHP = 30;
        } else if (this.currentHP <= 0) {
            this.currentHP = 0;
            this.listener.onHeroDeath();
        }

    }

    public int getTotalManaCrystals() {
        return this.totalManaCrystals;
    }

    public void setTotalManaCrystals(int totalManaCrystals) {
        this.totalManaCrystals = totalManaCrystals;
        if (this.totalManaCrystals > 10) {
            this.totalManaCrystals = 10;
        }

    }

    public int getCurrentManaCrystals() {
        return this.currentManaCrystals;
    }

    public void setCurrentManaCrystals(int currentManaCrystals) {
        this.currentManaCrystals = currentManaCrystals;
        if (this.currentManaCrystals > 10) {
            this.currentManaCrystals = 10;
        }

    }

    public void onMinionDeath(Minion m) {
        this.field.remove(m);
    }

    public HeroListener getListener() {
        return this.listener;
    }

    public ArrayList<Minion> getField() {
        return this.field;
    }

    public void setListener(HeroListener listener) {
        this.listener = listener;
    }

    public ArrayList<Card> getHand() {
        return this.hand;
    }

    public boolean isHeroPowerUsed() {
        return this.heroPowerUsed;
    }

    public ArrayList<Card> getDeck() {
        return this.deck;
    }

    public void setHeroPowerUsed(boolean powerUsed) {
        this.heroPowerUsed = powerUsed;
    }

    public boolean fieldContains(String n) {
        Iterator var3 = this.field.iterator();

        while(var3.hasNext()) {
            Minion m = (Minion)var3.next();
            if (m.getName().equals(n)) {
                return true;
            }
        }

        return false;
    }

    public String getName() {
        return this.name;
    }

    public void setValidator(ActionValidator validator) {
        this.validator = validator;
    }
}