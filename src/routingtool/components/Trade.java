package routingtool.components;

import routingtool.pokemon.Pokemon;

public class Trade extends Event{

	public Trade(Pokemon received, int index) {
		super(EventType.Trade);
		this.receivedPokemon = received;
		this.sentIndex = index;
	}

	@Override
	public void updatePokemonListAfter() {
		this.receivedPokemon.trade();
		PokemonTeam p = this.getParty().getListBefore().getCopy();
		p.replacePokemon(receivedPokemon, sentIndex);
		this.getParty().setListAfter(p);	
	}

	private Pokemon receivedPokemon;
	private int sentIndex;
}
