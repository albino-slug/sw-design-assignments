package pingpong.service;

import pingpong.model.Role;
import pingpong.model.User;

import java.util.List;
import java.util.Optional;

public interface UserService {

    List<User> findAll();

    Boolean save(User user);

    Boolean deleteById(Integer id);

    Optional<User> findById(Integer id);

    Optional<User> findByEmail(String email);

    Optional<User> findByRole(Role role);

    Optional<User> findByUsername(String username);

    Optional<User> findByUsernameAndPassword(String username, String password);

    Boolean addSumToUserAccountById(Integer userId, Integer sum);

    Boolean withdrawSumFromUserAccountById(Integer userId, Integer sum);

    Optional<Integer> getAccountBalanceById(Integer userId);

  //  Boolean addTournamentById(Integer userId, Integer tournamentId);

  //  void removeTournamentById(Integer userId, Integer tournamentId);

}
