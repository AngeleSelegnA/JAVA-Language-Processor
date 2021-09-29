package ast;

import errors.GestionErroresExp;

public class Menor extends ExpBinaria {
	public Menor (Exp op1, Exp op2, int fila, int col){
        super(op1, op2);
        this.fila=fila;
        this.columna=col;
    }
	
	public TExp tipoExp() {return TExp.MENOR;}
   
    public String toString(){
        return "menor" + op1().toString() + "," + op2().toString() + ")";
    }

    public TTipo chequea(){
        TTipo tipo1= op1().chequea();
        TTipo tipo2=op2().chequea();
        
        if(op1().tipoExp() == TExp.IDEN) {
            Iden id = (Iden) op1();
            tipo1 = id.chequea();

        }

        if (tipo1 == tipo2 && (tipo1 == TTipo.INT || tipo2 == TTipo.FLOAT)) {
            return     TTipo.BOOL;
        }
        else{

            GestionErroresExp.errorSemantico(this.getFila(),this.getCol(),"Error de tipos en la expresión Menor");
            return null;
        }
    }

    public void code() {
        op1().code();
        op2().code();
        System.out.println("i32.lt");
    }

}