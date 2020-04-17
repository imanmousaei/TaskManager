package IO;

import Main.Main;
import model.CheckList;
import model.Date;
import model.NormalTask;
import model.TaskStatus;
import model.TimedTask;

import java.io.*;
import java.util.*;

public class FileIO {
    private String fileName;
    private File file;
    private Scanner scanner;
    boolean isNewFile = false;
    private String hashedPassword;
    private String splitBy = " ~` ";

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
            hashedPassword = Main.hash(pass);
            return scanner.nextLine().equals(hashedPassword);
        }
    }

    public ArrayList<NormalTask> readAllTasksFromFile() {
        ArrayList<NormalTask> readTasks = new ArrayList<>();
        if(isNewFile){
            return readTasks;
        }
        while (scanner.hasNextLine()) {
            String data = scanner.nextLine();
            readTasks.add(extractTask(data));
        }
        return readTasks;
    }

    private NormalTask extractTask(String data) {
        if (data.charAt(0) == 'N') {
            return extractNormalTask(data);
        }
        else if (data.charAt(0) == 'T') {
            return extractTimedTask(data);
        }
        else if (data.charAt(0) == 'C') {
            return extractCheckList(data);
        }
        return null;
    }


    private NormalTask extractNormalTask(String s) {
        String[] data = s.split(splitBy,5);
        int id = Integer.parseInt(data[1]);
        TaskStatus state = parseStatus(data[4]);
        return new NormalTask(id, data[2], data[3], state);
    }

    private TimedTask extractTimedTask(String s) {
        String[] data = s.split(splitBy,11);
        int year = Integer.parseInt(data[5]);
        int month = Integer.parseInt(data[6]);
        int day = Integer.parseInt(data[7]);
        int hour = Integer.parseInt(data[8]);
        int minute = Integer.parseInt(data[9]);
        int second = Integer.parseInt(data[10]);
        Date date = new Date(year, month, day, hour, minute, second);

        return new TimedTask(extractNormalTask(s),date);
    }

    private CheckList extractCheckList(String s) {
        ArrayList<NormalTask> list = new ArrayList<>();
        String input = scanner.nextLine();
        while(!input.equals("</checklist>")){
            list.add(extractTask(input));
            input = scanner.nextLine();
        }
        return new CheckList(extractTimedTask(s),list);
    }

    private TaskStatus parseStatus(String status) {
        if (status.equals("completed")) {
            return TaskStatus.completed;
        }
        else if (status.equals("incomplete")) {
            return TaskStatus.incomplete;
        }
        else if (status.equals("ignored")) {
            return TaskStatus.ignored;
        }
        return null;
    }

    public void clearFile(){
        writeToFile(hashedPassword + "\n");
    }

    public void writeAllTasksToFile(ArrayList<NormalTask> tasksToWrite) {
        for (NormalTask t : tasksToWrite) {
            if (t.getClass() == CheckList.class) {
                appendCheckList((CheckList) t);
            }
            else if (t.getClass() == TimedTask.class) {
                appendTimedTask((TimedTask) t);
            }
            else {
                appendNormalTask(t);
            }
        }
    }


    private void appendNormalTask(NormalTask t) {
        appendToFile("N" + splitBy + t.getTaskId() + splitBy + t.getTitle() + splitBy +
                t.getDescription() + splitBy + t.getStatus() + "\n");
    }

    private void appendTimedTask(TimedTask t) {
        appendToFile("T" + splitBy + t.getTaskId() + splitBy + t.getTitle() + splitBy + t.getDescription() + splitBy +
                t.getStatus() + splitBy + t.getDeadline().toStringSplitBy(splitBy) + "\n");
    }

    private void appendCheckList(CheckList t) {
        appendToFile("C" + splitBy + t.getTaskId() + splitBy + t.getTitle() + splitBy + t.getDescription() + splitBy +
                t.getStatus() + splitBy + t.getDeadline().toStringSplitBy(splitBy) + "\n");
        writeAllTasksToFile( t.getAllItems());
        appendToFile("</checklist>\n");
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
