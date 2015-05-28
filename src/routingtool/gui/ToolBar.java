package routingtool.gui;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JToolBar;

import routingtool.Controller;
import routingtool.components.Event;
import routingtool.components.PokemonTeam;
import routingtool.gui.eventadd.AddEventWindow;

@SuppressWarnings("serial")
public class ToolBar extends JToolBar{
	
	public static final ImageIcon ADD_ICON = new ImageIcon(ToolBar.class.getResource("/image/IconPlus.png"));
	public static final ImageIcon REMOVE_ICON = new ImageIcon(ToolBar.class.getResource("/image/IconMinus.png"));
	public static final ImageIcon EDIT_ICON = new ImageIcon(ToolBar.class.getResource("/image/IconEdit.png"));
	public static final ImageIcon MOVEUP_ICON = new ImageIcon(ToolBar.class.getResource("/image/IconMoveUp.png"));
	public static final ImageIcon MOVEDOWN_ICON = new ImageIcon(ToolBar.class.getResource("/image/IconMoveDown.png"));
	
	public ToolBar(final JFrame mainWindow, final Controller c, final EventGridPanel egp){
		this.egp = egp;
		this.setFloatable(false);
		
		JButton addButton = new JButton();
		addButton.setToolTipText("Add new Event");
		addButton.setIcon(ADD_ICON);
		addButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				AddEventWindow aew;
				if (c.getEventListSize() == 0){
					aew = new AddEventWindow(mainWindow, c, true, new PokemonTeam());
				}
				else{
					PokemonTeam pp = c.receiveEvent(c.getEventListSize()-1).getStateAfter().getTeam();
					aew = new AddEventWindow(mainWindow, c, false, pp);
				}
				Event ev = aew.showDialog();
				if (ev != null) c.addEvent(ev);
			}			
		});
		this.add(addButton);
		
		JButton removeButton = new JButton();
		removeButton.setToolTipText("Remove Event");
		removeButton.setIcon(REMOVE_ICON);	
		removeButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				int index = getSelectedIndex();
				if (index == 0){
					JOptionPane.showMessageDialog(mainWindow, "Initial Event can't be deleted.", "Error", JOptionPane.ERROR_MESSAGE);
				}else if (index > 0){
					int option = JOptionPane.showConfirmDialog(mainWindow, "Are you sure you want to delete this event?", "Delete Event", JOptionPane.YES_NO_OPTION);
					if (option == JOptionPane.YES_OPTION){
						c.deleteEvent(index);
					}
				}
			}			
		});
		this.add(removeButton);
		
		JButton editButton = new JButton();
		editButton.setToolTipText("Edit Event");
		editButton.setIcon(EDIT_ICON);	
		editButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				int index = getSelectedIndex();
				if (index < 0) return; //non-selected items	
				Event e = c.receiveEvent(index);
				AddEventWindow aew = new AddEventWindow(mainWindow, c, e, index == 0);
				Event ev = aew.showDialog();
				if (ev != null) c.editEvent(ev, index);
			}			
		});
		this.add(editButton);
		
		JButton moveUpButton = new JButton();
		moveUpButton.setToolTipText("Move up Event");
		moveUpButton.setIcon(MOVEUP_ICON);	
		moveUpButton.setEnabled(true);
		moveUpButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				int index = getSelectedIndex();
				if (index <= 0) return;
				c.moveUp(index);
			}			
		});
		this.add(moveUpButton);
		
		JButton moveDownButton = new JButton();
		moveDownButton.setToolTipText("Move down Event");
		moveDownButton.setIcon(MOVEDOWN_ICON);	
		moveDownButton.setEnabled(true);
		moveDownButton.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent event) {
				int index = getSelectedIndex();
				if (index >= c.getEventListSize()) return;
				c.moveDown(index);
			}			
		});
		this.add(moveDownButton);
	}
	
	public int getSelectedIndex(){
		return egp.getSelectedIndex();
	}
	
	private EventGridPanel egp;
}
