package schwarzsword.serviceImpl;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schwarzsword.entity.UsersEntity;
import schwarzsword.service.RegistrationService;

import java.util.ArrayList;
import java.util.List;

@Service("userDetailsService")
public class MyUserDetailsService implements UserDetailsService {

    Logger log = LogManager.getLogger(MyUserDetailsService.class);


    private final RegistrationService registrationService;

    @Autowired
    public MyUserDetailsService(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    @Transactional
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {


        UsersEntity usersEntity = registrationService.getUserByUsername(username);

        if (usersEntity == null) {
            log.info("User not found");
            throw new UsernameNotFoundException("Username not found");
        }

        return new User(usersEntity.getUsername(), usersEntity.getPassword(), getGrantedAuthorities(usersEntity));
    }

    private List<GrantedAuthority> getGrantedAuthorities(UsersEntity usersEntity) {

        log.info("User was found " + usersEntity.getUsername());


        List<GrantedAuthority> authorities = new ArrayList<>();
        authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));

        log.info("Count of authorities of user:" + authorities.size());

        return authorities;

    }
}