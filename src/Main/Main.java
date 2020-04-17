package Main;

import IO.TaskIO;
import model.*;

import java.io.PrintStream;
import java.util.ArrayList;

public class Main {
    public static ArrayList<NormalTask> tasks = new ArrayList<>();


    public static void main(String[] args) {
        TaskIO.printHelp(System.out);

        TaskIO.processInput(System.in, System.out);

    }

    public static void addTask(NormalTask task) {
        tasks.add(task);
    }

    public static void printAllTasks(PrintStream out) {
        for (NormalTask t : tasks) {
            t.printTask(out);
        }
    }

    public static int getTaskId() {
        return tasks.size();
    }
}
