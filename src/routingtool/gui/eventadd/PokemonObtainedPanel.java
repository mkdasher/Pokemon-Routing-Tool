package routingtool.gui.eventadd;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
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
		this.pButton = new PokemonButton(aew, this, false);
		this.chkOutsider = new JCheckBox();
		this.add(new JPanel(){
			{
				this.setLayout(new BorderLayout());
				this.add(pButton, BorderLayout.CENTER);
				this.add(new JPanel(), BorderLayout.WEST);
				this.add(new JPanel(), BorderLayout.EAST);
			}
		},BorderLayout.NORTH);
		this.add(new JPanel(){
			{
				this.setLayout(new GridLayout(1,2));
				this.add(new JLabel("Outsider"));
				this.add(chkOutsider);
			}
		}, BorderLayout.CENTER);
	}

	@Override
	public Event getEvent() {
		if (pButton.getPokemon().isEmpty()){
			JOptionPane.showMessageDialog(aew, "No Pokemon was chosen.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		pButton.getPokemon().setOutsider(this.chkOutsider.isSelected());
		return new PokemonObtained(pButton.getPokemon());
	}
	
	@Override
	public EventType getEventType() {
		return EventType.PokemonObtained;
	}
	
	@Override
	public void updateComponents() {
		
	}
	
	@Override
	public void updateFromEvent(Event event) {
		PokemonObtained po = (PokemonObtained) event;
		this.pButton = new PokemonButton(aew, this, po.getGift(), false);
		this.remove(0);
		this.add(new JPanel(){
			{
				this.setLayout(new BorderLayout());
				this.add(pButton, BorderLayout.CENTER);
				this.add(new JPanel(), BorderLayout.WEST);
				this.add(new JPanel(), BorderLayout.EAST);
			}
		},BorderLayout.NORTH);
		this.chkOutsider.setSelected(po.getGift().isOutsider());
	}
	
	private AddEventWindow aew;
	private PokemonButton pButton;
	private JCheckBox chkOutsider;
}
