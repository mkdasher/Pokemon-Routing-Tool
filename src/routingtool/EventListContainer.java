package routingtool;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import routingtool.components.Event;
import routingtool.components.EventType;
import routingtool.components.SingleTrainerBattle;
import routingtool.util.Observable;
import routingtool.observers.EventListContainerObserver;

public class EventListContainer extends Observable<EventListContainerObserver>{
	public EventListContainer(){
		this.eventList = new ArrayList<Event>();
	}
	
	public void updateList(int index){
		if (index == 0){
			this.eventList.get(0).getStateBefore().setMoney(INITIAL_MONEY);
			this.eventList.get(0).updateStateAfter();
			index++;
		}
		
		//Last = previous Event from index that is not a Spinner Single Trainer Battle.
		int last = index;
		boolean lastFound = false;
		while(last >= 0 && !lastFound){
			last--;
			if (this.eventList.get(last).getEventType() != EventType.SingleTrainerBattle){
				lastFound = true;
			}else if (!((SingleTrainerBattle) this.eventList.get(last)).isSpinner()){
				lastFound = true;
			}
		}
		
		for (int i = index; i < this.eventList.size(); i++){
			this.eventList.get(i).updateStateBefore(this.eventList.get(last));
			this.eventList.get(i).updateStateAfter();
			if (this.eventList.get(i).getEventType() != EventType.SingleTrainerBattle){
				last = i;
			}else if (!((SingleTrainerBattle) this.eventList.get(i)).isSpinner()){
				last = i;
			}
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
	
	public void moveUp(int index) {
		if (index <= 0) return;
		Collections.swap(eventList, index, index - 1);
		this.updateList(index - 1);
		this.onEventListChange();
	}
	public void moveDown(int index) {
		if (index >= eventList.size()) return;
		Collections.swap(eventList, index, index + 1);
		this.updateList(index);
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
	private static final int INITIAL_MONEY = 3000;
}
