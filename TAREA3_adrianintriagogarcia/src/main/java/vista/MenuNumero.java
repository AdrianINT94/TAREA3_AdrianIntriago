package vista;

import java.util.ArrayList;
import java.util.Scanner;

import model.Numero;
import service.NumeroService;

public class MenuNumero {

    public static void mostrar() {

        Scanner leer = new Scanner(System.in);
        NumeroService service = new NumeroService();

        while (true) {
            System.out.println("\n--- MENÚ NÚMEROS ---");
            System.out.println("1. Crear número");
            System.out.println("2. Listar números");
            System.out.println("3. Eliminar número");
            System.out.println("4. Volver");
            System.out.print("Opción: ");

            int op = leer.nextInt();
            leer.nextLine();

            switch (op) {

                case 1:
                    System.out.print("ID: ");
                    int id = leer.nextInt();
                    leer.nextLine();

                    System.out.print("Nombre: ");
                    String nombre = leer.nextLine();

                    System.out.print("Duración (ej: 3.5): ");
                    double duracion = leer.nextDouble();
                    leer.nextLine();
                    
                    
                    System.out.print("Orden: ");
                    int orden = leer.nextInt();
                    leer.nextLine();

                    Numero n = new Numero(id, nombre, duracion, orden, new ArrayList<>());
                    service.save(n);
                    System.out.println("Número creado");
                    break;

                case 2:
                    service.getAll().forEach(num ->
                        System.out.println(num.getId() + " - " + num.getNombre())
                    );
                    break;

                case 3:
                    System.out.print("ID a eliminar: ");
                    service.delete(leer.nextInt());
                    leer.nextLine();
                    break;

                case 4:
                    return;

                default:
                    System.out.println("Opción inválida");
            }
        }
    }
}

