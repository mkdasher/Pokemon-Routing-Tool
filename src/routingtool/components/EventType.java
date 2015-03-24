package routingtool.components;

import java.util.ArrayList;
import java.util.List;

public enum EventType {
	PokemonObtained, WildEncounter, SingleTrainerBattle, DoubleTrainerBattle, Trade, EmptyEvent;
	
	@Override
	public String toString(){
		if (this == PokemonObtained) return "Pokemon Obtained";
		else if (this == WildEncounter) return "Wild Encounter";
		else if (this == SingleTrainerBattle) return "Single Trainer Battle";
		else if (this == DoubleTrainerBattle) return "Double Trainer Battle";
		else if (this == Trade) return "Trade";
		else return "Empty Event";
	}
	
	/**
	 * Gets list of all move categories
	 * @return
	 */
	public static List<EventType> getList(){
		List<EventType> list = new ArrayList<EventType>();
		list.add(SingleTrainerBattle);
		list.add(DoubleTrainerBattle);
		//list.add(WildEncounter);
		list.add(PokemonObtained);
		//list.add(Trade);
		return list;
	}
}
