package datos;

import dominio.Usuarios;
import java.sql.*;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;

public class UsuariosDaoJDBC {

    private static final String SQL_SELECT = "SELECT idusuarios, nombre, apellido, legajo FROM usuarios";

    private static final String SQL_SELECT_BY_LEGAJO
            = "SELECT idusuarios, nombre, apellido, legajo FROM usuarios  WHERE  legajo=?";

    private static final String SQL_SELECT_BY_NAME_LEGAGO = "SELECT *  FROM usuarios WHERE nombre=? and legajo=?";
//     

    private static final String SQL_INSERT = "INSERT INTO usuarios(nombre, apellido, legajo)"
            + "VALUES(?, ?, ?)";

    private static final String SQL_UPDATE = "UPDATE usuarios "
            + "SET idusuarios=?, nombre=?, apellido=?, legajo=? WHERE idusuarios=?";

    private static final String SQL_DELETE = "DELETE FROM usuarios WHERE idusuarios=?";

    //Se conecta a la base de datos, ejecuta la sentencia de todos los objetos de tipo usuario y regresa esta lista
    public List<Usuarios> listar() {

        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        Usuarios usuario = null;
        List<Usuarios> usuarios = new ArrayList<>();
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT);
            rs = stmt.executeQuery();
            while (rs.next()) {
                int idUsuario = rs.getInt("idusuarios");
                String nombre = rs.getString("nombre");
                String apellido = rs.getString("apellido");
                String legajo = rs.getString("legajo");

                usuario = new Usuarios(idUsuario, nombre, apellido, legajo);
                usuarios.add(usuario);

            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }

        return usuarios;
    }

    public Usuarios encontrar2(String legajo) throws SQLException {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;

        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_LEGAJO);
            stmt.setString(1, legajo);
            rs = stmt.executeQuery();

            while (rs.next()) {
                Usuarios usuario = new Usuarios();
                usuario.setNombre(rs.getString("nombre"));
                usuario.setLegajo(rs.getString("legajo"));
                usuario.setApellido(rs.getString("apellido"));
                Conexion.close(rs);
                Conexion.close(stmt);
                Conexion.close(conn);
                return usuario;
            }
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
            return null;
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return null;
    }

    public boolean encontrar(Usuarios usuario) {
        Connection conn = null;
        PreparedStatement stmt = null;
        ResultSet rs = null;
        try {
            conn = Conexion.getConnection();
            stmt = conn.prepareStatement(SQL_SELECT_BY_NAME_LEGAGO);
            stmt.setString(1, usuario.getNombre());
            stmt.setString(2, usuario.getLegajo());

            rs = stmt.executeQuery();
            while (rs.next()) {

                if (usuario.getNombre().equals(rs.getString("nombre"))) {
                    String nombre = rs.getString("nombre");
                    String legajo = rs.getString("legajo");
                    usuario.setNombre(nombre);
                    usuario.setLegajo(legajo);
                    return true;
                }

            }
        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        } finally {
            Conexion.close(rs);
            Conexion.close(stmt);
            Conexion.close(conn);
        }
        return false;
    }
}
