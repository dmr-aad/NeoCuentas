/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Time;
import java.util.Date;
import objetos.Cliente;
import objetos.CuentaCorriente;
import objetos.CuentaPlazo;
import objetos.Movimiento;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.core.query.IQuery;

/**
 *
 * @author a18danielmr
 */
public class Altas {
    public static void cliente(BufferedReader lee) throws IOException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "NeoCuentas.db");
        
        Cliente c = null;
        IQuery query = null;
        
        System.out.println("Introduce tu dni");
        String dni = lee.readLine();
        
        System.out.println("Introduce tu nombre");
        String nombre = lee.readLine();
        
        System.out.println("Introduce tu dirección");
        String direccion = lee.readLine();
        
        int op = Menu.opcion(lee, "¿Deseas dar de alta una cuenta?");
        if (op == 1) {
            System.out.println("Introduce un número de cuenta");
            int cuenta = Integer.parseInt(lee.readLine());
            
            System.out.println("Introduce una sucursal");
            String sucursal = lee.readLine();
            
            System.out.println("Introduce una cantidad");
            float cantidad = Float.parseFloat(lee.readLine());
            
            int tipo = Menu.tipo(lee);
            if (tipo == 1) { //Cuenta Corriente
                
                CuentaCorriente cc = null;
                Movimiento m = null;
                
                Date fecha = new Date();
                Time hora = new Time(System.currentTimeMillis());
                
                char operacion = 'I';
                float importe = cantidad;
                float saldoResultante = importe;
                
                cc = new CuentaCorriente(cuenta, sucursal, cantidad);
                m = new Movimiento(fecha, hora, operacion, importe, saldoResultante);
                cc.getMovimientos().add(m);
                m.setCuenta(cc);
                
                odb.store(cc);
                odb.store(m);
            } else { //Cuenta Plazo
                
                CuentaPlazo cp = null;
                
                
            }
        }
    }
}
