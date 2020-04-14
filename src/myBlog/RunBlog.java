package myBlog;

import javafx.geometry.Pos;
import myBlog.ifS.Commands;

import myBlog.model.Post;
import myBlog.storage.PostStorageImpl;

import java.util.Date;
import java.util.Scanner;

public class RunBlog implements Commands {

    private static PostStorageImpl postStorageImpl = new PostStorageImpl();
    private static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            printCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case EXIT:
                    isRun = false;
                    break;
                case ADD_POST:

                    break;
                case SEARCH_POST:

                    break;
                case POSTS_BY_CATEGORY:

                    break;
                case ALL_POSTS:

                    break;
                default:
                    System.out.println("Wrong command!");
            }
        }

    }

    private static void addPost() {
        System.out.println("Please input Post data: title,text,category");
        String postDataStr = scanner.nextLine();
        String[] postData = postDataStr.split(",");
        Post byTitle = postStorageImpl.getPostByTitle(postData[0]);
        if (byTitle != null) {
            System.out.println("Duplicate Title");
            addPost();
        } else {
            Post post = new Post();
            Date date = new Date();
            post.setTitle(postData[0]);
            post.setText(postData[1]);
            post.setCategory(postData[2]);
            post.setCreatedData(date);
            postStorageImpl.add(post);
            System.out.println("Thank you, Post was added");

        }
    }


    private static void printCommands() {
        System.out.println("Please input " + EXIT + " for EXIT");
        System.out.println("Please input " + ADD_POST + " for ADD_POST");
        System.out.println("Please input " + SEARCH_POST + " for SEARCH_POST");
        System.out.println("Please input " + POSTS_BY_CATEGORY + " for PRINT_POSTS_BY_CATEGORY");
        System.out.println("Please input " + ALL_POSTS + " for PRINT_ALL_POSTS");
    }
}

