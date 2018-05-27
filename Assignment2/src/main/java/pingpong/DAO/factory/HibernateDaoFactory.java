package pingpong.DAO.factory;

import pingpong.DAO.UserDAO;
import pingpong.DAO.factory.hibernateFactory.*;

public class HibernateDaoFactory extends DAOFactory {
    @Override
    public UserDAO getUserDao() {
        return new HibernateUserDAO();
    }
}
