package routingtool.components;

public class PokemonUsed{
	public PokemonUsed(int pokemonSlot, int trainerID, boolean[] used){
		this.trainerID = trainerID;
		this.used = used;
		this.pokemonSlot = pokemonSlot;
	}
	
	public PokemonUsed(int pokemonSlot, int trainerID){
		this.trainerID = trainerID;
		this.used = new boolean[PokemonTeam.MAX_PKM];
		for (int i = 0; i < PokemonTeam.MAX_PKM; i++) this.used[i] = false;
		this.pokemonSlot = pokemonSlot;
	}
	
	public int getPokemonSlot(){
		return this.pokemonSlot;
	}
	
	public boolean isUsed(int i){
		return this.used[i];
	}
	
	public void setUsed(int i){
		this.used[i] = true;
	}
	
	public void setUnused(int i){
		this.used[i] = false;
	}
	
	public int pokemonUsedAmount(int teamSize){
		int amount = 0;
		for (int i = 0; i < teamSize; i++){
			if (this.used[i]) amount++;
		}
		return amount;
	}
	
	/**
	 * Returns trainerID (0: wild end, 1: 1st trainer / single trainer, 2: 2ndtrainer)
	 * @return
	 */
	public int getTrainerID(){
		return this.trainerID;
	}
	
	/**
	 * Slot of the pokemon that was defeated. 0 if wild encounter.
	 */
	private int pokemonSlot;
	
	/**
	 * Trainer ID:
	 * - 0: Wild Encounter
	 * - 1: Single Battle / Double Battle trainer 1
	 * - 2: Double Battle trainer 2
	 */
	private int trainerID;
	public static final int TID_WILDENC = 0;
	public static final int TID_TRAINER1 = 1;
	public static final int TID_TRAINER2 = 2;
	private boolean[] used;
}
