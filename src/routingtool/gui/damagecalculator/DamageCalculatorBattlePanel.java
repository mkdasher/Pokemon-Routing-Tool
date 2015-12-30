package routingtool.gui.damagecalculator;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.FontFormatException;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;

import javax.swing.ImageIcon;
import javax.swing.JPanel;

import routingtool.components.PokemonTeam;
import routingtool.gui.ToolBar;
import routingtool.pokemon.Pokemon;
import routingtool.pokemon.data.Move;
import routingtool.pokemon.data.PokemonType;

public class DamageCalculatorBattlePanel extends JPanel{
	
	public DamageCalculatorBattlePanel(DamageCalculatorDialog dcd){
		this.dcd = dcd;
		this.initRegions();
		this.setParams();
	}
	
	private void initRegions(){
		this.myUsed = new PokemonUsedType[PokemonTeam.MAX_PKM];
		this.foeUsed = new PokemonUsedType[PokemonTeam.MAX_PKM];
		
		this.MOVEPANEL = new Image[Pokemon.MOVE_AMOUNT];
		for (int i = 0; i < Pokemon.MOVE_AMOUNT; i++){
			setMove(new Move(0), i);
		}
		setOwnSprite(0);
		setFoeSprite(0);
		this.MYPOKEMON = new Image[PokemonTeam.MAX_PKM];
		this.FOEPOKEMON = new Image[PokemonTeam.MAX_PKM];
		
		this.BG1_Reg = new Region(0,0,panelwidth, panelheight);
		this.MAINBOXES_Reg = new Region(0,0,panelwidth, panelheight);
		this.TEXTBOX_Reg = new Region(0, panelheight - TEXTBOX.getHeight(this), TEXTBOX.getWidth(this), TEXTBOX.getHeight(this));	
		this.BG2_Reg = new Region(0,0,panelwidth, panelheight);
		this.MOVEPANEL_Reg = new Region[Pokemon.MOVE_AMOUNT];
		this.MOVEPANEL_Reg[0] = new Region(2, 25, 124, 54);
		this.MOVEPANEL_Reg[1] = new Region(130, 25, 124, 54);
		this.MOVEPANEL_Reg[2] = new Region(2, 89, 124, 54);
		this.MOVEPANEL_Reg[3] = new Region(130, 89, 124, 54);
		this.EXPBAR_Reg = new Region(152, 135, MAX_BAR_SIZE, 2);
		this.MYPOKEMON_Reg = new Region[PokemonTeam.MAX_PKM];
		this.FOEPOKEMON_Reg = new Region[PokemonTeam.MAX_PKM];
		for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
			this.MYPOKEMON_Reg[i] = new Region(4 + 19*i,5,16,16);
			this.FOEPOKEMON_Reg[i] = new Region(180 + 12*i, 4, 10,10);
			setMyUsed(PokemonUsedType.None, i);
			setFoeUsed(PokemonUsedType.None, i);
		}
		this.SWITCHPOKEMON_Reg = new Region(112,155,32,32);
		this.OWN_PKM_SPRITE_Reg = new Region(20,80,96,96);
		this.FOE_PKM_SPRITE_Reg = new Region(145,20,96,96);
	}
	
	private void setParams(){
		InputStream is1 = DamageCalculatorBattlePanel.class.getResourceAsStream("/font/PokemonDPPt.TTF");
		InputStream is2 = DamageCalculatorBattlePanel.class.getResourceAsStream("/font/Pokedex.TTF");
		try {
			this.font1 = Font.createFont(Font.TRUETYPE_FONT, is1);
			this.font1 = font1.deriveFont(16f);
		} catch (Exception e) {
			this.font1 = new Font("serif", Font.PLAIN, 12);
		}
		try {
			this.font2 = Font.createFont(Font.TRUETYPE_FONT, is2);
			this.font2 = font2.deriveFont(8f);
		} catch (Exception e) {
			this.font2 = new Font("serif", Font.PLAIN, 12);
		}
		this.topPanel = new JPanel(){
				@Override
				public void paint(Graphics g) {
					super.paint(g);
					drawImage(BG1, BG1_Reg, this, g);
					drawImage(OWN_PKM_SPRITE, OWN_PKM_SPRITE_Reg, this, g);
					drawImage(FOE_PKM_SPRITE, FOE_PKM_SPRITE_Reg, this, g);
					drawImage(MAINBOXES, MAINBOXES_Reg, this, g);
					drawImage(EXPBAR, EXPBAR_Reg, this, g);
					drawImage(TEXTBOX, TEXTBOX_Reg, this, g);
					g.setFont(font1);
					g.setColor(new Color(0x000000));
					g.drawString(dcd.getMyPokemon().getBaseData().getName().toUpperCase(), 152, 113);
					g.drawString(dcd.getFoePokemon().getBaseData().getName().toUpperCase(), 2, 37);
					g.drawString(battleMessage, 16, 165);
					g.setFont(font2);
					g.setColor(new Color(0x4a4a4a));
					
					g.drawString(String.valueOf(dcd.getMyPokemon().getLevel()), 232, 110);
					g.drawString(String.valueOf(dcd.getFoePokemon().getLevel()), 82, 34);
					
					int hp = dcd.getMyPokemon().stat.hp;
					int str_hp_x = 190;
					if (hp < 10) str_hp_x += 8;
					if (hp < 100) str_hp_x += 8;
					g.drawString(String.valueOf(hp) + "/" + String.valueOf(hp), str_hp_x, 130);
				}
		};
		this.topPanel.setPreferredSize(new Dimension(panelwidth, panelheight));
		this.bottomPanel = new JPanel(){
			{
				this.addMouseListener(new MouseListener(){
					@Override
					public void mouseClicked(MouseEvent me) {
						int x = me.getX();
						int y = me.getY();
						for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
							if (MYPOKEMON_Reg[i].containsPoint(x, y)){
								if (myUsed[i] == PokemonUsedType.Used){
									dcd.changeMyPokemon(i);
								}
								break;
							}
							else if (FOEPOKEMON_Reg[i].containsPoint(x, y)){
								if (foeUsed[i] != PokemonUsedType.None){
									dcd.changeFoePokemon(i);
								}
								break;
							}
						}
						if (SWITCHPOKEMON_Reg.containsPoint(x, y)){
							dcd.switchPokemonTeam();
						}
						for (int i = 0; i < Pokemon.MOVE_AMOUNT; i++){
							if (MOVEPANEL_Reg[i].containsPoint(x, y)){
								dcd.useMove(i);
								break;
							}
						}
						DamageCalculatorBattlePanel.this.revalidate();
						DamageCalculatorBattlePanel.this.repaint();
					}
					@Override
					public void mouseEntered(MouseEvent me) {}
					@Override
					public void mouseExited(MouseEvent me) {}
					@Override
					public void mousePressed(MouseEvent me) {}
					@Override
					public void mouseReleased(MouseEvent me) {}	
				});
			}
			@Override
			public void paint(Graphics g) {
				super.paint(g);
				drawImage(BG2, BG2_Reg, this, g);
				g.setFont(font1);
				g.setColor(new Color(0x000000));
				for (int i = 0; i < MOVEPANEL_Reg.length; i++){
					Move move = dcd.getMyPokemon().getMove(i);
					drawImage(MOVEPANEL[i], MOVEPANEL_Reg[i], this, g);
					if (!move.isNone()){
						FontMetrics metrics = g.getFontMetrics(font1);
						String moveName = move.getName();
						int x = MOVEPANEL_Reg[i].getX() + (MOVEPANEL_Reg[i].getWidth() - metrics.stringWidth(moveName)) / 2;
						int y = MOVEPANEL_Reg[i].getY() + 23;
						g.drawString(moveName, x, y);
					}
				}
				for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
					drawImage(MYPOKEMON[i], MYPOKEMON_Reg[i], this, g);
					drawImage(FOEPOKEMON[i], FOEPOKEMON_Reg[i], this, g);
				}
				drawImage(SWITCHPOKEMON, SWITCHPOKEMON_Reg, this, g);
			}
		};
		this.setLayout(new GridLayout(2,1));
		this.bottomPanel.setPreferredSize(new Dimension(panelwidth, panelheight));
		this.add(topPanel);
		this.add(bottomPanel);
	}
	
	public void setMove(Move move, int i){
		String path = "/image/damagecalculator/movepanel/";
		if (move.isNone()){
			path += "empty";
		}
		else{
			path += move.getMoveType().getName().toLowerCase();
		}
		path += "panel.png";
		this.MOVEPANEL[i] = new ImageIcon(DamageCalculatorBattlePanel.class.getResource(path)).getImage();
	}
	
	public void setOwnSprite(int n){
		this.OWN_PKM_SPRITE = new ImageIcon(DamageCalculatorBattlePanel.class.getResource("/image/pokemon/back/" + n + ".png")).getImage();
	}
	
	public void setFoeSprite(int n){
		this.FOE_PKM_SPRITE = new ImageIcon(DamageCalculatorBattlePanel.class.getResource("/image/pokemon/" + n + ".png")).getImage();
	}
	
	/**
	 * Sets exp bar size according to a number n that goes from 0 to MAX_BAR_SIZE - 1
	 * @param n
	 */
	public void setExpBarSize(int n){
		this.EXPBAR_Reg = new Region(152, 135, n, 2);
	}
		
	public void drawImage(Image image, Region r, ImageObserver observer, Graphics g){
		g.drawImage(image, Math.round(r.getX()), Math.round(r.getY()), Math.round(r.getWidth()), Math.round(r.getHeight()), observer);
	}
	
	public void setBattleMessage(String s){
		this.battleMessage = s;
	}
	
	
	public void setMyUsed(PokemonUsedType put, int i){
		this.myUsed[i] = put;
		switch(put){
		case Used: this.MYPOKEMON[i] = this.MYPOKEMON_USED; break;
		case Unused: this.MYPOKEMON[i] = this.MYPOKEMON_UNUSED; break;
		case None: this.MYPOKEMON[i] = this.MYPOKEMON_NONE; break;
		}
	}
	public void setFoeUsed(PokemonUsedType put, int i){
		this.foeUsed[i] = put;
		switch(put){
		case Used: this.FOEPOKEMON[i] = this.FOEPOKEMON_USED; break;
		case Unused: this.FOEPOKEMON[i] = this.FOEPOKEMON_UNUSED; break;
		case None: this.FOEPOKEMON[i] = this.FOEPOKEMON_NONE; break;
		}
	}
	public enum PokemonUsedType{
		Used, Unused, None;
	}
	public PokemonUsedType myUsed[];
	public PokemonUsedType foeUsed[];
	
	private DamageCalculatorDialog dcd;
	
	private JPanel topPanel;
	private JPanel bottomPanel;
	private int foePokemonIndex = 0;
	private int myPokemonIndex = 0;
	private int panelwidth = 256;
	private int panelheight = 192;
	
	private String battleMessage;
	
	private Image BG1 = new ImageIcon(DamageCalculatorBattlePanel.class.getResource("/image/damagecalculator/BG1.png")).getImage();
	private Image BG2 = new ImageIcon(DamageCalculatorBattlePanel.class.getResource("/image/damagecalculator/BG2.png")).getImage();
	private Region BG1_Reg, BG2_Reg;
	private Image EXPBAR = new ImageIcon(DamageCalculatorBattlePanel.class.getResource("/image/damagecalculator/expbar.png")).getImage();
	private Region EXPBAR_Reg;
	private Image MAINBOXES = new ImageIcon(DamageCalculatorBattlePanel.class.getResource("/image/damagecalculator/mainPanelBoxes.png")).getImage();
	private Region MAINBOXES_Reg;
	private Image TEXTBOX = new ImageIcon(DamageCalculatorBattlePanel.class.getResource("/image/damagecalculator/textbox.png")).getImage();
	private Region TEXTBOX_Reg;
	private Image MOVEPANEL[];
	private Region MOVEPANEL_Reg[];
	private Image SWITCHPOKEMON = new ImageIcon(DamageCalculatorBattlePanel.class.getResource("/image/damagecalculator/switchPokemon.png")).getImage();
	private Region SWITCHPOKEMON_Reg;
	private Image MYPOKEMON[];
	private Image MYPOKEMON_USED  = new ImageIcon(DamageCalculatorBattlePanel.class.getResource("/image/damagecalculator/pokeUsed.png")).getImage();
	private Image MYPOKEMON_UNUSED  = new ImageIcon(DamageCalculatorBattlePanel.class.getResource("/image/damagecalculator/pokeUnused.png")).getImage();
	private Image MYPOKEMON_NONE  = new ImageIcon(DamageCalculatorBattlePanel.class.getResource("/image/damagecalculator/pokeNone.png")).getImage();
	private Region MYPOKEMON_Reg[];
	private Image FOEPOKEMON[];
	private Image FOEPOKEMON_USED  = new ImageIcon(DamageCalculatorBattlePanel.class.getResource("/image/damagecalculator/foeUsed.png")).getImage();
	private Image FOEPOKEMON_UNUSED  = new ImageIcon(DamageCalculatorBattlePanel.class.getResource("/image/damagecalculator/foeUnused.png")).getImage();
	private Image FOEPOKEMON_NONE  = new ImageIcon(DamageCalculatorBattlePanel.class.getResource("/image/damagecalculator/foeNone.png")).getImage();
	private Region FOEPOKEMON_Reg[];
	
	private Image OWN_PKM_SPRITE;
	private Region OWN_PKM_SPRITE_Reg;
	private Image FOE_PKM_SPRITE;
	private Region FOE_PKM_SPRITE_Reg;
	
	private Font font1, font2;
	
	public static final int MAX_BAR_SIZE = 96;
}
