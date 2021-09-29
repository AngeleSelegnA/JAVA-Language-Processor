package ast;

import errors.GestionErroresExp;

public class AccesoEnum extends ExpBinaria{
    private Tipo tipo;
    public AccesoEnum( Exp op1, Exp op2, int fila, int col){
        super(op1, op2); //op1 es el iden del enum y op2 es el indice del enum, debe ser un numero
        this.fila=fila;
        this.columna=col;
    }
    
    public TExp tipoExp() {return TExp.ACCESOENUM;}
 
    public String toString() {
        return "acceso a enum: "+ op1().toString()+" índice: "+ op2().toString() + "\n";
    }

    public Tipo getTipo(){return tipo;}
    public void setTipo(Tipo tipo){
        this.tipo=tipo;
    }

    public void vincula(){
        Iden id = (Iden) op1();
    }

    public TTipo chequea() {
        TTipo tipo1 = null;
        boolean es_enum = false;
        boolean es_varEnum = false;
        TTipo t = null; 
        if(op1().tipoExp()==TExp.IDEN){
            Iden idop1= (Iden) op1();
            es_enum=true;
            tipo1=op1().chequea();
        }
        if(es_enum && tipo1==TTipo.INT){
            
        }
        

        return t;
    }

    public void code() {
        
    }


    
}