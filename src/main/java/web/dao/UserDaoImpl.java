package web.dao;

import org.springframework.transaction.annotation.Transactional;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.stereotype.Repository;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class UserDaoImpl implements UserDao {

    private final Environment env;

    @PersistenceContext
    private EntityManager entityManager;

    @Autowired
    public UserDaoImpl(Environment env) {
        this.env = env;
    }

    @Override
    public void add(User user) {
        entityManager.merge(user);
    }

    @Override
    @SuppressWarnings("unchecked")
    public List<User> getUsers() {
        return entityManager.createQuery("from User", User.class).getResultList();
    }

    @Override
    @SuppressWarnings("unchecked")
    public User getUserById(long id) {
        return entityManager.find(User.class, id);
    }

    public void deleteUser(long id) {
        entityManager.remove(getUserById(id));
    }


    /*
    Далее идут вспомогательные методы
    */

    @Override
    @Transactional
    public void truncateTable() {
        existsTable("truncate");
    }

    @Override
    @Transactional
    public void fillUsersTable() {
        entityManager.createNativeQuery(
                        "INSERT INTO " +
                                env.getProperty("db.tableUsers") +
                                " (firstName, lastName, age, email) VALUES " +
                                "('James', 'Bond', 41, 'agent007@mi6.com'), " +
                                "('Ethan', 'Hunt', 42,'mission-imposible@wagner.com'), " +
                                "('Tony', 'Stark', 53,'T.Stark@starkindustries.st'), " +
                                "('Meglin', 'Rodion',48, 'no data'), " +
                                "('Elon', 'Musk', 52, 'elon.musk@SpaceX.com'), " +
                                "('Vladimir', 'Putin', 72, 'putin@email.ru'), " +
                                "('Alexandr', 'Lukashenko', 69, 'lukasBel@mail.by');")
                .executeUpdate();
    }

    private void existsTable(String action) {
        entityManager.createNativeQuery(action + " table " + env.getProperty("db.tableUsers")).executeUpdate();
    }
}












//@Override
//@Transactional
//public void dropTable() {
//    existsTable("drop");
//}


//    @Override
//    @Transactional
//    public void createUsersTable() {
//        entityManager.createNativeQuery(
//                        "create table if not exists users." +
//                                env.getProperty("db.tableUsers") +
//                                " (id bigint primary key auto_increment, " +
//                                "firstName varchar(40) not null, " +
//                                "lastName varchar(40) not null, " +
//                                "age int not null, " +
//                                "email varchar(40) not null;")
//                .executeUpdate();
//    }