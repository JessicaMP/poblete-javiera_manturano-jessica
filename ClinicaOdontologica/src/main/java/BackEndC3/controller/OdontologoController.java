package BackEndC3.controller;

import BackEndC3.entity.Odontologo;
import BackEndC3.entity.Turno;
import BackEndC3.exception.ResourceNotFoundException;
import BackEndC3.service.OdontologoService;
import BackEndC3.service.TurnoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/odontologos")
public class OdontologoController {
    @Autowired
    private OdontologoService odontologoService;

    @Autowired
    private TurnoService turnoService;

    @PostMapping
    public ResponseEntity<Odontologo> guardarOdontologo(@RequestBody Odontologo odontologo) {
        return ResponseEntity.ok(odontologoService.registrarOdontologo(odontologo));
    }

    @PutMapping
    public ResponseEntity<String> actualizarOdontologo(@RequestBody Odontologo odontologo) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(odontologo.getId());
        if (odontologoBuscado.isPresent()) {
            odontologoService.actualizarOdontologo(odontologo);
            return ResponseEntity.ok("Odontologo " + odontologo.getId() + " actualizado con éxito");
        }
        throw new ResourceNotFoundException("Odontologo no encontrado");
    }

    @GetMapping
    public ResponseEntity<List<Odontologo>> listarTodos() {
        return ResponseEntity.ok(odontologoService.listarTodos());
    }

    @GetMapping("/{id}")
    public ResponseEntity<Optional<Odontologo>> getOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        if (odontologoBuscado.isPresent()) {
            return ResponseEntity.ok(odontologoBuscado);
        }
        throw new ResourceNotFoundException("Odontologo no encontrado");
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> eliminarOdontologo(@PathVariable Long id) throws ResourceNotFoundException {
        Optional<Odontologo> odontologoBuscado = odontologoService.buscarPorId(id);
        if (odontologoBuscado.isPresent()) {
//            List<Turno> turnos = turnoService.buscarPorIdOdontologo(id);
//            System.out.println(turnos.isEmpty());
            odontologoService.eliminarOdontologo(id);
            return ResponseEntity.ok("Odontologo eliminado con exito");
        }
        throw new ResourceNotFoundException("Odontologo no encontrado");
    }
}
