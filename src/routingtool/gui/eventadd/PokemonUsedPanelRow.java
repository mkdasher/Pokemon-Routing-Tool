package routingtool.gui.eventadd;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

import routingtool.components.PokemonTeam;
import routingtool.components.PokemonUsed;
import routingtool.gui.PokemonUsedButton;
import routingtool.gui.ToolBar;
import routingtool.gui.components.PokemonIcon;
import routingtool.pokemon.Pokemon;

public class PokemonUsedPanelRow extends JPanel{
	private static final long serialVersionUID = 3053914763748924945L;
	public PokemonUsedPanelRow(PokemonTeam myParty, int trainerID, int index){
		this.trainerID = trainerID;
		this.index = index;
		
		this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		this.setLayout(new GridLayout(1,N_CELLS + 2));
		
		JLabel foePoke = new JLabel(new PokemonIcon(0, DEFAULT_SIZE));
		switch(trainerID){
		case PokemonUsed.TID_TRAINER1:
			foePoke.setToolTipText("Trainer 1, Pokemon " + (index + 1));
			break;
		case PokemonUsed.TID_TRAINER2:
			foePoke.setToolTipText("Trainer 2, Pokemon " + (index + 1));
			break;
		}
		JLabel arrow = new JLabel(RIGHT_ARROW);
		this.add(foePoke);
		this.add(arrow);
		
		this.usedButton = new PokemonUsedButton[N_CELLS];
		for (int i = 0; i < N_CELLS ; i++){
			this.usedButton[i] = new PokemonUsedButton();
			this.add(usedButton[i]);
			if (i < myParty.size()){
				this.usedButton[i].setEnabled(true);
				this.usedButton[i].setIcon(new PokemonIcon(myParty.getPokemon(i).getBaseData().getID(), DEFAULT_SIZE));
			}
			else{
				this.usedButton[i].setEnabled(false);
			}
			this.usedButton[i].setPreferredSize(new Dimension(DEFAULT_SIZE, DEFAULT_SIZE));
		}
		this.usedButton[0].setUsed(true);
	}
	
	
	
	public void setFoePokemon(Pokemon foe){
		((JLabel)this.getComponent(0)).setIcon(new PokemonIcon(foe.getBaseData().getID(), DEFAULT_SIZE));
	}
	
	public PokemonUsed getPokemonUsed(){
		PokemonUsed pu = new PokemonUsed(this.index, this.trainerID);
		for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
			if (usedButton[i].isUsed()){
				pu.setUsed(i);
			}
		}
		return pu;
	}
	
	public void updateComponents(PokemonUsed pokemonUsed) {
		for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
			this.usedButton[i].setUsed(pokemonUsed.isUsed(i));
		}
	}

	public int getTrainerID(){
		return this.trainerID;
	}
	
	public int getIndex(){
		return this.index;
	}
	
	private int trainerID;
	private int index;
	private PokemonUsedButton[] usedButton;
	private final int DEFAULT_SIZE = 32;
	private static final int N_CELLS = 6;
	public static final ImageIcon RIGHT_ARROW = new ImageIcon(ToolBar.class.getResource("/image/IconRightArrow.png"));
}