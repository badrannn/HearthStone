package engine;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.Border;

public class oppHand extends JPanel{
	public oppHand(GameController c,Game g){
		super();
		this.setLayout(new GridLayout(1,7));
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
		for(int i=0;i<g.getOpponent().getHand().size();i++){
			JLabel n=new JLabel("Opponent card in hand no. " + Integer.toString(i+1));
			n.setBorder(blackline);
			this.add(n);
		}
	}

}
