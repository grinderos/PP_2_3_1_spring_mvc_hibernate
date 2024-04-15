package web.service;


import web.model.User;

import java.util.List;

public interface UserService {

    void add(User user);

    List<User> getUsers();

    User getUserById(long id);

    void deleteUser(long id);

    /*
    Далее идут вспомогательные методы, чтоб не лазить каждый раз в Workbench
    */
    void truncateTable();

    void fillUsersTable();

}
