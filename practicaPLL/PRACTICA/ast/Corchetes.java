//para acceso a array
package ast;

import errors.GestionErroresExp;

public class Corchetes extends ExpBinaria{
    public Corchetes(Exp op1, Exp op2, int fila, int col){
        super(op1,op2);
        this.fila=fila;
        this.columna=col;
    }
    public TExp tipoExp() {return TExp.CORCHETES;}
    public String toString(){
        return op1().toString() + "acceso array posición "+ op2().toString();
    }
    public void vincula(){
		op1().vincula();
	
	}
    public TTipo chequea(){
        TTipo t =null;
        Iden id = (Iden) op1();
        TTipo tipo1=op1().chequea();
        TTipo tipo2=op2().chequea();
        if(tipo1== TTipo.ARRAY && tipo2==TTipo.INT){
            TTipo t2 = id.getTipo();
            if(t2 == TTipo.ARRAY){
                Array a = (Array)id.getNodo();
                t = a.getTipo().tipoTipo();
            }
        }
        else{
            GestionErroresExp.errorSemantico(fila, columna,"Error en el tipo de los corchetes");
        }
        return t;
    }
    @Override
    public void code() {
        // TODO Auto-generated method stub
        
    }

   

}