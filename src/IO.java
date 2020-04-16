import model.*;

import java.io.InputStream;
import java.io.OutputStream;
import java.io.PrintStream;
import java.util.Scanner;

public class IO {
    public static void printHelp(PrintStream out) {
        out.println(
                "Create New Task: " + Commands.CREATE + " -" +
                        Commands.NormalTask + "/" + Commands.TimedTask + "/" + Commands.CheckList +
                        " \nRemove: " + Commands.REMOVE + " taskId" +
                        " \nEdit: " + Commands.EDIT + " taskId" +
                        " \nPrint All Tasks : " + Commands.PrintAllTasks
        );
    }

    public static void processInput(InputStream in, PrintStream out) {
        Scanner cin = new Scanner(in);
        while(cin.hasNext()) {
            String input = cin.next();
            if (input.equals(Commands.CREATE)) {
                processCreate(cin, out);
            }
            else if (input.equals(Commands.REMOVE)) {
                processRemove(cin, out);
            }
            else if (input.equals(Commands.EDIT)) {
                processEdit(cin, out);
            }
        }
    }

    private static void processEdit(Scanner cin, PrintStream out) {
        int taskId = cin.nextInt();
        out.println(Main.tasks.get(taskId).toString());
    }

    private static void processRemove(Scanner cin, PrintStream out) {
        int taskId = cin.nextInt();
        Main.tasks.remove(taskId);
        out.println("Task " + taskId + " removed successfully.");
    }

    private static void processCreate(Scanner cin, PrintStream out) {
        String taskType = cin.next();

        taskType = taskType.substring(1);
        if (taskType.equals(Commands.NormalTask)) {
            Main.addNormalTask(getNormalTaskFromInput(cin, out));
        }
        else if (taskType.equals(Commands.TimedTask)) {
            Main.addTimedTask(getTimedTaskFromInput(cin, out));
        }
        else if (taskType.equals(Commands.CheckList)) {
            Main.addCheckList(getCheckListFromInput(cin, out));
        }
        else if (taskType.equals(Commands.PrintAllTasks)) {
            Main.printAllTasks(out);
        }
    }

    private static NormalTask getNormalTaskFromInput(Scanner cin, PrintStream out) {
        out.println("Enter Task Title : ");
        String title = cin.next();
        out.println("Enter Task Description : ");
        String description = cin.next();
        NormalTask normalTask = new NormalTask(title, description);
        return normalTask;
    }

    private static TimedTask getTimedTaskFromInput(Scanner cin, PrintStream out) {
        NormalTask normalTask = getNormalTaskFromInput(cin, out);
        out.print("Enter Deadline Deadline in the following format: Year month day hour minute second e.g. 2020 7 13 11 30 0");
        int year = cin.nextInt();
        int month = cin.nextInt();
        int day = cin.nextInt();
        int hour = cin.nextInt();
        int minute = cin.nextInt();
        int second = cin.nextInt();
        return new TimedTask(normalTask, new Date(year, month, day, hour, minute, second));
    }

    private static CheckList getCheckListFromInput(Scanner cin, PrintStream out) {
        return (CheckList) getNormalTaskFromInput(cin, out);
    }


}
