package routingtool;

import routingtool.gui.MainWindow;

public class Main {
	
	public static void main(String[] args) {
		Controller controller = new Controller(new ToolEngine());
		new MainWindow(controller);
	}
	
	//Auxiliary Main
	/*public static void main(String[] args) {
		//Pokemon pkm = new Pokemon(158, 5,
				//new StatPack(31), new StatPack(31), new Nature(0), new Ability(0));
		PokemonData pkm = new PokemonData(158);
		System.out.println(pkm.getName());
		System.out.println(pkm.type1.toString());
		System.out.println(pkm.type2.toString());
		System.out.println(pkm.expType.toString());
		
		Ability ab = new Ability(3);
		System.out.println(ab.toString());
		System.out.println(ab.getDescription());
	}*/
}
