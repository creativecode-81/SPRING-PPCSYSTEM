package francode.com.Models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "partidopolitico")
@Data @AllArgsConstructor @NoArgsConstructor
public class PartidoPolitico {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name="id_partido")
	private int id_partido;
	
	@Column(name="nombre")
	private String nombre;
	
	@Column(name="imagen")
	private String imagen;
	
	@OneToOne
	@JoinColumn(name = "id_alumno",nullable = false)
	private Alumno alumno;
	
	@Column(name = "estado")
	private String estado;
}
