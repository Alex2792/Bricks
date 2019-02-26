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

	public void pullItem(String key) {
		carrito.remove(key);

	}
	
	public String[] getKeys() {
		String [] keys = carrito.keySet().toArray(new String[0]);
		
		
		return keys;		
	}
	
	public int getCantidad(String key) {
		int cantidad = carrito.get(key);

		return cantidad;		
	}
	
	
}
