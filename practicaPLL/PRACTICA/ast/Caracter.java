package ast;

public class Caracter extends Exp{
    private String car;

    public Caracter(String car, int fila, int col){
        this.car = car;
        this.fila=fila;
        this.columna=col;
    }

    public TExp tipoExp(){return TExp.CARACTER;}

    public String toString(){
        return "caracter: " + this.car;
    }
    public String getCaracter(){return this.car;}

    @Override
    public void vincula() {        
    }

    @Override
    public TTipo chequea() {
        return  TTipo.CHAR;
    }

    @Override
    public void code() {
        // TODO Auto-generated method stub
        
    }
    
    
}
