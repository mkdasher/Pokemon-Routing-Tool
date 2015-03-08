package routingtool;

public class Controller {
	private ToolEngine e;
	public Controller(ToolEngine e) {
		this.e = e;
	}
	
	public void requestLoadFile(String filePath) {
		e.loadFile(filePath);
	}


	public void requestSaveFile(String filePath) {
		e.saveFile(filePath);
	}

	public void requestQuit() {
		e.quit();
	}
}
