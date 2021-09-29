package ast;

import java.util.List;
import errors.GestionErroresExp;

public class Iden extends Exp{
    private String id;
    private Nodo nodo;
    private TTipo tipo;

    public Iden(String id, int fila, int col){
        this.id = id;
        this.fila=fila;
        this.columna=col;
    }
    
    public TExp tipoExp(){return TExp.IDEN;}

    public String toString(){
        return "Identificador: (" + id + ")" ;
    }
    public void setTipo(TTipo tipo){
        this.tipo = tipo;
    }
    public TTipo getTipo(){
        return this.tipo;
    }

    public String getId(){return this.id;}

    public Nodo getNodo(){return this.nodo;}

    public void setNodo(Nodo nodo){
        this.nodo = nodo;
    }
    public void vincula(){
        Nodo refNodo= TabladeSimbolos.getDeclaracion(this.id);
            if(refNodo==null){//si no lo encuentra significa que esa variable no estaba declarada
                GestionErroresExp.errorSemantico(this.getFila(), this.getCol(), "La variable "+ this.id+" no está declarada\n");
            }
            else{ //si que esta en mi hashmap
                //al identificador le hago un set del nodo que tiene asociado en la tabla
                this.nodo=refNodo;
                if(nodo.tipoNodo()==TNodo.INS && ((Ins) nodo).tipoIns() == TIns.DECLARAC){ //SOY UNA INS DE DECLARACION
                    this.tipo = ((InsDeclarac) nodo).getTipo().tipoTipo();//el ttipo de la ins de declaracion sera el tipo de la delcaracion: int x = 3 es de ttipo int
                } 
                else if(nodo.tipoNodo() == TNodo.PARAM){
                    this.tipo = (((Parametro) nodo).getTipo()).tipoTipo();
                }
                else if(nodo.tipoNodo() == TNodo.INS && ((Ins) nodo).tipoIns() == TIns.STRUCT){
                    this.tipo = TTipo.IDSTRUCT;
                }
                else if(nodo.tipoNodo() == TNodo.INS && ((Ins) nodo).tipoIns() == TIns.ARRAY){
                    this.tipo = TTipo.ARRAY;
                }
                else if(nodo.tipoNodo() == TNodo.INS && ((Ins) nodo).tipoIns() == TIns.ENUM){
                    this.tipo = TTipo.IDENUM;
                }
               
            }
    }


    public TTipo chequea() {

        return this.tipo;

    }
    
    public int tamano(){
        int sol=1;
        switch(this.tipo){
            case INT: sol=1;
            break;

            case FLOAT: sol=1;
            break;

            case BOOL: sol=1;
            break;
            
            case ARRAY:{
                Array nodoA= (Array)this.nodo;
                List<Integer>tamanos=nodoA.getTams();
                for(int t: tamanos){
                    sol=sol*t;
                }
            }
            break;
            case IDSTRUCT:{
                InsStruct struct = (InsStruct) this.nodo;
                sol=0;
                for(Ins ins: struct.getElemsStruct()){
                    InsDeclarac dec= (InsDeclarac) ins;
                    Iden ident= (Iden) dec.getId();
                    sol+=ident.tamano();
                }
            }

            break;

            default:
            break;

        }
        return sol;
    }

    public void code(){
        if(nodo.tipoNodo()==TNodo.INS && ((Ins) nodo).tipoIns() == TIns.DECLARAC){ 
        InsDeclarac dec= (InsDeclarac)nodo;
        int etiqueta=dec.getEtiqueta();
        System.out.println("i32.const "+ etiqueta);
        System.out.println("i32.const 4");
        System.out.println("i32.mul");
        System.out.println("get_local ($localsStart)");
        System.out.println("i32.add");
        }
      
    }



    

}
