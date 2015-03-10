package routingtool.pokemon.data;

import java.util.ArrayList;
import java.util.List;

public class PokemonType extends Data{
	public PokemonType(String type){
		super(getTypeID(type),type);
	}
	
	public PokemonType(int n){
		super(n,toStr[n]);
	}
	
	public static int getTypeID(String type){
		for (int i = 0; i < toStr.length; i++){
			if (type.equals(toStr[i])){
				return i;
			}
		}
		return 18; //unknown
	}
	
	public PokemonType getCopy(){
		return new PokemonType(this.n);
	}
		
	private final static String[] toStr = {"Fighting","Flying","Poison","Ground",
		 "Rock","Bug","Ghost","Steel", "Fire","Water","Grass","Electric",
		 "Psychic","Ice","Dragon","Dark", "Normal", "None", "Unknown"};
	
	/**
	 * Gets list of all types
	 * @return
	 */
	public static List<PokemonType> getList(){
		List<PokemonType> list = new ArrayList<PokemonType>();
		for (int i = 0; i < POKEMONTYPE_N; i++){ // < instead of <= cause there's no "none" type at the beginning. List doesn't include Unknown.
			list.add(new PokemonType(i));
		}
		return list;
	}
	
	public static final int POKEMONTYPE_N = 18;
}
