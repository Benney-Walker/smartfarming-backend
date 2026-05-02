package com.bbquantum.smartfarmingbackend.Service;

import com.bbquantum.smartfarmingbackend.Entity.Users;
import com.bbquantum.smartfarmingbackend.Repository.UsersRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CustomUserDetailsService implements UserDetailsService {

    @Autowired
    private UsersRepo usersRepo;

    @Override
    public UserDetails loadUserByUsername(String emailAddress) throws UsernameNotFoundException {
        Users user = usersRepo.findByEmailAddress(emailAddress)
                .orElseThrow(() -> new UsernameNotFoundException("Invalid username or password"));

        List<SimpleGrantedAuthority> authorities = user.getUserRoles().stream()
                .map(role -> new SimpleGrantedAuthority(role.getUserRole().name()))
                .toList();

        return new User(
                user.getEmailAddress(),
                user.getPassword(),
                authorities
        );
    }
}
