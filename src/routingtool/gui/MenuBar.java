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
import javax.swing.filechooser.FileFilter;
import javax.swing.filechooser.FileNameExtensionFilter;

import routingtool.Controller;


public class MenuBar extends JMenuBar {
	private static final long serialVersionUID = 1L;

	public MenuBar(final Controller c) {
		JMenu menu = new JMenu("File");
		menu.setMnemonic(KeyEvent.VK_F);
		final JMenuItem openMenuItem = new JMenuItem("Open");
		openMenuItem.setAccelerator(KeyStroke.getKeyStroke(KeyEvent.VK_O, ActionEvent.CTRL_MASK));
		openMenuItem.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				JFileChooser fc = new JFileChooser();
				FileFilter ff = new FileNameExtensionFilter("Pokemon Routing Tool File (.prt)", "prt");
				fc.addChoosableFileFilter(ff);
				fc.setFileFilter(ff);
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
				FileFilter ff = new FileNameExtensionFilter("Pokemon Routing Tool File (.prt)", "prt");
				fc.addChoosableFileFilter(ff);
				fc.setFileFilter(ff);
				int returnVal = fc.showSaveDialog(saveMenuItem);
				String filePath;
				if (returnVal != JFileChooser.APPROVE_OPTION) {
					return; //nope
				}
				filePath =  fc.getSelectedFile().getAbsolutePath();
				
				//Add extension by default if the user hasn't
				if (fc.getFileFilter() instanceof FileNameExtensionFilter){
					FileNameExtensionFilter fef = (FileNameExtensionFilter) fc.getFileFilter();
					String[] extensions = fef.getExtensions();
					boolean extensionFound = false;
					for(String e : extensions){
						if (filePath.endsWith( "." + e.toLowerCase())){
							extensionFound = true;
							break;
						}
					}
					if (!extensionFound) filePath = filePath + "." + extensions[0].toLowerCase();
				}
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
