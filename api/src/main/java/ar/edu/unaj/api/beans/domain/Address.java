package ar.edu.unaj.api.beans.domain;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class Address {

    private String street;
    private long number;
    private String city;
    private String state;
    private String country;
    private String zipCode;
}
