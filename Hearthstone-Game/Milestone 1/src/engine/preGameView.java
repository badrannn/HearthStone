package engine;
import java.awt.*;

import javax.swing.*;
import javax.swing.border.LineBorder;

//JPanel p = new JPanel();
//JLabel t = new JLabel("Player one");
//t.setFont(new Font("Verdana",1,20));
//p.add(t);
//p.setBorder(new LineBorder(Color.BLACK)); // make it easy to see
//this.add(p);
//this.getContentPane().add(BorderLayout.CENTER,mage)
//maybe use this to control heteb2a feen el buttons
// this mainly says en awel ma el le3ba hetefta7 di awel 7aga heyshofoha 

@SuppressWarnings("serial")
public class preGameView extends JFrame implements GameListener {
	private JPanel main;
	private JPanel title1;
	private JPanel title2;
	private JLabel theplayer1;
	private JLabel theplayer2;
	//private JTextArea text1;
	public preGameView(GameController c){
		super();
		main=new JPanel();
		main.setLayout(new GridLayout(5,1));
		this.setBounds(600, 600, 600, 600);
		this.setVisible(true);
		this.setDefaultCloseOperation(EXIT_ON_CLOSE);
		theplayer1=new JLabel("PLAYER 1 CHOOSE YOUR HERO TO START THE ADVENTURE!");
		main.add(theplayer1);
		title1= new JPanel();
	    title1.setLayout(new GridLayout(1,5));
		JButton mage= new JButton("Mage");
		mage.addActionListener(c);
		mage.setBounds(100, 100, 100, 100);
		mage.setActionCommand("chose mage 1");
		JButton priest= new JButton("Priest");
		priest.setBounds(100, 100, 100, 100);
		priest.addActionListener(c);
		priest.setActionCommand("chose priest 1");
		JButton hunter= new JButton("Hunter");
		hunter.setBounds(100, 100, 100, 100);
		hunter.addActionListener(c);
		hunter.setActionCommand("chose hunter 1");
		JButton paladin= new JButton("Paladin");
		paladin.setBounds(100, 100, 100, 100);
		paladin.addActionListener(c);
		paladin.setActionCommand("chose paladin 1");
		JButton warlock= new JButton("Warlock");
		warlock.setBounds(100, 100, 100, 100);
		warlock.addActionListener(c);
		warlock.setActionCommand("chose warlock 1");
		title1.add(mage);
		title1.add(priest);
		title1.add(hunter);
		title1.add(paladin);
		title1.add(warlock);
		main.add(title1);
		
		
		
		
		
		
		
		
		theplayer2=new JLabel("PLAYER 2 CHOOSE YOUR HERO TO START THE ADVENTURE!");
		main.add(theplayer2);
		title2= new JPanel();
	    title2.setLayout(new GridLayout(1,5));
		JButton mage2= new JButton("Mage");
		mage2.addActionListener(c);
		mage2.setBounds(100, 100, 100, 100);
		mage2.setActionCommand("chose mage 2");
		JButton priest2= new JButton("Priest");
		priest2.setBounds(100, 100, 100, 100);
		priest2.addActionListener(c);
		priest2.setActionCommand("chose priest 2");
		JButton hunter2= new JButton("Hunter");
		hunter2.setBounds(100, 100, 100, 100);
		hunter2.addActionListener(c);
		hunter2.setActionCommand("chose hunter 2");
		JButton paladin2= new JButton("Paladin");
		paladin2.setBounds(100, 100, 100, 100);
		paladin2.addActionListener(c);
		paladin2.setActionCommand("chose paladin 2");
		JButton warlock2= new JButton("Warlock");
		warlock2.setBounds(100, 100, 100, 100);
		warlock2.addActionListener(c);
		warlock2.setActionCommand("chose warlock 2");
		title2.add(mage2);
		title2.add(priest2);
		title2.add(hunter2);
		title2.add(paladin2);
		title2.add(warlock2);
		main.add(title2);
		JButton firstnext= new JButton("START PLAYING!!");
		firstnext.addActionListener(c);
		firstnext.setActionCommand("chosen the heros lets play");
		main.add(firstnext);
		
		
		
		
		this.add(main);
		this.setLocationRelativeTo(null);
		this.setExtendedState(JFrame.MAXIMIZED_BOTH);
		this.revalidate();
		this.repaint();
	}
	
	public JPanel gettitle() {
		return title1;
	}

	public void setTitle(JPanel title) {
		this.title1 = title;
	}

	public JLabel getTheplayer() {
		return theplayer1;
	}

	public void setTheplayer(JLabel theplayer) {
		this.theplayer1 = theplayer;
	}


	@Override
	public void onGameOver() {
		// TODO Auto-generated method stub
	}
		
	
}
