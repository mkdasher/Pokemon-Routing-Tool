package routingtool.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;

import routingtool.Controller;
import routingtool.ToolEngine;


public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static final ImageIcon JFRAME_ICON = new ImageIcon("./res/image/raikouicon.png");
	private int width = 600;
	private int height = 600;
	private Controller controller;
	private EventGridList eventGridList;
	private EventInfo eventInfo;
	//private StatusBar statusBar;
	//private DataDisplay dataDisplay;
	
	public MainWindow (final Controller c){
		super("Pokemon Routing Tool");
		this.setIconImage(JFRAME_ICON.getImage());
		this.controller = c;
		this.eventGridList = new EventGridList(c);
		this.eventInfo = new EventInfo(c);
		this.add(new MainPanel(), BorderLayout.CENTER);
		this.setJMenuBar(new MenuBar(controller));
		this.setParams();
	}
	
	/**
	 * Sets various parameters to default values
	 */
	private void setParams() {
		this.setSize(this.width, this.height);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(500,500));
	}
	
	@SuppressWarnings("serial")
	private class MainPanel extends JPanel{
		MainPanel(){
			this.setLayout(new GridLayout(2,0));
			this.add(eventGridList);
			this.add(eventInfo);
		}
	}
}
