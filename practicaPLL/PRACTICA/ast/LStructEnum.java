package ast;
import java.util.ArrayList;


public class LStructEnum extends Nodo{
    private ArrayList<Ins> structs;
    private ArrayList<Ins>enums;
    
    public LStructEnum(){
        this.structs=new ArrayList<Ins>();
        this.enums=new ArrayList<Ins>();
    }

    public TNodo tipoNodo() {
        return TNodo.STRUCTENUM;
    }
    public  int getTam(){
        return(structs.size()+enums.size());
    }
    
    public String toString() {
        String st="", en="";
        if(structs.size() > 0){
            int i=0;
            while(i<structs.size()-1){
                st += structs.get(i).toString()+", ";
                ++i;
            }
            st += structs.get(i).toString() + "\n";
        }
        if(enums.size() > 0){
            int k=0;
            while(k<enums.size()-1){
                en+= enums.get(k).toString()+", ";
                ++k;
            }
            en+= enums.get(k).toString()+ "\n";
        }
        return (st+en) + "\n"; 
    }

    public void addS(Ins s){structs.add(s);}
    public void addE(Ins e){enums.add(e);}


    public void vincula() {
        for(Ins str:structs) str.vincula();
        for(Ins enm:enums) enm.vincula();
    }

    public void chequea() {
        for(Ins str:structs) str.chequea();
        for(Ins enm:enums) enm.chequea();
    }
    
}