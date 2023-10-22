package is.hi.hbv501g.verkefni.Services;

import is.hi.hbv501g.verkefni.Persistence.Entities.User;

import java.util.List;


public interface UserService {
    User save(User user);
    List<User> findAll();
    User findByUsername(String username);
    User login(User user);
}
