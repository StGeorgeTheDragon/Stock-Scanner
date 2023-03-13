import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URL;
import java.net.URLConnection;
import java.sql.SQLException;


public class stockScanner extends db{
	
  String dailyPrice = new String();
  	
	
  
	public stockScanner()
	{
		
		try {
			URL url = new URL("https://www.google.com/finance/quote/TPII:OTCMKTS");
				
				URLConnection urlConn = url.openConnection();
				InputStreamReader instream = new InputStreamReader(urlConn.getInputStream());
				BufferedReader buff = new BufferedReader(instream);
				String price = "Not Found";
				String line = buff.readLine();
				while (line != null) 
				{
					if(line.contains("\"Triad Pro Innovators Inc\",0,\"USD\","))
					{
					int target = line.indexOf("\"Triad Pro Innovators Inc\",0,\"USD\",");
					int deci = line.indexOf(".",target);
					int start = deci;
					while(line.charAt(start) != '[')
					{
						start--;
					}
					price = line.substring(start +1, deci +3);
					}
						
					line = buff.readLine();
					dailyPrice = price;
				}
			System.out.println("Today's current stock quote: " +dailyPrice);
			
			
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try {
			
			System.out.println("Inserting records into the table..."); 
			rs.updateString("Price", dailyPrice);
			rs.insertRow();
			rs.close();
			System.out.println("Record inserted succesfullly!");
			
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		try
		{
			System.out.println("Well I got here so far!!!!");
			//String priceList = rs.getString("Price");
			//System.out.print(priceList);
		}
		catch(Exception e)
		{
			
		}
	}
	
	public void tpiiPriceList()
	{
		
	}
	
}
