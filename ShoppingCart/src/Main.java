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

		//hasmaps para guardar la info de las propiedades y de los cupones
		//esta info se obtiene de un txt
		//el txt simula una base de datos 
		HashMap<String, Integer> propiedades = new HashMap<String, Integer>();
		HashMap<String, Integer> cupones = new HashMap<String, Integer>();

		propiedades = filetoHash("propiedades.txt");
		cupones = filetoHash("cupones.txt");

		//se le da formato a las propiedades para ser impresas en pantalla
		String text = "";		

		for (String name: propiedades.keySet()){
			String key =name.toString();
			
			//se le asigna formato de currency a los valores de las propiedades
			Integer value = propiedades.get(name);
			NumberFormat format = NumberFormat.getCurrencyInstance();
			String currency = format.format(value);

			text = text + key + " - " + currency + "\n";
		}

		System.out.println("Las propiedades disponibles son las siguientes (los precios son por ladrillo): \n\n" + text);

		//se crea un nuevo objeto tipo Carrito
		Carrito shoppingCart = new Carrito();

		Scanner scan = new Scanner(System.in);
		
		//flag para saber en que momento salir del while
		int flag = 0;

		while(true){
			if(flag == 1) {
				scan.close();
				break;
			}

			//impresion del menu de opciones
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


				System.out.println(impirmirCarrito(propiedades, shoppingCart));

				System.out.println("Cual propiedad quieres eliminar del carrito?");
				s = scan.next();

				shoppingCart.pullItem(s);

				break;
			case 3:				

				System.out.println(impirmirCarrito(propiedades, shoppingCart));

				System.out.println("Tienes algun cupon? Este es el momento de ingresarlo");

				s = scan.next();

				float descuento = 1;

				if(cupones.get(s)==null)
					System.out.println("Cupon no valido");
				else {
					descuento = cupones.get(s);
					descuento = (descuento/100);
				}

				String[] carKeys = shoppingCart.getKeys();

				float total = 0;

				for(int loop=0; loop< carKeys.length;loop++) {
					total = total + (propiedades.get(carKeys[loop]) * shoppingCart.getCantidad(carKeys[loop]));					
				}
				total = total * descuento;

				NumberFormat formatTotal = NumberFormat.getCurrencyInstance();
				String totalString = formatTotal.format(total);

				System.out.println("Su total es de: " + totalString);

				flag = 1;
				break;
			default:
				System.out.println("Opcion invalida");
				break;
			}

		}	

	}

	
	//Funcion que recibe uin string el cual es el nombre del txt donde esta la info que se almacenara en un hasmap
	//regresa el hasmap una vez leido la data del archivo txt
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

	//Funcion que obtiene los valores del carrito y de las propiedades (hashmaps) y los concatena en un string con formato
	//A los valores de cantidad les da formato de moneda
	//Regresa el string listo para ser impreso
	public static String impirmirCarrito(HashMap<String, Integer> p, Carrito car) {

		String[] keys = car.getKeys();
		int subtotalInt = 0;
		NumberFormat formatDinero = null;

		String imprimirCarrito = "Tu carrito tiene: \n\nPropiedad - Cantidad - Valor $ por ladrillo\n";

		for(int loop=0; loop<keys.length;loop++) {
			Integer valorDinero = (Integer) p.get(keys[loop]);
			formatDinero = NumberFormat.getCurrencyInstance();
			String stringDinero = formatDinero.format(valorDinero);

			subtotalInt = subtotalInt + (valorDinero*car.getCantidad(keys[loop]));

			imprimirCarrito = imprimirCarrito + keys[loop] + " - " + car.getCantidad(keys[loop]) + " - " + stringDinero + "\n";
		}

		String subtotalString = formatDinero.format(subtotalInt);

		imprimirCarrito = imprimirCarrito + "\nSubtotal: " + subtotalString + "\n";

		return imprimirCarrito;

	}
}

