package ast;

import errors.GestionErroresExp;

public class Resta extends ExpBinaria {
	
	public Resta(Exp op1, Exp op2, int fila, int col) {
		super(op1,op2);  
		this.fila=fila;
		this.columna=col;
	}     
   
	public TExp tipoExp() {return TExp.RESTA;}
   
	public String toString() {
        return "resta("+ op1().toString() + "," + op2().toString() + ")";
	}


	public TTipo chequea(){
        TTipo tipo1= op1().chequea();
        TTipo tipo2=op2().chequea();
        if(tipo1==TTipo.INT && tipo2==TTipo.INT){
            return TTipo.INT;
        }
		else if(tipo1==TTipo.INT && tipo2==TTipo.FLOAT){
            return TTipo.FLOAT;
        }
		else if(tipo1==TTipo.FLOAT && tipo2==TTipo.INT){
            return TTipo.FLOAT;
        }
		else if(tipo1==TTipo.FLOAT && tipo2==TTipo.FLOAT){
            return TTipo.FLOAT;
        }

        else{
            GestionErroresExp.errorSemantico(this.getFila(),this.getCol(),"Error de tipos en la expresión Resta");
            return null;
        }
    }
    public void code(){
        op1().code();
        op2().code();
        System.out.println("i32.sub");
    }
}