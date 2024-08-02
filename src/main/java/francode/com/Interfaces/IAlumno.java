package francode.com.Interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import francode.com.Models.Alumno;

@Repository
public interface IAlumno extends CrudRepository<Alumno, Integer>{

}
