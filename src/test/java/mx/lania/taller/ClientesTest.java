package mx.lania.taller;

import static org.hamcrest.CoreMatchers.containsString;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.annotation.DirtiesContext;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Jaguilar
 */
@SpringBootTest
@DirtiesContext
@AutoConfigureMockMvc
public class ClientesTest {

    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void listaVehiculos() throws Exception {
        mockMvc.perform(get("/clientes"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("CLIENTE A")))
                .andExpect(content().string(containsString("BCDE900202MJCXYZO0")));
    }
    
    @Test
    void buscarVehiculos() throws Exception {
        mockMvc.perform(get("/clientes?nombre=b"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("BCDE900202MJCXYZ00")));
    }

    @Test
    void buscarVariosVehiculos() throws Exception {
        mockMvc.perform(get("/clientes?nombre=cli"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("BCDE900202MJCXYZ00")))
                .andExpect(content().string(containsString("BCDE900202MJCXYZ00")));
    }

    @Test
    void buscarVehiculosNoExistentes() throws Exception {
        mockMvc.perform(get("/clientes?nombre=QWERTY"))
                .andExpect(status().isOk())
                .andExpect(content().string("[]"));
    }
}
