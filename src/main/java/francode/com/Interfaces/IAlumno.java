package francode.com.Interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import francode.com.Models.Alumno;

@Repository
public interface IAlumno extends CrudRepository<Alumno, Integer>{
	
	// Buscar alumnos por su número de documento
	@Query("SELECT a FROM Alumno a WHERE a.nro_doc = :nro_doc")
    Optional<Alumno> findByNroDoc(@Param("nro_doc") String nro_doc);

    // Buscar alumnos por su teléfono
	@Query("SELECT a FROM Alumno a WHERE a.telefono = :telefono")
    Optional<Alumno> findByPhone(@Param("telefono") String telefono);

	
	// Buscar alumnos por sus nombres, apellido_paterno, apellido_materno, nro_doc o telefono
	@Query("SELECT a FROM Alumno a " +
	           "WHERE (:nombres IS NULL OR a.nombres = :nombres) " +
	           "AND (:apellido_paterno IS NULL OR a.apellido_paterno = :apellido_paterno) " +
	           "AND (:apellido_materno IS NULL OR a.apellido_materno = :apellido_materno) " +
	           "AND (:nro_doc IS NULL OR a.nro_doc = :nro_doc) " +
	           "AND (:telefono IS NULL OR a.telefono = :telefono)")
	List<Alumno> findBySearch(
			@Param("nombres") String nombres,
			@Param("apellido_paterno") String apellido_paterno,
			@Param("apellido_materno") String apellido_materno,
			@Param("nro_doc") String nro_doc,
			@Param("telefono") String telefono);

}
