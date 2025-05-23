package mx.lania.taller.controladores;

import mx.lania.taller.entidades.Cliente;
import mx.lania.taller.repositorios.RepositorioClientes;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import jakarta.validation.Valid;
import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class ControladorClientes {
    RepositorioClientes repoClientes;

    public ControladorClientes(RepositorioClientes repoClientes) {
        this.repoClientes = repoClientes;
    }

    @GetMapping("clientes")
    public List<Cliente>  getTodos() {
        return this.repoClientes.findAll();
    }

    @GetMapping("clientes/{id}")
    public ResponseEntity<Cliente> getPorId(
            @PathVariable("id") Integer clienteId
    ) {
        Optional<Cliente> cl = this.repoClientes.findById(clienteId);
        if (cl.isPresent()) {
            return ResponseEntity.ok(cl.get());
        }
        else {
            return ResponseEntity.notFound().build();
        }
    }
    
    @GetMapping(value="clientes", params = {"nombre"})
    public ResponseEntity<List<Cliente>> buscarPorNombre(
            @RequestParam("nombre") String cadena
    ) {
        return ResponseEntity.ok(repoClientes.findByNombreContainingIgnoreCase(cadena));
    }

    @PostMapping("clientes")
    public ResponseEntity<Cliente> crear(
            @RequestBody @Valid Cliente nuevoC
            //, Errors errores
            ) {
        try {
            this.repoClientes.save(nuevoC);
            return ResponseEntity
                    .created(new URI("clientes/" + nuevoC.getClienteId()))
                    .body(nuevoC);
        }
        catch (Exception ex) {
            return ResponseEntity.status(400)
                    .header("ERROR", ex.getMessage())
                    .build();
        }
    }

}
