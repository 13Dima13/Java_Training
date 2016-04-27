package ua.stqa.pft.mantis.appmanager;

import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistry;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import ua.stqa.pft.mantis.model.UserData;
import ua.stqa.pft.mantis.model.Users;


import java.util.List;

public class DbHelper {

  private final org.hibernate.SessionFactory sessionFactory;

  public DbHelper() {
    // A SessionFactory is set up once for an application!
    final StandardServiceRegistry registry = new StandardServiceRegistryBuilder()
            .configure() // configures settings from hibernate.cfg.xml
            .build();

    sessionFactory = new MetadataSources(registry).buildMetadata().buildSessionFactory();

  }


  public Users users() {
    org.hibernate.Session session = sessionFactory.openSession();
    session.beginTransaction();
    List<UserData> result = session.createQuery("from UserData").list();
    for (UserData user : result) {
      System.out.println(user);
    }

    session.getTransaction().commit();
    session.close();

    return new Users(result);
  }
}