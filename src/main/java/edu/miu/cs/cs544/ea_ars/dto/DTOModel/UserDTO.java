package edu.miu.cs.cs544.ea_ars.dto.DTOModel;

import edu.miu.cs.cs544.ea_ars.domain.UserRole;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
public class UserDTO {
    private String username;
    private String password;
    private UserRole role;
    private boolean isEnabled;
}
