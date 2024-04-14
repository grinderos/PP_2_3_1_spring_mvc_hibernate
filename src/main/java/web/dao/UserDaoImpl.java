package web.dao;

import web.model.User;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;

import javax.persistence.TypedQuery;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {
    @Autowired
    private Environment env;

    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public void add(User user) {
        sessionFactory.getCurrentSession().save(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        TypedQuery<User> query = sessionFactory.getCurrentSession().createQuery("from User");
        return query.getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserById(long id) {
        TypedQuery<User> query = sessionFactory.getCurrentSession()
                .createQuery("from User where id = :id")
                .setParameter("id", id);

        if (query.getResultList().size() > 0) {
            return query.getResultList().get(0);
        } else {
            return null;
        }
    }

    public void updateUser(long id, User user){
        sessionFactory.getCurrentSession().merge(user);
    }

    public void deleteUser(long id){
        sessionFactory.getCurrentSession().delete(getUserById(id));
    }
    /*
    Далее идут вспомогательные методы, чтоб не лазить каждый раз в Workbench
    */

    @Override
    public void truncateTable() {
        existsTable("truncate");
    }

    @Override
    public void createUsersTable() {
        Session session = sessionFactory.getCurrentSession();

        session.createSQLQuery(
                        "create table if not exists " +
                                env.getProperty("db.tableUsers") +
                                " (id bigint primary key auto_increment, " +
                                "firstName varchar(40) not null, " +
                                "lastName varchar(40) not null, " +
                                "age int not null, " +
                                "email varchar(40) not null;")
                .executeUpdate();
    }

    @Override
    public void dropTable() {
        existsTable("drop");
    }

    private void existsTable(String action) {
        Session session = sessionFactory.getCurrentSession();
        session.createSQLQuery(action + " table " + env.getProperty("db.tableUsers"))
                .executeUpdate();
    }
}
