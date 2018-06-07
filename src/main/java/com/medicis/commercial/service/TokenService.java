package com.medicis.commercial.service;


import com.medicis.commercial.domain.User;
import com.medicis.commercial.repository.TokenRepository;
import com.medicis.commercial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TokenService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    public User validateEmail(String token){
        User user = getUser(token);
        if (user !=null) {
            user.setActive(true);
            userRepository.saveAndFlush(user);
            return user;
        }
        return null;
    }
    private User getUser(String verificationToken) {
        return tokenRepository.findByToken(verificationToken).getUser();
    }

}
