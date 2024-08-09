package francode.com.Interfaces;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import francode.com.Models.TipoDocumento;

@Repository
public interface ITipoDoc extends CrudRepository<TipoDocumento, Integer>{
	
	// Buscar tipo de documentos por su nombre
	@Query("SELECT td FROM TipoDocumento td WHERE td.nombre = :nombre")
	Optional<TipoDocumento> findByName(@Param("nombre") String nombre);
	
	// Buscar tipo de documentos por su nombre corto
	//@Query("SELECT td FROM TipoDocumento td WHERE td.nombre_corto = :nombre_corto")
	@Query("SELECT td FROM TipoDocumento td WHERE UPPER(td.nombre_corto) = UPPER(:nombre_corto)")
	Optional<TipoDocumento> findByShortName(@Param("nombre_corto") String nombre_corto);
	
	// Buscar tipo de documentos por su nombre o nombre corto
	@Query("SELECT td FROM TipoDocumento td " +
	           "WHERE (:nombre IS NULL OR td.nombre = :nombre) " +
	           "AND (:nombre_corto IS NULL OR td.nombre_corto = :nombre_corto)")
	List<TipoDocumento> findBySearchTypeDoc(@Param("nombre") String nombre, @Param("nombre_corto") String nombre_corto);

}
