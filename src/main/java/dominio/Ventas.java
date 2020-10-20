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
public class Ventas {
    
    private int idVenta;
    private int idProducto;
    private String nombreProducto;
    private int cantidad;
    private double precio;
 
    
    public Ventas() {
    }

    public Ventas(int idVenta) {
        this.idVenta = idVenta;
    }

    public Ventas(int idVenta, int idProducto, String nombreProducto, int cantidad, double precio) {
        this.idVenta = idVenta;
        this.idProducto = idProducto;
        this.nombreProducto = nombreProducto;
        this.cantidad = cantidad;
        this.precio  = precio;
        
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

   

    public int getIdVenta() {
        return idVenta;
    }

    public void setIdVenta(int idVenta) {
        this.idVenta = idVenta;
    }

    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombreProducto() {
        return nombreProducto;
    }

    public void setNombreProducto(String nombreProducto) {
        this.nombreProducto = nombreProducto;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    @Override
    public String toString() {
        return "Ventas{" + "idVenta=" + idVenta + ", idProducto=" + idProducto + ", nombreProducto=" + nombreProducto + ", cantidad=" + cantidad + '}';
    }

  
 
    
    
}
