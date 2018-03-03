
package cr.ac.una.moviles.lab1.bl;

import java.util.List;

/**
 *
 * @author _Adrian_Prendas_
 */

public interface IBaseBL <T,K> {
    public abstract boolean create(T o);
    public abstract boolean update(T o);
    public abstract boolean delete(K key);
    public abstract List<T> findByName(String str);
    public abstract List<T> findByType(String str);
    public abstract List<T> findAll();
}
