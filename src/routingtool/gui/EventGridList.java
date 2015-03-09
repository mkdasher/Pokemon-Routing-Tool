package routingtool.gui;

import routingtool.Controller;
import routingtool.observers.EventListContainerObserverHelper;
import routingtool.compontents.Event;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

@SuppressWarnings("serial")
public class EventGridList extends JPanel{
	
	final DefaultTableModel model = new DefaultTableModel(); 
	private final JTable table;
	
	public EventGridList(final Controller c){
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Trainer List"));
		this.add(new ToolBar(), BorderLayout.NORTH);
		model.addColumn("Item ID"); 
		model.addColumn("Item Description"); 
		this.table = new JTable(model){
			private static final long serialVersionUID = 1L;
			{
				c.addEventListContainerObserver(new EventListContainerObserverHelper() {
					private Thread changeThread;
					@Override
					public void eventListChange(final List<Event> eventList) {
						//we can only change if we can interrupt, else it's not neccesary
						if (changeThread != null && changeThread.isAlive())
							changeThread.interrupt();
						//avoiding to update the table multiple times in a few miliseconds (else we can get errors)
						(changeThread = new Thread() {
							public void run() {
								//if the table is changed before 25 milisecs, we don't update it yet.
								try {
									Thread.sleep(25);
								} catch (InterruptedException e) {return;}
								
								model.getDataVector().removeAllElements();
								model.fireTableDataChanged();
								for (int i = 0; i < eventList.size(); i++) 
									//model.addRow(new Object[]{eventList.get(i).getId(),eventList.get(i).toString()});
								model.fireTableDataChanged();
							}
						}).start();

					}
				});
			}	
		};
		this.add(new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) {
			private static final long serialVersionUID = 1L;
			{	
				this.setPreferredSize(new Dimension(0, 0)); //así se limita a ocupar lo que le dejen		
			}
		},BorderLayout.CENTER);
	}
}
