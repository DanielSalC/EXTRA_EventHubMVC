package es.daw.eventhubmvc.service;

import es.daw.eventhubmvc.dto.UserProfileUpdateRequest;
import es.daw.eventhubmvc.entity.User;
import es.daw.eventhubmvc.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

@Service
//@Transactional // en observación????
@RequiredArgsConstructor
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(
                        () -> new UsernameNotFoundException("Username not found")
                );
    }

    @Override
    public User updateProfile(Long userId, UserProfileUpdateRequest request) {
        return null;
    }
}
