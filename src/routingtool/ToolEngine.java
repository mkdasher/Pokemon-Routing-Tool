package routingtool;

import java.util.List;

import routingtool.compontents.Event;
import routingtool.pokemon.Pokemon;

public class ToolEngine {
	public ToolEngine(){
		this.elc = new EventListContainer();
	}
	
	private EventListContainer elc;
	
	/**
	 * Updates eventList
	 */
	public void updateList(){
		this.updateList(0);
	}
	
	/**
	 * Updates eventList from a List index
	 * @param index
	 */
	public void updateList(int index){
		
	}
	
	/****************************************/
	public void loadFile(String filePath) {
		
	}
	public void saveFile(String filePath) {
		
	}
	public void quit() {
		System.exit(0);		
	}
}
