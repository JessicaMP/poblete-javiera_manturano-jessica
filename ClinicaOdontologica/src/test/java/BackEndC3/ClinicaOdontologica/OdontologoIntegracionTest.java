package BackEndC3.ClinicaOdontologica;

import BackEndC3.entity.Odontologo;
import BackEndC3.service.OdontologoService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import static org.junit.jupiter.api.Assertions.assertFalse;

@SpringBootTest
@AutoConfigureMockMvc(addFilters = false)
public class OdontologoIntegracionTest {

    @Autowired
    private OdontologoService odontologoService;
    @Autowired
    private MockMvc mockMvc;

    private void cargarDatos() {
        Odontologo odontologo = odontologoService.registrarOdontologo(new Odontologo("RA73", "Railey", "Alcasas"));
    }

    @Test
    public void ListarTodosLosOdontologosTest() throws Exception {
        cargarDatos();
        MvcResult respuesta = mockMvc.perform(MockMvcRequestBuilders.get("/odontologos").accept(MediaType.APPLICATION_JSON))
                                     .andDo(MockMvcResultHandlers.print())
                                     .andExpect(MockMvcResultMatchers.status().isOk())
                                     .andReturn();
        assertFalse(respuesta.getResponse().getContentAsString().isEmpty());
    }
}
