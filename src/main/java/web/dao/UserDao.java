package web.dao;

import web.model.User;

import java.util.List;

public interface UserDao {
    void add(User user);

    List<User> getUsers();

    User getUserById(long id);

    static User createEmptyUser(){
        return new User();
    }

    void updateUser(long id, User user);

    void deleteUser(long id);
    /*
    Далее идут вспомогательные методы, чтоб не лазить каждый раз в Workbench
    */

    void truncateTable();

    void createUsersTable();

    void dropTable();
}
