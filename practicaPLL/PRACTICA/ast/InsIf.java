package ast;
import java.util.List;
import errors.GestionErroresExp;
public class InsIf extends Ins{

    private Exp cond;
    private List<Exp> condsElif;
    private List<Ins> cuerpoIf; 
    private List<List<Ins>> cuerposElif;
    private List<Ins> cuerpoElse; 
    private Integer numElif = 0; //numero de elifs
    private boolean ifElse = false; //nos indica si existe parte else
    
    public InsIf(Exp cond, List<Ins> cuerpoIf, int fila, int col){
            super();
            this.cond = cond;
            this.cuerpoIf = cuerpoIf;
            this.ifElse = false;
            this.numElif = 0;
            this.fila=fila;
            this.columna=col;
    }

    public InsIf(Exp cond, List<Ins> cuerpoIf, List<Ins> cuerpoElse, int fila, int col){
        super();
        this.cond = cond;
        this.cuerpoIf = cuerpoIf;
        this.cuerpoElse = cuerpoElse;
        this.ifElse = true;
        this.numElif = 0;
        this.fila=fila;
        this.columna=col;
    }

    
    public InsIf(Exp cond, List<Ins> cuerpoIf, List<Exp> condsElif, List<List<Ins>> cuerposElif, Integer tam, int fila, int col){
        super();
        this.cond = cond;
        this.cuerpoIf = cuerpoIf;
        this.condsElif = condsElif;
        this.cuerposElif = cuerposElif;
        this.numElif = condsElif.size(); //tam es redundante, pues es condsElif.size() pero asi conseguimos q no de error de erasure pq tiene mas parametros
        this.ifElse = false;
        this.fila=fila;
        this.columna=col;

    }

    public InsIf(Exp cond, List<Ins> cuerpoIf, List<Exp> condsElif, List<List<Ins>> cuerposElif, List<Ins> cuerpoElse, Integer tam, int fila, int col){
        super();
        this.cond = cond;
        this.cuerpoIf = cuerpoIf;
        this.condsElif = condsElif;
        this.cuerposElif = cuerposElif;
        this.cuerpoElse = cuerpoElse;
        this.numElif = condsElif.size();
        this.ifElse = true;
        this.fila=fila;
        this.columna=col;

    }


    public TIns tipoIns(){return TIns.IF;}

    public String toString(){
        String instrucciones = "Instrucción If( condición If: " + cond.toString() + "Instrucciones If: ";
        for (Ins ins : cuerpoIf){
            instrucciones = instrucciones + ins + ", ";
        }
        
        if(numElif > 0){
            for(int i = 0; i < numElif; ++i){
                instrucciones = instrucciones + "Condición Elif " + i+1 + ": " + this.condsElif.get(i).toString() + "Instrucciones Elif " + i+1 + ": ";

                for (Ins ins : this.cuerposElif.get(i)){
                    instrucciones = instrucciones + ins + ", ";
                }

            }
        }

        if (ifElse == true){
            instrucciones = instrucciones + "Instrucciones Else: ";
            for(Ins ins: cuerpoElse){
                instrucciones = instrucciones + ins+ ", ";
            }
        }
        return instrucciones + " )\n";

    }



	public void setCuerpoElse(List<Ins> cuerpoElse) {
		this.cuerpoElse = cuerpoElse;
	}

    public void vincula() {
        cond.vincula();
        TabladeSimbolos.abreBloque();
        for(Ins ins: cuerpoIf){
            ins.vincula();
        }
        TabladeSimbolos.cierraBloque();

        for(int i = 0; i < numElif; ++i){
            TabladeSimbolos.abreBloque();
            condsElif.get(i).vincula();
            for(Ins ins: cuerposElif.get(i)){
                ins.vincula();
            }
            TabladeSimbolos.cierraBloque();
        }
        if(ifElse){
            TabladeSimbolos.abreBloque();

            for(Ins ins: cuerpoElse){
                ins.vincula();
            }
            TabladeSimbolos.cierraBloque();
        }
        
    }

    @Override
    public void chequea() {
        TTipo tipo = cond.chequea();

        if(tipo != TTipo.BOOL){
			GestionErroresExp.errorSemantico(fila, columna, "La condición del if debe ser una expresión booleana");
		}
        for(Ins ins: cuerpoIf){
            ins.chequea();
        }

        for(int i = 0; i < numElif; ++i){
            TTipo tipoC = condsElif.get(i).chequea();
            if(tipoC != TTipo.BOOL){
                GestionErroresExp.errorSemantico(condsElif.get(i).getFila(), condsElif.get(i).getCol(), "La condición del elif debe ser una expresión booleana");
            }
            for(Ins ins: cuerposElif.get(i)){
                ins.chequea();
            }
        }
        if(ifElse){
            for(Ins ins: cuerpoElse){
                ins.chequea();
            }
        }
        

    }

    public int generaEtiqueta(int e) {
        int aux=e , aux1 = e;
        for(Ins ins: cuerpoIf){
            aux1 = ins.generaEtiqueta(aux);
            aux = aux1;
        }
        aux = e; 
        if(this.numElif>0){
            for(List<Ins> elif : cuerposElif){
                for(Ins ins: elif){
                    aux1 = ins.generaEtiqueta(aux);
                    aux = aux1;
                }
            }
        }
        aux = e;
        if(this.ifElse){
            for(Ins ins: cuerpoElse){
                aux1 = ins.generaEtiqueta(aux);
                aux = aux1;
            }
        } 
        return e;       
    }

    public int maxMemoria() {//if
        int maxSol = 0;
        int max = 0;
        for(Ins ins: cuerpoIf){
            max += ins.maxMemoria();
        }
        if(maxSol<max) maxSol = max;
        max = 0;
        if(this.numElif>0){
            for(List<Ins> elif : cuerposElif){
                for(Ins ins: elif){
                    max = ins.maxMemoria();
                }
            }
            if(maxSol<max)maxSol = max;
            max = 0;
        }
        if(maxSol<max) maxSol = max;
        max = 0;
        if(this.ifElse){
            for(Ins ins: cuerpoElse){
                max = ins.maxMemoria();
            }
        } 
        if(maxSol<max)maxSol = max;

        return maxSol;       
    }
    public void code() {
            this.cond.code(); //esto me devuelve 0 o 1
            System.out.println("if ");
            for(Ins in:cuerpoIf){
                in.code();
            }
            
            if(numElif>0){
                for(int i = 0; i < numElif; ++i){
                    this.condsElif.get(i).code();
                    System.out.println("else if ");
                    for (Ins ins : this.cuerposElif.get(i)){
                        ins.code();
                    }   
                }
            }
            if(ifElse == true){
                System.out.println("else ");
                for (Ins in:cuerpoElse){
                    in.code();
                }
            }
            System.out.println("end");
        
        

    }

}