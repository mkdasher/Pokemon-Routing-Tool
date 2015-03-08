package routingtool;

import java.util.List;

import routingtool.compontents.Event;
import routingtool.pokemon.Pokemon;
import routingtool.util.Observable;
import routingtool.observers.EventListContainerObserver;

public class EventListContainer extends Observable<EventListContainerObserver>{
	public EventListContainer(){
		
	}
	
	private List<Event> eventList;
	private List<Pokemon> initialPokemon;
}
