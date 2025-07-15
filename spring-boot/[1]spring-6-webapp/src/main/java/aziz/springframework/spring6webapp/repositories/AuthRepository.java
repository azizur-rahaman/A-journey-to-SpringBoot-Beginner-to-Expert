package aziz.springframework.spring6webapp.repositories;

import aziz.springframework.spring6webapp.domain.Author;
import org.springframework.data.repository.CrudRepository;

public interface AuthRepository extends CrudRepository<Author, Long> {

}
