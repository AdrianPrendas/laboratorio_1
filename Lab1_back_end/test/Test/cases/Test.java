package Test.cases;

import cr.ac.una.moviles.lab1.dao.ProductoDao;
import cr.ac.una.moviles.lab1.domain.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author _Adrián_Prendas_
 */
public class Test {
    
    public static ProductoDao pdao = ProductoDao.getInstance();
    
    public static void create(Producto p){
        pdao.create(p);
    }
    public static void read(Producto p){
        ArrayList l = new ArrayList(pdao.read(p));
        l.forEach(p2->System.out.println(p2));
    }
    public static void update(Producto p){
        pdao.update(p);
    }
    public static void delete(Producto p){
        pdao.delete(p);
    }
    
    public static void main(String[]args){
        Producto p = new Producto(10, "weed", (float) 500.0, true,"Canasta Basica");
        //create(p);
        //read(p);
        //update(p);
        delete(p);
        
        
        
    }
}
