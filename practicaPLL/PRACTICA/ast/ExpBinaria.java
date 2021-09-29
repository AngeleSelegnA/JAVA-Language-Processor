package ast;

public abstract class ExpBinaria extends Exp {
	private Exp op1;
	private Exp op2;

	protected ExpBinaria(Exp op1, Exp op2) {
		this.op1 = op1;
		this.op2 = op2;
	}
   
	public Exp op1() {return op1;}
	public Exp op2() {return op2;}    
   
	public TNodo tipoNodo() {
		return TNodo.EXPBIN;
	}
	public void vincula(){
		
		if (this.tipoExp()==TExp.ACCESOENUM){
			op1().vincula();
		}
		else{
		op1().vincula();
		op2().vincula();
		}
	}

}