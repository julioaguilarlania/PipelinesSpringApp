package mx.lania.taller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.hamcrest.CoreMatchers.startsWithIgnoringCase;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 *
 * @author Jaguilar
 */
@SpringBootTest
@AutoConfigureMockMvc
public class VehiculosTest {
    
    @Autowired
    private MockMvc mockMvc;
    
    @Test
    void listaVehiculos() throws Exception {
        mockMvc.perform(get("/vehiculos"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(content().string(containsString("ABCD900101HJCXYZ00")));
    }

    @Test
    void listaVehiculosXML() throws Exception {
        mockMvc.perform(get("/vehiculos").accept(MediaType.TEXT_XML))
                .andExpect(status().isOk())
                .andExpect(content().contentTypeCompatibleWith(MediaType.TEXT_XML)) // Match no exacto por el charset
                .andExpect(content().string(containsString("ABCD900101HJCXYZ00")));
    }
    
    @Test
    void getVehiculoExistente() throws Exception {
        mockMvc.perform(get("/vehiculos/ABC1234"))
                .andExpect(status().isOk())
                .andExpect(content().string(containsString("ACURA")));
    }
    
    @Test
    void getVehiculoNoExistente() throws Exception {
        mockMvc.perform(get("/vehiculos/QWR9812"))
                .andExpect(status().isNotFound());
    }
}
