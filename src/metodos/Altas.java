/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import java.io.BufferedReader;
import java.io.IOException;
import objetos.Cliente;
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
            
        }
    }
}
