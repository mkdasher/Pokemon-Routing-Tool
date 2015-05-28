package routingtool.gui.eventadd;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JCheckBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import routingtool.components.DoubleTrainerBattle;
import routingtool.components.Event;
import routingtool.components.EventType;
import routingtool.components.PokemonTeam;
import routingtool.components.PokemonUsed;
import routingtool.components.PokemonUsedList;
import routingtool.components.SingleTrainerBattle;
import routingtool.components.Trainer;
import routingtool.gui.MenuBar;
import routingtool.gui.components.IntegerJTextField;

public class SingleTrainerPanel extends EventTypePanel{
	public SingleTrainerPanel(final AddEventWindow aew, PokemonTeam myParty){
		this.aew = aew;
		this.setParams(myParty);
	}
	
	private void setParams(PokemonTeam myParty){
		this.setBorder(new TitledBorder("Single Trainer Event"));
		this.trainerPanel = new TrainerPanel(aew, this);
		this.pokemonPartyPanel = new PokemonPartyPanel(aew, this, myParty);
		this.trainerPanel.setBorder(new TitledBorder("Trainer"));
		this.pokemonUsedPanel = new PokemonUsedPanel(myParty);
		this.chkSpinner = new JCheckBox();
		this.money = new IntegerJTextField();
		this.setLayout(new BorderLayout());
		this.add(new JPanel(){
			{
				this.setLayout(new BorderLayout());
				this.add(new JPanel(){
					{
						this.setLayout(new GridLayout(2,1));
						this.add(trainerPanel);
						this.add(pokemonPartyPanel);
					}
				}, BorderLayout.CENTER);
				
				this.add(new JPanel(){
					{	
						this.setLayout(new GridLayout(2,1));
						this.add(new JPanel(){
							{
							this.setBorder(new TitledBorder("Money"));
							this.setLayout(new GridLayout(1,2));
							this.add(new JLabel("Money"));
							this.add(money);
							}
						});
						this.add(new JPanel(){
							{
							this.setBorder(new TitledBorder("Spinner"));
							this.setLayout(new GridLayout(1,2));
							this.add(new JLabel("Is spinner"));
							this.add(chkSpinner);
							}
						});

					}
				}, BorderLayout.NORTH);
			}
		}, BorderLayout.WEST);
		this.add(pokemonUsedPanel, BorderLayout.CENTER);
	}
	
	@Override
	public Event getEvent(){
	
		if (money.isEmpty()){
			JOptionPane.showMessageDialog(aew, "Money field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		Trainer trainer = trainerPanel.getTrainer();
		if (trainer.getName().isEmpty()){
			JOptionPane.showMessageDialog(aew, "Trainer name field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if (trainer.getPokemonTeam().isEmpty()){
			JOptionPane.showMessageDialog(aew, "Pokemon Team is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		//Get PokemonUsedList
		PokemonUsedList pudl = this.pokemonUsedPanel.getPokemonUsedList();
		
		//Get PokemonTeam
		PokemonTeam pTeam = pokemonPartyPanel.getPokemonTeam();
		
		return new SingleTrainerBattle(trainer, Integer.parseInt(money.getText()), pudl, pTeam, this.chkSpinner.isSelected());
	}
	
	@Override
	public EventType getEventType() {
		return EventType.SingleTrainerBattle;
	}
	
	@Override
	public void updateComponents() {
		this.trainerPanel.updateComponents();
		this.pokemonUsedPanel.updateComponents(this.trainerPanel.getTrainer(), 1);
	}
	
	@Override
	public void updateFromEvent(Event event) {
		SingleTrainerBattle stb = (SingleTrainerBattle) event;
		this.money.setText(String.valueOf(stb.getMoney()));
		this.chkSpinner.setSelected(stb.isSpinner());
		this.trainerPanel.updateComponents(stb.getTrainer());
		this.pokemonUsedPanel.updateComponents(stb.getPokemonUsedList());
		this.pokemonUsedPanel.updateComponents(this.trainerPanel.getTrainer(), 1);
	}
	
	private TrainerPanel trainerPanel;
	private PokemonPartyPanel pokemonPartyPanel;
	private PokemonUsedPanel  pokemonUsedPanel;
	private AddEventWindow aew;
	private IntegerJTextField money;
	private JCheckBox chkSpinner;
	
}
