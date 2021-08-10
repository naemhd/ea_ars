package edu.miu.cs.cs544.ea_ars.dto;

import edu.miu.cs.cs544.ea_ars.domain.Address;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDto {
    private Long id;
    private String userName;
}
