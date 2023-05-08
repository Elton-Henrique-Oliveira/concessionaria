package css_dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;

public class ConexaoDAO {
	
	public Connection conectaBD() {
		Connection conn = null;
		
		try {
			String url = "jdbc:mysql://mysql465.umbler.com:41890/tequila?user=tequilaadm&password=elton1234";
			conn = DriverManager.getConnection(url);
		} catch (SQLException error) {
			JOptionPane.showMessageDialog(null, error.getMessage());
		}
		return conn;
	}
	
}
