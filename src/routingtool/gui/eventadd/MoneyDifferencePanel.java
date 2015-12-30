package routingtool.gui.eventadd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.ButtonGroup;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JTextField;
import javax.swing.border.TitledBorder;

import routingtool.components.Event;
import routingtool.components.EventType;
import routingtool.components.ModifyMoney;
import routingtool.gui.components.IntegerJTextField;

public class MoneyDifferencePanel extends EventTypePanel{
	public  MoneyDifferencePanel(final AddEventWindow aew){
		this.aew = aew;
		this.setParams();
	}
	
	private void setParams(){
		this.setBorder(new TitledBorder("Money Change"));
		this.setLayout(new BorderLayout());
		this.money = new IntegerJTextField(6);
		this.description = new JTextField();
		this.rbIncrease = new JRadioButton("Increase");
		this.rbIncrease.setSelected(true);
		this.rbDecrease = new JRadioButton("Decrease");
		ButtonGroup rbGroup = new ButtonGroup();
		rbGroup.add(rbIncrease);
		rbGroup.add(rbDecrease);
		this.add(new JPanel(){
			{
				this.setLayout(new GridLayout(3,1));
				this.add(new JPanel(){
					{
						this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
						this.add(new JLabel("Money"));
						this.add(money);
					}
				});
				this.add(new JPanel(){
					{
						this.setBorder(BorderFactory.createLineBorder(Color.BLACK));
						this.setLayout(new GridLayout(2,1));
						this.add(rbIncrease);
						this.add(rbDecrease);
					}
				});
				this.add(new JPanel(){
					{
						this.setLayout(new BoxLayout(this, BoxLayout.LINE_AXIS));
						this.add(new JLabel("Description"));
						this.add(description);
					}
				});
			}
		},BorderLayout.NORTH);
		
		
	}

	@Override
	public Event getEvent() {
		if (money.isEmpty() || money.getText().equals("0")){
			JOptionPane.showMessageDialog(aew, "Money has to be different than 0", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		int m = Integer.parseInt(money.getText());
		if (rbDecrease.isSelected()) m = -m;
		return new ModifyMoney(m, description.getText());
	}

	@Override
	public EventType getEventType() {
		return EventType.ModifyMoney;
	}

	@Override
	public void updateComponents() {}

	@Override
	public void updateFromEvent(Event event) {
		int m = ((ModifyMoney)event).getMoneyDifference();
		if (m > 0){
			this.money.setText(String.valueOf(m));
			this.rbIncrease.setSelected(true);
		}
		else{
			this.money.setText(String.valueOf(-m));
			this.rbDecrease.setSelected(true);
		}
		this.description.setText(((ModifyMoney)event).getDescription());
	}

	private JRadioButton rbIncrease;
	private JRadioButton rbDecrease;
	private IntegerJTextField money;
	private JTextField description;
	private AddEventWindow aew;
}
