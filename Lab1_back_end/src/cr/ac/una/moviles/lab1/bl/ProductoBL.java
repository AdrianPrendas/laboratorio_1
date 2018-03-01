package cr.ac.una.moviles.lab1.bl;

import cr.ac.una.moviles.lab1.domain.Producto;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author _Adrian_Prendas
 */
public class ProductoBL extends BaseBL implements IBaseBL<Producto,Integer>{
    
    public ProductoBL(){
        super();
    }

    @Override
    public void create(Producto o) {
        this.getDao(o.getClass().getName()).create(o);
    }

    @Override
    public boolean update(Producto o) {
        return this.getDao(o.getClass().getName()).update(o);
    }

    @Override
    public boolean delete(Integer key) {
        return this.getDao(Producto.class.getName()).delete(key);
    }

    @Override
    public List<Producto> findByName(String str) {
        Producto p = new Producto(0,str,0,false,"");
        
        return new ArrayList(this.getDao(Producto.class.getName()).read(p));
    }
    
    
    @Override
    public List<Producto> findByType(String str) {
        Producto p = new Producto(0,"",0,false,str);

        return new ArrayList(this.getDao(Producto.class.getName()).read(p));
    }

    @Override
    public List<Producto> findAll() {
        return new ArrayList(this.getDao(Producto.class.getName()).readAll());
    }

}
