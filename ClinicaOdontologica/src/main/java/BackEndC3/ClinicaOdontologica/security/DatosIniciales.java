package BackEndC3.ClinicaOdontologica.security;

import BackEndC3.ClinicaOdontologica.entity.Usuario;
import BackEndC3.ClinicaOdontologica.entity.UsuarioRole;
import BackEndC3.ClinicaOdontologica.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;

    @Override
    public void run(ApplicationArguments args) throws Exception {
        String passSinCifrar = "user";
        String passCifrado = passwordEncoder.encode(passSinCifrar);
        Usuario usuario = new Usuario("jorgito", "jpereryradh", "jorgito@gmail.com", passCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);

        String passSinCifrarAdmin = "admin";
        String passCifradoAdmin = passwordEncoder.encode(passSinCifrarAdmin);
        Usuario usuarioAdmin = new Usuario("jessica", "jessMP", "admin@admin.com", passCifradoAdmin, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioAdmin);
    }
}
