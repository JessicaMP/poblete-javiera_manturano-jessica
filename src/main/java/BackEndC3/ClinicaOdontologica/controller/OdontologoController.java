package BackEndC3.ClinicaOdontologica.controller;

import BackEndC3.ClinicaOdontologica.model.Odontologo;
import BackEndC3.ClinicaOdontologica.model.Paciente;
import BackEndC3.ClinicaOdontologica.service.OdontologoService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/odontologos")
public class OdontologoController {
    private OdontologoService odontologoService;

    public OdontologoController() {
        odontologoService= new OdontologoService();
    }
    @PostMapping
    public Odontologo guardarOdontologo(@RequestBody Odontologo odontologo){
        return odontologoService.guardarOdontologo(odontologo);
    }

    @PutMapping
    public String actualizarOdontologo(@RequestBody Odontologo odontologo){
        Odontologo odontologoBuscado= odontologoService.buscarPorId(odontologo.getId());
        if(odontologoBuscado!=null){
            odontologoService.actualizarOdontologo(odontologo);
            return "Odontologo actualizado con exito";
        } else{
            return "Odontologo no encontrado";
        }

    }

    @GetMapping
    public List<Odontologo> getOdontologos() {
        return odontologoService.listarOdontologos();
    }

    @GetMapping("/{id}")
    public Odontologo getOdontologo(@PathVariable("id") Integer id) {
        return odontologoService.buscarPorId(id);
    }

    @DeleteMapping("/{id}")
    public String deleteOdontologo(@PathVariable("id") Integer id) {
        return  odontologoService.eliminarPorID(id);
    }

}
