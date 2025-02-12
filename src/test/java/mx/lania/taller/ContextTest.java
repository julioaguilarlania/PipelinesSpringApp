package mx.lania.taller;

import mx.lania.taller.controladores.ControladorVehiculos;
import static org.assertj.core.api.Assertions.assertThat;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.DirtiesContext;

/**
 *
 * @author Jaguilar
 */
@SpringBootTest
@DirtiesContext
public class ContextTest {
    
    @Autowired
    ControladorVehiculos ctrlVehiculos;
    
    @Test
    void cargaContexto() {
        assertThat(ctrlVehiculos).isNotNull();
    }
}
