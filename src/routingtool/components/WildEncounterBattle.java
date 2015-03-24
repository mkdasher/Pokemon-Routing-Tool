package routingtool.components;

import routingtool.components.PokemonUsed;
import routingtool.pokemon.Pokemon;
import routingtool.util.Formula;

public class WildEncounterBattle extends Event{
	
	/**
	 * Event that involves battling a Wild Encounter (not catching, for that, see PokemonObtained.java)
	 */
	public WildEncounterBattle(Pokemon encounter, PokemonUsedList pokemonUsedList){
		super(EventType.WildEncounter);
		this.encounter = encounter;
		this.pokemonUsedList = pokemonUsedList;
	}
	
	@Override
	public void updatePokemonListAfter() {
		PokemonTeam pokemonTeam = this.getParty().getListBefore().getCopy();
		PokemonUsed pud = pokemonUsedList.get(0);
		int amount = pud.pokemonUsedAmount();
		for (int j = 0; j < PokemonTeam.MAX_PKM; j++){
			Pokemon pkmUsed = pokemonTeam.getPokemon(j);
			int experience = Formula.calculateExperience(pkmUsed, this.encounter, amount, false);
			pkmUsed.gainEVs(this.encounter);
			pkmUsed.gainExperience(experience);
		}
		this.getParty().setListAfter(pokemonTeam);	
	}

	private PokemonUsedList pokemonUsedList;
	private Pokemon encounter;
}
