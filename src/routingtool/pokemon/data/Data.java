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
	
	/**
	 * Same than toString, but I often prefer to use this method.
	 * @return
	 */
	public String getName(){
		return this.name;
	}
		
	protected int n;
	private String name;
}
