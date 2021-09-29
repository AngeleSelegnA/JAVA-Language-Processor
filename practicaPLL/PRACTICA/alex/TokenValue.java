package alex;

public class TokenValue {
	public String lexema;
	public int fila;
	public int columna;
	
	public TokenValue(String lexema, int f, int col) {
		super();
		this.lexema = lexema;
		this.fila = f;
		this.columna=col;
	}

	public String getLexema() {
		return lexema;
	}

	public int getF() {
		return fila;
	}

	public int getC(){
		return columna;
	}

}