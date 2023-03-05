package entity;


public class Task {

    /**
     * taskid in task table is as task_id and its marked as auto_increment while creating table in database
     * hence while adding task don't add task_id
     */
    private int taskId;
    private String taskTitle;
    private String taskText;
    private String assignedTo;
    private boolean taskCompleted;


    public Task(){

    }

    public Task(int taskId, String taskTitle, String taskText, String assignedTo, boolean taskCompleted) {
        this.taskId = taskId;
        this.taskTitle = taskTitle;
        this.taskText = taskText;
        this.assignedTo = assignedTo;
        this.taskCompleted = taskCompleted;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskTitle() {
        return taskTitle;
    }

    public void setTaskTitle(String taskTitle) {
        this.taskTitle = taskTitle;
    }

    public String getTaskText() {
        return taskText;
    }

    public void setTaskText(String taskText) {
        this.taskText = taskText;
    }

    public String getAssignedTo() {
        return assignedTo;
    }

    public void setAssignedTo(String assignedTo) {
        this.assignedTo = assignedTo;
    }

    public boolean isTaskCompleted() {
        return taskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        this.taskCompleted = taskCompleted;
    }

    @Override
    public String toString() {
        return "Task{" +"\n"+
                " taskId= " + taskId +",\n"+
                " taskTitle= " + taskTitle +",\n" +
                " taskText= " + taskText + ",\n" +
                " assignedTo= " + assignedTo + ",\n" +
                " taskCompleted= " + taskCompleted +",\n"+
                '}'+"\n";
    }
}
