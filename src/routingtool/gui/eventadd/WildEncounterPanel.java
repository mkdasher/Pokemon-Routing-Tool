package routingtool.gui.eventadd;

import java.awt.BorderLayout;

import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import routingtool.components.Event;
import routingtool.components.EventType;
import routingtool.components.PokemonObtained;
import routingtool.components.PokemonTeam;
import routingtool.components.PokemonUsed;
import routingtool.components.WildEncounterBattle;

public class WildEncounterPanel extends EventTypePanel{
	public WildEncounterPanel(final AddEventWindow aew, PokemonTeam myParty){
		this.aew = aew;
		this.setParams(myParty);
	}
	
	private void setParams(PokemonTeam myParty){
		this.setBorder(new TitledBorder("Wild Encounter Event"));
		this.wildPokemonButton = new PokemonButton(aew, this, false);
		this.pupRow = new PokemonUsedPanelRow(myParty, 0, 0);
		this.add(new JPanel(){
			{
				this.setLayout(new BorderLayout());
				this.add(wildPokemonButton, BorderLayout.CENTER);
				this.add(new JPanel(), BorderLayout.WEST);
				this.add(new JPanel(), BorderLayout.EAST);
				this.add(pupRow, BorderLayout.SOUTH);
			}
		},BorderLayout.NORTH);
	}
	
	@Override
	public Event getEvent() {
		if (wildPokemonButton.getPokemon().isEmpty()){
			JOptionPane.showMessageDialog(aew, "No Pokemon was chosen.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		PokemonUsed pu = pupRow.getPokemonUsed();
		return new WildEncounterBattle(wildPokemonButton.getPokemon(), pu);
	}

	@Override
	public EventType getEventType() {
		return EventType.WildEncounter;
	}

	@Override
	public void updateComponents() {
		this.pupRow.setFoePokemon(wildPokemonButton.getPokemon());
	}

	@Override
	public void updateFromEvent(Event event) {
		WildEncounterBattle we = (WildEncounterBattle) event;
		pupRow.updateComponents(we.getPokemonUsed());
		this.wildPokemonButton = new PokemonButton(aew, this, we.getWildPokemon(), false);
		this.remove(0);
		this.add(new JPanel(){
			{
				this.setLayout(new BorderLayout());
				this.add(wildPokemonButton, BorderLayout.CENTER);
				this.add(new JPanel(), BorderLayout.WEST);
				this.add(new JPanel(), BorderLayout.EAST);
				this.add(pupRow, BorderLayout.SOUTH);
			}
		},BorderLayout.NORTH);
	}
	
	private AddEventWindow aew;
	private PokemonButton wildPokemonButton;
	private PokemonUsedPanelRow pupRow;
}
