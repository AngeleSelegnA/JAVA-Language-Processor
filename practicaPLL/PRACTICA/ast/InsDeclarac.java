package ast;

import errors.GestionErroresExp;

public class InsDeclarac extends Ins{
    private Tipo tipo;
	private Exp id;
	private Exp valor;
    boolean conValorIni = false;
    private int etiqueta;

    public InsDeclarac(Tipo tipo, Exp id, Exp valor, int fila, int col){
        this.tipo=tipo;
        this.id=id;
        this.valor=valor;
        this.fila=fila;
        this.columna=col;
        this.conValorIni = true;
    }
    public InsDeclarac(Tipo tipo, Exp id, int fila, int col){
        this.tipo=tipo;
        this.id=id;
        this.fila=fila;
        this.columna=col;
        this.conValorIni = false;

    }


 
    public TIns tipoIns() { return TIns.DECLARAC;}


    public String toString() {
       String sol= "Declaración (Tipo: "+ tipo.toString()+" Nombre: "+ id.toString();
       if(valor!=null){
           sol=sol+" Valor inicial: "+ valor.toString();
       }
       return sol + "\n";
    }


    public boolean conValorIni(){
        return conValorIni;
    }

    public Exp getId(){
        return this.id;
    }

    public Tipo getTipo(){
        return this.tipo;
    }

    public int getEtiqueta(){
        

        return this.etiqueta;
    }

    public void vincula() {
        Iden iden = (Iden) id;
        //debemos comprobar que no estemos declarando algo que ya existe en este ambito
        Nodo nodo = TabladeSimbolos.getDeclaracion(iden.getId());

        if(nodo == null){
            TabladeSimbolos.insertaId(iden.getId(), this); //this es una ins que extiende de nodo asi que es un nodo
            iden.vincula();
            //no existe
            if(this.conValorIni == true){
                valor.vincula(); //valor puede ser una expresion y no solo un numero, char...
            }
        }
        else{
            GestionErroresExp.errorSemantico(fila, columna, "El identificador ya existe. Tomamos la primera declaración.");}
       
        
    }
    public void chequea() {
        if(conValorIni){
            TTipo t1 = tipo.tipoTipo();
            TTipo t2 = valor.chequea();
            if(t1 != t2 && t1 != TTipo.FLOAT){
                GestionErroresExp.errorSemantico(fila, columna, "Los tipos no son compatibles en la declaración con valor incial.");
            }
            else if(t1 != t2 && t2 != TTipo.INT){//el primero es float, solo es error si el segundo no es int ni float
                GestionErroresExp.errorSemantico(fila, columna, "Los tipos no son compatibles en la declaración con valor incial.");
            }
        }
        
    }


    int maxMemoria(int c, int max){
        Iden identificador= (Iden) id;
        int tam_dec=identificador.tamano();
        if(c+tam_dec>max)max=c+tam_dec;
        return max;
    }

    public void code(){
        if(conValorIni){ 
            Iden identificador= (Iden) id;
            identificador.code(); 
            valor.code();
            System.out.println("i32.store");
        }
    }

    public int generaEtiqueta(int e){
        Iden identificador= (Iden) id;
        etiqueta = e + identificador.tamano();
        return etiqueta;
    }
    public int maxMemoria(){
        Iden identificador= (Iden) id;
        int tam_dec=identificador.tamano();
        return tam_dec;
    }
}