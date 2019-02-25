import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

public class main {

	public static void main(String[] args) {
		
		String propiedadesPath = "propiedades.txt", cuponesPath = "cupones.txt";
		
		HashMap<String, Integer> propiedades = new HashMap<String, Integer>();
		HashMap<String, Integer> cupones = new HashMap<String, Integer>();
		
		propiedades = FiletoHash(propiedadesPath);
		cupones = FiletoHash(cuponesPath);
		
		System.out.println(Collections.singletonList(propiedades));	
		System.out.println(Collections.singletonList(cupones));	

	}
	
	public static HashMap<String, Integer> FiletoHash(String docPath){
		
		HashMap<String, Integer> mapBuffer = new HashMap<String, Integer>();
		
		String line;
		try {
			BufferedReader reader = new BufferedReader(new FileReader(docPath));
			
			while((line = reader.readLine()) != null) {
				String[] parts = line.split(":", 2);
				if(parts.length >= 2) {
					String key = parts[0];
					Integer value = Integer.parseInt(parts[1]);
					mapBuffer.put(key, value);
				}else {
					System.out.println("Line " + line + " not valid\n");
				}
			}
			
			reader.close();
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		
		return mapBuffer;
		
	}

}
