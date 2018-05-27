package pingpong.DAO.factory.JdbcFactory;

import pingpong.model.Role;
import pingpong.model.User;
import pingpong.DAO.UserDAO;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import java.util.List;
import java.util.Optional;

public class JdbcUserDao implements UserDAO {
    @Override
    public Optional<User> findById(Integer id) {
        throw new NotImplementedException();
    }

    @Override
    public void delete(User objectToDelete) {
        throw new NotImplementedException();
    }

    @Override
    public void update(User objectToUpdate) {
        throw new NotImplementedException();
    }

    @Override
    public void insert(User objectToCreate) {
        throw new NotImplementedException();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByRole(Role role) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsername(String username) {
        return Optional.empty();
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
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
    public List<User> findAll() {
        return null;
    }

    @Override
    public void save(User o) {

    }
}