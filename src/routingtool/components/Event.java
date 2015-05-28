package routingtool.components;


public abstract class Event {
	
	public Event(EventType eventType){
		this.eventType = eventType;
		this.stateBefore = new State();
		this.stateAfter = new State();
	}
	
	/**
	 * returns EventType
	 * @return
	 */
	public EventType getEventType(){
		return this.eventType;
	}
	
	
	/**
	 * updates StateAfter by taking the Event's data.
	 * Abstract method, each subclass has it's own implementation.
	 */
	public abstract void updateStateAfter();
	
	/**
	 * updates StateBefore from previous Event on an Event list.
	 * @param previous
	 */
	public void updateStateBefore(Event previous){
		this.stateBefore.setTeam(previous.getStateAfter().getTeam().getCopy());
		this.stateBefore.setMoney(previous.getStateAfter().getMoney());
	}
	
	/**
	 * updates both States.
	 * @param previous
	 */
	public void updateStates(Event previous){
		this.updateStateBefore(previous);
		this.updateStateAfter();
	}
		
	/**
	 * Gets State before the Event
	 * @return
	 */
	public State getStateBefore(){
		return this.stateBefore;
	}
	
	/**
	 * Gets State after the Event
	 * @return
	 */
	public State getStateAfter(){
		return this.stateAfter;
	}

	private EventType eventType;
	private State stateBefore;
	private State stateAfter;
}
