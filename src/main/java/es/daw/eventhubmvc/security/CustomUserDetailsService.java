package es.daw.eventhubmvc.security;

import es.daw.eventhubmvc.entity.User;
import es.daw.eventhubmvc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CustomUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {


//        User user = userRepository.findByUsername(login)
//                .orElseThrow(() -> new UsernameNotFoundException(login));

        User user = userRepository
                .findByUsernameOrEmail(login,login)
                .orElseThrow(() -> new UsernameNotFoundException("User not found with username or email: " + login));

        return user;
    }



}

