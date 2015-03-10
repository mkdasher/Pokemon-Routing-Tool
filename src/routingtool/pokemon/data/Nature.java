package routingtool.pokemon.data;

public class Nature{
	
	public Nature(int n)
    {
        if (n >= 0 && n < NATURE_N)
        {
            this.setNature(n);
        }
    }
	
	public void setNature(int n)
    {
		this.n = n;
		this.natureBoost = new double[StatPack.STAT_N];
        for (int i = 0; i < StatPack.STAT_N; i++)
            natureBoost[i] = 1;
        if (n % StatPack.STAT_N == 0) return; //neutral natures
        natureBoost[natStatOrder(n / 5 + 1)] = 1.1;
        natureBoost[natStatOrder(n % 5 + 1)] = 0.9;
    }
	
	public double getNatureBoost(int n)
    {
        return natureBoost[n];
    }
	
	/**
	 * get Nature Order for nature boost
	 * atk,def,spa,spd,spe to atk,def,spe,spa,spd
	 * @param n
	 * @return
	 */
	private int natStatOrder(int n)
    {
        if (n == 3) return 5;
        else if (n == 4) return 3;
        else if (n == 5) return 4;
        else return n;
    }
	
	/**
	 * Get a copy of the nature
	 * @return
	 */
	public Nature getCopy() {
		return new Nature(this.n);
	}
	
	@Override
	public String toString(){
		return toStr[n];
	}
	
	private final static String[] toStr = {"Hardy", "Lonely", "Brave", "Adamant", "Naughty", "Bold", "Docile", "Relaxed",
		"Impish", "Lax", "Timid", "Hasty", "Serious", "Jolly", "Naive", "Modest", "Mild", "Quiet",
		"Bashful", "Rash", "Calm", "Gentle", "Sassy", "Careful", "Quirky"};
	
	private final static int NATURE_N = 25;
	public int n;
	private double[] natureBoost;
}
