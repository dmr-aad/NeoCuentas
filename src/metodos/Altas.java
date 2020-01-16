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
import objetos.Cuenta;
import objetos.CuentaCorriente;
import objetos.CuentaPlazo;
import objetos.Movimiento;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author a18danielmr
 */
public class Altas {
    
    public static void main(BufferedReader lee) throws IOException {
        int opcion;
        do {
            opcion = Integer.parseInt(lee.readLine());
            switch (opcion) {
                case 1:
                    cuentaCorriente(lee);
                    break;
                case 2:
                    cuentaPlazo(lee);
                    break;
                case 3:
                    movimiento(lee);
                    break;
                case 0:
                    System.out.println("SALIENDO...");
                    break;
            }
        }while(opcion != 0);
    }

    public static void cuentaCorriente(BufferedReader lee) throws IOException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "NeoCuentas.db");

        Cliente c = null;
        CuentaCorriente cc = null;
        Movimiento m = null;
        IQuery query = null;

        c = cargarCliente(lee);

        if (c != null) {
            System.out.println("Introduce un número de cuenta");
            int cuenta = Integer.parseInt(lee.readLine());
            //TODO comprobar que no existe

            System.out.println("Introduce una sucursal");
            String sucursal = lee.readLine();

            System.out.println("Introduce una cantidad");
            float cantidad = Float.parseFloat(lee.readLine());

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
        }
        odb.close();
    }

    public static void cuentaPlazo(BufferedReader lee) throws IOException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "NeoCuentas.db");

        Cliente c = null;
        CuentaPlazo cp = null;
        Movimiento m = null;
        IQuery query = null;

        c = cargarCliente(lee);

        if (c != null) {
            System.out.println("Introduce un número de cuenta");
            int cuenta = Integer.parseInt(lee.readLine());
            //TODO comprobar que no existe

            System.out.println("Introduce una sucursal");
            String sucursal = lee.readLine();

            System.out.println("Introduce una cantidad");
            float cantidad = Float.parseFloat(lee.readLine());

            System.out.println("Introduzca los intereses");
            int intereses = Integer.parseInt(lee.readLine());

            System.out.println("Introduce la fecha de vencimiento");
            String fecha = lee.readLine();

            float depositoPlazo = cantidad;

            cp = new CuentaPlazo(cuenta, sucursal, cantidad, intereses, fecha, depositoPlazo);

            odb.store(cp);
        }
        odb.close();
    }

    public static void movimiento(BufferedReader lee) throws IOException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "NeoCuentas.db");
        Movimiento m = null;

        CuentaCorriente cc = cargarCuentaCorriente(lee);
        if (cc != null) {
            Date fecha = new Date();
            Time hora = new Time(System.currentTimeMillis());

            float saldoRestante = (float) cc.getSaldoActual(), importe;

            char operacion = Menu.tipoOperacion(lee);

            System.out.println("Introduce el importe");
            importe = Float.parseFloat(lee.readLine());

            if (operacion == 'I') {
                saldoRestante = (float) cc.getSaldoActual() + importe;
            } else if (operacion == 'R') {
                saldoRestante = (float) cc.getSaldoActual() - importe;
            } else {
                System.out.println("Operación no válida");
            }

            m = new Movimiento(fecha, hora, operacion, importe, saldoRestante);
            cc.getMovimientos().add(m);

            odb.store(cc);
            odb.store(m);
        }
        odb.close();
    }

    public static CuentaCorriente cargarCuentaCorriente(BufferedReader lee) throws IOException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "NeoCuentas.db");
        CuentaCorriente cc = null;
        System.out.println("Introduce tu numero de cuenta (0 para salir)");
        int ncuenta = Integer.parseInt(lee.readLine());

        if (ncuenta != 0) {

            //Buscamos si existe la cuenta
            IQuery query = new CriteriaQuery(Cuenta.class,
                    Where.equal("numero", ncuenta)).setPolymorphic(true);
            Objects<Cuenta> objects = odb.getObjects(query);

            if (!objects.isEmpty()) { //La cuenta existe
                Cuenta c = (Cuenta) odb.getObjects(query).getFirst();
                if (c instanceof CuentaCorriente) { //La cuenta es corriente
                    cc = (CuentaCorriente) c;
                }
            }
        }
        return cc;
    }

    public static Cliente cargarCliente(BufferedReader lee) throws IOException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "NeoCuentas.db");
        Cliente c = null;
        System.out.println("Introduce tu dni (0 para salir)");
        String dni = lee.readLine();

        if (!dni.equals("0")) { //Atajo de escape

            //Buscamos si existe el dni
            IQuery query = new CriteriaQuery(Cliente.class,
                    Where.equal("dni", dni));
            Objects<Cliente> objects = odb.getObjects(query);

            if (objects.isEmpty()) { //No existe dni
                int opcion = Menu.opcion(lee, "No se ha encontrado ese dni, ¿Desea darse de alta?");
                if (opcion == 1) { //Si

                    System.out.println("Introduce tu nombre");
                    String nombre = lee.readLine();

                    System.out.println("Introduce tu dirección");
                    String direccion = lee.readLine();

                    c = new Cliente(dni, nombre, direccion);  //Creo el cliente
                } //SALIR
            } else {  //Existe el cliente
                c = (Cliente) odb.getObjects(query).getFirst();
            }
        }
        return c;
    }
}
