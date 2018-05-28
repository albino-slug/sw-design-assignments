package pingpong.DAO.factory.hibernateFactory;

import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import pingpong.DAO.TournamentDAO;
import pingpong.model.Tournament;
import pingpong.util.HibernateUtil;

import java.util.List;
import java.util.Optional;

public class HibernateTournamentDAO implements TournamentDAO {
    private SessionFactory sessionFactory = HibernateUtil.getSessionFactory();


    @Override
    public Optional<Tournament> findById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Transaction transaction = currentSession.beginTransaction();
        Tournament cart = (Tournament) currentSession.get(Tournament.class, id);
        transaction.commit();
        if (cart == null){
            return Optional.empty();
        }
        else{
            return Optional.of(cart);
        }
    }

    @Override
    public void delete(Tournament objectToDelete) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.delete(objectToDelete);
        transaction.commit();
    }

    @Override
    public void update(Tournament objectToUpdate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.update(objectToUpdate);
        transaction.commit();
    }

    @Override
    public void insert(Tournament objectToCreate) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        currentSession.persist(objectToCreate);
        transaction.commit();
    }

    @Override
    public void deleteById(Integer id) {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        Query hqlQuery = currentSession.createQuery("delete from " + Tournament.class.getName() + " where id= :idParameter");
        hqlQuery.setInteger("idParameter", id);
        hqlQuery.executeUpdate();

        transaction.commit();
    }

    @Override
    public void closeConnection() {
        sessionFactory.close();
    }

    @Override
    public List<Tournament> findAll() {
        Session currentSession = sessionFactory.getCurrentSession();
        Transaction transaction = currentSession.beginTransaction();
        List<Tournament> users = (List<Tournament>)((Query) currentSession.createQuery("from " + Tournament.class.getName())).list();
        transaction.commit();
        return users;
    }

    @Override
    public void save(Tournament o) {
        this.insert(o);//TODO add update or smth
    }
}
