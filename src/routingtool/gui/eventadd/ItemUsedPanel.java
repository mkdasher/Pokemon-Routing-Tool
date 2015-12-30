package routingtool.gui.eventadd;

import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.DefaultCellEditor;
import javax.swing.DefaultComboBoxModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JToolBar;
import javax.swing.ListSelectionModel;
import javax.swing.border.TitledBorder;
import javax.swing.table.DefaultTableModel;

import routingtool.components.Event;
import routingtool.components.EventType;
import routingtool.components.ItemUsedData;
import routingtool.components.PokemonTeam;
import routingtool.components.ItemUsed;
import routingtool.gui.ToolBar;
import routingtool.gui.components.IntegerJTextField;
import routingtool.pokemon.Pokemon;
import routingtool.pokemon.data.PokemonItem;
import routingtool.util.DataListUtil;

public class ItemUsedPanel extends EventTypePanel{

	private static final long serialVersionUID = -5813686990232167382L;
	public ItemUsedPanel(AddEventWindow aew, PokemonTeam myParty) {
		this.aew = aew;
		this.myParty = myParty;
		this.setParams();
	}
	
	private void setParams(){
		this.setBorder(new TitledBorder("Item Used"));
		this.setLayout(new BorderLayout());
		this.add(new JToolBar(){
			private static final long serialVersionUID = 3977487037161380351L;
			private final ImageIcon ADD_ICON = new ImageIcon(ToolBar.class.getResource("/image/IconPlus.png"));
			private final ImageIcon REMOVE_ICON = new ImageIcon(ToolBar.class.getResource("/image/IconMinus.png"));
			{
				this.setFloatable(false);
				JButton addButton = new JButton();
				addButton.setToolTipText("Add new Item");
				addButton.setIcon(ADD_ICON);
				addButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent event) {
						model.addRow(new Object[]{DataListUtil.itemList[0], myParty.getPokemon(0), 0});
					}
				});
				this.add(addButton);
				
				JButton removeButton = new JButton();
				removeButton.setToolTipText("Remove Item");
				removeButton.setIcon(REMOVE_ICON);	
				removeButton.addActionListener(new ActionListener(){
					@Override
					public void actionPerformed(ActionEvent event) {
						if (table.getSelectedRow() == -1){
							if (table.getRowCount() > 0){
								model.removeRow(table.getRowCount() - 1);
							}
						}
						else{
							model.removeRow(table.getSelectedRow());
						}
					}			
				});
				this.add(removeButton);
			}
		}, BorderLayout.NORTH);
		model.addColumn("Item Used"); 
		model.addColumn("Pokemon");
		model.addColumn("Amount");
		table = new JTable(model){
			private static final long serialVersionUID = 1L;
			{
								
			}	
		};
		this.table.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		this.table.getTableHeader().setReorderingAllowed(false);
		this.table.getTableHeader().setResizingAllowed(false);
		
		JComboBox<PokemonItem> ibox = new JComboBox<PokemonItem>();
		ibox.setModel(new DefaultComboBoxModel(DataListUtil.itemList));
		ibox.setEnabled(true);
		this.table.getColumnModel().getColumn(0).setCellEditor(new DefaultCellEditor(ibox));
		JComboBox<Pokemon> pbox = new JComboBox<Pokemon>();
		pbox.setModel(new DefaultComboBoxModel(myParty.toArray()));
		pbox.setEnabled(true);
		this.table.getColumnModel().getColumn(1).setCellEditor(new DefaultCellEditor(pbox));
		this.table.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new IntegerJTextField(2)));
	
		this.add(new JScrollPane(table,JScrollPane.VERTICAL_SCROLLBAR_ALWAYS,JScrollPane.HORIZONTAL_SCROLLBAR_NEVER),BorderLayout.CENTER);
	}

	@Override
	public Event getEvent() {
		try{
			this.table.getCellEditor().stopCellEditing();
		}
		catch (Exception e){};
		List<ItemUsedData> itemUsedList = new ArrayList<ItemUsedData>();
		for (int i = 0; i < model.getRowCount(); i++){
			PokemonItem item = (PokemonItem) model.getValueAt(i, 0);
			if (item.getID() != 0){
				String amountStr = model.getValueAt(i, 2).toString();
				if (!amountStr.equals("")){
					int amount = Integer.parseInt(amountStr);
					if (amount != 0){
						Pokemon pkm = (Pokemon) model.getValueAt(i, 1);
						int index = -1;
						for (int j = 0; j < myParty.size(); j++){
							if (pkm == myParty.getPokemon(j)) index = j;
						}
						if (index != -1){
							for (int k = 0; k < amount; k++){
								itemUsedList.add(new ItemUsedData(item, index));
							}
						}
					}
				}
			}
		}
		if (itemUsedList.size() == 0){
			JOptionPane.showMessageDialog(aew, "Item Used List is empty.", "Error", JOptionPane.ERROR_MESSAGE);
			return null;
		}
		return new ItemUsed(itemUsedList);
	}

	@Override
	public EventType getEventType() {
		return EventType.ItemUsed;
	}

	@Override
	public void updateComponents() {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void updateFromEvent(Event event) {
		ItemUsed iu = (ItemUsed) event;
		List<ItemUsedData> iudList = iu.getItemUsedList();
		int listIndex = 0;
		int itemIndex = iudList.get(0).item.getID();
		int pkmIndex = iudList.get(0).pokemonIndex;
		int amount = 0;
		for (int i = 0; i < iudList.size(); i++){
			ItemUsedData iud = iudList.get(i);
			if (iud.item.getID() == itemIndex && iud.pokemonIndex == pkmIndex){
				amount++;
			}
			else{
				model.addRow(new Object[]{findItem(iudList.get(listIndex).item.getName()), myParty.getPokemon(pkmIndex), amount});
				itemIndex = iud.item.getID();
				pkmIndex = iud.pokemonIndex;
				listIndex = i;
				amount = 1;
			}
		}
		model.addRow(new Object[]{findItem(iudList.get(listIndex).item.getName()), myParty.getPokemon(pkmIndex), amount});
	}
	
	private PokemonItem findItem(String name){
		int imin = 0, imax = DataListUtil.itemList.length;
		while (imax >= imin){
			int imid = (imin + imax) / 2;
			String curName = ((PokemonItem) DataListUtil.itemList[imid]).getName();
			if (curName.equals(name)){
				return (PokemonItem) DataListUtil.itemList[imid];
			}
			else if (curName.compareTo(name) < 0){
				imin = imid + 1;
			}
			else{
				imax = imid - 1;
			}
		}
		return (PokemonItem) DataListUtil.itemList[0];
	}

	private AddEventWindow aew;
	private PokemonTeam myParty;
	private JTable table;
	
	final DefaultTableModel model = new DefaultTableModel(){
		private static final long serialVersionUID = 7192495016457725219L;
	}; 
}
