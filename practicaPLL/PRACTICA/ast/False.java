package ast;
public class False extends Exp{
    private Boolean valor;

    public False(int fila, int col){
        this.valor = false;
        this.fila=fila;
        this.columna=col;
    }
    public TExp tipoExp(){return TExp.FALSE;}

    public Boolean getValor(){
        return valor;
    }
    public String toString(){
        return "false";
    }
    @Override
    public void vincula() {
        
    }
    public TTipo chequea(){
        return TTipo.BOOL;
    }
    public void code() {
        System.out.println("0");       
    }
    
}



	