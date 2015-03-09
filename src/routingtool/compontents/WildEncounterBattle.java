package routingtool.compontents;

public class WildEncounterBattle extends Event{
	
	/**
	 * Event that involves battling a Wild Encounter (not catching, for that, see PokemonObtained.java)
	 */
	public WildEncounterBattle(){
		super(EventType.WildEncounter);
		this.eventType = EventType.WildEncounter;
	}
	
	@Override
	public void updatePokemonListAfter() {
		// TODO Auto-generated method stub
		
	}

}
