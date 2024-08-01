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

import francode.com.Models.TipoDocumento;
import francode.com.Services.STipoDoc;

@RestController
@RequestMapping("/tipodocumento")
@CrossOrigin(origins = "http://localhost:4200",maxAge = 120)
public class CTipoDoc {
	
	@Autowired
	STipoDoc service;
	
	@GetMapping("/listar")
	public ResponseEntity<?> listAll() {
	    try {
	        List<TipoDocumento> tipodoc = service.findAll();
	        return ResponseEntity.ok(tipodoc);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Tipo de Documento no encontrado");
	    }
	}
	
	
	@GetMapping("/find/{id}")
	public ResponseEntity<?> findById(@PathVariable int id) {
	    try {
	        Optional<TipoDocumento> tipodoc = service.findById(id);
	        if (tipodoc.isPresent()) {
	            return ResponseEntity.ok(tipodoc);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Documento no encontrado"); // Devuelve 404 con mensaje
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al buscar el tipo de documento");
	    }
	}
	
	@PostMapping("/save")
	public ResponseEntity<?> save(@RequestBody TipoDocumento r) {
	    try {
	    	TipoDocumento saveTipoDoc = service.save(r);
	        return ResponseEntity.ok(saveTipoDoc);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al guardar el tipo de documento");
	    }
	}
	
	@PutMapping("/update")
	public ResponseEntity<?> update(@RequestBody TipoDocumento r) {
	    try {
	    	TipoDocumento updateTipoDoc = service.save(r);
	        return ResponseEntity.ok(updateTipoDoc);
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al actualizar el tipo de documento");
	    }
	}
	
	@GetMapping("/disable/{id}")
	public ResponseEntity<?> deleteById(@PathVariable int id) {
	    try {
	        Optional<TipoDocumento> tipodoc = service.deleteById(id);
	        if (tipodoc.isPresent()) {
	            return ResponseEntity.ok(tipodoc);
	        } else {
	            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Tipo de Documento no encontrado"); // Devuelve 404 con mensaje
	        }
	    } catch (Exception e) {
	        return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Error al inhabilitar el tipo de documento");
	    }
	}


}
