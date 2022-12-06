package tk.apap.rumahsehat.security;

import java.util.HashSet;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import tk.apap.rumahsehat.model.UserModel;
import tk.apap.rumahsehat.repository.UserDb;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {
  @Autowired
  private UserDb userDb;

  @Override
  public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    UserModel user = userDb.findByUsername(username);
    if (user!=null) {
      Set<GrantedAuthority> grantedAuthorities = new HashSet<GrantedAuthority>();
      grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole()));
      return new User(user.getUsername(), user.getPassword(), grantedAuthorities);
    } else {
			throw new UsernameNotFoundException("User not found with username: " + username);
		}
  }
}
