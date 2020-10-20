package web;

import datos.UsuariosDaoJDBC;
import dominio.Usuarios;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

@WebServlet("/ControladorLogin")
public class ControladorLogin extends HttpServlet {

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.accesoLogin(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        this.prueba(request, response);

    }

    private void accesoLogin(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.sendRedirect("login.jsp");

    }

//    private void principalLogin(HttpServletRequest request, HttpServletResponse response)
//            throws ServletException, IOException {
//
//        String nombreUsuario = request.getParameter("usuario");
//        String password = request.getParameter("password");
//        boolean encontrado = false;
//        UsuariosDaoJDBC consulta = new UsuariosDaoJDBC();
//        encontrado = consulta.encontrar(new Usuarios(nombreUsuario, password));
//
//        if (encontrado == true) {
//
//            HttpSession sesion = request.getSession();
//
//            sesion.setAttribute("UserName", nombreUsuario);
//            ServletControlador re = new ServletControlador();
//            re.doGet(request, response);
//        } else {
//            response.sendRedirect("login.jsp?mensaje=Error, Usuario no encontrado");
//        }
//    }
    private void prueba(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        PrintWriter out = null;
        JSONArray list  = new JSONArray();
        JSONObject obj = new JSONObject();
        String msj;

        try {
            out = response.getWriter();
            String nombre = request.getParameter("usuario");
            String legajo = request.getParameter("password");

            UsuariosDaoJDBC buscarUser = new UsuariosDaoJDBC();
            Usuarios usuario1 = buscarUser.encontrar2(legajo);
      
            if (usuario1 != null) {

                msj = "1";
                obj.put("msj", msj);
                list.add(obj);
                out.println(list.toJSONString());
                out.flush();
                

            } else {
                msj = "3";
                obj.put("msj", msj);
                list.add(obj);
                out.println(list.toJSONString());
                out.flush();
                
                
            }

        } catch (SQLException ex) {
            ex.printStackTrace(System.out);
        

    }

}
}