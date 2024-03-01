package com.voting.votingsystem;

import com.voting.votingsystem.Entities.MyAuthority;
import com.voting.votingsystem.Entities.MyUserDetails;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        Set<MyAuthority> myAuthorities = new HashSet<>(Set.of(new MyAuthority("user"), new MyAuthority("Admin")));

        return new MyUserDetails("kelvinkerin@gmail.com","$2a$10$Js9Q.tP8KBT4s.S2W/w24.Os8PIAHtgIJnO6eZXvnqzEXFGMJ3qHu",myAuthorities);
    }

    public String getUserPassword() {
        UserDetails user = loadUserByUsername("kering");
        return user.getPassword();
    }


}
