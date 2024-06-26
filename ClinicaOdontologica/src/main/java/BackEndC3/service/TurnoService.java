package BackEndC3.service;

import BackEndC3.entity.Odontologo;
import BackEndC3.entity.Paciente;
import BackEndC3.dto.TurnoDTO;
import BackEndC3.entity.Turno;
import BackEndC3.repository.TurnoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class TurnoService {
    @Autowired
    private TurnoRepository turnoRepository;

    public Turno registrarTurno(Turno turno) {
//        Turno turnoGuardado = turnoRepository.save(turno);
//        return turnoAturnoDTO(turnoGuardado);
        return turnoRepository.save(turno);
    }

    //    public Optional<TurnoDTO> buscarPorId(Long id) {
//        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
//        if (turnoBuscado.isPresent()) {
//            return Optional.of(turnoAturnoDTO(turnoBuscado.get()));
//        }
//        return Optional.empty();
//    }
    public Optional<Turno> buscarPorId(Long id) {
        Optional<Turno> turnoBuscado = turnoRepository.findById(id);
        if (turnoBuscado.isPresent()) {
            return Optional.of(turnoBuscado.get());
        }
        return Optional.empty();
    }

    public void eliminarTurno(Long id) {
        turnoRepository.deleteById(id);
    }

//    public List<Turno>  buscarPorIdPaciente(Long id) {
//        return turnoRepository.findByIdPacientes(id);
//    }
//
//    public List<Turno> buscarPorIdOdontologo(Long id) {
//        return turnoRepository.findAllByIdOdontologos(id);
//    }

//    public List<TurnoDTO> listarTodos() {
//        List<Turno> listaTurno = turnoRepository.findAll();
//        List<TurnoDTO> listaDTO = new ArrayList<>();
//        for (Turno turno : listaTurno) {
//            listaDTO.add(turnoAturnoDTO(turno));
//
//        }
//        return listaDTO;
//    }

    public List<Turno> listarTodos() {
        return turnoRepository.findAll();
    }

    public void actualizarTurno(Turno turno) {
        turnoRepository.save(turno);
    }

    public TurnoDTO turnoAturnoDTO(Turno turno) {
        TurnoDTO turnoDTO = new TurnoDTO();
        turnoDTO.setId(turno.getId());
        turnoDTO.setFecha(turno.getFecha());
        turnoDTO.setPacienteId(turno.getPaciente().getId());
        turnoDTO.setOdontologoId(turno.getOdontologo().getId());

        return turnoDTO;
    }

    public Turno turnoDTOaTurno(TurnoDTO turnoDTO) {
        Turno turno = new Turno();
        Odontologo odontologo = new Odontologo();
        Paciente paciente = new Paciente();
        odontologo.setId(turnoDTO.getOdontologoId());
        paciente.setId(turnoDTO.getPacienteId());
        turno.setFecha(turnoDTO.getFecha());
        turno.setId(turnoDTO.getId());
        turno.setOdontologo(odontologo);
        turno.setPaciente(paciente);
        return turno;
    }

}
