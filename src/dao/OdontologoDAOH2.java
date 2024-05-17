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
    private static final String SQL_SELECT_ALL = "SELECT * FROM ODONTOLOGO?";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return null;
    }

    @Override
    public list<Odontologo> listarOdontologos() {
        logger.info("iniciando la operacion de buscador de un odontogos");
        Connection connection = null;
        list<Odontologo> odontologos = new ArrayList<Object>();

        try {
            connection = BD.getConnection();
            Statement statement = connection.createStatement();
            PreparedStatement psSELectAll = connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs = psSELectAll.executeQuery();
            while (rs.next()) {
                Odontologo newOdontologo = new Odontologo(rs.getString(1), rs.getString(2), rs.getInt(3));
                odontologos.add(newOdontologo);
            }

        } catch (Exception e) {
            logger.error(e.getMessage());
        }
    }
}