package engine;

import java.awt.GridLayout;

import javax.swing.JPanel;

public class Mains extends JPanel {
	private opponentPanel oppPanel;
	private oppHand opponentHand;
	private oppoField oppfield1;
	private currentField currfield1;
	private currentHand currhand1;
	private currentPanel currPanel;
	private Game r;
	
public Mains(GameController c,Game g){
		super();
		r=g;
		this.setLayout(new GridLayout(6,1));
		this.setVisible(true);
		oppPanel=new opponentPanel(c,g);
		opponentHand=new oppHand(c,g);
		oppfield1= new oppoField(c,g);
		currfield1= new currentField(c,g);
		currhand1=new currentHand(c,g);
		currPanel=new currentPanel(c,g);
		this.add(oppPanel);
		this.add(opponentHand);
		this.add(oppfield1);
		this.add(currfield1);
		this.add(currhand1);
		this.add(currPanel);
		
		
			this.revalidate();
			this.repaint();
	}
public Game getGame(){
	return r;
}

}
