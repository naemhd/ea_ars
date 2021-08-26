package edu.miu.cs.cs544.ea_ars.dto.DTOModel;

import edu.miu.cs.cs544.ea_ars.domain.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class AirPortDto {
    private Long id;
    private String airportCode;
    private String name;
    private Address address;
}