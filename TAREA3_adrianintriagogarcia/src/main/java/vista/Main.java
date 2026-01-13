package vista;

import java.util.Scanner;

import model.Credencial;
import model.Rol;
import service.CredencialService;

public class Main {

    private static Credencial usuarioActual = null;
    private static CredencialService credencialService = new CredencialService();

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        while (true) {

            
            while (usuarioActual == null) {
                System.out.println("\n=== LOGIN ===");
                System.out.print("Usuario: ");
                String user = sc.nextLine();
                System.out.print("Password: ");
                String pass = sc.nextLine();

                Credencial c = credencialService.getByUsername(user);

                if (c != null && c.getPassword().equals(pass)) {
                    usuarioActual = c;
                    System.out.println("Login correcto como " + c.getRol());
                } else {
                    System.out.println("Credenciales incorrectas");
                }
            }

            
            mostrarMenu(sc);
        }
    }

    private static void mostrarMenu(Scanner sc) {

        System.out.println("\n===== MENÚ PRINCIPAL =====");
        System.out.println("1. Ver espectáculos");

        if (usuarioActual.getRol() == Rol.ADMIN) {
            System.out.println("2. Gestión Personas");
            System.out.println("3. Gestión Credenciales");
        }

        if (usuarioActual.getRol() == Rol.COORDINADOR || usuarioActual.getRol() == Rol.ADMIN) {
            System.out.println("4. Gestión Espectáculos");
            System.out.println("5. Gestión Números");
        }

        if (usuarioActual.getRol() == Rol.ARTISTA) {
            System.out.println("6. Ver mi ficha");
        }

        System.out.println("0. Logout");
        System.out.print("Opción: ");

        int opcion = sc.nextInt();
        sc.nextLine();

        switch (opcion) {

            case 1 -> MenuEspectaculo.mostrar();

            case 2 -> {
                if (usuarioActual.getRol() == Rol.ADMIN)
                    MenuPersona.mostrar();
            }

            case 3 -> {
                if (usuarioActual.getRol() == Rol.ADMIN)
                    MenuCredencial.mostrar();
            }

            case 4 -> {
                if (usuarioActual.getRol() != Rol.ARTISTA)
                    MenuEspectaculo.mostrar();
            }

            case 5 -> {
                if (usuarioActual.getRol() != Rol.ARTISTA)
                    MenuNumero.mostrar();
            }

            case 6 -> {
                if (usuarioActual.getRol() == Rol.ARTISTA)
                    System.out.println("TODO: Ver ficha artista");
            }

            case 0 -> {
                usuarioActual = null;
                System.out.println("Sesión cerrada");
            }

            default -> System.out.println("Opción inválida");
        }
    }
}
