package routingtool.pokemon.data;

import routingtool.util.CSVFileReader;

public class Move extends Data {
	public Move(int n){
		 super(n, findName(n));
		 CSVFileReader fileReader = new CSVFileReader();
		 String[] data = fileReader.getLine(n, "./res/database/moves.csv");
		 this.moveType = new PokemonType(data[2]);
		 this.moveCategory = MoveCategory.fromString(data[3]);
		 this.pp = Integer.parseInt(data[4]);
		 this.power = Integer.parseInt(data[5]);
		 this.accuracy = Integer.parseInt(data[6]);
	}

	
	public static String findName(int n){
		CSVFileReader fileReader = new CSVFileReader();
		String[] data = fileReader.getLine(n, "./res/database/moves.csv");
		return data[1];
	}
	
	public int getPower(){
		return this.power;
	}
	
	public int getPP(){
		return this.pp;
	}
	
	public int getAccuracy(){
		return this.accuracy;
	}
	
	public MoveCategory getMoveCategory(){
		return this.moveCategory;
	}
	
	public PokemonType getMoveType(){
		return this.moveType;
	}
	
	public Move getCopy(){
		return new Move(n);
	}

	private PokemonType moveType;
	private MoveCategory moveCategory;
	private int pp;
	private int power;
	private int accuracy;
}
