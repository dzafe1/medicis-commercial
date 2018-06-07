package com.medicis.commercial.service;

import com.medicis.commercial.domain.Hospital;
import com.medicis.commercial.domain.User;
import com.medicis.commercial.repository.HospitalRepository;
import com.medicis.commercial.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.transaction.annotation.Transactional;

import java.util.Collection;
import java.util.Collections;

@Service
public class MedicisUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    HospitalRepository hospitalRepository;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getOneByEmail(email);
        if (user != null) {
            Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority(user.getRole()));
            return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getActive(), true, true,
                    true, authorities);
        }else{
            Hospital hospital = hospitalRepository.findOneByEmail(email);
            Collection<? extends GrantedAuthority> authorities = Collections.singleton(new SimpleGrantedAuthority("Hospital"));
            return new org.springframework.security.core.userdetails.User(hospital.getEmail(), hospital.getPassword(),hospital.getActive(),true,true,
                    true,  authorities);
        }
    }
}