/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.util.Date;

/**
 *
 * @author a18danielmr
 */
public class CuentaPlazo extends Cuenta{
    private int intereses;
    private String fechaVencimiento;
    private float depositoPlazo;

    public CuentaPlazo() {
    }

    public CuentaPlazo(int numero, String sucursal, double saldoActual, int intereses, String fechaVencimiento, float depositoPlazo) {
        super(numero, sucursal, saldoActual);
        this.intereses = intereses;
        this.fechaVencimiento = fechaVencimiento;
        this.depositoPlazo = depositoPlazo;
    }

    public int getIntereses() {
        return intereses;
    }

    public void setIntereses(int intereses) {
        this.intereses = intereses;
    }

    public String getFechaVencimiento() {
        return fechaVencimiento;
    }

    public void setFechaVencimiento(String fechaVencimiento) {
        this.fechaVencimiento = fechaVencimiento;
    }

    public float getDepositoPlazo() {
        return depositoPlazo;
    }

    public void setDepositoPlazo(float depositoPlazo) {
        this.depositoPlazo = depositoPlazo;
    }

    

    
    
    
}
