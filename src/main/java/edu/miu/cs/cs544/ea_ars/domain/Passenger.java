package edu.miu.cs.cs544.ea_ars.domain;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long Id;

    @NotEmpty
    @Column(length = 50, nullable = false)
    private String firstName;

    @NotEmpty
    @Column(length = 50, nullable = false)
    private String lastName;

    @Past
    private LocalDate dateOfBirth;

    private String email;

    @Embedded
    private Address address;

    //@ToString.Exclude
    @JsonManagedReference
    @OneToMany(mappedBy = "passenger", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Reservation> reservations = new ArrayList<>();

    public Passenger(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public Passenger(String firstName, String lastName, String email, LocalDate dateOfBirth, Address address, List<Reservation> reservations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.reservations = reservations;
        this.address = address;
        this.email = email;
    }

    //Convenience method
    public void addReservation(Reservation reservation) {
        reservation.setPassenger(this);
        this.reservations.add(reservation);
    }


}
