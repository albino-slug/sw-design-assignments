package pingpong.DAO.factory;

import pingpong.DAO.UserDAO;

public abstract class DAOFactory {

    public enum Type {
        HIBERNATE,
        JDBC;
    }

    protected DAOFactory(){}

    public static DAOFactory getInstance(Type factoryType) {
        switch (factoryType) {
            case HIBERNATE:
                return new HibernateDaoFactory();
            case JDBC:
                return new JdbcDaoFactory();
            default:
                throw new IllegalArgumentException("Invalid factory");
        }
    }

      public abstract UserDAO getUserDao();
}
