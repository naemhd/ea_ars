package edu.miu.cs.cs544.ea_ars.domain;

import com.fasterxml.jackson.annotation.*;
import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@NoArgsConstructor
//@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @NotEmpty
    @Size(max = 6)
    @Column(length = 6, nullable = false)
    private String reservationCode;

    private LocalDate reservationDate;

    private boolean isPaid;


    @ManyToOne
    private User reservedBy;


    @JsonManagedReference
    @OneToMany(fetch = FetchType.EAGER,mappedBy = "reservation", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private Set<Ticket> tickets = new HashSet<>();

    @JsonBackReference
    @ManyToOne(fetch = FetchType.LAZY, cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    @JoinColumn
    private Passenger passenger;

    public Reservation(String reservationCode, LocalDate reservationDate) {
        this.reservationCode = reservationCode;
        this.reservationDate = reservationDate;
    }

    public Reservation(String reservationCode, LocalDate reservationDate, User reservedBy,Passenger passenger) {
        this.reservationCode = reservationCode;
        this.reservationDate = reservationDate;
        this.reservedBy = reservedBy;
        this.passenger = passenger;
    }

    //    Collections convenience method
    public boolean addTicket(Ticket ticket) {
        boolean success = false;
        if (tickets.add(ticket)) {
            ticket.setReservation(this);
            success = true;
        }
        return success;
    }
}
