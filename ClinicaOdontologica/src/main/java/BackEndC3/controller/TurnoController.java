package BackEndC3.controller;


import BackEndC3.dto.TurnoDTO;
import BackEndC3.entity.Odontologo;
import BackEndC3.entity.Paciente;
import BackEndC3.entity.Turno;
import BackEndC3.exception.ResourceNotFoundException;
import BackEndC3.service.OdontologoService;
import BackEndC3.service.PacienteService;
import BackEndC3.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/turnos")
public class TurnoController {
    @Autowired
    private TurnoService turnoService;
    @Autowired
    private PacienteService pacienteService;
    @Autowired
    private OdontologoService odontologoService;

    @PostMapping
    public ResponseEntity<TurnoDTO> guardarTurno(@RequestBody Turno turno) {
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());

        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            turno.setPaciente(pacienteBuscado.get());
            turno.setOdontologo(odontologoBuscado.get());
            return ResponseEntity.ok(turnoService.registrarTurno(turno));
        }
        return ResponseEntity.badRequest().build();

    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno) {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPorId(turno.getId());
        if (!turnoBuscado.isPresent()) {
            return ResponseEntity.badRequest().body("Turno no encontrado");
        }
        Optional<Paciente> pacienteBuscado = pacienteService.buscarPorId(turno.getPaciente().getId());
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());

        if (pacienteBuscado.isPresent() && odontologoBuscado.isPresent()) {
            turno.setPaciente(pacienteBuscado.get());
            turno.setOdontologo(odontologoBuscado.get());
            turnoService.actualizarTurno(turno);
            return ResponseEntity.ok("Turno actualizado con el id: " + turno.getId() + " con éxito");
        }
        return ResponseEntity.badRequest().body("Paciente u Odontólogo no encontrados");

    }

    @GetMapping
    public ResponseEntity<List<TurnoDTO>> buscarTodos() {
        return ResponseEntity.ok(turnoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<TurnoDTO>> getTurno(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()) {
            return ResponseEntity.ok(turnoService.buscarPorId(id));
        }
        throw new ResourceNotFoundException("Turno no encontrado");
    }

    @DeleteMapping
    public ResponseEntity<String> eliminarTurno(@PathVariable Long id) {
        Optional<TurnoDTO> turnoBuscado = turnoService.buscarPorId(id);
        if (turnoBuscado.isPresent()) {
            turnoService.eliminarTurno(id);
            return ResponseEntity.ok("Turno " + id + " eliminado con exito");
        }
        return ResponseEntity.notFound().build();
    }
}