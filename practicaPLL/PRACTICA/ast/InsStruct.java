package ast;
import java.util.List;

public class InsStruct extends Ins{

    private Exp iden;
    private List<Ins> elemsStruct; //será una lista de declaraciones, ya que los elementos de un struct son declaraciones de tipos

    public InsStruct( Exp iden, List<Ins> elemsStruct, int fila, int col){
        this.iden = iden;
        this.elemsStruct = elemsStruct;
        this.fila=fila;
        this.columna=col;
    }

    public TIns tipoIns(){return TIns.STRUCT;}

    public String toString(){
        String elementos = ""; 
        for(Ins elem: this.elemsStruct){
            elementos = elementos + elem.toString() + ", ";
        }

        return "STRUCT( NOMBRE: " + iden.toString() + ", ELEMENTOS: " + elementos + " )\n";
    }


    public Exp getIdStruct(){
        return this.iden;
    }

    public List<Ins> getElemsStruct(){
        return this.elemsStruct;
    }

   
    public void vincula(){
        Iden id= (Iden) this.iden;
        //me inserto a mi con mi id que es insstruct.getId, meto mi propio nodo  que es insstruct porque es this, y this es un nodo
        boolean ok = TabladeSimbolos.insertaId(id.getId(), this);
        TabladeSimbolos.abreBloque();
        
        //para cada elemento del struct tb lo vinculo cada uno
        if(ok){
                
            for (Ins instruccion : this.elemsStruct) {
                instruccion.vincula();
            }
        }

} 

    public void chequea() {
        for(Ins ins: elemsStruct){
           ins.chequea();
        }
    }

    public int generaEtiqueta(int e){
        return e;
    }

    @Override
    public int maxMemoria() {
        // TODO Auto-generated method stub
        return 0;
    }
    public void code() {
            
    }
}

