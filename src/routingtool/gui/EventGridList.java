package routingtool.gui;

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
	
	public EventGridList(){
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Trainer List"));
		this.add(new ToolBar(), BorderLayout.NORTH);
		model.addColumn("Item ID"); 
		model.addColumn("Item Description"); 
		this.table = new JTable(model);
		this.add(new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER) {
			private static final long serialVersionUID = 1L;
			{	
				this.setPreferredSize(new Dimension(0, 0)); //así se limita a ocupar lo que le dejen		
			}
		},BorderLayout.CENTER);
	}
	
	/*
	 public RobotInfo(final Controller c) {
		this.setLayout(new BorderLayout());
		this.setBorder(new TitledBorder("Robot Info"));
		this.add(new RobotFuelAndPointsStatus(c), BorderLayout.NORTH);
		model.addColumn("Item ID"); 
		model.addColumn("Item Description"); 
		this.table = new JTable(model) {
			private static final long serialVersionUID = 1L;
			{
				c.addItemContainerObserver(new ItemContainerObserverHelper() {
					private Thread changeThread;
					@Override
					public void inventoryChange(final List<Item> inventory) {
						//sólo si podemos lo interrumpimos, si no, no es necesario
						if (changeThread != null && changeThread.isAlive())
							changeThread.interrupt();
						//así aseguramos que sólo se cambie la tabla de una vez en una vez, porque estaba dando
						//errores rojos feos en la consola
						(changeThread = new Thread() {
							public void run() {
								//si tras 25 ms no ha cambiado el inventario, lo actualizamos
								try {
									Thread.sleep(25);
								} catch (InterruptedException e) {
									//si nos interrumpen por otro cambio, pasamos de actualizar y ya lo hará el siguiente
									return;
								}
								
								model.getDataVector().removeAllElements();
								model.fireTableDataChanged();
								for (int i = 0; i < inventory.size(); i++) 
									model.addRow(new Object[]{inventory.get(i).getId(),inventory.get(i).toString()});
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
		this.addKeyController();
	}
	 */
}
