/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package metodos;

import java.io.BufferedReader;
import java.io.IOException;

/**
 *
 * @author a18danielmr
 */
public class Menu {
    public static int main(BufferedReader lee) throws IOException {
        int opcion;
        System.out.println("***MENU BANCO***");
        System.out.println("* 1. Altas");
        System.out.println("* 2. Borrar cuenta plazo");
        System.out.println("* 3. Modificar cuenta plazo");
        System.out.println("* 4. Consultas");
        System.out.println("* 0. Salir");
        opcion = Integer.parseInt(lee.readLine());
        return opcion;
    }
    
    public static int altas(BufferedReader lee) throws IOException {
        int opcion;
        System.out.println("***ALTAS***");
        System.out.println("* 1. Cuenta Corriente");
        System.out.println("* 2. Cuenta Plazo");
        System.out.println("* 3. Movimiento");
        System.out.println("* 0. Salir");
        opcion = Integer.parseInt(lee.readLine());
        return opcion;
    }
    
    public static int consultar(BufferedReader lee) throws IOException {
        int opcion;
        System.out.println("***CONSULTAS***");
        System.out.println("* 1. Clientes cuyos nombres empiecen por C");
        System.out.println("* 2. Clientes con saldo menor a 200.000€ en cuenta corriente");
        System.out.println("* 3. Número de clientes en números rojos");
        System.out.println("* 4. Saldo medio de todas las cuentas plazo");
        System.out.println("* 5. Extracto de movimientos");
        System.out.println("* 0. Salir");
        opcion = Integer.parseInt(lee.readLine());
        return opcion;
    }
      
    public static char tipoOperacion(BufferedReader lee) throws IOException {
        int opcion;
        char tipo = 'A';
        do {            
            System.out.println("Seleccione el tipo de operación:");
            System.out.println("* 1. Ingreso");
            System.out.println("* 2. Retirada");
            opcion = Integer.parseInt(lee.readLine());
            if (opcion == 1) {
                tipo =  'I';
            } else if (opcion == 2) {
                tipo =  'R';
            }
        } while (opcion != 1 && opcion != 2);
        return tipo;
    }
    
    public static int opcion(BufferedReader lee, String texto) throws IOException {
        int opcion;
        System.out.println(texto);
        System.out.println("* 1. Si");
        System.out.println("* 2. No");
        opcion = Integer.parseInt(lee.readLine());
        return opcion;
    }
}
