package routingtool.gui;

import routingtool.Controller;
import routingtool.observers.EventListContainerObserverHelper;
import routingtool.components.Event;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.util.List;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableModel;

public class EventGridPanel extends JPanel{
	
	static final long serialVersionUID = 40643;
	
	final DefaultTableModel model = new DefaultTableModel(){
		private static final long serialVersionUID = 4140756218591596256L;

		@Override
	    public boolean isCellEditable(int row, int column) {
	       return false;
	    }
	}; 
	
	private final JTable table;
	private JScrollPane pane;
	
	public EventGridPanel(final JFrame MainWindow, final Controller c){
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Event List"));
		this.add(new ToolBar(MainWindow, c, this), BorderLayout.NORTH);
		model.addColumn("Event Type");
		model.addColumn("Description");
		this.table = new JTable(model){
			private static final long serialVersionUID = 1L;
			{
				c.addEventListContainerObserver(new EventListContainerObserverHelper() {
					private Thread changeThread;
					@Override
					public void eventListChange(final List<Event> eventList) {
						int scrollVal = pane.getHorizontalScrollBar().getValue();
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
									model.addRow(new Object[]{eventList.get(i).getEventType().toString(),eventList.get(i).toString()});
								model.fireTableDataChanged();
								table.setRowSelectionInterval(eventList.size()-1, eventList.size()-1);
							}
						}).start();
						pane.getVerticalScrollBar().setValue(pane.getVerticalScrollBar().getMaximum());
					}
				});
				
			}	
		};
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.getTableHeader().setResizingAllowed(false);
		this.table.getSelectionModel().addListSelectionListener(new ListSelectionListener(){

			@Override
			public void valueChanged(ListSelectionEvent arg0) {
				c.onEventGridSelectionChanged(table.getSelectedRow());
			}
			
		});
		pane = new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) {
			private static final long serialVersionUID = 1L;
			{	
				this.setPreferredSize(new Dimension(0, table.getRowHeight() * 6));
			}
		};
		this.add(pane,BorderLayout.CENTER);
	}

	public int getSelectedIndex() {
		return table.getSelectedRow();
	}
}
