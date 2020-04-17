package Main;

import IO.FileIO;
import IO.TaskIO;
import model.NormalTask;

import java.io.PrintStream;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Scanner;

public class Main {
    public static ArrayList<NormalTask> tasks = new ArrayList<>();
    private static String username , password;


    public static void main(String[] args) {
        System.out.println("Enter Username : ");
        Scanner cin = new Scanner(System.in);
        username = cin.nextLine();

        System.out.println("Enter Password : ");
        password = cin.nextLine();

        FileIO userFile = new FileIO(  username + ".txt");
        if(userFile.isPasswordCorrect(hash(password))) {
            tasks = userFile.readAllTasksFromFile();
        }
        else{
            try {
                throw new IllegalAccessException("Wrong Password");
            }
            catch (IllegalAccessException e) {
                e.printStackTrace();
            }
            return;
        }
        TaskIO.printHelp(System.out);

        TaskIO.processInput(System.in, System.out);

        userFile.writeAllTasksToFile(tasks);

    }

    public static void addTask(NormalTask task) {
        tasks.add(task);
    }

    public static void printAllTasks(PrintStream out) {
        int taskId = 0;
        for (NormalTask t : tasks) {
            t.setTaskId(taskId);
            t.printTask(out);
            taskId++;
        }
    }

    public static int getTaskId() {
        return tasks.size();
    }

    public static String hash(String pass) {
        MessageDigest md = null;
        try {
            md = MessageDigest.getInstance("MD5");
        }
        catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        //Add password bytes to digest
        md.update(pass.getBytes());
        byte[] bytes = md.digest();
        //This bytes[] has bytes in decimal format;
        //Convert it to hexadecimal format
        StringBuilder sb = new StringBuilder();
        for(int i=0; i< bytes.length ;i++)
        {
            sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
        }
        return sb.toString();
    }
}
