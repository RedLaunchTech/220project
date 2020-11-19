import java.util.ArrayList;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;

public class Gui {
	
	JFrame frame;
	
	JPanel gameMap;
	JPanel statsMap;
	
	JLabel label;
	
	ArrayList<JButton> gameBoard = new ArrayList<JButton>();
	
	public Gui() {
		frame = new JFrame("This is a program");
		
		
		gameMap = new JPanel();
		statsMap = new JPanel();
		
		gameMap.setBorder(BorderFactory.createEmptyBorder(30, 30, 10, 30));
		
		
	}
}
