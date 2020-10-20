/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dominio;

/**
 *
 * @author Jeanmarie
 */
public class Ventas_Cliente {

    int id_venta;
    int id_cliente;
    int id_producto;
    double total;
    int cantidadProductos;

    public Ventas_Cliente(int id_venta, int id_cliente, int id_producto, double total, int cantidadProductos) {
        this.id_venta = id_venta;
        this.id_cliente = id_cliente;
        this.id_producto = id_producto;
        this.total = total;
        this.cantidadProductos = cantidadProductos;
        
    }

    public Ventas_Cliente() {
        
    }

    public int getId_producto() {
        return id_producto;
    }

    public void setId_producto(int id_producto) {
        this.id_producto = id_producto;
    }

    public int getCantidadProductos() {
        return cantidadProductos;
    }

    public void setCantidadProductos(int cantidadProductos) {
        this.cantidadProductos = cantidadProductos;
    }

   

    public int getId_venta() {
        return id_venta;
    }

    public void setId_venta(int id_venta) {
        this.id_venta = id_venta;
    }

    public int getId_cliente() {
        return id_cliente;
    }

    public void setId_cliente(int id_cliente) {
        this.id_cliente = id_cliente;
    }

    public double getTotal() {
        return total;
    }

    public void setTotal(double total) {
        this.total = total;
    }

   

    
    
    
}
