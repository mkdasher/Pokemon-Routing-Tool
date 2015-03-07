package routingtool.pokemon.data;

import java.util.List;

public abstract class Data {
	
	//Data without database
	public Data(int n, String name){
		this.n = n;
		this.name = name;
	}
	
	//Data with database
	public Data(int n, String name, String databaseFile){
		this.n = n;
		this.name = name;
		this.databaseFile = databaseFile;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
	
	public String getDataBaseFile(){
		return this.databaseFile;
	}
	
	public abstract List<Data> getList();
	
	public int n;
	private String name;
	private String databaseFile;
}
