import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        TaskRepository.initialize();

        TodoApp app = new TodoApp();

        app.run();
    }
}