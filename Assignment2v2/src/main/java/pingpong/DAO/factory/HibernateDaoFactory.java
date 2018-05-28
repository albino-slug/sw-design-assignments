package pingpong.DAO.factory;

import pingpong.DAO.TournamentDAO;
import pingpong.DAO.UserDAO;
import pingpong.DAO.factory.hibernateFactory.*;
import pingpong.model.Tournament;

public class HibernateDaoFactory extends DAOFactory {
    @Override
    public UserDAO getUserDao() {
        return new HibernateUserDAO();
    }

    @Override
    public TournamentDAO getTournamentDao() {
        return new HibernateTournamentDAO();
    }
}
