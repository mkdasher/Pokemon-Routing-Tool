package routingtool.pokemon.data;

import java.util.ArrayList;
import java.util.List;

import routingtool.util.CSVFileReader;

public class PokemonItem extends Data{
	public PokemonItem(int n){
		super(n, findName(n));
		CSVFileReader fileReader = new CSVFileReader();
		String[] data = fileReader.getLine(n, "./res/database/items.csv");
		this.itemPocket = data[2];
	}
	
	/**
	 * Private constructor. For getCopy()
	 * @param n
	 * @param name
	 * @param pocket
	 */
	private PokemonItem(int n, String name, String pocket){
		super(n, name);
		this.itemPocket = pocket;
	}
	
	private static String findName(int n){
		CSVFileReader fileReader = new CSVFileReader();
		String[] data = fileReader.getLine(n, "./res/database/items.csv");
		return data[1];
	}

	/**
	 * Gets list of all items
	 * @return
	 */
	public static List<PokemonItem> getList(){
		List<PokemonItem> list = new ArrayList<PokemonItem>();
		for (int i = 0; i <= ITEM_N; i++){
			list.add(new PokemonItem(i));
		}
		return list;
	}

	public PokemonItem getCopy() {
		return new PokemonItem(n, this.getName(), this.itemPocket);
	}
	
	public String getItemPocket(){
		return this.itemPocket;
	}
	
	public static final int ITEM_N = 536;
	private String itemPocket;
}
