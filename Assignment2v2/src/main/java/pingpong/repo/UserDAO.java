package pingpong.repo;

import pingpong.model.Role;
import pingpong.model.User;

import java.util.Optional;

public interface UserDAO extends DAO<User> {
    @Override
    Optional<User> findById(Integer id);

    @Override
    void delete(User user);

    @Override
    void update(User user);

    @Override
    void insert(User user);

    Optional<User> findByEmail(String email);

    Optional<User> findByRole(Role role);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);
}
