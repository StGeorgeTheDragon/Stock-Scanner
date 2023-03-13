import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;

public class db {
	//variables for connecting and getting results used mostly in GUI class
	ResultSet rs;
	Connection con;
	Statement st;
	ResultSetMetaData md;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//calls the class GUI and runs it here
		new stockScanner();
		
		
	}
	
	//method for database connection run in the db class constructor
	public  db()
		{
			connect();
		}
	//database connection method
	public void connect()
	{
		 
		try {
			
			String databaseURL = "jdbc:ucanaccess://TriadProInnovators.accdb";
			con = DriverManager.getConnection(databaseURL);
			st = con.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE,ResultSet.CONCUR_UPDATABLE);
			String sql ="select * from DailyStockPrice";
			rs = st.executeQuery(sql);
			
			
			System.out.println("Connected to MS access database.");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
