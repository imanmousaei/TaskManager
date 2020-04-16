package model;

import java.util.ArrayList;

public class CheckList extends NormalTask {
    private ArrayList<TimedTask> list = new ArrayList<>();

    public CheckList(String title, String description) {
        super(title,description);
    }

    public void addItem(TimedTask task) {
        list.add(task);
    }

    public TimedTask readItem(int index) {
        return list.get(index);
    }

    @Override
    public String toString() {
        String string = "CheckList{" +
                "title = " + title +
                ", description = " + description +
                ", status = " + status +
                '}';
        for(TimedTask tt:list){
            string = string.concat(tt.toString());
        }
        return string;
    }
}
