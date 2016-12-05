package screens;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SQLhandler {

	Connection connect = null;
	Statement state;

	public SQLhandler() throws ClassNotFoundException {
		Class.forName("org.sqlite.JDBC");

		try {
			connect = DriverManager.getConnection("jdbc:sqlite:project.db");
			state = connect.createStatement();
			state.setQueryTimeout(30);
		} catch (SQLException e) {
			System.err.println(e.getMessage());
		}
	}

	public boolean Insert(String Statement) {
		try {
			state.executeUpdate(Statement);
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		}

		return true;
	}

	public ResultSet Query(String target) throws SQLException {
		ResultSet result = null;

		result = state.executeQuery(target);

		return result;

	}
}
