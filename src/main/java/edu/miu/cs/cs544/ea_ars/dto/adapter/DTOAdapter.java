package edu.miu.cs.cs544.ea_ars.dto.adapter;


import edu.miu.cs.cs544.ea_ars.domain.User;
import edu.miu.cs.cs544.ea_ars.dto.DTOModel.UserDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;
@Component
public class DTOAdapter {

    public ModelMapper getMapper(){
        ModelMapper modelMapper = new ModelMapper();
        modelMapper= ModelMapperConfiguration.getConfiguration(modelMapper);
        return modelMapper;
    }

    public UserDTO userDTOAdapter(User user){
        UserDTO userDTO = getMapper().map(user, UserDTO.class);
        return userDTO;
    }

    public User dtoToUserAdapter(UserDTO userDTO){
        User user = getMapper().map(userDTO, User.class);
        return user;
    }

    public <S, T> T dtoMapper(S source, Class<T> target){
        return getMapper().map(source,target);
    }

    public <S, T> List<T> dtoListMapper(List<S> source, Class<T> targetClass) {
        return source
                .stream()
                .map(element -> getMapper().map(element, targetClass))
                .collect(Collectors.toList());
    }

//    How to use
//    List<UserDTO> userDtoList = mapList(users, UserDTO.class);
}
