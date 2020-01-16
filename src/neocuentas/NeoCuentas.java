/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package neocuentas;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import metodos.Altas;
import metodos.Menu;

/**
 *
 * @author a18danielmr
 */
public class NeoCuentas {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws IOException {
        BufferedReader lee = new BufferedReader (new InputStreamReader (System.in));
        int op;
        do {
            op = Menu.main(lee);
            switch (op) {
                case 1:
                    Altas.main(lee);
                    break;
                case 2:
                    System.out.println("En construcción");
                    break;
                case 3:
                    System.out.println("En construcción");
                    break;
                case 4:
                    System.out.println("En construcción");
                    break;
                case 0:
                    System.out.println("SALIENDO...");
                    break;
            }
        }while(op != 0);
    }
    
}
