package dao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo> {
    private static final Logger logger = Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_SELECT_ALL = "SELECT * FROM ODONTOLOGOS";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return null;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        return null;
    }

    @Override
    public void eliminar(Integer id) {

    }

    @Override
    public void actualizar(Odontologo odontologo) {

    }

    @Override
    public List<Odontologo> buscarTodos() {
        return null;
    }

    @Override
    public List<Odontologo> listarOdontologos() {
        logger.info("iniciando la operacion de buscador de un odontogos");
        Connection connection = null;
        List<Odontologo> odontologos = new ArrayList<>();

        try {
            connection = BD.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement psSELectAll = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = psSELectAll.executeQuery();
            while (rs.next()) {
                Odontologo newOdontologo = new Odontologo(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("matricula"));
                odontologos.add(newOdontologo);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
        return odontologos;
    }
}