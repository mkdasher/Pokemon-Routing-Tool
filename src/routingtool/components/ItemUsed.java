package routingtool.components;

import java.util.List;

import routingtool.pokemon.Pokemon;
import routingtool.pokemon.data.PokemonItem;
import routingtool.pokemon.data.StatPack;

public class ItemUsed extends Event{

	public ItemUsed(List<ItemUsedData> itemList) {
		super(EventType.ItemUsed);
		this.itemList = itemList;
	}

	@Override
	public void updateStateAfter() {
		this.getStateAfter().setTeam(this.getStateBefore().getTeam().getCopy());
		this.getStateAfter().setMoney(this.getStateBefore().getMoney());
		for (int i = 0; i < itemList.size(); i++){
			PokemonItem item = itemList.get(i).item;
			Pokemon p;
			switch (item.getID()){
			case PokemonItem.RARE_CANDY:
				p = this.getStateAfter().getTeam().getPokemon(itemList.get(i).pokemonIndex).getCopy();
				p.levelUp();
				p.checkEvolution();
				this.getStateAfter().getTeam().replacePokemon(p, itemList.get(i).pokemonIndex);
				break;
			case PokemonItem.HP_UP:
				p = this.getStateAfter().getTeam().getPokemon(itemList.get(i).pokemonIndex).getCopy();
				p.useVitamin(StatPack.HP);
				p.checkEvolution();
				this.getStateAfter().getTeam().replacePokemon(p, itemList.get(i).pokemonIndex);
				break;
			case PokemonItem.PROTEIN:
				p = this.getStateAfter().getTeam().getPokemon(itemList.get(i).pokemonIndex).getCopy();
				p.useVitamin(StatPack.ATK);
				p.checkEvolution();
				this.getStateAfter().getTeam().replacePokemon(p, itemList.get(i).pokemonIndex);
				break;
			case PokemonItem.IRON:
				p = this.getStateAfter().getTeam().getPokemon(itemList.get(i).pokemonIndex).getCopy();
				p.useVitamin(StatPack.DEF);
				p.checkEvolution();
				this.getStateAfter().getTeam().replacePokemon(p, itemList.get(i).pokemonIndex);
				break;
			case PokemonItem.CARBOS:
				p = this.getStateAfter().getTeam().getPokemon(itemList.get(i).pokemonIndex).getCopy();
				p.useVitamin(StatPack.SPE);
				p.checkEvolution();
				this.getStateAfter().getTeam().replacePokemon(p, itemList.get(i).pokemonIndex);
				break;
			case PokemonItem.CALCIUM:
				p = this.getStateAfter().getTeam().getPokemon(itemList.get(i).pokemonIndex).getCopy();
				p.useVitamin(StatPack.SPA);
				p.checkEvolution();
				this.getStateAfter().getTeam().replacePokemon(p, itemList.get(i).pokemonIndex);
				break;
			case PokemonItem.ZINC:
				p = this.getStateAfter().getTeam().getPokemon(itemList.get(i).pokemonIndex).getCopy();
				p.useVitamin(StatPack.SPD);
				p.checkEvolution();
				this.getStateAfter().getTeam().replacePokemon(p, itemList.get(i).pokemonIndex);
				break;
			}
		}
	}
	
	@Override
	public String toString(){
		ItemUsedData iud = itemList.get(0);
		if (itemList.size() == 1){
			return (iud.item.getName() + " used on " + getStateBefore().getTeam().getPokemon(iud.pokemonIndex).getBaseData().getName());
		}
		else{
			int itemID = itemList.get(0).item.getID();
			int index = itemList.get(0).pokemonIndex;
			for (int i = 0; i < itemList.size(); i++){
				if ((itemID != itemList.get(i).item.getID()) || (index != itemList.get(i).pokemonIndex)){
					return (itemList.size() + " items used.");
				}
			}
			return (itemList.size() + " " + iud.item.getName() + " used on " + getStateBefore().getTeam().getPokemon(iud.pokemonIndex).getBaseData().getName());
		}
	}
	
	public List<ItemUsedData> getItemUsedList(){
		return this.itemList;
	}
	
	private List<ItemUsedData> itemList;
}

