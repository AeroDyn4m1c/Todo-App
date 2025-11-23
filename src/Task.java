public class Task {
    private int id;
    private String title;
    private boolean completed;

    // Constructors
    public Task(String title, boolean completed) {
        setTitle(title);
        setCompleted(completed);
    }

    public Task(int id, String title, boolean completed) {
        setId(id);
        setTitle(title);
        setCompleted(completed);
    }

    // Override toString method
    public String toString() {
        return id + ". " + title + " | Completed: " + completed;
    }

    // Getters and Setters
    void setId(int id) {
        this.id = id;
    }

    int getId() {
        return id;
    }

    void setTitle(String title) {
        this.title = title;
    }

    String getTitle() {
        return title;
    }

    void setCompleted(boolean completed) {
        this.completed = completed;
    }

    boolean getCompleted() {
        return completed;
    }
}
