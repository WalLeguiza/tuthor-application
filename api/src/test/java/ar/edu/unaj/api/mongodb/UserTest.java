package ar.edu.unaj.api.mongodb;

import ar.edu.unaj.api.beans.domain.Address;
import ar.edu.unaj.api.beans.domain.Subject;
import ar.edu.unaj.api.beans.entity.User;
import ar.edu.unaj.api.exception.UserException;
import ar.edu.unaj.api.service.UserService;
import lombok.extern.log4j.Log4j;
import org.junit.After;
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
public class UserTest {

    @Autowired
    private UserService userService;

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

        log.info("Loading data for the object User...");
        this.nameTest = "ARTURO";
        this.lastNameTest = "JAURETCHE";
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
        log.debug("Loaded data for the object user...");

        log.info("Creating user..");
        User user = new User(nameTest, lastNameTest, emailTest, dniTest, telephoneTest,
                cellphoneTest, addressTest, academicLevelTest, subjectsDictationTest, enabledTest);
        log.debug(String.format("Object created %s", user));

        try {

            log.debug("Saving user...");
            this.userService.create(user);
            log.debug(String.format("Saved %s", user));

        } catch (UserException e) {
            System.out.println(e.getMessage());
        }

    }

    @Test
    public void getAllTest () {

        log.info("Getting student...");
        List<User> studentsList = this.userService.findAll();

        studentsList.forEach(System.out::println);

        assertNotNull("There aren't students. The students list is null", studentsList);
        assertFalse("There aren't students. The student list is empty", studentsList.isEmpty());

    }

    @Test
    public void updateTest () {

        User user = null;

        try {

            user = this.userService.findByDni (this.dniTest);

            user.setName("Arturo");
            user.setLastName("Jauretche");

            this.userService.update(user);

        } catch (UserException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(user);

        assertTrue("Update failed", user.getName() == "Arturo"
                    && user.getLastName() == "Jauretche");
    }

    @Test
    public void disableTest () {
        User user = null;

        try {

            user = this.userService.findByDni(dniTest);

            this.userService.disable(user);

        } catch (UserException e) {
            System.out.println(e.getMessage());
        }

        System.out.println(user);

        assertFalse("Disable test failed", user.getEnabled());
    }

    @After
    public void end () {
        try {
            User user = this.userService.findByDni(this.dniTest);
            this.userService.delete(user.getId());
        } catch (UserException e) {
            System.out.println(e.getMessage());
        }
    }
}
