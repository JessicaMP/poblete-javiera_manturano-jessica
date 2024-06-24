package BackEndC3.service;

import BackEndC3.entity.Usuario;
import BackEndC3.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UsuarioService implements UserDetailsService {
    @Autowired
    private UsuarioRepository usuarioRepository;
    //aca necesitamos un metodo que nos devuelva la autenticación

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        //como resolvemos?
        Optional<Usuario> usuarioBuscado = usuarioRepository.findByEmail(username);
        if (usuarioBuscado.isPresent()) {
            return usuarioBuscado.get();
        }
        throw new UsernameNotFoundException("Usuario inexistente: " + username);
    }
}
