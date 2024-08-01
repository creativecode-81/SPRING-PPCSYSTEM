package francode.com.Interfaces;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import francode.com.Models.TipoDocumento;

@Repository
public interface ITipoDoc extends CrudRepository<TipoDocumento, Integer>{

}
