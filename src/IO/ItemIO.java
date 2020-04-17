package IO;

import Main.Main;
import model.CheckList;
import model.Commands;

import java.io.PrintStream;
import java.util.Scanner;

public class ItemIO {

    public ItemIO(){}

    public static void processInputCheckList(CheckList checkList, Scanner cin, PrintStream out) {
        String input = cin.next();
        if (input.equals(Commands.CREATE)) {
            processCreate( checkList,cin, out);
        }
        else if (input.equals(Commands.REMOVE)) {
            int index = cin.nextInt();
            checkList.removeItem(index);
            out.println("Item " + index + " removed successfully.");
        }
        else if (input.equals(Commands.EDIT)) {
            int index = cin.nextInt();
            out.println(checkList.readItem(index).toString());
            checkList.readItem(index).editTask(cin, out);
        }
    }

    private static void processCreate(CheckList checkList,Scanner cin, PrintStream out) {
        String taskType = cin.next();

        taskType = taskType.substring(1);
        if (taskType.equals(Commands.NormalTask)) {
            checkList.addItem(TaskIO.getNormalTaskFromInput(checkList.getTaskIndex(), cin, out));
        }
        else if (taskType.equals(Commands.TimedTask)) {
            checkList.addItem(TaskIO.getTimedTaskFromInput(checkList.getTaskIndex(), cin, out));
        }
        else if (taskType.equals(Commands.CheckList)) {
            checkList.addItem(TaskIO.getCheckListFromInput(checkList.getTaskIndex(), cin, out));
        }
        out.println("Task added successfully.");
    }


}
