package cr.ac.una.moviles.lab1.dao;

import java.util.List;

/**
 *
 * @author _Adrián_Prendas_
 */
public interface CRUD<T> {
    boolean create(T t);
    List<T> read(T t);
    boolean update(T t);
    boolean delete(T t); 
}
