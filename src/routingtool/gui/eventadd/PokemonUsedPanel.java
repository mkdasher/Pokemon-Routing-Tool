package routingtool.gui.eventadd;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseMotionAdapter;

import javax.swing.BorderFactory;
import javax.swing.BoxLayout;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.TitledBorder;

import routingtool.components.Event;
import routingtool.components.PokemonTeam;
import routingtool.components.PokemonUsed;
import routingtool.components.PokemonUsedList;
import routingtool.components.Trainer;

public class PokemonUsedPanel extends JPanel{
	public PokemonUsedPanel(PokemonTeam myParty){
		this.setBorder(new TitledBorder("Pokemon Used"));
		this.setLayout(new GridLayout(N_ROWS,1));
		this.row = new PokemonUsedPanelRow[N_ROWS];
		for (int i = 0; i < N_ROWS; i++){
			this.row[i] = new PokemonUsedPanelRow(myParty, (i / PokemonTeam.MAX_PKM) + 1, i % PokemonTeam.MAX_PKM);
			this.row[i].setVisible(true);
			this.row[i].setPreferredSize(new Dimension(0,0));
			handleDrag(this.row[i]);
		}
	}
	
	JPanel curPanel;
	int curPanelY;
	int curPanelNo;
	
	public void handleDrag(final JPanel panel){
		  panel.addMouseListener(new MouseAdapter() {
		        @Override
		        public void mousePressed(MouseEvent me) {
		        	 curPanelY = me.getY();
		             Object[] c = PokemonUsedPanel.this.getComponents();
		             curPanelNo = 0;
		             for (int i = 0; i < c.length; i++){
		            	 if (c[i] == panel){
		            		 curPanelNo = i;
		            	 }
		             }
		             curPanel = (JPanel) PokemonUsedPanel.this.getComponent(curPanelNo);
		             curPanel.setBorder(BorderFactory.createLineBorder(Color.ORANGE));
		        }
		        @Override
		        public void mouseReleased(MouseEvent me){
		        	curPanel.setBorder(BorderFactory.createLineBorder(Color.BLACK));
		        	PokemonUsedPanel.this.revalidate();
		        }
		    });

		    panel.addMouseMotionListener(new MouseMotionAdapter() {
		        @Override
		        public void mouseDragged(MouseEvent me) {
		            me.translatePoint(0, me.getComponent().getLocation().y - curPanelY);
		            int temp;
		            if((int) ((me.getY() - curPanelY) / me.getComponent().getSize().height) < 0){
		                temp = 0;
		            }
		            else if((int) ((me.getY() - curPanelY) /  me.getComponent().getSize().height) < (PokemonUsedPanel.this.getComponentCount() - 1)){
		                temp = (me.getY() - curPanelY) /  me.getComponent().getSize().height;
		            }
		            else{
		            	temp = (PokemonUsedPanel.this.getComponentCount() - 1);
		            }
		            PokemonUsedPanel.this.remove(curPanel);
		            PokemonUsedPanel.this.add(curPanel, temp);
		            PokemonUsedPanel.this.revalidate();
		        }
		    });
	}
	
	public PokemonUsedList getPokemonUsedList(){
		PokemonUsedList pudl = new PokemonUsedList();
		for (int i = 0; i < this.getComponentCount(); i++){
			pudl.add(((PokemonUsedPanelRow)this.getComponent(i)).getPokemonUsed());
		}
		return pudl;
	}
	
	public void updateComponents(Trainer trainer, int trainerID){
		int amount = trainer.getPokemonTeam().size();
		boolean isAlreadyUsed[]  = new boolean[amount];
		for (int i = 0; i < amount; i++){
			isAlreadyUsed[i] = false;
		}
		for (int i = this.getComponentCount() - 1; i >= 0; i--){
			PokemonUsedPanelRow pupr = (PokemonUsedPanelRow) this.getComponent(i);
			if (pupr.getTrainerID() == trainerID){
				if (pupr.getIndex() >= trainer.getPokemonTeam().size()){
					this.remove(pupr);
				}
				else{
					isAlreadyUsed[pupr.getIndex()] = true;
				}
			}
		}
		for (int i = 0; i < amount; i++){
			int rowIndex = i + (trainerID - 1) * PokemonTeam.MAX_PKM;
			if (!isAlreadyUsed[i]){
				this.add(this.row[rowIndex]);
			}
			this.row[rowIndex].setFoePokemon(trainer.getPokemonTeam().getPokemon(i));
		}
	}
	
	public void updateComponents(PokemonUsedList pul){
		while(this.getComponentCount() > 0){
			this.remove(0);
		}
		for (int i = 0; i < pul.size(); i++){
			PokemonUsed pu = pul.get(i);
			int rowIndex = pu.getPokemonSlot() + (pu.getTrainerID() - 1) * PokemonTeam.MAX_PKM;
			this.row[rowIndex].updateComponents(pu);
			this.add(this.row[rowIndex]);
		}
	}
	
	private PokemonUsedPanelRow[] row;
	private static final int N_ROWS = 12;
}
