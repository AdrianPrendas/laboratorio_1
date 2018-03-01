package cr.ac.una.moviles.lab1.dao;

import cr.ac.una.moviles.lab1.domain.Tipo;
import java.sql.CallableStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author _Adrián_Prendas_
 */
public class TipoDao extends ABaseDAO implements IBaseCRUD<Tipo,String>{
    public static TipoDao uniqueInstance;
    public final String LIST_TYPES = "{?=call listaTipos()}";
    
    private TipoDao(){
        super();
    }
    
    public static TipoDao getInstance(){
        if(uniqueInstance==null)
            uniqueInstance = new TipoDao();
        return uniqueInstance;
    }

    @Override
    public boolean create(Tipo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public List<Tipo> read(Tipo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
 
    @Override
    public boolean update(Tipo t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public boolean delete(String t) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
    
    @Override
    public List<Tipo> readAll() {
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Tipo t = null;
        CallableStatement pstmt=null;  
        try {
            conectar();
        }catch (ClassNotFoundException e) {
            System.out.println("No se ha localizado el driver");
        } catch (SQLException e) {
            System.out.println("La base de datos no se encuentra disponible");
        }
        try{
            pstmt = conexion.prepareCall(LIST_TYPES);                
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);            
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
            while (rs.next()) {
                t = new Tipo();
                t.setNombre(rs.getString("nombre"));
                t.setPorcentaje(rs.getFloat("porcentaje"));
                coleccion.add(t);
            }
        }
        catch (SQLException e) {
            System.out.println("Sentencia no valida");
            e.printStackTrace();
        }
        finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
                System.out.println("Estatutos invalidos o nulos");
               e.printStackTrace();
            }
        }
        return coleccion;
    }
    
    
    
    
}
