package com.lld.bookmyshow.services;

import com.lld.bookmyshow.exceptions.PasswordNotMatchException;
import com.lld.bookmyshow.models.User;
import com.lld.bookmyshow.repositories.UserRepository;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class UserService {
    private UserRepository userRepository;

    UserService(UserRepository userRepository)
    {
        this.userRepository = userRepository;
    }
    public User singUp(String email, String password) throws PasswordNotMatchException {
        Optional<User> optionalUser = userRepository.findAllByEmail(email);

        User user = null;
        if(optionalUser.isPresent())
        {
            user =  signIn(optionalUser.get(), password);
        }
        else {
            user = new User();
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            user.setEmail(email);
            user.setPassword(bCryptPasswordEncoder.encode(password));
            user = userRepository.save(user);
        }
        return user;
    }

    public User signIn(User user, String password) throws PasswordNotMatchException {
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();

        if(bCryptPasswordEncoder.matches(user.getPassword(), password))
        {
            System.out.println("Sign In successful");
        }
        else {
            throw new PasswordNotMatchException("Wrong password");
        }
        return user;
    }
}
