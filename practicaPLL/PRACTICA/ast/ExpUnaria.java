package ast;
public abstract class ExpUnaria extends Exp {
    private Exp op1;

    public ExpUnaria(Exp op1) {
      this.op1 = op1;
    }

    public Exp op1() {return op1;}
    public TNodo tipoNodo() {
		  return TNodo.EXPUNARIA;
    }
    public void vincula(){
      op1.vincula();
    }

 }