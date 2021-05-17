package engine;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import model.cards.minions.Minion;

public class currentField extends JPanel{
	
	public currentField(GameController c,Game g){
		super();
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
		if(g.getCurrentHero().getField().isEmpty()){
			this.add(new JLabel("Your field is empty click on minions in your hand to play them"));
		}
		else{
		this.setLayout(new GridLayout(1,7));
		for(int i=0;i<g.getCurrentHero().getField().size();i++){
			Minion m=g.getCurrentHero().getField().get(i);
			JPanel pls=new JPanel();
			pls.setLayout(new GridLayout(2,1));
			JTextArea e=new JTextArea(m.tostring());
			e.setLineWrap(true);
			pls.add(e);
			JButton j=new JButton("Attack");
			j.setActionCommand("Minion on field to attack number"+i);
			j.addActionListener(c);
			pls.add(j);
			pls.setVisible(true);
			pls.setBorder(blackline);
			this.add(pls);
		}
		}
	}

}
