package pingpong.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import pingpong.model.Tournament;
import pingpong.model.TournamentCategory;
import pingpong.model.TournamentFee;
import pingpong.repository.TournamentRepo;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TournamentServiceImpl implements TournamentService {
    @Autowired
    private TournamentRepo tournamentRepo;

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
}
