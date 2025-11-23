import java.sql.*;
import java.util.ArrayList;

public class TaskRepository {
    private static final String DB_URL = "jdbc:sqlite:data/tasks.db";

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void initialize() {
        String sql = """
        CREATE TABLE IF NOT EXISTS tasks (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT NOT NULL,
            completed BOOLEAN NOT NULL
        );
        """;

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.execute();
            System.out.println("Database ready.");

        } catch (SQLException e) {
            System.out.println("Database init error: " + e.getMessage());
        }
    }

    public static void addTask(Task task) {
        String sql = "INSERT INTO tasks (title, completed) VALUES (?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS)) {

            pstmt.setString(1, task.getTitle());
            pstmt.setBoolean(2, task.getCompleted());

            pstmt.executeUpdate();

            ResultSet keys = pstmt.getGeneratedKeys();
            if (keys.next()) {
                int id = keys.getInt(1);
                task.setId(id);
            }

            System.out.println("Task saved into database.");

        } catch (SQLException e) {
            System.out.println("Error saving task: " + e.getMessage());
        }
    }

    public static void removeTask(int id) {
        String sql = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setInt(1, id);

            pstmt.executeUpdate();

            System.out.println("Task removed from database.");

        } catch (SQLException e) {
            System.out.println("Error removing task: " + e.getMessage());
        }
    }

    public static ArrayList<Task> listAllTasks() {
        String sql = "SELECT * FROM tasks";
        ArrayList<Task> taskList = new ArrayList<>();

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql);
             ResultSet rs = pstmt.executeQuery()) {

            while (rs.next()) {
                int id = rs.getInt("id");
                String title = rs.getString("title");
                boolean completed = rs.getBoolean("completed");

                taskList.add(new Task(id, title, completed));
            }

        } catch (SQLException e) {
            System.out.println("Error listing all tasks: " + e.getMessage());
        }
        return taskList;
    }
}