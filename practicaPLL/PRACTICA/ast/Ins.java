package ast;

public abstract class Ins extends Nodo{
    
    public abstract TIns tipoIns();
    public abstract String toString();
    public TNodo tipoNodo(){return TNodo.INS;}
    public abstract void vincula();    
    public abstract void chequea();
    public abstract int generaEtiqueta(int e);
    public abstract int maxMemoria();
    public abstract void code();
}
