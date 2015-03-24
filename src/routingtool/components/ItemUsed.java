package routingtool.components;

import java.util.List;

import routingtool.pokemon.Pokemon;
import routingtool.pokemon.data.PokemonItem;

public class ItemUsed extends Event{

	public ItemUsed(EventType eventType,List<ItemUsedData> itemList) {
		super(eventType);
		this.itemList = itemList;
	}

	@Override
	public void updatePokemonListAfter() {
		for (int i = 0; i < itemList.size(); i++){
			PokemonItem item = itemList.get(i).item;
			switch (item.getID()){
			case 50: //Rare Candy
				Pokemon p = this.getParty().getListBefore().getPokemon(itemList.get(i).pokemon).getCopy();
				p.levelUp();
				this.getParty().getListAfter().replacePokemon(p, itemList.get(i).pokemon);
				break;
			}
		}
	}
	private List<ItemUsedData> itemList;
	
	public class ItemUsedData{
		public ItemUsedData(PokemonItem item, int pokemon){
			this.item = item;
			this.pokemon = pokemon;
		}
		
		public PokemonItem item;
		public int pokemon;
	}
}

