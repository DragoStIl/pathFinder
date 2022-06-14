package workshop.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import workshop.model.User;
import workshop.model.dto.UserRegistrationDTO;
import workshop.repository.UserRepository;

import java.util.Optional;

@Service
public class AuthService {

    private UserRepository userRepository;

    @Autowired
    public AuthService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public void register(UserRegistrationDTO registrationDTO){
        if(!registrationDTO.getPassword().equals(registrationDTO.getConfirmPassword())){
            throw new RuntimeException("password does not match the confirmation");
        }
        Optional<User> optionalUser = this.userRepository.findByEmail(registrationDTO.getEmail());

        if (optionalUser.isPresent()){
            throw new RuntimeException("email is already used");
        }

        User user = new User(
                registrationDTO.getUsername(),
                registrationDTO.getPassword(),
                registrationDTO.getEmail(),
                registrationDTO.getFullName(),
                registrationDTO.getAge()
        );
        this.userRepository.save(user);
    }
}
