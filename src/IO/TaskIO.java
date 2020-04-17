package IO;

import Main.Main;
import model.*;

import java.io.InputStream;
import java.io.PrintStream;
import java.util.Scanner;

import static model.Date.getDateFromInput;

public class TaskIO {
    public TaskIO(){}
    public static void printHelp(PrintStream out) {
        out.println(
                "Create New Task: " + Commands.CREATE + " -" +
                Commands.NormalTask + "/" + Commands.TimedTask + "/" + Commands.CheckList +
                " \nRemove: " + Commands.REMOVE + " taskId" +
                " \nEdit: " + Commands.EDIT + " taskId" +
                " \nPrint All Tasks : " + Commands.PrintAllTasks +
                " \nSave & Exit : " + Commands.EXIT
        );
    }

    public static void processInput(InputStream in, PrintStream out) {
        Scanner cin = new Scanner(in);
        while (cin.hasNext()) {
            String input = cin.next();
            if (input.equals(Commands.CREATE)) {
                processCreate(cin, out);
            }
            else if (input.equals(Commands.REMOVE)) {
                int taskId = cin.nextInt();
                Main.tasks.remove(taskId);
                out.println("Task " + taskId + " removed successfully.");
            }
            else if (input.equals(Commands.EDIT)) {
                int taskId = cin.nextInt();
                out.println(Main.tasks.get(taskId).toString());
                Main.tasks.get(taskId).editTask(cin, out);
            }
            else if (input.equals(Commands.PrintAllTasks)) {
                Main.printAllTasks(out);
            }
            else if(input.equals(Commands.EXIT)){
                return;
            }
        }
    }

    private static void processCreate(Scanner cin, PrintStream out) {

        String taskType = cin.next();

        taskType = taskType.substring(1);
        if (taskType.equals(Commands.NormalTask)) {
            Main.addTask(getNormalTaskFromInput(Main.getTaskId(), cin, out));
        }
        else if (taskType.equals(Commands.TimedTask)) {
            Main.addTask(getTimedTaskFromInput(Main.getTaskId(), cin, out));
        }
        else if (taskType.equals(Commands.CheckList)) {
            Main.addTask(getCheckListFromInput(Main.getTaskId(), cin, out));
        }
        out.println("Task added successfully.");
    }

    public static NormalTask getNormalTaskFromInput(int taskId, Scanner cin, PrintStream out) {
        out.println("Enter Task Title : ");
        cin.nextLine();
        String title = cin.nextLine();
        out.println("Enter Task Description : ");
        String description = cin.nextLine();
        return new NormalTask(taskId, title, description);
    }

    public static TimedTask getTimedTaskFromInput(int taskId, Scanner cin, PrintStream out) {
        NormalTask normalTask = getNormalTaskFromInput(taskId, cin, out);
        out.println("Enter Deadline in the following format: Year month day hour minute second e.g. 2020 7 13 11 30 0");
        Date date = getDateFromInput(cin);
        return new TimedTask(normalTask, date);
    }

    public static CheckList getCheckListFromInput(int taskId, Scanner cin, PrintStream out) {
        return new CheckList(getTimedTaskFromInput(taskId, cin, out));
    }


}
