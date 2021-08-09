package edu.miu.cs.cs544.ea_ars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Passenger {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long Id;

    @Column(length = 50,nullable = false)
    private String firstName;

    @Column(length = 50,nullable = false)
    private String lastName;

//    @NotNull
    private LocalDate dateOfBirth;

    private String email;

    @Embedded
    private Address address;

    @OneToMany(mappedBy = "passenger",cascade = CascadeType.ALL)
    private List<Reservation> reservations =new ArrayList<>();

    public Passenger(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }
    public Passenger(String firstName, String lastName, LocalDate dateOfBirth, Address address, List<Reservation> reservations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.reservations =reservations;
        this.address = address;
    }

    //Convenience method
    public void addReservation(Reservation reservation){
        reservation.setPassenger(this);
        this.reservations.add(reservation);
    }


}
