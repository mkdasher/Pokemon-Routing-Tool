package routingtool.pokemon.data;

import routingtool.util.CSVFileReader;

public class PokemonData extends Data {
	public PokemonData(int n){
		 super(n, findName(n));
		 CSVFileReader fileReader = new CSVFileReader();
		 String[] data = fileReader.getLine(n, "./res/database/pokemon.csv");
		 this.type1 = new PokemonType(data[2]);
		 this.type2 = new PokemonType(data[3]);
		 data = fileReader.getLine(n, "./res/database/pokemonEV.csv");
		 this.baseStat = new StatPack(
				 Integer.parseInt(data[1]),
				 Integer.parseInt(data[2]),
				 Integer.parseInt(data[3]),
				 Integer.parseInt(data[4]),
				 Integer.parseInt(data[5]),
				 Integer.parseInt(data[6])
				 );
		 data = fileReader.getLine(n, "./res/database/pokemonExp.csv");
		 this.exp = Integer.parseInt(data[1]);
		 this.EV = new StatPack(
				 Integer.parseInt(data[2]),
				 Integer.parseInt(data[3]),
				 Integer.parseInt(data[4]),
				 Integer.parseInt(data[5]),
				 Integer.parseInt(data[6]),
				 Integer.parseInt(data[7])
				 );
		 data = fileReader.getLine(n, "./res/database/pokemonExpType.csv");
		 this.expType = new ExperienceType(data[1]);
	}
	
	public static String findName(int n){
		CSVFileReader fileReader = new CSVFileReader();
		String[] data = fileReader.getLine(n, "./res/database/pokemon.csv");
		return data[1];
	}
	
	public String getName(){
		return this.toString();
	}
	
    public StatPack baseStat;
    public int exp;
    public StatPack EV;
    public PokemonType type1;
    public PokemonType type2;
    public ExperienceType expType;
}
