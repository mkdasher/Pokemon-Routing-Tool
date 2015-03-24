package routingtool.util;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.List;

import routingtool.components.Event;
import routingtool.components.EventType;
import routingtool.components.PokemonObtained;
import routingtool.components.PokemonTeam;
import routingtool.components.PokemonUsedList;
import routingtool.components.PokemonUsed;
import routingtool.components.SingleTrainerBattle;
import routingtool.components.DoubleTrainerBattle;
import routingtool.components.Trainer;
import routingtool.pokemon.Pokemon;
import routingtool.pokemon.data.Ability;
import routingtool.pokemon.data.Nature;
import routingtool.pokemon.data.PokemonItem;
import routingtool.pokemon.data.StatPack;

public class FileUtil {
	public static List<Event> loadFile(String path){
		FileInputStream fis = null;
		ObjectInputStream ois = null;
		try {
			fis = new FileInputStream(path);
			ois = new ObjectInputStream(fis);
		} catch (Exception e) {return null;}
		
		List<Event> list = new ArrayList<Event>();
		
		try {
			int listSize = ois.readInt();
			for (int i = 0; i < listSize; i++){
				EventType etype  = (EventType)ois.readObject();
				switch(etype){
				case PokemonObtained:
					list.add(new PokemonObtained(readPokemon(ois)));
					break;
				case SingleTrainerBattle:
					list.add(new SingleTrainerBattle(
							readTrainer(ois),
							ois.readInt(),
							readPokemonUsedList(ois),
							ois.readBoolean()));
					break;
				case DoubleTrainerBattle:
					list.add(new DoubleTrainerBattle(
							readTrainer(ois),
							readTrainer(ois),
							ois.readInt(),
							readPokemonUsedList(ois)));
					break;
				case Trade:
					break;
				case WildEncounter:
					break;
				default:
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		try {ois.close();} catch (IOException e) {}
		try {fis.close();} catch (IOException e) {}
		return list;
	}
	
	public static void saveFile(String path, List<Event> list){
		FileOutputStream fos = null;
		ObjectOutputStream oos = null;
		try {
			fos = new FileOutputStream(path);
			oos = new ObjectOutputStream(fos);
		} catch (Exception e) {}
		
		try {
			oos.writeInt(list.size());
			for (int i = 0; i < list.size(); i++){
				Event event = list.get(i);
				oos.writeObject(event.getEventType());
				switch(event.getEventType()){
				case PokemonObtained:
					writePokemon(((PokemonObtained)event).getGift(), oos);
					break;
				case SingleTrainerBattle:
					SingleTrainerBattle stb = (SingleTrainerBattle) event;
					writeTrainer(stb.getTrainer(), oos);
					oos.writeInt(stb.getMoney());
					writePokemonUsedList(stb.getPokemonUsedList(), oos);
					oos.writeBoolean(stb.isSpinner());
					break;
				case DoubleTrainerBattle:
					DoubleTrainerBattle dtb = (DoubleTrainerBattle) event;
					writeTrainer(dtb.getTrainer1(), oos);
					writeTrainer(dtb.getTrainer2(), oos);
					oos.writeInt(dtb.getMoney());
					writePokemonUsedList(dtb.getPokemonUsedList(), oos);
					break;
				case Trade:
					break;
				case WildEncounter:
					break;
				default:
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
		try {oos.close();} catch (IOException e) {}
		try {fos.close();} catch (IOException e) {}
	}
	
	private static void writePokemon(Pokemon pkm, ObjectOutputStream oos) throws IOException{
		oos.writeInt(pkm.getBaseData().getID());
		oos.writeInt(pkm.getLevel());
		writeStatPack(pkm.IV, oos);
		writeStatPack(pkm.EV, oos);
		oos.writeInt(pkm.getNature().getID());
		oos.writeInt(pkm.getAbility().getID());
		oos.writeInt(pkm.getHeldItem().getID());
	}
	private static Pokemon readPokemon(ObjectInputStream ois) throws IOException{
		return new Pokemon(
				ois.readInt(),
				ois.readInt(),
				readStatPack(ois),
				readStatPack(ois),
				new Nature(ois.readInt()),
				new Ability(ois.readInt()),
				new PokemonItem(ois.readInt())
				);
	}
	
	private static void writeStatPack(StatPack pack, ObjectOutputStream oos) throws IOException{
		for (int i = 0; i < StatPack.STAT_N; i++){
			oos.writeInt(pack.getStat(i));
		}
	}
	private static StatPack readStatPack(ObjectInputStream ois) throws IOException{
		StatPack sp = new StatPack();
		for (int i = 0; i < StatPack.STAT_N; i++){
			sp.setStat(i, ois.readInt());
		}
		return sp;
	}
	
	private static void writeTrainer(Trainer t, ObjectOutputStream oos) throws IOException{
		writeString(t.getName(), oos);
		PokemonTeam team = t.getPokemonTeam();
		oos.writeInt(team.size());
		for (int i = 0; i < team.size(); i++){
			writePokemon(team.getPokemon(i), oos);
		}
	}
	private static Trainer readTrainer(ObjectInputStream ois) throws IOException{
		String str = readString(ois);
		PokemonTeam team = new PokemonTeam();
		int size = ois.readInt();
		for (int i = 0; i < size; i++){
			team.addPokemon(readPokemon(ois));
		}
		return new Trainer(str, team);
	}
	
	private static void writePokemonUsedList(PokemonUsedList pul, ObjectOutputStream oos) throws IOException{
		oos.writeInt(pul.size());
		for (int i = 0; i < pul.size(); i++){
			writePokemonUsed(pul.get(i), oos);
		}
	}
	private static PokemonUsedList readPokemonUsedList(ObjectInputStream ois) throws IOException{
		PokemonUsedList pul = new PokemonUsedList();
		int size = ois.readInt();
		for (int i = 0; i < size; i++){
			pul.add(readPokemonUsed(ois));
		}
		return pul;
	}
	
	private static void writePokemonUsed(PokemonUsed pu, ObjectOutputStream oos) throws IOException{
		oos.writeInt(pu.getPokemonSlot());
		oos.writeInt(pu.getTrainerID());
		for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
			oos.writeBoolean(pu.isUsed(i));
		}
	}
	private static PokemonUsed readPokemonUsed(ObjectInputStream ois) throws IOException{
		PokemonUsed pu = new PokemonUsed(ois.readInt(), ois.readInt());
		for (int i = 0; i < PokemonTeam.MAX_PKM; i++){
			if (ois.readBoolean()) pu.setUsed(i);
		}
		return pu;
	}
	
	private static void writeString(String str, ObjectOutputStream oos) throws IOException{
		oos.writeInt(str.length());
		oos.writeChars(str);
	}
	private static String readString(ObjectInputStream ois) throws IOException{
		int size = ois.readInt();
		char[] c = new char[size];
		for (int i = 0; i < size; i++){
			c[i] = ois.readChar();
		}
		return new String(c);
	}
}
