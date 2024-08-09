package francode.com.Models;

import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Table;

import jakarta.persistence.Column;
import jakarta.persistence.JoinColumn;

@Entity
@Table(name = "alumno")
@Data @AllArgsConstructor @NoArgsConstructor
public class Alumno {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_alumno")
	private int id_alumno;
	
	@Column(name="nombres")
	private String nombres;
	
	@Column(name="apellido_paterno")
	private String apellido_paterno;
	
	@Column(name="apellido_materno")
	private String apellido_materno;
	
	@ManyToOne
	@JoinColumn(name = "id_tipodoc",nullable = false)
	private TipoDocumento typeDoc;

	@Column(name = "nro_doc")
	private String nro_doc;
	
	@Column(name = "telefono")
	private String telefono;
	
	@Column(name = "estado")
	private String estado;
}
