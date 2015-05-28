package routingtool;

import routingtool.components.Event;
import routingtool.observers.EventListContainerObserver;

public class Controller {
	private ToolEngine e;
	public Controller(ToolEngine e) {
		this.e = e;
	}
	
	public GameVersion receiveGameVersion(){
		return this.e.getGameVersion();
	}
	
	public void setGameVersion(GameVersion version){
		this.e.setGameVersion(version);
	}
	
	public void requestLoadFile(String filePath) {
		this.e.loadFile(filePath, this);
	}

	public void requestSaveFile(String filePath) {
		this.e.saveFile(filePath);
	}
	
	public void addEvent(Event e){
		this.e.addEvent(e);
	}
	
	public void moveUp(int index) {
		this.e.moveUp(index);
	}
	public void moveDown(int index) {
		this.e.moveDown(index);
	}
	
	public void deleteEvent(int index) {
		this.e.deleteEvent(index);
	}
	
	public Event receiveEvent(int n) {
		return this.e.getEvent(n);
	}

	public int getEventListSize() {
		return this.e.getEventListSize();
	}

	public void editEvent(Event ev, int index) {
		this.e.editEvent(ev,index);
	}

	public void requestQuit() {
		this.e.quit();
	}
	
	public void onEventGridSelectionChanged(int index){
		this.e.onEventGridSelectionChanged(index);
	}
	
	public void addEventListContainerObserver(EventListContainerObserver observer) {
		this.e.addEventListContainerObserver(observer);
	}
}
