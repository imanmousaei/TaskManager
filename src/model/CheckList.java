package model;

import java.util.ArrayList;

public class CheckList extends NormalTask {
    private ArrayList<TimedTask> list;

    public CheckList(String title, String description) {
        super(title,description);
    }

    public void addItem(TimedTask task) {
        list.add(task);
    }

    public TimedTask readItem(int index) {
        return list.get(index);
    }

}
