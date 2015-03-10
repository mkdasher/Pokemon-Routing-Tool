package routingtool.util;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStreamReader;

public class CSVFileReader {
	public CSVFileReader(){
		
	}

	public String[] getLine(int n, String path) {
		FileInputStream fis = null;
		BufferedReader br;
		try {
			fis = new FileInputStream(path);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
			return null;
		}
		br = new BufferedReader(new InputStreamReader(fis));
		String[] result = null;
		try {
			for (int i = 0; i < n + 1; i++){
				br.readLine();
			}
			result = br.readLine().split(",");
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try {
			br.close();
		} catch (IOException e) {
			e.printStackTrace();
		}
		return result;
	}
}
