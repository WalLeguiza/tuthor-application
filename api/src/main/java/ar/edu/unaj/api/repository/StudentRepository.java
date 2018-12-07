package ar.edu.unaj.api.repository;

import ar.edu.unaj.api.beans.entity.Student;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface StudentRepository extends MongoRepository<Student, String> {

    Boolean existsByDni (String dni);
    Student findStudentByDni (String dni);

}
