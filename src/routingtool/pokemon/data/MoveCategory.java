package routingtool.pokemon.data;

public enum MoveCategory {
	Physical, Special, Status;

	public static MoveCategory fromString(String s) {
		if (s.equals("Physical")) return MoveCategory.Physical;
		else if (s.equals("Special")) return MoveCategory.Special;
		else return MoveCategory.Status;
	}
	
	@Override
	public String toString(){
		if (this == Physical) return "Physical";
		else if (this == Special) return "Special";
		else return "Status";
	}
	
	
}
