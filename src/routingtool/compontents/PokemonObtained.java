package routingtool.compontents;


import routingtool.pokemon.Pokemon;

public class PokemonObtained extends Event
{

	private Pokemon gift;
	
	/**
	 * Event that includes either catching a Wild Pokemon or receiving a Gift Pokemon.
	 * @param gift
	 */
	public PokemonObtained(Pokemon gift){
		super(EventType.PokemonObtained);
		this.gift = gift;
	}
	
	@Override
	public void updatePokemonListAfter() {
		PokemonTeam p = this.getParty().getListBefore().getCopy();
		p.addPokemon(gift);
		this.getParty().setListAfter(p);
	}

}
