import java.util.ArrayList;
import java.util.Scanner;

public class TodoApp {
    // Define list of objects tasks from class Task
    private ArrayList<Task> tasks = new ArrayList<Task>();
    private Scanner scanner = new Scanner(System.in);

    public void addTask(String title) {
        Task task = new Task (title, false);
        TaskRepository.addTask(task);
    }

    public void removeTask(int index) {
        tasks.remove(index);
        System.out.println("Task removed.");
    }

    public void listTasks() {
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    public void markCompleted(int index) {
        tasks.get(index).setCompleted(true);
        System.out.println("Task marked as completed.");
    }

    public void run() {
        int index = 0;
        System.out.println("Welcome to the TODO App!");

        while (true) {
            System.out.println("Select one of the options with the keyboard (1-5):");
            System.out.println("""
                1. Add a task.
                2. Remove a task.
                3. List all tasks.
                4. Mark task as completed.
                5. Exit the program.""");

            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {
                case 1: // Add a task
                    System.out.println("Write the title of the task:");
                    String title = scanner.nextLine();

                    System.out.println("Adding the task...");

                    addTask(title);
                    break;

                case 2: // Remove a task
                    System.out.println("What task would you like to remove? (Task number)");
                    listTasks();

                    index = scanner.nextInt();
                    System.out.println("Removing task...");

                    removeTask(index - 1);
                    break;

                case 3: // List all tasks
                    System.out.println("Showing all the tasks...");
                    listTasks();
                    break;

                case 4: // Mark task as completed
                    System.out.println("What task has been completed? (Task number)");
                    listTasks();

                    index = scanner.nextInt();
                    System.out.println("Marking task as completed...");
                    markCompleted(index - 1);
                    break;

                case 5:
                    System.out.println("Exiting the program...");
                    return;
            }
        }
    }
}
