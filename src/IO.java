import model.Commands;

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
                        " \nEdit: " + Commands.EDIT + " taskId"
        );
    }

    public static void processInput(InputStream in, PrintStream out) {
        Scanner cin = new Scanner(in);
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

    private static void processEdit(Scanner cin, PrintStream out) {
        int taskId = cin.nextInt();
        out.println(Main.tasks.get(taskId).toString());
    }

    private static void processRemove(Scanner cin, PrintStream out) {
        int taskId = cin.nextInt();
        Main.tasks.remove(taskId);
        out.println("Task " + taskId + " removed successfully." );
    }

    private static void processCreate(Scanner cin, PrintStream out) {
        // TODO
    }


}
