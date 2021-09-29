package ast;
import java.util.List;

import errors.GestionErroresExp;
public class InsWhile extends Ins{

    private Exp condicion;
    private List<Ins> cuerpo;

    public InsWhile(Exp condicion, List<Ins> cuerpo,int fila, int col){
        super();
        this.condicion = condicion;
        this.cuerpo = cuerpo;
        this.fila=fila;
        this.columna=col;
    }

    public TIns tipoIns(){return TIns.WHILE;}

    public String toString(){
    
        String inscuerpo = "";
        for (Ins instrucc : this.cuerpo){
            inscuerpo = inscuerpo + instrucc.toString() + ", ";
        }
        return "While( Condicion: " + condicion.toString() + ", Cuerpo del while: " + inscuerpo + " )\n";
    
    }

    public Exp getCondicion(){
        return this.condicion;
    }

    public List<Ins> getCuerpo(){
        return this.cuerpo;
    }

    public void vincula(){
        //no necesito aqui mirar cosas de si estan en la tabla identificadores porque ya la vinculacion de cond si lo necesita
        condicion.vincula();//va fuera porque no es una declaracion de dentro como pasaba e el for
        TabladeSimbolos.abreBloque();
        for( Ins inst: this.cuerpo){
            inst.vincula();
        }
        TabladeSimbolos.cierraBloque();
    }

    public void chequea() {
        TTipo t = condicion.chequea();
        if(t != TTipo.BOOL){
            GestionErroresExp.errorSemantico(fila, columna, "Error, el tipo de la condición debe ser booleano.");
        }

        for(Ins ins: cuerpo){
            ins.chequea();
        }


    }

    public int generaEtiqueta(int e){
        int aux=e, aux1 = e;
        for(Ins ins: cuerpo){
            aux1 = generaEtiqueta(aux);
            aux = aux1;
        }
        return e;
    }

    public int maxMemoria(){
		int maxSol = 0; 
		for(Ins ins: cuerpo){
			maxSol += ins.maxMemoria();
		}
        return maxSol;
    }
    public void code() {
        System.out.println("block");
        System.out.println("loop");
        this.condicion.code();
        System.out.println("bf_if 1");
        for(Ins i: this.cuerpo){
            i.code();
        }
        System.out.println("br 0");
        System.out.println("end");
        System.out.println("end");
            
    }

}