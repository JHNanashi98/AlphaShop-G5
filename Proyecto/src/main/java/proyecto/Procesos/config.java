package proyecto.Procesos;

import proyecto.entidades.Usuario;

/*@Configuration*/
public class config {
    /*@PostMapping("/login")*/
    public String login(/*@RequestBody*/ Usuario user) {
        String email = user.getEmail();
        String contra = user.getContra();

        return "Autentificación exitosa";
    }
    /*@PostMapping("/registro")*/
    public String registrarCuenta(/*@RequestBody*/ Usuario usuario) {

        return "Cuenta registrada exitosamente";
    }
}
