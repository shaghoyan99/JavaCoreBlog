package myBlog.ifS;

public interface Commands {

    int LOGIN = 1;
    int REGISTER = 2;
    int POST_BY_CATEGORY = 3;
    int SEARCH_POST = 4;
    int ALL_POST = 5;
    int EXIT = 0;

    int LOGOUT = 0;
    int ADD_POST = 1;
    int SEARCH_ALL_POST = 2;
    int PRINT_ALL_POST = 3;

     static void printMainCommands() {
        System.out.println("Please input " + LOGIN + " for LOGIN");
        System.out.println("Please input " + REGISTER + " for REGISTER");
        System.out.println("Please input " + POST_BY_CATEGORY + " for PRINT_POSTS_BY_CATEGORY");
        System.out.println("Please input " + SEARCH_POST + " for SEARCH_POST");
        System.out.println("Please input " + ALL_POST + " for PRINT_ALL_POSTS");
        System.out.println("Please input " + EXIT + " for EXIT");
    }

     static void printUserCommands() {
        System.out.println("Please input " + LOGOUT + " for LOGOUT");
        System.out.println("Please input " + ADD_POST + " for ADD_POST");
        System.out.println("Please input " + SEARCH_ALL_POST + " for SEARCH_POST");
        System.out.println("Please input " + PRINT_ALL_POST + " for PRINT_BY_POST");

    }
}
