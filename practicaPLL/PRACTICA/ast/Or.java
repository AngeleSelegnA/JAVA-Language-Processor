package ast;
public class Or extends ExpBinaria{
    
    public Or (Exp opnd1, Exp opnd2, int fila, int col){
        super(opnd1,opnd2);
        this.fila=fila;
        this.columna=col;
    }

    public TExp tipoExp(){ return TExp.OR;}
    
    public String toString(){
        return "or("+ op1().toString() + "," + op2().toString() + ")";
    }

    public TTipo chequea() {
        TTipo tipo = null;
        TTipo tipo1 = op1().chequea();
        TTipo tipo2 = op2().chequea();
        if(tipo1 == TTipo.BOOL && tipo2 == TTipo.BOOL){
            tipo = TTipo.BOOL;
        }
        return tipo;
        
    }

    public void code(){
        op1().code();
        op2().code();
        System.out.println("i32.or");
    }

}