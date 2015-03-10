package routingtool.pokemon.data;

import java.util.List;

import routingtool.util.CSVFileReader;

public class PokemonItem extends Data{
	public PokemonItem(int n){
		super(n, findName(n));
		CSVFileReader fileReader = new CSVFileReader();
		String[] data = fileReader.getLine(n, "./res/database/items.csv");
		this.itemPocket = data[2];
	}
	
	private static String findName(int n){
		CSVFileReader fileReader = new CSVFileReader();
		String[] data = fileReader.getLine(n, "./res/database/items.csv");
		return data[1];
	}

	public static List<Data> getList(){
		return null;
	}

	public PokemonItem getCopy() {
		return new PokemonItem(n);
	}
	
	public String getItemPocket(){
		return this.itemPocket;
	}
	
	private String itemPocket;
}
