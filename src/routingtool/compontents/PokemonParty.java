package routingtool.compontents;

import java.util.List;
import routingtool.pokemon.Pokemon;

public class PokemonParty {
	
	public PokemonParty(){
		
	}
	public PokemonParty(List<Pokemon> pokemonList){
		this.pokemonListBefore = pokemonList;
	}
	private List<Pokemon> pokemonListBefore;
	private List<Pokemon> pokemonlistAfter;
}
