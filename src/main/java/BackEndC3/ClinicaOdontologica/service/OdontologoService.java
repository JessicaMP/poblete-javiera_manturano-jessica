package BackEndC3.ClinicaOdontologica.service;

import BackEndC3.ClinicaOdontologica.dao.OdontologoDAOH2;
import BackEndC3.ClinicaOdontologica.dao.iDao;
import BackEndC3.ClinicaOdontologica.model.Odontologo;
import BackEndC3.ClinicaOdontologica.model.Paciente;

import java.util.List;

public class OdontologoService {
    private iDao<Odontologo> odontologoiDao;

    public OdontologoService() {
        odontologoiDao = new OdontologoDAOH2();
    }

    //metodos manuales
    public  Odontologo guardarOdontologo(Odontologo odontologo){
        return odontologoiDao.guardar(odontologo);
    }

    public List<Odontologo> listarOdontologos() {
        return odontologoiDao.buscarTodos();
    }

    public Odontologo buscarPorId(Integer id){
        return odontologoiDao.buscarPorId(id);
    }

    public void actualizarOdontologo(Odontologo odontologo){
        odontologoiDao.actualizar(odontologo);
    }

    public String eliminarPorID(Integer id){
        return odontologoiDao.eliminar(id);
    }

}