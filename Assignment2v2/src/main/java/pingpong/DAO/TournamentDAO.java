package pingpong.DAO;

import pingpong.model.Tournament;

import java.util.Optional;

public interface TournamentDAO extends DAO<Tournament> {
@Override
Optional<Tournament> findById(Integer id);

@Override
    void delete(Tournament user);

@Override
    void update(Tournament user);

@Override
    void insert(Tournament user);
}
