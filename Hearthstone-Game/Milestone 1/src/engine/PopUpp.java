package engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Component;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.TextArea;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

import model.cards.Card;
import model.cards.spells.Spell;
import model.cards.minions.*;

public class PopUpp extends JFrame{
	
	public PopUpp(String s){
		super();
		JLabel b=new JLabel(s);
		b.setBounds(600, 600, 600, 600);
		b.setFont(new Font("Serif", Font.PLAIN, 22));
		this.setBounds(600,600,600,600);
		this.getContentPane().setBackground(Color.ORANGE);
		this.add(b);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		this.revalidate();
		this.repaint();
	}
	
	public PopUpp(String s,int x,GameController c){
		super();
		if(x==1){
		JLabel b=new JLabel(s);
		b.setFont(new Font("Serif", Font.PLAIN, 22));
		this.setBounds(400,400,400,400);
		this.getContentPane().setBackground(Color.CYAN);
		this.add(BorderLayout.NORTH,b);
		this.setLocationRelativeTo(null);
		JButton j=new JButton("click to Start a New Game");
		j.setActionCommand("new game");
		j.addActionListener(c);
		this.add(BorderLayout.SOUTH,j);
		this.setVisible(true);
		this.revalidate();
		this.repaint();
		
		}
	}
	public PopUpp(String s, Card c) {
		super();
		this.setLayout(new GridLayout(2,1));
		JLabel b=new JLabel(s);
		b.setBounds(300, 300, 300, 300);
		b.setFont(new Font("Serif", Font.PLAIN, 22));
		this.setBounds(400,400,400,400);
		this.add(b);
		this.setLocationRelativeTo(null);
		this.setVisible(true);
		if(c instanceof Spell){
		JPanel k= new JPanel();
		k.setVisible(true);
		k.setLayout(new GridLayout(3,1));
		k.add(new JLabel("Name: "+c.getName()));
		k.add(new JLabel("Mana Cost: "+c.getManaCost()+""));
		k.add(new JLabel("Rarity: "+c.getRarity()+""));
		this.add(k);
		this.revalidate();
		this.repaint();
		}
		else if(c instanceof Minion){
			JPanel k= new JPanel();
			k.setVisible(true);
			k.setLayout(new GridLayout(8,1));
			k.add(new JLabel("Name: "+c.getName()));
			k.add(new JLabel("Mana Cost: "+c.getManaCost()+""));
			k.add(new JLabel("Rarity: "+c.getRarity()+""));
			k.add(new JLabel("Attack: "+((Minion)c).getAttack()));
			k.add(new JLabel("HP: "+((Minion)c).getCurrentHP()));
			k.add(new JLabel("Taunt: "+((Minion)c).isTaunt()));
			k.add(new JLabel("Divine: "+((Minion)c).isDivine()));
			k.add(new JLabel("Charge: "+(!((Minion)c).isSleeping())));
			this.add(k);
			this.revalidate();
			this.repaint();
		}
		
	}
		}


