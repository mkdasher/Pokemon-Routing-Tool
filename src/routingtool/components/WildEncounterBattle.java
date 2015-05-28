package routingtool.components;

import routingtool.components.PokemonUsed;
import routingtool.pokemon.Pokemon;
import routingtool.util.Formula;

public class WildEncounterBattle extends Event{
	
	/**
	 * Event that involves battling a Wild Encounter (not catching, for that, see PokemonObtained.java)
	 */
	public WildEncounterBattle(Pokemon encounter, PokemonUsed pokemonUsed){
		super(EventType.WildEncounter);
		this.encounter = encounter;
		this.pokemonUsed = pokemonUsed;
	}
	
	@Override
	public void updateStateAfter() {
		PokemonTeam pokemonTeam = this.getStateBefore().getTeam().getCopy();
		int PokemonLevelsBefore[] = new int[6];
		for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
			PokemonLevelsBefore[i] = pokemonTeam.getPokemon(i).getLevel();
		}
		
		int amount = pokemonUsed.pokemonUsedAmount(pokemonTeam.size());
		for (int j = 0; j < PokemonTeam.MAX_PKM; j++){
			if (pokemonUsed.isUsed(j)){
				Pokemon pkm = pokemonTeam.getPokemon(j);
				int experience = Formula.calculateExperience(pkm, this.encounter, amount, false);
				pkm.gainEVs(this.encounter);
				pkm.gainExperience(experience);
			}
		}
		
		//If any Pokemon on your team has leveled up during the battle, check if it evolves.
		for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
			if (pokemonTeam.getPokemon(i).getLevel() > PokemonLevelsBefore[i]){
				pokemonTeam.getPokemon(i).checkEvolution();
			}
		}
		this.getStateAfter().setTeam(pokemonTeam);	
		this.getStateAfter().setMoney(this.getStateBefore().getMoney());
	}
	
	public Pokemon getWildPokemon() {
		return this.encounter;
	}
	
	public PokemonUsed getPokemonUsed(){
		return this.pokemonUsed;
	}
	
	@Override
	public String toString(){
		return this.encounter.getBaseData().getName() + " Lv. " + this.encounter.getLevel();
	}

	private PokemonUsed pokemonUsed;
	private Pokemon encounter;
}
