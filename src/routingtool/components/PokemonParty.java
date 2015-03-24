package routingtool.components;


public class PokemonParty {
	
	public PokemonParty(){
		this.pokemonListBefore = new PokemonTeam();
		this.pokemonListAfter = new PokemonTeam();
	}
	public PokemonParty(PokemonTeam pokemonListBefore){
		this.pokemonListBefore = pokemonListBefore;
		this.pokemonListAfter = new PokemonTeam();
	}
	
	public PokemonTeam getListBefore(){
		return this.pokemonListBefore;
	}
	public PokemonTeam getListAfter(){
		return this.pokemonListAfter;
	}
	public void setListBefore(PokemonTeam p){
		this.pokemonListBefore = p;
	}
	public void setListAfter(PokemonTeam p){
		this.pokemonListAfter = p;
	}
	
	private PokemonTeam pokemonListBefore;
	private PokemonTeam pokemonListAfter;
}
