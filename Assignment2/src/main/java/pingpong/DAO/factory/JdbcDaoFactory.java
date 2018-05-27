package pingpong.DAO.factory;

import pingpong.DAO.UserDAO;
import pingpong.DAO.factory.JdbcFactory.JdbcUserDao;

public class JdbcDaoFactory extends DAOFactory {

    @Override
    public UserDAO getUserDao() {
        return new JdbcUserDao();
    }

   }
