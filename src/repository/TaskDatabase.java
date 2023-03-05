package repository;

import entity.Task;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class TaskDatabase {

    private Connection conn = null;
    public TaskDatabase(Connection conn){
        this.conn = conn;
    }

    public  void addTask(Task task) throws Exception {
        String sql = "insert into task (taskTitle,taskText,assignedTo,taskCompleted) values(?,?,?,?)";
        PreparedStatement ps = null;

            ps = conn.prepareStatement(sql);
            ps.setString(1,task.getTaskTitle());
            ps.setString(2,task.getTaskText());
            ps.setString(3,task.getAssignedTo());
            ps.setBoolean(4,false);
            if(ps.executeUpdate()==0){
                throw new Exception("Failed to add the task");
            }else{
                System.out.println("Task Successfully Added");
            }

    }

    public void deleteTask(int taskId) throws Exception {
        String sql = "delete from task where task_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,taskId);
            if(ps.executeUpdate()==0){
                throw new Exception("Failed to delete the task with task id "+taskId);
            }else{
                System.out.println("Successfully Deleted the task with task Id "+taskId);
            }


    }

    public void editTaskText(String text, int taskId) throws Exception {
       String sql ="update task set taskText=? where task_id=?";
        PreparedStatement ps = null;

            ps = conn.prepareStatement(sql);
            ps.setString(1,text);
            ps.setInt(2,taskId);
           if( ps.executeUpdate()==0){
               throw new Exception("Error while updating task with taskId "+ taskId);
           }else{
               System.out.println("Successfully updated");
           }


    }

    public void editTaskTitle(String text, int taskId) throws Exception {
        String sql = "update task set taskTitle=? where task_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,text);
            ps.setInt(2,taskId);
           if( ps.executeUpdate()==0){
               throw new Exception("Failed To Update Text Title with task Id "+taskId);
           }else{
               System.out.println("Successfully Updated Text title");
           }

    }

    public void markCompleted(int taskId) throws Exception {
        String sql = "update task set taskCompleted=? where task_id=?";

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setBoolean(1,true);
            ps.setInt(2,taskId);
            if(ps.executeUpdate()==0){
                throw new Exception("Failed to Mark Complete the task please try again");
            }else{
                System.out.println("Task marked Completed successfully");
            }


    }

    public Task fetchTask(int taskId) throws Exception {
        String sql ="select task_id, taskTitle, taskText, assignedTo, taskCompleted from task where task_id=?";


            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setInt(1,taskId);
           ResultSet rs = ps.executeQuery();

           if(rs.next()){
               Task task = new Task();
               task.setTaskId(rs.getInt("task_id"));
               task.setTaskCompleted(rs.getBoolean("taskCompleted"));
               task.setTaskText(rs.getString("taskText"));
               task.setTaskTitle(rs.getString("taskTitle"));
               task.setAssignedTo(rs.getString("assignedTo"));
               return task;
           }else{
               throw new Exception("No Task with task Id "+ taskId);
           }


    }

    public List<Task> fetchAllTasksOfAUser(String assignedTo) throws SQLException {

        String sql ="select task_id, taskTitle, taskText, assignedTo, taskCompleted from task where assignedTo=?";
        List<Task> list = new ArrayList<>();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,assignedTo);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Task task = new Task();
                task.setTaskId(rs.getInt("task_id"));
                task.setTaskCompleted(rs.getBoolean("taskCompleted"));
                task.setTaskText(rs.getString("taskText"));
                task.setTaskTitle(rs.getString("taskTitle"));
                task.setAssignedTo(rs.getString("assignedTo"));
                list.add(task);

            }


        return list;

    }

    public List<Task> fetchAllCompletedOrUncompleteTasks(boolean status, String assignedTo) throws SQLException {
        String sql ="select task_id, taskTitle, taskText, assignedTo, taskCompleted from task where assignedTo=? and taskCompleted=?";
        List<Task> list = new ArrayList<>();

            PreparedStatement ps = conn.prepareStatement(sql);
            ps.setString(1,assignedTo);
            ps.setBoolean(2,status);
            ResultSet rs = ps.executeQuery();
            while (rs.next()){
                Task task = new Task();
                task.setTaskId(rs.getInt("task_id"));
                task.setTaskCompleted(rs.getBoolean("taskCompleted"));
                task.setTaskText(rs.getString("taskText"));
                task.setTaskTitle(rs.getString("taskTitle"));
                task.setAssignedTo(rs.getString("assignedTo"));
                list.add(task);

            }


        return list;
    }

    
}
