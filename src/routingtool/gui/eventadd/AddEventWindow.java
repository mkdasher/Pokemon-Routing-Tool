package routingtool.gui.eventadd;

import java.awt.BorderLayout;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.List;

import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import routingtool.Controller;
import routingtool.components.Event;
import routingtool.components.EventType;
import routingtool.components.PokemonTeam;

public class AddEventWindow extends JDialog{

	private static final long serialVersionUID = 9181;
	
	/**
	 * Constructor for Add Event
	 * @param parent
	 * @param c
	 */
	public AddEventWindow(JFrame parent, Controller c, boolean isInitialEvent, PokemonTeam myParty){
		super(parent, "New Event");
		this.event = null;
		this.c = c;
		this.parent = parent;
		this.setParams(isInitialEvent, myParty);
	}
	
	/**
	 * Constructor for Edit Event
	 * @param parent
	 * @param c
	 * @param initial
	 * @param is
	 */
	public AddEventWindow(JFrame parent, Controller c, Event event, boolean isInitialEvent){
		super(parent, "Edit Event");
		this.event = event;
		this.c = c;
		this.parent = parent;
		this.setParams(isInitialEvent, event.getStateBefore().getTeam());
	}
	
	@SuppressWarnings("serial")
	private void setParams(boolean isInitialEvent, PokemonTeam myParty){
		this.setModal(true);
		this.setLayout(new BorderLayout());
		this.eTypePanel = new EventSelectionTypePanel(isInitialEvent);
		this.eSettingsPanel = new EventSettingsPanel(this, isInitialEvent, this.event, myParty);
		this.getContentPane().add(this.eTypePanel, BorderLayout.NORTH);
		this.getContentPane().add(this.eSettingsPanel, BorderLayout.CENTER);
		this.add(new JPanel(){
			{
				this.setLayout(new GridLayout(1,2));
				
				JButton acceptButton = new JButton("Accept");
				acceptButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent arg0) {
						AddEventWindow.this.event = eSettingsPanel.getTopPanel().getEvent();
						if (event != null) AddEventWindow.this.dispose();							
					}
				});
				this.add(acceptButton);
				
				JButton cancelButton = new JButton("Cancel");
				cancelButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent e) {
						AddEventWindow.this.dispose();
					}
				});
				this.add(cancelButton);
			}
		}, BorderLayout.SOUTH);
		this.pack();
		this.setLocationByPlatform(true);
		this.setLocation(parent.getX() + 10, parent.getY() + 30);
	}
	
	public Event showDialog(){
		this.setVisible(true);
		return this.event;
	}
	
	private class EventSelectionTypePanel extends JPanel{

		private static final long serialVersionUID = 19172;

		@SuppressWarnings({ "rawtypes", "unchecked" })
		EventSelectionTypePanel(boolean isInitialPanel){
			this.setBorder(new TitledBorder("Event Type"));
			this.setLayout(new GridLayout(1,1));
			JLabel label = new JLabel("Chose Event type ");
			this.add(label);
			this.cmbEventType = new JComboBox<EventType>();
			if (isInitialPanel){
				EventType[] etypeInit = new EventType[1];
				etypeInit[0] = EventType.PokemonObtained;
				this.cmbEventType.setModel(new DefaultComboBoxModel(etypeInit));
				this.cmbEventType.setEnabled(false);
				this.cmbEventType.setSelectedIndex(0);
			}
			else{
				this.cmbEventType.setModel(new DefaultComboBoxModel(EventType.getList().toArray()));
				this.cmbEventType.setEnabled(true);
				this.cmbEventType.setSelectedIndex(0);
				if (event != null){
					this.cmbEventType.setSelectedIndex(findEvent(event.getEventType()));
				}
				this.cmbEventType.addItemListener(new ItemListener(){
					@Override
					public void itemStateChanged(ItemEvent arg0) {
						eSettingsPanel.switchLayout(cmbEventType.getSelectedItem().toString());
					}
				});
			}
			this.add(this.cmbEventType);
		}
		
		private int findEvent(EventType etype){
			List<EventType> list = EventType.getList();
			for (int i = 0; i < list.size(); i++){
				if (list.get(i) == etype){
					return i;
				}
			}
			return 0;
		}
		
		public EventType getActiveEventType(){
			try{
				return (EventType) this.cmbEventType.getSelectedItem();
			}catch(Exception e){
				return EventType.SingleTrainerBattle;
			}
		}
		
		private JComboBox<EventType> cmbEventType;
	}
	
	private EventSelectionTypePanel eTypePanel;
	private EventSettingsPanel eSettingsPanel;
	private Controller c;
	private Event event;
	private JFrame parent;
}
