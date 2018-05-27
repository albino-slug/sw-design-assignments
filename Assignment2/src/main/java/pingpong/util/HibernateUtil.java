package pingpong.util;

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.service.ServiceRegistry;
import org.springframework.context.annotation.Bean;

public class HibernateUtil {

    private static final String CONFIG_FILE_NAME = "hibernate.cfg.xml";
    private static SessionFactory sessionFactory;

    @Bean
    private static SessionFactory buildSessionFactory() {
        try {
            // Create the SessionFactory from hibernate.cfg.xml
            Configuration configuration = new Configuration();
            configuration.configure(CONFIG_FILE_NAME);
            configuration.addAnnotatedClass(pingpong.model.Game.class);
            configuration.addAnnotatedClass(pingpong.model.Match.class);
            configuration.addAnnotatedClass(pingpong.model.Tournament.class);
            configuration.addAnnotatedClass(pingpong.model.User.class);
//            configuration.addAnnotatedClass(pingpong.model.User.class);
            ServiceRegistry serviceRegistry = new StandardServiceRegistryBuilder().applySettings(configuration.getProperties()).build();
            SessionFactory sessionFactory = configuration.buildSessionFactory(serviceRegistry);
            return sessionFactory;
         //   sessionFactory.setPackagesToScan(new String[]{"com.myapps.testapps.model"});
        }
        catch (Throwable ex) {
            System.err.println("Initial SessionFactory creation failed." + ex);
            ex.printStackTrace();
            throw new ExceptionInInitializerError(ex);
        }
    }

    public static SessionFactory getSessionFactory() {
        if (sessionFactory == null)
            sessionFactory = buildSessionFactory();
        return sessionFactory;
    }
}
