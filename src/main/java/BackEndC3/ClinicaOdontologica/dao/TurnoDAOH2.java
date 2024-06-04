package BackEndC3.ClinicaOdontologica.dao;

import BackEndC3.ClinicaOdontologica.model.Turno;
import org.apache.log4j.Logger;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicLong;
import java.util.Iterator;

public class TurnoDAOH2 implements iDao<Turno>{
    private Logger logger= Logger.getLogger(TurnoDAOH2.class);
    private List<Turno> turnos= new ArrayList<>();
    private AtomicLong idCounter = new AtomicLong();
    @Override
    public Turno guardar(Turno turno) {
        logger.info("iniciando las operaciones de guardado de un turno");
        turno.setId((int) idCounter.incrementAndGet());

        PacienteDAOH2 pacienteDAOH2= new PacienteDAOH2();
        OdontologoDAOH2 odontologoDAOH2= new OdontologoDAOH2();
        turno.setPaciente(pacienteDAOH2.buscarPorId(turno.getPaciente().getId()));
        turno.setOdontologo(odontologoDAOH2.buscarPorId(turno.getOdontologo().getId()));
        turnos.add(turno);
        logger.info("turno guardado con exito");

        return turno;
    }

    @Override
    public Turno buscarPorId(Integer id) {
        for (Turno turno : turnos) {
            if(turno.getId().equals(id)){
                return turno;
            }else{
                System.out.println("turno no encontrado");
            }

        }
        return null;
    }

    //        logger.info("turno eliminado con exito");

    @Override
    public String eliminar(Integer id) {
        logger.info("Iniciando la operación de eliminar un turno por id: " + id);
        Iterator<Turno> iterator = turnos.iterator();

        while (iterator.hasNext()) {
            Turno turno = iterator.next();
            if (turno.getId().equals(id)) {
                iterator.remove();
                logger.info("Turno con id " + id + " eliminado con éxito");
                return "Turno eliminado con éxito";
            }
        }

        logger.info("Turno con id " + id + " no encontrado");
        return "Turno no encontrado";
    }

    @Override
    public void actualizar(Turno turno) {
        logger.warn("Iniciando las operaciones de actualización de un turno con id: " + turno.getId());

        // Buscar y actualizar el turno existente
        for (int i = 0; i < turnos.size(); i++) {
            Turno turnoExistente = turnos.get(i);
            if (turnoExistente.getId().equals(turno.getId())) {
                // Actualizar los campos del turno existente
                turnoExistente.setPaciente(turno.getPaciente());
                turnoExistente.setOdontologo(turno.getOdontologo());

                logger.info("Turno con id " + turno.getId() + " actualizado con éxito");
            }
        }

        logger.info("Turno con id " + turno.getId() + " no encontrado");
    }



    @Override
    public List<Turno> buscarTodos() {
        logger.info("iniciando las operacion de mostrar todos los turnos");
        return turnos;
    }

    @Override
    public Turno buscarPorString(String string) {
        return null;
    }
}
