package francode.com.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;

import francode.com.Interfaces.IAlumno;
import francode.com.Models.Alumno;

@Service
public class SAlumno {
	
	@Autowired
	IAlumno data;
	
	@Autowired
	JdbcTemplate template;

	// Listar alumnos
	public List<Alumno> findAll(){
		return (List<Alumno>)data.findAll();
	}
	
	// Buscar alumnos por su ID
	public Optional<Alumno>findById(int id){
		return data.findById(id);
	}
	
	// Buscar alumnos por sus nombres, apellido_paterno, apellido_materno, nro_doc o telefono
	public List<Alumno> search(String nombres, String apellido_paterno, String apellido_materno, String nro_doc, String telefono) {
	    return data.findBySearch(nombres, apellido_paterno, apellido_materno, nro_doc, telefono);
	}
	
	// Guardar y actualizar alumnos con validación
    public Alumno save(Alumno a) {
        // Verificar si el número de documento ya está registrado
        Optional<Alumno> existingStudentByDoc = data.findByNroDoc(a.getNro_doc());
        if (existingStudentByDoc.isPresent() && existingStudentByDoc.get().getId_alumno() != a.getId_alumno()) {
            throw new IllegalArgumentException("Este número de documento ya pertenece a un alumno");
        }

        // Verificar si el teléfono ya está registrado
        Optional<Alumno> existingStudentByPhone = data.findByPhone(a.getTelefono());
        if (existingStudentByPhone.isPresent() && existingStudentByPhone.get().getId_alumno() != a.getId_alumno()) {
            throw new IllegalArgumentException("Este número de teléfono ya pertenece a un alumno");
        }

        return data.save(a);
    }
	
	// Inhabilitar y habilitar alumnos
	public Optional<Alumno> deleteById(int id) {
	    Optional<Alumno> alum = findById(id);

	    if (alum.isPresent()) {
	        // El alumno existe, procedemos con la inhabilitación
	        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(template)
	                .withProcedureName("SP_habilitarAlumno");

	        SqlParameterSource inParams = new MapSqlParameterSource()
	                .addValue("p_id", id);

	        simpleJdbcCall.execute(inParams);

	        // Devolvemos el alumno inhabilitado como Optional
	        return alum;
	    } else {
	        // El alumno  no existe, devolvemos un Optional vacío
	        return Optional.empty();
	    }
	}

}
