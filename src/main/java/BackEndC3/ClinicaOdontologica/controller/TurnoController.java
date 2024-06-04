package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.dao.OdontologoDAOH2;
import BackEndC3.ClinicaOdontologica.dao.PacienteDAOH2;
import BackEndC3.ClinicaOdontologica.model.Odontologo;
import BackEndC3.ClinicaOdontologica.model.Paciente;
import BackEndC3.ClinicaOdontologica.model.Turno;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;
import BackEndC3.ClinicaOdontologica.service.PacienteService;
import BackEndC3.ClinicaOdontologica.service.TurnoService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/turnos")
public class TurnoController {
    private TurnoService turnoService;
    private PacienteService pacienteService;
    private OdontologoService odontologoService;

    public TurnoController() {
        turnoService= new TurnoService();
        pacienteService= new PacienteService();
        odontologoService= new OdontologoService();
    }

    @PostMapping
    public ResponseEntity<Turno> guardarTurno(@RequestBody Turno turno){
        Paciente pacienteBuscado= pacienteService.buscarPorID(turno.getPaciente().getId());
        Odontologo odontologoBuscado= odontologoService.buscarPorId(turno.getOdontologo().getId());
        if(pacienteBuscado!=null&&odontologoBuscado!=null){
            return ResponseEntity.ok(turnoService.guardarTurno(turno));
        } else {
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping
    public ResponseEntity<String> actualizarTurno(@RequestBody Turno turno){
        Turno turnoBuscado= turnoService.buscarPorId(turno.getId());
        if(turnoBuscado!=null){
            Paciente pacienteBuscado = pacienteService.buscarPorID(turno.getPaciente().getId());
            Odontologo odontologoBuscado = odontologoService.buscarPorId(turno.getOdontologo().getId());

            if (pacienteBuscado != null && odontologoBuscado != null) {
                turno.setPaciente(pacienteBuscado);
                turno.setOdontologo(odontologoBuscado);
                turnoService.actualizarTurno(turno);
                return ResponseEntity.ok("Turno actualizado con el id: "+ turno.getId()  + " con éxito");
            }
            return ResponseEntity.badRequest().body("Paciente u Odontólogo no encontrados");
        } else {
            return ResponseEntity.badRequest().body("Turno no encontrado");
        }
    }

    @GetMapping
    public ResponseEntity<List<Turno>> buscarTodos(){
        return ResponseEntity.ok(turnoService.buscarTodos());
    }
    @GetMapping("/{id}")
    public Turno getTurno(@PathVariable("id") Integer id) {
        return turnoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public  ResponseEntity<String> deleteTurno(@PathVariable("id") Integer id) {
        Turno turnoBuscado= turnoService.buscarPorId(id);

        if(turnoBuscado!=null) {
            String resultado = turnoService.eliminarPorID(id);
            if (resultado.equals("Turno eliminado con éxito")) {
                return ResponseEntity.ok(resultado);
            } else {
                return ResponseEntity.badRequest().body(resultado);
            }
        } else {
            return ResponseEntity.badRequest().body("Turno no encontrado");
        }
    }
}
