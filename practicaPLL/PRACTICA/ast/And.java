package ast;

import errors.GestionErroresExp;

public class And extends ExpBinaria{
   
    public And(Exp op1, Exp op2, int fila, int columna){
        super(op1, op2);
        this.fila=fila;
        this.columna=columna;
    }

    public TExp tipoExp(){return TExp.AND;}

    public String toString(){
        return "and(" + op1().toString() + "," + op2().toString() + ")";
    }

    
    public TTipo chequea() {
        TTipo tipo = null;
        TTipo tipo1 = op1().chequea();
        TTipo tipo2 = op2().chequea();
        if(tipo1 == TTipo.BOOL && tipo2 == TTipo.BOOL){
            tipo = TTipo.BOOL;
        }
        else{
            GestionErroresExp.errorSemantico(fila, columna, "Error de tipos en la expresión And");
        }
        return tipo;
        
    }

    public void code() {
        op1().code();
        op2().code();
        System.out.println("i32.and");
    }
    
}
