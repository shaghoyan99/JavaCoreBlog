package myBlog.commands;

import myBlog.exception.ModelNotFoundException;
import myBlog.model.User;

public interface UserStorage {

    void add(User user);

    User getUserByEmailAndPassword(String email,String password) throws ModelNotFoundException;

    User getUserByEmail(String email);

    void printUsers();
}