package routingtool.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JToolBar;

public class ToolBar extends JToolBar{
	
	public static final ImageIcon ADD_ICON = new ImageIcon("./res/image/IconPlus.png");
	public static final ImageIcon REMOVE_ICON = new ImageIcon("./res/image/IconMinus.png");
	
	public ToolBar(){
		this.setFloatable(false);
		
		JButton addButton = new JButton();
		addButton.setIcon(ADD_ICON);
		this.add(addButton);
		
		JButton removeButton = new JButton();
		removeButton.setIcon(REMOVE_ICON);
		this.add(removeButton);
	}
	
	

}
