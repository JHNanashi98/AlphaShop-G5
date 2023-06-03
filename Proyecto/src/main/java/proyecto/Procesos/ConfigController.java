package proyecto.Procesos;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import proyecto.Repositorio.UsuarioRepository;
import proyecto.entidades.Usuario;

@RestController
public class ConfigController {

    private final UsuarioRepository usuarioRepository;

    @Autowired
    public ConfigController(UsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @PostMapping("/recepcionDatosRegistro")
    public String registrarCuenta(@RequestBody Usuario usuario) {
        if (usuarioRepository.existsByEmail(usuario.getEmail())) {
            return "El correo electrónico ya está registrado";
        }

        Usuario nuevoUsuario = new Usuario();
        nuevoUsuario.setEmail(usuario.getEmail());
        nuevoUsuario.setContra(usuario.getContra());
        usuarioRepository.save(nuevoUsuario);

        return "Cuenta registrada exitosamente";
    }

    @PostMapping("/login")
    public String login(@RequestBody Usuario usuario) {
        Usuario usuarioExistente = usuarioRepository.findByEmail(usuario.getEmail());

        if (usuarioExistente == null || !usuarioExistente.getContra().equals(usuario.getContra())) {
            return "Inicio de sesión fallido: correo electrónico o contraseña incorrectos";
        }

        return "Inicio de sesión exitoso";
    }
}