package routingtool.gui;

import java.awt.BorderLayout;
import java.awt.Dimension;

import javax.swing.ImageIcon;
import javax.swing.JFrame;

import routingtool.Controller;


public class MainWindow extends JFrame {
	
	private static final long serialVersionUID = 1L;
	public static final ImageIcon JFRAME_ICON = new ImageIcon("./res/image/raikouicon.png");
	private int width = 600;
	private int height = 700;
	private Controller controller;
	private EventGridPanel eventGridPanel;
	private EventInfo eventInfo;
	
	public MainWindow (final Controller c){
		super("Pokemon Routing Tool");
		this.setIconImage(JFRAME_ICON.getImage());
		this.controller = c;
		this.eventGridPanel = new EventGridPanel(this, c);
		this.eventInfo = new EventInfo(c);
		this.add(eventGridPanel, BorderLayout.NORTH);
		this.add(eventInfo, BorderLayout.CENTER);
		this.setJMenuBar(new MenuBar(controller));
		this.setParams();
	}
	
	/**
	 * Sets various parameters to default values
	 */
	private void setParams() {
		this.setSize(this.width, this.height);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setMinimumSize(new Dimension(600,700));
		this.setLocationByPlatform(true);
		this.setVisible(true);
	}
}
