package model;

import java.io.PrintStream;
import java.util.Scanner;
import static model.Date.getDateFromInput;


public class TimedTask extends NormalTask {
    protected Date deadline = new Date();

    public TimedTask() {
    }

    public TimedTask(int taskId, String title, String description, Date deadline) {
        super(taskId, title, description);
        this.deadline = deadline;
    }

    public TimedTask(NormalTask normalTask, Date deadline) {
        super(normalTask.getTaskId(), normalTask.getTitle(), normalTask.getDescription());
        this.deadline = deadline;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "taskId = " + taskId +
                " -> TimedTask{" +
                " title = " + title +
                ", description = " + description +
                ", status = " + status +
                ", deadline = " + deadline.toString() +
                " }";
    }

    public void editTask(Scanner cin, PrintStream out) {
        super.printEdithelp(out);
        out.println("4.deadline");
        int editId = cin.nextInt();
        if(editId<=3) {
            super.processEditId(editId, cin, out);
        }
        else{
            out.println("Enter new Deadline in the following format: Year month day hour minute second e.g. 2020 7 13 11 30 0");
            Date newDeadline = getDateFromInput(cin);
            this.deadline = newDeadline;
        }
    }

}
