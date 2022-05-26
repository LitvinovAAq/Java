package main.util;

import lombok.RequiredArgsConstructor;
import main.entity.Role;
import main.entity.User;
import main.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Service
@RequiredArgsConstructor
@Component
public class InitiateUtils implements CommandLineRunner {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder;
    @Override
    public void run(String... args) throws Exception {
        Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByUsername("admin"));
        if(!optionalUser.isPresent()) {
            Set<Role> roles = new HashSet<>();
            roles.add(Role.USER);
            roles.add(Role.ADMIN);
            User user = new User();
            user.setActive(true);
            user.setUsername("admin");
            user.setPassword(passwordEncoder.encode("admin"));
            user.setActive(true);
            user.setRoles(roles);
            userRepository.save(user);
        }
    }
}
