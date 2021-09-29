package errors;

import alex.UnidadLexica;

public class GestionErroresExp {
  public static int erroresSintacticos=0;
  public static int erroresSemanticos=0;
   public static void errorLexico(int fila, int columna, String lexema) {
      System.out.println("ERROR LÉXICO: fila "+fila+" columna "+columna+": Caracter inexperado: "+lexema); 
      System.exit(1);
    }
    
     
   public static void errorSintactico(UnidadLexica unidadLexica) {
    ++erroresSintacticos;
    if(erroresSintacticos >= 10){
      System.out.print("Demasiados errores sintacticos. Imposible recuperarse\n");
      System.exit(1);
    }
    else{
      System.out.print("ERROR SINTÁCTCO: fila "+unidadLexica.fila()+" columna "+unidadLexica.columna()+". ");
    }
     
    
   }
   public static void errorSemantico(int fila, int columna, String mensaje) {
    erroresSemanticos++;
   if (erroresSemanticos >= 15) {
     System.out.println("Demasiados errores semánticos. Imposible recuperarse\n");
     System.exit(1);
   }
   System.out.println("ERROR SEMANTICO: fila: " + fila + " columna: " + columna + ". " + mensaje + '\n');
 }
}
