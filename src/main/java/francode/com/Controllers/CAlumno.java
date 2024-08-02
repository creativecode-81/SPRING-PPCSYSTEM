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
import org.springframework.web.bind.annotation.RestController;

import francode.com.Models.Alumno;
import francode.com.Services.SAlumno;

@RestController
@RequestMapping("/alumno")
@CrossOrigin(origins = "http://localhost:4200",maxAge = 120)
public class CAlumno {
	
	@Autowired
	SAlumno service;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listAll() {
	    try {
	        List<Alumno> alum = service.findAll();
	        return ResponseEntity.ok(alum);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Alumno no encontrado");
	    }
	}
	
	
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
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody Alumno r) {
	    try {
	    	Alumno saveTipoDoc = service.save(r);
	        return ResponseEntity.ok(saveTipoDoc);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el alumno");
	    }
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody Alumno r) {
	    try {
	    	Alumno updateTipoDoc = service.save(r);
	        return ResponseEntity.ok(updateTipoDoc);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el alumno");
	    }
	}
	
	@GetMapping("/disable/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
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
