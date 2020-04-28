package myBlog.storage;

import myBlog.commands.DataStorage;
import myBlog.exception.ModelNotFoundException;
import myBlog.model.Post;
import myBlog.model.User;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class DataStorageImpl implements DataStorage {
    List<User> users;
    List<Post> posts;
    Map<String, User> userMap = new HashMap<>();


    public DataStorageImpl() {
        users = new ArrayList<>();
        posts = new ArrayList<>();
    }

    public DataStorageImpl(int lenght) {
        users = new ArrayList<>(lenght);
        posts = new ArrayList<>(lenght);
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void addPost(Post post) {
        posts.add(post);
    }

    @Override
    public Post getPostByTitle(String title) throws ModelNotFoundException {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getTitle().equals(title)) {
                return posts.get(i);
            }
        }
        return null;
    }

    @Override
    public void searchPostsByKeyword(String keyword) throws ModelNotFoundException {
        boolean isFound = false;
        for (Post post : posts) {
            if (post.getTitle().contains(keyword) | post.getText().contains(keyword)) {
                isFound = true;
                System.out.println(post);
            }
        }
        if (!isFound) {
            throw new ModelNotFoundException();
        }
    }

    @Override
    public void printAllPosts() {
        for (int i = 0; i < posts.size(); i++) {
            System.out.println(posts.get(i));
        }
    }

    @Override
    public void printPostsByCategory(String category) throws ModelNotFoundException {
        for (int i = 0; i < posts.size(); i++) {
            if (posts.get(i).getCategory().equals(category)) {
                System.out.println(posts.get(i));
            }
        }
    }

    @Override
    public User getUserByEmailAndPassword(String email, String password) throws ModelNotFoundException {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email) && users.get(i).getPassword().equals(password)) {
                return users.get(i);
            }
        }
        throw new ModelNotFoundException("Wrong email or password !!!");
    }


    @Override
    public User getUserByEmail(String email) {
        for (int i = 0; i < users.size(); i++) {
            if (users.get(i).getEmail().equals(email)) {
                return users.get(i);
            }
        }
        return null;
    }

    @Override
    public void addMap(String email, User user) {
        userMap.put(email, user);
    }

}
