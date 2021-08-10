package edu.miu.cs.cs544.ea_ars.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.miu.cs.cs544.ea_ars.domain.Address;
import edu.miu.cs.cs544.ea_ars.domain.Reservation;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class PassengerDTO {

    private Long Id;

    @NotEmpty
    private String firstName;

    @NotEmpty
    private String lastName;

    @Past
    private LocalDate dateOfBirth;

    private String email;

    private Address address;

//    @JsonManagedReference
    private List<ReservationDTO> reservations = new ArrayList<>();

    public PassengerDTO(String firstName, String lastName, LocalDate dateOfBirth) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
    }

    public PassengerDTO(String firstName, String lastName,String email, LocalDate dateOfBirth, Address address, List<ReservationDTO> reservations) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.dateOfBirth = dateOfBirth;
        this.reservations = reservations;
        this.address = address;
        this.email = email;
    }

//    Convenience method
        public void addReservation(ReservationDTO reservation){
            reservation.setPassenger(PassengerAdopter.getPassenger(this));
            this.reservations.add(reservation);
        }


}


