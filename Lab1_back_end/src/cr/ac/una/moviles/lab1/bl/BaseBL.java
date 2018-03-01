package cr.ac.una.moviles.lab1.bl;

import cr.ac.una.moviles.lab1.dao.*;
import java.util.LinkedHashMap;

/**
 *
 * @author _Adrian_Prendas_
 */

public class BaseBL {
    private final LinkedHashMap<String, IBaseCRUD> daos;

    public BaseBL() {
        daos = new LinkedHashMap();
        daos.put("cr.ac.una.moviles.lab1.domain.Producto", ProductoDao.getInstance());
        daos.put("cr.ac.una.moviles.lab1.domain.Tipo", TipoDao.getInstance());
    }

    public IBaseCRUD getDao(String className) {
        return daos.get(className);
    }
}
