package pingpong.DAO;

import java.util.List;
import java.util.Optional;

public interface DAO<T> {
    Optional<T> findById(Integer id);

    void delete(T objectToDelete);

    void update(T objectToUpdate);

    void insert(T objectToCreate);

    void deleteById(Integer id);

    void closeConnection();

    List<T> findAll();

    void save(T o);
}