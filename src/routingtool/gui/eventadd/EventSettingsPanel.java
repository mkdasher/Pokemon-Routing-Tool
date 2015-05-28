package routingtool.gui.eventadd;

import java.awt.CardLayout;
import javax.swing.JPanel;

import routingtool.components.Event;
import routingtool.components.EventType;
import routingtool.components.PokemonTeam;

public class EventSettingsPanel extends JPanel{
	EventSettingsPanel(final AddEventWindow aew, boolean isInitialEvent, Event event, PokemonTeam myParty){
		card = new CardLayout();
		this.setLayout(card);
		dtPanel = new DoubleTrainerPanel(aew, myParty);
		stPanel = new SingleTrainerPanel(aew, myParty);
		poPanel = new PokemonObtainedPanel(aew);
		wePanel = new WildEncounterPanel(aew, myParty);
		iuPanel = new ItemUsedPanel(aew, myParty);
		mdPanel = new MoneyDifferencePanel(aew);
		this.add(stPanel);
		this.add(dtPanel);
		this.add(poPanel);
		this.add(wePanel);
		this.add(iuPanel);
		this.add(mdPanel);
		card.addLayoutComponent(stPanel, EventType.SingleTrainerBattle.toString());
		card.addLayoutComponent(dtPanel, EventType.DoubleTrainerBattle.toString());
		card.addLayoutComponent(poPanel, EventType.PokemonObtained.toString());
		card.addLayoutComponent(wePanel, EventType.WildEncounter.toString());
		card.addLayoutComponent(iuPanel, EventType.ItemUsed.toString());
		card.addLayoutComponent(mdPanel, EventType.ModifyMoney.toString());
		
		if (isInitialEvent){
			card.show(this, EventType.PokemonObtained.toString());
			this.panelOnTop = EventType.PokemonObtained.toString();
			if (event != null){
				initializePanel(event);
			}
		}
		else{
			if (event != null){
				card.show(this, event.getEventType().toString());
				this.panelOnTop = event.getEventType().toString();
				initializePanel(event);
			}
			else{
				card.show(this, EventType.SingleTrainerBattle.toString());
				this.panelOnTop = EventType.SingleTrainerBattle.toString();
			}
		}
	}
	
	private void initializePanel(Event event){
		switch(event.getEventType()){
		case SingleTrainerBattle:
			stPanel.updateFromEvent(event);
			break;
		case DoubleTrainerBattle:
			dtPanel.updateFromEvent(event);
			break;
		case PokemonObtained:
			poPanel.updateFromEvent(event);
			break;
		case WildEncounter:
			wePanel.updateFromEvent(event);
			break;
		case ItemUsed:
			iuPanel.updateFromEvent(event);
			break;
		case ModifyMoney:
			mdPanel.updateFromEvent(event);
			break;
		default:
		}
	}
	
	public EventTypePanel getTopPanel(){
		if (panelOnTop.equals(EventType.SingleTrainerBattle.toString()))
				return stPanel;
		else if (panelOnTop.equals(EventType.DoubleTrainerBattle.toString()))
				return dtPanel;
		else if (panelOnTop.equals(EventType.PokemonObtained.toString()))
			return poPanel;
		else if (panelOnTop.equals(EventType.WildEncounter.toString()))
			return wePanel;
		else if (panelOnTop.equals(EventType.ItemUsed.toString()))
			return iuPanel;
		else return mdPanel;
	}
	
	public void switchLayout(String s){
		this.card.show(this, s);
		this.panelOnTop = s;
	}
	
	public CardLayout getCardLayout(){
		return this.card;
	}
	
	private String panelOnTop;
	private CardLayout card;
	
	private SingleTrainerPanel stPanel;
	private DoubleTrainerPanel dtPanel;
	private PokemonObtainedPanel poPanel;
	private WildEncounterPanel wePanel;
	private ItemUsedPanel iuPanel;
	private MoneyDifferencePanel mdPanel;
}
