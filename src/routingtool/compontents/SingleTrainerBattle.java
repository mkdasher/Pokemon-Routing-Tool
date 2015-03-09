package routingtool.compontents;

public class SingleTrainerBattle extends TrainerBattle {
	
	/**
	 * Event that involves battling a Single trainer.
	 * @param trainer
	 * @param money
	 */
	public SingleTrainerBattle(Trainer trainer, int money) {
		super(money,EventType.SingleTrainerBattle);
		this.trainer = trainer;
	}

	private Trainer trainer;

	@Override
	public void updatePokemonListAfter() {
		
	}
}
