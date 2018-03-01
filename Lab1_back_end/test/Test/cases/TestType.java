package Test.cases;

import cr.ac.una.moviles.lab1.bl.TipoBL;
import java.util.List;

/**
 *
 * @author _Adrián_Prendas_
 */
public class TestType {
    public static TipoBL tbl= new TipoBL();
    
    
    public static void main(String [] args){
        List lista = tbl.findAll();
        lista.forEach(t->System.out.println(t));
    }
    
}
