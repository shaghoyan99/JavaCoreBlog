package myBlog.ifS;

import myBlog.model.Post;

public interface PostStorage {

    void add(Post post);

    Post getPostByTitle(String title);

    Post searchPostsByKeyword(String keyword);

    void printAllPosts();

    void printPostsByCategory(String category);

}
