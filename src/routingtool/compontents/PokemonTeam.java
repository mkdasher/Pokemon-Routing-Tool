package routingtool.compontents;

import java.util.List;

import routingtool.pokemon.Pokemon;

public class PokemonTeam {
	public PokemonTeam(){
	}
	
	public void addPokemon(Pokemon pkm){
		if (this.team.size() >= MAX_PKM) return;
		this.team.add(pkm);
	}
	
	public void replacePokemon(Pokemon pkm, int i){
		if (i < 0 || i >= MAX_PKM) return;
        if (i >= this.team.size())
        {
            this.addPokemon(pkm);
        }
        else this.team.set(i, pkm);
	}
	
	public void erasePokemon(int i){
		if (i < 0 || i >= this.team.size()) return;
		this.team.remove(i);
	}
	
	public int getPokemonCount(){
		return this.team.size();
	}
	
	public Pokemon getPokemon(int i){
		return this.team.get(i);
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
	private final int MAX_PKM = 6;
}
