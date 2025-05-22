package org.example;

import java.util.Scanner;

/**
 * Clase responsable de interactuar con el usuario por consola.
 * Controla el menú principal y el flujo de login.
 */
public class ConsolaLogin {

    private final Scanner scanner; //
    private final DatosLogin datos;
    private final Login login;

    public ConsolaLogin() {
        scanner = new Scanner(System.in);
        datos = new DatosLogin(); // cargar los datos al iniciar.
        login = new Login(); // crear una instancia de Login.
    }

    /**
     * Controla el ciclo principal del menú del sistema.
     * Permite múltiples intentos de login mostrando el menú nuevamente.
     */
    public void menu() {
        String opcion;
        do {
            mostrarOpciones();
            opcion = scanner.nextLine();
            ejecutarOpcion(opcion);
        } while (!opcion.equals("2"));
        System.out.println("Hasta pronto");
        scanner.close(); // cerrar el scanner al finalizar.
    }

    /**
     * Muestra las opciones disponibles para el usuario
     */
    private void mostrarOpciones() {
        System.out.println("\n--- Menú Principal ---");
        System.out.println("1. Iniciar sesión");
        System.out.println("2. Salir");
        System.out.print("Seleccione una opción: ");
    }

    /**
     * Ejecuta la opción seleccionada por el usuario.
     *
     * @param opcion opción ingresada por el usuario
     */
    private void ejecutarOpcion(String opcion) {
        switch (opcion) {
            case "1":
                manejarLogin();
                break;
            case "2":
                break;
            default:
                System.out.println("Opción no válida. Intente de nuevo.");
                break;
        }
    }

    /**
     * Solicita usuario y contraseña, y muestra el resultado. [cite: 20]
     * Delega la verificación de credenciales a la clase Login. [cite: 5]
     */
    private void manejarLogin() {
        System.out.println("\n--- Iniciar Sesión ---");
        System.out.print("Ingrese usuario: ");
        String usuario = scanner.nextLine(); // pedir por consola
        System.out.print("Ingrese contraseña: ");
        String contrasena = scanner.nextLine(); // pedir contraseña por consola

        // llamar a login.autenticar() y mostrar mensaje según resultado
        if (login.autenticar(usuario, contrasena, datos)) {
            System.out.println("¡Autenticación exitosa! Bienvenido, " + usuario + ".");
            // aqui se podria redirigir a otra parte del sistema si existiera
        } else {
            System.out.println("Error: Usuario o contraseña incorrectos.");
            // mostrar mensajes de error y volver al menú si falla
        }
    }
}

