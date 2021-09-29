package ast;

public class InsReturn extends Ins{
    private Exp valor;
    public InsReturn( Exp valor, int fila, int col){
        this.valor=valor;
        this.fila=fila;
        this.columna=col;
    }

    public TTipo getTipo (){
        return valor.chequea();

    }
    public TIns tipoIns() { return TIns.RETURN;}

    public String toString() {
        return ("Return( "+ valor.toString()+" )\n" );
    }
 
    public void vincula() {

        valor.vincula();
    }

    public void chequea() {
        valor.chequea();
    }
    

    public int generaEtiqueta(int e){
        return e;
    }
    public int maxMemoria(){
        return 0;
    }
    public void code() {
        valor.code();
        System.out.println("return");            
    }

    
}
