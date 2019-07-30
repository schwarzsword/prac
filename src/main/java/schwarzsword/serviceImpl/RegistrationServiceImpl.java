package schwarzsword.serviceImpl;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import schwarzsword.entity.UsersEntity;
import schwarzsword.repository.UsersRepository;
import schwarzsword.service.RegistrationService;

import java.util.Optional;

@Service("registrationService")
public class RegistrationServiceImpl implements RegistrationService {
    private final
    UsersRepository usersRepository;

    private String salt = BCrypt.gensalt();

    @Autowired
    public RegistrationServiceImpl(UsersRepository usersRepository) {
        this.usersRepository = usersRepository;
    }

    @Override
    public UsersEntity signIn(String username, String password) {
        UsersEntity usersEntity = getUserByUsername(username);
        if (BCrypt.checkpw(password, usersEntity.getPassword())) {
            return usersEntity;
        } else throw new UsernameNotFoundException("Неверное имя пользователя или пароль");
    }

    @Transactional
    @Override
    public UsersEntity signUp(String username, String password, String mail)
            throws UsernameNotFoundException {
        if (!usersRepository.existsByUsername(username)) {
            String pwd = BCrypt.hashpw(password, salt);
            UsersEntity user = new UsersEntity(username, pwd, mail);
            usersRepository.save(user);
            return user;
        } else throw new UsernameNotFoundException("Данный пользователь уже существует");
    }

    @Override
    public UsersEntity getUserByUsername(String username) throws UsernameNotFoundException {
        Optional<UsersEntity> optionalUsersEntity = usersRepository.findByUsername(username);
        if (optionalUsersEntity.isPresent()) {
            return optionalUsersEntity.get();
        } else throw new UsernameNotFoundException("Пользователь с данным именем не найден");
    }
}