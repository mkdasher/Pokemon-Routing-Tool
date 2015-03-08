package routingtool.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;

import javax.swing.JFileChooser;
import javax.swing.JMenu;
import javax.swing.JMenuBar;
import javax.swing.JMenuItem;
import javax.swing.JOptionPane;
import javax.swing.KeyStroke;

import routingtool.Controller;
import routingtool.ToolEngine;


public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	public MenuBar(final Controller c) {
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_J);
		final JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		openMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showOpenDialog(openMenuItem);
				String filePath;
				if (returnVal != JFileChooser.APPROVE_OPTION) {
					return; //nope
				}
				filePath =  fc.getSelectedFile().getAbsolutePath();
				c.requestLoadFile(filePath);
			}			
		});
		menu.add(openMenuItem);
		
		final JMenuItem saveMenuItem = new JMenuItem("Save as..");
		saveMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_S, ActionEvent.CTRL_MASK));
		saveMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser fc = new JFileChooser();
				int returnVal = fc.showSaveDialog(saveMenuItem);
				String filePath;
				if (returnVal != JFileChooser.APPROVE_OPTION) {
					return; //nope
				}
				filePath =  fc.getSelectedFile().getAbsolutePath();
				c.requestSaveFile(filePath);
			}			
		});
		menu.add(saveMenuItem);
		
		final JMenuItem exitMenuItem = new JMenuItem("Exit");
		exitMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				c.requestQuit();
			}			
		});
		menu.add(exitMenuItem);
		
		this.add(menu);

		menu = new JMenu("Help");
		final JMenuItem aboutMenuItem = new JMenuItem("About..");
		aboutMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent e) {
				String str = "Pokemon Routing Tool by MKDasher";
				JOptionPane.showMessageDialog(MenuBar.this, str , "Pokemon Routing Tool", JOptionPane.INFORMATION_MESSAGE);
			}			
		});
		menu.add(aboutMenuItem);
		this.add(menu);	
	}
}
