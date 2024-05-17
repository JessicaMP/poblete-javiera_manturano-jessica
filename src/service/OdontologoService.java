package service;

import dao.OdontologoDAOH2;
import dao.iDao;
import model.Odontologo;

public class OdontologoService {
    private iDao<Odontologo> odontologoiDao;

    public OdontologoService() {
        odontologoiDao = new OdontologoDAOH2();
    }

    //metodos manuales
    public Odontologo guardarOdontologo(Odontologo odontologo) {
        return odontologoiDao.guardar(odontologo);
    }

    public Odontologo listarOdontologos() {
        return odontologoiDao.listarOdontologos();

    }
}