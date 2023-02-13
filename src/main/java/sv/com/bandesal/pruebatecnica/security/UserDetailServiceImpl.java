package sv.com.bandesal.pruebatecnica.security;
/*
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import sv.com.bandesal.pruebatecnica.model.User;
import sv.com.bandesal.pruebatecnica.repository.IUserRepository;

@Service
public class UserDetailServiceImpl implements UserDetailsService {

    @Autowired
    private IUserRepository repo;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = repo.findByEmail(email);

        if (user != null)
            return new UserDetailsImpl(user);
        else throw new UsernameNotFoundException("Invalid username or password.");
    }
}*/