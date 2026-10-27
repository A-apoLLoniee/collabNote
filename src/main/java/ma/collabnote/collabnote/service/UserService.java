package ma.collabnote.collabnote.service;

import ma.collabnote.collabnote.entity.User;
import ma.collabnote.collabnote.exception.UserNotFound;
import ma.collabnote.collabnote.repository.UserRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.UUID;

@Service
public class UserService {
    private final UserRepository userRepository;

    public UserService (UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    public User getUserById(UUID userId) {
        return userRepository.findById(userId)
                .orElseThrow(() -> new UserNotFound(userId));
    }

    public User addUser (User user) {
        return userRepository.save(user);
    }

    @Transactional
    public User changeName (UUID id, String name) {
        User user = getUserById(id);
        user.setName(name);
        return userRepository.save(user);
    }

    @Transactional
    public User changePassword (UUID id, String password) {
        User user = getUserById(id);
        user.setPasswordHash(password);
        return userRepository.save(user);
    }

    @Transactional
    public void removeUser (UUID id) {
        User user = getUserById(id);
        userRepository.delete(user);
    }
 }
