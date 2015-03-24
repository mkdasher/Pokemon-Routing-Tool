package routingtool.util;

import routingtool.pokemon.Pokemon;

public class Formula {
	
	/*return floor((max(1,(exp_yield * loser_level // 7) // num_poke))
                       * trainer_battle * orig_trainer * hold_item)*/
	public static int calculateExperience(Pokemon pkmUsed, Pokemon pkmDefeated, int usedPokemonAmount, boolean isTrainer){
		if (usedPokemonAmount == 0) return 0;
		
		int yield = pkmDefeated.getBaseData().exp;
		int level = pkmDefeated.getLevel();
		float outsider = 1F;
		float trainer = 1F;
		if (isTrainer) trainer = 1.5F;
		if (pkmUsed.isTradedPokemon()) outsider = 1.5F;
		
		int exp = ((yield * level) / 7) / usedPokemonAmount;
		if (exp < 1) exp = 1;
		exp = (int)(exp * trainer);
        exp = (int)(exp * outsider);
        
        return exp;
	}
}
