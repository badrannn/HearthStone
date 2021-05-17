package engine;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

public class opponentPanel extends JPanel{
	private JLabel hp;
	private JButton nameofhero;
	private JLabel manano;
	private JLabel deck;
	private JPanel s;
	
public opponentPanel(GameController c,Game g){
	super();
	Border blackline = BorderFactory.createLineBorder(Color.black);
	this.setBorder(blackline);
	this.setLayout(new GridLayout(1,4));
	hp=new JLabel("hp" +Integer.toString(g.getOpponent().getCurrentHP()));
	hp.setBorder(blackline);
	this.add(hp);
	s=new JPanel();
	s.setLayout(new GridLayout(2,1));
	s.setBorder(blackline);
	s.setVisible(true);
	JTextArea q=new JTextArea(g.getOpponent().getName());
	q.setBorder(blackline);
	s.add(q);
	nameofhero=new JButton("DAMAGE!");
	nameofhero.setActionCommand("hero to be attacked");
	nameofhero.addActionListener(c);
	nameofhero.setBorder(blackline);
	s.add(nameofhero);
	this.add(s);
	manano=new JLabel("mana:"+Integer.toString(g.getOpponent().getCurrentManaCrystals()));
	manano.setBorder(blackline);
	this.add(manano);
	deck=new JLabel("Cards in deck" + Integer.toString(g.getOpponent().getDeck().size()));
	deck.setBorder(blackline);
	this.add(deck);
	this.revalidate();
	this.repaint();
	//if(g.getOpponent().getCurrentHP()==0){
		//c.onGameOver();
	//}
	
}

public JLabel getHp() {
	return hp;
}

public void setHp(JLabel hp) {
	this.hp = hp;
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


}
