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
public class Usuarios {
    
    private int idUsuario;
    private String nombre;
    private String apellido;
    private String legajo;

    public Usuarios() {
    }

    
    public Usuarios(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public Usuarios(int idUsuario, String nombre, String apellido, String legajo) {
        this.idUsuario = idUsuario;
        this.nombre = nombre;
        this.apellido = apellido;
        this.legajo = legajo;
    }

    public Usuarios(String nombre, String legajo) {
        this.nombre = nombre;
        this.legajo = legajo;
    }

    
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getLegajo() {
        return legajo;
    }

    public void setLegajo(String legajo) {
        this.legajo = legajo;
    }

    @Override
    public String toString() {
        return "Usuarios{" + "idUsuario=" + idUsuario + ", nombre=" + nombre + ", apellido=" + apellido + ", legajo=" + legajo + '}';
    }
    
    
}