package dao;
import model.Odontologo;
import org.apache.log4j.Logger;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo>{
    private static final Logger logger= Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGO?";

    @Override
    public Odontologo guardar(Odontologo odontologo) {
        return null;
    }

    @Override
    public Odontologo buscarPorOdontologos {
        logger.info("iniciando la operacion de buscado de un odontogos");
        Connection connection= null;
        Odontologo odontologo= null;
        Matricula matricula= null;
        try{
            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            PreparedStatement psSELectAll= connection.prepareStatement(SQL_SELECT_ALL);
            ResultSet rs= psSELectAll.executeQuery();
            while(rs.next()){
                Odontologo= new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3).toLocalDate());
            }


        }catch (Exception e){
            logger.error(e.getMessage());
        }

    }