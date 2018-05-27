package pingpong.repo.factory;

import pingpong.repo.UserDAO;
import pingpong.repo.factory.JdbcFactory.JdbcUserDao;

public class JdbcDaoFactory extends DAOFactory {

    @Override
    public UserDAO getUserDao() {
        return new JdbcUserDao();
    }

   }
