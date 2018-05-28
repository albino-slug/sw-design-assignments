package pingpong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pingpong.DAO.TournamentDAO;
import pingpong.DAO.UserDAO;
import pingpong.DAO.factory.DAOFactory;
import pingpong.model.Tournament;
import pingpong.model.TournamentCategory;
import pingpong.model.TournamentFee;
import pingpong.repository.TournamentRepo;
import pingpong.repository.UserRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentServiceImpl implements TournamentService {

    TournamentDAO tournamentRepo;
    UserDAO userRepo;
    public TournamentServiceImpl(){
        tournamentRepo = DAOFactory.getInstance(DAOFactory.Type.HIBERNATE).getTournamentDao();
        userRepo = DAOFactory.getInstance(DAOFactory.Type.HIBERNATE).getUserDao();
    }

//    @Autowired
//    private TournamentRepo tournamentRepo;
//
//    @Autowired
//    private UserRepo userRepo;

    @Override
    public List<Tournament> findAll() {
        return tournamentRepo.findAll();
    }

    @Override
    public Boolean save(Tournament tournament) {
        try {
            tournamentRepo.save(tournament);
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
                tournamentRepo.deleteById(id);
            }
            catch (Exception e) {
                e.printStackTrace();
                return Boolean.FALSE;
            }
        }
        return Boolean.TRUE;
    }

    @Override
    public List<Tournament> findByTournamentCategory(TournamentCategory tournamentCategory) {
        List<Tournament> wantedTournaments = new ArrayList<>();
        List<Tournament> allTournaments = findAll();

        for (Tournament tournament : allTournaments){
            if (tournament.getTournamentCategory() == tournamentCategory){
                wantedTournaments.add(tournament);
            }
        }

        return wantedTournaments;
    }

    @Override
    public Optional<Tournament> findById(Integer id) {
        return tournamentRepo.findById(id);
    }

    @Override
    public List<Tournament> findByTournamentFee(TournamentFee tournamentFee) {
        return tournamentRepo.findByTournamentFee(tournamentFee);
    }

    @Override
    public Optional<Tournament> findByTournamentName(String name) {
        return tournamentRepo.findByName(name);
    }

    @Override
    public Boolean addUserById(Integer tournamentId, Integer userId) {
        if (tournamentId.intValue() < 0 || userId.intValue() < 0){
            return Boolean.FALSE;
        }
        else {
            try {
                if (tournamentRepo.findById(tournamentId).isPresent()){
                    Tournament tournament = tournamentRepo.findById(tournamentId).get();
                    tournament.addNewPlayer(userRepo.findById(userId).get());
                    tournamentRepo.save(tournament);
                    System.out.println("User " + userId + " has been added to tournament " + tournamentId);
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
    public void removeUserById(Integer tournamentId, Integer userId){
        if (tournamentId.intValue() < 0 || userId.intValue() < 0){
            return ;
        }
        else {
            try {
                if (tournamentRepo.findById(tournamentId).isPresent()){
                    Tournament tournament = tournamentRepo.findById(tournamentId).get();
                    tournament.removePlayer(userRepo.findById(userId).get());
                    tournamentRepo.save(tournament);
                    System.out.println("User " + userId + " has been removed from course " + tournamentId);
                }
                else {
                    return ;
                }
            }
            catch (Exception e) {
                e.printStackTrace();
                return ;
            }
        }
    }

    @Override
    public List<Tournament> findTournamentsByUserId(Integer id) {
        List<Tournament> tournaments = findAll();
        List<Tournament> wantedTournaments = new ArrayList<>();

        for (Tournament tournament : tournaments){
            if (tournament.getPlayers().contains(userRepo.findById(id).get())){
                wantedTournaments.add(tournament);
            }
        }

        return wantedTournaments;
    }
}
