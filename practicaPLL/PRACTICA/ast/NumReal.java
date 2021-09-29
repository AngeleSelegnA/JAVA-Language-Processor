package ast;
public class NumReal extends Exp{

    private String valor;
    
    public NumReal( String valor, int fila, int col ){
        this.valor = valor;
        this.fila=fila;
        this.columna=col;
    }

    public TExp tipoExp(){return TExp.REAL;}

    public String toString(){
        return "real: " + valor;
    }

    public String getEntero(){
        return this.valor;
    }

    public void vincula() {
        
    }

    public TTipo chequea() {
        return TTipo.FLOAT;
    }
    public void code(){
        System.out.println("i32.const "+valor);
    }
}