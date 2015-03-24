package routingtool;

import java.io.ByteArrayOutputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;

import routingtool.components.Event;
import routingtool.observers.EventListContainerObserver;
import routingtool.util.FileUtil;

public class ToolEngine {
	
	private EventListContainer elc;
	
	public ToolEngine(){
		this.elc = new EventListContainer();
	}
	
	/**
	 * Calls eventSelectionChange
	 */
	public void onEventGridSelectionChanged(int index) {
		this.elc.onEventGridSelectionChanged(index);
	}
	
	/**
	 * Adds event
	 * @param e
	 */
	public void addEvent(Event e) {
		this.elc.addEvent(e);
	}
	
	/**
	 * Delete event
	 * @param n
	 */
	public void deleteEvent(int n) {
		this.elc.deleteEvent(n);
	}
	
	/**
	 * Gets event
	 * @param n
	 */
	public Event getEvent(int n) {
		return this.elc.getEvent(n);
	}
	
	/**
	 * Gets event list size
	 * @return
	 */
	public int getEventListSize() {
		return this.elc.size();
	}
	
	/**
	 * Replaces event at the specified position (index) with an specified event (ev)
	 * @param ev
	 * @param index
	 */
	public void editEvent(Event ev, int index) {
		this.elc.editEvent(ev,index);
	}
		
	/****************************************/
	public void loadFile(String filePath) {
		this.elc.setEventList(FileUtil.loadFile(filePath));
	}
	public void saveFile(String filePath) {
		FileUtil.saveFile(filePath, this.elc.getEventList());
	}
	public void quit() {
		System.exit(0);		
	}

	public void addEventListContainerObserver(EventListContainerObserver observer) {
		this.elc.addObserver(observer);
	}
}
