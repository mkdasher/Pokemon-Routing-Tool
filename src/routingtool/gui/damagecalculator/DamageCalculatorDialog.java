package routingtool.gui.damagecalculator;

import java.awt.BorderLayout;

import javax.swing.JDialog;
import javax.swing.JFrame;

import routingtool.components.DoubleTrainerBattle;
import routingtool.components.Event;
import routingtool.components.PokemonTeam;
import routingtool.components.PokemonUsed;
import routingtool.components.SingleTrainerBattle;
import routingtool.components.State;
import routingtool.components.Trainer;
import routingtool.gui.damagecalculator.DamageCalculatorBattlePanel.PokemonUsedType;
import routingtool.pokemon.Pokemon;
import routingtool.pokemon.data.Move;
import routingtool.util.Formula;

public class DamageCalculatorDialog extends JDialog{
	public DamageCalculatorDialog(JFrame parent, Event e){
		super(parent, "Damage Calculator");
		switch (e.getEventType()){
		case SingleTrainerBattle:
			this.stb = (SingleTrainerBattle) e;
			break;
		case DoubleTrainerBattle:
			break;
		case WildEncounter:
			break;
		}
		myTeamPokeAmount = stb.getStateBefore().getTeam().size();
		foeTeamPokeAmount = stb.getTrainer().getPokemonTeam().size();
		this.setModal(true);
		this.setParams();
		this.initialize();
		this.pack();
		this.setLocationByPlatform(true);
		this.setLocation(parent.getX() + 10, parent.getY() + 30);
		this.setVisible(true);
	}
	
	private void setParams(){
		this.setResizable(false);
		this.setLayout(new BorderLayout());
		this.dcbp = new DamageCalculatorBattlePanel(this);
		this.add(dcbp, BorderLayout.WEST);
		this.dcrp = new DamageCalculatorResultPanel(this);
		this.add(dcrp, BorderLayout.CENTER);
	}
	
	private void initialize(){
		this.createStates();
		this.setPUIndex(0);
	}
	
	public void createStates(){
		this.states = new State[this.stb.getPokemonUsedList().size()];
		this.states[0] = this.stb.getStateBefore();
		for (int i = 1; i < this.stb.getPokemonUsedList().size(); i++){
			this.states[i] = new State();
			states[i].setMoney(states[i-1].getMoney());			
			PokemonTeam pokemonTeam = states[i-1].getTeam().getCopy();
			PokemonUsed pud = this.stb.getPokemonUsedList().get(i-1);
			int amount = pud.pokemonUsedAmount(pokemonTeam.size());
			Pokemon pkmDefeated = stb.getTrainer().getPokemonTeam().getPokemon(pud.getPokemonSlot());
			for (int j = 0; j < PokemonTeam.MAX_PKM; j++){
				if (pud.isUsed(j)){
					Pokemon pkmUsed = pokemonTeam.getPokemon(j);
					int experience = Formula.calculateExperience(pkmUsed, pkmDefeated, amount, true);
					pkmUsed.gainEVs(pkmDefeated);
					pkmUsed.gainExperience(experience);
				}
			}
			states[i].setTeam(pokemonTeam);
		}
	}
	
	public void changeFoePokemon(int n){
		setPUIndex(stb.getPokemonUsedList().getIndex(PokemonUsed.TID_TRAINER1, n));
	}
	public void changeMyPokemon(int n){
		if (!isOwnPokemon) switchPokemonTeam();
		setOwnPokemon(states[currentState].getTeam().getPokemon(n));
	}
	
	public void setPUIndex(int n){
		this.isOwnPokemon = true;
		this.currentState = n;
		this.isOwnPokemon = true;
		this.pokeUsedIndex = n;
		PokemonUsed pu = stb.getPokemonUsedList().get(n);
		myPokemon = states[n].getTeam().getPokemon(0);
		for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
			if (pu.isUsed(i)){
				myPokemon = states[n].getTeam().getPokemon(i);
				break;
			}
		}
		foePokemon = stb.getTrainer().getPokemonTeam().getPokemon(pu.getPokemonSlot());
		setOwnPokemon(myPokemon);
		setFoePokemon(foePokemon);
		//Update Own Pokeball Icons
		for (int i = 0; i < myTeamPokeAmount; i++){
			if (pu.isUsed(i)) dcbp.setMyUsed(PokemonUsedType.Used,i);
			else dcbp.setMyUsed(PokemonUsedType.Unused,i);
		}
		//Update Foe Pokeball icons
		for (int i = 0; i < foeTeamPokeAmount; i++){
			dcbp.setFoeUsed(PokemonUsedType.Used,i);
		}
		for (int i = 0; i < n; i++){
			dcbp.setFoeUsed(PokemonUsedType.Unused, stb.getPokemonUsedList().get(i).getPokemonSlot());
		}
		dcbp.setBattleMessage("What will " + myPokemon.getBaseData().getName().toUpperCase() + " do?");
	}
	
	public void setOwnPokemon(Pokemon pokemon){
		this.myPokemon = pokemon;
		for (int i = 0; i < Pokemon.MOVE_AMOUNT; i++){
			Move m = myPokemon.getMove(i);
			this.dcbp.setMove(m, i);
		}
		if (pokemon.getLevel() == Pokemon.MAX_LEVEL){
			this.dcbp.setExpBarSize(0);
		}
		else{
			int curExp = pokemon.getExperience();
			int thisLevelExp = pokemon.getBaseData().expType.experience(pokemon.getLevel() - 1);
			int nextLevelExp = pokemon.getBaseData().expType.experience(pokemon.getLevel());
			int aux1 = nextLevelExp - thisLevelExp;
			int aux2 = curExp - thisLevelExp;
			this.dcbp.setExpBarSize(aux2 * DamageCalculatorBattlePanel.MAX_BAR_SIZE / aux1);
		}
		this.dcbp.setOwnSprite(pokemon.getBaseData().getID());
	}
	
	public void setFoePokemon(Pokemon pokemon){
		this.foePokemon = pokemon;
		this.dcbp.setFoeSprite(pokemon.getBaseData().getID());
	}
	
	public void switchPokemonTeam(){
		this.isOwnPokemon = !this.isOwnPokemon;
		Pokemon aux = this.foePokemon;
		this.setFoePokemon(this.myPokemon);
		this.setOwnPokemon(aux);
		this.dcbp.setBattleMessage("What will " + this.myPokemon.getBaseData().getName().toUpperCase() + " do?");
	}
	
	public Pokemon getMyPokemon(){
		return this.myPokemon;
	}
	
	public Pokemon getFoePokemon(){
		return this.foePokemon;
	}
	
	public void useMove(int i){
		this.dcbp.setBattleMessage(myPokemon.getBaseData().getName().toUpperCase() + " used " + 
				myPokemon.getMove(i).getName() + ".");
		this.dcrp.useMove(myPokemon, foePokemon, myPokemon.getMove(i));
	}
	
	private SingleTrainerBattle stb;
	private DamageCalculatorBattlePanel dcbp;
	private DamageCalculatorResultPanel dcrp;
	
	private final int myTeamPokeAmount;
	private final int foeTeamPokeAmount;
	private int pokeUsedIndex;
	private boolean isOwnPokemon;
	private int currentState;
	private Pokemon myPokemon;
	private Pokemon foePokemon;
	private State states[];
}