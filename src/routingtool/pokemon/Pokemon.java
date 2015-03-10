package routingtool.pokemon;

import routingtool.pokemon.data.Ability;
import routingtool.pokemon.data.Move;
import routingtool.pokemon.data.Nature;
import routingtool.pokemon.data.PokemonData;
import routingtool.pokemon.data.PokemonItem;
import routingtool.pokemon.data.StatPack;

public class Pokemon {
	
	/**
	 * Constructor. Level and Stats are calculated from IV, EV, level.
	 * @param n
	 * @param level
	 * @param IV
	 * @param EV
	 * @param nature
	 * @param ability
	 */
	public Pokemon(int n, int level, StatPack IV, StatPack EV, Nature nature, Ability ability){
		this.n = n;
		this.baseData = new PokemonData(n);
		this.level = level;
		this.calculateExperience();
		this.IV = IV;
		this.EV = EV;
		this.nature = nature;
		this.ability = ability;
		this.calculateStats();
	}
	
	/**
	 * Private constructor for getCopy(). (Level and Stats are copied)
	 * @param n
	 * @param level
	 * @param exp
	 * @param stat
	 * @param IV
	 * @param EV
	 * @param nature
	 * @param ability
	 */
	private Pokemon(int n, int level, int exp, StatPack stat, StatPack IV, StatPack EV, Nature nature, Ability ability, PokemonItem heldItem, boolean pokerus){
		this.n = n;
		this.baseData = new PokemonData(n);
		this.level = level;
		this.experience = exp;
		this.IV = IV;
		this.EV = EV;
		this.nature = nature;
		this.ability = ability;
		this.stat = stat;
		this.heldItem = heldItem;
		this.pokerus = pokerus;
	}
	
	/**
	 * Sets current experience from current level.
	 */
	public void calculateExperience(){
		this.experience = this.baseData.expType.experience(this.level - 1);
	}
	
	/**
	 * Sets current stats from current level / EV / IV.
	 */
	public void calculateStats(){
		this.stat = new StatPack();
        for (short i = 0; i < StatPack.STAT_N; i++)
        {
            this.stat.setStat(i, calculateStat(i));
        }
	}
	 public short calculateStat(int i)
     {
         int aux;
         if (i == 0)
         {
             aux = ((this.IV.hp + (2 * this.getBaseData().baseStat.hp) + 100) * this.level) / 100 + 10;
             return (short)aux;
         }
         else
         {
             double aux_d = (double)(((this.IV.getStat(i) + (2 * this.getBaseData().baseStat.getStat(i))) * this.level) / 100 + 5) * this.nature.getNatureBoost(i);
             aux = (int)aux_d;
             if (Math.abs((double)aux - aux_d) > 0.9999)
                 aux++;
             return (short)aux;
         }
     }
	 
	 /**
	  * Sets Moves for the Pokemon
	  * @param a
	  * @param b
	  * @param c
	  * @param d
	  */
	 public void setMoves(Move a, Move b, Move c, Move d)
     {
         this.move = new Move[4];
         move[0] = a;
         move[1] = b;
         move[2] = c;
         move[3] = d;
     }
	 
	 /**
	  * Gets move i of the pokemon
	  * @param i
	  * @return
	  */
     public Move getMove(int i)
     {
         return this.move[i];
     }
	 
	 /**
	  * Get Pokemon Data from a Pokemon
	  * @return
	  */
	 public PokemonData getBaseData(){
		 return this.baseData;
	 }

	 /**
	  * Get a copy of a Pokemon
	  * @return
	  */
	public Pokemon getCopy() {
		Pokemon pkm = new Pokemon(
				this.n,
				this.level, 
				this.experience,
				this.stat.getCopy(),
				this.IV.getCopy(),
				this.EV.getCopy(),
				this.nature.getCopy(),
				this.ability.getCopy(),
				this.heldItem.getCopy(),
				this.pokerus
				);
		pkm.setMoves(
				this.move[0].getCopy(),
				this.move[1].getCopy(),
				this.move[2].getCopy(),
				this.move[3].getCopy()
			);
		return pkm;
	}
	
	@Override
	public String toString(){
		return this.baseData.getName();
	}
	
	public StatPack stat;
	public StatPack IV;
	public StatPack EV;
	public PokemonItem heldItem;
	public int experience;
	public int level;
	public Nature nature;
	public Ability ability;
	public boolean pokerus;
	
	private Move[] move;
	private PokemonData baseData;
	private int n;
}
