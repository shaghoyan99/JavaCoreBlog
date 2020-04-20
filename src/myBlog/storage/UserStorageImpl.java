package myBlog.storage;

import myBlog.exception.ModelNotFoundException;
import myBlog.ifS.UserStorage;
import myBlog.model.User;

public class UserStorageImpl implements UserStorage {

    private User[] users;
    private int size;

    public UserStorageImpl() {
        users = new User[15];
    }

    public UserStorageImpl(int temp) {
        users = new User[temp];
    }

    @Override
    public void add(User user) {
        if (size == users.length) {
            extend();
        }
        users[size++] = user;
    }

    private void extend() {
        User[] users1 = new User[users.length + 10];
        System.arraycopy(users, 0, users1, 0, users.length);
        users = users1;
    }


    @Override
    public User getUserByEmailAndPassword(String email, String password) throws ModelNotFoundException {
        for (int i = 0; i < size; i++) {
            if (users[i].getEmail().equals(email) && users[i].getPassword().equals(password)) {
                return users[i];
            }
        }
       throw new ModelNotFoundException("Wrong email or password !!!");
    }


    @Override
    public void printUsers() {
        for (int i = 0; i < size; i++) {
            System.out.println(users[i]);
        }
    }
}
