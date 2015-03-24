package routingtool.gui.eventadd;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import routingtool.components.Event;
import routingtool.components.EventType;
import routingtool.components.PokemonUsed;
import routingtool.components.PokemonUsedList;
import routingtool.components.SingleTrainerBattle;
import routingtool.components.Trainer;
import routingtool.gui.MenuBar;
import routingtool.gui.components.IntegerJTextField;

public class SingleTrainerPanel extends EventTypePanel{
	public SingleTrainerPanel(final AddEventWindow aew){
		this.aew = aew;
		this.setParams();
	}
	
	private void setParams(){
		this.setBorder(new TitledBorder("Single Trainer Event"));
		this.trainerPanel = new TrainerPanel(aew, this, "Trainer");
		this.pokemonUsedPanel = new PokemonUsedPanel();
		this.money = new IntegerJTextField();
		this.setLayout(new BorderLayout());
		this.add(new JPanel(){
			{
				this.setLayout(new BorderLayout());
				this.add(new JPanel(){
					{
						this.setLayout(new GridLayout(2,1));
						this.add(trainerPanel);
						this.add(new JPanel()); //empty JPanel
					}
				}, BorderLayout.CENTER);
				
				this.add(new JPanel(){
					{
						this.setBorder(new TitledBorder("Money"));
						this.setLayout(new GridLayout(1,2));
						this.add(new JLabel("Money"));
						this.add(money);
					}
				}, BorderLayout.NORTH);
			}
		}, BorderLayout.WEST);
		this.add(pokemonUsedPanel, BorderLayout.EAST);
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
		
		//Create custom PokemonUsed and return event
		PokemonUsedList pudl = new PokemonUsedList();
		for (int i = 0; i < trainer.getPokemonTeam().size(); i++){
			PokemonUsed pud = new PokemonUsed(i, 1);
			pud.setUsed(0);
			pudl.add(pud);
		}
		return new SingleTrainerBattle(trainer, Integer.parseInt(money.getText()), pudl, false);
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
	
	private TrainerPanel trainerPanel;
	private PokemonUsedPanel  pokemonUsedPanel;
	private AddEventWindow aew;
	private IntegerJTextField money;
}
