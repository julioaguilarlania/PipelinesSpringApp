package mx.lania.taller.repositorios;

import mx.lania.taller.entidades.Vehiculo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface RepositorioVehiculos extends JpaRepository<Vehiculo,String> {

    @Query(value="SELECT * FROM vehiculos" +
            " WHERE kilometraje >= :k1 AND kilometraje <= :k2", nativeQuery = true)
    List<Vehiculo> buscarPorKilometraje(
            @Param("k1") Double k1, @Param("k2") Double k2
    );


    @Query(value="SELECT placas,cliente_id,color,kilometraje FROM vehiculos" +
            " WHERE kilometraje >= :k1 AND kilometraje <= :k2", nativeQuery = true)
    List<Object[]> buscarPorKilometrajeDos(
            @Param("k1") Double k1, @Param("k2") Double k2
    );
}
