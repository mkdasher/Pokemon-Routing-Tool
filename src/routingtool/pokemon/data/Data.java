package routingtool.pokemon.data;


public abstract class Data {
	
	//Data without database
	public Data(int n, String name){
		this.n = n;
		this.name = name;
	}
	
	public int getID(){
		return this.n;
	}
	
	@Override
	public String toString(){
		return this.name;
	}
		
	protected int n;
	private String name;
}
