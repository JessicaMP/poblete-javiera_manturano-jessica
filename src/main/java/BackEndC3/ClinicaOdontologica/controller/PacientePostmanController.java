package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.model.Paciente;
import BackEndC3.ClinicaOdontologica.service.PacienteService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController //para trabajar sin tecnologia de vista
// @Controller<-- es controller pq vamos a usar una tecnologia de vista

@RequestMapping("/api/pacientes")
public class PacientePostmanController {
    private PacienteService pacienteService;

    public PacientePostmanController() {
        pacienteService= new PacienteService();
    }
    @PostMapping //--> nos permite persistir los datos que vienen desde la vista
    public Paciente guardarPaciente(@RequestBody Paciente paciente){
        return pacienteService.guardarPaciente(paciente);
    }
    @PutMapping
    public String actualizarPaciente(@RequestBody Paciente paciente){

        Paciente pacienteBuscado= pacienteService.buscarPorID(paciente.getId());
        if(pacienteBuscado!=null){
            pacienteService.actualizarPaciente(paciente);
            return "paciente actualizado con exito";
        }else{
            return "paciente no encontrado";
        }

    }

    @GetMapping
    public List<Paciente> getPacientes() {
        return pacienteService.listarPacientes();
    }
    @GetMapping("/{id}")
    public Paciente getPaciente(@PathVariable("id") Integer id) {
        return pacienteService.buscarPorID(id);
    }


    @DeleteMapping("/{id}")
    public String deletePaciente(@PathVariable("id") Integer id) {
        return  pacienteService.eliminarPorID(id);
    }
}
