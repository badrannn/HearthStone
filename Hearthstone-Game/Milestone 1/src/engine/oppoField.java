package engine;

import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.border.Border;

import model.cards.minions.Minion;

public class oppoField extends JPanel{

	
	public oppoField(GameController c,Game g){
		super();
		Border blackline = BorderFactory.createLineBorder(Color.black);
		this.setBorder(blackline);
		if(g.getOpponent().getField().isEmpty()){
			this.add(new JLabel("Opponent's field is empty"));
		}
		else{
		this.setLayout(new GridLayout(1,7));
		for(int i=0;i<g.getOpponent().getField().size();i++){
			Minion m=g.getOpponent().getField().get(i);
			JPanel h=new JPanel();
			h.setBorder(blackline);
			h.setLayout(new GridLayout(2,1));
			JTextArea d=new JTextArea(m.tostring());
			d.setLineWrap(true);
			JButton b=new JButton("DAMAGE!");
			b.addActionListener(c);
			b.setActionCommand("wants to attack the minion number "+i);
			b.setBorder(blackline);
			h.add(d);
			h.add(b);
			this.add(h);
		}
		}
	}
}