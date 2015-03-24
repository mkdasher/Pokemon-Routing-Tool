package routingtool.gui.eventadd;

import java.awt.BorderLayout;
import java.awt.GridLayout;

import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import routingtool.components.DoubleTrainerBattle;
import routingtool.components.Event;
import routingtool.components.EventType;
import routingtool.components.PokemonUsed;
import routingtool.components.PokemonUsedList;
import routingtool.components.SingleTrainerBattle;
import routingtool.components.Trainer;
import routingtool.gui.components.IntegerJTextField;

public class DoubleTrainerPanel extends EventTypePanel{
	public DoubleTrainerPanel(final AddEventWindow aew){
		this.aew = aew;
		this.setParams();
	}
	
	private void setParams(){
		this.setBorder(new TitledBorder("Double Trainer Event"));
		this.setLayout(new BorderLayout());
		this.trainerPanel1 = new TrainerPanel(aew, this, "Trainer 1");
		this.trainerPanel2 = new TrainerPanel(aew, this, "Trainer 2");
		this.pokemonUsedPanel = new PokemonUsedPanel();
		this.money = new IntegerJTextField();
		this.add(new JPanel(){
			{
				this.setLayout(new BorderLayout());
				this.add(new JPanel(){
					{
						this.setLayout(new GridLayout(2,1));
						this.add(trainerPanel1);
						this.add(trainerPanel2);
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
	public Event getEvent() {
		if (money.isEmpty()){
			JOptionPane.showMessageDialog(aew, "Money field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		Trainer trainer1 = trainerPanel1.getTrainer();
		if (trainer1.getName().isEmpty()){
			JOptionPane.showMessageDialog(aew, "Trainer 1 name field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if (trainer1.getPokemonTeam().isEmpty()){
			JOptionPane.showMessageDialog(aew, "Trainer 1 Pokemon Team is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		Trainer trainer2 = trainerPanel2.getTrainer();
		if (trainer2.getName().isEmpty()){
			JOptionPane.showMessageDialog(aew, "Trainer 2 name field is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		if (trainer2.getPokemonTeam().isEmpty()){
			JOptionPane.showMessageDialog(aew, "Trainer 2 Pokemon Team is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		
		//Create custom PokemonUsed and return event
		PokemonUsedList pudl = new PokemonUsedList();
		for (int i = 0; i < trainer1.getPokemonTeam().size(); i++){
			PokemonUsed pud = new PokemonUsed(i, 1);
			pud.setUsed(0);
			pudl.add(pud);
		}
		for (int i = 0; i < trainer2.getPokemonTeam().size(); i++){
			PokemonUsed pud = new PokemonUsed(i, 2);
			pud.setUsed(0);
			pudl.add(pud);
		}
		return new DoubleTrainerBattle(trainer1, trainer2, Integer.parseInt(money.getText()), pudl);
	}
	
	@Override
	public EventType getEventType() {
		return EventType.DoubleTrainerBattle;
	}
	
	@Override
	public void updateComponents() {
		this.trainerPanel1.updateComponents();
		this.trainerPanel2.updateComponents();
		this.pokemonUsedPanel.updateComponents(this.trainerPanel1.getTrainer(), 1);
		this.pokemonUsedPanel.updateComponents(this.trainerPanel2.getTrainer(), 2);
	}
	
	private TrainerPanel trainerPanel1;
	private TrainerPanel trainerPanel2;
	private PokemonUsedPanel  pokemonUsedPanel;
	private IntegerJTextField money;
	private AddEventWindow aew;
}
