package routingtool.gui.eventadd;

import java.awt.GridLayout;

import javax.swing.JButton;
import javax.swing.JPanel;

import routingtool.components.PokemonUsedList;
import routingtool.components.Trainer;

public class PokemonUsedPanel extends JPanel{
	public PokemonUsedPanel(){
		this.pokemonUsedList = new PokemonUsedList();
		this.usedButtonArray = new PokemonUsedButtonArray[N_ROWS];
		this.setLayout(new GridLayout(N_ROWS,1));
		for (int i = 0; i < N_ROWS; i++){
			this.usedButtonArray[i] = new PokemonUsedButtonArray();
			this.add(usedButtonArray[i]);
			this.usedButtonArray[i].setVisible(false);
		}
	}
	
	public void updateComponents(Trainer trainer, int TrainerID){
		for (int i = 0; i < trainer.getPokemonTeam().size(); i++){
			//TODO
		}
	}
	
	private class PokemonUsedButtonArray extends JPanel{
		PokemonUsedButtonArray(){
			this.setLayout(new GridLayout(1,N_CELLS));
			this.usedButton = new PokemonUsedButton[N_CELLS];
			for (int i = 0; i < N_CELLS; i++){
				this.usedButton[i] = new PokemonUsedButton();
				this.add(usedButton[i]);
				this.usedButton[i].setEnabled(true);
			}
		}
		private PokemonUsedButton[] usedButton;
	}
	
	private class PokemonUsedButton extends JButton{
		//TODO
	}
	
	private PokemonUsedList pokemonUsedList;
	private PokemonUsedButtonArray[] usedButtonArray;
	private static final int N_ROWS = 12;
	private static final int N_CELLS = 6;
}
