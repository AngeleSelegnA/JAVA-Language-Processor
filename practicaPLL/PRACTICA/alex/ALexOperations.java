package alex;

import constructorast.ClaseLexica;
import errors.GestionErroresExp;

public class ALexOperations {
  private AnalizadorLexicoExp alex;
  public ALexOperations(AnalizadorLexicoExp alex) {
   this.alex = alex;   
  }
  

  public UnidadLexica unidadId() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IDEN,alex.lexema()); 
}
public UnidadLexica unidadInt() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.INT,"int"); 
}
public UnidadLexica unidadFloat() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FLOAT,"float"); 
}
public UnidadLexica unidadBool() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.BOOL,"bool"); 
}
public UnidadLexica unidadChar() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CHAR,"char"); 
}

public UnidadLexica unidadPrint() {
  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PRINT,"print"); 
}  



public UnidadLexica unidadEntero() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ENTERO,alex.lexema()); 

}public UnidadLexica unidadReal() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.REAL,alex.lexema()); 
   
}public UnidadLexica unidadCaracter() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CARACTER,alex.lexema()); 
}



public UnidadLexica unidadTrue() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.TRUE,"true"); 
} 
public UnidadLexica unidadFalse() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FALSE,"false"); 
} 
public UnidadLexica unidadStruct() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.STRUCT,"struct"); 
} 
public UnidadLexica unidadFun() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FUN,"fun"); 
} 
public UnidadLexica unidadVoid() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.VOID,"void"); 
} 
public UnidadLexica unidadCall() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CALL,"call"); 
} 
public UnidadLexica unidadWhile() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.WHILE,"while"); 
} 
public UnidadLexica unidadFor() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.FOR,"for"); 
} 
public UnidadLexica unidadIf() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IF,"if"); 
  } 
public UnidadLexica unidadElif() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ELIF,"elif"); 
} 
public UnidadLexica unidadElse() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ELSE,"else"); 
} 
public UnidadLexica unidadEnum() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ENUM,"enum"); 
} 
public UnidadLexica unidadReturn() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.RETURN,"return"); 
}


public UnidadLexica unidadSuma() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.SUMA,"+"); 
} 
public UnidadLexica unidadResta() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.RESTA,"-"); 
} 
public UnidadLexica unidadMul() {
  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MULTI,"*"); 
} 
public UnidadLexica unidadDiv() {
  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DIV,"/"); 
} 
public UnidadLexica unidadMod() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MODULO,"%"); 
}
public UnidadLexica unidadAnd() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.AND,"&&"); 
}
public UnidadLexica unidadOr() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.OR,"||"); 
}
public UnidadLexica unidadNot() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.NOT,"!"); 
}
public UnidadLexica unidadIgual() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IGUAL,"="); 
} 
public UnidadLexica unidadIgualIgual() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.IGUALIGUAL,"=="); 
} 
public UnidadLexica unidadDistinto() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.DISTINTO,"!="); 
} 
public UnidadLexica unidadMayor() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAYOR,">");
}  
public UnidadLexica unidadMenor() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENOR,"<");  
} 
public UnidadLexica unidadMayorIgual() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MAYORIGUAL,">=");
}  
public UnidadLexica unidadMenorIgual() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENORIGUAL,"<="); 
} 
public UnidadLexica unidadMasMas() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MASMAS,"++"); 
} 
public UnidadLexica unidadMenosMenos(){
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.MENOSMENOS,"--"); 
} 


public UnidadLexica unidadCAp() {
  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CAP,"["); 
} 
public UnidadLexica unidadCCierre() {
  return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.CCIERRE,"]"); 
}
public UnidadLexica unidadPAp() {
return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PAP,"("); 
} 
public UnidadLexica unidadPCierre() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PCIERRE,")"); 
} 
public UnidadLexica unidadLLAp() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LLAP,"{"); 
} 
public UnidadLexica unidadLLCierre() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.LLCIERRE,"}"); 
}  



public UnidadLexica unidadComa() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.COMA,",");  
} 
public UnidadLexica unidadPuntoComa() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.PUNTOCOMA,";");  
} 
public UnidadLexica unidadPunto() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ACCESOSTRUCT,".");  
} 
public UnidadLexica unidadDosPuntos() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.ACCESOENUM,":");  
} 

public UnidadLexica unidadBarra() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.BARRA,"|");  
} 
public UnidadLexica unidadEof() {
   return new UnidadLexica(alex.fila(),alex.columna(),ClaseLexica.EOF,"<EOF>"); 
  }

public void error(){
   GestionErroresExp.errorLexico(alex.fila(),alex.columna(),alex.lexema());
}
}
