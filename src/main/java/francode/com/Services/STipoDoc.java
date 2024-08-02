package francode.com.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import francode.com.Interfaces.ITipoDoc;
import francode.com.Models.TipoDocumento;



@Service
public class STipoDoc {
	@Autowired
	ITipoDoc data;
	
	@Autowired
	JdbcTemplate template;
	
	// Listar tipo de documentos
	public List<TipoDocumento> findAll(){
		return (List<TipoDocumento>)data.findAll();
	}
	
	// Buscar tipo de documentos por su ID
	public Optional<TipoDocumento>findById(int id){
		return data.findById(id);
	}
	
	// Buscar tipo de documentos por su nombre o nombre_corto
	public List<TipoDocumento> search(String nombre, String nombre_corto) {
        return data.findBySearchTypeDoc(nombre, nombre_corto);
    }
	
	// Guardar y actualizar tipo de documentos
	public TipoDocumento save(TipoDocumento t) {
		return data.save(t);
	}
	
	// Inhabilitar y habilitar tipo de documentos
	public Optional<TipoDocumento> deleteById(int id) {
	    Optional<TipoDocumento> tipoDoc = findById(id);

	    if (tipoDoc.isPresent()) {
	        // El tipo de documento existe, procedemos con la inhabilitación
	        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(template)
	                .withProcedureName("SP_habilitarTipoDoc");

	        SqlParameterSource inParams = new MapSqlParameterSource()
	                .addValue("p_id", id);

	        simpleJdbcCall.execute(inParams);

	        // Devolvemos el tipo de documento inhabilitado como Optional
	        return tipoDoc;
	    } else {
	        // El tipo de documento  no existe, devolvemos un Optional vacío
	        return Optional.empty();
	    }
	}

}
