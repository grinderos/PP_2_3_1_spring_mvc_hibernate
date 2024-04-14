package web.service;


import web.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    List<User> getUsers();

    User getUserById(long id);

    User createEmptyUser();

    void updateUser(long id, User user);

    void deleteUser(long id);

    /*
    Далее идут вспомогательные методы, чтоб не лазить каждый раз в Workbench
    */
    void truncateTable();

    void createUsersTable();

    void dropTable();
}
