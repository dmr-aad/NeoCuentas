/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import java.io.BufferedReader;
import java.io.IOException;
import objetos.Cliente;
import objetos.CuentaCorriente;
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
    
    public static void cuentaCorriente_Doscientos(BufferedReader lee) throws IOException {
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
    }
}
