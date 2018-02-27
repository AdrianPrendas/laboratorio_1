
package cr.ac.una.moviles.lab1.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 *
 * @author _Adrián_Prendas_
 */
abstract class DAO {
    
    protected Connection conexion= null; 
    
    protected DAO() {
        
    }
    
    protected void conectar() throws SQLException,ClassNotFoundException 
    {
        Class.forName("oracle.jdbc.driver.OracleDriver");
        conexion = DriverManager.getConnection("jdbc:oracle:thin:@localhost:1521:XE","tor","root");

    }
    
    protected void desconectar() throws SQLException{
        if(!conexion.isClosed())
            conexion.close();       
    }
}
