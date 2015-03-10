package routingtool.pokemon.data;

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
		
	private final static String[] toStr = {"Fighting","Flying","Poison","Ground",
		 "Rock","Bug","Ghost","Steel", "Fire","Water","Grass","Electric",
		 "Psychic","Ice","Dragon","Dark", "Normal", "None", "Unknown"};
	
	public static List<Data> getList() {
		return null;
	}
}
