package ar.edu.unaj.api.beans.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Subject {

    private String name;
    private String description;
    private String level;
}
