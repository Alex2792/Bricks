import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Collections;
import java.util.HashMap;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

public class Main {

	public static void main(String[] args) {

		HashMap<String, Integer> propiedades = new HashMap<String, Integer>();
		HashMap<String, Integer> cupones = new HashMap<String, Integer>();

		propiedades = filetoHash("propiedades.txt");
		cupones = filetoHash("cupones.txt");

		//System.out.println(Collections.singletonList(propiedades));	
		//System.out.println(Collections.singletonList(cupones));
		/**
		JFrame ventanaInicial = new JFrame("100ladrillos");		
		ventanaInicial.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		//Display
		ventanaInicial.setLocationByPlatform(true);
		ventanaInicial.setSize(800, 500);
		ventanaInicial.setVisible(true);
		
		StringBuilder buff = new StringBuilder();
		buff.append("<html><table>");
		buff.append(text);
		buff.append("</table></html>");

		JLabel textLabel = new JLabel(buff.toString());

		ventanaInicial.getContentPane().add(textLabel);
		 **/


		String text = "";		

		for (String name: propiedades.keySet()){

			String key =name.toString();
			Integer value = propiedades.get(name);
			NumberFormat format = NumberFormat.getCurrencyInstance();
			String currency = format.format(value);
			
			text = text + key + " - " + currency + "\n";
		}

		//System.out.println(text);
		System.out.println("Las propiedades disponibles son las siguientes (los precios son por ladrillo): \n\n" + text);
		
		while(true){
			System.out.println("De cual propiedad quieres comprar?");
		}
		
		
	}

	public static HashMap<String, Integer> filetoHash(String docPath){

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
			System.out.println("Error");
			e.printStackTrace();
		} 


		return mapBuffer;

	}

}
