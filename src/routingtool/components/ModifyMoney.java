package routingtool.components;

public class ModifyMoney extends Event{

	/**
	 * Event that involves a money change (Buying / Selling stuff). Since this tool
	 * doesn't track items in your bag yet. Only money difference info is stored.
	 * @param moneyDifference
	 */
	public ModifyMoney(int moneyDifference, String description) {
		super(EventType.ModifyMoney);
		this.moneyDifference = moneyDifference;
		this.description = description;
	}

	@Override
	public void updateStateAfter() {
		this.getStateAfter().setTeam(this.getStateBefore().getTeam().getCopy());
		this.getStateAfter().setMoney(this.getStateBefore().getMoney() + moneyDifference);
	}

	public int getMoneyDifference(){
		return this.moneyDifference;
	}
	
	public String getDescription(){
		return this.description;
	}
	
	@Override
	public String toString(){
		if (moneyDifference > 0){
			return "Money received: " + moneyDifference;
		}
		else{
			return "Money spent: " + (-moneyDifference);
		}
	}
	
	private int moneyDifference;
	private String description;
}
