import java.text.NumberFormat;
import java.util.HashMap;

public class Carrito{

	HashMap<String, Integer> carrito = new HashMap<String, Integer>();

	//metodo para agregar un nuevo item al carrito
	//si el item ya existia en el carrito, se le suma la cantidad
	public void putItem(String propiedad, Integer cantidad) {

		Integer value = carrito.get(propiedad);

		if(value != null)
			cantidad += value;

		carrito.put(propiedad, cantidad);		
	}

	//eliminamos del carrito
	public void pullItem(String key) {
		carrito.remove(key);

	}
	
	//obtenemos las keys (nombres de las propiedades) que tenemos en el carrito
	//regresa un arreglo
	public String[] getKeys() {
		String [] keys = carrito.keySet().toArray(new String[0]);
		
		
		return keys;		
	}
	
	//obtenemos la cantidad de ladrillos dada una propiedad (key)
	//regresa la cantidad
	public int getCantidad(String key) {
		int cantidad = carrito.get(key);

		return cantidad;		
	}
	
	
}
