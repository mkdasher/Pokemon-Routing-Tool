package routingtool;

import routingtool.compontents.PokemonParty;
import routingtool.compontents.Battle;

public class Event {
	
	/**
	 * Default Constructor
	 */
	public Event(){
		this.party = null;
		this.battle = null;
	}
	
	/**
	 * Constructor
	 * @param party
	 * @param battle
	 */
	public Event(PokemonParty party, Battle battle){
		this.party = party;
		this.battle = battle;
	}
	
	public void setParty(PokemonParty p){
        this.party = p;
    }
    public void setBattle(Battle b)
    {
        this.battle = b;
    }
    public PokemonParty getParty(){
        return this.party;
    }
    public Battle getBattle(){
        return this.battle;
    }
    
	private PokemonParty party;
	private Battle battle;
}
