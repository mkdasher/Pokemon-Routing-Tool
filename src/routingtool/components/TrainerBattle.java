package routingtool.components;

public abstract class TrainerBattle extends Event {
	
	/**
	 * Abstract class. Event that involves trainer Battling.
	 * Subclasses: SingleTrainerBattle, DoubleTrainerBattle
	 * Might implement TripleTrainerBattle in the future.
	 * @param money
	 * @param e
	 * @param pul
	 * @param myTeam
	 */
	public TrainerBattle(int money, EventType e, PokemonUsedList pul, PokemonTeam myTeam){
		super(e);
		this.money = money;
		this.pokemonUsedList = pul;
		this.myTeam = myTeam;
	}
	
	public int getMoney(){
		return this.money;	
	}
	
	public PokemonTeam getMyTeam(){
		return this.myTeam;
	}
	
	@Override
	public void updateStateBefore(Event previous){
		//First, edits the list by taking the previous event.
		super.updateStateBefore(previous);
		//Now, edits the moves of the team with myTeam.
		PokemonTeam origTeam = this.getStateBefore().getTeam();
		for (int i = 0; i < origTeam.size(); i++){
			origTeam.getPokemon(i).setMoves(
					myTeam.getPokemon(i).getMove(0),
					myTeam.getPokemon(i).getMove(1),
					myTeam.getPokemon(i).getMove(2),
					myTeam.getPokemon(i).getMove(3));
			origTeam.getPokemon(i).setHeldItem(myTeam.getPokemon(i).getHeldItem());
		}
	}
	
	public PokemonUsedList getPokemonUsedList(){
		return this.pokemonUsedList;
	}
	
	private int money;
	private PokemonUsedList pokemonUsedList;
	private PokemonTeam myTeam;
}
