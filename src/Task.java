public class Task {
    private String title;
    private boolean completed;

    // Constructor
    public Task(String title, boolean completed) {
        setTitle(title);
        setCompleted(completed);
    }

    // Override toString method
    public String toString() {
        return getTitle() + ". | Completed: " + getCompleted();
    }

    // Getters and Setters
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
