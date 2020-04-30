package rs.ac.uns.ftn.sbnz.service.implementation;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import rs.ac.uns.ftn.sbnz.models.User;
import rs.ac.uns.ftn.sbnz.repository.UserRepository;
import rs.ac.uns.ftn.sbnz.service.UserService;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public User findOne(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public User findUserByEmail(String email) {
        return userRepository.findUserByEmail(email).orElse(null);
    }

    @Override
    public User save(User user) {
        return userRepository.save(user);
    }

    @Override
    public void registerAUser(User user) {
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        this.save(user);
    }
}
