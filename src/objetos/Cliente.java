/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 *
 * @author a18danielmr
 */
public class Cliente {
    private String dni;
    private String nombre;
    private String direccion;
    private List cuentas;

    public Cliente() {
    }

    public Cliente(String dni, String nombre, String direccion) {
        this.dni = dni;
        this.nombre = nombre;
        this.direccion = direccion;
        this.cuentas = new ArrayList<>();
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public List getCuentas() {
        return cuentas;
    }

    public void setCuentas(List cuentas) {
        this.cuentas = cuentas;
    }

    

    
    
    
}
