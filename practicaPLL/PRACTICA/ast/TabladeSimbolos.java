package ast;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class TabladeSimbolos {
    //Trabajamos con una pila de tablas de simbolos 
	private static List<Map<String, Nodo>> tabla = new ArrayList<>();
	public static void abreBloque() {
        //En un ámbito nuevo (funcion nueva, bloque...) necsito una tabla nueva
		tabla.add(new HashMap<>());
	}
	
	public static void cierraBloque() {
        //Cuando salgo de ese ambito pues elimino su tabla 
		tabla.remove(tabla.size() - 1);
	}
	
	public static boolean insertaId(String id, Nodo nodo) {
		//coge el ultimo
		int ambito_act = tabla.size() - 1;
        //Si en el diccionario no esta en el hashmap insertado (el de mi ambito actual )
		boolean insert_valido = !tabla.get(ambito_act).containsKey(id);
		
		if(insert_valido) { //si no esta lo inserto
			tabla.get(ambito_act).put(id, nodo);
		}
		
		else { 
			System.err.println("ERROR SEMÁNTIOO: fila " + nodo.getFila()+ ", columna "+ nodo.getCol() + 
					" variable "+ id + " ya ha sido declarada. Tomamos como válida su primera declaración");
		}
		
		return insert_valido;
	}
	
	public static Nodo getDeclaracion(String id) {
		
		int tam = tabla.size() - 1;
		for(int i = tam; i >= 0; i--) { //Miro todos los ambitos desde el ultimo hasta el primero (pos 0) como si fuese pila
			if(tabla.get(i).containsKey(id)) { //si encuentra en algun sitio una declaracion con ese identificador
				return tabla.get(i).get(id); //devuelve el nodo
			}
		}
		
		return null;
	}
}