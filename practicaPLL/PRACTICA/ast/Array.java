package ast;
import java.util.List;

import errors.GestionErroresExp;
public class Array extends Ins{
    private Tipo tipo;
    private Exp iden;
    private List<Integer> tams;
    private Exp valorInicial;
    private boolean vi;
    private int numDims;

    //queda hacer arrays de mas de una dimension

    public Array(Tipo tipo,  Exp iden, List<Integer> tams, Exp valorInicial, int fila, int col){
        this.tipo = tipo;
        this.iden = iden;
        this.tams = tams;
        this.valorInicial = valorInicial;
        this.vi = true;
        this.numDims = tams.size();
        this.fila = fila;
	    this.columna = col;
    }

    public Array( Tipo tipo, Exp iden,  List<Integer> tams, int fila, int col){
        this.tipo = tipo;
        this.iden = iden;
        this.tams = tams;
        this.vi = false;
        this.numDims = tams.size();
        this.fila = fila;
	     this.columna = col;
    }


    public Tipo getTipo(){return this.tipo;}
    public int getNumDims(){return this.numDims;}
    public void setNumDims( int numDims ){ this.numDims = numDims;}

    public List<Integer> getTams(){return this.tams;}

    public Exp getValorInicial(){return this.valorInicial;}

    public TIns tipoIns(){return TIns.ARRAY;}

    public String toString(){
        String tamsvi = "";
        for (Integer tam : tams) {
            tamsvi = tamsvi + tam.toString() + ", ";


        }
        
        if(vi){
            tamsvi = tamsvi +  "Valor inicial = " + valorInicial.toString();
        }
        
        return "array  de tipo" + this.tipo.toString() + ", Nombre: " + this.iden.toString() + ", Dimensiones del array = " + tamsvi + ")\n"; 
    }

    public void vincula(){
        Iden id = (Iden) iden;
        TabladeSimbolos.insertaId(id.getId(), this);
    }

    public void chequea() {
        if(vi==true){
            TTipo tipo = valorInicial.chequea();
            if(tipo != this.tipo.tipoTipo()){
                GestionErroresExp.errorSemantico(fila,columna,"Error en el tipo de el valor inicial, no se corresponde con el tipo del vector");
            }
        }
        
        
    }

    @Override
    public int generaEtiqueta(int e) {
        // TODO Auto-generated method stub
        return 0;
    }

    @Override
    public int maxMemoria() {
        // TODO Auto-generated method stub
        return 0;
    }
    public void code() {
        this.iden.code();
        
    }

}
