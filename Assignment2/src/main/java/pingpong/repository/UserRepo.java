package pingpong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pingpong.model.Role;
import pingpong.model.User;

import java.util.Optional;

@Repository
public interface UserRepo extends JpaRepository<User, Integer> {

    Optional<User> findById(Integer id);

    Optional<User> findByRole(Role role);

    @Transactional
    void deleteById(Integer id);

    public Optional<User> findByEmail(String email);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

}

