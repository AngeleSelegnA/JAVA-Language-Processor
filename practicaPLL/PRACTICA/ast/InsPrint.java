package ast;
public class InsPrint extends Ins{
    private Exp imp;

    public InsPrint(Exp imp, int fila, int col){
        this.imp = imp;
        this.fila=fila;
        this.columna=col;
    }
    public TIns tipoIns() {return TIns.PRINT;}

    public String toString() {
        return "Print: " + imp.toString()+ "\n";
    }

    public Exp getPrint(){return this.imp;}

    public void vincula(){
       imp.vincula();

    }
    @Override
    public void chequea() {
        imp.chequea();
    }

    public int generaEtiqueta(int e){
        return e;
    }
    public int maxMemoria(){
        return 0;
    }
    public void code() {
        imp.code();
        System.out.println("call $print");            
    }
}
