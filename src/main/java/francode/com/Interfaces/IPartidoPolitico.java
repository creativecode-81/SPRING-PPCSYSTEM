package francode.com.Interfaces;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import francode.com.Models.PartidoPolitico;

@Repository
public interface IPartidoPolitico extends CrudRepository<PartidoPolitico, Integer>{
	
	// Buscar partidos políticos por su nombre
		@Query("SELECT p FROM PartidoPolitico p " +
		           "WHERE (:nombre IS NULL OR p.nombre = :nombre)")
		List<PartidoPolitico> findBySearch(
				@Param("nombre") String nombre);


}
