package BackEndC3.ClinicaOdontologica.controller;


import BackEndC3.ClinicaOdontologica.model.Odontologo;
import BackEndC3.ClinicaOdontologica.model.Paciente;
import BackEndC3.ClinicaOdontologica.service.PacienteService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.ArrayList;
import java.util.List;

@Controller //<-- es controller pq vamos a usar una tecnologia de vista
@RequestMapping("/pacientes")
public class PacientesController {
    private PacienteService pacienteService;

    public PacientesController() {
        pacienteService= new PacienteService();
    }
    @GetMapping
    public String listarPacientes(Model model){
        List<Paciente> pacientes = pacienteService.listarPacientes();
        model.addAttribute("pacientes", pacientes);
        return "pacientes";

        //return pacienteService.buscarPorEmail(email);
    }
}
