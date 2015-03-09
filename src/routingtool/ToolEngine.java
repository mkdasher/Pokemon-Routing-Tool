package routingtool;

import java.util.List;

import routingtool.compontents.Event;
import routingtool.observers.EventListContainerObserver;
import routingtool.pokemon.Pokemon;

public class ToolEngine {
	
	private EventListContainer elc;
	
	public ToolEngine(){
		this.elc = new EventListContainer();
	}
		
	/**
	 * Updates eventList
	 */
	public void updateList(){
		this.elc.updateList(0);
	}
	
	/**
	 * Updates eventList from a List index
	 * @param index
	 */
	public void updateList(int index){
		this.elc.updateList(index);
	}
	
	/****************************************/
	public void loadFile(String filePath) {
		//TODO
	}
	public void saveFile(String filePath) {
		//TODO
	}
	public void quit() {
		System.exit(0);		
	}

	public void addEventListContainerObserver(EventListContainerObserver i) {
		this.elc.addObserver(i);
	}
}
