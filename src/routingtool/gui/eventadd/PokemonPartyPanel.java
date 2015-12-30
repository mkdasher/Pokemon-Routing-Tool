package routingtool.gui.eventadd;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import routingtool.components.PokemonTeam;
import routingtool.components.Trainer;


public class PokemonPartyPanel extends JPanel{
	public PokemonPartyPanel(final AddEventWindow aew, final EventTypePanel etp, PokemonTeam myParty){
		this.aew = aew;
		this.etp = etp;
		this.myParty = myParty;
		this.partySubpanel = new PokemonPartySubpanel();
		this.setLayout(new BorderLayout());
		this.add(partySubpanel);
	}
	
	class PokemonPartySubpanel extends JPanel{
		public PokemonPartySubpanel(){
			this.setBorder(new TitledBorder("My Party"));
			this.setLayout(new GridLayout(2,3));
			pokemonButton = new PokemonButton[N_BUTTON];
			for (int i =0; i < N_BUTTON; i++){
				if (i < myParty.size()){
					pokemonButton[i] = new PokemonButton(aew, etp, myParty.getPokemon(i), true);
					pokemonButton[i].setEnabled(true);
				}
				else{
					pokemonButton[i] = new PokemonButton(aew, etp, true);
					pokemonButton[i].setEnabled(false);
				}
				this.add(pokemonButton[i]);
			}
			pokemonButton[0].setEnabled(true);
		}		
	}
	
	public PokemonTeam getPokemonTeam() {
		PokemonTeam team = new PokemonTeam();
		for (int i = 0; i < N_BUTTON; i++){
			team.addPokemon(pokemonButton[i].getPokemon());
		}
		return team;
	}
	
	private AddEventWindow aew;
	private EventTypePanel etp;
	private static final int N_BUTTON = 6;
	private PokemonButton[] pokemonButton;
	private PokemonPartySubpanel partySubpanel;
	private PokemonTeam myParty;
}
