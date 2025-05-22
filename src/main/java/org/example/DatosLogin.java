package org.example;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;


public class DatosLogin {

    // se usara un HashMap para almacenar las credenciales (usuario como clave, contraseña como valor)
    private Map<String, String> credencialesMap;

    /**
     * Constructor que inicializa las credenciales desde el archivo
     */
    public DatosLogin() {
        credencialesMap = new HashMap<>();
        cargarUsuarios(); // llama al metodo para cargar usuarios al instanciar.
    }

    /**
     * Lee el archivo login.txt y agrega las líneas válidas al mapa de credenciales
     * Ignora líneas vacías o mal formateadas
     * Cierra adecuadamente los recursos de E/S utilizando try-with-resources
     */
    private void cargarUsuarios() {
        // el nombre del archivo donde se guardan los usuarios y contraseñas
        String nombreArchivo = "src/main/resources/login.txt";  //busca el archivo

        // uso try-with-resources para asegurar que BufferedReader y FileReader se cierren
        try (BufferedReader lector = new BufferedReader(new FileReader(nombreArchivo))) {
            String linea;
            // lee línea por línea
            while ((linea = lector.readLine()) != null) {
                if (linea.trim().isEmpty() || !linea.contains(";")) {
                    // ignorar líneas vacías o que no contengan el delimitador (;)
                    continue;
                }
                String[] partes = linea.split(";", 2); // divide en usuario y contraseña
                if (partes.length == 2) {
                    String usuario = partes[0].trim(); //para eliminar espacios en blanco
                    String contrasena = partes[1].trim();
                    if (!usuario.isEmpty() && !contrasena.isEmpty()) {
                        credencialesMap.put(usuario, contrasena);
                    } else {
                        // ignorar si el usuario o la contraseña están vacíos después del split.
                        System.err.println("usuario o contraseña vacíos en " + nombreArchivo + ": " + linea);
                    } //err para mensajes de error
                } else {
                    // ignorar líneas que no tengan la forma usuario;contraseña.
                    System.err.println("formato incorrecto en " + nombreArchivo + ": " + linea);
                }
            }
        } catch (IOException e) {
            // manejo de errores si el archivo no se encuentra o no se puede leer
            System.err.println("Error al leer el archivo " + nombreArchivo + ": " + e.getMessage());
            // considerar si la aplicación debe terminar o continuar sin datos cargados.
            // por ahora, solo se imprime el error.
        }
    }

    /**
     * Provee un metodo para obtener la contraseña a partir del usuario
     *
     * @param usuario El nombre de usuario.
     * @return La contraseña si el usuario existe, o null en caso contrario.
     */
    public String obtenerContrasena(String usuario) {
        return credencialesMap.get(usuario);
    }
}

