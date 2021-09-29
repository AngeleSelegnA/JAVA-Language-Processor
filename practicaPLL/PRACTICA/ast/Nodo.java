package ast;

public abstract class Nodo{
    //atributos fila y col, y como el resto extienden de ella loss tienen
    protected int fila;
    protected int columna;

    public abstract TNodo tipoNodo();
    public abstract String toString();
    public int getFila(){
        return fila;
    }
    public int getCol(){
        return columna;
    }
    public void setFila(int fila){
        this.fila=fila;
    }

    public void setCol(int columna){
        this.columna=columna;
    }
    
}