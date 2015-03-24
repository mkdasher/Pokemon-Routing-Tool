package routingtool.gui.eventadd;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import routingtool.components.PokemonTeam;
import routingtool.components.Trainer;

@SuppressWarnings("serial")
public class TrainerPanel extends JPanel{
	public TrainerPanel(final AddEventWindow aew, final EventTypePanel etp, String titleBorder){
		this.aew = aew;
		this.etp = etp;
		this.trainerPokemon = new TrainerPokemon();
		this.setBorder(new TitledBorder(titleBorder));
		this.setLayout(new BorderLayout());
		this.add(new TrainerNameArea(),BorderLayout.NORTH);
		this.add(trainerPokemon,BorderLayout.CENTER);
	}
	
	public Trainer getTrainer(){
		String name = txtTrainerName.getText();
		PokemonTeam team = new PokemonTeam();
		for (int i = 0; i < N_BUTTON; i++){
			team.addPokemon(pokemonButton[i].getPokemon());
		}
		return new Trainer(name, team);
	}
	
	public void updateComponents(){
		Trainer t = this.getTrainer();
		for (int i =0; i < N_BUTTON; i++){
			trainerPokemon.remove(0);
		}
		pokemonButton = new PokemonButton[N_BUTTON];
		for (int i =0; i < N_BUTTON; i++){
			pokemonButton[i] = new PokemonButton(aew, etp, t.getPokemonTeam().getPokemon(i));
			trainerPokemon.add(pokemonButton[i]);
			pokemonButton[i].setEnabled(i <= t.getPokemonTeam().size());
		}
		this.revalidate();
	}
	
	class TrainerNameArea extends JPanel{
		public TrainerNameArea(){
			this.setLayout(new GridLayout(1,1));
			this.add(new JLabel("Trainer name "));
			txtTrainerName = new JTextField("");
			this.add(txtTrainerName);
		}
		
	}
	
	class TrainerPokemon extends JPanel{
		public TrainerPokemon(){
			this.setBorder(new TitledBorder("Pokemon Team"));
			this.setLayout(new GridLayout(2,3));
			pokemonButton = new PokemonButton[N_BUTTON];
			for (int i =0; i < N_BUTTON; i++){
				pokemonButton[i] = new PokemonButton(aew, etp);
				this.add(pokemonButton[i]);
				pokemonButton[i].setEnabled(false);
			}
			pokemonButton[0].setEnabled(true);
		}		
	}
	
	private JTextField txtTrainerName;
	private AddEventWindow aew;
	private EventTypePanel etp;
	private static final int N_BUTTON = 6;
	private PokemonButton[] pokemonButton;
	private TrainerPokemon trainerPokemon;
}


