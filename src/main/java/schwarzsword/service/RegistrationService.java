package schwarzsword.service;

import org.springframework.security.core.userdetails.UsernameNotFoundException;
import schwarzsword.entity.UsersEntity;

public interface RegistrationService {
    UsersEntity signIn(String username, String password) throws UsernameNotFoundException;

    UsersEntity signUp(String username, String password, String mail) throws UsernameNotFoundException;

    UsersEntity getUserByUsername(String username) throws UsernameNotFoundException;

}
