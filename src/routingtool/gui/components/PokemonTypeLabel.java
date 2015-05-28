package routingtool.gui.components;

import java.awt.Dimension;
import java.awt.Image;

import javax.swing.ImageIcon;
import javax.swing.JLabel;

import routingtool.pokemon.data.PokemonType;

@SuppressWarnings("serial")
public class PokemonTypeLabel extends JLabel{
	public PokemonTypeLabel(){
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
	}
	
	public PokemonTypeLabel(PokemonType ptype){
		this.setPreferredSize(new Dimension(WIDTH,HEIGHT));
		this.setType(ptype);
	}
	
	public void setType(PokemonType ptype){
		String path = "/image/pokemontypes/" + ptype.getName().toLowerCase() + ".png";
		if (ptype.isNone()){
			this.setIcon(new ImageIcon());	
			return;
		}
		
		Image img = new ImageIcon(PokemonIcon.class.getResource("/image/pokemontypes/" + ptype.getName().toLowerCase() + ".png")).getImage();
		Image resizedImage = img.getScaledInstance(WIDTH, HEIGHT, java.awt.Image.SCALE_SMOOTH);
		this.setIcon(new ImageIcon(resizedImage));
	}

	private static final int WIDTH = 34;
	private static final int HEIGHT = 16;
}
