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
public class CuentaCorriente extends Cuenta{
    private List movimientos;

    public CuentaCorriente() {
    }

    public CuentaCorriente(int numero, String sucursal, float saldoActual) {
        super(numero, sucursal, saldoActual);
        this.movimientos = new ArrayList<>();
    }

    public List getMovimientos() {
        return movimientos;
    }

    public void setMovimientos(List movimientos) {
        this.movimientos = movimientos;
    }

    

    
    
    
    
}
