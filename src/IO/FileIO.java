package IO;

import Main.Main;
import model.CheckList;
import model.NormalTask;
import model.TimedTask;

import java.io.*;
import java.util.*;

public class FileIO {
    private String fileName;
    private File file;
    private Scanner scanner;
    boolean isNewFile = false;
    private String hashedPassword;

    public FileIO(String fileName) {
        this.fileName = fileName;
        this.file = new File(fileName);
        try {
            scanner = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            createFile();
            isNewFile = true;
        }
    }

    public boolean isPasswordCorrect(String pass) {
        if (isNewFile) {
            hashedPassword = Main.hash(pass);
            writeToFile(hashedPassword);
            return true;
        }
        else {
            return scanner.nextLine().equals(Main.hash(pass));
        }
    }

    public ArrayList<NormalTask> readAllTasksFromFile() {
        ArrayList<NormalTask> readTasks = new ArrayList<>();
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
//            readTasks.add(get) TODO
        }
        return readTasks;
    }

    public void writeAllTasksToFile(ArrayList<NormalTask> tasksToWrite) {
        writeToFile(hashedPassword + "\n");
        for (NormalTask t : tasksToWrite) {
            if (t.getClass() == CheckList.class) {
                writeCheckList((CheckList) t);
            }
            else if (t.getClass() == TimedTask.class) {
                writeTimedTask((TimedTask) t);
            }
            else {
                writeNormalTask(t);
            }
        }
    }


    private void writeNormalTask(NormalTask t) {
        appendToFile("NormalTask ~ " + t.getTaskId() + " ~ " + t.getTitle() + " ~ " +
                t.getDescription() + " ~ " + t.getStatus() + "\n");
    }

    private void writeTimedTask(TimedTask t) {
        appendToFile("TimedTask ~ " + t.getTaskId() + " ~ " + t.getTitle() + " ~ " + t.getDescription() + " ~ " +
                t.getStatus() + " ~ " + t.getDeadline().toStringSimple() + "\n");
    }

    private void writeCheckList(CheckList t) {
        appendToFile("CheckList ~ " + t.getTaskId() + " ~ " + t.getTitle() + " ~ " + t.getDescription() + " ~ " +
                t.getStatus() + " ~ " + t.getDeadline().toStringSimple() + "\n");
        writeAllTasksToFile(t.getAllItems());
    }

    public int readFirstInt() {
        Scanner reader = null;
        try {
            reader = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
            return -1;
        }
        if (reader.hasNextInt()) {
            return reader.nextInt();
        }
        else {
            return 0;
        }
    }

    public void createFile() {
        try {
            file.createNewFile();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void writeToFile(String str) {
        FileWriter myWriter;
        try {
            myWriter = new FileWriter(fileName);
            myWriter.write(str);
            myWriter.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void appendToFile(String textToAppend) {
        BufferedWriter writer = null;
        try {
            writer = new BufferedWriter(
                    new FileWriter(fileName, true)  //Set true for append mode
            );
            writer.write(textToAppend);
            writer.close();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public boolean found(String str) {
        Scanner sc = null;
        try {
            sc = new Scanner(file);
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        while (sc.hasNext()) {
            String data = sc.next();
            if (data.equals(str)) {
                return true;
            }
        }
        return false;
    }


}
