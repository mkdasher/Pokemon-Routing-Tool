package routingtool.gui;

import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import routingtool.Controller;

@SuppressWarnings("serial")
public class EventInfo extends JPanel{
	public EventInfo(Controller c){
		this.setBorder(new TitledBorder("Information"));
	}
}
