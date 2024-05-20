package service;

import dao.OdontologoDAOH2;
import dao.iDao;
import model.Odontologo;

import java.util.List;

public class OdontologoService {
    private iDao<Odontologo> odontologoiDao;

    public OdontologoService() {
        odontologoiDao = new OdontologoDAOH2();
    }

    //metodos manuales
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoiDao.guardar(odontologo);
    }

    public List<Odontologo> listarOdontologos() {
        return odontologoiDao.listarOdontologos();
    }
}