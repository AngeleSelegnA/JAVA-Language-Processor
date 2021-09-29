package ast;
import java.util.List;

public class AS {
  public Exp Suma(Exp op1, Exp op2, int fila, int col) {return new Suma(op1,op2, fila, col);}  

  public Exp Resta(Exp op1, Exp op2, int fila, int col) {return new Resta(op1,op2, fila, col);}  

  public Exp Multi(Exp op1, Exp op2, int fila, int col) {return new Multi(op1,op2, fila, col);}

  public Exp Div(Exp op1, Exp op2, int fila, int col) {return new Div(op1,op2, fila, col);}  

  public Exp NumEntero(String num, int fila, int col) {return new NumEntero(num, fila, col);}      
 
  public Exp NumReal(String num, int fila, int col) {return new NumReal(num, fila, col);}      

  public Exp Modulo(Exp op1, Exp op2, int fila, int col) {return new Modulo(op1,op2, fila, col);}  

  public Exp MasMas(Exp op1, int fila, int col) {return new MasMas(op1, fila, col);}  

  public Exp MenosMenos(Exp op1, int fila, int col) {return new MenosMenos(op1, fila, col);}  

  public Exp Not(Exp op1, int fila, int col) {return new Not(op1, fila, col);}  

  public Exp And(Exp op1, Exp op2,int fila, int col) {return new And(op1, op2,fila,col);}  

  public Exp Or(Exp op1, Exp op2, int fila, int col) {return new Or(op1, op2, fila, col);}  

  public Exp Mayor(Exp op1, Exp op2, int fila, int col) {return new Mayor(op1, op2, fila, col);}  

  public Exp Menor(Exp op1, Exp op2, int fila, int col) {return new Menor(op1, op2, fila, col);}  

  public Exp MayorIgual(Exp op1, Exp op2, int fila, int col) {return new MayorIgual(op1, op2, fila, col);}  

  public Exp MenorIgual(Exp op1, Exp op2, int fila, int col) {return new MenorIgual(op1, op2, fila, col);}  

  public Exp Igual(Exp op1, Exp op2, int fila, int col) {return new Igual(op1, op2, fila, col);}  

  public Exp IgualIgual(Exp op1, Exp op2, int fila, int col) {return new IgualIgual(op1, op2, fila, col);}  

  public Exp Distinto(Exp op1, Exp op2, int fila, int col) {return new Distinto(op1, op2, fila, col);}  
  
  public Exp Corchetes(Exp op1, Exp op2, int fila, int col) {return new Corchetes(op1, op2, fila, col);}  

  public Exp AccesoStruct(Exp op1, Exp op2, int fila, int col) {return new AccesoStruct(op1, op2, fila, col);}  //acceso a struct

  public Exp AccesoEnum(Exp op1, Exp op2, int fila, int col) {return new AccesoEnum(op1, op2, fila, col);}  //acceso a enum

  public Exp True(int fila, int col) {return new True(fila, col);}  

  public Exp False(int fila, int col) {return new False( fila, col);}  

  public Exp Caracter(String c, int fila, int col) {return new Caracter(c, fila, col);}  

  public Exp Iden(String id, int fila, int col) {return new Iden(id, fila, col);}  

  public Exp CallFun(Exp iden, List<Exp> argumentos, int fila, int col) {return new CallFun(iden, argumentos, fila, col);}  

  public Exp CallFun(Exp iden, int fila, int col) {return new CallFun(iden, fila, col);}  
  
  public Ins InsCallFun(Exp iden, List<Exp> argumentos, int fila, int col) {return new InsCallFun(iden, argumentos, fila, col);}  

  public Ins InsCallFun(Exp iden, int fila, int col) {return new InsCallFun(iden, fila, col);}  

  public Ins InsAsignac(Exp iden, Exp valor, int fila, int col){return new InsAsignac(iden, valor, fila, col);}

  public Ins InsFun(Tipo tipo, Exp iden, List<Parametro> parametros, List<Ins> cuerpo, int fila, int col){return new InsFun(tipo, iden, parametros, cuerpo, fila, col);}

  public Ins InsFun(Tipo tipo, Exp iden, List<Ins> cuerpo, int fila, int col){return new InsFun(tipo, iden, cuerpo, fila, col);}

  public Ins Array(Tipo tipo, Exp iden, List<Integer> tams, Exp valorInicial, int fila, int col) {return new Array(tipo, iden,tams, valorInicial, fila, col);} 

  public Ins Array(Tipo tipo,Exp iden, List<Integer> tams, int fila, int col) {return new Array(tipo,iden, tams, fila,  col);}  

  public Ins InsIf(Exp cond, List<Ins> cuerpoIf, int fila, int col){return new InsIf(cond, cuerpoIf, fila, col);}
               
  public Ins InsIf(Exp cond, List<Ins> cuerpoIf, List<Ins> cuerpoElse, int fila, int col){return new InsIf(cond, cuerpoIf, cuerpoElse, fila, col);}
      
  public Ins InsIf(Exp cond, List<Ins> cuerpoIf, List<Exp> condsElif, List<List<Ins>> cuerposElif, int aux, int fila, int col){return new InsIf(cond, cuerpoIf, condsElif, cuerposElif, aux, fila, col);}
           
  public Ins InsIf(Exp cond, List<Ins> cuerpoIf, List<Exp> condsElif, List<List<Ins>> cuerposElif, List<Ins> cuerpoElse, Integer aux, int fila, int col){return new InsIf(cond, cuerpoIf, condsElif, cuerposElif, cuerpoElse, aux, fila, col);}

  public Ins InsFor(Ins ins, Exp cond, Exp paso, List<Ins> cuerpo, int fila, int col){return new InsFor(ins, cond, paso, cuerpo, fila, col);}

  public Ins InsProc(Exp iden, List<Parametro> parametros, List<Ins> cuerpo, int fila, int col){return new InsProc(iden, parametros, cuerpo, fila, col);}
     
  public Ins InsProc(Exp iden,  List<Ins> cuerpo, int fila, int col){return new InsProc(iden, cuerpo, fila, col);}
  
  public Ins InsReturn(Exp valor, int fila, int col){return new InsReturn(valor, fila, col);}

  public Ins InsDeclarac(Tipo tipo, Exp id, Exp valor, int fila, int col){return new InsDeclarac(tipo, id, valor,fila,col);}
  
  public Ins InsDeclarac(Tipo tipo, Exp id,int fila, int col){return new InsDeclarac(tipo, id, fila, col);}
            
  public Ins InsEnum(Exp iden, List<Exp>cuerpo, int fila, int col){return new InsEnum(iden, cuerpo,fila,col);}
          
  public Ins InsStruct(Exp iden, List<Ins> elemsStruct, int fila, int col){return new InsStruct(iden, elemsStruct,fila,col);}
  
  public Ins InsWhile(Exp condicion, List<Ins> cuerpo, int fila, int col){return new InsWhile(condicion, cuerpo,fila,col);}

  public Ins InsPrint(Exp imp, int fila, int col){return new InsPrint(imp,fila,col);}

  public P Prog(List<Ins> cuerpo, LStructEnum dec, int fila, int col ){return new P(cuerpo, dec ,fila,col);}  
	
  public Tipo TInt() 
	{return new TInt();}
  
  public Tipo TBool() 
  {return new TBool();}
      	
  public Tipo TChar() 
  {return new TChar();}
  	
  public Tipo TFloat() 
  {return new TFloat();}
  	
  public Tipo TArray(Tipo tipo, int fila, int col) 
  {return new TArray(tipo, fila, col);}
	
  public  LStructEnum LStructEnum(){return new LStructEnum();}

	public Parametro Parametro(Tipo tipo, Exp iden, int fila, int col) {return new Parametro(tipo, iden, fila, col);}

}