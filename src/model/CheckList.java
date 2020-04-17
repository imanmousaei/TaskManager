package model;

import IO.*;

import java.io.PrintStream;
import java.util.ArrayList;
import java.util.Scanner;



public class CheckList extends TimedTask {
    private ArrayList<NormalTask> list = new ArrayList<>();

    public CheckList(int taskId, String title, String description, Date deadline) {
        super(taskId, title, description, deadline);
    }

    public CheckList(NormalTask normalTask, Date deadline) {
        super(normalTask, deadline);
    }

    public CheckList(TimedTask timedTask) {
        super(timedTask.getTaskId(), timedTask.getTitle(), timedTask.getDescription(), timedTask.getDeadline());
    }

    public void addItem(NormalTask task) {
        list.add(task);
    }

    public NormalTask readItem(int index) {
        return list.get(index);
    }

    public void removeItem(int index){
        list.remove(index);
    }

    public int getTaskIndex(){
        return list.size();
    }

    @Override
    public String toString() {
        String string = "taskId = " + taskId +
                " -> CheckList{" +
                " title = " + title +
                ", description = " + description +
                ", status = " + status +
                " }\n";
        for (NormalTask tt : list) {
            string = string + "\t" + tt.toString() + "\n";
        }
        return string;
    }




    @Override
    protected void printEditHelp(PrintStream out) {
        super.printEditHelp(out);
        out.print("5.goto items");
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
        if (editId <= 4) {
            super.processEditId(editId, cin, out);
        }
        else  {
            TaskIO.printHelp(out);
            ItemIO.processInputCheckList(this,cin,out);
        }
    }


}
