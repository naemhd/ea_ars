package edu.miu.cs.cs544.ea_ars.dto.adapter;

import edu.miu.cs.cs544.ea_ars.domain.Airline;
import edu.miu.cs.cs544.ea_ars.dto.DTOModel.AirlineDTO;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;

@Component
public class AirlineAdaptar {

    public Airline getAirline(AirlineDTO airlineDTO) {
        Airline airline=new Airline();
        if (airlineDTO != null) {
            airline = new Airline(airlineDTO.getCode(), airlineDTO.getName(),airlineDTO.getHistory());
        }
        return airline;
    }

    public AirlineDTO getAirlineDTO(Airline airline) {
        AirlineDTO airlineDTO = new AirlineDTO();
        if (airline != null) {
            airlineDTO = new AirlineDTO(airline.getCode(),airline.getName(),airline.getHistory());
        }
        return airlineDTO;
    }

    public List<AirlineDTO> getAirlineDTOList(List<Airline> airlineList) {
        List<AirlineDTO> airlineDTOList = new ArrayList<>();
        if (airlineList.size() != 0) {
            for (Airline airline : airlineList) {
                airlineDTOList.add(this.getAirlineDTO(airline));
            }
        }
        return airlineDTOList;
    }
}
