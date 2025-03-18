package edu.student.itson.dissof.megazarl.presentacion;

import java.util.Scanner;

public class App {
    /**
     * Solicita al usuario seleccionar una opción y muestra la pantalla
     * correspondiente.
     * 
     * <p>Este es un método temporal.
     * 
     * @param scanner Objeto Scanner para leer la entrada del usuario
     */
    private static void elegirYDesplegarPantalla(Scanner scanner) {
        while (true) {
            System.out.println("Elige una pantalla que desplegar:");
            System.out.println("1. Dirección");
            System.out.println("2. Información producto");
            System.out.println("3. Mensaje");

            if (scanner.hasNextInt()) {
                int seleccion = scanner.nextInt();
                scanner.nextLine(); // Consumir el carácter de nueva línea

                switch (seleccion) {
                    case 1 -> {
                        new Direccion().setVisible(true);
                        return;
                    }
                    case 2 -> {
                        new InformacionProducto().setVisible(true);
                        return;
                    }
                    case 3 -> {
                        new Mensaje().setVisible(true);
                        return;
                    }
                    default -> System.out.println("Opción inválida.");
                }
            } else {
                System.out.println("Entrada inválida. Debes usar un número.");
                scanner.nextLine();
            }
        }
    }
            
    public static void main(String[] args) {
        // Cuando ya no se necesite, remover el método, este comentario, y su
        // prosiguiente línea es suficiente.
        elegirYDesplegarPantalla(new Scanner(System.in));
    }
}
