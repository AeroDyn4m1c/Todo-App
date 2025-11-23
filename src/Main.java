import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Task.tasks = new ArrayList<Task>();

        Task.tasks.add(new Task("Finish project.", false));
        Task.tasks.add(new Task("Push to repository.", false));
        Task.tasks.add(new Task("Test functions.", true));

        Task.printTasks();
    }
}
