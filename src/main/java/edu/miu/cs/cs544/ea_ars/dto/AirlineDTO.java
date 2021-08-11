package edu.miu.cs.cs544.ea_ars.dto;

import edu.miu.cs.cs544.ea_ars.dto.DTOModel.FlightDTO;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.util.ArrayList;
import java.util.List;


@Getter
@Setter
@NoArgsConstructor
@ToString
public class AirlineDTO {

    private Long Id;

    @NotEmpty
    private String code;

    @NotEmpty
    private String name;

    private String history;

    private List<FlightDTO> flights = new ArrayList<>();

    public AirlineDTO(String code, String name, String history) {
        this.code = code;
        this.name = name;
        this.history = history;
    }
}


