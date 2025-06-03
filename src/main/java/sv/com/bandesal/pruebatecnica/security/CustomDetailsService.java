package sv.com.bandesal.pruebatecnica.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sv.com.bandesal.pruebatecnica.model.Role;
import sv.com.bandesal.pruebatecnica.repository.IUserRepository;
import java.util.Collection;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class CustomDetailsService implements UserDetailsService{

    private final  IUserRepository IUserRepository;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var user = IUserRepository.findByEmail(email);
        if (user != null) {
                return new org.springframework.security.core.userdetails.User(user.getEmail(),
                        user.getPassword(),
                        mapRolesToAuthorities(user.getRoles()));
        }else
            throw new UsernameNotFoundException("Invalid username or password.");
    }

    private Collection < ? extends GrantedAuthority> mapRolesToAuthorities(Collection <Role> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getName()))
                .collect(Collectors.toList());
    }
}
