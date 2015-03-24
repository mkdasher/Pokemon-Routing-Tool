package routingtool;

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
	}
	
	//Auxiliary Main
	/*public static void main(String[] args) {
		//Pokemon pkm = new Pokemon(158, 5,
				//new StatPack(31), new StatPack(31), new Nature(0), new Ability(0));
		List<Move> ab = Move.getList();
		for (int i = 0; i <= Move.MOVE_N; i++){
			System.out.println(ab.get(i));
		}
	}*/
}
