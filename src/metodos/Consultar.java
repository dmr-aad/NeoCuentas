/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import objetos.Cliente;
import objetos.Cuenta;
import objetos.CuentaCorriente;
import objetos.CuentaPlazo;
import objetos.Movimiento;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.ObjectValues;
import org.neodatis.odb.Objects;
import org.neodatis.odb.Values;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.ICriterion;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;
import org.neodatis.odb.impl.core.query.values.ValuesCriteriaQuery;

/**
 *
 * @author mirotic13
 */
public class Consultar {
    public static void main(BufferedReader lee) throws IOException {
        int op;
        do {
            op = Menu.consultar(lee);
            switch(op) {
                case 1:
                case 2:
                case 3:
                case 4:
                case 5:
                case 0:
                    System.out.println("SALIENDO...");
                    break;
            }
        }while(op != 0);
    }
    public static void clientes_C(BufferedReader lee) throws IOException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "NeoCuentas.db");
        
        ICriterion crit = Where.like("nombre", "C%");
        CriteriaQuery query = new CriteriaQuery(Cliente.class, crit);
        
        Objects<Cliente> clientes = odb.getObjects(query);
        
        if (!clientes.isEmpty()) {
            while (clientes.hasNext()) {
                Cliente c = clientes.next();
                System.out.println("DNI: " + c.getDni());
                System.out.println("Nombre: " + c.getNombre());
                System.out.println("Dirección: " + c.getDireccion());
                System.out.println("*****************************************");
            }
        } else {
            System.out.println("No hay ningún cliente cuyo nombre empiece por C");
        }
        
        odb.close();
    }
    
    public static void cuentaCorriente_Doscientos() {
        ODB odb = ODBFactory.openClient("localhost", 8000, "NeoCuentas.db");
        
        ICriterion crit = Where.lt("saldoActual", 200000);
        CriteriaQuery query = new CriteriaQuery(CuentaCorriente.class, crit);
        
        Objects<CuentaCorriente> cuentas = odb.getObjects(query);
        
        if (!cuentas.isEmpty()) {
            CuentaCorriente cc = cuentas.getFirst();
            
            for (Cliente cliente : cc.getClientes()) {
                System.out.println("DNI: " + cliente.getDni());
                System.out.println("Nombre: " + cliente.getNombre());
                System.out.println("Dirección: " + cliente.getDireccion());
                System.out.println("*****************************************");
            }
        }
        odb.close();
    }
    
    public static void numRojos(){
        ODB odb = ODBFactory.openClient("localhost", 8000, "NeoCuentas.db");
        
        Values val = odb.getValues(new ValuesCriteriaQuery(Cuenta.class).count("saldoActual"));
        ObjectValues ov = val.nextValues();
        BigInteger value = (BigInteger)ov.getByAlias("saldoActual");
        
        System.out.println("Número de cuentas en números rojos: " + value.intValue());
        System.out.println("*******************************************************");
        
        odb.close();
    }
    
    public static void saldoMedio() {
        ODB odb = ODBFactory.openClient("localhost", 8000, "NeoCuentas.db");
        
        Values val = odb.getValues(new ValuesCriteriaQuery(CuentaPlazo.class).avg("saldoActual"));
        ObjectValues ov = val.nextValues();
        BigDecimal value = (BigDecimal)ov.getByAlias("saldoActual");
        
        System.out.println("Media de saldo en todas las cuentas plazo: " + value.longValue());
        System.out.println("***************************************************************");
        
        odb.close();
    }
    
    public static void extracto(BufferedReader lee) throws IOException, ParseException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "NeoCuentas.db");
        
        System.out.println("Introduzca el numero de cuenta");
        int nCuenta = Integer.parseInt(lee.readLine());
        
        IQuery query = new CriteriaQuery(CuentaCorriente.class,
                    Where.equal("numero", nCuenta));
            Objects<CuentaCorriente> cuentas = odb.getObjects(query);
            
        if (!cuentas.isEmpty) { //Existe la cuenta
            CuentaCorriente cc = (CuentaCorriente) odb.getObjects(query).getFirst();
            
            System.out.println("Introduzca la fecha inicial (dd/MM/yyyy)");
            String fechaIni = lee.readLine();
            System.out.println("Introduzca la fecha final (dd/MM/yyyy)");
            String fechaFin = lee.readLine();
            
            SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
            Date fIni = sdf.parse(fechaIni);
            Date fFin = sdf.parse(fechaFin);
            
            for (Movimiento movimiento : cc.getMovimientos()) {
                if (movimiento.getFecha().compareTo(fIni) >= 0 && movimiento.getFecha().compareTo(fFin) <= 0) {
                    System.out.println("Operacion: " + movimiento.getOperacion());
                    System.out.println("Importe: " + movimiento.getImporte());
                    System.out.println("Saldo Restante: " + movimiento.getSaldoRestante());
                    System.out.println("************************************************");
                }
            }
            
        } else {
            System.out.println("No existe ninguna cuenta con ese número");
        }
        odb.close();
    }
}
