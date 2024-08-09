package francode.com.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import francode.com.Interfaces.IPartidoPolitico;
import francode.com.Models.PartidoPolitico;



@Service
public class SPartidoPolitico {
	@Autowired
	IPartidoPolitico data;
	
	@Autowired
	JdbcTemplate template;
	
	// Listar partidos políticos
	public List<PartidoPolitico> findAll(){
		return (List<PartidoPolitico>)data.findAll();
	}
	
	// Buscar partidos políticos por su ID
	public Optional<PartidoPolitico>findById(int id){
		return data.findById(id);
	}
	
	// Buscar partidos políticos por su nombre
	public List<PartidoPolitico> search(String nombre) {
		return data.findBySearch(nombre);
	}
	
	// Guardar o actualizar partidos políticos
		public PartidoPolitico save(PartidoPolitico p) {
			// Verificar si el alumno ya está asociado a otro partido político
			Optional<PartidoPolitico> existingParty = data.findByAlumnoId(p.getAlumno().getId_alumno());

			if (existingParty.isPresent() && existingParty.get().getId_partido() != p.getId_partido()) {
				throw new IllegalArgumentException("Este alumno ya pertenece a un partido político");
			}
			
			// Si no está asociado, se procede con el guardado
			return data.save(p);
		}
	
	// Inhabilitar y habilitar partidos políticos
	public Optional<PartidoPolitico> deleteById(int id) {
	    Optional<PartidoPolitico> part = findById(id);

	    if (part.isPresent()) {
	        // El partido político existe, procedemos con la inhabilitación
	        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(template)
	                .withProcedureName("SP_habilitarPartPolitico");

	        SqlParameterSource inParams = new MapSqlParameterSource()
	                .addValue("p_id", id);

	        simpleJdbcCall.execute(inParams);

	        // Devolvemos el partido político inhabilitado como Optional
	        return part;
	    } else {
	        // El partido político no existe, devolvemos un Optional vacío
	        return Optional.empty();
	    }
	}

}
