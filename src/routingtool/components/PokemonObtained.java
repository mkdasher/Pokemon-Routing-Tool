package routingtool.components;


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
	
	public Pokemon getGift(){
		return this.gift;
	}
	
	@Override
	public void updateStateAfter() {
		PokemonTeam p = this.getStateBefore().getTeam().getCopy();
		p.addPokemon(gift);
		this.getStateAfter().setTeam(p);
		this.getStateAfter().setMoney(this.getStateBefore().getMoney());
	}
	
	@Override
	public String toString(){
		return this.gift.getBaseData().getName() + " Lv. " + this.gift.getLevel();
	}

}
