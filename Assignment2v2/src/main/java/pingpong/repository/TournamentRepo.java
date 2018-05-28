package pingpong.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import pingpong.model.Tournament;
import pingpong.model.TournamentFee;

import java.util.List;
import java.util.Optional;

@Repository
public interface TournamentRepo extends JpaRepository<Tournament, Integer> {
    List<Tournament> findAll();

    @Transactional
    Boolean deleteById(Integer id);

    Optional<Tournament> findById(Integer id);

    List<Tournament> findByTournamentFee(TournamentFee tournamentFee);

    Optional<Tournament> findByName(String name);
}
