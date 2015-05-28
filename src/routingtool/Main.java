package routingtool;

import java.io.IOException;
import java.net.URISyntaxException;

import javax.swing.UIManager;

import routingtool.gui.MainWindow;
import routingtool.util.DataListUtil;

public class Main {
	
	public static void main(String[] args) {
		DataListUtil.init();
		try {
			UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
		} catch (Exception e) {}
		Controller controller = new Controller(new ToolEngine());
		new MainWindow(controller);
		/*String a[] = null;
		try {
			a = Test.getResourceListing(Main.class,"");
		} catch (URISyntaxException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		for (int i = 0; i < 2; i++)
		System.out.print(a[i]);*/
	}
}
