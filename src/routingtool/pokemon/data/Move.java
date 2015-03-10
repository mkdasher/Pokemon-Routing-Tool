package routingtool.pokemon.data;

import java.util.ArrayList;
import java.util.List;

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
	
	/**
	 * Private constructor. For getCopy().
	 * @param n
	 * @param name
	 * @param moveType
	 * @param moveCategory
	 * @param pp
	 * @param power
	 * @param accuracy
	 */
	private Move(int n, String name, PokemonType moveType, MoveCategory moveCategory, int pp, int power, int accuracy){
		super(n, name);
		this.moveType = moveType;
		this.moveCategory = moveCategory;
		this.pp = pp;
		this.power = power;
		this.accuracy = accuracy;
	}

	
	public static String findName(int n){
		CSVFileReader fileReader = new CSVFileReader();
		String[] data = fileReader.getLine(n, "./res/database/moves.csv");
		return data[1];
	}
	
	/**
	 * Gets list of all moves
	 * @return
	 */
	public static List<Move> getList(){
		List<Move> list = new ArrayList<Move>();
		for (int i = 0; i <= MOVE_N; i++){
			list.add(new Move(i));
		}
		return list;
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
		return new Move(this.n, this.getName(), this.moveType.getCopy(), this.moveCategory, this.pp, this.power, this.accuracy);
	}
	
	public static final int MOVE_N = 467;
	
	private PokemonType moveType;
	private MoveCategory moveCategory;
	private int pp;
	private int power;
	private int accuracy;
}
