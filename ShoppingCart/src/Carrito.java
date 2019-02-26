import java.text.NumberFormat;
import java.util.HashMap;

public class Carrito{

	HashMap<String, Integer> carrito = new HashMap<String, Integer>();

	public void putItem(String propiedad, Integer cantidad) {

		Integer value = carrito.get(propiedad);

		if(value != null)
			cantidad += value;

		carrito.put(propiedad, cantidad);		
	}

	public String showCarrito() {


		String text = "(Proiedad - Cantidad)\n";		

		for (String name: carrito.keySet()){

			String key =name.toString();
			Integer value = carrito.get(name);			
			text = text + key + " - " + value + "\n";
		}

		return text;

	}

	public void pullItem(String key) {

	}
	
	public String[] getKeys() {
		String [] keys = carrito.keySet().toArray(new String[0]);
		
		
		return keys;		
	}
	
	public int getCantidad(String key) {
		int cantidad = 0;



		return cantidad;		
	}
	
	
}
