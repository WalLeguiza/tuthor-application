package ar.edu.unaj.api.service;

import ar.edu.unaj.api.beans.entity.Student;
import ar.edu.unaj.api.exception.StudentException;
import ar.edu.unaj.api.repository.StudentRepository;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Log4j
public class StudentService {

    @Autowired
    private StudentRepository studentRepository;

    public StudentService () {

        super ();
    }

    public void create (Student student) throws StudentException {

        Boolean studentExist = this.studentRepository.existsByDni(student.getDni());

        log.info("Checking student existing...");

        if (!studentExist) {
            this.studentRepository.save(student);
        } else {
            log.error(String.format("Existing object, %s", student));
            throw new StudentException(111);
        }
    }

    public List<Student> getAllSrudent () {

        return this.studentRepository.findAll();
    }
}
