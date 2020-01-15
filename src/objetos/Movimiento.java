/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package objetos;

import java.sql.Time;
import java.util.Date;

/**
 *
 * @author a18danielmr
 */
public class Movimiento {
    private Date fecha;
    private Time hora;
    private Cuenta cuenta;
    private char operacion;
    private float importe;
    private float saldoRestante;

    public Movimiento() {
    }

    public Movimiento(Date fecha, Time hora, char operacion, float importe, float saldoRestante) {
        this.fecha = fecha;
        this.hora = hora;
        this.operacion = operacion;
        this.importe = importe;
        this.saldoRestante = saldoRestante;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora() {
        return hora;
    }

    public void setHora(Time hora) {
        this.hora = hora;
    }

    public Cuenta getCuenta() {
        return cuenta;
    }

    public void setCuenta(Cuenta cuenta) {
        this.cuenta = cuenta;
    }

    public char getOperacion() {
        return operacion;
    }

    public void setOperacion(char operacion) {
        this.operacion = operacion;
    }

    public float getImporte() {
        return importe;
    }

    public void setImporte(float importe) {
        this.importe = importe;
    }

    public float getSaldoRestante() {
        return saldoRestante;
    }

    public void setSaldoRestante(float saldoRestante) {
        this.saldoRestante = saldoRestante;
    }

    
    
    
}
