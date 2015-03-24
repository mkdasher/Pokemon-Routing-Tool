package routingtool.components;

public abstract class TrainerBattle extends Event {
	
	/**
	 * Abstract class. Event that involves trainer Battling.
	 * Subclasses: SingleTrainerBattle, DoubleTrainerBattle
	 * Might implement TripleTrainerBattle in the future.
	 * @param money
	 * @param e
	 */
	public TrainerBattle(int money, EventType e, PokemonUsedList pul){
		super(e);
		this.money = money;
		this.pokemonUsedList = pul;
	}
	
	public int getMoney(){
		return money;	
	}
	
	public PokemonUsedList getPokemonUsedList(){
		return this.pokemonUsedList;
	}
	private int money;
	private PokemonUsedList pokemonUsedList;
}
