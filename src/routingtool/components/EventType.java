package routingtool.components;

import java.util.ArrayList;
import java.util.List;

public enum EventType {
	SingleTrainerBattle, DoubleTrainerBattle, PokemonObtained, WildEncounter, ItemUsed, Trade, ModifyMoney, EmptyEvent;
	
	@Override
	public String toString(){
		if (this == PokemonObtained) return "Pokemon Obtained";
		else if (this == WildEncounter) return "Wild Encounter";
		else if (this == SingleTrainerBattle) return "Single Trainer Battle";
		else if (this == DoubleTrainerBattle) return "Double Trainer Battle";
		else if (this == ItemUsed) return "Item Used";
		else if (this == Trade) return "Trade";
		else if (this == ModifyMoney) return "Modify Money";
		else return "Empty Event";
	}
	
	
	public int index(){
		if (this == SingleTrainerBattle) return 0;
		else if (this == DoubleTrainerBattle) return 1;
		else if (this == PokemonObtained) return 2;
		else if (this == WildEncounter) return 3;
		else if (this == ItemUsed) return 4;
		else if (this == Trade) return 5;
		else if (this == ModifyMoney) return 6;
		else return 7; //Empty Event;
	}
	
	/**
	 * Gets list of all move categories
	 * @return
	 */
	public static List<EventType> getList(){
		List<EventType> list = new ArrayList<EventType>();
		list.add(SingleTrainerBattle);
		list.add(DoubleTrainerBattle);
		list.add(PokemonObtained);
		list.add(WildEncounter);
		list.add(ItemUsed);
		list.add(ModifyMoney);
		//list.add(Trade);
		return list;
	}
}
