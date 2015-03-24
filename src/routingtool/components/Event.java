package routingtool.components;


public abstract class Event {
	
	public Event(EventType eventType){
		this.eventType = eventType;
		this.party = new PokemonParty();
	}
	
	/**
	 * returns eventType
	 * @return
	 */
	public EventType getEventType(){
		return this.eventType;
	}
	
	
	/**
	 * updates PokemonParty.pokemonListAfter from the data obtained from the event.
	 * Abstract method, implemented by it's subclasses.
	 */
	public abstract void updatePokemonListAfter();
	
	/**
	 * updates PokemonParty.pokemonListBefore from previous Event on an Event list.
	 * @param previous
	 */
	public void updatePokemonListBefore(Event previous){
		this.party.setListBefore(previous.getParty().getListAfter().getCopy());
	}
	
	/**
	 * updates both lists of Pokemon party.
	 * @param previous
	 */
	public void updatePokemonLists(Event previous){
		this.updatePokemonListBefore(previous);
		this.updatePokemonListAfter();
	}
	
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
	private EventType eventType;
}
