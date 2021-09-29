package ast;

import errors.GestionErroresExp;

public class InsAsignac extends Ins{
    private Exp iden;
    private Exp valor;
    //no es iden es designador es decir o un iden o un struct.campo que es un iden en vd o un array[i] que es un iden en vd

    public InsAsignac(Exp iden, Exp valor, int fila, int col){
        super();
        this.iden = iden;
        this.valor = valor;
        this.fila=fila;
        this.columna=col;
    }

    public TIns tipoIns(){return TIns.ASIGNAC;}


    public String toString() {
        return  "Asignación: " + iden.toString() + " = " + valor.toString() +"\n";
    }
    

    public void vincula() {
        iden.vincula();
        valor.vincula();
    }

    
    public void chequea() {
        boolean ok = false;
        TTipo a = iden.chequea();
        TTipo b = valor.chequea();
        if(a == b || (a == TTipo.FLOAT && b == TTipo.INT)){
            ok = true;
        }

        if(!ok){
            GestionErroresExp.errorSemantico(fila, columna, "Error de incompatibilidad es la asignación.");
        }
        
    }
    public void code(){
        Iden identificador= (Iden)iden;
        identificador.code();
        //codigo inicio contxto
        System.out.println("i32.add");
        valor.code();
        System.out.println("i32.store");
        
    }

    public int generaEtiqueta(int e){
        return e;
    }
    public int maxMemoria(){
        return 0; 
    }
    
}

