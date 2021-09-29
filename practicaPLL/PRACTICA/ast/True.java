package ast;
public class True extends Exp{
    private Boolean valor;

    public True(int fila, int col){
        this.valor = true;
        this.fila=fila;
        this.columna=col;
    }
    public TExp tipoExp(){return TExp.TRUE;}

    public Boolean getValor(){
        return valor;
    }
    public String toString(){
        return "true";
    }
    @Override
    public void vincula() {
        // TODO Auto-generated method stub
        
    }
    public TTipo chequea(){
        return TTipo.BOOL;
    }
    @Override
    public void code() {
        
        System.out.println("1");       
        
    }
    
}
