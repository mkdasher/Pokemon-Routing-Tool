package routingtool;

import java.util.ArrayList;
import java.util.List;

import routingtool.components.Event;
import routingtool.util.Observable;
import routingtool.observers.EventListContainerObserver;

public class EventListContainer extends Observable<EventListContainerObserver>{
	public EventListContainer(){
		this.eventList = new ArrayList<Event>();
	}
	
	public void updateList(int index) {
		if (index == 0){
			this.eventList.get(0).updatePokemonListAfter();
			index++;
		}
		
		for (int i = index; i < this.eventList.size(); i++){
			this.eventList.get(i).updatePokemonListBefore(this.eventList.get(i-1));
			this.eventList.get(i).updatePokemonListAfter();
		}
	}
	
	public void addEvent(Event e){
		this.eventList.add(e);
		this.updateList(this.eventList.size() - 1);
		this.onEventListChange();
	}
	
	public void deleteEvent(int n) {
		this.eventList.remove(n);
		this.updateList(n);
		this.onEventListChange();
	}
	
	public void editEvent(Event ev, int index) {
		this.eventList.set(index, ev);
		this.updateList(index);
		this.onEventListChange();
	}
	
	public void setEventList(List<Event> eList) {
		this.eventList = eList;
		this.updateList(0);
		this.onEventListChange();
	}
	
	public List<Event> getEventList(){
		return this.eventList;
	}
	
	public int size(){
		return this.eventList.size();
	}
	
	public Event getEvent(int n) {
		if (n < 0 || n >= this.eventList.size()){
			return null;
		}
		return this.eventList.get(n);
	}
	
	private void onEventListChange(){
		for (int i = 0; i < this.observers.size(); i++){
			this.observers.get(i).eventListChange(eventList);
		}
	}
	
	public void onEventGridSelectionChanged(int index) {
		//If no selection
		if (index == -1) return;

		for (int i = 0; i < this.observers.size(); i++){
			this.observers.get(i).eventSelectionChange(eventList.get(index));
		}
	}
	
	private List<Event> eventList;
}
