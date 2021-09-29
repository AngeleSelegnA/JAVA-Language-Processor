package ast;

import errors.GestionErroresExp;

public class Div extends ExpBinaria {
	
	public Div(Exp op1, Exp op2, int fila, int col) {
		super(op1,op2);
		this.fila=fila;
		this.columna=col;
	}    
   
	public TExp tipoExp() {return TExp.DIV;}
   
	public String toString() {
		return "división " + op1().toString() + "/" + op2().toString();
	}

	public TTipo chequea() {
        TTipo tipo = null;
        TTipo tipo1 = op1().chequea();
        TTipo tipo2 = op2().chequea();
        if(tipo1 == TTipo.INT && tipo2 == TTipo.INT){
            tipo = TTipo.INT;
        }
		else if(tipo1 == TTipo.FLOAT && tipo2 == TTipo.FLOAT){
            tipo = TTipo.FLOAT;
        }
        else if(tipo1 == TTipo.FLOAT && tipo2 == TTipo.INT){
            tipo = TTipo.FLOAT;
        }
        else if(tipo1 == TTipo.INT && tipo2 == TTipo.FLOAT){
            tipo = TTipo.FLOAT;
        }
		else{
            GestionErroresExp.errorSemantico(fila, columna, "Error de tipos en la expresión Div");
        }
        return tipo;
        
    }

    public void code() {
        op1().code();
        op2().code();
        System.out.println("i32.div_s");
        
    }
}