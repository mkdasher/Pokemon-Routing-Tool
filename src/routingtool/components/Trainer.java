package routingtool.components;

public class Trainer {
	public Trainer(String name){
		this.name = name;
		this.team = new PokemonTeam();
	}
	public Trainer(String name, PokemonTeam team){
		this.name = name;
		this.team = team;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	public String getName(){
		return this.name;
	}
	
	public void updateName(String name){
		this.name = name;
	}
	
	public PokemonTeam getPokemonTeam(){
		return this.team;
	}
	
	private PokemonTeam team;
	private String name;
}
