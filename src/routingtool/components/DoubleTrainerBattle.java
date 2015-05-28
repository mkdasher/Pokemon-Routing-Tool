package routingtool.components;

import routingtool.components.PokemonUsed;
import routingtool.pokemon.Pokemon;
import routingtool.util.Formula;

public class DoubleTrainerBattle extends TrainerBattle{
	
	/**
	 * Event that involves battling a Double trainer.
	 * @param trainer1
	 * @param trainer2
	 * @param money
	 */
	public DoubleTrainerBattle(Trainer trainer1, Trainer trainer2, int money, PokemonUsedList pokemonUsedList, PokemonTeam myTeam) {
		super(money,EventType.DoubleTrainerBattle, pokemonUsedList, myTeam);
		this.trainer1 = trainer1;
		this.trainer2 = trainer2;
	}
	
	public Trainer getTrainer1(){
		return this.trainer1;
	}
	public Trainer getTrainer2(){
		return this.trainer2;
	}
	
	@Override
	public void updateStateAfter() {
		PokemonTeam pokemonTeam = this.getStateBefore().getTeam().getCopy();
		int PokemonLevelsBefore[] = new int[6];
		for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
			PokemonLevelsBefore[i] = pokemonTeam.getPokemon(i).getLevel();
		}
		for (int i = 0; i < getPokemonUsedList().size(); i++){
			PokemonUsed pud = getPokemonUsedList().get(i);
			int amount = pud.pokemonUsedAmount(pokemonTeam.size());
			Trainer trainer = null;
			if (pud.getTrainerID() == 1) trainer = trainer1;
			else trainer = trainer2;
			Pokemon pkmDefeated = trainer.getPokemonTeam().getPokemon(pud.getPokemonSlot());
			for (int j = 0; j < PokemonTeam.MAX_PKM; j++){
				if (pud.isUsed(j)){
					Pokemon pkmUsed = pokemonTeam.getPokemon(j);
					int experience = Formula.calculateExperience(pkmUsed, pkmDefeated, amount, true);
					pkmUsed.gainEVs(pkmDefeated);
					pkmUsed.gainExperience(experience);
				}
			}
		}
		//If any Pokemon on your team has leveled up during the battle, check if it evolves.
		for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
			if (pokemonTeam.getPokemon(i).getLevel() > PokemonLevelsBefore[i]){
				pokemonTeam.getPokemon(i).checkEvolution();
			}
		}
		this.getStateAfter().setTeam(pokemonTeam);
		this.getStateAfter().setMoney(this.getStateBefore().getMoney() + this.getMoney());
	}
	
	@Override
	public String toString(){
		return trainer1.getName() + " / " + trainer2.getName();
	}
	
	private Trainer trainer1;
	private Trainer trainer2;
}
