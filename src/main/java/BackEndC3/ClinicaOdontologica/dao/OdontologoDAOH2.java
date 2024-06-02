package BackEndC3.ClinicaOdontologica.dao;


import BackEndC3.ClinicaOdontologica.model.Odontologo;
import BackEndC3.ClinicaOdontologica.model.Paciente;
import org.apache.log4j.Logger;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OdontologoDAOH2 implements iDao<Odontologo> {
    private static final Logger logger= Logger.getLogger(OdontologoDAOH2.class);
    private static final String SQL_INSERT="INSERT INTO ODONTOLOGOS(MATRICULA, NOMBRE, APELLIDO) VALUES(?,?,?)";
    private static final String SQL_SELECT_ONE="SELECT * FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_DELETE="DELETE FROM ODONTOLOGOS WHERE ID=?";
    private static final String SQL_UPDATE="UPDATE ODONTOLOGOS SET MATRICULA=?, NOMBRE=?, APELLIDO=? WHERE ID=?";
    private static final String SQL_SELECT_ALL="SELECT * FROM ODONTOLOGOS";
    private static final String SQL_SELECT_BY_MATRICULA="SELECT * ODONTOLOGOS WHERE MATRICULA=?";
    @Override
    public Odontologo guardar(Odontologo odontologo) {
        logger.info("iniciando las operaciones de guardado del odontologo :  "+odontologo.getMatricula());
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psInsert= connection.prepareStatement(SQL_INSERT, Statement.RETURN_GENERATED_KEYS);
            psInsert.setString(1,odontologo.getMatricula());
            psInsert.setString(2, odontologo.getNombre());
            psInsert.setString(3, odontologo.getApellido());
            psInsert.execute();
            ResultSet clave= psInsert.getGeneratedKeys();
            while (clave.next()){
                odontologo.setId(clave.getInt(1));
            }

        } catch (Exception e){
            logger.error(e.getMessage());
        }
        return odontologo;
    }

    @Override
    public Odontologo buscarPorId(Integer id) {
        logger.info("iniciando las operaciones de:  buscado por ID: "+id);
        Odontologo odontologo=null;
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psSelectOne= connection.prepareStatement(SQL_SELECT_ONE);
            psSelectOne.setInt(1,id);
            ResultSet rs= psSelectOne.executeQuery();
            while(rs.next()){
                odontologo= new Odontologo(rs.getInt(1),rs.getString(2),rs.getString(3),rs.getString(4));
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return odontologo;
    }


    @Override
    public String eliminar(Integer id) {
        logger.info("Iniciando la operacion de eliminar un odontologo por id : "+id);
        Connection connection= null;
        Odontologo odontologo= null;
        String message = "";

        try {
            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            PreparedStatement psSElectOne= connection.prepareStatement(SQL_DELETE);
            psSElectOne.setInt(1,id);
            int rs= psSElectOne.executeUpdate();

            if (rs > 0) {
                message = "Odontologo con ID: " + id + " fue eliminado exitosamente.";
                logger.info(message);
            } else {
                message = "No se encontró un Odontologo con ID: " + id + " para eliminar.";
                logger.warn(message);
            }
        }  catch (Exception e){
            logger.error(e.getMessage());
        }
        return message;
    }

    @Override
    public void actualizar(Odontologo odontologo) {
        logger.warn("Iniciando las operaciones de actualizacion de un odontologo con id: " + odontologo.getId());
        Connection connection= null;
        try{
            connection= BD.getConnection();
            PreparedStatement psUpdate= connection.prepareStatement(SQL_UPDATE);
            psUpdate.setString(1, odontologo.getMatricula());
            psUpdate.setString(2, odontologo.getNombre());
            psUpdate.setString(3, odontologo.getApellido());
            psUpdate.setInt(4,odontologo.getId());
            psUpdate.execute();

        } catch (Exception e){
            logger.error(e.getMessage());
        }
    }

    @Override
    public List<Odontologo> buscarTodos() {
        logger.info("iniciando las operaciones de:  listado de todos los odontologos");
        List<Odontologo> listaOdontologos= new ArrayList<>();
        Odontologo odontologo= null;
        Connection connection= null;
        try{
            connection= BD.getConnection();
            Statement statement= connection.createStatement();
            ResultSet rs= statement.executeQuery(SQL_SELECT_ALL);
            while (rs.next()){
                //construir el odontologo
                odontologo= new Odontologo(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4));
                //solo me resta agregarselo a la lista.
                listaOdontologos.add(odontologo);
            }

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return listaOdontologos;
    }

    @Override
    public Odontologo buscarPorString(String string) {
        logger.info("iniciando las operaciones de: ");
        Connection connection= null;
        try{
            connection= BD.getConnection();

        }catch (Exception e){
            logger.error(e.getMessage());
        }
        return null;
    }

//    @Override
//    public List<Odontologo> listarOdontologos() {
//        logger.info("iniciando la operacion de buscador de un odontogos");
//        Connection connection = null;
//        List<Odontologo> odontologos = new ArrayList<>();
//
//        try {
//            connection = BD.getConnection();
//            Statement statement = connection.createStatement();
//            PreparedStatement psSELectAll = connection.prepareStatement(SQL_SELECT_ALL);
//            ResultSet rs = psSELectAll.executeQuery();
//            while (rs.next()) {
//                Odontologo newOdontologo = new Odontologo(rs.getString("nombre"), rs.getString("apellido"), rs.getInt("matricula"));
//                odontologos.add(newOdontologo);
//            }
//
//        } catch (Exception e) {
//            logger.error(e.getMessage());
//        }
//        return odontologos;
//    }
}