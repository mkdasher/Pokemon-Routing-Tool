package routingtool.components;

import java.util.ArrayList;
import java.util.List;

public class PokemonUsedList {
	
	public PokemonUsedList(){
		this.list = new ArrayList<PokemonUsed>();
	}
	
	public PokemonUsedList(List<PokemonUsed> list){
		this.list = list;
	}
	
	public void add(PokemonUsed pud){
		this.list.add(pud);
	}
	public void remove(int i){
		this.list.remove(i);
	}
	public int size(){
		return this.list.size();
	}
	public PokemonUsed get(int i){
		return this.list.get(i);
	}
	
	public int getIndex(int trainerID, int pokemonSlot){
		for (int i = 0; i < this.list.size(); i++){
			if (this.list.get(i).getPokemonSlot() == pokemonSlot && this.list.get(i).getTrainerID() == trainerID){
				return i;
			}
		}
		return -1;
	}
	
	private List<PokemonUsed> list;
}
