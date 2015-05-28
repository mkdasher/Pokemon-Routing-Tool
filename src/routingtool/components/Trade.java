package routingtool.components;

import routingtool.pokemon.Pokemon;

public class Trade extends Event{

	public Trade(Pokemon received, int index) {
		super(EventType.Trade);
		this.receivedPokemon = received;
		this.sentIndex = index;
	}

	@Override
	public void updateStateAfter() {
		this.receivedPokemon.trade();
		PokemonTeam p = this.getStateBefore().getTeam().getCopy();
		p.replacePokemon(receivedPokemon, sentIndex);
		this.getStateAfter().setTeam(p);
		
		this.getStateAfter().setMoney(this.getStateBefore().getMoney());
	}
	
	public int getSentIndex(){
		return this.sentIndex;
	}
	
	public Pokemon getReceivedPokemon(){
		return receivedPokemon;
	}

	private Pokemon receivedPokemon;
	private int sentIndex;
}
