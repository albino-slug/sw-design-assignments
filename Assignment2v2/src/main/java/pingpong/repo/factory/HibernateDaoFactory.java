package pingpong.repo.factory;

import pingpong.repo.UserDAO;
import pingpong.repo.factory.hibernateFactory.*;

public class HibernateDaoFactory extends DAOFactory {
    @Override
    public UserDAO getUserDao() {
        return new HibernateUserDAO();
    }
}
