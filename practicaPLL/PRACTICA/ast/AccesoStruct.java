package ast;
import java.io.IOException;
import java.io.PrintWriter;

import errors.GestionErroresExp;
        
public class AccesoStruct extends ExpBinaria{
    private Tipo tipo;
    public AccesoStruct( Exp op1, Exp op2,int fila, int col){
        super(op1, op2); //op1 es el iden del struct y op2 es el campo del struct
        this.fila=fila;
        this.columna=col;
    }
            
    public TExp tipoExp() {return TExp.ACCESOSTRUCT;}
         
    public String toString() {
        return "acceso a struct: "+ op1().toString()+" campo: "+ op2().toString() + "\n";
    }
        
    public Tipo getTipo(){return tipo;}
    public void setTipo(Tipo tipo){
        this.tipo=tipo;
    }
    public void vincula(){
        Iden id = (Iden) op1();
        op1().vincula();
    }
    public TTipo chequea(){
        TTipo tipo = null;
        TTipo tipo1 = null;
        boolean es_struct = false;
        boolean es_campo = false;
        Iden idop1 = null;
        if(op1().tipoExp() == TExp.IDEN) {
            idop1 = (Iden) op1();
            es_struct = true;
            tipo1 = idop1.chequea();//si esta bien es de tipo idstruct
        }
        if(es_struct && tipo1==TTipo.IDSTRUCT){
            InsStruct nodo=(InsStruct)TabladeSimbolos.getDeclaracion(idop1.getId()); //si esta bien tengo el nombre del struct en idop1 y obtengo el nodo del struct
            for(Ins ins: nodo.getElemsStruct()){//recorro los campos del struct
                Iden idop2= (Iden)op2();
                InsDeclarac dec= (InsDeclarac) ins;
                Iden idenDecl= (Iden) dec.getId();

                if(!es_campo && idenDecl.getId().equals(idop2.getId())) {
                    tipo  = dec.getTipo().tipoTipo();
                    es_campo = true;
                 } 
                        
            }
            if(!es_campo){
                    GestionErroresExp.errorSemantico(this.getFila(), this.getCol(), "Error semántico: intentamos acceder a un campo no definido en este struct");
            }
                    
        }
            
        else{
            GestionErroresExp.errorSemantico(this.getFila(), this.getCol(), "Error semántico:el tipo por el que intentamos acceder a un campo no es un struct ");
        }
        return tipo;
    }

    public void code(){
        Iden op1= (Iden) op1();
        op1().code();
        System.out.println("i32.const "+ op1.tamano());
        System.out.println("i32.add");

        
    }
            
}