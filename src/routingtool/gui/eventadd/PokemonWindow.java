package routingtool.gui.eventadd;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.GridLayout;
import java.awt.Window;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.util.ArrayList;
import java.util.List;

import javax.swing.BorderFactory;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

import routingtool.pokemon.Pokemon;
import routingtool.pokemon.data.*;
import routingtool.util.CSVFileReader;
import routingtool.util.ComboBoxUtil;
import routingtool.util.DataListUtil;
import routingtool.util.FileUtil;
import routingtool.gui.components.PokemonIcon;
import routingtool.gui.components.IntegerJTextField;

public class PokemonWindow extends JDialog{
	private static final long serialVersionUID = -4515420509664013455L;
	public enum PokemonWindowType{
		TrainerPokemon, PokemonObtained, PokemonParty;
	}
	
	public PokemonWindow(final Window parent, PokemonWindowType type, Pokemon previous){
		super(parent, "Pokemon");
		this.type = type;
		this.pokemon = previous;
		this.parent = parent;
		this.setParams();
		this.setPanels();
		if (this.type == PokemonWindowType.PokemonParty){
			setEditOnlyMoves();
		}
	}	
	
	/**
	 * If this is PokemonParty Window type, everything should be read-only except for the moves.
	 */
	private void setEditOnlyMoves() {
		this.cmbPokemon.setEnabled(false);
		this.cmbNature.setEnabled(false);
		this.cmbAbility.setEnabled(false);
		this.txtLevel.setEnabled(false);
		this.cmbHeldItem.setEnabled(true); //Held Item is also editable in set move only mode.
		this.txtHP.setEnabled(false);
		this.txtAtk.setEnabled(false);
		this.txtDef.setEnabled(false);
		this.txtSpa.setEnabled(false);
		this.txtSpd.setEnabled(false);
		this.txtSpe.setEnabled(false);
	}

	private void setParams(){
		this.setModal(true);
		this.setLayout(new BorderLayout());
		this.setLocation(parent.getX() + 10, parent.getY() + 30);
				
		this.lblPokemonID = new JLabel("#1");
		this.lblPokemonID.setHorizontalAlignment(SwingConstants.RIGHT);
		this.pkmIconLabel = new JLabel(new PokemonIcon(1));
		this.pkmIconLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		
		this.txtLevel = new IntegerJTextField(3);
		this.txtLevel.setText(String.valueOf(pokemon.getLevel()));
		this.txtAtk = new IntegerJTextField(2);
		this.txtAtk.setEnabled(this.type == PokemonWindowType.PokemonObtained);		
		this.txtAtk.setText(String.valueOf(pokemon.IV.atk));
		this.txtDef = new IntegerJTextField(2);
		this.txtDef.setEnabled(this.type == PokemonWindowType.PokemonObtained);
		this.txtDef.setText(String.valueOf(pokemon.IV.def));
		this.txtSpa = new IntegerJTextField(2);
		this.txtSpa.setEnabled(this.type == PokemonWindowType.PokemonObtained);
		this.txtSpa.setText(String.valueOf(pokemon.IV.spa));
		this.txtSpd = new IntegerJTextField(2);
		this.txtSpd.setEnabled(this.type == PokemonWindowType.PokemonObtained);
		this.txtSpd.setText(String.valueOf(pokemon.IV.spd));
		this.txtSpe = new IntegerJTextField(2);
		this.txtSpe.setEnabled(this.type == PokemonWindowType.PokemonObtained);
		this.txtSpe.setText(String.valueOf(pokemon.IV.spe));
		this.txtHP = new IntegerJTextField(2);
		if(this.type == PokemonWindowType.TrainerPokemon){
			txtHP.getDocument().addDocumentListener(new DocumentListener(){
				@Override
				public void changedUpdate(DocumentEvent arg0){updateAllIVs();}
				@Override
				public void insertUpdate(DocumentEvent arg0){updateAllIVs();}
				@Override
				public void removeUpdate(DocumentEvent arg0){updateAllIVs();}
				public void updateAllIVs(){
					txtAtk.setText(txtHP.getText());
					txtDef.setText(txtHP.getText());
					txtSpa.setText(txtHP.getText());
					txtSpd.setText(txtHP.getText());
					txtSpe.setText(txtHP.getText());
				}
			});
		}
		this.txtHP.setText(String.valueOf(pokemon.IV.hp));
		this.cmbAbility = new JComboBox<Ability>();
		this.cmbAbility.setModel(new DefaultComboBoxModel(DataListUtil.abilityList));
		this.cmbPokemon = new JComboBox<PokemonData>();
		this.cmbPokemon.setModel(new DefaultComboBoxModel(DataListUtil.pokemonList));
		this.cmbPokemon.setEnabled(true);
		this.cmbPokemon.addItemListener(new ItemListener(){
			@Override
			public void itemStateChanged(ItemEvent arg0) {
				PokemonData pkm = (PokemonData) cmbPokemon.getSelectedItem();
				pkmIconLabel.setIcon(new PokemonIcon(pkm.getID()));
				lblPokemonID.setText("#" + pkm.getID());
				updateAbilityModel(pkm.getID());
			}
		});
		this.cmbPokemon.setSelectedItem(ComboBoxUtil.getComboBoxItem(this.cmbPokemon, pokemon.getBaseData().getID()));
		this.cmbAbility.setEnabled(true);
		this.cmbAbility.setSelectedItem(ComboBoxUtil.getComboBoxItem(this.cmbAbility, pokemon.getAbility().getID()));
		this.cmbNature = new JComboBox<Nature>();
		this.cmbNature.setModel(new DefaultComboBoxModel(DataListUtil.natureList));
		this.cmbNature.setEnabled(true);
		this.cmbNature.setSelectedItem(ComboBoxUtil.getComboBoxItem(this.cmbNature, pokemon.getNature().getID()));
		this.cmbHeldItem = new JComboBox<PokemonItem>();
		this.cmbHeldItem.setModel(new DefaultComboBoxModel(DataListUtil.itemList));
		this.cmbHeldItem.setEnabled(true);
		this.cmbHeldItem.setSelectedItem(ComboBoxUtil.getComboBoxItem(this.cmbHeldItem, pokemon.getHeldItem().getID()));
		this.cmbMove1 = new JComboBox<Move>();
		this.cmbMove1.setModel(new DefaultComboBoxModel(DataListUtil.moveList));
		this.cmbMove1.setEnabled(true);
		this.cmbMove1.setSelectedItem(ComboBoxUtil.getComboBoxItem(this.cmbMove1, pokemon.getMove(0).getID()));	
		this.cmbMove2 = new JComboBox<Move>();
		this.cmbMove2.setModel(new DefaultComboBoxModel(DataListUtil.moveList));
		this.cmbMove2.setEnabled(true);
		this.cmbMove2.setSelectedItem(ComboBoxUtil.getComboBoxItem(this.cmbMove2, pokemon.getMove(1).getID()));		
		this.cmbMove3 = new JComboBox<Move>();
		this.cmbMove3.setModel(new DefaultComboBoxModel(DataListUtil.moveList));
		this.cmbMove3.setEnabled(true);
		this.cmbMove3.setSelectedItem(ComboBoxUtil.getComboBoxItem(this.cmbMove3, pokemon.getMove(2).getID()));
		this.cmbMove4 = new JComboBox<Move>();
		this.cmbMove4.setModel(new DefaultComboBoxModel(DataListUtil.moveList));
		this.cmbMove4.setEnabled(true);
		this.cmbMove4.setSelectedItem(ComboBoxUtil.getComboBoxItem(this.cmbMove4, pokemon.getMove(3).getID()));
		
		//Initialize Pokemon Image to the correct Pokemon
		this.pkmIconLabel.setIcon(new PokemonIcon(((PokemonData) cmbPokemon.getSelectedItem()).getID()));
	}
	
	private void setPanels(){
		this.getContentPane().add(new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = -1535519037381253670L;

			{
				this.setLayout(new BorderLayout());
				this.setBorder(new TitledBorder("Pokemon"));
				
				this.add(pkmIconLabel, BorderLayout.WEST);
				this.add(new JPanel(){
					private static final long serialVersionUID = 1L;
					{	
						this.setLayout(new GridLayout(3,1));
						this.add(cmbPokemon);
						this.add(lblPokemonID);
					}
				}, BorderLayout.CENTER);
			}
		}, BorderLayout.NORTH);
		this.getContentPane().add(new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 5220284243873950134L;

			{
				this.setLayout(new GridLayout(4,2));
				this.add(new JLabel("Nature"));
				this.add(cmbNature);
				this.add(new JLabel("Ability"));
				this.add(cmbAbility);
				this.add(new JLabel("Level"));
				this.add(txtLevel);
				this.add(new JLabel("Held Item"));
				this.add(cmbHeldItem);
			}
		}, BorderLayout.CENTER);
		this.getContentPane().add(new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = -1063330546910324861L;

			{
				
				this.setLayout(new BorderLayout());
				
				this.add(new JPanel(){
					{
						this.setBorder(new TitledBorder("IV"));
						this.setLayout(new GridLayout(6,2));
						this.add(new JLabel("HP  "));
						this.add(txtHP);
						this.add(new JLabel("Attack  "));
						this.add(txtAtk);
						this.add(new JLabel("Defense  "));
						this.add(txtDef);
						this.add(new JLabel("Sp. Atk  "));
						this.add(txtSpa);
						this.add(new JLabel("Sp. Def  "));
						this.add(txtSpd);
						this.add(new JLabel("Speed  "));
						this.add(txtSpe);
					}
				}, BorderLayout.WEST);
				
				this.add(new JPanel(){
					{
						this.setBorder(new TitledBorder("Moves"));
						this.setLayout(new GridLayout(4,1));
						this.add(cmbMove1);
						this.add(cmbMove2);
						this.add(cmbMove3);
						this.add(cmbMove4);
					}
				}, BorderLayout.CENTER);
				
				this.add(new JPanel(){
					{
						this.setLayout(new GridLayout(1,2));
						
						JButton acceptButton = new JButton("Accept");
						acceptButton.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent arg0) {
								
								//Sets Level to 1 and IVs to 0 default if empty text area
								if (txtHP.isEmpty()) txtHP.setText("0");
								if (txtAtk.isEmpty()) txtAtk.setText("0");
								if (txtDef.isEmpty()) txtDef.setText("0");
								if (txtSpa.isEmpty()) txtSpa.setText("0");
								if (txtSpd.isEmpty()) txtSpd.setText("0");
								if (txtSpe.isEmpty()) txtSpe.setText("0");
								if (txtLevel.isEmpty()) txtLevel.setText("1");
								if (Integer.parseInt(txtLevel.getText()) == 0) txtLevel.setText("1");
								
								//If lvl > 100, error
								if (Integer.parseInt(txtLevel.getText()) > 100)
									{
										JOptionPane.showMessageDialog(PokemonWindow.this, "Level can't be higher than 100.", "Error", JOptionPane.ERROR_MESSAGE);
										return;
									}
								
								//If IV > 31, error
								if (Integer.parseInt(txtHP.getText()) > 31 ||
									Integer.parseInt(txtAtk.getText()) > 31 ||
									Integer.parseInt(txtDef.getText()) > 31 ||
									Integer.parseInt(txtSpa.getText()) > 31 ||
									Integer.parseInt(txtSpd.getText()) > 31 ||
									Integer.parseInt(txtSpe.getText()) > 31)
								{
									JOptionPane.showMessageDialog(PokemonWindow.this, "IVs can't be higher than 31.", "Error", JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								//If ID = 0, it means there's no pokemon
								if (((PokemonData)cmbPokemon.getSelectedItem()).getID() == 0){
									pokemon = new Pokemon();
									PokemonWindow.this.dispose();
									return;
								}
								
								//Ability (none)
								if (((Ability)cmbAbility.getSelectedItem()).getID() == 0){
									JOptionPane.showMessageDialog(PokemonWindow.this, "Select an ability.", "Error", JOptionPane.ERROR_MESSAGE);
									return;
								}
								
								pokemon = new Pokemon(
									((PokemonData)cmbPokemon.getSelectedItem()).getID(),
									Integer.parseInt(txtLevel.getText()),
									new StatPack(
											Integer.parseInt(txtHP.getText()),
											Integer.parseInt(txtAtk.getText()),
											Integer.parseInt(txtDef.getText()),
											Integer.parseInt(txtSpa.getText()),
											Integer.parseInt(txtSpd.getText()),
											Integer.parseInt(txtSpe.getText())),
									new StatPack(0),
									(Nature)cmbNature.getSelectedItem(),
									(Ability)cmbAbility.getSelectedItem(),
									(PokemonItem)cmbHeldItem.getSelectedItem(),
									false);
								pokemon.setMoves(
									(Move)cmbMove1.getSelectedItem(),
									(Move)cmbMove2.getSelectedItem(),
									(Move)cmbMove3.getSelectedItem(),
									(Move)cmbMove4.getSelectedItem());
								
								PokemonWindow.this.dispose();							
							}
						});
						this.add(acceptButton);
						
						JButton cancelButton = new JButton("Cancel");
						cancelButton.addActionListener(new ActionListener(){
							@Override
							public void actionPerformed(ActionEvent e) {
								PokemonWindow.this.dispose();
							}
						});
						this.add(cancelButton);
					}
				}, BorderLayout.SOUTH);
			}
		}, BorderLayout.SOUTH);
		this.pack();
	}
	
	public Pokemon showDialog() {
		setVisible(true);
		return this.pokemon;
	}
		
	private void updateAbilityModel(int n){
		if (n == 0){
			this.cmbAbility.setModel(new DefaultComboBoxModel(DataListUtil.abilityList));
			return;
		}
		
		final int _NUMROWS = 1751;
		int imin = n-1, imax = n*3, imid = 0;
		int selectedID = 0;
		String[] data;
		CSVFileReader fileReader = new CSVFileReader();
		List<Ability> list = new ArrayList<Ability>();
		
		if (imax >= _NUMROWS) imax = _NUMROWS - 1;
		while(imax >= imin){
			imid = (imin + imax) / 2;
			data = fileReader.getLine(imid, FileUtil.ABILITIES_BY_PKM);
			selectedID = Integer.parseInt(data[0]);
			if (selectedID == n) break;
			else if (selectedID < n) imin = imid + 1;
			else imax = imid - 1;
		}
		while (selectedID == n && imid >= 0){
			imid = imid - 1;
			data = fileReader.getLine(imid, FileUtil.ABILITIES_BY_PKM);
			selectedID = Integer.parseInt(data[0]);
		}
		imid = imid + 1;
		data = fileReader.getLine(imid, FileUtil.ABILITIES_BY_PKM);
		selectedID = Integer.parseInt(data[0]);
		while (selectedID == n && imid < _NUMROWS){
			if (Integer.parseInt(data[2]) == 0 && Integer.parseInt(data[1]) < 165) //if it's non hidden ability & it's not gen 6
			list.add(new Ability(Integer.parseInt(data[1])));
			imid = imid + 1;
			data = fileReader.getLine(imid, FileUtil.ABILITIES_BY_PKM);
			selectedID = Integer.parseInt(data[0]);
		}
		this.cmbAbility.setModel(new DefaultComboBoxModel(list.toArray()));
	}
	
	private JLabel pkmIconLabel;
	private JComboBox<PokemonData> cmbPokemon;
	private JComboBox<Nature> cmbNature;
	private JComboBox<Ability> cmbAbility;
	private JComboBox<PokemonItem> cmbHeldItem;
	private JComboBox<Move> cmbMove1;
	private JComboBox<Move> cmbMove2;
	private JComboBox<Move> cmbMove3;
	private JComboBox<Move> cmbMove4;
	private IntegerJTextField txtLevel;
	private IntegerJTextField txtHP;
	private IntegerJTextField txtAtk;
	private IntegerJTextField txtDef;
	private IntegerJTextField txtSpa;
	private IntegerJTextField txtSpd;
	private IntegerJTextField txtSpe;
	private JLabel lblPokemonID;
	private Pokemon pokemon;
	private Window parent;
	private PokemonWindowType type;
}
