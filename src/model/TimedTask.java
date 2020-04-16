package model;

import java.io.PrintStream;

public class TimedTask extends NormalTask {
    protected Date deadline = new Date();

    public TimedTask() {
    }

    public TimedTask(int taskId, String title, String description, Date deadline) {
        super(taskId, title, description);
        this.deadline = deadline;
    }

    public TimedTask(NormalTask normalTask, Date deadline) {
        super(normalTask.getTaskId(), normalTask.getTitle(), normalTask.getDescription());
        this.deadline = deadline;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    @Override
    public String toString() {
        return "taskId = " + taskId +
                " -> TimedTask{" +
                " title = " + title +
                ", description = " + description +
                ", status = " + status +
                ", deadline = " + deadline.toString() +
                " }";
    }

}
