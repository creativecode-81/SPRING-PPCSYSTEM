package francode.com.Controllers;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import francode.com.Models.Alumno;
import francode.com.Services.SAlumno;

@RestController
@RequestMapping("/alumno")
@CrossOrigin(origins = "http://localhost:4200",maxAge = 120)
public class CAlumno {
	
	@Autowired
	SAlumno service;
	
	// Listar alumnos
	@GetMapping("/listar")
	public ResponseEntity<?> listAll() {
	    try {
	        List<Alumno> alum = service.findAll();
	        return ResponseEntity.ok(alum);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Alumno no encontrado");
	    }
	}
	
	// Buscar alumnos por su ID
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
	    try {
	        Optional<Alumno> alum = service.findById(id);
	        if (alum.isPresent()) {
	            return ResponseEntity.ok(alum);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado"); // Devuelve 404 con mensaje
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar el alumno");
	    }
	}
	
	// Buscar alumnos por sus nombres, apellido_paterno, apellido_materno, nro_doc o telefono
	@GetMapping("/search")
	public ResponseEntity<?> searchStudents(
			@RequestParam(required = false) String nombres,
            @RequestParam(required = false) String apellido_paterno,
            @RequestParam(required = false) String apellido_materno,
            @RequestParam(required = false) String nro_doc,
            @RequestParam(required = false) String telefono) {
		try {
            List<Alumno> alumnos = service.search(nombres, apellido_paterno, apellido_materno, nro_doc, telefono);
            if (!alumnos.isEmpty()) {
                return ResponseEntity.ok(alumnos);
            } else {
                return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron alumnos");
            }
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar alumnos");
        }
	}
	
	// Guardar alumnos
	@PostMapping("/save")
	public ResponseEntity<?> saveStudents(@RequestBody Alumno r) {
	    try {
	    	Alumno saveAlum = service.save(r);
	        return ResponseEntity.ok(saveAlum);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el alumno");
	    }
	}
	
	// Actualizar alumnos
	@PutMapping("/update")
	public ResponseEntity<?> updateStudents(@RequestBody Alumno r) {
	    try {
	    	Alumno updateAlum = service.save(r);
	        return ResponseEntity.ok(updateAlum);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el alumno");
	    }
	}
	
	// Inhabilitar y habilitar alumnos
	@GetMapping("/disable/{id}")
	public ResponseEntity<?> disableStudents(@PathVariable int id) {
	    try {
	        Optional<Alumno> alum = service.deleteById(id);
	        if (alum.isPresent()) {
	            return ResponseEntity.ok(alum);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Alumno no encontrado"); // Devuelve 404 con mensaje
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al inhabilitar el alumno");
	    }
	}

}
