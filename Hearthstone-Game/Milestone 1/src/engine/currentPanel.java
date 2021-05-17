package engine;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class currentPanel extends JPanel{
	private JLabel hp;
	private JButton heropower;
	private JButton nameofhero;
	private JLabel manano;
	private JLabel deck;
	private JButton endturn;
	
public currentPanel(GameController c,Game g){
	super();
	Border blackline = BorderFactory.createLineBorder(Color.black);
	this.setBorder(blackline);
	this.setLayout(new GridLayout(1,6));
	hp=new JLabel("hp" +Integer.toString(g.getCurrentHero().getCurrentHP()));
	hp.setBorder(blackline);
	this.add(hp);
	heropower=new JButton("Hero POWER!!");
	heropower.setActionCommand("current hero used herpower");
	heropower.addActionListener(c);
	heropower.setBorder(blackline);
	this.add(heropower);
	nameofhero=new JButton(g.getCurrentHero().getName());
	nameofhero.setActionCommand("hero0");
	nameofhero.addActionListener(c);
	nameofhero.setBorder(blackline);
	this.add(nameofhero);
	manano=new JLabel("mana:"+Integer.toString(g.getCurrentHero().getCurrentManaCrystals()));
	manano.setBorder(blackline);
	this.add(manano);
	deck=new JLabel("Cards in deck" + Integer.toString(g.getCurrentHero().getDeck().size()));
	deck.setBorder(blackline);
	this.add(deck);
	endturn=new JButton("click if you want to end you turn");
	endturn.addActionListener(c);
	endturn.setActionCommand("turn ended");
	this.add(endturn);
	this.revalidate();
	this.repaint();
	//if(g.getCurrentHero().getCurrentHP()==0){
		//c.onGameOver();
		
	}

	



public JLabel getHp() {
	return hp;
}

public void setHp(JLabel hp) {
	this.hp = hp;
}

public JButton getHeropower() {
	return heropower;
}

public void setHeropower(JButton heropower) {
	this.heropower = heropower;
}

public JButton getNameofhero() {
	return nameofhero;
}

public void setNameofhero(JButton nameofhero) {
	this.nameofhero = nameofhero;
}

public JLabel getManano() {
	return manano;
}

public void setManano(JLabel manano) {
	this.manano = manano;
}

public JLabel getDeck() {
	return deck;
}

public void setDeck(JLabel deck) {
	this.deck = deck;
}

public JButton getEndturn() {
	return endturn;
}

public void setEndturn(JButton endturn) {
	this.endturn = endturn;
}	

}
