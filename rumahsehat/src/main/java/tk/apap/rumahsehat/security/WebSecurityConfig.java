// package tk.apap.rumahsehat.security;

// import org.springframework.beans.factory.annotation.Autowired;
// import org.springframework.context.annotation.Bean;
// import org.springframework.context.annotation.Configuration;
// import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
// import org.springframework.security.config.annotation.web.builders.HttpSecurity;
// import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
// import org.springframework.security.web.SecurityFilterChain;
// import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

// @Configuration
// @EnableWebSecurity
// public class WebSecurityConfig {

<<<<<<< HEAD
  @Bean
  public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
    http
            .authorizeRequests()
            .antMatchers("/css/**").permitAll()
            .antMatchers("/js/**").permitAll()
            .antMatchers("/login-sso", "/validate-ticket").permitAll()
            .antMatchers("/obat/{id}/ubah-stok").hasAuthority("apoteker")
            .anyRequest().authenticated()
            .and()
            .formLogin()
            .loginPage("/login").permitAll()
            .and()
            .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
            .logoutSuccessUrl("/login").permitAll();
    return http.build();
  }
=======
//   @Bean
//   public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
//     http
//       .authorizeRequests()
//       .antMatchers("/css/**").permitAll()
//       .antMatchers("/js/**").permitAll()
//       .antMatchers("/login-sso", "/validate-ticket").permitAll()
//       .antMatchers("/obat/{id}/ubah-stok").hasAuthority("apoteker")
//       .anyRequest().authenticated()
//       .and()
//       .formLogin()
//       .loginPage("/login").permitAll()
//       .and()
//       .logout().logoutRequestMatcher(new AntPathRequestMatcher("/logout"))
//       .logoutSuccessUrl("/login").permitAll();
//     return http.build();
//   }
>>>>>>> e1135a7517ae09a61e6339b98d5f993188e71e89

//   public BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();

//   @Autowired
//   private UserDetailsService userDetailsService;

//   @Autowired
//   public void configAuthentication(AuthenticationManagerBuilder auth) throws Exception {
//     auth.userDetailsService(userDetailsService).passwordEncoder(encoder);
//   }
// }



