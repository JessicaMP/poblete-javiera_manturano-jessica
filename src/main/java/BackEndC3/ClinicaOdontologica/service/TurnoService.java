package BackEndC3.ClinicaOdontologica.service;

import BackEndC3.ClinicaOdontologica.dao.TurnoDAOH2;
import BackEndC3.ClinicaOdontologica.dao.iDao;
import BackEndC3.ClinicaOdontologica.model.Odontologo;
import BackEndC3.ClinicaOdontologica.model.Turno;

import java.util.List;

public class TurnoService {
    private iDao<Turno> turnoiDao;

    public TurnoService() {
        turnoiDao= new TurnoDAOH2();
    }
    public Turno guardarTurno(Turno turno){
        return turnoiDao.guardar(turno);
    }
    public List<Turno> buscarTodos(){
        return turnoiDao.buscarTodos();
    }
    public Turno buscarPorId(Integer id){
        return turnoiDao.buscarPorId(id);
    }
    public String eliminarPorID(Integer id){
        return turnoiDao.eliminar(id);
    }

    public void actualizarTurno(Turno turno){
         turnoiDao.actualizar(turno);
    }


}
