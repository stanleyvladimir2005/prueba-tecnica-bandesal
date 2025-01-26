package sv.com.bandesal.pruebatecnica.serviceImpl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import sv.com.bandesal.pruebatecnica.dto.UserDto;
import sv.com.bandesal.pruebatecnica.model.Role;
import sv.com.bandesal.pruebatecnica.model.User;
import sv.com.bandesal.pruebatecnica.repository.IRoleRepository;
import sv.com.bandesal.pruebatecnica.repository.IUserRepository;
import sv.com.bandesal.pruebatecnica.service.IUserService;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements IUserService {

    @Autowired
    private IUserRepository userRepository;

    @Autowired
    private IRoleRepository roleRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public void saveUser(UserDto userDto) {
        var user = new User();
        user.setName(userDto.getFirstName() + " " + userDto.getLastName());
        user.setEmail(userDto.getEmail());

        user.setPassword(passwordEncoder.encode(userDto.getPassword()));
        var role = roleRepository.findByName("ROLE_ADMIN");
        if(role == null)
            role = checkRoleExist();

        user.setRoles(List.of(role));
        userRepository.save(user);
    }

    @Override
    public User findByEmail(String email) {
        return userRepository.findByEmail(email);
    }

    @Override
    public List<UserDto> findAllUsers() {
        var users = userRepository.findAll();
        return users.stream().map(this::convertEntityToDto).collect(Collectors.toList());
    }

    private UserDto convertEntityToDto(User user){
        var userDto = new UserDto();
        var name = user.getName().split(" ");
        userDto.setFirstName(name[0]);
        userDto.setLastName(name[1]);
        userDto.setEmail(user.getEmail());
        return userDto;
    }

    private Role checkRoleExist() {
        var role = new Role();
        role.setName("ROLE_ADMIN");
        return roleRepository.save(role);
    }
}