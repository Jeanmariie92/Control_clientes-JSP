package web;

import datos.ClienteDaoJDBC;
import datos.ProductosDaoJDBC;
import datos.Ventas_ClienteDaoJDBC;

import dominio.Productos;

import dominio.Ventas;
import dominio.Ventas_Cliente;
import dominio.cliente;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/ControladorProducto")
public class ControladorProducto extends HttpServlet {

    Ventas venta = new Ventas();
    List<Ventas> lista = new ArrayList<>();
    int idVenta = 0;
    int idProducto;
    String nombreP;

    int idCliente;
    cliente cliente;
    double precio;
    Productos producto = new Productos();
    int cantidad;
    double total = 0;

    //Recuperar el listado de lientes y compartirlo
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {
                case "eliminar":
                    this.eliminarP(request, response);
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

        response.sendRedirect("principal.jsp"); //Este metodo notifica al navegador, por lo que se actualiza la url, el anterior no.
    }

    //deberia llamar al Servlet de Cliente y que me devuelva algo 
    private void buscarCliente(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos el idCliente

        idCliente = Integer.parseInt(request.getParameter("idCliente"));
        cliente = new ClienteDaoJDBC().encontrar(new cliente(idCliente));

        if (cliente != null) {
            reDirigir(request, response);
        }
        else{
        
             String errorCliente = "Cliente no Encontrado";
             request.setAttribute("errorCliente", errorCliente);
        }
    }

    private void buscarProducto(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //recuperamos el idCliente

        int idProducto = Integer.parseInt(request.getParameter("idProducto"));
        producto = new ProductosDaoJDBC().encontrar(new Productos(idProducto));

        if (producto != null) {

           
            request.setAttribute("producto", producto);
           reDirigir(request, response);
        }

    }

    private void agregaraLista(HttpServletRequest request, HttpServletResponse response, int idproducto, String nombreP, double precio,
            int cantidad)
            throws ServletException, IOException {

        idVenta = idVenta + 1;
        venta = new Ventas();
        venta.setIdProducto(idProducto);
        venta.setNombreProducto(nombreP);
        venta.setPrecio(precio);
        venta.setIdVenta(idVenta);
        venta.setCantidad(cantidad);
        lista.add(venta);

    }

    private void reDirigir(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        request.setAttribute(
                "total", total);
        request.setAttribute(
                "cliente", cliente);
        request.setAttribute(
                "lista", lista);
        request.setAttribute(
                "totalProductos", lista.size());
        request.getRequestDispatcher(
                "principal.jsp").forward(request, response);

    }

    private void agregarP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        boolean agregado = false;
        idProducto = Integer.parseInt(request.getParameter("idProducto_"));
        cantidad = Integer.parseInt(request.getParameter("cantidad"));
        nombreP = request.getParameter("nombreProducto");
        precio = Double.parseDouble(request.getParameter("precio"));
        if (lista.size() > 0) {

            for (int i = 0; i < lista.size() && agregado != true; i++) {

                if (lista.get(i).getIdProducto() == idProducto) {

                    int cantidadactual = lista.get(i).getCantidad();
                    lista.get(i).setCantidad(cantidadactual + cantidad);
                    agregado = true;
                }
            }
            if (agregado != true) {

                agregaraLista(request, response, idProducto, nombreP, precio, cantidad);
            }
        } else {

            agregaraLista(request, response, idProducto, nombreP, precio, cantidad);
        }

        total = 0;
        for (int i = 0; i < lista.size(); i++) {

            double precio = lista.get(i).getPrecio();
            int cantidad = lista.get(i).getCantidad();
            double totalVentap = precio * cantidad;
            total = total + totalVentap;
        }
        reDirigir(request, response);
    }

    private void cancelar(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        lista.clear();
        producto = null;
        total = 0;
        request.getRequestDispatcher("principal.jsp").forward(request, response);

    }

    private void eliminarP(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        // buscar en una lista el indice donde esta el producto recibido
        // o dar valor a las filas en el jsp yt luego recuperar ese valor, restandole 1 deberiamos conseguir el indice
        for (int i = 0; i < lista.size(); i++) {

            int idProductolista = Integer.parseInt(request.getParameter("idProductolista"));
            int idProductolistaexiste;
            idProductolistaexiste = lista.get(i).getIdProducto();
            if (idProductolista == idProductolistaexiste) {

                lista.remove(i);

            } else {

            }

        }

        request.setAttribute("lista", lista);
        request.setAttribute("cliente", cliente);

        request.getRequestDispatcher("principal.jsp").forward(request, response);

    }

    private void generarV(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        Ventas_ClienteDaoJDBC objetoDatos = new Ventas_ClienteDaoJDBC();

        for (int i = 0; i < lista.size(); i++) {

            int id_venta = lista.get(i).getIdVenta();
            int id_producto = lista.get(i).getIdProducto();
            int id_cliente = idCliente;
            double total = (lista.get(i).getPrecio()) * (lista.get(i).getCantidad());
            int totalProductos = lista.get(i).getCantidad();

            Ventas_Cliente ventaRegistro = new Ventas_Cliente(id_venta, id_cliente, id_producto, total, totalProductos);

            objetoDatos.insertar(ventaRegistro);

        }
        lista.clear();
        request.getRequestDispatcher("principal.jsp").forward(request, response);

    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
/// Nombre del boton que realiza la accion request.getParameter
        String accion = request.getParameter("accion");
        if (accion != null) {
            switch (accion) {

                case "buscarC":
                    this.buscarCliente(request, response);
                    break;
                case "buscarP":
                    this.buscarProducto(request, response);
                    break;
                case "agregarP":
                    this.agregarP(request, response);
                    break;
                case "cancelar":
                    this.cancelar(request, response);
                    break;
                case "eliminar":
                    this.eliminarP(request, response);
                    break;
                case "generarV":
                    this.generarV(request, response);
                    break;
                default:
                    this.accionDefault(request, response);
            }
        } else {
            this.accionDefault(request, response);
        }
    }

}
