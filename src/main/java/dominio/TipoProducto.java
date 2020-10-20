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
public class TipoProducto {
    
    private int idTipoProducto;
    private String nombre;

    public TipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public TipoProducto(int idTipoProducto, String nombre) {
        this.idTipoProducto = idTipoProducto;
        this.nombre = nombre;
    }

    public int getIdTipoProducto() {
        return idTipoProducto;
    }

    public void setIdTipoProducto(int idTipoProducto) {
        this.idTipoProducto = idTipoProducto;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    @Override
    public String toString() {
        return "TipoProducto{" + "idTipoProducto=" + idTipoProducto + ", nombre=" + nombre + '}';
    }
    
    
}
