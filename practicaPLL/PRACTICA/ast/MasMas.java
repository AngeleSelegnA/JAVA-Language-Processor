package ast;
import errors.GestionErroresExp;

public class MasMas extends ExpUnaria {

    public MasMas(Exp op1, int fila, int col) {
        super(op1);
        this.fila=fila;
        this.columna=col;
    }

    public TExp tipoExp() {

        return TExp.MASMAS;
    }

    public String toString() {

        return "suma ++ (" + op1().toString() + ")";
    }
    public TTipo chequea(){
        TTipo t=null;
        Iden id = (Iden) op1();
        TTipo tipo1=op1().chequea();
        if(tipo1== TTipo.INT ){
            t= TTipo.INT;
        }
        else if(tipo1==TTipo.FLOAT){
            t=TTipo.FLOAT;
        }
        else{
            GestionErroresExp.errorSemantico(fila, columna,"Error en el tipo de la expresión MasMas, solo se puede aplicar a algo de tipo Int o Float");
        }
        return t;
    }

    @Override
    public void code() {
        op1().code();
        System.out.println("i32.const 1");
        System.out.println("i32.add");        
    }

    


}