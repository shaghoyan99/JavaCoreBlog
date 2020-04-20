package myBlog.ifS;

import myBlog.exception.ModelNotFoundException;
import myBlog.model.User;

public interface UserStorage {

    void add(User user);

    User getUserByEmailAndPassword(String email,String password) throws ModelNotFoundException;

    void printUsers();
}
