package DAO;

import Entity.User;

import java.util.List;

public class UserDAOImpl extends CommonDAO implements UserDAO{

    @Override
    public User mergeUser(User user) {
        return super.merge(user);
    }

    @Override
    public void deleteUser(User user) {
super.delete(user);
    }

    @Override
    public List<User> getAll() {
        return super.getAll(User.class);
    }

    @Override
    public User getById(Long userId) {
        return super.getById(userId, User.class);
    }
}
