package web.service;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import web.config.DaoConfig;
import web.dao.UserDao;
import web.model.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Transactional
    @Override
    public void add(User user) {
        userDao.add(user);
    }

    @Transactional(readOnly = true)
    @Override
    public List<User> getUsers() {
        return userDao.getUsers();
    }

    @Override
    @Transactional(readOnly = true)
    public User getUserById(long id) {
        return userDao.getUserById(id);
    }

    @Override
    @Transactional
    public User createEmptyUser(){
        return UserDao.createEmptyUser();
    }
    @Override
    @Transactional
    public void updateUser(long id, User user){
        userDao.updateUser(id, user);
    }

    @Override
    @Transactional
    public void deleteUser(long id){
        userDao.deleteUser(id);
    }

    /*
    Далее идут вспомогательные методы, чтоб не лазить каждый раз в Workbench
    */
    @Override
    @Transactional
    public void truncateTable() {
        userDao.truncateTable();
    }

    @Override
    @Transactional
    public void createUsersTable() {
        userDao.createUsersTable();
    }

    @Override
    @Transactional
    public void dropTable() {
        userDao.dropTable();
    }
}
