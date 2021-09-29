package ast;

import errors.GestionErroresExp;

public class Modulo extends ExpBinaria{


    public Modulo(Exp op1, Exp op2, int fila, int col){
        super(op1, op2);
        this.fila=fila;
        this.columna=col;
    }
    public TExp tipoExp(){return TExp.MODULO;}

    public String toString(){
        return "módulo(" + op1().toString() + "," + op2().toString() + ")";
    }

    public TTipo chequea(){
        TTipo tipo1= op1().chequea();
        TTipo tipo2=op2().chequea();
        if(tipo1==tipo2 && tipo1==TTipo.FLOAT){
            return TTipo.FLOAT;
        }
        else if(tipo1==tipo2 && tipo1==TTipo.INT) return TTipo.INT;
        else{
            GestionErroresExp.errorSemantico(this.getFila(),this.getCol(),"Error de tipos en la expresión Modulo");
            return null;
        }
    }

    public void code() {
        op1().code();
        op2().code();
        System.out.println("i32.ne");
        
    }
}