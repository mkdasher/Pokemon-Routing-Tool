package routingtool;

import routingtool.gui.MainWindow;

public class Main {
	
	public static void main(String[] args) {
		ToolEngine toolEngine = new ToolEngine();
		Controller controller = new Controller(toolEngine);
		new MainWindow(controller);
	}
	
}
