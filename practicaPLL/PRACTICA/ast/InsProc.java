package ast;
import java.util.ArrayList;
import java.util.List;

import errors.GestionErroresExp;


public class InsProc extends Ins{

    private Exp iden;
    private List<Parametro> parametros; //no se si mejor <Ins>
    private List<Ins> cuerpo;
    private int numParametros = 0;


    public InsProc(Exp iden, List<Parametro> parametros, List<Ins> cuerpo, int fila, int col){
        super();
        this.iden = iden;
        this.parametros=parametros;
        this.cuerpo=cuerpo;
        this.numParametros = parametros.size();
        this.fila=fila;
        this.columna=col;
    }
    public InsProc(Exp iden, List<Ins> cuerpo, int fila, int col){
        super();
        this.iden = iden;
        this.cuerpo=cuerpo;
        this.numParametros = 0;
        this.fila=fila;
        this.columna=col;
        this.parametros=new ArrayList<Parametro>();
    }
    
    public TIns tipoIns(){return TIns.PROC;}


    public String toString(){
        String params = "";
        String instrucciones = "";
        for(Parametro parametro: this.parametros){
            params = params + parametro.toString() + ", ";
        }
        
        for(Ins ins: this.cuerpo){
            instrucciones = instrucciones + ins.toString() + ", ";
        }
        return "PROCEDIMIENTO void (NOMBRE: " + iden.toString() + ", PARÁMETROS: " + params + ", INSTRUCCIONES: " + instrucciones + " )\n\n"; 

    }
    


    public int getNumParametros(){
        return this.numParametros;
    }

    public Exp getIden(){
        return this.iden;
    }

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
        Iden id = (Iden) iden;
        boolean ok = TabladeSimbolos.insertaId(id.getId(), this);
        if(ok){
            TabladeSimbolos.abreBloque();
            for (Parametro p : parametros){
                p.vincula();
            }
            for(Ins i : cuerpo){
                i.vincula();
            }


            TabladeSimbolos.cierraBloque();
        }

    }
    public void chequea(){
		for(Parametro p: this.parametros) {
			if(p.getTipo().tipoTipo() == TTipo.ARRAY) {
					GestionErroresExp.errorSemantico(this.getFila(), this.getCol(), "Error tipos InsFun: en el parámetro " + ((Iden)p.getIden()).getId() +". Los parámetros de una función no pueden ser de tipo vector");
			}
		}
        for(Ins ins: this.cuerpo){
            if(ins.tipoIns()==TIns.RETURN){
                GestionErroresExp.errorSemantico(fila, columna, "Error, los procedimientos no pueden hacer return.");
            }
            else{
                ins.chequea();
            }
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
    public int maxMemoria(){ //insfun
        int maxSol = 0;
        for(Ins ins: this.cuerpo){
            maxSol +=  ins.maxMemoria();
            
        }
        return maxSol;
    }
    public void code(){
        
        for (Ins elem: cuerpo){
            elem.code();
        }
    }
}
 
 
