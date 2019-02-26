import java.awt.BorderLayout;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.text.NumberFormat;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.Scanner;

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

		Carrito shoppingCart = new Carrito();

		Scanner scan = new Scanner(System.in);

		int flag = 0;

		while(true){
			if(flag == 1) {
				scan.close();
				break;
			}

			System.out.println("Que deseas hacer?\n1)Agregar al carrito\n2)Ver y editar carrito\n3)Proceder a pagar");

			int opcion = scan.nextInt();

			switch(opcion) {
			case 1:
				System.out.println("Estas son las propiedades disponibles y sus costos por ladrillo: \n\n" + text);

				System.out.println("De cual propiedad quieres comprar?");
				String s = scan.next();

				System.out.println("Cuantos ladrillos quieres comprar?");
				int i = scan.nextInt();

				shoppingCart.putItem(s, i);

				break;
			case 2:				
				System.out.println("Tu carrito tiene: \n");
				
				String[] keys = shoppingCart.getKeys();
				
				String imprimirCarrito = "Propiedad - Cantidad - Valor $\n";
				
				for(int loop=0; loop<keys.length;loop++) {
					Integer valorDinero = propiedades.get(keys[loop]);
					NumberFormat formatDinero = NumberFormat.getCurrencyInstance();
					String stringDinero = formatDinero.format(valorDinero);
					
					imprimirCarrito = imprimirCarrito + keys[loop] + " - " + shoppingCart.getCantidad(keys[loop]) + " - " + stringDinero + "\n";
				}
				
				System.out.println(imprimirCarrito);
				
				System.out.println("Cual propiedad quieres eliminar del carrito?");
				s = scan.next();
				
				shoppingCart.pullItem(s);

				break;
			case 3:


				flag = 1;
				break;
			default:
				System.out.println("Opcion invalida");
				break;
			}

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

