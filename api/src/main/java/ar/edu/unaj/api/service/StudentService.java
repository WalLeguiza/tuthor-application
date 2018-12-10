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
            throw new StudentException(100111);
        }
    }

    @Override
    public List<Student> findAll () {

        return this.studentRepository.findAll();
    }

    @Override
    public Student findById(String id) throws StudentException{

        Boolean exist = this.studentRepository.existsById(id);

        log.info("Checking student existing...");

        if (exist) {
            log.debug("Returning student..");
            return this.studentRepository.getOneById(id);
        } else {
            log.error(String.format("No existing student object with id: %s", id));
            throw new StudentException(100222);
        }
    }

    public Student findByDni(String dni) throws StudentException{

        Boolean exist = this.studentRepository.existsByDni(dni);

        log.info("Checking student existing...");

        if (exist) {
            log.debug("Returning student..");
            return this.studentRepository.getOneByDni(dni);
        } else {
            log.error(String.format("No existing student object with DNI: %s", dni));
            throw new StudentException(100222);
        }
    }

    @Override
    public void update (Student student) throws StudentException{

        Boolean dniExist = this.studentRepository.existsByDni(student.getDni());
        Boolean emailExist = this.studentRepository.existsByEmail(student.getEmail());

        log.info("Checking student existing...");

        if (dniExist && emailExist) {
            log.debug("Updating student..");
            this.studentRepository.save(student);
        } else {
            log.error(String.format("No existing object, %s", student));
            throw new StudentException(100222);
        }
    }

    @Override
    public void delete(String id) throws StudentException{

        Student student = this.studentRepository.getOneById(id);

        if (student != null) {
            log.debug("Deleting student..");
            this.studentRepository.delete(this.studentRepository.getOneById(id));
        } else {
            log.error(String.format("No existing object with id %s", id));
            throw new StudentException(100222);
        }
    }

    public void disable (Student student) throws StudentException{

        Boolean exist = this.studentRepository.existsById(student.getId());

        if (student != null && exist) {

            if (student.getEnabled()) {

                student.setEnabled(false);
                log.debug("Updating disabled student..");
                this.studentRepository.save(student);

            } else {
                log.info("The student is already disable");
            }

        } else {

            log.error(String.format("No existing object %s", student));
            throw new StudentException(100222);

        }
    }
}
