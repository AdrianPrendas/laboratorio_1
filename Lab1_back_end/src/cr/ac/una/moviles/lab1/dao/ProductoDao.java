
package cr.ac.una.moviles.lab1.dao;

import cr.ac.una.moviles.lab1.domain.Producto;
import java.sql.CallableStatement;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import oracle.jdbc.OracleTypes;

/**
 *
 * @author _Adrián_Prendas_
 */
public class ProductoDao extends DAO implements CRUD<Producto> {
    private static ProductoDao uniqueInstance;
    private static final String CREATE_PRODUCT = "{call insertarProducto(?,?,?,?,?)}";
    private static final String READ_PRODUCTO_BY_TYPE = "{?=call buscarProductoTipo(?)}";
    private static final String READ_PRODUCTO_BY_NAME = "{?=call buscarProductoNombre(?)}";
    private static final String UPDATE_PRODUCT = "{call modificarProducto(?,?,?,?,?)}";
    private static final String DELETE_PRODUCT = "{call eliminarProducto(?)}";
    
    private static final String LIST_PRODUCTS = "{?=call listaProductos()}";
    
    private ProductoDao(){
        super();
    }
    
    public static ProductoDao getInstance(){
        if(uniqueInstance == null)
            uniqueInstance = new ProductoDao();
        return uniqueInstance;
    }
    
    @Override
    public boolean create(Producto p){
        CallableStatement pstmt = null;
        boolean resp = true;
        try {
            conectar();
            
        } catch (ClassNotFoundException e) {
            System.out.println("No se ha localizado el driver");
            e.printStackTrace();
            resp=false;
        } catch (SQLException e) {
            System.out.println("La base de datos no se encuentra disponible");
            e.printStackTrace();
            resp=false;
        }
        try{
            pstmt = conexion.prepareCall(CREATE_PRODUCT);                                                
            pstmt.setInt(1,p.getCodigo());
            pstmt.setString(2,p.getNombre());
            pstmt.setFloat(3,p.getPrecio());
            pstmt.setString(4,String.valueOf(p.getImportado()));
            pstmt.setInt(5,p.getTipo());
            pstmt.execute();//retorna true o false
        } catch (SQLException e) {
            System.out.println("Llave duplicada");
            e.printStackTrace();
            resp=false;
        }
        finally{
            try {
                if (pstmt!=null)
                    pstmt.close();                                    
                desconectar();
            } catch (SQLException e) {
                e.printStackTrace();
                resp=false;
                System.out.println("Estatutos invalidos o nulos");
            }
        }
        if(resp)
            System.out.println("se creo con exito: "+p.toString());
        return resp;
    }

    @Override
    public List<Producto> read(Producto p1) {
        ResultSet rs = null;
        ArrayList coleccion = new ArrayList();
        Producto p2 = null;
        CallableStatement pstmt=null;  
        try {
            conectar();
            
            if(p1.getNombre().isEmpty()){
                pstmt = conexion.prepareCall(READ_PRODUCTO_BY_TYPE);            
                pstmt.setInt(2,p1.getTipo());            
            }
            else{
                pstmt = conexion.prepareCall(READ_PRODUCTO_BY_NAME);            
                pstmt.setString(2,p1.getNombre());            
            }
            pstmt.registerOutParameter(1, OracleTypes.CURSOR);            
            pstmt.execute();
            rs = (ResultSet)pstmt.getObject(1); 
            while (rs.next()) {
                p2 = new Producto();
                p2.setCodigo(rs.getInt("codigo"));
                p2.setNombre(rs.getString("nombre"));
                p2.setPrecio(rs.getFloat("precio"));
                p2.setImportado(rs.getString("importado").charAt(0));
                p2.setTipo(rs.getInt("tipo"));
                coleccion.add(p2);
            }
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (SQLException e) {
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
               e.printStackTrace();
            }
        }
        return coleccion;
    }

    @Override
    public boolean update(Producto p) {
        PreparedStatement pstmt = null;
        boolean resp=true;
        try {
            conectar();
            
            pstmt = conexion.prepareStatement(UPDATE_PRODUCT);
            pstmt.setInt(1,p.getCodigo());
            pstmt.setString(2,p.getNombre());
            pstmt.setFloat(3,p.getPrecio());
            pstmt.setString(4,String.valueOf(p.getImportado()));
            pstmt.setInt(5,p.getTipo());            
            int resultado = pstmt.executeUpdate();
            if (resultado == 0)//si es diferente de 0 es porq si afecto un registro o mas
                resp=false;            
            else
               System.out.println("Modificacion Exitosa");            
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            resp=false;
        } catch (SQLException e) {
            e.printStackTrace();
            resp=false;
        }
        finally {
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
            } catch (SQLException e) {
               resp=false;
            }
        }
        return resp;
    }

    @Override
    public boolean delete(Producto t) {
        boolean resp=true;
        ResultSet rs = null;     
        ArrayList coleccion = new ArrayList();
        Producto elcontacto = null;
        CallableStatement pstmt=null;  
        try {
            conectar();
            
            pstmt = conexion.prepareCall(DELETE_PRODUCT);                        
            pstmt.setInt(1,t.getCodigo());            
            pstmt.execute();
        } catch (ClassNotFoundException e) {
           e.printStackTrace();
           resp=false;
        } catch (SQLException e) {
           e.printStackTrace();
           resp=false;
        } finally {
            try {
                if (rs != null) {
                    rs.close();
                }
                if (pstmt != null) {
                    pstmt.close();
                }
                desconectar();
                System.out.println("se elimino con exito: "+t.toString());
                return resp;
            } catch (SQLException e) {
                e.printStackTrace();
                return false;
            }
        }
    }
    
}
