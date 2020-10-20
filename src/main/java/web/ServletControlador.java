package web;

import datos.ClienteDaoJDBC;
import datos.ProductosDaoJDBC;
import datos.UsuariosDaoJDBC;
import dominio.Productos;
import dominio.Usuarios;
import dominio.Ventas;
import dominio.cliente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ServletControlador")
public class ServletControlador extends HttpServlet {

    Ventas venta = new Ventas();
    List<Ventas> lista = new ArrayList<>();
    int idVenta = 0;
    int idProducto;
    String nombreP;
    int cantidadP;
     int idCliente;
     cliente cliente;
     double precio;
    Productos producto = new Productos();
    int cantidad ;
    //Recuperar el listado de lientes y compartirlo
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "editar":
                    this.editarCliente(request, response);
                    break;
                case "eliminar":
                    this.eliminarCliente(request, response);
                    break;
                case "controlP":
                    this.controlUsuarios(request, response);
                    break;
                case "productos":
                    request.getRequestDispatcher("agregarProductos.jsp").forward(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void accionDefault(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        List<cliente> clientes = new ClienteDaoJDBC().listar();
        System.out.println("cliente =" + clientes);//Ver todo lo que tenemos en la bbdd
        HttpSession sesion = request.getSession();
        sesion.setAttribute("clientes", clientes);//Compartimos el listado de clientes, la colocamos en el alcance de request
        sesion.setAttribute("totalClientes", clientes.size());
        sesion.setAttribute("saldoTotal", this.calcularSaldoTotal(clientes));
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);//Se redirige a clientes.jsp, para poder enviar el objeto rqeust y response a clientes.jsp
        response.sendRedirect("principal.jsp"); //Este metodo notifica al navegador, por lo que se actualiza la url, el anterior no.
    }

    private void controlUsuarios(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        List<Usuarios> usuarios = new UsuariosDaoJDBC().listar();
        System.out.println("usuario =" + usuarios);//Ver todo lo que tenemos en la bbdd
        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuarios", usuarios);//Compartimos el listado de clientes, la colocamos en el alcance de request
        //request.getRequestDispatcher("clientes.jsp").forward(request, response);//Se redirige a clientes.jsp, para poder enviar el objeto rqeust y response a clientes.jsp
//       response.sendRedirect("controlarUsuarios.jsp"); //Este metodo notifica al navegador, por lo que se actualiza la url, el anterior no.
        String jspUsuarios = "controlarUsuarios.jsp";
        request.getRequestDispatcher(jspUsuarios).forward(request, response);

    }

    private double calcularSaldoTotal(List<cliente> clientes) {

        double saldoTotal = 0;

        for (cliente cliente : clientes) {

            saldoTotal += cliente.getSaldo();
        }
        return saldoTotal;
    }

    private void buscarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos el idCliente

        idCliente = Integer.parseInt(request.getParameter("idCliente"));
        cliente = new ClienteDaoJDBC().encontrar(new cliente(idCliente));

        if (cliente != null) {
            request.setAttribute("cliente", cliente);
            request.getRequestDispatcher("principal.jsp").forward(request, response);
        }
    }

    private void buscarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos el idCliente

        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        producto = new ProductosDaoJDBC().encontrar(new Productos(idProducto));

        if (producto != null) {

            
            request.setAttribute("producto", producto);
            request.setAttribute("lista", lista);
            request.setAttribute("cliente", cliente);
            request.getRequestDispatcher("principal.jsp").forward(request, response);

        }

    }

    private void agregarP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        idProducto = Integer.parseInt(request.getParameter("idProducto_"));
        nombreP = request.getParameter("nombreProducto");
        precio = Double.parseDouble(request.getParameter("precio"));
        cantidad = Integer.parseInt(request.getParameter("cantidad"));
        
        venta = new Ventas();
        venta.setIdProducto(idProducto);
        venta.setNombreProducto(nombreP);
        venta.setPrecio(precio);
        venta.setIdVenta(idVenta);
        venta.setCantidad(cantidad);
        
        lista.add(venta);
     
        
        request.setAttribute("cliente", cliente);
        request.setAttribute("lista", lista);
        request.getRequestDispatcher("principal.jsp").forward(request,response);

    }

    private void editarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos el idCliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        cliente cliente = new ClienteDaoJDBC().encontrar(new cliente(idCliente));
        request.setAttribute("cliente", cliente);
        String jspEditar = "/WEB-INF/paginas/cliente/editarCliente.jsp";
        request.getRequestDispatcher(jspEditar).forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/// Nombre del boton que realiza la accion request.getParameter
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "insertar":
                    this.insertarCliente(request, response);
                    break;
                case "modificar":
                    this.modificarCliente(request, response);
                    break;
                case "buscarC":
                    this.buscarCliente(request, response);
                    break;
                case "buscarP":
                    this.buscarProducto(request, response);
                    break;
                case "agregarP":
                    this.agregarP(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

    private void insertarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Recuperamos los valores del formulario, agregar Cliente
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        if (saldoString != null && !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
        }
        cliente cliente = new cliente(nombre, apellido, email, telefono, saldo);
        int registrosModificados = new ClienteDaoJDBC().insertar(cliente);
        System.out.println("Registros modificados = " + registrosModificados);
        this.accionDefault(request, response);
    }

    private void modificarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        int idCliente = Integer.parseInt(request.getParameter("idCliente"));
        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String email = request.getParameter("email");
        String telefono = request.getParameter("telefono");
        double saldo = 0;
        String saldoString = request.getParameter("saldo");
        if (saldoString != null && !"".equals(saldoString)) {
            saldo = Double.parseDouble(saldoString);
        }

        //Creamos el objeto de cliente (modelo)
        cliente cliente = new cliente(idCliente, nombre, apellido, email, telefono, saldo);

        //Modificamos nuevo cliente en la base de datos (capa de datos)
        int registrosModificados = new ClienteDaoJDBC().actualizar(cliente);
        System.out.println("Registros modificados = " + registrosModificados);

        //Redirigimos a la accion por dafault
        this.accionDefault(request, response);
    }

    private void eliminarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        //Recuperamos los valores del formulario, modificar Cliente
        int idCliente = Integer.parseInt(request.getParameter("idCliente"));

        //Creamos el objeto de cliente (modelo)
        cliente cliente = new cliente(idCliente);

        //Modificamos nuevo cliente en la base de datos (capa de datos)
        int registrosModificados = new ClienteDaoJDBC().eliminar(cliente);
        System.out.println("Registros modificados = " + registrosModificados);

        //Redirigimos a la accion por dafault
        this.accionDefault(request, response);
    }

}
