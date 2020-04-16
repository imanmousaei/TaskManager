package model;

import java.util.ArrayList;

public class CheckList extends NormalTask {
    private ArrayList<TimedTask> list = new ArrayList<>();

    public CheckList(int taskId, String title, String description) {
        super(taskId, title, description);
    }

    public CheckList(NormalTask normalTask) {
        super(normalTask.getTaskId(), normalTask.getTitle(), normalTask.getDescription());
    }

    public void addItem(TimedTask task) {
        list.add(task);
    }

    public TimedTask readItem(int index) {
        return list.get(index);
    }

    @Override
    public String toString() {
        String string = "taskId = " + taskId +
                " -> CheckList{" +
                " title = " + title +
                ", description = " + description +
                ", status = " + status +
                " }\n";
        for (TimedTask tt : list) {
            string = string + "\t" + tt.toString() + "\n";
        }
        return string;
    }
}
