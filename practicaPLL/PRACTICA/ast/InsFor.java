package ast;

import java.util.List;

import errors.GestionErroresExp;

public class InsFor extends Ins{
    
    private Ins dec;
	private Exp cond;
	private Exp paso;

	private List<Ins> cuerpo;

	public InsFor(Ins dec, Exp cond, Exp paso, List<Ins> cuerpo, int fila, int col) {
		super();
		this.dec = dec;
		this.cond = cond;
		this.paso = paso;
		this.cuerpo = cuerpo;
		this.fila=fila;
		this.columna=col;
	}
    public TIns tipoIns() { return TIns.FOR; }


    public String toString() {
        String inscuerpo = "";
        for (Ins instrucc : this.cuerpo){
            inscuerpo = inscuerpo + instrucc.toString() + ", ";
        }
        return "For (Inicialización: "+ dec.toString()+" Condición: "+cond.toString()+" Paso: "+paso.toString()+" Cuerpo: "+ inscuerpo+")\n";
       
    }
	public void vincula() {
		TabladeSimbolos.abreBloque();
		//la asignacion debia ser con valor inicial apra nuestro lenguaje
		if(dec.tipoIns() == TIns.DECLARAC){
			InsDeclarac insDec = (InsDeclarac) dec;
			if(insDec.conValorIni() == true){
				//esta declaracion tiene su identificaador en este nodo
				//Iden id = (Iden) insDec.getId();
				//ok =  TabladeSimbolos.insertaId(id.getId(), this);
				insDec.vincula();

			}
			else{
				GestionErroresExp.errorSemantico(this.fila, this.columna, "La variable de la declaración del for debe tener valor inicial");

			}
			

		}
		else{
			GestionErroresExp.errorSemantico(this.fila, this.columna, "La primera declaración del for debe ser una instrucción con valor inicial");

		}


		cond.vincula(); //debemos comprobar que es de tipo bool
		paso.vincula();
		for(Ins ins:cuerpo){
			ins.vincula();
		}	
		

		TabladeSimbolos.cierraBloque();

	}
	public void chequea() {

		TTipo tipo = cond.chequea();

		if(tipo != TTipo.BOOL){
			GestionErroresExp.errorSemantico(fila, columna, "La condición del for debe ser una expresión booleana");
		}
		paso.chequea();
		
	}

	public int generaEtiqueta(int e) {
		int aux=e, aux1 = e;
		dec.generaEtiqueta(e);
		aux = e+1;
		for(Ins ins: cuerpo){
            aux1 = ins.generaEtiqueta(aux);
			aux = aux1;
        }	
		return e;

	}

	public int maxMemoria(){
		int maxSol = 0; 
		maxSol += dec.maxMemoria();
		for(Ins ins: cuerpo){
			maxSol += ins.maxMemoria();
		}
        return maxSol;
    }
	
	public void code() {
		System.out.println("block");
        System.out.println("loop");
        this.cond.code();
		paso.code();
        System.out.println("bf_if 1");
        for(Ins i: this.cuerpo){
            i.code();
        }
		this.paso.code();
        System.out.println("br 0");
        System.out.println("end");
        System.out.println("end");
            
	}
 
	
}