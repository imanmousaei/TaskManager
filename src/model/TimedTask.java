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

    @Override
    protected void printEditHelp(PrintStream out) {
        super.printEditHelp(out);
        out.print("4.deadline ");
    }

    @Override
    public void editTask(Scanner cin, PrintStream out) {
        printEditHelp(out);
        out.println();
        int editId = cin.nextInt();
        this.processEditId(editId, cin, out);
    }

    @Override
    protected void processEditId(int editId, Scanner cin, PrintStream out) {
        if (editId <= 3) {
            super.processEditId(editId, cin, out);
        }
        else {
            out.println("Enter new Deadline in the following format: Year month day hour minute second e.g. 2020 7 13 11 30 0");
            this.deadline = getDateFromInput(cin);
        }
    }




}
