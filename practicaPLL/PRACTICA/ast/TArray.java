package ast;

public class TArray extends Tipo{

    private Tipo tipoArray;

    public TArray(Tipo tipoArray, int fila, int col){
        //DUDA super();
        this.tipoArray = tipoArray; //el tipo de elementos del array
        this.fila=fila;
        this.columna=col;
    }
    
    public TTipo tipoTipo(){return TTipo.ARRAY;}

    public Tipo getTipoArray(){
        return this.tipoArray;
    }

    public void setTipoArray(Tipo tipoArray){
        this.tipoArray = tipoArray;
    }
    public String toString(){
            return "array \n";

    }
    
}
	