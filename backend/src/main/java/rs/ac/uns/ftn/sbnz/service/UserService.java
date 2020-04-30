package rs.ac.uns.ftn.sbnz.service;

import rs.ac.uns.ftn.sbnz.models.User;

public interface UserService {

    User findOne(Long id);

    User findUserByEmail(String email);

    User save(User user);

    void registerAUser(User user);
}
