package ar.edu.unaj.api.mongodb;

import ar.edu.unaj.api.beans.domain.Address;
import ar.edu.unaj.api.beans.domain.Subject;
import ar.edu.unaj.api.beans.entity.Student;
import ar.edu.unaj.api.exception.StudentException;
import ar.edu.unaj.api.service.StudentService;
import lombok.extern.log4j.Log4j;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j
public class StudentTest {

    @Autowired
    private StudentService studentService;

    @Before
    public void setup () {

        log.info("Creating address..");
        Address address = new Address("Lisboa", 714, "Florencio Varela",
                "Buenos Aires", "Argentina", "1888");
        log.debug(String.format("Object created %s", address));

        log.info("Creating subjects..");
        Subject [] subjects = new Subject[] {
                new Subject("Lengua", "Lengua", "Secundario"),
                new Subject("Matematicas", "Matematicas", "Secundario")
        };
        log.debug(String.format("Object created %s", Arrays.toString(subjects)));

        log.info("Creating student..");
        Student student = new Student("Leandro", "Leguiza", "leanleguiza@gmail.com",
                "40731455", "43675432",
                "1187873402", address, "Primario",
                Arrays.asList(subjects), true);
        log.debug(String.format("Object created %s", student));

        try {

            log.debug("Saving student...");
            this.studentService.create(student);
            log.debug(String.format("Saved %s", student));

        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }
    }

    @Test
    public void getAll() {

        log.info("Getting student...");
        List<Student> studentsList = this.studentService.getAllSrudent();

        studentsList.forEach(System.out::println);

        assertNotNull("There aren't students, students list is null", studentsList);
        assertFalse("There aren't students, student list is empty", studentsList.isEmpty());
    }
}
