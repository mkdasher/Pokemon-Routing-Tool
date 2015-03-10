package routingtool.pokemon.data;

import java.util.List;

import routingtool.util.CSVFileReader;

public class Ability extends Data{
	public Ability(int n){
		super(n, findName(n));
		CSVFileReader fileReader = new CSVFileReader();
		String[] data = fileReader.getLine(n, "./res/database/abilities.csv");
		this.description = data[2];
	}
	
	private static String findName(int n){
		CSVFileReader fileReader = new CSVFileReader();
		String[] data = fileReader.getLine(n, "./res/database/abilities.csv");
		return data[1];
	}

	public static List<Data> getList(){
		return null;
	}

	public Ability getCopy() {
		return new Ability(n);
	}
	
	public String getDescription(){
		return this.description;
	}
	
	private String description;
}
