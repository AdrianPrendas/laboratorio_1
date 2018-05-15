package Test.cases;

import cr.ac.una.moviles.lab1.bl.ProductoBL;
import cr.ac.una.moviles.lab1.dao.ProductoDao;
import cr.ac.una.moviles.lab1.domain.Producto;
import java.util.ArrayList;

/**
 *
 * @author _Adrián_Prendas_
 */
public class TestProduct {
    public static ProductoBL pbl = new ProductoBL();
    public static ProductoDao pdao = ProductoDao.getInstance();
    
    public static void create(Producto p){
        //pdao.create(p);
        pbl.create(p);
    }
    public static void read(Producto p){
        /*ArrayList l = new ArrayList(pdao.read(p));
        l.forEach(p2->System.out.println(p2));*/
        ArrayList l = new ArrayList(pbl.findByName(p.getNombre()));
        l.forEach(p2->System.out.println(p2));        
    }
    public static void findByName(Producto p){
        ArrayList l = new ArrayList(pbl.findByName(p.getNombre()));
        l.forEach(p2->System.out.println(p2));        
    }
    public static void findByKey(Integer key){
        Producto p = pbl.findByKey(key);
        System.out.println(p);
    }
    public static void findByType(Producto p){
        ArrayList l = new ArrayList(pbl.findByType(p.getTipo()));
        l.forEach(p2->System.out.println(p2));        
    }
    public static void readAll(){
        /*ArrayList l = new ArrayList(pdao.readAll());
        l.forEach(p2->System.out.println(p2));*/
        ArrayList l = new ArrayList(pbl.findAll());
        l.forEach(p2->System.out.println(p2));
    }
    public static void update(Producto p){
        //pdao.update(p);
        pbl.update(p);
    }
    public static void delete(Integer key){
        //pdao.delete(p);
        pbl.delete(key);
    }
    
    public static void main(String[]args){
        Producto p = new Producto(10, "weed", (float) 500.0, true,"Canasta Basica");
        //create(p);
        //findByName(p);
        //findByType(p);
        //update(p);
        //delete(10);
        //readAll();
        findByKey(5);
        
        
        
    }
}
