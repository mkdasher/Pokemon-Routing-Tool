package routingtool.gui;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.Box;
import javax.swing.JButton;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextArea;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;

import routingtool.Controller;
import routingtool.components.EmptyEvent;
import routingtool.components.Event;
import routingtool.components.PokemonTeam;
import routingtool.components.SingleTrainerBattle;
import routingtool.gui.components.PokemonIcon;
import routingtool.gui.components.PokemonTypeLabel;
import routingtool.observers.EventListContainerObserverHelper;
import routingtool.pokemon.Pokemon;
import routingtool.pokemon.data.PokemonType;
import routingtool.pokemon.data.StatPack;

public class EventInfo extends JPanel{
	private static final long serialVersionUID = 9026191378428306639L;
	public EventInfo(Controller c){
		this.c = c;
		this.currentEvent = new EmptyEvent();
		this.currentPokemon = new Pokemon();
		this.setParams();
		c.addEventListContainerObserver(new EventListContainerObserverHelper(){
			
			@Override
			public void eventSelectionChange(Event current){
				currentEvent = current;
				currentPokemon = currentEvent.getParty().getListAfter().getPokemon(0);
				pkmPartyPane.setSelectedComponent(pkmPartyAfter);
				EventInfo.this.updateCurrentEvent();
				EventInfo.this.updateCurrentParty();
				EventInfo.this.updateCurrentPokemon();
			}
			
		});
	}
	
	private void setParams(){
		this.eventInfoPanel = new JPanel();
		this.eventInfoPanel.setBorder(new TitledBorder("Event Info"));
		
		//Pokemon Party
		this.pkmPartyPane = new JTabbedPane();
		this.pkmPartyBefore = new JPanel();
		this.pkmPartyBefore.setLayout(new GridLayout(2,3));
		this.pkmPartyAfter = new JPanel();
		this.pkmPartyAfter.setLayout(new GridLayout(2,3));
		this.pkmPBeforeButton = new EInfoPButton[PokemonTeam.MAX_PKM];
		this.pkmPAfterButton = new EInfoPButton[PokemonTeam.MAX_PKM];
		for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
			this.pkmPBeforeButton[i] = new EInfoPButton();
			this.pkmPAfterButton[i] = new EInfoPButton();
			this.pkmPartyBefore.add(pkmPBeforeButton[i]);
			this.pkmPartyAfter.add(pkmPAfterButton[i]);
		}
		this.pkmPartyPane.addTab("Before", pkmPartyBefore);
		this.pkmPartyPane.addTab("After", pkmPartyAfter);
		this.pkmPartyPane.getTabComponentAt(0);
		
		//Current Pokemon
		this.pkmIconLabel = new JLabel(new PokemonIcon(1));
		this.pkmIconLabel.setPreferredSize(new Dimension(64,64));
		this.pkmIconLabel.setBorder(BorderFactory.createLineBorder(Color.black));
		this.pkmIconLabel.setOpaque(true);
		
		this.pkmTypeLabel1 = new PokemonTypeLabel(new PokemonType(2));
		this.pkmTypeLabel2 = new PokemonTypeLabel(new PokemonType(4));
		
		this.lblPokemonName = new JLabel();
		this.lblPokemonName.setBackground(Color.WHITE);
		this.lblPokemonName.setHorizontalAlignment(SwingConstants.CENTER);
		this.lblPokemonName.setBorder(BorderFactory.createLineBorder(Color.black));
		this.lblPokemonName.setFont(new Font("Leelawadee", Font.BOLD, 14));
		this.lblPokemonName.setOpaque(true);
		this.lblPokemonName.setBackground(Color.WHITE);
		
		this.txtExperience = new JTextArea();
		this.txtExperience.setEditable(false);
		this.txtLevel = new JTextArea();
		this.txtLevel.setEditable(false);
		this.txtHeldItem = new JTextArea();
		this.txtHeldItem.setEditable(false);
		this.txtNature = new JTextArea();
		this.txtNature.setEditable(false);
		this.txtAbility = new JTextArea();
		this.txtAbility.setEditable(false);
		
		this.txtEV = new JTextArea[StatPack.STAT_N];
		this.txtIV = new JTextArea[StatPack.STAT_N];
		this.txtStat = new JTextArea[StatPack.STAT_N];
		for (int i = 0; i < StatPack.STAT_N; i++){
			this.txtEV[i] = new JTextArea();
			this.txtEV[i].setEditable(false);
			this.txtIV[i] = new JTextArea();
			this.txtIV[i].setEditable(false);
			this.txtStat[i] = new JTextArea();
			this.txtStat[i].setEditable(false);
		}
		
		this.txtMove = new JTextArea[4];
		for (int i = 0; i < 4; i++){
			this.txtMove[i] = new JTextArea();
			this.txtMove[i].setEditable(false);
		}
		
		//Components
		this.setBorder(new TitledBorder("Event"));
		this.setLayout(new BorderLayout());
		
		this.add(new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = -8768910101757685005L;

			{
				this.setLayout(new BorderLayout());
				this.add(eventInfoPanel, BorderLayout.CENTER);
				this.add(new JPanel(){
					{
					this.setBorder(new TitledBorder("Pokemon Party"));
						this.add(pkmPartyPane);
					}
				}, BorderLayout.SOUTH);
			}
		}, BorderLayout.WEST);
		
		this.add(new JPanel(){
			/**
			 * 
			 */
			private static final long serialVersionUID = 7133290352265769708L;

			{
			this.setLayout(new BorderLayout());
			this.setBorder(new TitledBorder("Selected Pokemon"));
			this.add(new JPanel(){
				{
					this.setLayout(new GridLayout(1,0));
					this.add(Box.createGlue());
					this.add(new JPanel(){
						{
							this.setLayout(new BorderLayout());
							this.add(pkmIconLabel, BorderLayout.NORTH);
							this.add(lblPokemonName, BorderLayout.CENTER);
							this.add(new JPanel(){
								{
									this.setBorder(BorderFactory.createLineBorder(Color.black));
									this.setOpaque(true);
									this.setBackground(Color.WHITE);
									this.setLayout(new GridLayout(1,0));
									this.add(pkmTypeLabel1, BorderLayout.WEST);
									pkmTypeLabel1.setHorizontalAlignment(SwingConstants.CENTER);
									this.add(pkmTypeLabel2, BorderLayout.EAST);
									pkmTypeLabel2.setHorizontalAlignment(SwingConstants.CENTER);
									pkmTypePanel = this;
								}
							}, BorderLayout.SOUTH);
						}
					});
					this.add(Box.createGlue());
				}
			}, BorderLayout.NORTH);
			this.add(new JPanel(){
				{
					this.setLayout(new BorderLayout());
					this.add(new JPanel(){
						{
							this.setLayout(new GridLayout(5,2));
							this.add(new JLabel("Level"){{this.setHorizontalAlignment(SwingConstants.CENTER);}});
							this.add(txtLevel);
							this.add(new JLabel("Held Item"){{this.setHorizontalAlignment(SwingConstants.CENTER);}});
							this.add(txtHeldItem);
							this.add(new JLabel("Experience"){{this.setHorizontalAlignment(SwingConstants.CENTER);}});
							this.add(txtExperience);
							this.add(new JLabel("Ability"){{this.setHorizontalAlignment(SwingConstants.CENTER);}});
							this.add(txtAbility);
							this.add(new JLabel("Nature"){{this.setHorizontalAlignment(SwingConstants.CENTER);}});
							this.add(txtNature);
						}
					}, BorderLayout.NORTH);
					this.add(new JPanel(){
						{
							this.setLayout(new GridLayout(7,4));
							this.setBorder(new TitledBorder("Stats"));
							this.add(new JPanel());
							this.add(new JLabel("Stat"){{this.setHorizontalAlignment(SwingConstants.CENTER);}});
							this.add(new JLabel("IV"){{this.setHorizontalAlignment(SwingConstants.CENTER);}});
							this.add(new JLabel("EV"){{this.setHorizontalAlignment(SwingConstants.CENTER);}});
							for (int i = 0; i < StatPack.STAT_N; i++){
								switch(i){
								case 0: this.add(new JLabel("HP"){{this.setHorizontalAlignment(SwingConstants.CENTER);}});
								break;
								case 1: this.add(new JLabel("Attack"){{this.setHorizontalAlignment(SwingConstants.CENTER);}});
								break;
								case 2: this.add(new JLabel("Defense"){{this.setHorizontalAlignment(SwingConstants.CENTER);}});
								break;
								case 3: this.add(new JLabel("Sp. atk"){{this.setHorizontalAlignment(SwingConstants.CENTER);}});
								break;
								case 4: this.add(new JLabel("Sp. def"){{this.setHorizontalAlignment(SwingConstants.CENTER);}});
								break;
								default: this.add(new JLabel("Speed"){{this.setHorizontalAlignment(SwingConstants.CENTER);}});
								break;
								}
								this.add(txtStat[i]);
								this.add(txtIV[i]);	
								this.add(txtEV[i]);	
							}
						}
					}, BorderLayout.CENTER);
				}
			}, BorderLayout.CENTER);
			this.add(new JPanel(){
				{
					this.setLayout(new GridLayout(4,2));
					this.add(new JLabel("Move 1"));
					this.add(txtMove[0]);
					this.add(new JLabel("Move 2"));
					this.add(txtMove[1]);
					this.add(new JLabel("Move 3"));
					this.add(txtMove[2]);
					this.add(new JLabel("Move 4"));
					this.add(txtMove[3]);
				}
			}, BorderLayout.SOUTH);
			}
		}, BorderLayout.CENTER);
		this.updateCurrentParty();
		this.updateCurrentPokemon();
	}
	
	private void updateCurrentEvent(){
		while (this.eventInfoPanel.getComponentCount() > 0){
			this.eventInfoPanel.remove(0);
		}
		switch (this.currentEvent.getEventType()){
		case SingleTrainerBattle:
			this.eventInfoPanel.add(new JPanel(){
				/**
				 * 
				 */
				private static final long serialVersionUID = 2245404665999799484L;

				{
					final SingleTrainerBattle stb = (SingleTrainerBattle) currentEvent;
					this.setLayout(new BorderLayout());
					this.add(new JPanel(){
						{
							this.setLayout(new BorderLayout());
							this.setBorder(new TitledBorder("Trainer"));
							this.add(new JPanel(){
								{
									this.setLayout(new GridLayout(1,2));
									this.add(new JLabel("Name"));
									this.add(new JTextArea(){
										{
											this.setText(stb.getTrainer().getName());
											this.setEditable(false);
										}
									});
								}
							}, BorderLayout.NORTH);
							this.add(new JPanel(){
								{
									this.setLayout(new GridLayout(2,3));
									for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
										this.add(new EInfoPButton(stb.getTrainer().getPokemonTeam().getPokemon(i)));
									}
								}
							}, BorderLayout.CENTER);
						}
					}, BorderLayout.CENTER);
					this.add(new JPanel(){
						{
							this.setBorder(new TitledBorder("Money"));
							this.setLayout(new GridLayout(1,2));
							this.add(new JLabel("Money"));
							this.add(new JTextArea(){
								{
									this.setText(String.valueOf(stb.getMoney()));
									this.setEditable(false);
								}
							});
						}
					}, BorderLayout.SOUTH);
				}
			});
			break;
		case DoubleTrainerBattle:
			
			break;
		case PokemonObtained:
			
			break;
		default:
			break;
		}
	}
	
	private void updateCurrentParty(){
		for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
			this.pkmPBeforeButton[i].setPokemon(currentEvent.getParty().getListBefore().getPokemon(i));
			this.pkmPAfterButton[i].setPokemon(currentEvent.getParty().getListAfter().getPokemon(i));
		}
	}
	
	private void updateCurrentPokemon(){
		this.pkmIconLabel.setIcon(new PokemonIcon(currentPokemon.getBaseData().getID()));
		this.lblPokemonName.setText(currentPokemon.getBaseData().getName());
		if (currentPokemon.getBaseData().getID() == 0) this.lblPokemonName.setText(" ");
		while(pkmTypePanel.getComponentCount() > 0){
			this.pkmTypePanel.remove(0);
		}
		this.pkmTypeLabel1.setType(currentPokemon.getBaseData().type1);
		this.pkmTypePanel.add(pkmTypeLabel1);
		this.pkmTypeLabel2.setType(currentPokemon.getBaseData().type2);
		if (!currentPokemon.getBaseData().type2.isNone())
			this.pkmTypePanel.add(pkmTypeLabel2);
		for (int i = 0; i < StatPack.STAT_N; i++){
			this.txtEV[i].setText(String.valueOf(currentPokemon.EV.getStat(i)));
			this.txtIV[i].setText(String.valueOf(currentPokemon.IV.getStat(i)));
			this.txtStat[i].setText(String.valueOf(currentPokemon.stat.getStat(i)));
		}
		this.txtLevel.setText(String.valueOf(currentPokemon.getLevel()));
		this.txtHeldItem.setText(String.valueOf(currentPokemon.getHeldItem()));
		this.txtExperience.setText(String.valueOf(currentPokemon.getExperience()));
		this.txtNature.setText(currentPokemon.getNature().toString());
		this.txtAbility.setText(currentPokemon.getAbility().toString());
		for (int i = 0; i < 4; i++){
			this.txtMove[i].setText(currentPokemon.getMove(i).toString());
		}
	}
	
	private class EInfoPButton extends JButton{
		/**
		 * 
		 */
		private static final long serialVersionUID = -6034073052180921184L;
		private EInfoPButton(){
			super();
			this.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					currentPokemon = EInfoPButton.this.pkm;
					updateCurrentPokemon();
				}
			});
		}
		private EInfoPButton(Pokemon pkm){
			super();
			this.addActionListener(new ActionListener(){
				@Override
				public void actionPerformed(ActionEvent arg0) {
					currentPokemon = EInfoPButton.this.pkm;
					updateCurrentPokemon();
				}
			});
			this.setPokemon(pkm);
		}
		public void setPokemon(Pokemon pkm){
			this.pkm = pkm;
			this.setIcon(new PokemonIcon(this.pkm.getBaseData().getID()));
			if (this.pkm.getBaseData().getID() == 0){
				this.setEnabled(false);
				this.setVisible(false);
			}
			else{
				this.setEnabled(true);
				this.setVisible(true);
			}
		}
		private Pokemon pkm;
	}
	
	private Controller c;
	
	private Event currentEvent;
	
	//Party
	private JTabbedPane pkmPartyPane;
	private JPanel pkmPartyBefore;
	private JPanel pkmPartyAfter;
	private EInfoPButton pkmPBeforeButton[];
	private EInfoPButton pkmPAfterButton[];
	
	//Current Pokemon
	private Pokemon currentPokemon;
	private JTextArea txtLevel;
	private JTextArea txtHeldItem;
	private JTextArea txtExperience;
	private JTextArea txtNature;
	private JTextArea txtAbility;
	private JPanel pkmTypePanel;
	private JPanel eventInfoPanel;
	private PokemonTypeLabel pkmTypeLabel1;
	private PokemonTypeLabel pkmTypeLabel2;
	private JLabel pkmIconLabel;
	private JLabel lblPokemonName;
	private JTextArea txtStat[];
	private JTextArea txtIV[];
	private JTextArea txtEV[];
	private JTextArea txtMove[];
}
