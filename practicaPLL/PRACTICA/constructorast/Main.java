package constructorast;

import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.io.Reader;
import alex.AnalizadorLexicoExp;
import errors.GestionErroresExp;
import ast.P;

public class Main {
   public static void main(String[] args) throws Exception {
     Reader input = new InputStreamReader(new FileInputStream(args[0]));
	 AnalizadorLexicoExp alex = new AnalizadorLexicoExp(input);
	 ConstructorASTExp constructorast = new ConstructorASTExp(alex);

	 //El analisis de la semantica se hace sobre el ast
	 P programa=(P)constructorast.parse().value; //aqui tengo mi programa parseado, tengo el ast 
	 
	 if(GestionErroresExp.erroresSintacticos==0){
		System.out.println(programa);
		programa.vincula();//si no tiene errores sintacticos hago las comprobaciones semanticas y si no hay errores
		//sera apto para ser traducido. En caso contrario se mostrarán los errores y se rechazara el progrma 
		programa.chequea();
	 }

	 if(GestionErroresExp.erroresSemanticos==0 && GestionErroresExp.erroresSintacticos==0){
		 programa.code();
	 }
	 
 }
}   
   
