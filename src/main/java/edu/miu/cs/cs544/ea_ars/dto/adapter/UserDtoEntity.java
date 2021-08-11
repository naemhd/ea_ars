package edu.miu.cs.cs544.ea_ars.dto.adapter;

import edu.miu.cs.cs544.ea_ars.domain.User;
import edu.miu.cs.cs544.ea_ars.dto.UserDto;

import java.util.List;
import java.util.stream.Collectors;

public class UserDtoEntity {
    public UserDto entityToDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setId(user.getId());
        userDto.setUserName(userDto.getUserName());

        return userDto;
    }

    public List<UserDto> entityToDto(List<User> users) {
        return users.stream().map(a -> entityToDto(a)).collect(Collectors.toList());
    }

    public User dtoToEntity(UserDto userDto) {
        User user = new User();
        user.setId(userDto.getId());
        user.setUsername(userDto.getUserName());
        return user;
    }

//   public User creatEntityFromDto(UserDto userDto) {
//         User user = new User();
//        user.setId(userDto.getId());
//        user.setUsername(userDto.getUserName());
//        return airport;
//    }
}
