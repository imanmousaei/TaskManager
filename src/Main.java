import model.*;

import java.io.PrintStream;
import java.util.ArrayList;

public class Main {
    public static ArrayList<NormalTask> tasks = new ArrayList<>();


    public static void main(String[] args) {
        IO.printHelp(System.out);

        IO.processInput(System.in, System.out);

    }

    public static void addNormalTask(NormalTask task) {
        tasks.add(task);
    }

    public static void addTimedTask(TimedTask task) {
        tasks.add(task);
    }

    public static void addCheckList(CheckList task) {
        tasks.add(task);
    }

    public static void printAllTasks(PrintStream out) {
        for (NormalTask t : tasks) {
            t.printTask(out);
        }
    }

    public static int getTaskId(){
        return tasks.size();
    }
}
