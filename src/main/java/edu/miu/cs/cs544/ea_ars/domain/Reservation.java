package edu.miu.cs.cs544.ea_ars.domain;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;


@Entity
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(length=6, nullable=false)
    private String reservationCode;

//    @NotNull
    private LocalDate reservationDate;

//    @NotNull
    private boolean isPaid;

    @ManyToOne
    private User reservedBy;

    @OneToMany
    private Set<Ticket> tickets = new HashSet<>();

    @ManyToOne
    private Passenger passenger;

    public Reservation(String reservationCode, LocalDate reservationDate) {
        this.reservationCode = reservationCode;
        this.reservationDate = reservationDate;
    }

    public Reservation(String reservationCode, LocalDate reservationDate, User reservedBy) {
        this.reservationCode = reservationCode;
        this.reservationDate = reservationDate;
        this.reservedBy = reservedBy;
    }

//    Collections convenience method
    public boolean addTicket(Ticket ticket){
        boolean success=false;
        if(tickets.add(ticket)){
            ticket.setReservation(this);
            success=true;
        }
        return success;
    }
}
