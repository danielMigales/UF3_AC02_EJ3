/**
 * @Daniel Migales
 */
package uf3_ac02_ej3;

import java.util.Scanner;

public class Main {

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_RESET = "\u001B[0m";

    public static void main(String[] args) {

        Conexion bd = null;
        Scanner teclado = new Scanner(System.in);
        Scanner tecladoStrings = new Scanner(System.in);
        boolean salir = true;

        try {
            bd = new Conexion();
            do {
                System.out.println("\n" + ANSI_BLUE + "----------------MENU PRINCIPAL-------------" + ANSI_RESET + "\n");
                System.out.println("1. Consultar tiempo." );
                System.out.println("2. Descargar prediccion meteorologica (XML).");
                System.out.println("3. Salir del programa.");

                System.out.println("\n" + "Elija una opcion.");
                int opcion = teclado.nextInt();

                switch (opcion) {

                    case 1:
                        System.out.println("\n" + "Escriba el nombre de la ciudad a consultar:");
                        String ciudad = tecladoStrings.nextLine();
                        System.out.println("\n" + "Escriba la fecha a consultar:");
                        String fecha = tecladoStrings.nextLine();
                        bd.consultarTiempo(ciudad, fecha);
                        break;
                    case 2:
                        
                        break;
                    case 3:
                        salir = false;
                        break;
                }
            } while (salir);

        } catch (Exception e) {
        } finally {
            if (bd != null) {
                bd.desconectar();
            }
        }
    }
}
