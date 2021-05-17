package engine;

import java.awt.GridLayout;
import java.awt.PopupMenu;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class GameView extends JFrame implements GameListener{
	private Mains main;
	//private GameController f;
	//private Game h;
	
public GameView(GameController c, Game g){
	super();
	main=new Mains(c,g);
	this.setLocationRelativeTo(null);
	//this.setBounds(600, 600, 600, 600);
	this.setExtendedState(JFrame.MAXIMIZED_BOTH);
	this.setVisible(true);
	this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		this.add(main);
		this.revalidate();
		this.repaint();
	}	
	
	
public void refresh(GameController f,Game h){
	Mains n=new Mains(f,h);
	this.remove(main);
	main=n;
	this.add(main);
	this.revalidate();
	this.repaint();
	
}	
public void removeMain(String s){
	this.remove(main);
	JLabel text=new JLabel(s);
	this.add(text);
	this.revalidate();
	this.repaint();
}
	
	
	@Override
	public void onGameOver() {
	
	}







}