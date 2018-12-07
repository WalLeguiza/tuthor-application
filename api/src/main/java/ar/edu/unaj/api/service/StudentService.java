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
public class StudentService implements GeneralService<Student, String>{

    @Autowired
    private StudentRepository studentRepository;

    public StudentService () {

        super ();
    }

    @Override
    public void create (Student student) throws StudentException {

        Boolean dniExist = this.studentRepository.existsByDni(student.getDni());
        Boolean emailExist = this.studentRepository.existsByEmail(student.getEmail());

        log.info("Checking student existing...");

        if (!dniExist || !emailExist) {
            this.studentRepository.save(student);
        } else {
            log.error(String.format("Existing object, %s", student));
            throw new StudentException(111);
        }
    }

    @Override
    public List<Student> findAll () {

        return this.studentRepository.findAll();
    }

    @Override
    public Student findById(String id) {

        return this.studentRepository.getOneById(id);
    }

    public Student findByDni(String dni) {

        return this.studentRepository.getOneByDni(dni);
    }

    @Override
    public void update (Student student) throws StudentException{

        Boolean dniExist = this.studentRepository.existsByDni(student.getDni());
        Boolean emailExist = this.studentRepository.existsByEmail(student.getEmail());

        log.info("Checking student existing...");

        if (dniExist && emailExist) {
            this.studentRepository.save(student);
        } else {
            log.error(String.format("No existing object, %s", student));
            throw new StudentException(222);
        }
    }

    @Override
    public void delete(String id) throws StudentException{

        Student student = this.studentRepository.getOneById(id);

        if (student != null) {
            this.studentRepository.delete(this.studentRepository.getOneById(id));
        } else {
            log.error(String.format("No existing object with id %s", id));
            throw new StudentException(222);
        }
    }

    @Override
    public void disable(String id) throws StudentException{

        Student student = this.studentRepository.getOneById(id);

        if (student != null) {

            student.setEnabled(false);
            this.studentRepository.save(student);

        } else {

            log.error(String.format("No existing object with id %s", id));
            throw new StudentException(222);

        }
    }
}
