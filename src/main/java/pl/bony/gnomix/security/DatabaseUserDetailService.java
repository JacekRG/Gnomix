package pl.bony.gnomix.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import pl.bony.gnomix.security.model.User;
import pl.bony.gnomix.security.model.UserPrincipal;
import pl.bony.gnomix.security.model.UserRepository;

@Service
public class DatabaseUserDetailService implements UserDetailsService {

    private final UserRepository repo;

    @Autowired
    public DatabaseUserDetailService(UserRepository userRepository) {
        this.repo = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user = repo.findByUsername(username);

        if (user == null) {
            throw new UsernameNotFoundException(username);
        }

        return new UserPrincipal(user);
    }
}
