package datos;

import dominio.Productos;
import dominio.TipoProducto;
import java.sql.*;
import java.util.*;

public class ProductosDaoJDBC {
    
    private static final String SQL_SELECT = "SELECT * FROM Productos";
    
    private static final String SQL_SELECT_BY_ID = "SELECT *  FROM Productos WHERE id_producto=?";
//     

    private static final String SQL_INSERT = "INSERT INTO Productos(nombre, cantidad, tipo, precio)"
            + "VALUES(?, ?, ?,?,?)";
    
    private static final String SQL_UPDATE = "UPDATE Productos "
            + "SET idProducto=?, nombre=?, cantidad=?, tipo=?, precio=? WHERE idProducto=?";
    
    private static final String SQL_DELETE = "DELETE FROM Productos WHERE idProducto=?";

    //Se conecta a la base de datos, ejecuta la sentencia de todos los objetos de tipo producto y regresa esta lista
    public List<Productos> listar() {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Productos producto = null;
        List<Productos> Productos = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idproducto = rs.getInt("idProducto");
                String nombre = rs.getString("nombre");
                int cantidad = rs.getInt("cantidad");
                int tipoProducto = rs.getInt("tipo");
                double precio = rs.getDouble("precio");
                
                producto = new Productos(idproducto, nombre, cantidad, tipoProducto, precio);
                Productos.add(producto);
                
            }
            
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return Productos;
    }
    
    public Productos encontrar(Productos producto) {
        
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_ID);
            stmt.setInt(1, producto.getIdProducto());
            
            rs = stmt.executeQuery();
            while (rs.next()) {
                
                int idProducto = rs.getInt("id_producto");
                String nombre = rs.getString("nombre");
                double precio = rs.getDouble("precio");
                
                producto.setIdProducto(idProducto);
                producto.setNombre(nombre);
                producto.setPrecio(precio);
                
            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        
        return producto;
    }
    
     public int eliminar(Productos producto) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_DELETE);
            stmt.setInt(1, producto.getIdProducto());
            rows = stmt.executeUpdate();
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {

            Conexion.close(stmt);
            Conexion.close(conn);

        }

        return rows;
    }
    
}
//}

//    public int insertar producto) {
//
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        int rows = 0;
//
//        try {
//            conn = Conexion.getConnection();
//            stmt = conn.prepareStatement(SQL_INSERT);
//            stmt.setString(1, producto.getNombre());
//            stmt.setString(2, producto.getApellido());
//            stmt.setString(3, producto.getEmail());
//            stmt.setString(4, producto.getTelefono());
//            stmt.setDouble(5, producto.getSaldo());
//
//            rows = stmt.executeUpdate();
//
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//
//            Conexion.close(stmt);
//            Conexion.close(conn);
//
//        }
//
//        return rows;
//    }
//
//    public int actualizar producto) {
//
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        int rows = 0;
//
//        try {
//            conn = Conexion.getConnection();
//            stmt = conn.prepareStatement(SQL_UPDATE);
//
//            stmt.setString(1, producto.getNombre());
//            stmt.setString(2, producto.getApellido());
//            stmt.setString(3, producto.getEmail());
//            stmt.setString(4, producto.getTelefono());
//            stmt.setDouble(5, producto.getSaldo());
//            stmt.setInt(6, producto.getI producto());
//
//            rows = stmt.executeUpdate();
//
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//
//            Conexion.close(stmt);
//            Conexion.close(conn);
//
//        }
//
//        return rows;
//    }
//
//    public int eliminar producto) {
//
//        Connection conn = null;
//        PreparedStatement stmt = null;
//        int rows = 0;
//
//        try {
//            conn = Conexion.getConnection();
//            stmt = conn.prepareStatement(SQL_DELETE);
//            stmt.setInt(1, producto.getI producto());
//            rows = stmt.executeUpdate();
//        } catch (SQLException ex) {
//            ex.printStackTrace(System.out);
//        } finally {
//
//            Conexion.close(stmt);
//            Conexion.close(conn);
//
//        }
//
//        return rows;
//    }
//
//}
