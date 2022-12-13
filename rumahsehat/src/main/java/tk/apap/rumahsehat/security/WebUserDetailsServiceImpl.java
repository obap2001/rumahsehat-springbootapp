package tk.apap.rumahsehat.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tk.apap.rumahsehat.model.UserModel;
import tk.apap.rumahsehat.repository.UserDb;


import java.util.ArrayList;
import java.util.List;

@Service
public class WebUserDetailsServiceImpl implements UserDetailsService {

    final UserDb userRepository;

    public WebUserDetailsServiceImpl(UserDb userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        UserModel user = userRepository.findByUsername(username);
        List<GrantedAuthority> authorityList = new ArrayList<>();
        authorityList.add(new SimpleGrantedAuthority(user.getRole()));
        return new User(user.getUsername(), user.getPassword(), authorityList);
    }
}