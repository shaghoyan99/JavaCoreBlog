package myBlog.commands;

import myBlog.exception.ModelNotFoundException;
import myBlog.model.Post;
import myBlog.model.User;

public interface PostStorage {

    void add(Post post);

    Post getPostByTitle(String title) throws ModelNotFoundException;

    void searchPostsByKeyword(String keyword);

    void printAllPosts();

    void printPostsByCategory(String category) throws  ModelNotFoundException;

    void printPostByUser(String email);

}
