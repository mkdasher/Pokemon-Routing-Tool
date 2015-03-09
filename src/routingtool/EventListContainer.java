package routingtool;

import java.util.List;

import routingtool.compontents.Event;
import routingtool.pokemon.Pokemon;
import routingtool.util.Observable;
import routingtool.observers.EventListContainerObserver;

public class EventListContainer extends Observable<EventListContainerObserver>{
	public EventListContainer(){
		
	}
	
	public void updateList(int index) {
		
	}
	
	public void addEvent(Event e){
		this.eventList.add(e);
	}
	
	private void onEventListChange(){
		for (int i = 0; i < this.observers.size(); i++){
			this.observers.get(i).eventListChange(eventList);
		}
	}
	
	private List<Event> eventList;
}
