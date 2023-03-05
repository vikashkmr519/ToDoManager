import config.DatabaseConfig;
import entity.Task;
import entity.User;
import service.TaskService;
import service.UserService;

import java.sql.Connection;
import java.util.Scanner;

public class Main {

    private static Connection conn = null;

    private static String currUserEmail = null;

    public static void main(String[] args) {
        System.out.println("Welcome To our TODO MANAGEMENT SYSTEM");
        Scanner sc = new Scanner(System.in);
        optionForLogin(sc);

    }

    public static void optionForLogin(Scanner sc) {
        boolean exitCondition = true;
        conn = DatabaseConfig.getConnection();
        UserService userService = new UserService(conn);
        TaskService taskService = new TaskService(conn);
        do {
            System.out.println("********Choose one option*********\n"
                    + "1. Login\n" +
                    "2. Register\n" +
                    "0. Exit");
            String choice = sc.nextLine();

            switch (choice) {
                case "1":
                    loginUser(sc, userService, taskService);

                    break;
                case "2":
                    registerUser(sc, userService);
                    break;
                case "0":
                    System.out.println("***********Thank You!! for visiting**********");
                    exitCondition = false;
                    break;
                default:
                    System.out.println("Choose Correct Option");
            }

        } while (exitCondition);

    }

    private static void registerUser(Scanner sc , UserService userService){
        User user = new User();
        System.out.println("----Enter username----");
        String username = sc.nextLine();
        System.out.println("----Enter email----");
        String email = sc.nextLine();
        System.out.println("----Enter Password----");
        String password = sc.nextLine();
        user.setUsername(username);
        user.setEmail(email);
        user.setPassword(password);
        userService.registerUser(user);
    }

    public static void loginUser(Scanner sc, UserService userService, TaskService taskService) {



            System.out.println("******Welcome to Login panel*******");
            System.out.println("-----Enter your email-----");
            String email= sc.nextLine();
            System.out.println("------Enter your password------");
            String password = sc.nextLine();

            if (!email.isEmpty() && !password.isEmpty()) {
                userService.loginUser(email, password);
                currUserEmail = email;
                workAfterLoggedIn(sc, taskService);
            } else {
                System.out.println("******Invalid Credentials*****\n"+
                        "************************************");

            }
        }




    private static void workAfterLoggedIn(Scanner sc, TaskService taskService) {

        boolean afterLoginCondition = true;

        do {
            System.out.println("*****Choose any one option****** \n" +
                    "1. Add Task\n" +
                    "2. Update Task\n" +
                    "3. Delete Task\n" +
                    "4. Search A Task\n" +
                    "5. See All Your Tasks\n" +
                    "6. See All Completed Task\n" +
                    "7. See All Non Completed Task\n" +
                    "8. Logout");
            String choice = sc.nextLine();

            switch (choice) {
                case "1": addTask(sc,taskService);
                break;
                case "2": updateTask(sc,taskService);
                break;
                case "3":
                    deleteTask(sc,taskService);
                    break;
                case "4":
                    searchAtask(sc,taskService);
                    break;
                case "5":
                    fetchAlltask(taskService);
                    break;
                case "6":
                    fetchAllCompletedTask(taskService);
                    break;
                case "7":
                    fetchAllNotCompletedTask(taskService);
                    break;
                case "8":
                    afterLoginCondition = false;
                    break;
                default:
                    System.out.println("***Choose correct option****");
            }

        } while (afterLoginCondition);
    }
    private static void fetchAlltask(TaskService taskService){
        taskService.fetchAlltask(currUserEmail);
    }
    private static void fetchAllCompletedTask(TaskService taskService){
        taskService.fetchAllCompletedtask( currUserEmail);
    }
    private static void fetchAllNotCompletedTask(TaskService taskService){
        taskService.fetchAllNotCompletedtask( currUserEmail);
    }

    private static void searchAtask(Scanner sc, TaskService taskService){
        System.out.println("----Enter task id---");
        int id  = sc.nextInt();
        sc.nextLine();
        System.out.println(taskService.searchATask(id));
    }

    private static void deleteTask(Scanner sc, TaskService taskService){
        System.out.println("---Enter task id----");
        int id  = sc.nextInt();
        sc.nextLine();
        taskService.deleteTask(id);
    }

    private static void addTask(Scanner sc , TaskService taskService){
        Task  task = new Task();
        System.out.println("----Enter your task title----");
        task.setTaskTitle(sc.nextLine());
        System.out.println("----Enter Your task text----");
        task.setTaskText(sc.nextLine());
        task.setTaskCompleted(false);
        task.setAssignedTo(currUserEmail);
        taskService.addTask(task);
    }

    private static void updateTask(Scanner sc , TaskService taskService){
        boolean updateCondition = true;
        do{
            System.out.println("****Select One Option*****\n"+
                    "1. Edit Text\n"+
                    "2. Edit title\n"+
                    "3. Mark Completed\n"+
                    "4. Exit Updation");

            String  choice = sc.nextLine();
            int taskid = 0;
            switch(choice){

                case "1":
                    System.out.println("-----Enter taskId----");
                    taskid = sc.nextInt();
                    sc.nextLine();
                    System.out.println("-----Enter the updated text----");
                    String text = sc.nextLine();

                    taskService.updateText(text,taskid);
                    break;
                case "2":
                    System.out.println("-----Enter taskId-----");
                    taskid = sc.nextInt();
                    sc.nextLine();
                    System.out.println("----Enter the updated title-----");
                    String title = sc.nextLine();

                    taskService.updateTitle(title,taskid);
                    break;
                case "3":
                    System.out.println("----Enter taskId----");
                    taskid = sc.nextInt();
                    sc.nextLine();
                    taskService.markCompleted(taskid);
                    break;
                case "4":
                    updateCondition= false;
                            break;
                default:
                    System.out.println("*****Choose correct option*****");


            }
        }while(updateCondition);
    }
}
