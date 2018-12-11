package ar.edu.unaj.api.repository;

import ar.edu.unaj.api.beans.entity.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends MongoRepository<User, String> {

    Boolean existsByDni (String dni);
    Boolean existsByEmail (String email);
    User getOneById (String id);
    User getOneByDni (String dni);

}
