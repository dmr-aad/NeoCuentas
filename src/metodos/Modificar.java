/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;
import objetos.Cliente;
import objetos.Cuenta;
import objetos.CuentaPlazo;
import org.neodatis.odb.ODB;
import org.neodatis.odb.ODBFactory;
import org.neodatis.odb.Objects;
import org.neodatis.odb.core.query.IQuery;
import org.neodatis.odb.core.query.criteria.Where;
import org.neodatis.odb.impl.core.query.criteria.CriteriaQuery;

/**
 *
 * @author mirotic13
 */
public class Modificar {

    public static void intereses(BufferedReader lee) throws IOException {
        ODB odb = ODBFactory.openClient("localhost", 8000, "NeoCuentas.db");

        System.out.println("Introduce tu dni");
        String dni = lee.readLine();

        IQuery query = new CriteriaQuery(Cliente.class, Where.equal("dni", dni));
        Objects<Cliente> objectsCli = odb.getObjects(query);

        int contador = 0;

        if (!objectsCli.isEmpty()) {
            Cliente c = (Cliente) odb.getObjects(query).getFirst();
            List<Cuenta> cuentas = c.getCuentas();
            for (Cuenta cuenta : cuentas) {
                if (cuenta instanceof CuentaPlazo) {
                    System.out.println("CuentaPlazo: " + cuenta.getNumero());
                    contador++;
                }
            }
            boolean coincide = false;
            int nCuenta = -1;
            if (contador != 0) {
                do {                    
                    System.out.println("Teclea el número de cuenta que quieras modificar (0 para salir)");
                    nCuenta = Integer.parseInt(lee.readLine());
                    
                    query = new CriteriaQuery(CuentaPlazo.class, Where.equal("numero", nCuenta));
                    Objects<CuentaPlazo> objectsCuenta = odb.getObjects(query);
                    
                    if(!objectsCuenta.isEmpty()) {
                        coincide = true;
                    }
                } while (!coincide || nCuenta == 0);
                if (coincide) {
                    CuentaPlazo cp = (CuentaPlazo)odb.getObjects(query);
                    
                    System.out.println("Los intereses de la cuenta son de " + cp.getIntereses());
                    System.out.println("Introduzca el nuevo valor");
                    int intereses = Integer.parseInt(lee.readLine());
                    cp.setIntereses(intereses);
                    odb.store(cp);
                }
            } else {
                System.out.println("No tiene Cuentas a Plazo disponibles");
            }

        } else {
            System.out.println("No se ha encontrado ningún cliente con ese dni");
        }
        
        odb.close();
    }
}
