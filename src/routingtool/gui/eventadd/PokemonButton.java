package routingtool.gui.eventadd;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;

import routingtool.components.EventType;
import routingtool.gui.components.PokemonIcon;
import routingtool.pokemon.Pokemon;

public class PokemonButton extends JButton{

	private static final long serialVersionUID = 918187;
	
	public PokemonButton(final AddEventWindow aew, final EventTypePanel etp){
		this.pokemon = new Pokemon();
		this.aew = aew;
		this.etp = etp;
		this.setParams();
	}
	
	public PokemonButton(final AddEventWindow aew, final EventTypePanel etp, Pokemon pokemon){
		this.pokemon = pokemon;
		this.aew = aew;
		this.etp = etp;
		this.setParams();
	}
	
	private void setParams(){
		this.setPreferredSize(new Dimension(64,64));
		this.setMinimumSize(new Dimension(64,64));
		this.setMaximumSize(new Dimension(64,64));
		this.setIcon(new PokemonIcon(this.pokemon.getBaseData().getID()));
		this.addActionListener(new ActionListener(){
			@Override
			public void actionPerformed(ActionEvent arg0) {
				PokemonWindow pWindow = new PokemonWindow(aew, isTrainerButton(), pokemon);
				PokemonButton.this.pokemon = pWindow.showDialog();
				PokemonButton.this.setIcon(new PokemonIcon(pokemon.getBaseData().getID()));
				etp.updateComponents();
			}
			
		});
	}
	
	private boolean isTrainerButton(){
		EventType etype = etp.getEventType();
		if (etype == EventType.DoubleTrainerBattle || etype == EventType.SingleTrainerBattle){
			return true;
		} else return false;
	}
	
	@Override
	public void paint(Graphics g) {
		super.paint(g);
	}
	
	
	public Pokemon getPokemon(){
		return this.pokemon;
	}
	
	private Pokemon pokemon;
	private EventTypePanel etp;
	private AddEventWindow aew;
}
