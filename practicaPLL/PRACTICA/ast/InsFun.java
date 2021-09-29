package ast;

import java.util.ArrayList;
import java.util.List;

import errors.GestionErroresExp;

public class InsFun extends Ins{
    private Tipo tipo;
    private Exp iden;
    private List<Parametro> parametros; 
	private Exp valorReturn;
    private List<Ins> cuerpo;
    private int numParametros = 0;


	

public InsFun(Tipo tipo, Exp iden, List<Parametro> parametros, List<Ins> cuerpo, int fila, int col)	{
        super();
        this.tipo = tipo;
		this.iden = iden;
		this.parametros = parametros;
		this.cuerpo = cuerpo;
        this.numParametros = parametros.size();
        this.fila=fila;
        this.columna=col;
}

public InsFun(Tipo tipo, Exp iden, List<Ins> cuerpo, int fila, int col)	{
    super();
    this.tipo = tipo;
    this.iden = iden;
    this.cuerpo = cuerpo;
    this.numParametros = 0;
    this.fila=fila;
    this.columna=col;
    this.parametros=new ArrayList<Parametro>();
}

	public TIns tipoIns() {
		return TIns.FUN;
	}
	
    public String toString(){
        String params = "(";
        String instrucciones = "(";
        for(Parametro parametro: this.parametros){
            params = params + parametro.toString() + ", ";
        }
        
        for(Ins ins: this.cuerpo){
            instrucciones = instrucciones + ins.toString() + ", ";
        }
        params += ")";
        instrucciones += ")";
        return "FUNCIÓN (TIPO: " + this.tipo.toString() +  ", NOMBRE: " + iden.toString() + ", PARÁMETROS: " + params + ", INSTRUCCIONES: " + instrucciones + " )\n\n"; 

    }

	public Tipo getTipo() {return this.tipo;}
	
	public void setTipo(Tipo tipo) {this.tipo = tipo;}

	public Exp getIden() {return this.iden;}

	public Exp getValorReturn() {return this.valorReturn;}

	public void setValorReturn(Exp valorReturn) {this.valorReturn = valorReturn;	}

    public int getNumParametros(){return this.numParametros; }

    public List<Parametro> getParametros(){
        return this.parametros;
    }

    public List<Ins> getCuerpo(){
        return this.cuerpo;
    }

    public void incrementarNumParametros(int incremento) {
		this.numParametros += incremento;
	}

    public void vincula(){
        Iden ident= (Iden)this.iden;
        boolean ok = TabladeSimbolos.insertaId(ident.getId(), this);
        
        if (ok){

            TabladeSimbolos.abreBloque();
            for( Parametro param: this.parametros){
                    param.vincula();
            }
            
            for (Ins inst: cuerpo){
                inst.vincula();
            }        

            TabladeSimbolos.cierraBloque();
        }

    }

    public void chequea(){

        boolean tieneReturn = false;
		for(Parametro p: this.parametros) {
			if(p.getTipo().tipoTipo() == TTipo.ARRAY) {
					GestionErroresExp.errorSemantico(this.getFila(), this.getCol(), "Error tipos InsFun: en el parámetro " + ((Iden)p.getIden()).getId() +". Los parámetros de una función no pueden ser de tipo vector");
			}
		}
        for(Ins ins: this.cuerpo){
            if(ins.tipoIns()==TIns.RETURN){
                tieneReturn = true;
                InsReturn insR = (InsReturn) ins;
                if(insR.getTipo()!=this.tipo.tipoTipo()){
                    GestionErroresExp.errorSemantico(insR.getFila(), insR.getCol(), "Error, el tipo de la instrucción return debe coincidir con el tipo de la función.");
                }

            }
            else{
                ins.chequea();
            }
        }

        if(tieneReturn==false){
            GestionErroresExp.errorSemantico(fila, columna, "Error, las funciones deben hacer return.");
        }

        
		
    }

    public int generaEtiqueta(int e){
        int aux=e, aux1 = e;
        for(Ins ins: cuerpo){
            aux1 = ins.generaEtiqueta(aux);
            aux = aux1;
        }
        return e;
    }

    public void code(){
        
        for (Ins elem: cuerpo){
            elem.code();
        }
    }

    public int maxMemoria(){ //insfun
        int maxSol = 0;
        for(Ins ins: this.cuerpo){
            maxSol +=  ins.maxMemoria();
            
        }
        return maxSol;
    }

}