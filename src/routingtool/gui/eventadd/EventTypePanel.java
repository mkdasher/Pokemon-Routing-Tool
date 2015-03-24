package routingtool.gui.eventadd;

import javax.swing.JPanel;

import routingtool.components.Event;
import routingtool.components.EventType;

@SuppressWarnings("serial")
public abstract class EventTypePanel extends JPanel {

	public abstract Event getEvent();
	
	/**
	 * Use this instead of getEvent().getEventType(), since getEvent() can return null.
	 * @return
	 */
	public abstract EventType getEventType();
	
	public abstract void updateComponents();
}
