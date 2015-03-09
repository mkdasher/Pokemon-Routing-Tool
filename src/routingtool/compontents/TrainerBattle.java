package routingtool.compontents;

public abstract class TrainerBattle extends Event {
	
	/**
	 * Abstract class. Event that involves trainer Battling.
	 * Subclasses: SingleTrainerBattle, DoubleTrainerBattle
	 * Might implement TripleTrainerBattle in the future.
	 * @param money
	 * @param e
	 */
	public TrainerBattle(int money, EventType e){
		super(e);
		this.money = money;
	}
	
	public int getMoney(){
		return money;	
	}
	
	private int money;
}
