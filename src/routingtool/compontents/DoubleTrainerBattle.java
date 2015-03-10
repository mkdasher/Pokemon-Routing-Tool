package routingtool.compontents;

public class DoubleTrainerBattle extends TrainerBattle{
	
	/**
	 * Event that involves battling a Double trainer.
	 * @param trainer1
	 * @param trainer2
	 * @param money
	 */
	public DoubleTrainerBattle(Trainer trainer1, Trainer trainer2, int money) {
		super(money,EventType.DoubleTrainerBattle);
		this.trainer1 = trainer1;
		this.trainer2 = trainer2;
	}
	
	@Override
	public void updatePokemonListAfter() {
		
	}
	
	private Trainer trainer1;
	private Trainer trainer2;
	private PokemonUsed pokemonUsed1;
	private PokemonUsed pokemonUsed2;
}
