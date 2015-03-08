package routingtool.observers;

import java.util.List;

import routingtool.compontents.Event;

public interface EventListContainerObserver {
	/**
	 * Notifies that the list has been changed
	 * @param itemList
	 */
	public void eventListChanged(List<Event> itemList);
}
