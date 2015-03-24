package routingtool.gui.eventadd;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import routingtool.components.Event;
import routingtool.components.EventType;
import routingtool.components.PokemonObtained;

@SuppressWarnings("serial")
public class PokemonObtainedPanel extends EventTypePanel{
	public PokemonObtainedPanel(final AddEventWindow aew){
		this.aew = aew;
		this.setParams();
	}
	
	private void setParams(){
		this.setBorder(new TitledBorder("Pokemon Obtained Event"));
		this.setLayout(new BorderLayout());
		this.pButton = new PokemonButton(aew, this);
		this.add(new JPanel(){
			{
				this.setLayout(new BorderLayout());
				this.add(pButton, BorderLayout.CENTER);
				this.add(new JPanel(), BorderLayout.WEST);
				this.add(new JPanel(), BorderLayout.EAST);
			}
		},BorderLayout.NORTH);
	}

	@Override
	public Event getEvent() {
		if (pButton.getPokemon().isEmpty()){
			JOptionPane.showMessageDialog(aew, "No Pokemon was chosen.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		return new PokemonObtained(pButton.getPokemon());
	}
	
	@Override
	public EventType getEventType() {
		return EventType.PokemonObtained;
	}
	
	@Override
	public void updateComponents() {
		// TODO Auto-generated method stub
		
	}
	
	private AddEventWindow aew;
	private PokemonButton pButton;
}
