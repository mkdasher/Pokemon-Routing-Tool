package routingtool.components;

import java.util.ArrayList;
import java.util.List;

import routingtool.pokemon.Pokemon;

public class PokemonTeam {
	public PokemonTeam(){
		this.team = new ArrayList<Pokemon>();
	}
	
	public void addPokemon(Pokemon pkm){
		if (pkm.isEmpty()) return;
		if (this.team.size() >= MAX_PKM) return;
		this.team.add(pkm);
	}
	
	public void replacePokemon(Pokemon pkm, int i){
		if (i < 0) return;
        if (i >= this.team.size())
        {
            this.addPokemon(pkm);
        }
        else if (pkm.isEmpty()) this.team.remove(i);
        else this.team.set(i, pkm);
	}
	
	public void erasePokemon(int i){
		if (i < 0 || i >= this.team.size()) return;
		this.team.remove(i);
	}
	
	public int size(){
		return this.team.size();
	}
	
	public boolean isEmpty(){
		return this.team.size() == 0;
	}
	
	public Pokemon getPokemon(int i){
		if (i < this.team.size()) return this.team.get(i);
		return new Pokemon(); //empty Pokemon
	}
	
	public PokemonTeam getCopy(){
		PokemonTeam t = new PokemonTeam();
		for (int i = 0; i < this.team.size(); i++){
			Pokemon pkm = this.team.get(i).getCopy();
			t.addPokemon(pkm);
		}
		return t;
	}
	
	private List<Pokemon> team;
	public static final int MAX_PKM = 6;
}
