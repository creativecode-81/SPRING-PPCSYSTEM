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

import francode.com.Models.PartidoPolitico;
import francode.com.Services.SPartidoPolitico;

@RestController
@RequestMapping("/partidopolitico")
@CrossOrigin(origins = "http://localhost:4200", maxAge = 120)
public class CPartidoPolitico {
	@Autowired
	SPartidoPolitico service;

	// Listar partidos políticos
	@GetMapping("/listar")
	public ResponseEntity<?> listAll() {
		try {
			List<PartidoPolitico> part = service.findAll();
			return ResponseEntity.ok(part);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Partido Político no encontrado");
		}
	}

	// Buscar partidos políticos por su ID
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
		try {
			Optional<PartidoPolitico> part = service.findById(id);
			if (part.isPresent()) {
				return ResponseEntity.ok(part);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partido Político no encontrado"); // Devuelve 404 con mensaje
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar el partido político");
		}
	}

	// Buscar partidos políticos por su nombre
	@GetMapping("/search")
	public ResponseEntity<?> searchPoliticalParties(@RequestParam(required = false) String nombre) {
		try {
			List<PartidoPolitico> part = service.search(nombre);
			if (!part.isEmpty()) {
				return ResponseEntity.ok(part);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("No se encontraron partidos políticos");
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar partidos políticos");
		}
	}

	// Guardar partidos políticos
	@PostMapping("/save")
	public ResponseEntity<?> savePoliticalParties(@RequestBody PartidoPolitico r) {
		try {
			PartidoPolitico savePartPol = service.save(r);
			return ResponseEntity.ok(savePartPol);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el partido político");
		}
	}

	// Actualizar partidos políticos
	@PutMapping("/update")
	public ResponseEntity<?> updatePoliticalParties(@RequestBody PartidoPolitico r) {
		try {
			PartidoPolitico updatePartPol = service.save(r);
			return ResponseEntity.ok(updatePartPol);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al actualizar el partido político");
		}
	}

	// Inhabilitar y habilitar partidos políticos
	@GetMapping("/disable/{id}")
	public ResponseEntity<?> disablePoliticalParties(@PathVariable int id) {
		try {
			Optional<PartidoPolitico> part = service.deleteById(id);
			if (part.isPresent()) {
				return ResponseEntity.ok(part);
			} else {
				return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Partido Político no encontrado"); // Devuelve 404 con mensaje
			}
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
					.body("Error al inhabilitar el partido político");
		}
	}

}
