package com.rockieslearning.crud.security.services;


import com.rockieslearning.crud.entity.User;
import com.rockieslearning.crud.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private final UserRepository userRepository;

    public UserDetailsServiceImpl (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    @Transactional
    public UserDetails loadUserByUsername(String phone)
            throws UsernameNotFoundException {

        User user = userRepository.findUserByPhone(phone)
                .orElseThrow(() ->
                        new UsernameNotFoundException("User Not Found with -> phone : " + phone)
                );

        return UserDetailsImpl.build(user);
    }
}
