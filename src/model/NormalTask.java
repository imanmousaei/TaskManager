package model;

import java.io.PrintStream;
import java.util.Scanner;

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

    protected void printEditHelp(PrintStream out) {
        out.print("What do you want to edit? 1.title 2.description 3.status ");
    }

    public void editTask(Scanner cin, PrintStream out) {
        printEditHelp(out);
        out.println();
        int editId = cin.nextInt();
        processEditId(editId, cin, out);
        out.println("Task edited successfully.");
    }

    protected void processEditId(int editId, Scanner cin, PrintStream out) {
        if (editId == 1) {
            out.println("Enter the new title : ");
            this.title = cin.next();
        }
        else if (editId == 2) {
            out.println("Enter the new description : ");
            this.description = cin.nextLine();
        }
        else if (editId == 3) {
            out.println("Choose the new status : 1.completed 2.incomplete 3.ignored");
            int st = cin.nextInt();
            if (st == 1) {
                this.status = TaskStatus.completed;
            }
            else if (st == 2) {
                this.status = TaskStatus.incomplete;
            }
            else if (st == 3) {
                this.status = TaskStatus.ignored;
            }
        }
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
