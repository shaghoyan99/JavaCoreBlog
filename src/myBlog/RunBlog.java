package myBlog;


import myBlog.exception.ModelNotFoundException;
import myBlog.ifS.Commands;

import myBlog.model.Post;
import myBlog.model.Type;
import myBlog.model.User;
import myBlog.storage.PostStorageImpl;
import myBlog.storage.UserStorageImpl;

import java.util.Date;
import java.util.Scanner;

public class RunBlog implements Commands {

    private static final UserStorageImpl userStorageImpl = new UserStorageImpl();
    private static final PostStorageImpl postStorageImpl = new PostStorageImpl();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        boolean isRun = true;
        while (isRun) {
            Commands.printMainCommands();
            int mainCommand;
            try {
                mainCommand = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                mainCommand = -1;
            }
            switch (mainCommand) {
                case LOGIN:
                    loginUser();
                    break;
                case REGISTER:
                    registor();
                    break;
                case POST_BY_CATEGORY:
                    searchPost();
                    break;
                case SEARCH_POST:
                    postByCategory();
                    break;
                case ALL_POST:
                    postStorageImpl.printAllPosts();
                    break;
                case EXIT:
                    isRun = false;
                    break;
                default:
                    System.out.println("Wrong command!");
            }
        }
    }

    private static void registor() {
        try{
            System.out.println("Please input User data: name,surname,email,password");
            String userData = scanner.nextLine();
            String[] userDataStr =  userData.split(",");
            User user = new User();
            user.setName(userDataStr[0]);
            user.setSurname(userDataStr[1]);
            user.setEmail(userDataStr[2]);
            user.setPassword(userDataStr[3]);
            user.setType(Type.USER);
            userStorageImpl.add(user);
            System.out.println("Thank you, User was added");
        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Data! please try again");
            registor();
        }
    }

    private static void loginUser() {
        System.out.println("Please input email and password(email.password)");
        String userData = scanner.nextLine();
        String[] userDataStr = userData.split(",");
        try {
            User user = userStorageImpl.getUserByEmailAndPassword(userDataStr[0],userDataStr[1]);
        }catch (ModelNotFoundException e){
            e.getMessage();
        }


    }



    private static void addPost() {
        try {
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
        } catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Data! please try again");
            addPost();
        }


    }

    private static void searchPost() {
        System.out.println("Write the word you want to search for");
        String search = scanner.nextLine();
        postStorageImpl.searchPostsByKeyword(search);


    }

    private static void postByCategory() {
        System.out.println("Please input Post category");
        try {
            String category = scanner.nextLine();
            postStorageImpl.printPostsByCategory(category);
        }catch (ModelNotFoundException e){
            System.out.println(e.getMessage());
            postByCategory();
        }
    }

}

