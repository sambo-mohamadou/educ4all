package com.poo2hibernate.educ4all.user;

import com.poo2hibernate.educ4all.niveau.Niveau;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Service
public class UserService {
    private final UserRepository userRepository;

    @Autowired
    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getUsers(){return userRepository.findAll();}

    public void addNewUser(User user) {
        Optional<User> userByEmail = userRepository
                .findUserByEmail(user.getName());

        if(userByEmail.isPresent()){
            throw new IllegalStateException("Email Of The User already taken");
        }

        userRepository.save(user);
    }

    public void deleteUser(Long userId) {
        if(!userRepository.existsById(userId)){
            throw new IllegalStateException("This User doesn't exist");
        }

        userRepository.deleteById(userId);
    }

    @Transactional
    public void updateUser(Long userId, String name, String email, String password, Niveau niveau) {
        User user = userRepository.findById(userId)
                .orElseThrow(() -> new IllegalStateException("This User doesn't exist"));

        if(name != null && name.length() > 0 && !Objects.equals(name, user.getName())) {
            user.setName(name);
        }

        if(email != null && email.length() > 0 && !Objects.equals(email, user.getEmail())) {
            user.setEmail(email);
        }

        if(password != null && password.length() > 0 && !Objects.equals(password, user.getPassword())) {
            user.setPassword(password);
        }

        if(niveau != null && !Objects.equals(niveau, user.getNiveau())) {
            user.setNiveau(niveau);
        }

    }

    public boolean verify(String email, String password) {
        User user = userRepository.findUserByEmail(email)
                .orElseThrow(() -> new IllegalStateException("This User doesn't exist"));

        return user.getPassword() == password;
    }
}
