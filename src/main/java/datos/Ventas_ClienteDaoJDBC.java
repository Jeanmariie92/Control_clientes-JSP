package datos;

import dominio.Ventas_Cliente;
import dominio.cliente;
import java.sql.*;
import java.util.*;

public class Ventas_ClienteDaoJDBC {


    private static final String SQL_INSERT = "INSERT INTO ventas_cliente(id_venta, fk_id_cliente, fk_id_producto, total, cantidad_producto)"
            + "VALUES(?, ?, ?, ?, ?)";




    public int insertar(Ventas_Cliente ventac) {

        Connection conn = null;
        PreparedStatement stmt = null;
        int rows = 0;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_INSERT);
            stmt.setInt(1, ventac.getId_venta());
            stmt.setInt(2, ventac.getId_cliente());
            stmt.setInt(3, ventac.getId_producto());
            stmt.setDouble(4, ventac.getTotal());
            stmt.setInt(5, ventac.getCantidadProductos());
            

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
