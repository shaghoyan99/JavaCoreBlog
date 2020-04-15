package myBlog.storage;

import myBlog.ifS.PostStorage;
import myBlog.model.Post;

public class PostStorageImpl implements PostStorage {

    private Post[] posts;
    private int size;


    public PostStorageImpl() {
        posts = new Post[15];
    }

    public PostStorageImpl(int temp) {
        posts = new Post[temp];
    }


    @Override
    public void add(Post post) {
        if (size == posts.length) {
            extend();
        }
        posts[size++] = post;
    }

    private void extend() {
        Post[] posts1 = new Post[posts.length * 2];
        System.arraycopy(posts, 0, posts1, 0, posts.length);
        posts = posts1;
    }


    @Override
    public Post getPostByTitle(String title) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().equals(title)) {
                return posts[i];
            }
        }
        return null;
    }

    @Override
    public Post searchPostsByKeyword(String keyword) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getTitle().contains(keyword) || posts[i].getText().contains(keyword)) {
                System.out.println(posts[i]);
            }
        }
        return null;
    }

    @Override
    public void printAllPosts() {
        for (int i = 0; i < size; i++) {
            System.out.println(posts[i]);
        }

    }

    @Override
    public void printPostsByCategory(String category) {
        for (int i = 0; i < size; i++) {
            if (posts[i].getCategory().equals(category)) {
                System.out.println(posts[i]);
            }
        }
    }
}
