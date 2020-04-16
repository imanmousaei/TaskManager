package model;

public class NormalTask{
    protected String title;
    protected String description;
    protected TaskStatus status;

    public NormalTask() {
    }

    public NormalTask(String title, String description) {
        this.title = title;
        this.description = description;
        this.status = TaskStatus.incomplete;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public TaskStatus getStatus() {
        return status;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setStatus(TaskStatus status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "NormalTask{" +
                "title='" + title + " : " +
                ", description='" + description + " , " +
                ", status=" + status +
                '}';
    }

}
