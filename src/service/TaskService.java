package service;

import entity.Task;
import repository.TaskDatabase;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class TaskService {

    private Connection conn = null;
    private TaskDatabase database = null;
    public TaskService(Connection con){
        this.conn = con;
        this.database = new TaskDatabase(conn);
    }

    public void addTask(Task task){

        try {
            database.addTask(task);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void updateTitle(String text, int id){
        try {
            database.editTaskTitle(text,id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void updateText(String text, int id){
        try {
            database.editTaskText(text,id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void markCompleted(int id){
        try {
            database.markCompleted(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteTask(int id)
    {
        try {
            database.deleteTask(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public Task searchATask(int id){
        try {
            return database.fetchTask(id);
        } catch (Exception e) {
            System.out.println(e.getMessage());
            return new Task();
        }
    }

    public void fetchAlltask(String assignedTo){

        List<Task> list = null;
        try {
            list = database.fetchAllTasksOfAUser(assignedTo);
            for(Task task : list){
                System.out.println(task);

            }

            if(list.size()==0){
                System.out.println("There are no tasks assigned to you");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }

    public void fetchAllCompletedtask(String assignedTo){
        List<Task> list = null;
        try {
            list = database.fetchAllCompletedOrUncompleteTasks(true,assignedTo);
            for(Task task: list  ){
                System.out.println(task);
            }

            if(list.size()==0){
                System.out.println("There are no task matching the requirement");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
    public void fetchAllNotCompletedtask(String assignedTo){
        List<Task> list = null;
        try {
            list = database.fetchAllCompletedOrUncompleteTasks(false,assignedTo);
            for(Task task:  list){
                System.out.println(task);
            }

            if(list.size()==0){
                System.out.println("There are no task matching the requirement");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

    }
}
