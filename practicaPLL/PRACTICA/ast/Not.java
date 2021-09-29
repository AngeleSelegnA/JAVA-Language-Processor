package ast;
import errors.GestionErroresExp;

public class Not extends ExpUnaria{
    public Not(Exp op1, int fila, int col){
        super(op1);
        this.fila=fila;
        this.columna=col;
    }
    public TExp tipoExp() {
       return TExp.NOT;
    }

    public String toString() {
        return "not "+ op1().toString();
    }
    public TTipo chequea(){
        TTipo t=null;
        Iden id = (Iden) op1();
        TTipo tipo1=op1().chequea();
        if(tipo1== TTipo.BOOL ){
            t= TTipo.BOOL;
        }
        else{
            GestionErroresExp.errorSemantico(fila, columna,"Error en el tipo de la expresión Not, solo se puede aplicar a algo de tipo Bool");
        }
        return t;
    }
    public void code() {
        op1().code();
        System.out.println("i32.eqz");
    }
}