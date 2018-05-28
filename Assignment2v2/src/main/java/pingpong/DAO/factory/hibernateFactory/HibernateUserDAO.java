package pingpong.DAO.factory.hibernateFactory;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pingpong.model.Role;
import pingpong.model.User;
import pingpong.DAO.UserDAO;
import pingpong.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class HibernateUserDAO implements UserDAO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    @Override
    public Optional<User> findById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Transaction transaction = currentSession.beginTransaction();
        User cart = (User) currentSession.get(User.class, id);
        transaction.commit();
        if (cart == null){
            return Optional.empty();
        }
        else{
            return Optional.of(cart);
        }
    }

    @Override
    public void delete(User objectToDelete) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
    }

    @Override
    public void update(User objectToUpdate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
    }

    @Override
    public void insert(User objectToCreate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(objectToCreate);
        transaction.commit();
    }

    @Override
    public Optional<User> findByEmail(String email) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        User cart = (User) currentSession.get(User.class, email);
        transaction.commit();
        if (cart == null){
            return Optional.empty();
        }
        else{
            return Optional.of(cart);
        }
    }

    @Override
    public Optional<User> findByRole(Role role) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        User cart = (User) currentSession.get(User.class, role);
        transaction.commit();
        if (cart == null){
            return Optional.empty();
        }
        else{
            return Optional.of(cart);
        }
    }

    @Override
    public Optional<User> findByUsername(String username) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        User cart = (User) currentSession.get(User.class, username);
        transaction.commit();
        if (cart == null){
            return Optional.empty();
        }
        else{
            return Optional.of(cart);
        }
    }

    @Override
    public Optional<User> findByUsernameAndPassword(String username, String password) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        User cart = (User) currentSession.get(User.class, username);//TODO
        transaction.commit();
        if (cart == null){
            return Optional.empty();
        }
        else{
            return Optional.of(cart);
        }
    }

    @Override
    public void deleteById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query hqlQuery = currentSession.createQuery("delete from " + User.class.getName() + " where id= :idParameter");
        hqlQuery.setInteger("idParameter", id);
        hqlQuery.executeUpdate();

        transaction.commit();
    }

    @Override
    public void closeConnection() {
        sessionFactory.close();
    }

    @Override
    public List<User> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        List<User> users = (List<User>)((Query) currentSession.createQuery("from " + User.class.getName())).list();
        transaction.commit();
        return users;
    }

    @Override
    public void save(User o) {
        this.insert(o);//TODO add update or smth
    }
}