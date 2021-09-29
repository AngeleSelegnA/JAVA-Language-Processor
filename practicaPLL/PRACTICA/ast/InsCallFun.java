package ast;
import java.util.ArrayList;
import java.util.List;

import errors.GestionErroresExp;
public class InsCallFun extends Ins{

    private Exp iden;
    private List<Exp> argumentos;
    private Nodo nodoFun;
    private int numArgs = 0;

    

    public InsCallFun(Exp iden, List<Exp> argumentos, int fila, int col){
        super();
        this.iden = iden;
        this.argumentos = argumentos;
        this.numArgs = argumentos.size();
        this.fila=fila;
        this.columna=col;

    }
    public InsCallFun(Exp iden, int fila, int col){
        super();
        this.iden = iden;
        this.numArgs=0;
        this.fila=fila;
        this.columna=col;
        this.argumentos=new ArrayList<Exp>();
    }

    public TIns tipoIns(){return TIns.CALL;}

    public String toString(){
     
        String args = "(";
        for (Exp arg : this.argumentos){
            args = args + arg.toString() + ", ";
        }
        return "LLamada a Función ( Nombre: " + iden.toString() + ", Argumentos: " + args + " ))\n";
    
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
            for(Exp argumento: argumentos){
                argumento.vincula();
            }
        }
        else{//el identificador esta pero no designa a un procedimiento, es una funcion
            GestionErroresExp.errorSemantico(this.fila, this.columna, "El nombre usado para designar al procedimiento o función existe en la tabla, pero no se corresponde con el identificador de un procedimiento.\n");

        }
    }
    @Override
    public void chequea() {
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
            }       
            else{
                GestionErroresExp.errorSemantico(fila, columna, "El número de argumentos es erróneo.");
            }
        }
    
        public int generaEtiqueta(int e){
            return e;
        }
        public int maxMemoria(){
            return 0;
        }
        public void code() {
            
        }

}