package engine;

import java.awt.Color;
import java.awt.Font;
import javax.swing.JFrame;
import javax.swing.JLabel
;
public class PopUp extends JFrame {
	
	public PopUp(String s) {
		JFrame j = new JFrame();
		j.setVisible(true);
		j.setBounds(500,60, 300, 210);
		j.setDefaultCloseOperation(HIDE_ON_CLOSE);
		JLabel t=new JLabel(s);
		j.add(t);
		t.setFont(new Font("Serif", Font.BOLD, 26));
		t.setForeground(Color.red);
		t.setHorizontalAlignment(JLabel.CENTER);
		j.getContentPane().setBackground(Color.yellow);
		this.revalidate();
		this.repaint();
	}

	public static void main(String[]args) {
		PopUp p = new PopUp("Sorry");
	}

}
