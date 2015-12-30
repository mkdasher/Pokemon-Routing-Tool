package routingtool.components;

import routingtool.pokemon.data.PokemonItem;

/**
 * Auxiliary class for ItemUsed
 * @author MKDasher
 *
 */
public class ItemUsedData {
	public ItemUsedData(PokemonItem item, int pokemonIndex){
		this.item = item;
		this.pokemonIndex = pokemonIndex;
	}
	public PokemonItem item;
	public int pokemonIndex;
}
