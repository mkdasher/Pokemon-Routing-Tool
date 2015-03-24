package routingtool.gui.eventadd;

import java.awt.CardLayout;
import javax.swing.JPanel;

import routingtool.components.EventType;

public class EventSettingsPanel extends JPanel{
	EventSettingsPanel(final AddEventWindow aew, boolean isInitialEvent){
		card = new CardLayout();
		this.setLayout(card);
		dtPanel = new DoubleTrainerPanel(aew);
		stPanel = new SingleTrainerPanel(aew);
		poPanel = new PokemonObtainedPanel(aew);
		this.add(stPanel);
		this.add(dtPanel);
		this.add(poPanel);
		card.addLayoutComponent(stPanel, EventType.SingleTrainerBattle.toString());
		card.addLayoutComponent(dtPanel, EventType.DoubleTrainerBattle.toString());
		card.addLayoutComponent(poPanel, EventType.PokemonObtained.toString());
		if (isInitialEvent){
			card.show(this, EventType.PokemonObtained.toString());
			this.panelOnTop = EventType.PokemonObtained.toString();
		}
		else{
			card.show(this, EventType.SingleTrainerBattle.toString());
			this.panelOnTop = EventType.SingleTrainerBattle.toString();
		}
		
	}
	
	public void switchLayout(String s){
		this.card.show(this, s);
		this.panelOnTop = s;
	}
	
	public CardLayout getCardLayout(){
		return this.card;
	}
	
	public EventTypePanel getTopPanel(){
		if (panelOnTop.equals(EventType.SingleTrainerBattle.toString()))
				return stPanel;
		else if (panelOnTop.equals(EventType.DoubleTrainerBattle.toString()))
				return dtPanel;
		else return poPanel;
	}
	
	private String panelOnTop;
	private CardLayout card;
	private SingleTrainerPanel stPanel;
	private DoubleTrainerPanel dtPanel;
	private PokemonObtainedPanel poPanel;
}
