package DAO;

import Entity.User;

import java.util.List;

public interface UserDAO {

    User mergeUser(User user);
    void deleteUser(User user);
    List<User> getAll();
    User getById(Long userId);
}
