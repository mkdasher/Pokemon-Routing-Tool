package routingtool.components;

import routingtool.components.PokemonUsed;
import routingtool.util.Formula;
import routingtool.pokemon.Pokemon;

public class SingleTrainerBattle extends TrainerBattle{
	
	/**
	 * Event that involves battling a Single trainer.
	 * @param trainer
	 * @param money
	 */
	public SingleTrainerBattle(Trainer trainer, int money, PokemonUsedList pokemonUsedList, boolean spinner) {
		super(money,EventType.SingleTrainerBattle, pokemonUsedList);
		this.trainer = trainer;
		this.spinner = spinner;
	}
	
	public Trainer getTrainer(){
		return this.trainer;
	}

	@Override
	public void updatePokemonListAfter() {
		PokemonTeam pokemonTeam = this.getParty().getListBefore().getCopy();
		int PokemonLevelsBefore[] = new int[6];
		for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
			PokemonLevelsBefore[i] = pokemonTeam.getPokemon(i).getLevel();
		}
		for (int i = 0; i < getPokemonUsedList().size(); i++){
			PokemonUsed pud = getPokemonUsedList().get(i);
			int amount = pud.pokemonUsedAmount();
			Pokemon pkmDefeated = this.trainer.getPokemonTeam().getPokemon(pud.getPokemonSlot());
			for (int j = 0; j < PokemonTeam.MAX_PKM; j++){
				Pokemon pkmUsed = pokemonTeam.getPokemon(j);
				int experience = Formula.calculateExperience(pkmUsed, pkmDefeated, amount, true);
				pkmUsed.gainEVs(pkmDefeated);
				pkmUsed.gainExperience(experience);
			}
		}
		//If any Pokemon on your team has leveled up during the battle, check if it evolves.
		for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
			if (pokemonTeam.getPokemon(i).getLevel() > PokemonLevelsBefore[i]){
				pokemonTeam.getPokemon(i).checkEvolution();
			}
		}
		this.getParty().setListAfter(pokemonTeam);	
	}
	
	@Override
	public String toString(){
		if (this.spinner) return "(Spinner) " + trainer.getName();
		return trainer.getName();
	}
	
	public boolean isSpinner(){
		return this.spinner;
	}
	
	private boolean spinner;
	private Trainer trainer;
}
