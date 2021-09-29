package ast;
public abstract class Exp extends Nodo{
    public abstract TExp tipoExp();
    public Exp op1() {throw new UnsupportedOperationException ("op1");}
    public Exp op2() {throw new UnsupportedOperationException ("op2");}
    public abstract String toString();
    public TNodo tipoNodo(){return TNodo.EXP;}
    public abstract void vincula();
    public abstract TTipo chequea();
    public abstract void code();
}