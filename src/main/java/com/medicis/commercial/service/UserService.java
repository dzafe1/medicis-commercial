package com.medicis.commercial.service;


import com.medicis.commercial.domain.User;
import com.medicis.commercial.domain.VerificationToken;
import com.medicis.commercial.repository.TokenRepository;
import com.medicis.commercial.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.web.authentication.WebAuthenticationDetails;
import org.springframework.stereotype.Service;


import javax.servlet.http.HttpServletRequest;
import java.util.UUID;

@Service
public class UserService{

    @Autowired
    UserRepository userRepository;

    @Autowired
    TokenRepository tokenRepository;

    @Autowired
    public JavaMailSender emailSender;


    @Autowired
    private AuthenticationManager authenticationManager;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);

    public boolean registerUser(User user){
        if (emailExists(user.getEmail())){
            return false;
        }else {
            BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
            String token = UUID.randomUUID().toString();
            user.setImgPath("images/avatar5.png");
            user.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
            userRepository.save(user);
            createVerificationToken(user, token);
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(user.getEmail());
            message.setSubject("Medicis Confirmation Code");
            message.setText("Please enter this code in order to activate your account: " + token);
            emailSender.send(message);
            return true;
        }

    }

    private Boolean emailExists(String email){
        User user = userRepository.getByEmail(email);
        return user != null;
    }

    public VerificationToken getVerificationToken(String VerificationToken) {
        return tokenRepository.findByToken(VerificationToken);
    }

    private void createVerificationToken(User user, String token) {
        VerificationToken newToken = new VerificationToken(token,user);
        tokenRepository.save(newToken);
    }


    public User findLoggedInUsername() {
        try {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            return getUserByEmail(principal.getUsername());
        }catch (java.lang.ClassCastException e){
            logger.info(e.getLocalizedMessage());
            return null;
        }

    }
    private User getUserByEmail(String email){
        return userRepository.getByEmail(email);
    }

   /* public void autoLogin(HttpServletRequest request,User user) {
        UserDetails userDetails = userDetailsService.loadUserByUsername(user.getEmail());
        UsernamePasswordAuthenticationToken usernamePasswordAuthenticationToken = new UsernamePasswordAuthenticationToken(userDetails, "HarisHaris", userDetails.getAuthorities());
        usernamePasswordAuthenticationToken.setDetails(new WebAuthenticationDetails(request));
        authenticationManager.authenticate(usernamePasswordAuthenticationToken);

        if (usernamePasswordAuthenticationToken.isAuthenticated()) {
            SecurityContextHolder.getContext().setAuthentication(usernamePasswordAuthenticationToken);
            logger.debug(String.format("Auto login %s successfully!", user.getEmail()));
        }
    }*/

}
