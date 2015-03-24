package routingtool.observers;

import java.util.List;

import routingtool.components.Event;

public interface EventListContainerObserver {
	
	/**
	 * Notifies that the list has been changed
	 * @param itemList
	 */
	public void eventListChange(List<Event> itemList);
	
	/**
	 * Notifies that a new event has been selected in the Event Grid, and gets the current event.
	 */
	public void eventSelectionChange(Event current);
}
