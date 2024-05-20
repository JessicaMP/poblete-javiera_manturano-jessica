import dao.BD;
import model.Odontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.OdontologoService;
import java.util.List;


public class OdontologoServiceTest {
    @Test
    public void ListarOdontologo(){
        BD.crearTablas();
        OdontologoService odontologoService= new OdontologoService();
        List<Odontologo> odontologos = (List<Odontologo>) odontologoService.listarOdontologos();
        Assertions.assertNotNull(odontologos, "La lista de odontólogos no debería ser nula");
    }
}