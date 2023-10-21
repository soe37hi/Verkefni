package is.hi.hbv501g.verkefni.Services.Implementation;

import is.hi.hbv501g.verkefni.Persistence.Entities.User;
import is.hi.hbv501g.verkefni.Persistence.Repositories.UserRepository;
import is.hi.hbv501g.verkefni.Services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import java.util.List;

@Service
public class UserServiceImplementation implements UserService {
    BCryptPasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    private UserRepository userRepository;

    @Autowired
    public UserServiceImplementation(UserRepository userRepository){

        this.userRepository = userRepository;
    }

    @Override
    public User save(User user){
        String encodedPassword = passwordEncoder.encode(user.getPassword());
        user.setPassword(encodedPassword);
        return userRepository.save(user);
    }

    @Override
    public List<User> findAll(){

        return userRepository.findAll();
    }

    @Override
    public User findByUsername(String username){

        return userRepository.findByUsername(username);
    }

    @Override
    public User login(User user){
        User doesExist = findByUsername(user.getUsername());
        if(doesExist != null){
            if(passwordEncoder.matches(user.getPassword(), doesExist.getPassword())){
                return doesExist;
            }
        }

        return null;
    }
}