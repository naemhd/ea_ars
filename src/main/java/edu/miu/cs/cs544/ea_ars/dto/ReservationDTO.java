package edu.miu.cs.cs544.ea_ars.dto;


import com.fasterxml.jackson.annotation.JsonBackReference;
import edu.miu.cs.cs544.ea_ars.domain.Passenger;
import edu.miu.cs.cs544.ea_ars.domain.Ticket;
import edu.miu.cs.cs544.ea_ars.domain.User;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
@ToString
public class ReservationDTO {

        private long id;

        @NotEmpty
        @Size(max=6)
        private String reservationCode;

        private LocalDate reservationDate;

        private boolean isPaid;


        private User reservedBy;

        private Set<Ticket> tickets = new HashSet<>();

//        @JsonBackReference
        private Passenger passenger;

        public ReservationDTO(String reservationCode, LocalDate reservationDate) {
            this.reservationCode = reservationCode;
            this.reservationDate = reservationDate;
        }

        public ReservationDTO(String reservationCode, LocalDate reservationDate, User reservedBy) {
            this.reservationCode = reservationCode;
            this.reservationDate = reservationDate;
            this.reservedBy = reservedBy;
        }

        //    Collections convenience method
//        public boolean addTicket(Ticket ticket){
//            boolean success=false;
//            if(tickets.add(ticket)){
//                ticket.setReservation(this);
//                success=true;
//            }
//            return success;
//        }
    }


