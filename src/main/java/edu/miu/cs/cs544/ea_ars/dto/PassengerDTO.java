package edu.miu.cs.cs544.ea_ars.dto;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import edu.miu.cs.cs544.ea_ars.domain.Address;
import edu.miu.cs.cs544.ea_ars.domain.Reservation;
import lombok.*;


import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Past;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;


@Data
@NoArgsConstructor
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

    private List<Reservation> reservations = new ArrayList<>();

    public void addReservation(Reservation reservation) {
        reservation.setPassenger(PassengerAdopter.getPassenger(this));
        this.reservations.add(reservation);
    }


}


