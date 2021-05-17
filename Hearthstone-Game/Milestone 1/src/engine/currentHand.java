package engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import model.cards.Card;
import model.cards.minions.*;
import model.cards.spells.*;
import model.cards.*;

public class currentHand extends JPanel{

	public currentHand(GameController c,Game g){
		super();
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
		this.setLayout(new GridLayout(1,10));
		
		for(int i=0;i<g.getCurrentHero().getHand().size();i++){
			Card d=((Card)(g.getCurrentHero().getHand().get(i)));
			JPanel k=new JPanel();
			k.setLayout(new GridLayout(2,1));
			k.setVisible(true);
			k.setBorder(blackline);
			if (d instanceof Spell){
				JTextArea r=new JTextArea(((Spell) d).tostring());
				r.setBorder(blackline);
				r.setLineWrap(true);
				k.add(r);
				JButton j=new JButton("click to play");
				j.addActionListener(c);
				j.setActionCommand(i+ "card in hand");
				j.setBorder(blackline);
				k.add(j);
				this.add(k);
			}
			else{
				JTextArea r=new JTextArea(((Minion) d).tostring());
				r.setBorder(blackline);
				r.setLineWrap(true);
				k.add(r);
				JButton j=new JButton("click to play");
				j.addActionListener(c);
				j.setActionCommand(i+ "card in hand");
				j.setBorder(blackline);
				//j.setBounds(5, 5, 5, 5);
				k.add(j);
				this.add(k);
			}
			}
	}
}
