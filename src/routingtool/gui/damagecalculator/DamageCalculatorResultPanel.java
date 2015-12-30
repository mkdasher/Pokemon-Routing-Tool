package routingtool.gui.damagecalculator;

import java.awt.Dimension;

import javax.swing.JPanel;
import javax.swing.JTextArea;

import routingtool.pokemon.Pokemon;
import routingtool.pokemon.data.Ability;
import routingtool.pokemon.data.Move;
import routingtool.pokemon.data.MoveCategory;
import routingtool.pokemon.data.PokemonItem;
import routingtool.pokemon.data.PokemonType;
import routingtool.util.EffectivenessUtil;
import routingtool.util.Formula;

//http://www.smogon.com/dp/articles/damage_formula

public class DamageCalculatorResultPanel extends JPanel{
	public DamageCalculatorResultPanel(DamageCalculatorDialog dcd){
		this.dcd = dcd;
		this.setParams();
	}
	
	private void setParams(){
		this.setPreferredSize(new Dimension(400,384));
		this.txtRange = new JTextArea();
		this.add(txtRange);
	}
	
	public void useMove(Pokemon own, Pokemon foe, Move move){
		
		if (move.getMoveCategory() == MoveCategory.Status) return;
		
		int currentHP = own.stat.hp;
		
		int level = own.getLevel();
		int basepower = getBasePower(own, foe, move);
		int attack = getAttack(own, move);
		int defense = getDefense(foe, move);
		int crit = 1;
		
		//STAB
		float stab = 1;
		if (own.getBaseData().type1.getID() == move.getMoveType().getID()){
			stab = 1.5f;
		}
		if (own.getBaseData().type2.getID() == move.getMoveType().getID()){
			stab = 1.5f;
		}
		
		//Super Effective
		PokemonType moveType = move.getMoveType();
		if (move.getID() == Move.HIDDEN_POWER)
			moveType = own.getHiddenPowerType();
		float superef1 = EffectivenessUtil.getEffectiveness(moveType, foe.getBaseData().type1);
		float superef2 = EffectivenessUtil.getEffectiveness(moveType, foe.getBaseData().type2);
		
		
		//Formula
		String str = "Move used: " + move.getName() + ". Foe's HP " + foe.stat.hp + "\n";
		for (int i = 85; i <= 100; i++){
			str += "(" + String.valueOf(i) + ") - ";
			for (int j = 0; j <= 6; j++){
				int final_atk = (int) (attack * atkMult(j));
				int final_def = defense;//(int) (defense * atkMult(j));
				str += String.valueOf(Formula.calculateDamage(
						level,
						basepower,
						final_atk, //attack
						final_def, //def
						1, //mod1
						crit, //crit
						1, //mod2
						i, //rand
						stab, //stab
						superef1, //supereffect
						superef2, //supereffect
						1)); //mod3
				str += " - ";
			}
			if (i < 100) str += "\n";
		}
		this.txtRange.setText(str);
	}
	
	public int getBasePower(Pokemon own, Pokemon foe, Move move){
		int power = move.getPower();
		int movetype = move.getMoveType().getID();
		if (move.getID() == Move.HIDDEN_POWER){
			power = own.getHiddenPowerDamage();
			movetype = own.getHiddenPowerType().getID();
		}
		if (own.getAbility().getID() == Ability.TECHNICIAN && move.getPower() <= 60){
			power *= 1.5;
		}
		if (own.getBaseData().getID() == 25 && own.getHeldItem().getID() == PokemonItem.LIGHT_BALL){ //Pikachu and Lightball
			power *= 2;
		}
		if (movetype == PokemonType.ELECTRIC && own.getHeldItem().getID() == PokemonItem.MAGNET){
			power *= 1.2;
		}
		if (movetype == PokemonType.FIRE && own.getHeldItem().getID() == PokemonItem.CHARCOAL){
			power *= 1.2;
		}
		if ((movetype == PokemonType.FIRE || movetype == PokemonType.ICE)
			&& foe.getAbility().getID() == Ability.THICK_FAT){
			power *= 0.5;
		}
		return power;
	}
	
	public int getAttack(Pokemon own, Move move){
		int a = own.stat.atk;
		if (move.getMoveCategory() == MoveCategory.Special){
			a = own.stat.spa;
			if (own.getHeldItem().getID() == PokemonItem.CHOICE_SPECS){
				a *= 1.5;
			}
			else if (own.getHeldItem().getID() == PokemonItem.CHARCOAL && move.getMoveType().getID() == PokemonType.FIRE){
				a *= 1.1;
			}
		}
		return a;
	}
	
	public int getDefense(Pokemon foe, Move move){
		int d = foe.stat.def;
		if (move.getMoveCategory() == MoveCategory.Special){
			d = foe.stat.spd;
		}
		return d;
	}
	
	public float atkMult(int j){
		int a1 = 2, a2 = 2;
		if (j > 0) a1 += j;
		else if (j < 0) a2 -= j;
		return (float)a1 / (float)a2;
	}
	
	private DamageCalculatorDialog dcd;
	private JTextArea txtRange;
}
