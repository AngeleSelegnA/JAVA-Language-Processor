package alex;

import java_cup.runtime.Symbol;

public class UnidadLexica extends Symbol {
   private int fila;
   private int columna;
   public UnidadLexica(int fila, int col,int clase, String lexema) {
      super(clase,new TokenValue(lexema, fila,col));
      this.fila = fila;
      this.columna=col;
   }
   public int clase () {return sym;}
   public String lexema() {return ((TokenValue)value).lexema;}
   public int fila() {return ((TokenValue)value).fila;}
   public int columna() {return ((TokenValue)value).columna;}
}
