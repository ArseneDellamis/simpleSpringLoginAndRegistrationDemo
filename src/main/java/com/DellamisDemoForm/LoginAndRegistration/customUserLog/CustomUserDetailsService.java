package com.DellamisDemoForm.LoginAndRegistration.customUserLog;

import com.DellamisDemoForm.LoginAndRegistration.dao.UserRepository;
import com.DellamisDemoForm.LoginAndRegistration.entities.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {

        User user= userRepository.findByEmail(username) ;
        if (user == null){
            throw new UsernameNotFoundException("User not Found");
        }

            return new CustomUserDetails(user);
        }
    }
