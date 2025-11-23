import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class TaskRepository {
    private static final String DB_URL = "jdbc:sqlite:data/tasks.db";

    public static void initialize() {
        String sql = """
        CREATE TABLE IF NOT EXISTS tasks (
            id INTEGER PRIMARY KEY AUTOINCREMENT,
            title TEXT NOT NULL,
            completed BOOLEAN NOT NULL
        );
        """;

        try (Connection conn = connect();
             PreparedStatement stmt = conn.prepareStatement(sql)) {

            stmt.execute();
            System.out.println("Database ready.");

        } catch (SQLException e) {
            System.out.println("Database init error: " + e.getMessage());
        }
    }

    private static Connection connect() throws SQLException {
        return DriverManager.getConnection(DB_URL);
    }

    public static void addTask(Task task) {
        String sql = "INSERT INTO tasks (title, completed) VALUES (?, ?)";

        try (Connection conn = connect();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, task.getTitle());
            pstmt.setBoolean(2, task.getCompleted());

            pstmt.executeUpdate();

            System.out.println("Task saved into database.");

        } catch (SQLException e) {
            System.out.println("Error saving task: " + e.getMessage());
        }
    }
}