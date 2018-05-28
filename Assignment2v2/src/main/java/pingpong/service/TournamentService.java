package pingpong.service;

import pingpong.model.Tournament;
import pingpong.model.TournamentCategory;
import pingpong.model.TournamentFee;

import java.util.List;
import java.util.Optional;

public interface TournamentService {
    List<Tournament> findAll();

    Boolean save(Tournament tournament);

    Boolean deleteById(Integer id);

    List<Tournament> findByTournamentCategory(TournamentCategory tournamentCategory);

    Optional<Tournament> findById(Integer id);

    List<Tournament> findByTournamentFee(TournamentFee tournamentFee);

    Optional<Tournament> findByTournamentName(String name);

    Boolean addUserById(Integer tournamentId, Integer userId);

    void removeUserById(Integer tournamentId, Integer userId);

    List<Tournament> findTournamentsByUserId(Integer id);
}
