package ast;
import java.util.List;

public class InsEnum extends Ins{
 
    private Exp iden;
    private List<Exp> cuerpo; //sera lista de ctes 

    public InsEnum(Exp iden, List<Exp>cuerpo, int fila, int col){
        super();
        this.iden=iden;
        this.cuerpo=cuerpo;
        this.fila=fila;
        this.columna=col;
    }
	
    public TIns tipoIns() {return TIns.ENUM;}

	public Exp getId() {
		return iden;
	}

	public List<Exp> getConstantes() {
		return cuerpo;
	}


    public String toString() {
        String sol= "ENUM (NOMBRE: "+ iden.toString()+ " VALORES: ";
        for(Exp e : cuerpo){
            sol = sol + e.toString()+", ";
        }
        return sol+ "\n";
    }
    
    public void vincula(){
        //no abro nuevo bloque y asi no puedo tener dos enums con mismo campo
        //cojo el identificador de enum
        Iden identificador= (Iden) this.iden;
        //lo meto en mi tabla 
        TabladeSimbolos.insertaId(identificador.getId(),this);
        //para cada uno de los elementos de enum que seran otros identificadores hago lo mismo
        TabladeSimbolos.abreBloque();
        for(Exp idLista: cuerpo){
            Iden idenLista= (Iden)idLista;
            TabladeSimbolos.insertaId( idenLista.getId(), this);
        }
    }
    public void chequea() {
        for(Exp exp: cuerpo){
           exp.chequea();
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