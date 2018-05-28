package pingpong.DAO.factory.JdbcFactory;

import pingpong.DAO.TournamentDAO;
import pingpong.model.Tournament;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Optional;

public class JdbcTournamentDao implements TournamentDAO {
    @Override
    public Optional<Tournament> findById(Integer id) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(Tournament objectToDelete) {
        throw new NotImplementedException();
    }

    @Override
    public void update(Tournament objectToUpdate) {
        throw new NotImplementedException();
    }

    @Override
    public void insert(Tournament objectToCreate) {
        throw new NotImplementedException();
    }

    public Optional<Tournament> findByEmail(String email) {
        return Optional.empty();
    }

    public Optional<Tournament> findByTournamentname(String username) {
        return Optional.empty();
    }

    public Optional<Tournament> findByTournamentnameAndPassword(String username, String password) {
        return Optional.empty();
    }

    @Override
    public void deleteById(Integer id) {
        throw new NotImplementedException();
    }

    @Override
    public void closeConnection() {

    }

    @Override
    public List<Tournament> findAll() {
        return null;
    }

    @Override
    public void save(Tournament o) {

    }
}
