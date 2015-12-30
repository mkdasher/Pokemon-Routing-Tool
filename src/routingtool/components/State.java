package routingtool.components;

public class State {
	
	/**
	 * Default constructor
	 */
	public State(){
		this.team = new PokemonTeam();
		this.money = 0;
	}
	
	/**
	 * Creates a state by adding a PokemonTeam and money.
	 * @param team
	 * @param money
	 */
	public State(PokemonTeam team, int money){
		this.team = team;
		this.money = money;
	}
	
	/**
	 * Gets Pokemon Team from the state.
	 * @return
	 */
	public PokemonTeam getTeam(){
		return this.team;
	}
	
	/**
	 * Set Pokemon Team for this state.
	 * @param team
	 */
	public void setTeam(PokemonTeam team){
		this.team = team;
	}
	
	public int getMoney(){
		return this.money;
	}
	
	public void setMoney(int money){
		this.money = money;
	}
	
	private PokemonTeam team;
	private int money;
}
