package routingtool;

import routingtool.observers.EventListContainerObserver;

public class Controller {
	private ToolEngine e;
	public Controller(ToolEngine e) {
		this.e = e;
	}
	
	public void requestLoadFile(String filePath) {
		this.e.loadFile(filePath);
	}

	public void requestSaveFile(String filePath) {
		this.e.saveFile(filePath);
	}

	public void requestQuit() {
		this.e.quit();
	}
	
	public void addEventListContainerObserver(EventListContainerObserver i) {
		this.e.addEventListContainerObserver(i);
	}
}
