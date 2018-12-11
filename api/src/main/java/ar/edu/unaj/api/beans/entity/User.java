package ar.edu.unaj.api.beans.entity;

import ar.edu.unaj.api.beans.domain.Address;
import ar.edu.unaj.api.beans.domain.Subject;
import lombok.*;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;

@Getter @Setter
@EqualsAndHashCode
@NoArgsConstructor
@ToString (exclude = {"id"})
@Document (collection = "students")
public class User {

    @Id
    private String id;
    private String name;
    private String lastName;
    private String email;
    private String dni;
    private String telephone;
    private String cellphone;
    private Address address;
    private String academicLevel;
    private List<Subject> dictationSubjects;
    private Boolean enabled;

    /**
     * Constructor with all arguments less id.
     *
     * @param name
     * @param lastName
     * @param email
     * @param dni
     * @param telephone
     * @param cellphone
     * @param address
     * @param academicLevel
     * @param dictationSubjects
     * @param enabled
     */
    public User(String name, String lastName, String email, String dni, String telephone, String cellphone,
                Address address, String academicLevel, List<Subject> dictationSubjects, Boolean enabled) {
        this.name = name;
        this.lastName = lastName;
        this.email = email;
        this.dni = dni;
        this.telephone = telephone;
        this.cellphone = cellphone;
        this.address = address;
        this.academicLevel = academicLevel;
        this.dictationSubjects = dictationSubjects;
        this.enabled = enabled;
    }

}
