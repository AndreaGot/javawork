import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class AS400Connect {

	AS400Connect() throws SQLException {
		Connection con = null;
		FileOutputStream out = null;
		try {
			out = new FileOutputStream("D:\\logResult.txt");
		} catch (FileNotFoundException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		
		try {
			Class.forName("com.ibm.as400.access.AS400JDBCDriver");
		} catch (ClassNotFoundException e) {
			System.out.println(e);
			System.exit(0);
		}


		try {
			con = DriverManager.getConnection("jdbc:as400://IP_SERVER;naming=sql;errors=full;codepage=CODEPAGE;", "USER", "PASSWORD");

			PreparedStatement stmt = con.prepareStatement("QUERY TO EXECUTE");                   

			ResultSet rs = stmt.executeQuery();
			while(rs.next()) {

				String field1 = rs.getString(1);
				byte[] b = field1.getBytes();
				out.write(b);
				System.out.println(field1);

			}

			//  may have to put these on a try-catch block
			rs.close();
			stmt.close();
			con.close();
		} catch (Exception e) {

			e.printStackTrace();

		}
		finally
		{
			try {
				out.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}

	// within some method

	public static void main(String[] args) {
		try {
			new AS400Connect();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
