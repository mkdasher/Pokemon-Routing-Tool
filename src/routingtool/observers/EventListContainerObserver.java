package routingtool.observers;

import java.util.List;

import routingtool.compontents.Event;
import routingtool.pokemon.Pokemon;

public interface EventListContainerObserver {
	/**
	 * Notifies that the list has been changed
	 * @param itemList
	 */
	public void eventListChange(List<Event> itemList);
}
