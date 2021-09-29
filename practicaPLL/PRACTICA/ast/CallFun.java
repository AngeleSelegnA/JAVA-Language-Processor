package ast;
import java.util.List;

import errors.GestionErroresExp;

public class CallFun extends Exp {


    private Exp iden;
    private List<Exp> argumentos;

    private Nodo nodoFun;

    private int numArgs = 0;

    

    public CallFun(Exp iden, List<Exp> argumentos, int fila, int col){
        super();
        this.iden = iden;
        this.argumentos = argumentos;
        this.numArgs = argumentos.size();
        this.fila=fila;
        this.columna=col;

    }
    public CallFun(Exp iden, int fila, int col){
        super();
        this.iden = iden;
        this.numArgs=0;
        this.fila=fila;
        this.columna=col;
    }

    public TExp tipoExp() {
        return TExp.CALL;

    }
    public TIns tipoIns(){return TIns.CALL;}

    public String toString(){
    
        String args = "";
        if(this.numArgs>0){
            for (Exp arg : this.argumentos){
                args = args + arg.toString() + ", ";
            }
        }
        return "call( nombre: " + iden.toString() + ", argumentos: " + args + " )\n";
    
    }

    public Exp getId(){
        return this.iden;
    }

    public List<Exp> getArgumentos(){
        return this.argumentos;
    }

    public int getNumArgs(){
        return this.numArgs;
    }
    
  
    public void vincula() {

        Iden id =  (Iden) iden;

        Nodo refNodo =  TabladeSimbolos.getDeclaracion(id.getId()); //referencia al nodo de la tabla que tiene el id de la funcion

        Ins ins = (Ins) refNodo;

        if(refNodo == null){//el identificador no esta
            GestionErroresExp.errorSemantico(this.fila, this.columna, "El procedimiento o función llamada no existe.\n");
        }
        else if(ins.tipoIns() == TIns.PROC || ins.tipoIns()==TIns.FUN){//el identificador esta y designa a un procedimiento o a una funcion

            this.nodoFun = refNodo;

            if(this.numArgs>0){
                for(Exp argumento: argumentos){

                argumento.vincula();
                }
            }
        }
        else{//el identificador esta pero no designa a un procedimiento, es una funcion
            GestionErroresExp.errorSemantico(this.fila, this.columna, "El nombre usado para designar al procedimiento o función existe en la tabla, pero no se corresponde con el identificador de un procedimiento.\n");

        }
    }
    public TTipo chequea() {
        TTipo t = null;
        Iden id =  (Iden) iden;
        nodoFun = TabladeSimbolos.getDeclaracion(id.getId());//obtengo el nodo de esta funcion
		if (((Ins) nodoFun).tipoIns() == TIns.FUN && ((InsFun) nodoFun).getNumParametros() == numArgs) {
			boolean ok = true;
            List<Parametro> params = ((InsFun) nodoFun).getParametros();
			for (int i = 0; i < numArgs; ++i) {//debo comprobar que el tipo de los parametros y de los argumentos coincide
                TTipo tipoParam = params.get(i).getTipo().tipoTipo();
                Iden idArg = (Iden) argumentos.get(i);
				ok =  (idArg.getTipo() == tipoParam);
                if(!ok){
                    GestionErroresExp.errorSemantico(fila, columna, "El tipo de uno de los argumentos no coincide con el de la función");
                }
            }
            t = ((InsFun) nodoFun).getTipo().tipoTipo();//tipo de return de la funcion
        }
        else if (((Ins) nodoFun).tipoIns() == TIns.PROC && ((InsProc) nodoFun).getNumParametros() == numArgs) {
			boolean ok = true;
            List<Parametro> params = ((InsProc) nodoFun).getParametros();
			for (int i = 0; i < numArgs; ++i) {//debo comprobar que el tipo de los parametros y de los argumentos coincide
                TTipo tipoParam = params.get(i).getTipo().tipoTipo();
                Iden idArg = (Iden) argumentos.get(i);
				ok =  (idArg.getTipo() == tipoParam);
                if(!ok){
                    GestionErroresExp.errorSemantico(fila, columna, "El tipo de uno de los argumentos no coincide con el de la función");
                }
            }
            t = TTipo.VOID;
        }       
        else{
            GestionErroresExp.errorSemantico(fila, columna, "El identificador no se corresponde con una función/procedimiento o el número de argumentos es erróneo.");
        }
        return t;
    }
    @Override
    public void code() {
        // TODO Auto-generated method stub
        
    }


    
}
