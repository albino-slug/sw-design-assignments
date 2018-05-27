package pingpong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pingpong.DAO.UserDAO;
import pingpong.DAO.factory.DAOFactory;
import pingpong.model.Role;
import pingpong.model.Tournament;
import pingpong.model.User;
import pingpong.repository.TournamentRepo;
import pingpong.repository.UserRepo;

import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

//    UserDAO userRepo;
//    public UserServiceImpl(){
//        userRepo = DAOFactory.getInstance(DAOFactory.Type.HIBERNATE).getUserDao();
//    }

    @Override
    public List<User> findAll() {
        return userRepo.findAll();
    }

    @Override
    public Boolean save(User user) {
        try {
            userRepo.save(user);
        }
        catch (Exception e) {
            return Boolean.FALSE;
        }
        return Boolean.TRUE;
    }

    @Override
    public Boolean deleteById(Integer id) {
        if (id.intValue() < 0){
            return Boolean.FALSE;
        }
        else {
            try {
                userRepo.deleteById(id);
            }
            catch (Exception e) {
                e.printStackTrace();
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public Optional<User> findById(Integer id) {
        return userRepo.findById(id);
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return userRepo.findByEmail(email);
    }

    @Override
    public Optional<User> findByRole(Role role) {
        return userRepo.findByRole(role);
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return userRepo.findByUsername(username);
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        return userRepo.findByUsernameAndPassword(username, password);
    }

    public Boolean addSumToUserAccountById(Integer userId, Integer sum){
        if (userId.intValue() < 0){
            return Boolean.FALSE;
        }
        else {
            try {
                if (userRepo.findById(userId).isPresent()){
                    User user = userRepo.findById(userId).get();
                    user.addToAccount(sum);
                    userRepo.save(user);
                    System.out.println("Sum " + sum + " has been added to user's " + userId + " account");
                }
                else {
                    return Boolean.FALSE;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public Boolean withdrawSumFromUserAccountById(Integer userId, Integer sum){
        if (userId.intValue() < 0){
            return Boolean.FALSE;
        }
        else {
            try {
                if (userRepo.findById(userId).isPresent()){
                    User user = userRepo.findById(userId).get();
                    user.withdrawFromAccount(sum);
                    userRepo.save(user);
                    System.out.println("Sum " + sum + " has been withdrawn from user's " + userId + " account");
                }
                else {
                    return Boolean.FALSE;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    public Optional<Integer> getAccountBalanceById(Integer userId){
        Optional<Integer> accountBalance;
        if (userId.intValue() < 0){
            return Optional.empty();
        }
        else {
            try {
                if (userRepo.findById(userId).isPresent()){
                    User user = userRepo.findById(userId).get();
                    accountBalance = Optional.ofNullable(user.getAccount());
                }
                else {
                    return Optional.empty();
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                return Optional.empty();
            }
        }
        return accountBalance;
    }

    @Override
    public Boolean addTournamentById(Integer userId, Integer tournamentId) {
        if (tournamentId.intValue() < 0 || userId.intValue() < 0){
            return Boolean.FALSE;
        }
        else {
            try {
                if (tournamentRepo.findById(tournamentId).isPresent()){
                    User user = userRepo.findById(userId).get();
                    Tournament tournament = tournamentRepo.findById(tournamentId).get();
                    user.enrollInTournament(tournament);
                    if (user.getAccount() >= tournament.getTournamentPrice()){
                        user.setAccount(user.getAccount() - tournamentRepo.findById(tournamentId).get().getTournamentPrice());
                        userRepo.save(user);
                        System.out.println("Tournament " + tournamentId + " has been added to user " + userId);
                    }
                    else {
                        System.out.println("[Error] user " + userId + " does not possess the necessary funds for this transaction");

                    }

                }
                else {
                    return Boolean.FALSE;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public void removeTournamentById(Integer userId, Integer tournamentId) {
        if (tournamentId.intValue() < 0 || userId.intValue() < 0){
            return;
        }
        else {
            try {
                if (tournamentRepo.findById(tournamentId).isPresent()){
                    User user = userRepo.findById(userId).get();
                    user.disenrollFromTournament(tournamentRepo.findById(tournamentId).get());
                    userRepo.save(user);
                    System.out.println("Tournament " + tournamentId + " has been removed from user " + userId);
                }
                else {
                    return;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                return;
            }
        }
    }



    @Autowired
    private TournamentRepo tournamentRepo;

    @Autowired
    private UserRepo userRepo;

    //UserServiceImpl userServiceimpl = new UserServiceImpl();
}

