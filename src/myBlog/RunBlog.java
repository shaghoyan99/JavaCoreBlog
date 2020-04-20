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
    private static  User user;

    public static void main(String[] args) {
        main();
    }

    private static void main() {
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
                    login();
                    break;
                case REGISTER:
                    registor();
                    break;
                case POST_BY_CATEGORY:
                    postByCategory();
                    break;
                case SEARCH_POST:
                    searchPost();
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
            User userByEmail = userStorageImpl.getUserByEmail(userDataStr[2]);
            if (userByEmail != null) {
                System.out.println("Dublicate Data!!!");
            }else {
                User user = new User();
                user.setName(userDataStr[0]);
                user.setSurname(userDataStr[1]);
                user.setEmail(userDataStr[2]);
                user.setPassword(userDataStr[3]);
                user.setType(Type.USER);
                userStorageImpl.add(user);
                System.out.println("Thank you, User was added");
            }

        }catch (ArrayIndexOutOfBoundsException e) {
            System.out.println("Invalid Data! please try again");
            registor();
        }
    }

    private static void login() {
        System.out.println("Please input email and password(email.password)");
        String userData = scanner.nextLine();
        String[] userDataStr = userData.split(",");
        try {
            user = userStorageImpl.getUserByEmailAndPassword(userDataStr[0],userDataStr[1]);
            if (user.getType() == Type.USER) {
                loginuser();
            }
        }catch (ModelNotFoundException | ArrayIndexOutOfBoundsException e){
            System.out.println(e.getMessage());
            login();
        }


    }

    private static void loginuser() {
        boolean isRun = true;
        while (isRun) {
            Commands.printUserCommands();
            int command;
            try {
                command = Integer.parseInt(scanner.nextLine());
            } catch (NumberFormatException e) {
                command = -1;
            }
            switch (command) {
                case LOGOUT:
                    isRun = false;
                    main ();
                    break;
                case ADD_POST:
                    addPost();
                    break;
                case SEARCH_ALL_POST:
                    searchPost();
                    break;
                case PRINT_ALL_POST:
                    postStorageImpl.printAllPosts();
                    break;
            }
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
                post.setUser(user);
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

