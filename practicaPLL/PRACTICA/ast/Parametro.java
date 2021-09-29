package ast;

public class Parametro extends Nodo{
	private Tipo tipo;
	private Exp iden;
	public Parametro(Tipo tipo, Exp iden, int fila, int col) {
		super();
		this.tipo = tipo;
		this.iden = iden;
		this.fila=fila;
		this.columna=col;
		
	}
	public TNodo tipoNodo() {
		return TNodo.PARAM;
	}

	public Exp getIden() {
		return iden;
	}
	
	public Tipo getTipo() {
		return this.tipo;
	}
	
	public void setTipo(Tipo tipo) {
		this.tipo = tipo;
	}
	
	
	
	public String toString() {
		return "Parametro( Tipo: " + tipo.toString()  + ", nombre: " + iden.toString() + " )\n";
	}
	
	

	public void vincula(){
		Iden identificador=(Iden)iden;
		TabladeSimbolos.insertaId(identificador.getId(), this);
		
	}
	
	
}