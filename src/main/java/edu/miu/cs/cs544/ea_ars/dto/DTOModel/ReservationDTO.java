package edu.miu.cs.cs544.ea_ars.dto.DTOModel;

import edu.miu.cs.cs544.ea_ars.domain.Passenger;
import edu.miu.cs.cs544.ea_ars.domain.Ticket;
import edu.miu.cs.cs544.ea_ars.domain.User;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Data
@NoArgsConstructor
public class ReservationDTO {

    private long id;

    @NotEmpty
    @Size(max = 6)
    private String reservationCode;

    private LocalDate reservationDate;

    private boolean isPaid;

    private User reservedBy;

    private Set<Ticket> tickets = new HashSet<>();

    private Passenger passenger;


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
