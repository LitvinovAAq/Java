package main;



import main.entity.*;
import main.repository.JournalRepository;
import main.repository.UserRepository;
import main.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.security.crypto.factory.PasswordEncoderFactories;
import org.springframework.security.crypto.password.PasswordEncoder;


import java.util.HashSet;
import java.util.Optional;
import java.util.Set;


@SpringBootApplication()
public class Application {

    public static void main(String[] args) {
        SpringApplication.run(Application.class, args);
    }

    @Autowired
    private JournalRepository repo;

    @Autowired
    private UserRepository userRepository;

    @Bean
    public PasswordEncoder passwordEncoder() {
        return PasswordEncoderFactories.createDelegatingPasswordEncoder();
    }
//    @Autowired
//    private PasswordEncoder passwordEncoder;

//    @Bean
//    public CommandLineRunner test(RoutesService routesService, AutoService autoService,
//                                  JournalService journalService, AutoPersonnelService autoPersonnelService){
//        return args -> {
//            Optional<User> optionalUser = Optional.ofNullable(userRepository.findUserByUsername("admin"));
//            if(!optionalUser.isPresent()) {
//                Set<Role> roles = new HashSet<>();
//                roles.add(Role.USER);
//                roles.add(Role.ADMIN);
//                User user = new User();
//                user.setActive(true);
//                user.setUsername("admin");
//                user.setPassword(passwordEncoder.encode("admin"));
//                user.setActive(true);
//                user.setRoles(roles);
//                userRepository.save(user);
//            }
//        };
//    }


}
