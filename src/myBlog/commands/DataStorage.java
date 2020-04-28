package myBlog.commands;

import myBlog.exception.ModelNotFoundException;
import myBlog.model.Post;
import myBlog.model.User;

public interface DataStorage {
    Post getPostByTitle(String title) throws ModelNotFoundException;

    void searchPostsByKeyword(String keyword) throws ModelNotFoundException;

    void printAllPosts();

    void printPostsByCategory(String category) throws  ModelNotFoundException;

    User getUserByEmailAndPassword(String email, String password) throws ModelNotFoundException;

    User getUserByEmail(String email);

    void addMap(String email,User user);

}
