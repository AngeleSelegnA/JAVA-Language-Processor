package ast;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
public class P extends Nodo{
    private List<Ins>cuerpo;
    private LStructEnum dec;
    private File fich = new File("codigo.txt");
    
    public P(List<Ins> cuerpo,  LStructEnum dec,int fila,int columna) {
        this.cuerpo = cuerpo;
        this.dec=dec;
        this.fila=fila;
        this.columna=columna;
	}

    public TNodo tipoNodo() {return TNodo.PROG;}

    public String toString() {
        String programa = "";
        for (Ins elem : cuerpo){
            programa = programa + elem.toString() + ", \n";
        }
        
        return "Programa( \n" +dec.toString()+"\n"+ programa + " )";
    }
    
    public void nuevaIns(Ins ins) {
		this.cuerpo.add(0, ins);
	}

    public void vincula() {
        TabladeSimbolos.abreBloque();
        dec.vincula();
        for(Ins funcion :cuerpo){
            funcion.vincula();
        }
        
    }

    public void chequea() {
        dec.chequea();
        for(Ins funcion :cuerpo){

            funcion.chequea();
        }
        for(int i = 0; i < dec.getTam(); ++i){//cerramos aqui losbloques de los struct y enum 
            TabladeSimbolos.cierraBloque();
        }
        TabladeSimbolos.cierraBloque();//cerramos  el bloque del programa

    }
    
    public void code(){
        generaEtiqueta();
        int mem = maxMemoria();
        mem = mem*4 + 8; //byte son 4 + sp + pl
        //creamos un nuevo ambito de definicion de cosas
        System.out.println( "(module" );
        System.out.println("(type $_sig_i32ri32 (func (param i32) (result i32)))");
        System.out.println( "(type $_sig_ri32 (func (result i32)))" );
        System.out.println( "(type $_sig_void (func ))" );
        System.out.println( "(import \"runtime\" \"exceptionHandler\" (func $exception (type $_sig_i32)))");
        System.out.println( "(import \"runtime\" \"print\" (func $print (type $_sig_i32)))" );
        System.out.println( "(import \"runtime\" \"read\" (func $read (type $_sig_ri32)))" );
        System.out.println( "(memory "+ mem + ")" );
        System.out.println( "(global $SP (mut i32) (i32.const 0)) ;; start of stack" );
        System.out.println( "(global $MP (mut i32) (i32.const 0)) ;; mark pointer" );
        System.out.println( "(global $NP (mut i32) (i32.const 131071996)) ;; heap 2000*64*1024-4" );

        //Metemos aqui lo de reservar memoria y liberar porque lo necesitamos y da igual el orden
        System.out.println("(func $reserveStack (param $size i32)");
        System.out.println("(result i32)");
        System.out.println("get_global $MP"); //donde empieza el marco actual
        System.out.println("get_global $SP");
        System.out.println("set_global $MP");
        System.out.println("get_global $SP");
        System.out.println("get_local $size");
        System.out.println("i32.add");
        System.out.println("set_global $SP");
        System.out.println("get_global $SP");
        System.out.println("get_global $NP");
        System.out.println("i32.gt_u");
        System.out.println("if");
        System.out.println("i32.const 3");
        System.out.println("call $exception");
        System.out.println("end");
        System.out.println(")");
        System.out.println("(func $freeStack (type $_sig_void)");
        System.out.println("get_global $MP");
        System.out.println("i32.load offset=4");
        System.out.println("set_global $SP");
        System.out.println("get_global $MP");
        System.out.println("i32.load");
        System.out.println("set_global $MP");
        System.out.println(")");
        System.out.println( "(start $main)" ); //ahora mismo ponemos main pero luego sera la primera fun de nuestro programa
        System.out.println( "(func $main"   );
        for (Ins elem : cuerpo){
             elem.code();
        }
        

       
        
        System.out.println( ")" );
        System.out.println( ")" );
    
}
    
    public int maxMemoria(){//maximo de todas las funciones pq bloques se cierran
        int max = 0;
        for(Ins ins: cuerpo){//cada funcion
            int maxAux=0;
            maxAux=ins.maxMemoria();
        if(maxAux>max) max=maxAux;
        }
        return max;
    }

    public int generaEtiqueta(){
        int e = -1;
        int aux=e, aux1 = e;
        for(Ins ins: cuerpo){
            aux1 = ins.generaEtiqueta(aux);
            aux = aux1;
        }
        return aux;
        
    }
}

    
