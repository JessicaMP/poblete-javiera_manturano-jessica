package BackEndC3.security;

import BackEndC3.service.OdontologoService;
import BackEndC3.service.PacienteService;
import BackEndC3.service.TurnoService;
import BackEndC3.dto.TurnoDTO;
import BackEndC3.entity.*;
import BackEndC3.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Component;

import java.time.LocalDate;

@Component
public class DatosIniciales implements ApplicationRunner {

    @Autowired
    private UsuarioRepository usuarioRepository;
    @Autowired
    private BCryptPasswordEncoder passwordEncoder;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private TurnoService turnoService;

    public void addUsers() {
        String passSinCifrar = "user";
        String passCifrado = passwordEncoder.encode(passSinCifrar);
        Usuario usuario = new Usuario("jorgito", "jpereryradh", "jorgito@gmail.com", passCifrado, UsuarioRole.ROLE_USER);
        usuarioRepository.save(usuario);

        String passSinCifrarAdmin = "admin";
        String passCifradoAdmin = passwordEncoder.encode(passSinCifrarAdmin);
        Usuario usuarioAdmin = new Usuario("jessica", "jessMP", "admin@admin.com", passCifradoAdmin, UsuarioRole.ROLE_ADMIN);
        usuarioRepository.save(usuarioAdmin);
    }

    public void content() {
        Paciente paciente = pacienteService.guardarPaciente(new Paciente("Jorgito", "pereyra", "11111", LocalDate.of(2024, 6, 20), new Domicilio("calle falsa", 123, "La Rioja", "Argentina"), "jorge.pereyra@digitalhouse.com"));
        Odontologo odontologo = odontologoService.registrarOdontologo(new Odontologo("MP10", "Gina", "Arias"));
        TurnoDTO turnoDTO = turnoService.registrarTurno(new Turno(paciente, odontologo, LocalDate.of(2024, 6, 20)));
    }
    @Override
    public void run(ApplicationArguments args) throws Exception {
        addUsers();
        content();
    }
}
