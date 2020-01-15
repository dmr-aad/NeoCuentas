/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

/**
 *
 * @author a18danielmr
 */
public abstract class Cuenta implements Serializable{
    private int numero;
    private String sucursal;
    private Set<Cliente> clientes;
    private double saldoActual;

    public Cuenta() {
    }
    
    

    public Cuenta(int numero, String sucursal, double saldoActual) {
        this.numero = numero;
        this.sucursal = sucursal;
        this.clientes = new HashSet<>();
        this.saldoActual = saldoActual;
    }

    public int getNumero() {
        return numero;
    }

    public void setNumero(int numero) {
        this.numero = numero;
    }

    public String getSucursal() {
        return sucursal;
    }

    public void setSucursal(String sucursal) {
        this.sucursal = sucursal;
    }

    public Set<Cliente> getClientes() {
        return clientes;
    }

    public void setClientes(Set<Cliente> clientes) {
        this.clientes = clientes;
    }

    public double getSaldoActual() {
        return saldoActual;
    }

    public void setSaldoActual(double saldoActual) {
        this.saldoActual = saldoActual;
    }

    
    
    
}
