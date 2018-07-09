package com.medicis.commercial.service;


import com.medicis.commercial.domain.User;
import com.medicis.commercial.domain.VerificationToken;
import com.medicis.commercial.repository.TokenRepository;
import com.medicis.commercial.repository.UserRepository;
import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;


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
    HospitalService hospitalService;

    private static final Logger logger = LoggerFactory.getLogger(UserService.class);
    private final static String ACCOUNT_SID = "ACc5a04797cedd4b225d4a09cf9f60cbdc";
    private final static String AUTH_TOKEN = "fd7cbe137ceb89960e591bc0e05e4ed3";
    private static final String ALPHA_NUMERIC_STRING = "ABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

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


    public Object findLoggedInUsername() {
        try {
            UserDetails principal = (UserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String role = principal.getAuthorities().iterator().next().toString();
            switch (role) {
                case "USER":
                    return getUserByEmail(principal.getUsername());
                case "HOSPITAL":
                    return hospitalService.getHospitalByEmail(principal.getUsername());
                default:
                    return null;
            }
        }catch (java.lang.ClassCastException e){
            logger.info(e.getLocalizedMessage());
            return null;
        }
    }

    @Modifying
    public Boolean editUserProfile(User user){
       User authUser = (User)findLoggedInUsername();
       if (!authUser.getId().equals(user.getId())) return false;
           if (!user.getPhoneNumber().isEmpty() && !user.getVerifiedPhoneNumber() && !user.getPhoneNumber().equals(authUser.getPhoneNumber())) {
               Twilio.init(ACCOUNT_SID, AUTH_TOKEN);
               String randomString = randomAlphaNumeric(4);
            /*   Message message = Message.creator(
                       new PhoneNumber(user.getPhoneNumber()),
                       new PhoneNumber("+14159681251"),
                       "Please verify your phone number. " +
                               "Enter this code: " + randomString
               ).create();*/
               authUser.setPhoneNumber(user.getPhoneNumber());
               authUser.setPhoneToken(randomString);
           }

        if (!user.getfName().equals(authUser.getfName()) && !user.getfName().isEmpty()){
            authUser.setfName(user.getfName());
        }
        if (!user.getlName().equals(authUser.getlName()) && !user.getlName().isEmpty()){
            authUser.setlName(user.getlName());
        }
        if (!user.getGender().equals(authUser.getGender()) && !user.getGender().isEmpty()){
            authUser.setGender(user.getGender());
        }
        if (user.getDob()!=null){
            authUser.setDob(user.getDob());
        }
        BCryptPasswordEncoder bCryptPasswordEncoder = new BCryptPasswordEncoder();
        if (!bCryptPasswordEncoder.matches(user.getPassword(),authUser.getPassword())){
            authUser.setPassword(bCryptPasswordEncoder.encode(user.getPassword()));
        }

        userRepository.saveAndFlush(authUser);
        return true;
    }

    private User getUserByEmail(String email){
        return userRepository.getByEmail(email);
    }

    private static String randomAlphaNumeric(int count) {
        StringBuilder builder = new StringBuilder();
        while (count-- != 0) {
            int character = (int)(Math.random()*ALPHA_NUMERIC_STRING.length());
            builder.append(ALPHA_NUMERIC_STRING.charAt(character));
        }
        return builder.toString();
    }

    public Boolean verifyPhone(User authUser, String code) {
        if (authUser.getPhoneToken().equals(code)){
            authUser.setVerifiedPhoneNumber(true);
            userRepository.saveAndFlush(authUser);
            return true;
        }
        return false;
    }
}
