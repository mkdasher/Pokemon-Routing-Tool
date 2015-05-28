package routingtool;

import java.util.List;

import routingtool.components.Event;
import routingtool.observers.EventListContainerObserver;
import routingtool.util.FileUtil;

public class ToolEngine {
	
	private EventListContainer elc;
	private GameVersion version;
	
	public ToolEngine(){
		this.elc = new EventListContainer();
		this.version = GameVersion.Generation4;
	}
	
	/**
	 * Get game Version
	 * @return
	 */
	public GameVersion getGameVersion(){
		return this.version;
	}
	
	/**
	 * Set game Version
	 * @param version
	 */
	public void setGameVersion(GameVersion version){
		if (this.version != version){
			this.version = version;
			this.updateToCurrentVersion();
		}
	}
	
	/**
	 * Updates settings to CurrentVersion
	 */
	public void updateToCurrentVersion(){
		//TODO
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
	 * Switch event index with event index-1
	 * @param index
	 */
	public void moveUp(int index) {
		this.elc.moveUp(index);
	}
	
	/**
	 * Switch event index with event index+1 
	 * @param index
	 */
	public void moveDown(int index) {
		this.elc.moveDown(index);
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
	public void loadFile(String filePath, Controller c) {
		List<Event> list = FileUtil.loadFile(filePath, c);
		if (list != null){
			this.elc.setEventList(list);
		}
	}
	public void saveFile(String filePath) {
		FileUtil.saveFile(filePath, this.elc.getEventList(), this.version);
	}
	public void quit() {
		System.exit(0);		
	}

	public void addEventListContainerObserver(EventListContainerObserver observer) {
		this.elc.addObserver(observer);
	}
	
}
