package ast;

public class NumEntero extends Exp{

    private String valor;
    
    public NumEntero( String valor, int fila, int col ){
        this.valor = valor;
        this.fila=fila;
        this.columna=col;
    }

    public TExp tipoExp(){return TExp.ENTERO;}

    public String toString(){
        return "entero: " + valor;
    }

    public String getEntero(){
        return this.valor;
    }

    @Override
    public void vincula() {
        
    }

    public TTipo chequea() {
        return TTipo.INT;
    }
    public void code(){
        System.out.println("i32.const "+valor);
    }
}