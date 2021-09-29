package ast;

import errors.GestionErroresExp;

public class Distinto extends ExpBinaria{
    public Distinto(Exp op1, Exp op2, int fila, int col){
        super(op1, op2);
        this.fila=fila;
        this.columna=col;

    }

    public TExp tipoExp(){return TExp.DISTINTO;}

    public String toString(){
		return "distinto " + op1().toString() + "!=" + op2().toString();

    }

    public TTipo chequea() {
      TTipo tipo = null;
      TTipo tipo1 = op1().chequea();
      TTipo tipo2 = op2().chequea();
      if(tipo1 == tipo2 ){
          tipo = TTipo.BOOL;
      }
      else{
        GestionErroresExp.errorSemantico(fila, columna, "Error de tipos en la expresión Distinto");
      }
      return tipo;
      
  }

    @Override
    public void code() {
      // TODO Auto-generated method stub
      
    }
  
}

