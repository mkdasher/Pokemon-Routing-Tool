package routingtool.compontents;

public class Trainer {
	public Trainer(String name){
		this.name = name;
		this.team = new PokemonTeam();
	}
	public Trainer(String name, PokemonTeam team){
		this.name = name;
		this.team = team;
	}
	
	PokemonTeam team;
	String name;
}
