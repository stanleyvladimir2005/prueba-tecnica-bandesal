package sv.com.bandesal.pruebatecnica.service;

import sv.com.bandesal.pruebatecnica.dto.UserDto;
import sv.com.bandesal.pruebatecnica.model.User;
import java.util.List;

public interface IUserService {

    void saveUser(UserDto userDto);
    User findByEmail(String email);
    List<UserDto> findAllUsers();
}