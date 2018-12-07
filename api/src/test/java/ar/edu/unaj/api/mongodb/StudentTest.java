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

import static org.junit.Assert.*;

@RunWith(SpringRunner.class)
@SpringBootTest
@Log4j
public class StudentTest {

    @Autowired
    private StudentService studentService;

    private String nameTest;
    private String lastNameTest;
    private String dniTest;
    private String emailTest;
    private String telephoneTest;
    private String cellphoneTest;
    private Address addressTest;
    private String academicLevelTest;
    private List<Subject> subjectsDictationTest;
    private Boolean enabledTest;

    @Before
    public void setup () {

        log.info("Loading data for the object Student...");
        this.nameTest = "Arturo";
        this.lastNameTest = "Jauretche";
        this.dniTest = "13780036";
        this.emailTest = "arturojauretche@unaj.edu.ar";
        this.telephoneTest = "43556473";
        this.cellphoneTest = "1132468772";
        this.addressTest = new Address("Av. Calchaqui", 2798, "Florencio Varela",
                                       "Buenos Aires", "Argentina", "1888");
        this.academicLevelTest = "Universitario";

        Subject [] subjects = new Subject[] {
                new Subject("Literatura", "Literatura Comtemporanea", "Secundario"),
                new Subject("Derecho", "Derecho", "Universitario")
        };

        this.subjectsDictationTest = Arrays.asList(subjects);
        this.enabledTest = Boolean.TRUE;
        log.debug("Loaded data for the object student...");

        log.info("Creating student..");
        Student student = new Student(nameTest, lastNameTest, emailTest, dniTest, telephoneTest,
                cellphoneTest, addressTest, academicLevelTest, subjectsDictationTest, enabledTest);
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
        List<Student> studentsList = this.studentService.findAll();

        studentsList.forEach(System.out::println);

        assertNotNull("There aren't students. The students list is null", studentsList);
        assertFalse("There aren't students. The student list is empty", studentsList.isEmpty());

    }

    @Test
    public void update() {

        Student student = this.studentService.findByDni (this.dniTest);
        student.setName("Arturo Martin");
        student.setLastName("Jauretche");
        student.setDni("13780035");

        try {
            this.studentService.update(student);
        } catch (StudentException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(student);

        assertTrue("Update failed",student.getDni() == "13780035"
                    && student.getName() == "Arturo Martin"
                    && student.getLastName() == "Jauretche");
    }
}
