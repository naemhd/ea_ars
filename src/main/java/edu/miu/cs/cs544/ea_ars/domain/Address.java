package edu.miu.cs.cs544.ea_ars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Address {
    @Column(length=50, nullable = false)
    private String street;

    @Column(length=50, nullable = false)
    private String city;

    @Column(length=50, nullable = false)
    private String state;

    @Column(length=10, nullable = false)
    private String zipCode;

    public Address(String street, String city, String state, String zip) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.zipCode = zip;
    }
}
