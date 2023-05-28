package proyecto.proyecto;

import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Scanner;
import org.json.JSONArray;
import org.json.JSONObject;
import org.json.JSONTokener;

public class MAIN {

    public static void main(String[] args) {

        ArrayList<Usuario> usuarios = new ArrayList<Usuario>();

        Scanner sc = new Scanner(System.in);
        int opc = 0;

        do {
            System.out.println("1. Crear usuario");
            System.out.println("2. Inicia sesión");
            System.out.println("3. Cierra sesión");

            System.out.println("4. Salir del programa");
            opc = sc.nextInt();

            switch (opc) {
                case 1:
                    crearCuenta(usuarios, sc);
                    break;
                case 2:
                    iniciarSesion(usuarios, sc);
                    break;
                case 3:
                    cerrarSesion();
                case 4:
                    JSONArray jsonArray = new JSONArray();
                    for (Usuario usuario : usuarios) {
                        JSONObject jsonObject = new JSONObject();
                        jsonObject.put("nombre", usuario.getNombre());
                        jsonObject.put("apellido", usuario.getApellido());
                        jsonObject.put("tipoDocumento", usuario.getDocumento());
                        jsonObject.put("numeroDocumento", usuario.getNumero());
                        jsonObject.put("correo", usuario.getCorreo());
                        jsonObject.put("clave", usuario.getClave());

                        jsonArray.put(jsonObject);
                    }
                    try (FileWriter file = new FileWriter("usuarios.json")) {
                        file.write(jsonArray.toString());
                        System.out.println("El archivo JSON se ha creado correctamente.");
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    System.out.println("OPCION INVALIDA");
                    break;
            }

        } while (opc != 4);
    }

    public static void crearCuenta(ArrayList<Usuario> usuarios, Scanner sc) {
        JSONObject objeto = new JSONObject();
        System.out.println("Ingrese su nombre: ");
        String nombre = sc.next();
        System.out.println("Ingrese su apellido: ");
        String apellido = sc.next();
        System.out.println("Ingrese tipo de documento: ");
        String tDoc = sc.next();
        System.out.println("Ingrese el numero de documento: ");
        String numerodeDoc = sc.next();
        System.out.println("Ingrese correo electrónico: ");
        String correo = sc.next();
        System.out.println("Ingrese la clave: ");
        String clave = sc.next();
        usuarios.add(new Usuario(nombre, apellido, tDoc, numerodeDoc, correo, clave));
        System.out.println("SE CREO CON EXITO!");
    }

    public static void iniciarSesion(ArrayList<Usuario> usuarios, Scanner sc) {
        System.out.println("Ingrese su correo: ");
        String correo = sc.next();
        System.out.println("Digite la clave: ");
        String clave = sc.next();
        JSONArray auxiliar;

        try {
            FileReader lector = new FileReader("usuarios.json");
            auxiliar = new JSONArray(new JSONTokener(lector));
            lector.close();
        } catch (IOException e) {
            System.out.println("NO SE PUEDE LEER EL ARCHIVO");
            return;
        }

        boolean usuarioExiste = false;

        for (int i = 0; i < auxiliar.length(); i++) {
            JSONObject aux1 = auxiliar.getJSONObject(i);
            String usuarioCorreo = aux1.getString("correo");
            String usuarioClave = aux1.getString("clave");
            if (usuarioCorreo.equalsIgnoreCase(correo) && usuarioClave.equalsIgnoreCase(clave)) {
                usuarioExiste = true;
                break;
            }
        }

        if (usuarioExiste) {
            System.out.println("Se ha iniciado la sesion");
        } else {
            System.out.println("Usuario no encontrado");
        }
    }

    public static void cerrarSesion() {
        System.out.println("Sesion cerrada");
    }
}
