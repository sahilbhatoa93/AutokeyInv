package controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.mysql.jdbc.Connection;

public class CycleCountResetJob implements Job{
	

	private static String url;
	private static String dbName;
	private static String usr;
	private static String password;
	
	public CycleCountResetJob(String dbName,String password,String url,String usr)
	{
	this.dbName=dbName;
	this.password=password;
	this.url="jdbc:mysql://"+url+"/";
	this.usr=usr;
		
		
	}
	
	public CycleCountResetJob()
	{
		
		
	}
	
	
	
public void execute(JobExecutionContext context) throws JobExecutionException {
	    
		try{
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query2="update loginprofile set cycleCountCheck='0';";
	    		PreparedStatement pst = con.prepareStatement(query2);
			    pst.executeUpdate(query2);
		     con.close();
		     }
		catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	

}
