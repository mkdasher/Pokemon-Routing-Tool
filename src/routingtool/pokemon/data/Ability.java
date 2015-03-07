package routingtool.pokemon.data;

import java.util.List;

public class Ability extends Data{
	public Ability(int n){
		super(n, findName(n),"abilities.csv");
	}
	
	public Ability(int n, String name){
		super(n,name,"abilities.csv");
	}
	
	private static String findName(int n){
		return null;
	}
		
	@Override
	public String toString(){
		return "hola";
	}

	@Override
	public List<Data> getList(){
		return null;
	}
}
