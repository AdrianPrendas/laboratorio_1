package cr.ac.una.moviles.lab1.bl;

import cr.ac.una.moviles.lab1.domain.Tipo;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author _Adrián_Prendas_
 */
public class TipoBL extends BaseBL implements IBaseBL {
    
    public TipoBL(){
        super();
    }

    @Override
    public void create(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean update(Object o) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(Object key) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findByName(String str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findByType(String str) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List findAll() {
        return new ArrayList(this.getDao(Tipo.class.getName()).readAll());
    }
    
}
