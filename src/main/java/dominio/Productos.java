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
public class Productos {
    
    private int idProducto;
    private String nombre;
    private int cantidad;
    private int tipoProducto;
    private double precio;

    public Productos(int idProducto) {
        this.idProducto = idProducto;
    }

    public Productos() {
    }

    public Productos(int idProducto, String nombre, int cantidad, int tipoProducto, double precio) {
        this.idProducto = idProducto;
        this.nombre = nombre;
        this.cantidad = cantidad;
        this.tipoProducto = tipoProducto;
        this.precio = precio;
    }

   

    
    public int getIdProducto() {
        return idProducto;
    }

    public void setIdProducto(int idProducto) {
        this.idProducto = idProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public int getTipo() {
        return tipoProducto;
    }

    public void setTipo(int tipoProducto) {
        this.tipoProducto = tipoProducto;
    }

    

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    @Override
    public String toString() {
        return "Productos{" + "idProducto=" + idProducto + ", nombre=" + nombre + ", cantidad=" + cantidad + ", tipoProducto=" + tipoProducto + ", precio=" + precio + '}';
    }

   
    
}
