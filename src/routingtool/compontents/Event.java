package routingtool.compontents;


public abstract class Event {
	

	public Event(EventType eventType){
		this.eventType = eventType;
	}
	
	
	/**
	 * updates PokemonParty.pokemonListAfter from the data obtained from the event.
	 * Abstract method, implemented by it's subclasses.
	 */
	public abstract void updatePokemonListAfter();
	
	/**
	 * sets Pokemon party
	 * @param p
	 */
	public void setParty(PokemonParty p){
        this.party = p;
    }
	
	/**
	 * gets Pokemon party
	 * @return
	 */
    public PokemonParty getParty(){
        return this.party;
    }
	private PokemonParty party;
	public EventType eventType;
}
