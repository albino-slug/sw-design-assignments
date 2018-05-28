package pingpong.service;

import pingpong.model.User;

import java.util.Optional;

public interface AuthenticationService {

    Optional<User> loadByNameAndPassword(String username, String password);

    Boolean register(User user);

}
