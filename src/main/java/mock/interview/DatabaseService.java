package mock.interview;

import org.springframework.stereotype.Service;
import java.sql.*;


@Service
public class DatabaseService {
    private static final String URL = "jdbc:postgresql://localhost:5432/interviewdb";
    private static final String USER = "your_username";
    private static final String PASSWORD = "your_password";


    public String getQuestionByRole(String role) {
        String question = "No question found for this role.";
        try (Connection conn = DriverManager.getConnection(URL, USER, PASSWORD);
             PreparedStatement stmt = conn.prepareStatement("SELECT question FROM interview_questions WHERE role = ? LIMIT 1")) {
            stmt.setString(1, role);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                question = rs.getString("question");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return question;
    }
}




