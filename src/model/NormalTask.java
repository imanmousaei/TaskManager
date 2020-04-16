package model;

import java.io.PrintStream;

public class NormalTask {
    protected String title;
    protected String description;
    protected TaskStatus status;
    protected int taskId;

    public NormalTask() {
    }

    public NormalTask(int taskId, String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.incomplete;
        this.taskId = taskId;
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "taskId = " + taskId +
                " -> NormalTask{" +
                " title = " + title +
                ", description = " + description +
                ", status = " + status +
                " }";
    }

    public void printTask(PrintStream out) {
        out.println(this.toString());
    }

}
