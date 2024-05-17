import dao.BD;
import model.Odontologo;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import service.OdontologoService;

public class OdontologoServiceTest {
    @Test
    public void ListarOdontologo(){
        BD.crearTablas();
        OdontologoService odontologoService= new OdontologoService();
        Odontologo odontologo = odontologoService.listarOdontologos();
        Assertions.assertTrue(odontologo!=null);
    }
}