package edu.miu.cs.cs544.ea_ars.domain;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@NoArgsConstructor
@Getter
@Setter
@ToString
public class Ticket {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    private int number;

    private String reservationCode;

    private LocalDate flightDate;

   @ManyToOne
    private Flight flight;

    @JsonBackReference
    @ManyToOne(cascade = {CascadeType.ALL})
    @JoinColumn
    private Reservation reservation;

    public Ticket(int number, String reservationCode, LocalDate flightDate) {
        this.number = number;
        this.reservationCode = reservationCode;
        this.flightDate = flightDate;
    }

    public Ticket(int number, String reservationCode, LocalDate flightDate, Flight flight, Reservation reservation) {
        this.number = number;
        this.reservationCode = reservationCode;
        this.flightDate = flightDate;
        this.flight = flight;
        this.reservation = reservation;
    }
}
