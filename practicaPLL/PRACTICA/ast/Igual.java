package ast;
public class Igual extends ExpBinaria{
    public Igual(Exp op1, Exp op2, int fila, int col){
        super(op1, op2);
        this.fila=fila;
        this.columna=col;

    }

    public TExp tipoExp(){return TExp.IGUAL;}

    public String toString(){
		return "igual " + op1().toString() + "," + op2().toString();

    }

    public TTipo chequea() {
    
      return null;
    }

  
    public void code() {
      
      
    }


}