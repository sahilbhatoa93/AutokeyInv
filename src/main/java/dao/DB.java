package dao;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Blob;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Random;

import org.quartz.CronScheduleBuilder;
import org.quartz.JobBuilder;
import org.quartz.JobDetail;
import org.quartz.Scheduler;
import org.quartz.SchedulerException;
import org.quartz.SchedulerFactory;
import org.quartz.Trigger;
import org.quartz.TriggerBuilder;
import org.quartz.impl.StdSchedulerFactory;
import model.Alert;
import model.DiscrepanciesObject;
import model.Inventory;
import model.Item;
import model.Key;
import model.LogObject;
import model.Login;
import model.SearchObject;
import model.TransferObject;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Multimap;
import com.itextpdf.text.log.SysoLogger;
import com.mysql.jdbc.Connection;

import controller.CycleCountResetJob;
import controller.InventoryCountJob;
public class DB {
	
	
	public DB(String dbName,String password,String url,String usr)
	{
	this.dbName=dbName;
	this.password=password;
	this.url="jdbc:mysql://"+url+"/";
	this.usr=usr;
		
		
	}
	
	public DB()
	{
		
		
	}
	
	private static String url;
	private static String dbName;
	private static String usr;
	private static String password;
	Scheduler scheduler ;
	public ArrayList<String> authorizeCredentials(Login l)
	{
		ArrayList<String>result=new ArrayList<String>();
		
		try{
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     Statement stmt = con.createStatement();
		     String query = "select * from loginprofile where LoginID='"+l.getLoginID()+"';";
		     String query2 = "select * from loginprofile where LoginID='"+l.getLoginID()+"' and password='"+l.getPassword()+"';";
		     String query3 = "select * from loginprofile where LoginID='"+l.getLoginID()+"' and password='"+l.getPassword()+"' and profile='"+l.getRole()+"';";
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     PreparedStatement preparedStatement2 = con.prepareStatement(query2);
		     PreparedStatement preparedStatement3 = con.prepareStatement(query3);
	         ResultSet rs = preparedStatement.executeQuery();
	         ResultSet rs2 = preparedStatement2.executeQuery();
	         ResultSet rs3 = preparedStatement3.executeQuery();
	         rs.last();
	         rs2.last();
	         rs3.last();
	         result.add(String.valueOf(rs.getRow()));
	         result.add(String.valueOf(rs2.getRow()));
	         result.add(String.valueOf(rs3.getRow()));
	         takeLog(l.getLoginID(), "SignIn", null);
	         stmt.close();
			 con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return result; 
		
		
	}
	
	public boolean decrementItemNumber(String conditionLoop,Key k,String currentUser)
	{
		String activeInventory="";
		boolean result=false;
		if (k.getInvCode().equals(""))
		{
			if  (!(k.getItemCodePart1().equals("")))
			{
				 activeInventory=getInventoryNameCode(k.getItemCodePart1());
			}
			else if (!(k.getItemCodePart2().equals("")))
			{
				 activeInventory=getInventoryNameCode(k.getItemCodePart2());
			}
			else if (!(k.getItemCodePart3().equals("")))
			{
				 activeInventory=getInventoryNameCode(k.getItemCodePart3());
			}
			else if (!(k.getItemCodePart4().equals("")))
			{
				 activeInventory=getInventoryNameCode(k.getItemCodePart4());
			}
		}
		else
		{
			 activeInventory=k.getInvCode();
			
		}
		ArrayList<String>itemCodeList=new ArrayList<String>();
		itemCodeList.add(k.getItemCodePart1());
		itemCodeList.add(k.getItemCodePart2());
		itemCodeList.add(k.getItemCodePart3());
		itemCodeList.add(k.getItemCodePart4());
		itemCodeList.add(k.getItemCodePart5());
		ArrayList<String>categoryList=new ArrayList<String>();
		ArrayList<String>subCategoryList=new ArrayList<String>();
		ArrayList<String>brandList=new ArrayList<String>();
		ArrayList<String>modelList=new ArrayList<String>();
		if (k.getCategoryNamePart1() ==null)
		categoryList.add("");
		else
			categoryList.add(k.getCategoryNamePart1());	
		if (k.getCategoryNamePart2() ==null)
			categoryList.add("");
			else
				categoryList.add(k.getCategoryNamePart2());	
		if (k.getCategoryNamePart3() ==null)
			categoryList.add("");
			else
				categoryList.add(k.getCategoryNamePart3());	
		if (k.getCategoryNamePart4() ==null)
			categoryList.add("");
			else
				categoryList.add(k.getCategoryNamePart4());	
		if (k.getCategoryNamePart5() ==null)
			categoryList.add("");
			else
				categoryList.add(k.getCategoryNamePart5());	
		
		
		if (k.getSubCategoryNamePart1() ==null)
			subCategoryList.add("");
			else
				subCategoryList.add(k.getSubCategoryNamePart1());	
			if (k.getSubCategoryNamePart2() ==null)
				subCategoryList.add("");
				else
					subCategoryList.add(k.getSubCategoryNamePart2());	
			if (k.getSubCategoryNamePart3() ==null)
				subCategoryList.add("");
				else
					subCategoryList.add(k.getSubCategoryNamePart3());	
			if (k.getSubCategoryNamePart4() ==null)
				subCategoryList.add("");
				else
					subCategoryList.add(k.getSubCategoryNamePart4());	
			if (k.getSubCategoryNamePart5() ==null)
				subCategoryList.add("");
				else
					subCategoryList.add(k.getSubCategoryNamePart5());	
		
			if (k.getBrandPart1() ==null)
				brandList.add("");
				else
					brandList.add(k.getBrandPart1());	
				if (k.getBrandPart2() ==null)
					brandList.add("");
					else
						brandList.add(k.getBrandPart2());	
				if (k.getBrandPart3() ==null)
					brandList.add("");
					else
						brandList.add(k.getBrandPart3());	
				if (k.getBrandPart4() ==null)
					brandList.add("");
					else
						brandList.add(k.getBrandPart4());	
				if (k.getBrandPart5() ==null)
					brandList.add("");
					else
						brandList.add(k.getBrandPart5());
				
				if (k.getModelPart1() ==null)
					modelList.add("");
					else
						modelList.add(k.getModelPart1());	
					if (k.getModelPart2() ==null)
						modelList.add("");
						else
							modelList.add(k.getModelPart2());	
					if (k.getModelPart3() ==null)
						modelList.add("");
						else
							modelList.add(k.getModelPart3());	
					if (k.getModelPart4() ==null)
						modelList.add("");
						else
							modelList.add(k.getModelPart4());	
					if (k.getModelPart5() ==null)
						modelList.add("");
						else
							modelList.add(k.getModelPart5());
		Random rand=new Random();		
		int generationID=rand.nextInt(1000000)+1;
		for (int i=0;i<itemCodeList.size();i++)
		{
			LogObject object=new LogObject();
			if (!(itemCodeList.get(i).equals("")))
			{
				try{
				     Class.forName("com.mysql.jdbc.Driver").newInstance();
				     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
				     Statement stmt = con.createStatement();
				    String query = "update inventory set noOfItems= GREATEST(0, noOfItems - 1) where itemCode='"+itemCodeList.get(i)+"' and invName='"+activeInventory+"';";
					     stmt.executeUpdate(query);
					     stmt.close();
						 
						 result=true;
						 object.setKeyGenerationID(String.valueOf(generationID));
						 object.setPart1(itemCodeList.get(i));
						 object.setInvFrom(activeInventory);
						 object.setItemCode(itemCodeList.get(i));
						 takeLog(currentUser, "New Key", object);		 
						 con.close();
				     }
				  catch(Exception e)
				  {
					 e.printStackTrace();
				  }	
				}
			
			
			else if (!(categoryList.get(i).equals("")))
			{
				try{
				     Class.forName("com.mysql.jdbc.Driver").newInstance();
				     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
				     Statement stmt = con.createStatement();
				     String query = "update inventory set noOfItems= GREATEST(0, noOfItems - 1) where category='"+categoryList.get(i)+"' and subcategory='"+subCategoryList.get(i)+"' and brand='"+brandList.get(i)+"' and model='"+modelList.get(i)+"' and invName='"+activeInventory+"';";
				     stmt.executeUpdate(query);
				     stmt.close();
					result=true;
					 String query2="select itemCode from inventory where category='"+categoryList.get(i)+"' and subcategory='"+subCategoryList.get(i)+"' and brand='"+brandList.get(i)+"' and model='"+modelList.get(i)+"' and invName='"+activeInventory+"';";
					 PreparedStatement preparedStatement = con.prepareStatement(query2);	
				     ResultSet rs = preparedStatement.executeQuery();
			         String itemCode="";
				     while(rs.next())
			         {
			        	itemCode=rs.getString(1); 
			         }
			         stmt.close();
					 con.close();
					 object.setKeyGenerationID(String.valueOf(generationID));
					 object.setPart1(itemCode);
					 object.setInvFrom(activeInventory);
					 object.setBrand(brandList.get(i));
					 object.setModel(modelList.get(i));
					 object.setCategory(categoryList.get(i));
					 object.setSubCategory(subCategoryList.get(i));
					 takeLog(currentUser, "New Key", object);
					 con.close();
					}
				  catch(Exception e)
				  {
					 e.printStackTrace();
				  }	
				}
		}
	
		return result; 
		
		
	}
	
	public String getInventoryName(String code)
	{
		String invName = "";
		try{
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     Statement stmt = con.createStatement();
		     String query = "select inventoryName from invtable where inventoryCode='"+code+"';";
		    
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
	         while(rs.next())
	        	 invName=rs.getString(1);
	         stmt.close();
			 con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return invName;
		
	}
	
	public String getInventoryNameCode(String code)
	{
		String invName = "";
		try{
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     Statement stmt = con.createStatement();
		     String query = "select invname from inventory where itemcode='"+code+"';";
		    
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
	         while(rs.next())
	        	 invName=rs.getString(1);
	         stmt.close();
			 con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return invName;
		
	}
	
	public String getInventoryCode(String invName)
	{
		String invCode = "";
		try{
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     Statement stmt = con.createStatement();
		     String query = "select inventoryCode from invtable where inventoryName='"+invName+"';";
		    
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
	         while(rs.next())
	        	 invCode=rs.getString(1);
	         stmt.close();
			 con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return invCode;
		
	}
	public ArrayList<Item> searchKey(Item i)
	{
		String itemCode="";
		
		 ArrayList<String> accessList=getCategoryAndSubCategoryList("sahil").get(4);
		 
		 for (int j=0;j<accessList.size();j++)
		 {
			 	String invName=accessList.get(j);
			 	accessList.set(j,"'"+invName+"'");
		 } 
		 String accessListString=accessList.toString().replaceAll("\\[", "").replaceAll("\\]","").replaceAll(","," or invName= ");
		if (i.getItemCode().equals(""))
		{
			itemCode=i.getInvCode()+i.getRack()+i.getShelf()+i.getColumn()+i.getCompartment();
		}
		else
		{
			itemCode=i.getItemCode();
			}	
		ArrayList<Item>result=new ArrayList<Item>();
			try{
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query="";
		     if (!(i.getBrand().equals(""))) 
		     query = "select * from inventory where brand like'%"+i.getBrand()+"%' and model like '%"+i.getModel()+"%' and invName="+accessListString+";";
		     else if (!(i.getCategoryName().equals(""))) 
			     query = "select * from inventory where category='"+i.getCategoryName()+"' and invName="+accessListString+";";
		     else if (!(i.getSubCategoryName().equals(""))) 
			     query = "select * from inventory where category='"+i.getCategoryName()+"' and subcategory='"+i.getSubCategoryName()+"'  and invName="+accessListString+";";
		     else if (!(i.getSku().equals(""))) 
		     {
		    	 if (!(i.getInvName().equals("")))
		    		   query = "select * from inventory where sku='"+i.getSku()+"' and invName='"+i.getInvName()+"';";
		    	 else
		    	 {
		    		
		    		 query = "select * from inventory where sku='"+i.getSku()+"' and invName="+accessListString+";";
		    	 }
		     }
			 else if (!(i.getDescription().equals(""))) 
			     query = "select * from inventory where description like '%"+i.getDescription()+"%'  and invName="+accessListString+";";
		     else if (!(i.getItemCode().equals(""))) 
			     query = "select * from inventory where itemCode = '"+itemCode+"'  and invName="+accessListString+";";
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
		     Blob blob = null;
		     Item item;
		     while (rs.next())
		     {
		    	 ArrayList<String>positionArray;
		    	 item=new Item();
		    	 item.setRestockLimit(rs.getString("restocklimit"));
		    	 item.setBatteryPartNumber(rs.getString("batteryPartNumber"));
		    	 item.setBrand(rs.getString("brand"));
		    	 item.setButtonConfiguration(rs.getString("buttonConfiguration"));
		    	 item.setCategoryName(rs.getString("category"));
		    	 positionArray=getItemPosition(rs.getString("itemCode"),rs.getString("invName"));
		    	 item.setColumn(positionArray.get(2));
		    	 item.setCompartment(positionArray.get(3));
		    	 item.setDescription(rs.getString("description"));
		    	 item.setEmergencyKey(rs.getString("emergencyKey"));
		    	 item.setFfcId(rs.getString("fccid"));
		    	 item.setiC(rs.getString("ic"));
		    	 item.setModel(rs.getString("model"));
		    	 item.setNoOfItems(rs.getString("noOfItems"));
		    	 blob=rs.getBlob("imageURL");
		    	 byte[] imgData=null;
			     try
			     {
			    	 imgData = blob.getBytes(1, (int) blob.length());
			     }
			     catch(NullPointerException e)
			     {
			    	 System.out.println("No Image");
			     }
		    	 item.setImageURLByteArray(imgData);
		    	 item.setInvName(rs.getString("invName"));
		    	 item.setItemCode(rs.getString("itemCode"));
		    	 item.setNoOfButton(rs.getString("noOfButtons"));
		    	 item.setNoOfItems(rs.getString("noOfItems"));
		    	 item.setProductNotes(rs.getString("productnotes"));
		    	 item.setRack(positionArray.get(0));
		    	 item.setShelf(positionArray.get(1));
		    	 item.setSku(rs.getString("sku"));
		    	 item.setTrim(rs.getString("trim"));
		    	 item.setSubCategoryName(rs.getString("subcategory"));
		    	 result.add(item);
		    	 }
		      con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return result;
	}
	
	public ArrayList<String>getItemPosition(String itemCode,String invName)
	{
		ArrayList<String>positionArray=new ArrayList<String>();
		try{
			String invCode="";
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "select inventoryCode from invtable where inventoryName='"+invName+"';"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
		     while (rs.next())
		     {
		    	 invCode=rs.getString(1);
		     }
		     positionArray.add(itemCode.substring(invCode.length(), itemCode.length()-3));
		     positionArray.add(itemCode.substring(invCode.length()+1, itemCode.length()-2));
		     positionArray.add(itemCode.substring(invCode.length()+2,itemCode.length()-1));
		     positionArray.add(itemCode.substring(invCode.length()+3, itemCode.length()));
			 con.close();
			
			 
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		return positionArray;
		
	}
	
	public String updateItem(SearchObject object,String currentUser)
	{
		InputStream inputStream=null;
		InputStream exisitingImageInputStream=null;
		try {
			inputStream = object.getImageURL().getInputStream();
		} catch (IOException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		catch (NullPointerException e1) {
			// TODO Auto-generated catch block
			System.out.println("No Image Found");
		}
		try{
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		      String query = "select imageURL,cycleCount,cycleCountDate from inventory where sku='"+object.getSku()+"' and invName='"+object.getInvName()+"';";
			  PreparedStatement preparedStatement = con.prepareStatement(query);	
			  ResultSet rs = preparedStatement.executeQuery();
			  String cycleCount="";
			  String cycleCountDate="";
		      Blob blob = null;
		      while (rs.next())
			     {
			    	 blob=rs.getBlob("imageURL");
			    	 cycleCount=rs.getString("cycleCount");
			    	 cycleCountDate=rs.getString("cycleCountDate");
			    	 try
				     {
			    		 exisitingImageInputStream= blob.getBinaryStream(1, (int) blob.length());
				    }
				     catch(NullPointerException e)
				     {
				    	 System.out.println("No Image");
				     }
			     }
				
		     String query2="delete from inventory where sku='"+object.getSku()+"' and invName='"+object.getInvName()+"';";
		     PreparedStatement pst = con.prepareStatement(query2);
		     pst.executeUpdate(query2);
	    	 PreparedStatement pst2 = null ;
		     if (object.getImageURL()==null)
				{
		    	
		    	 pst2 = con.prepareStatement("insert into inventory values(null,'"+object.getSku()+"','"+object.getInvName()+"','"+object.getBrand()+"','"+object.getItemCode()+"','"+object.getNoOfItems()+"',?,'"+object.getCategoryName()+"','"+object.getSubCategoryName()+"','"+object.getDescription()+
	    		 			"','"+object.getFfcId()+"','"+object.getiC()+"','"+object.getNoOfButton()+"','"+object.getButtonConfiguration()+"','"+object.getEmergencyKey()+"','"+object.getBatteryPartNumber()+"','"+object.getProductNotes()+"','"+object.getRestockLimit()+"','"+cycleCount+"','"+cycleCountDate+"','"+object.getModel()+"','"+object.getTrim()+"','"+object.getFromYear()+"','"+object.getToYear()+"',null);");
		    	  pst2.setBinaryStream(1,exisitingImageInputStream,exisitingImageInputStream.available());
				}
		     else
		     {
		    	  pst2 = con.prepareStatement("insert into inventory values(null,'"+object.getSku()+"','"+object.getInvName()+"','"+object.getBrand()+"','"+object.getItemCode()+"','"+object.getNoOfItems()+"',?,'"+object.getCategoryName()+"','"+object.getSubCategoryName()+"','"+object.getDescription()+
	    		 			"','"+object.getFfcId()+"','"+object.getiC()+"','"+object.getNoOfButton()+"','"+object.getButtonConfiguration()+"','"+object.getEmergencyKey()+"','"+object.getBatteryPartNumber()+"','"+object.getProductNotes()+"','"+object.getRestockLimit()+"','"+cycleCount+"','"+cycleCountDate+"','"+object.getModel()+"','"+object.getTrim()+"','"+object.getFromYear()+"','"+object.getToYear()+"',null);");
		    	  pst2.setBinaryStream(1,inputStream,inputStream.available());
		     }
		      
		     
		     pst2.executeUpdate();
		     LogObject logObject=new LogObject();
		     logObject.setBrand(object.getBrand());
		     logObject.setCategory(object.getCategoryName());
		     logObject.setSubCategory(object.getSubCategoryName());
		     logObject.setSku(object.getSku());
		     logObject.setNumber(object.getNoOfItems());
		     logObject.setInvFrom(object.getInvName());
		     logObject.setModel(object.getModel());
		     takeLog(currentUser, "Update", logObject);
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		
		
		return null;
		
		
		
	}
	
	public String generateItemCode(Item item)
	{
		String invCode=getInventoryCode(item.getInvName());
		String rack=item.getRack();
		String shelf=item.getShelf();
		String column=item.getColumn();
		String compartment=item.getCompartment();
		return invCode+rack+shelf+column+compartment;
	}
	
	public String addItem(Item searchObject,String currentUser)
	{
		String result="Item not Added";
		InputStream inputStream=null;
		Date dNow = new Date( );
	     SimpleDateFormat ft = 
	     new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss");
		try {
			inputStream = searchObject.getImageURL().getInputStream();
		} catch (IOException e1) {
			 System.out.println("Image not valid");
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		try{
			String itemCode=null;
			String invName=null;
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     PreparedStatement pst = con.prepareStatement("insert into inventory values(null,'"+searchObject.getSku()+"','"+invName+"','"+searchObject.getBrand()+"','"+itemCode+"','"+searchObject.getNoOfItems()+"',?,'"+searchObject.getCategoryName()+"','"+searchObject.getSubCategoryName()+"','"+searchObject.getDescription()+
		    		 			"','"+searchObject.getFfcId()+"','"+searchObject.getiC()+"','"+searchObject.getNoOfButton()+"','"+searchObject.getButtonConfiguration()+"','"+searchObject.getEmergencyKey()+"','"+searchObject.getBatteryPartNumber()+"','"+searchObject.getProductNotes()+"','"+searchObject.getRestockLimit()+"','1','"+ft.format(dNow)+"','"+searchObject.getModel()+"','"+searchObject.getTrim()+"','"+searchObject.getFromYear()+"','"+searchObject.getToYear()+"',null);");
		     
		     pst.setBinaryStream(1,inputStream,inputStream.available());
		     pst.executeUpdate(); 
		     result="New Item Added";
		     LogObject logObject=new LogObject();
		     logObject.setBrand(searchObject.getBrand());
		     logObject.setCategory(searchObject.getCategoryName());
		     logObject.setSubCategory(searchObject.getSubCategoryName());
		     logObject.setSku(searchObject.getSku());
		     logObject.setNumber(searchObject.getNoOfItems());
		     logObject.setInvFrom(invName);
		     logObject.setItemCode(itemCode);
		     logObject.setModel(searchObject.getModel());
		     takeLog(currentUser, "Add Item", logObject);
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
			 result=result+"<br>"+e.getMessage();
		  }
		
		
		
		return result;
	}
	
	
	public String assignInventoryToItem(Item item,String currentUser){
		
		String result="Item not Added";
		InputStream exisitingImageInputStream=null;
		Date dNow = new Date( );
	     SimpleDateFormat ft = 
	     new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss");
		try{
			String itemCode=generateItemCode(item);
			String invName=item.getInvName();
		    Class.forName("com.mysql.jdbc.Driver").newInstance();
		    Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		    String query = "select * from inventory where sku='"+item.getSku()+"' and itemCode = 'null' ;";
			  PreparedStatement preparedStatement = con.prepareStatement(query);	
			  ResultSet rs = preparedStatement.executeQuery();
			  Blob blob = null;
		      while (rs.next())
			     {
			    	 blob=rs.getBlob("imageURL");
			    	 item.setBatteryPartNumber(rs.getString("batteryPartNumber"));
			    	 item.setBrand(rs.getString("brand"));
			    	 item.setButtonConfiguration(rs.getString("buttonConfiguration"));
			    	 item.setCategoryName(rs.getString("category"));
			    	 item.setDescription(rs.getString("description"));
			    	 item.setEmergencyKey(rs.getString("emergencyKey"));
			    	 item.setFfcId(rs.getString("fccid"));
			    	 item.setFromYear(rs.getString("fromYear"));
			    	 item.setiC(rs.getString("ic"));
			    	 item.setModel(rs.getString("model"));
			    	 item.setNoOfButton(rs.getString("noOfButtons"));
			    	 item.setProductNotes(rs.getString("productNotes"));
			    	 item.setRestockLimit(rs.getString("restocklimit"));
			    	 item.setSubCategoryName(rs.getString("subcategory"));
			    	 item.setToYear(rs.getString("toYear"));
			    	 item.setTrim(rs.getString("trim"));
			    	 item.setNoOfItems(rs.getString("noOfItems"));
			
			    	 try
				     {
			    		 exisitingImageInputStream= blob.getBinaryStream(1, (int) blob.length());
			    		 
				    }
				     catch(NullPointerException e)
				     {
				    	 System.out.println("No Image");
				     }
			     }
		      PreparedStatement pst = con.prepareStatement("insert into inventory values(null,'"+item.getSku()+"','"+invName+"','"+item.getBrand()+"','"+itemCode+"','"+item.getNoOfItems()+"',?,'"+item.getCategoryName()+"','"+item.getSubCategoryName()+"','"+item.getDescription()+
		    		 			"','"+item.getFfcId()+"','"+item.getiC()+"','"+item.getNoOfButton()+"','"+item.getButtonConfiguration()+"','"+item.getEmergencyKey()+"','"+item.getBatteryPartNumber()+"','"+item.getProductNotes()+"','"+item.getRestockLimit()+"','1','"+ft.format(dNow)+"','"+item.getModel()+"','"+item.getTrim()+"','"+item.getFromYear()+"','"+item.getToYear()+"',null);");
		     
		     pst.setBinaryStream(1,exisitingImageInputStream,exisitingImageInputStream.available());
		     pst.executeUpdate(); 
		     result="New Item Added";
		     LogObject logObject=new LogObject();
		     logObject.setBrand(item.getBrand());
		     logObject.setCategory(item.getCategoryName());
		     logObject.setSubCategory(item.getSubCategoryName());
		     logObject.setSku(item.getSku());
		     logObject.setNumber(item.getNoOfItems());
		     logObject.setInvFrom(invName);
		     logObject.setItemCode(itemCode);
		     logObject.setModel(item.getModel());
		     takeLog(currentUser, "Add Item", logObject);
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
			 result=result+"<br>"+e.getMessage();
		  }
		
		
		
		return result;
	}
	
public Item searchUnassignedItem(Item item){
		Item result=new Item();
		try{
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		      Statement statement2 = con.createStatement();
		      String query="";
		      if (item.getSku().equals("") || item.getSku()==null)
		      {
		    	  query="select * from inventory where brand like '%"+item.getBrand()+"%' AND category like '%"+item.getCategoryName()+"%' AND subcategory like '%"+item.getSubCategoryName()+"%' AND model like '%"+item.getModel()+"%' And trim like '%"+item.getTrim()+"%' AND fromYear like '%"+item.getFromYear()+"%' AND toYear like '%"+item.getToYear()+"%' AND itemCode = 'null';";
		      }
		      else
		      {
		    	 query="select * from inventory where sku='"+item.getSku()+"' AND itemCode = 'null';"; 
		      }
		      System.out.println(query);
	            ResultSet rs2 = 
	                statement2.executeQuery(query) ; 
			
				while(rs2.next()) {
					 result.setCategoryName(rs2.getString("category"));
			    	 result.setSubCategoryName(rs2.getString("subCategory"));
			    	 result.setSku(rs2.getString("sku"));
			    	 result.setBrand(rs2.getString("brand"));
			    	 result.setModel(rs2.getString("model"));
			    	 result.setTrim(rs2.getString("trim"));
			    	 result.setFromYear(rs2.getString("fromYear"));
			    	 result.setToYear(rs2.getString("toYear"));
			    	 result.setDescription(rs2.getString("description"));
			    	 result.setNoOfButton(rs2.getString("noOfButtons"));
			    	 result.setiC(rs2.getString("ic"));
			    	 result.setBatteryPartNumber(rs2.getString("batteryPartNumber"));
			    	 result.setNoOfItems(rs2.getString("noOfItems"));
	            }
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		}
		return result;
	}


public Item searchUnassignedModelTrimItem(Item item){
	Item result=new Item();
	try{
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
	      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
	      Statement statement2 = con.createStatement();
	      String query="";
	     
	    	 query="select * from inventory where sku='"+item.getSku()+"' AND (brand ='null');"; 
	      
            ResultSet rs2 = 
                statement2.executeQuery(query) ; 
		
			while(rs2.next()) {
				 result.setCategoryName(rs2.getString("category"));
		    	 result.setSubCategoryName(rs2.getString("subCategory"));
		    	 result.setSku(rs2.getString("sku"));
		    	 result.setDescription(rs2.getString("description"));
		    	 result.setNoOfButton(rs2.getString("noOfButtons"));
		    	 result.setiC(rs2.getString("ic"));
		    	 result.setBatteryPartNumber("batteryPartNumber");
				
            }
	     con.close();
		}
	  catch(Exception e)
	  {
		 e.printStackTrace();
	}
	System.out.println(result);
	return result;
}
	public boolean changePassword(Login l,String currentUser)
	{
		
		boolean result=false;
			try{
			     Class.forName("com.mysql.jdbc.Driver").newInstance();
			      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
			     Statement stmt = con.createStatement();
			     String query = "update loginprofile set password='"+l.getNewPassword()+"' where LoginID='"+l.getLoginID()+"' and password='"+l.getCurrentPassword()+"';";
			     int i=stmt.executeUpdate(query);
			     stmt.close();
				 con.close();
				 if (i==0)
				 {
					 result=false;
				 }
				 else
				 {
					  LogObject logObject=new LogObject();
					     takeLog(currentUser, "Change Password", logObject);
					 result=true;	 
				 }
				 
				}
			  catch(Exception e)
			  {
				 e.printStackTrace();
			  }
			
			return result;
		}

	
	public String initiateTransferStocks(ArrayList<TransferObject> transferObjectList,String currentUser)
	{
		String transferID="";
		Random rand = new Random();
		int  randomNumber = rand.nextInt(1000000000) + 1;
		for (int i=0;i<transferObjectList.size();i++)
		{
			try
			{
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     Statement stmt = con.createStatement();
		     Date dNow = new Date( );
		     SimpleDateFormat ft = 
		     new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss");
		     transferID=String.valueOf(randomNumber);
		     String query3 = "insert into transfer values(null,'"+transferID+"','"+transferObjectList.get(i).getSku()+"','"+transferObjectList.get(i).getTransferAmount()+"','"+currentUser+"','"+ft.format(dNow)+"','INITIATED','"+transferObjectList.get(i).getInvCodeFrom()+"','"+transferObjectList.get(i).getInvCodeTo()+"','"+transferObjectList.get(i).getCategoryName()+"','"+transferObjectList.get(i).getSubCategoryName()+"','"+transferObjectList.get(i).getBrand()+"','"+currentUser+"','"+transferObjectList.get(i).getModel()+"');";	
		     stmt.executeUpdate(query3);
		     LogObject logObject=new LogObject();
		     logObject.setBrand(transferObjectList.get(i).getBrand());
		     logObject.setCategory(transferObjectList.get(i).getCategoryName());
		     logObject.setSubCategory(transferObjectList.get(i).getSubCategoryName());
		     logObject.setSku(transferObjectList.get(i).getSku());
		     logObject.setNumber(transferObjectList.get(i).getTransferAmount());
		     logObject.setInvFrom(transferObjectList.get(i).getInvCodeFrom());
		     logObject.setInvTo(transferObjectList.get(i).getInvCodeTo());
		     logObject.setTransferID(transferID);
		     logObject.setReason(transferObjectList.get(i).getReason());
		     logObject.setModel(transferObjectList.get(i).getModel());
		     takeLog(currentUser, "Initiate Transfer", logObject);
		     stmt.close();
			 con.close();
		}
		  catch(Exception e)
		  {
			 e.printStackTrace();
			
		  }
			
		
	
		}
		return transferID;
		
		}
	
	
	public String getSkuListJSArray()
	{
		String result="";
		try{
			ArrayList<String>strList=new ArrayList<String>();
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		      Statement statement2 = con.createStatement();
	            ResultSet resultset3 = 
	                statement2.executeQuery("select sku from inventory;") ; 
			
				while(resultset3.next()) {
	              strList.add(resultset3.getString(1));
	            }
				 for (int i = 0; i < strList.size(); i++)
				 {  
					 if (result.equals(""))
						 result="'"+strList.get(i)+"'";
					 else
						 result=result+",'"+strList.get(i)+"'";
				 }
		         con.close();
				}
		  catch(Exception e)
		  {
			 e.printStackTrace();
			
		  }
		
		result="["+result+"]";
		return result;
		
		
	}
	
	
	
	public ArrayList<HashMap<String, String>> getLocationMap()
	{
		ArrayList<HashMap<String, String>>result1=new ArrayList<HashMap<String,String>>();
		HashMap<String,String>inventoryMap=new HashMap<String, String>();
		HashMap<String, String>result=new HashMap<String, String>();
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		      String query = "select inventoryCode,inventoryName from invtable ;";
			     PreparedStatement preparedStatement = con.prepareStatement(query);	
			     ResultSet rs = preparedStatement.executeQuery();
			     while (rs.next())
			     {
			    	 inventoryMap.put(rs.getString("inventoryName"), rs.getString("inventoryCode")) ;
			    }
			     result1.add(inventoryMap);
			  String query2 = "select itemCode,invName,sku from inventory ;";
			     PreparedStatement preparedStatement1 = con.prepareStatement(query2);	
			     ResultSet rs1 = preparedStatement1.executeQuery();
			     while (rs1.next())
			     {
			    	 ArrayList<String>locationList=new ArrayList<String>();
			    	String inventoryCode=inventoryMap.get(rs1.getString("invName"));
			    	String itemCode=rs1.getString("itemCode");
			    	locationList.add(itemCode.substring(inventoryCode.length(), 3));
			    	locationList.add(itemCode.substring(inventoryCode.length()+1, 4));
			    	locationList.add(itemCode.substring(inventoryCode.length()+2,5));
			    	locationList.add(itemCode.substring(inventoryCode.length()+3, 6));
			    	result.put("\""+rs1.getString("invName")+rs1.getString("sku")+"\"","\""+locationList.toString().replaceAll("\\[","").replaceAll("\\]","")+"\"");
			     }
			  
			     result1.add(result);
		         con.close();
				}
		  catch(Exception e)
		  {
			 e.printStackTrace();
			
		  }
		
		return result1;
		
		
	}
	
	
	public Multimap<String, String> getCategoryMap()
	{
		Multimap<String, String> multimap = ArrayListMultimap.create();
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		      String query = "select category_name,subcategory_name from category ;";
			     PreparedStatement preparedStatement = con.prepareStatement(query);	
			     ResultSet rs = preparedStatement.executeQuery();
			     while (rs.next())
			     {
			    	 multimap.put(rs.getString("category_name"), rs.getString("subcategory_name")) ;
			    }
		         con.close();
				}
		  catch(Exception e)
		  {
			 e.printStackTrace();
			
		  }
		
		return multimap;
		
		
	}
	
	public Multimap<String, String> getBrandMap()
	{
		Multimap<String, String> multimap = ArrayListMultimap.create();
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		      String query = "select DISTINCT brandName, model from carbrandtable ;";
			     PreparedStatement preparedStatement = con.prepareStatement(query);	
			     ResultSet rs = preparedStatement.executeQuery();
			     while (rs.next())
			     {
			    	if (!(rs.getString("brandName")==null || rs.getString("model")==null))
			    	 multimap.put(rs.getString("brandName"), rs.getString("model").trim()) ;
			    }
		         con.close();
				}
		  catch(Exception e)
		  {
			 e.printStackTrace();
			
		  }
		
		return multimap;
		
		
	}
	
	public Multimap<String, String> getmodelMap()
	{
		Multimap<String, String> multimap = ArrayListMultimap.create();
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		      String query = "select model,trim from carbrandtable ;";
			     PreparedStatement preparedStatement = con.prepareStatement(query);	
			     ResultSet rs = preparedStatement.executeQuery();
			     while (rs.next())
			     {
			    	 if (!(rs.getString("model")==null || rs.getString("trim")==null|| rs.getString("model").equals("")))
			    	 {
			    		
					    	 multimap.put(rs.getString("model").trim(), rs.getString("trim")) ;	 
			    	 }
			    	 
			    }
		         con.close();
				}
		  catch(Exception e)
		  {
			 e.printStackTrace();
			
		  }
		
		return multimap;
		
		
	}
	
	public ArrayList<String> getShipperList()
	{
		ArrayList<String> multimap = new ArrayList<String>();
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		      String query = "select shippername from shippers ;";
			     PreparedStatement preparedStatement = con.prepareStatement(query);	
			     ResultSet rs = preparedStatement.executeQuery();
			     while (rs.next())
			     {
			    	 multimap.add(rs.getString(1));
			    }
		         con.close();
				}
		  catch(Exception e)
		  {
			 e.printStackTrace();
			
		  }
		
		return multimap;
		
		
	}
	
	public String addTechnician(Login l,String currentUser)
	{
		String result="";
		try{
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     Statement stmt = con.createStatement();
		     String query = "insert into loginprofile values (null,'"+l.getName()+"','"+l.getPassword()+"','"+l.getRole()+"','"+l.getLoginID()+"','"+l.getEmail()+"','"+l.getAccessInvList().toString()+"','0');";
		     int i=stmt.executeUpdate(query);
		     stmt.close();
			 con.close();
			 if (i==0)
			 {
				 result="Error";
			 }
			 else
			 {
				 LogObject logObject=new LogObject();
				 logObject.setTechnician(l.getLoginID());
			     takeLog(currentUser, "Create Technician", logObject);
				 result="Added";	 
			 }
			 
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		return result;
	}
	public Login searchTechnician(Login l)
	{
		
		Login login=new Login();
		try{
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		  
		     String query = "select name,password,profile,email,accessList  from loginprofile where LoginID='"+l.getLoginID()+"';";
		     PreparedStatement preparedStatement1 = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement1.executeQuery();
		    
		     while (rs.next())
		     {
		    	 login.setName(rs.getString(1));
		    	 login.setPassword(rs.getString(2));
		    	 login.setRole(rs.getString(3));
		    	 login.setLoginID(l.getLoginID());
		    	 login.setEmail(rs.getString(4));
		    	 login.setAccessInvList(new ArrayList<String>(Arrays.asList(rs.getString(5).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").split(","))));
		     }
	         con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		
		return login;
	}
	public String updateTechnician(Login l,String currentUser)
	{
		String result="";
		try{
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     Statement stmt = con.createStatement();
		     String query="";
		     if (l.getAccessInvList()==null)
		     {
		    	 query = "update loginprofile set name='"+l.getName()+"', password='"+l.getPassword()+"', profile='"+l.getRole()+"', email='"+l.getEmail()+"'"
	     					+ "where LoginID='"+l.getLoginID()+"';";
		     }
		     else
		    	 query = "update loginprofile set name='"+l.getName()+"', password='"+l.getPassword()+"', profile='"+l.getRole()+"', email='"+l.getEmail()+"', accessList='"+l.getAccessInvList()+"'"
		     					+ "where LoginID='"+l.getLoginID()+"';";
		     int i=stmt.executeUpdate(query);
		     stmt.close();
			 con.close();
			 if (i==0)
			 {
				 result="Error";
			 }
			 else
			 {
				 LogObject logObject=new LogObject();
				 logObject.setTechnician(l.getLoginID());
			     takeLog(currentUser, "Update Technician", logObject);
				 result="Updated";	 
			 }
			 
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		
		return result;
	}
	public String removeTechnician(Login l,String currentUser)
	{
		String result="";
		try{
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     Statement stmt = con.createStatement();
		     String query = "delete from loginprofile where LoginID='"+l.getLoginID()+"';";
		     int i=stmt.executeUpdate(query);
		     stmt.close();
			 con.close();
			 if (i==0)
			 {
				 result="Error";
			 }
			 else
			 {
				 LogObject logObject=new LogObject();
				 logObject.setTechnician(l.getLoginID());
			     takeLog(currentUser, "Remove Technician", logObject);
				 result="Removed";	 
			 }
			 
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		
		return result;
	}
	public String deleteItem(Item i,String currentUser)
	{
		String result="";
	
		boolean condition=true;
		if (i.getItemCode().equals("") || i.getItemCode()==null)
		{
			condition=false;
		}
		try{
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     Statement stmt = con.createStatement();
		     String query ="";
		     if (condition)
		     query = "delete from inventory where itemCode='"+i.getItemCode()+"';";
		     else
		    	 query = "delete from inventory where invName='"+i.getInvName()+"' and sku='"+i.getSku()+"';";
		     int numberAffected=stmt.executeUpdate(query);
		     stmt.close();
			 con.close();
			 System.out.println(query);
			 if (numberAffected==0)
			 {
				 result="Error";
			 }
			 else
			 {
				 LogObject logObject=new LogObject();
				 logObject.setItemCode(i.getItemCode());
			     logObject.setInvFrom(i.getInvName());
			     logObject.setSku(i.getSku());
				 takeLog(currentUser, "Remove Item", logObject);
				 result="Removed";	 
			 }
			 
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		
		return result;
	}
	public String createInventory(Inventory inventory,String currentUser)
	{
		String result="";
		try{
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     Statement stmt = con.createStatement();
		     String query = "insert into invtable values (null,'"+inventory.getInventoryCode()+"','"+inventory.getInventoryName()+"');";
		     int i=stmt.executeUpdate(query);
		     String query3 = "select accessList from loginprofile where name='admin' AND profile='admin';";
			    
		     PreparedStatement preparedStatement = con.prepareStatement(query3);	
		     ResultSet rs2 = preparedStatement.executeQuery();
		     String accessList="";
		     while (rs2.next())
		     {
		    	 accessList=rs2.getString("accessList");
		    }
		     if (accessList.equals(""))
		    	 accessList="["+inventory.getInventoryName()+"]";	 
		     else	 
		     accessList=accessList.replaceAll("\\]","")+","+inventory.getInventoryName()+"]";
		     String query2 = "update loginprofile set accessList='"+accessList+"' where name='admin' AND profile='admin';";
		     stmt.executeUpdate(query2);
		     stmt.close();
			 con.close();
			 if (i==0)
			 {
				 result="Error";
			 }
			 else
			 {
				 LogObject logObject=new LogObject();
			     logObject.setInvFrom(inventory.getInventoryName());
			     takeLog(currentUser, "Add Inventory", logObject);
				 result="Removed";	
				 result="Added";	 
			 }
			 
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return result;
		
	}
	public String updateInventory(Inventory inventory,String currentUser)
	{
		String result="";
		try{
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     Statement stmt = con.createStatement();
		     String query4 = "select inventoryName,inventoryCode from invtable where id='"+inventory.getId()+"';"; 
			 PreparedStatement preparedStatement4 = con.prepareStatement(query4);	
		     ResultSet rs4 = preparedStatement4.executeQuery();
		     String prevInvName="";
		     String prevInvCode="";
		     while (rs4.next())
		     {
		    	 prevInvName=rs4.getString("inventoryName");
		    	 prevInvCode=rs4.getString("inventoryCode");
		    	 
		    }
		     
		     String query5 = "select itemCode from inventory where invName='"+prevInvName+"';"; 
			 PreparedStatement preparedStatement5 = con.prepareStatement(query5);	
		     ResultSet rs5 = preparedStatement5.executeQuery();
		     while (rs5.next())
		     {
		    	 String query = "update inventory set itemCode='"+rs5.getString(1).replaceFirst(prevInvCode, inventory.getInventoryCode())+"' where inventoryName='"+inventory.getInventoryName()+"';"; 
		    	 stmt.executeUpdate(query);
		    } 
		     String query = "update invtable set inventoryCode='"+inventory.getInventoryCode()+"',inventoryName='"+inventory.getInventoryName()+
		    		 "' where id='"+inventory.getId()+"';"; 
		   
		     int i=stmt.executeUpdate(query);
		     String query3 = "select accessList from loginprofile where name='admin' AND profile='admin';";
			    
		     PreparedStatement preparedStatement = con.prepareStatement(query3);	
		     ResultSet rs2 = preparedStatement.executeQuery();
		     String accessList="";
		     while (rs2.next())
		     {
		    	 accessList=rs2.getString("accessList");
		    }
		     accessList= accessList.replaceFirst(prevInvName,inventory.getInventoryName());
		     String query2 = "update loginprofile set accessList='"+accessList+"' where name='admin' AND profile='admin';";
		     stmt.executeUpdate(query2);
		     stmt.close();
			 con.close();
			 if (i==0)
			 {
				 result="Error";
			 }
			 else
			 {

				 LogObject logObject=new LogObject();
			     logObject.setInvFrom(inventory.getInventoryName());
			     takeLog(currentUser, "Update Inventory", logObject);
				 result="Updated";	 
			 }
			 
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		return result;
	}
	public String removeInventory(Inventory inventory,String currentUser)
	{
		String result="";
		try{
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     Statement stmt = con.createStatement();
		     String query = "delete from invtable where inventoryName='"+inventory.getInventoryName()+"';";
		     System.out.println(query);
		     String query3 = "select accessList from loginprofile where name='admin' AND profile='admin';";
			    
		     PreparedStatement preparedStatement = con.prepareStatement(query3);	
		     ResultSet rs2 = preparedStatement.executeQuery();
		     String accessList="";
		     while (rs2.next())
		     {
		    	 accessList=rs2.getString("accessList");
		    }
		    accessList= accessList.replaceFirst(","+inventory.getInventoryName(), "");
		    accessList= accessList.replaceFirst(inventory.getInventoryName()+",", "");
		     String query2 = "update loginprofile set accessList='"+accessList+"' where name='admin' AND profile='admin';";
		     stmt.executeUpdate(query2);
		     int i=stmt.executeUpdate(query);
		     stmt.close();
			 con.close();
			 if (i==0)
			 {
				 result="Error";
			 }
			 else
			 {

				 LogObject logObject=new LogObject();
			     logObject.setInvFrom(inventory.getInventoryName());
			     takeLog(currentUser, "Remove Inventory", logObject);
				 result="Removed";	 
			 }
			 
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return result;
		
	}
	public Inventory searchInventory(Inventory inventory)
	{
		Inventory inv=new Inventory();
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "select id,inventoryCode,inventoryName from invtable where inventoryName='"+inventory.getInventoryName()+"';"; 
		     PreparedStatement preparedStatement1 = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement1.executeQuery();
		     while (rs.next())
		     {
		    	 inv.setId(rs.getString(1));
		    	 inv.setInventoryCode(rs.getString(2));
		    	 inv.setInventoryName(rs.getString(3));
		     }
			 con.close();
			
			 
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return inv;
		
	}
	public ArrayList<TransferObject> searchTransferID(TransferObject object)
	{
		ArrayList<TransferObject>result=new ArrayList<TransferObject>();
		
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "select * from transfer where transferID='"+object.getTransferId()+"' and status='INITIATED';"; 
		     PreparedStatement preparedStatement1 = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement1.executeQuery();
		     while (rs.next())
		     {
		    	 TransferObject transferObject=new TransferObject();
		    	 transferObject.setTransferId(rs.getString("transferID"));
		    	 transferObject.setSku(rs.getString("sku"));
		    	 transferObject.setInvCodeFrom(rs.getString("fromInv"));
		    	 transferObject.setInvCodeTo(rs.getString("toInv"));
		    	 transferObject.setBrand(rs.getString("brand"));
		    	 transferObject.setCategoryName(rs.getString("category"));
		    	 transferObject.setSubCategoryName(rs.getString("subcategory"));
		    	 transferObject.setTransferAmount(rs.getString("noOfItems"));
		    	 transferObject.setModel(rs.getString("model"));
		    	 transferObject.setDate(rs.getString("tranfertime"));
		    	 String query2 = "select itemCode from inventory where sku='"+transferObject.getSku()+"' and invName='"+transferObject.getInvCodeTo()+"';";
		    	 PreparedStatement preparedStatement = con.prepareStatement(query2);	
			     ResultSet rs2 = preparedStatement.executeQuery();
		    	 while (rs2.next())
		    	 {
		    		 transferObject.setItemCodeTo(rs2.getString("itemCode"));
		    		 ArrayList<String>positionArray=getItemPosition(transferObject.getItemCodeTo(), transferObject.getInvCodeTo());
		    		 transferObject.setRackTo(positionArray.get(0));
		    		 transferObject.setShelfTo(positionArray.get(1));
		    		 transferObject.setColumnTo(positionArray.get(2));
		    		 transferObject.setCompartmentTo(positionArray.get(3));
		    		 
		    	 }
		    	 result.add(transferObject);
		    	
		     }
			 con.close();
			
			 
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return result;
		
	}
	
	public String doTransferStocks(TransferObject object,String transferID,String currentUser)
	{
		String result="Transfer Failed";
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     Statement stmt = con.createStatement();
		     
		     String query2 = "update inventory set noOfItems= noOfItems - '"+object.getTransferAmount()+"' where sku='"+object.getSku()+"' and invName='"+object.getInvCodeFrom()+"';";
		     String query3 = "update inventory set noOfItems= noOfItems + '"+object.getTransferAmount()+"' where sku='"+object.getSku()+"' and invName='"+object.getInvCodeTo()+"';";
		     String query4 = "update transfer set status='COMPLETE' where sku='"+object.getSku()+"' and transferID='"+transferID+"';";
		     System.out.println(query2);
		     System.out.println(query3);
		     System.out.println(query4);
		     int i=stmt.executeUpdate(query2);
		     int j=stmt.executeUpdate(query3);
		     int k=stmt.executeUpdate(query4);
		     con.close();
		     if (i!=0 & j!=0 & k!=0)
			{
		    	 LogObject logObject=new LogObject();
		    	 logObject.setNumber(object.getTransferAmount());
			     logObject.setInvFrom(object.getInvCodeFrom());
			     logObject.setInvTo(object.getInvCodeTo());
			     logObject.setSku(object.getSku());
			     logObject.setTransferID(transferID);
			     logObject.setReason(object.getReason());
			     takeLog(currentUser, "Transfer Complete", logObject);
				  
				result="transfer Complete";
			}
		    }
		
		  catch(Exception e)
		  {
			  result="Transfer Failed\n";
			  result=result+e.getMessage();
			  e.printStackTrace();
		  }
		return result;
		
	}
	
	public ArrayList<TransferObject> showPendingTransfer(String currentUser)
	{
		ArrayList<TransferObject> result=new ArrayList<TransferObject>();
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "select * from transfer where status='INITIATED' and user='"+currentUser+"'  group by transferID ;"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
		     while (rs.next())
		     {
		    	 TransferObject object=new TransferObject();
		    	 object.setTransferId(rs.getString("transferID"));
		    	 object.setSku(rs.getString("sku"));
		    	 object.setInvCodeFrom(rs.getString("fromInv"));
		    	 object.setInvCodeTo(rs.getString("toInv"));
		    	 object.setBrand(rs.getString("brand"));
		    	 object.setCategoryName(rs.getString("category"));
		    	 object.setSubCategoryName(rs.getString("subcategory"));
		    	 object.setTransferAmount(rs.getString("noOfItems"));
		    	 object.setDate(rs.getString("tranfertime"));
		    	 object.setModel(rs.getString("model"));
		    	 result.add(object);
		     }
		    
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return result;
		
	}
	
	public ArrayList<TransferObject> receiveShipmentRequest(TransferObject object,String currentUser)
	{
		ArrayList<TransferObject> result=new ArrayList<TransferObject>();
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		      String query="";
		     if (object.getSku().equals(""))
		    	 query = "update inventory set noOfItems=noOfItems+'"+object.getTransferAmount()+"' where invName='"+object.getInvCodeFrom()+"' and brand='"+object.getSku()+"' and category='"+object.getCategoryName()+"' and subcategory='"+object.getSubCategoryName()+"' and model='"+object.getModel()+"';";
		    	 else
		      query = "update inventory set noOfItems=noOfItems+'"+object.getTransferAmount()+"' where invName='"+object.getInvCodeFrom()+"' and sku='"+object.getSku()+"';"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     Statement stmt = con.createStatement();
		     if (!(object.getShipperAddress().equals("")))
		     {
		    	 String query2 = "insert into shippers values (null,'"+object.getShipperName().replace(",", "")+"','"+object.getShipperAddress()+"','"+object.getShipperEmail()+"','"+object.getShipperPhoneNumber()+"');";
			     stmt.executeUpdate(query2);
		     }
		    	 
		     stmt.close();
		     con.close();
		     LogObject logObject=new LogObject();
	    	 logObject.setNumber(object.getTransferAmount());
		     logObject.setInvFrom(object.getInvCodeFrom());
		     logObject.setSku(object.getSku());
		     logObject.setCategory(object.getCategoryName());
		     logObject.setSubCategory(object.getSubCategoryName());
		     logObject.setBrand(object.getBrand());
		     logObject.setModel(object.getModel());
		     logObject.setShipperName(object.getShipperName());
		     takeLog(currentUser, "Recieve Shipment", logObject);
		    
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return result;
		
	}
	
	public void addNewCategoryRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "insert into category values(null,'"+object.getCategoryName()+"','');"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     LogObject logObject=new LogObject();
	    	 logObject.setCategory(object.getCategoryName());
		     takeLog(currentUser, "Add Category", logObject);
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	public Item searchCategoryRequest(Item object)
	{
		Item result=new Item();
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "select category_id,category_name from category where category_name='"+object.getCategoryName()+"';"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
		     while (rs.next())
		     {
		    	result.setItemCode(rs.getString("category_id"));
		    	result.setCategoryName(rs.getString("category_name"));
		    }
		    
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return result;
		
	}
	public void updateCategoryRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		      String query = "select category_name from category where  category_id='"+object.getItemCode()+"';"; 
			     PreparedStatement preparedStatement = con.prepareStatement(query);	
			     String prevCategoryName="";
			     ResultSet rs = preparedStatement.executeQuery();
			     while (rs.next())
			     {
			    	 prevCategoryName=rs.getString("category_name");
			    }
		     String query2 = "update category set category_name='"+object.getCategoryName()+"' where category_id='"+object.getItemCode()+"';";
		     String query3 = "update category set category_name='"+object.getCategoryName()+"' where category_name='"+prevCategoryName+"';";
		    
		     PreparedStatement preparedStatement2 = con.prepareStatement(query2);	
		     preparedStatement2.executeUpdate();
		     PreparedStatement preparedStatement3 = con.prepareStatement(query3);	
		     preparedStatement3.executeUpdate();
		     LogObject logObject=new LogObject();
	    	 logObject.setCategory(object.getCategoryName());
		     takeLog(currentUser, "Update Category", logObject);
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	public void deleteCategoryRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "delete from category where category_name='"+object.getCategoryName()+"' "; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     LogObject logObject=new LogObject();
	    	 logObject.setCategory(object.getCategoryName());
		     takeLog(currentUser, "Remove Category", logObject);
		     preparedStatement.executeUpdate();
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	
	//////
	
	public void addNewReasonRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "insert into reasontable values(null,'"+object.getReason()+"');"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     LogObject logObject=new LogObject();
	    	 logObject.setReason(object.getReason());
		     takeLog(currentUser, "Add Reason", logObject);
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	public Item searchReasonRequest(Item object)
	{
		Item result=new Item();
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "select reasonName from reasontable where reasonName='"+object.getReason()+"';"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
		     while (rs.next())
		     {
		    	result.setReason(rs.getString("reasonName"));
		    
		    }
		    
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return result;
		
	}
	public void updateReasonRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "update reasontable set reasonName='"+object.getReason()+"' ;";
		    
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     LogObject logObject=new LogObject();
		     logObject.setReason(object.getReason());
		     takeLog(currentUser, "Update Reason", logObject);
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	public void deleteReasonRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "delete from reasontable where reasonName='"+object.getReason()+"' "; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     LogObject logObject=new LogObject();
		     logObject.setReason(object.getReason());
		     takeLog(currentUser, "Remove Reason", logObject);
		     preparedStatement.executeUpdate();
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	
	
	public void addNewSubCategoryRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "insert into category values(null,'"+object.getCategoryName()+"','"+object.getSubCategoryName()+"');"; 
		    
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     LogObject logObject=new LogObject();
	    	 logObject.setCategory(object.getCategoryName());
	    	 logObject.setSubCategory(object.getSubCategoryName());
		     takeLog(currentUser, "Add SubCategory", logObject);
		     preparedStatement.executeUpdate();
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	public Item searchSubCategoryRequest(Item object)
	{
		Item result=new Item();
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "select category_id,subCategory_name from category where subCategory_name='"+object.getSubCategoryName().trim()+"';"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
		     while (rs.next())
		     {
		    	result.setItemCode(rs.getString("category_id"));
		    	result.setSubCategoryName(rs.getString("subCategory_name"));
		    }
		    
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		return result;
		
	}
	public void updateSubCategoryRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "update category set subCategory_name='"+object.getSubCategoryName()+"' where category_id='"+object.getItemCode()+"';";
		    System.out.println(query);
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     LogObject logObject=new LogObject();
		     logObject.setCategory(object.getCategoryName());
	    	 logObject.setSubCategory(object.getSubCategoryName());
		     takeLog(currentUser, "Update SubCategory", logObject);
		     preparedStatement.executeUpdate();
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	public void deleteSubCategoryRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "delete from category where subCategory_name='"+object.getSubCategoryName()+"' and category_name='"+object.getCategoryName().trim()+"' "; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     LogObject logObject=new LogObject();
		     logObject.setCategory(object.getCategoryName());
	    	 logObject.setSubCategory(object.getSubCategoryName());
		     takeLog(currentUser, "Remove SubCategory", logObject);
		     preparedStatement.executeUpdate();
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	
	
	public void addNewBrandRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "insert into carbrandtable values(null,'"+object.getBrand()+"','"+object.getBrand()+"','',null);"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     LogObject logObject=new LogObject();
		     logObject.setBrand(object.getBrand());
		     takeLog(currentUser, "Add Brand", logObject);
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	
	public void addNewTrimRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "insert into carbrandtable values(null,'"+object.getBrand()+"','"+object.getBrand()+"','"+object.getModel().trim()+"','"+object.getTrim()+"');"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     LogObject logObject=new LogObject();
		     logObject.setBrand(object.getTrim());
		     takeLog(currentUser, "Add Brand", logObject);
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	public void addNewModelRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "insert into carbrandtable values(null,null,'"+object.getBrand()+"','"+object.getModel()+"',null);"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     LogObject logObject=new LogObject();
		     logObject.setBrand(object.getBrand());
		     logObject.setModel(object.getModel());
		     takeLog(currentUser, "Add Model", logObject);
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	public Item searchBrandRequest(Item object,String currentUser)
	{
		Item result=new Item();
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "select brandName,brandCode from carbrandtable where brandName='"+object.getBrand()+"';"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
		     while (rs.next())
		     {
		    	result.setBrand(rs.getString("brandName"));
		    	result.setBrandCode(rs.getString("brandCode"));
		    }
		    
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return result;
		
	}
	
	public Item searchTrimRequest(Item object,String currentUser)
	{
		Item result=new Item();
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "select brandName,model,trim from carbrandtable where brandName='"+object.getBrand()+"' and model='"+object.getModel().trim()+"' and trim='"+object.getTrim().trim()+"';"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
		     while (rs.next())
		     {
		    	result.setBrand(rs.getString("brandName"));
		    	result.setModel(rs.getString("model"));
		    	result.setTrim(rs.getString("trim"));
		    }
		    
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return result;
		
	}
	public void updateBrandRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "update carbrandtable set brandName='"+object.getBrand()+"' where brandCode='"+object.getBrandCode()+"';";
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     LogObject logObject=new LogObject();
		     logObject.setBrand(object.getBrand());
		     takeLog(currentUser, "Update Brand", logObject);
		     
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	public void deleteBrandRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "delete from carbrandtable where brandName='"+object.getBrand()+"' "; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     LogObject logObject=new LogObject();
		     logObject.setBrand(object.getBrand());
		     takeLog(currentUser, "Remove Brand", logObject);
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	
	public Item searchModelRequest(Item object,String currentUser)
	{
		Item result=new Item();
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "select brandName,model from carbrandtable where brandName='"+object.getBrand()+"' and model='"+object.getModel().trim()+"';"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
		     while (rs.next())
		     {
		    	result.setBrand(rs.getString("brandName"));
		    	result.setModel(rs.getString("model"));
		    }
		    
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return result;
		
	}
	public void updateModelRequest(Item object,String currentUser,String prevModel)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "update carbrandtable set model='"+object.getModel()+"' where brandname='"+object.getBrand()+"' AND model='"+prevModel+"';";
		     System.out.println(query);
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     LogObject logObject=new LogObject();
		     logObject.setBrand(object.getBrand());
		     logObject.setModel(object.getModel());
		     takeLog(currentUser, "Update Model", logObject);
		     
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	
	
	public void updateTrimRequest(Item object,String currentUser,String prevTrim)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "update carbrandtable set trim='"+object.getTrim()+"' where brandname='"+object.getBrand()+"' AND trim='"+prevTrim+"';";
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     LogObject logObject=new LogObject();
		     logObject.setBrand(object.getBrand());
		     logObject.setModel(object.getModel());
		     takeLog(currentUser, "Update Model", logObject);
		     
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	
	
	
	public void deleteTrimRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "delete from carbrandtable where brandName='"+object.getBrand()+"' and trim='"+object.getTrim().trim()+"';"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     LogObject logObject=new LogObject();
		     logObject.setBrand(object.getBrand());
		     logObject.setModel(object.getModel());
		     takeLog(currentUser, "Remove Model", logObject);
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	public void deleteModelRequest(Item object,String currentUser)
	{
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "delete from carbrandtable where brandName='"+object.getBrand()+"' and model='"+object.getModel().trim()+"';"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     LogObject logObject=new LogObject();
		     logObject.setBrand(object.getBrand());
		     logObject.setModel(object.getModel());
		     takeLog(currentUser, "Remove Model", logObject);
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
	}
	public String createCronExpression(Alert alert)
	{
		String exp="";
		String hour="";
		String min="";
		ArrayList<String>scheduleTimingArray=new ArrayList<String>(Arrays.asList(alert.getScheduleTIme().split(",")));
		for (int i=0;i<scheduleTimingArray.size();i++)
		{
			if (scheduleTimingArray.get(i)!="")
			{
				alert.setScheduleTIme(scheduleTimingArray.get(i));
			}
		}
		if (alert.getAlertMinutes()=="" && alert.getAlertHours()=="")
		{
		 hour=alert.getScheduleTIme().split(":")[0];
		 min=alert.getScheduleTIme().split(":")[1];
		}
		//By Minutes
		if (alert.getAlertMinutes()!="")
		{
			exp="0 0/"+alert.getAlertMinutes()+" * 1/1 * ? *";
			
		}
		//By Hours
		else if (alert.getAlertHours()!="")
		{
			exp="0 0 0/"+alert.getAlertHours()+" 1/1 * ? *";
			
		}
		//By Daily
		else if (alert.getAlertDay()!="")
		{
			exp="0 "+min+" "+hour+" 1/"+alert.getAlertDay()+" * ? *";
			
		}
		//By Weekly
		else if (alert.getDayname()!=null)
		{
			String dayNames=alert.getDayname().toString().replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "");
			exp="0 "+min+" "+hour+" ? * "+dayNames+" *";
			
		}
		//By Monthly
		else if (alert.getMonthDate()!="" &&  alert.getMonthNumber()!="")
		{
			exp="0 "+min+" "+hour+" "+alert.getMonthDate().replaceAll(",", "")+" 1/"+alert.getMonthNumber()+" ? *";
			
		}
		//By Yearly
		else if (alert.getMonthDate()!="" &&  alert.getMonthName()!="")
		{
			exp="0 "+min+" "+hour+" "+alert.getMonthDate().replaceAll(",", "")+" "+alert.getMonthName()+" ? *";
		}
		return exp;
	}

	public void createAlert(Alert alert)
	{
		String exp=createCronExpression(alert);
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "insert into alerttable values(null,'"+alert.getAlertName()+"','"+alert.getInvName()+"','"+alert.getUserName()+"','"+exp+"');"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     con.close();
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		 
		 Trigger trigger;
		 JobDetail job = JobBuilder.newJob(InventoryCountJob.class)
				.withIdentity(alert.getAlertName(), "group1").build();

			// Trigger the job to run on the next round minute
		  trigger = TriggerBuilder.newTrigger()
                 .startNow()
                 .withSchedule(
                      CronScheduleBuilder.cronSchedule(exp)).withIdentity(alert.getAlertName(), "group1")
                 .build();

			// schedule it
		
			try {
				 SchedulerFactory factory = new StdSchedulerFactory();
				    scheduler = factory.getScheduler();
				    scheduler.start();
				    scheduler.scheduleJob(job, trigger);
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	}
	
	public Alert getCronExpressiondetails(String exp)
	{
		Alert result=new Alert();
		ArrayList<String>cronExpArray=new ArrayList<String>(Arrays.asList(exp.split(" ")));
		//By Minutes
		if (exp.matches("0 0\\/[1-5]* \\* 1\\/1 \\* \\? \\*"))
		{
			result.setAlertMinutes(cronExpArray.get(1).substring(2));
		}
		//By Hours
		else if (exp.matches("0 0 0\\/[1-9]* 1\\/1 \\* \\? \\*"))
		{
			result.setAlertHours(cronExpArray.get(2).substring(2));
		}
		//By Daily
		else if (exp.matches("0\\s.+\\s.+\\s1\\/[1-9]+\\s\\*\\s\\?\\s\\*"))
		{
			result.setAlertDay(cronExpArray.get(3).substring(2));
			result.setScheduleTIme(cronExpArray.get(2)+":"+cronExpArray.get(1));
		}
		//By Monthly
		else if (exp.matches("0\\s.+\\s.+\\s.+\\s1\\/.+\\s\\? \\*"))
				{
					result.setMonthDate(cronExpArray.get(3));
					result.setMonthNumber(cronExpArray.get(4).substring(2));
					result.setScheduleTIme(cronExpArray.get(2)+":"+cronExpArray.get(1));
				}
		//By Yearly
		else if (exp.matches("0\\s.+\\s.+\\s.+\\s.+\\s\\? \\*"))
				{
					result.setMonthName(cronExpArray.get(4));
					result.setMonthDate(cronExpArray.get(3));
					result.setScheduleTIme(cronExpArray.get(2)+":"+cronExpArray.get(1));
				}
		//By Weekly
		else 
				{
					ArrayList<String>dayname=new ArrayList<String>(Arrays.asList(cronExpArray.get(5).split(",")));
					result.setDayname(dayname);
					result.setAlertHours(cronExpArray.get(2).substring(2));
				}		
		return result;
	}
	public Alert searchAlert(Alert alert)
	{
		Alert result=new Alert();
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "select * from alerttable where alertName='"+alert.getAlertName()+"';"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
		     while (rs.next())
		     {
		    	Alert cronAlertObject=getCronExpressiondetails(rs.getString("AlertTime"));
		    	result.setAlertDay(cronAlertObject.getAlertDay());
		    	result.setAlertHours(cronAlertObject.getAlertHours());
		    	result.setAlertMinutes(cronAlertObject.getAlertMinutes());
		    	result.setDayname(cronAlertObject.getDayname());
		    	result.setInvName(new ArrayList<String>(Arrays.asList(rs.getString("invName").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").split(","))));
		    	result.setMonthDate(cronAlertObject.getMonthDate());
		    	result.setMonthName(cronAlertObject.getMonthName());
		    	result.setMonthNumber(cronAlertObject.getMonthNumber());
		    	result.setScheduleTIme(cronAlertObject.getScheduleTIme());
		    	result.setAlertName(rs.getString("AlertName"));
		    	result.setUserName(new ArrayList<String>(Arrays.asList(rs.getString("userName").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").split(","))));
		    }
		    
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		return result;
	}
	
	
	
	public void removeAlert(Alert alert)
	{
		
		try{
		
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "delete from alerttable where alertName='"+alert.getAlertName()+"';"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     con.close();
		}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		JobDetail job = JobBuilder.newJob(InventoryCountJob.class)
				.withIdentity(alert.getAlertName(), "group1").build();

			try {
				 SchedulerFactory factory = new StdSchedulerFactory();
				    scheduler = factory.getScheduler();
				    scheduler.deleteJob(job.getKey());
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		
	}
	
	public void updateAlert(Alert alert)
	{
		String cronExp=createCronExpression(alert);
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "update alerttable set invName='"+alert.getInvName()+"', userName='"+alert.getUserName()+"' ,AlertTime='"+cronExp+"' where alertName='"+alert.getAlertName()+"';"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     preparedStatement.executeUpdate();
		     con.close();
		     JobDetail job = JobBuilder.newJob(InventoryCountJob.class)
						.withIdentity(alert.getAlertName(), "group1").build();

					// Trigger the job to run on the next round minute
				  Trigger trigger = TriggerBuilder.newTrigger()
		                 .startNow()
		                 .withSchedule(
		                      CronScheduleBuilder.cronSchedule(cronExp)).withIdentity("InventoryCheckJobTrigger", "group1")
		                 .build();

					// schedule it
				
					try {
						 SchedulerFactory factory = new StdSchedulerFactory();
						    scheduler = factory.getScheduler();
						    scheduler.start();
						   
						    //Delete previous Job
						    scheduler.deleteJob(job.getKey());
						    //Schedule a new one
						    scheduler.scheduleJob(job, trigger);
					} catch (SchedulerException e) {
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
		}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		}
	
	public void startSchedulers()
	{
		
		try{
			 startCycleCountScheduler();
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "select * from alerttable;" ;
		    PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
		     while (rs.next())
		     {
		    	 Alert alert=new Alert();
		    	 alert.setAlertName(rs.getString("AlertName"));
		    	 String cronExpression=rs.getString("AlertTime");
		    	 Trigger trigger;
		 		 JobDetail job = JobBuilder.newJob(InventoryCountJob.class)
		 				.withIdentity(alert.getAlertName(), "group1").build();

		 			// Trigger the job to run on the next round minute
		 		  trigger = TriggerBuilder.newTrigger()
		                  .startNow()
		                  .withSchedule(
		                       CronScheduleBuilder.cronSchedule(cronExpression)).withIdentity(alert.getAlertName(), "group1")
		                  .build();

		 			// schedule it
		 		
		 			try {
		 				 SchedulerFactory factory = new StdSchedulerFactory();
		 				    scheduler = factory.getScheduler();
		 				    scheduler.start();
		 				    scheduler.scheduleJob(job, trigger);
		 			} catch (SchedulerException e) {
		 				// TODO Auto-generated catch block
		 			}
		    	 
		    }
		}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		
		
		     }
	
	public void startCycleCountScheduler()
	{
   	 String cronExpression="0 0 0 1/1 * ? *";
   	 Trigger trigger;
		 JobDetail job = JobBuilder.newJob(CycleCountResetJob.class)
				.withIdentity("CycleCountReset", "group2").build();

			// Trigger the job to run on the next round minute
		  trigger = TriggerBuilder.newTrigger()
                 .startNow()
                 .withSchedule(
                      CronScheduleBuilder.cronSchedule(cronExpression)).withIdentity("CycleCountReset", "group2")
                 .build();

			// schedule it
		
			try {
				 SchedulerFactory factory = new StdSchedulerFactory();
				    scheduler = factory.getScheduler();
				    scheduler.start();
				    scheduler.scheduleJob(job, trigger);
			} catch (SchedulerException e) {
				// TODO Auto-generated catch block
			}
	}
	
	public ArrayList<Item> createRestockIInventoryList(String loginID)
	{
		InventoryCountJob inventoryCountJob=new InventoryCountJob();
		ArrayList<Item> result=inventoryCountJob.execute(loginID);
		return result;
		
		
		
	}
		
	public ArrayList<ArrayList<String>> getCategoryAndSubCategoryList(String currentUser)
	{
		ArrayList<ArrayList<String>>list=new ArrayList<ArrayList<String>>();
		 try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
			Statement statement1 = con.createStatement();
	        
	        ResultSet resultset1 = 
	            statement1.executeQuery("select DISTINCT subCategory_name from category;") ; 
			ArrayList<String>categoryList=new ArrayList<String>(); 
			//categoryList.add("Choose One");
			
			ArrayList<String>subCategoryList=new ArrayList<String>();
			ArrayList<String>brandList=new ArrayList<String>();
			ArrayList<String>inventoryList=new ArrayList<String>();
			/*subCategoryList.add("Choose One");
			brandList.add("Choose One");
			inventoryList.add("Choose One");*/
			ArrayList<String>JSONList=new ArrayList<String>();
			while(resultset1.next()) {
				subCategoryList.add(resultset1.getString(1));
			}
			
			
			Statement statement21 = con.createStatement();
		
			
	        ResultSet resultset21 = 
	        		statement21.executeQuery("select accessList from loginprofile where LoginID='"+currentUser+"';") ; 
			while(resultset21.next()) {
				ArrayList<String> invList=new ArrayList<String>(Arrays.asList(resultset21.getString(1).replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").split(",")));
				inventoryList.addAll(invList);
			}
			
			
			Statement statement2 = con.createStatement();
			ResultSet resultset2 = 
					statement2.executeQuery("select DISTINCT category_name from category;") ; 
				while(resultset2.next()) {
				categoryList.add(resultset2.getString(1));
				
				}
				
				
				
				Statement statement3 = con.createStatement();
				ResultSet resultset3 = 
						statement3.executeQuery("select DISTINCT brandName from carbrandtable;") ; 
					while(resultset3.next()) {
						brandList.add(resultset3.getString(1));
					
					}	
					Statement statement4 = con.createStatement();
					ResultSet resultset4 = 
							statement4.executeQuery("select category_name,subCategory_name from category;") ; 
					String catJSONData="{";	
					String preCatName="";
					
					while(resultset4.next()) {
							
							if (preCatName.equals(resultset4.getString(1)))
							{
								catJSONData.replaceFirst("]", "");
								catJSONData=catJSONData+",'"+resultset4.getString(2)+"']";
								
							}
							else
							{
								if (preCatName.equals(""))
									catJSONData=catJSONData+"'"+resultset4.getString(1)+"':['"+resultset4.getString(2)+"'";
								else
									catJSONData=catJSONData+",'"+resultset4.getString(1)+"':['"+resultset4.getString(2)+"'";
							}
							
							
							preCatName=resultset4.getString(1);
						}
					Statement statement5 = con.createStatement();
					  ResultSet resultset5 = 
				                statement5.executeQuery("select sku from inventory;") ; 
							ArrayList<String>skuList=new ArrayList<String>();
							while(resultset5.next()) {
								skuList.add(resultset5.getString(1));
				            }
							Statement statement6 = con.createStatement();
							  ResultSet resultset6 = 
						                statement6.executeQuery("select reasonName from reasontable;") ; 
									ArrayList<String>reasonList=new ArrayList<String>();
									while(resultset6.next()) {
										reasonList.add(resultset6.getString(1));
						            }
									
									
									Statement statement7 = con.createStatement();
									  ResultSet resultset7 = 
											  statement7.executeQuery("select LoginID from loginprofile;") ; 
											ArrayList<String>userList=new ArrayList<String>();
											while(resultset7.next()) {
												userList.add(resultset7.getString(1));
								            }
											
											Statement statement8 = con.createStatement();
											  ResultSet resultset8 = 
													  statement8.executeQuery("select alertName from alerttable;") ; 
													ArrayList<String>alertList=new ArrayList<String>();
													while(resultset8.next()) {
														alertList.add(resultset8.getString(1));
										            }
			catJSONData=catJSONData+"}";		
			JSONList.add(preCatName);	
			list.add(subCategoryList);
			list.add(categoryList);
			list.add(brandList);
			list.add(JSONList);
			list.add(inventoryList);
			list.add(skuList);
			list.add(reasonList);
			list.add(userList);
			list.add(alertList);
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated 	catch block
			e.printStackTrace();
		}
		return list;
	     
		
	}
	
	public TransferObject getTransferOutData(TransferObject object,String condition)
	{
		TransferObject result=new TransferObject();
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
            Statement statement = con.createStatement();
            ResultSet resultset;
            result=object;
            if (condition.equals("1"))
            {
            	resultset = statement.executeQuery("select sku from inventory where invName='"+object.getInvCodeFrom()+"' and category='"+object.getCategoryName()+"' and "
            			+ "subcategory='"+object.getSubCategoryName()+"' and brand='"+object.getBrand()+"' and model='"+object.getModel()+"';") ;
            	while(resultset.next()) {
                	result.setSku(resultset.getString("sku"));
                }
            }
            else if (condition.equals("2"))
            {
            	resultset = statement.executeQuery("select sku,invName,brand,category,subcategory,model from inventory where itemCode='"+object.getItemCodeFrom()+"';") ;
            	while(resultset.next()) {
                	result.setSku(resultset.getString("sku"));
                	result.setInvCodeFrom(resultset.getString("invName"));
                	result.setBrand(resultset.getString("brand"));
                	result.setCategoryName(resultset.getString("category"));
                	result.setSubCategoryName(resultset.getString("subcategory"));
                	result.setModel(resultset.getString("model"));
                }
            	ResultSet resultset2 = statement.executeQuery("select invName from inventory where itemCode='"+object.getItemCodeTo()+"';") ;
            	while(resultset2.next()) {
                	result.setInvCodeTo(resultset2.getString("invName"));
                }
            }
            else
            	{
            	
            	resultset = statement.executeQuery("select brand,category,subcategory,model from inventory where sku='"+object.getSku()+"';") ;
            	while(resultset.next()) {
            		result.setBrand(resultset.getString("brand"));
                	result.setCategoryName(resultset.getString("category"));
                	result.setSubCategoryName(resultset.getString("subcategory"));
                	result.setModel(resultset.getString("model"));
                }
            }
		
        } catch (InstantiationException | IllegalAccessException | ClassNotFoundException | SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return result;
	}
	
	public ArrayList<String>getReasonList()
	{
		ArrayList<String>result=new ArrayList<String>();
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "select reasonName from reasontable;" ;
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
		     while (rs.next())
		     {
		    	 result.add(rs.getString("reasonName"));
		    }
		}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		return result;
	}
	
	
	public ArrayList<Item>createCycleCountInventoryList(String currentUser)
	{
		Item item;
		ArrayList<Item> result=new ArrayList<Item>();
		try{
			 ArrayList<String>accessList=getCategoryAndSubCategoryList(currentUser).get(4);
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     for (int i=0;i<accessList.size();i++)
		     {
		     String query = "select * from inventory where invName='"+accessList.get(i)+"' and cycleCount='0';" ;
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs2 = preparedStatement.executeQuery();
		     int counter=0;
		     while (rs2.next())
		     {
		    	 if (counter<10)
		    	 {
		    		 item=new Item();
			    	 ArrayList<String>positionArray=getItemPosition(rs2.getString("itemCode"),rs2.getString("invName"));
			    	 item.setRack(positionArray.get(0));
			    	 item.setShelf(positionArray.get(1));
			    	 item.setColumn(positionArray.get(2));
			    	 item.setCompartment(positionArray.get(3));
			    	 item.setItemCode(rs2.getString("itemCode"));
			    	 item.setCategoryName(rs2.getString("category"));
			    	 item.setSubCategoryName(rs2.getString("subCategory"));
			    	 item.setInvName(rs2.getString("invName"));
			    	 item.setSku(rs2.getString("sku"));
			    	 item.setBrand(rs2.getString("brand"));
			    	 item.setDescription(rs2.getString("description"));
			    	 item.setNoOfButton(rs2.getString("noOfButtons"));
			    	 result.add(item);
			    	 counter++;
		    	 }
		    }
		   }
		}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		
		
		return result;
		
	}
	public void doCycleCount(HashMap<String,Item> itemList,String currentUser)
	{
		
		try{
			 ArrayList<String>accessList=getCategoryAndSubCategoryList(currentUser).get(4);
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     for (int i=0;i<accessList.size();i++)
		     {
		     String query = "select * from inventory where invName='"+accessList.get(i)+"' and cycleCount='0';" ;
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs2 = preparedStatement.executeQuery();
		     while (rs2.next())
		     {
		    	String numberInInv=rs2.getString("noOfItems");
		    	String key=rs2.getString("invName")+rs2.getString("sku");
		    	if (!(itemList.get(key).getNoOfItems().equals(numberInInv)))
				{
		    		Date dNow = new Date( );
				     SimpleDateFormat ft = 
				     new SimpleDateFormat ("yyyy.MM.dd HH:mm:ss");
				     String query2="insert into descrepency values (null,'"+rs2.getString("sku")+"','"+rs2.getString("invName")+"','"+rs2.getString("brand")+"','"+rs2.getString("itemCode")+"','"+numberInInv+"','"+itemList.get(key).getNoOfItems()+"','"+rs2.getString("category")+"','"+rs2.getString("subcategory")+"','"+currentUser+"','"+ft.format(dNow)+"','"+rs2.getString("model")+"','INITIATED');";
				     PreparedStatement pst = con.prepareStatement(query2);
				     pst.executeUpdate(query2);
				     String query4="update inventory set cycleCount='1' , cycleCountDate='"+ft.format(dNow)+"' where sku='"+rs2.getString("sku")+"' and invName='"+rs2.getString("invName")+"';";
				     PreparedStatement pst4 = con.prepareStatement(query4);
				     pst4.executeUpdate(query4);
				
				}
		    }
		   }
		   String query3="update loginprofile set cycleCountCheck='1' where LoginID='"+currentUser+"';";
				   PreparedStatement pst = con.prepareStatement(query3);
			    pst.executeUpdate(query3);
		     
		}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		
		
	}
	
	public boolean cycleCompletionCheck(String currentUser)
	{
		  String condition="";
		try{ 
		Class.forName("com.mysql.jdbc.Driver").newInstance();
	     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
	     String query = "select cycleCountCheck from loginprofile where LoginID='"+currentUser+"';" ;
	     PreparedStatement preparedStatement = con.prepareStatement(query);	
	     ResultSet rs2 = preparedStatement.executeQuery();
	   
	     while (rs2.next())
	     {
			condition= rs2.getString("cycleCountCheck");
		}
	     
	     
		}  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		if (condition.equals("0"))
			return false;
		else
	     return true;    
	}	
	
	
	
	public void takeLog(String currentUser,String activity,LogObject item)
	{
		  Date dNow = new Date( );
		     SimpleDateFormat ft = 
		     new SimpleDateFormat ("yyyy-MM-dd HH:mm:ss"); 
		   
		try
		{ 
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
	     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
	     String query3="";
	     if (activity.equals("SignIn") || activity.equals("Change Password") || activity.equals("SignOut"))
	      query3="insert into log values (null,null,null,null,null,null,null,null,'"+activity+"','"+ft.format(dNow)+"','"+currentUser+"',null,null,null,null,null,null,null,null)";
	     else if (activity.equals("New Key"))
	    	 query3="insert into log values (null,'"+item.getSku()+"','"+item.getBrand()+"','"+item.getCategory()+"','"+item.getSubCategory()+"','"+item.getInvFrom()+"','"+item.getInvTo()+"','"+item.getReason()+"','"+activity+"','"+ft.format(dNow)+"','"+currentUser+"',null,null,'"+item.getPart1()+"','"+item.getKeyGenerationID()+"',null,'"+item.getItemCode()+"','"+item.getModel()+"',null)"; 
	     else if (activity.equals("Update") || activity.equals("Add Item") )
	    	 query3="insert into log values (null,'"+item.getSku()+"','"+item.getBrand()+"','"+item.getCategory()+"','"+item.getSubCategory()+"','"+item.getInvFrom()+"',null,null,'"+activity+"','"+ft.format(dNow)+"','"+currentUser+"','"+item.getNumber()+"',null,null,null,null,'"+item.getItemCode()+"','"+item.getModel()+"',null)";
	     else if (activity.equals("Initiate Transfer"))
	    	 query3="insert into log values (null,'"+item.getSku()+"','"+item.getBrand()+"','"+item.getCategory()+"','"+item.getSubCategory()+"','"+item.getInvFrom()+"','"+item.getInvTo()+"','"+item.getReason()+"','"+activity+"','"+ft.format(dNow)+"','"+currentUser+"','"+item.getNumber()+"','"+item.getTransferID()+"',null,null,null,null,'"+item.getModel()+"',null)";
	     else if (activity.equals("Create Technician") || activity.equals("Update Technician") || activity.equals("Remove Technician"))
	    	  query3="insert into log values (null,null,null,null,null,null,null,null,'"+activity+"','"+ft.format(dNow)+"','"+currentUser+"',null,null,null,null,'"+item.getTechnician()+"',null,'"+item.getModel()+"',null)";
	     else if (activity.equals("Remove Item"))
	    	 query3="insert into log values (null,'"+item.getSku()+"','"+item.getBrand()+"','"+item.getCategory()+"','"+item.getSubCategory()+"','"+item.getInvFrom()+"',null,null,'"+activity+"','"+ft.format(dNow)+"','"+currentUser+"','"+item.getNumber()+"',null,null,null,null,'"+item.getItemCode()+"','"+item.getModel()+"',null)";
	     else if (activity.equals("Transfer Complete"))
	    	 query3="insert into log values (null,'"+item.getSku()+"','"+item.getBrand()+"','"+item.getCategory()+"','"+item.getSubCategory()+"','"+item.getInvFrom()+"','"+item.getInvTo()+"','"+item.getReason()+"','"+activity+"','"+ft.format(dNow)+"','"+currentUser+"','"+item.getNumber()+"','"+item.getTransferID()+"',null,null,null,null,'"+item.getModel()+"',null)";
	     else if (activity.equals("Recieve Shipment"))
	    	 query3="insert into log values (null,'"+item.getSku()+",null,null,null,'"+item.getInvFrom()+"',null,null,'"+activity+"','"+ft.format(dNow)+"','"+currentUser+"','"+item.getNumber()+"',null,null,null,null,null,'"+item.getModel()+"','"+item.getShipperName()+"')";
	     else if (activity.equals("Remove Category") || activity.equals("Update Category") || activity.equals("Add Category"))
	    	 query3="insert into log values (null,null,null,'"+item.getCategory()+"',null,null,null,null,'"+activity+"','"+ft.format(dNow)+"','"+currentUser+"',null,null,null,null,null,null,'"+item.getModel()+"',null)";
	     else if (activity.equals("Remove Inventory") || activity.equals("Update Inventory") || activity.equals("Add Inventory"))
	    	 query3="insert into log values (null,null,null,'"+item.getCategory()+"',null,'"+item.getInvFrom()+"',null,null,'"+activity+"','"+ft.format(dNow)+"','"+currentUser+"',null,null,null,null,null,null,'"+item.getModel()+"',null)";
	     else if (activity.equals("Remove SubCategory") || activity.equals("Update SubCategory") || activity.equals("Add SubCategory"))
	    	 query3="insert into log values (null,null,null,'"+item.getCategory()+"','"+item.getSubCategory()+"','"+item.getInvFrom()+"',null,null,'"+activity+"','"+ft.format(dNow)+"','"+currentUser+"',null,null,null,null,null,null,'"+item.getModel()+"',null)";
	     else if (activity.equals("Remove Brand") || activity.equals("Update Brand") || activity.equals("Add Brand"))
	    	 query3="insert into log values (null,null,'"+item.getBrand()+"',null,null,null,null,null,'"+activity+"','"+ft.format(dNow)+"','"+currentUser+"',null,null,null,null,null,null,'"+item.getModel()+"',null)";
	     else if (activity.equals("Remove Model") || activity.equals("Update Model") || activity.equals("Add Model"))
	    	 query3="insert into log values (null,null,'"+item.getBrand()+"',null,null,null,null,null,'"+activity+"','"+ft.format(dNow)+"','"+currentUser+"',null,null,null,null,null,null,'"+item.getModel()+"',null)";
	     else if (activity.equals("Remove Reason") || activity.equals("Update Reason") || activity.equals("Add Reason"))
	    	 query3="insert into log values (null,null,'"+item.getBrand()+"',null,null,null,null,'"+item.getReason()+"','"+activity+"','"+ft.format(dNow)+"','"+currentUser+"',null,null,null,null,null,null,'"+item.getModel()+"',null)";
	    
	     //query3="insert into log values (null,'"+item.getSku()+"','"+item.getBrand()+"','"+item.getCategory()+"','"+item.getSubCategory()+"','"+item.getInvFrom()+"','"+item.getInvTo()+"','"+item.getReason()+"','"+activity+"','"+ft.format(dNow)+"','"+currentUser+"','"+item.getNumber()+"','"+item.getTransferID()+"',null,null,null,null,null,null)";
	   System.out.println(query3);
	     PreparedStatement pst = con.prepareStatement(query3);
	    pst.executeUpdate(query3);
	     	}
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		}
	
	
	public ArrayList<String>getLoginIDList()
	{
		ArrayList<String>result=new ArrayList<String>();
		try{ 
			Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     String query = "select LoginID from loginprofile" ;
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs2 = preparedStatement.executeQuery();
		   
		     while (rs2.next())
		     {
		    	 result.add(rs2.getString("LoginID"));
			}
		     
		     
			}  catch(Exception e)
			  {
				 e.printStackTrace();
			  }
		
	return result;	
	}
	
	
	public ArrayList<LogObject> getLog(Login l,String currentUser)
	{
		ArrayList<LogObject> result=new ArrayList<LogObject>();
		if (l.getLogDateRangeFrom().equals(""))
	 		l.setLogDateRangeFrom("2016-11-01");
	 	if (l.getLogDateRangeTo().equals(""))
	 	{
	 		 Date dNow = new Date( );
		     SimpleDateFormat sdf = new SimpleDateFormat ("yyyy-MM-dd"); 
		     String dNow1=sdf.format(dNow);
		     l.setLogDateRangeTo(dNow1);
	 	}
		 try
		{ 
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
	     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
	     String query = "";
	     if (l.getLogCategory().equals("SignIn/SignOut Activity"))
			{
	    	 query="select log1.user,log1.date,min(log1.date) as 'SignIn',max(log2.date) as 'SignOut' from ((select * from log where user='"+l.getLoginID()+"' AND activity='SignIn' and (date BETWEEN '"+l.getLogDateRangeFrom()+"' AND '"+l.getLogDateRangeTo()+"')) as log1) inner  join ((select * from log where user='"+l.getLoginID()+"' AND activity='SignOut' and (date BETWEEN '"+l.getLogDateRangeFrom()+"' AND '"+l.getLogDateRangeTo()+"')) as log2) on DATE(log1.date) = DATE(log2.date);";

	    	 	//query="select * from log where (activity='SignIn' OR activity='SignOut') AND user='"+currentUser+"' AND (date BETWEEN '"+l.getLogDateRangeFrom()+"' AND '"+l.getLogDateRangeTo()+"');";
			}
			else if (l.getLogCategory().equals("Password Change Activity"))
			{
				query="select * from log where activity='Change Password' AND user='"+currentUser+"' AND (date BETWEEN '"+l.getLogDateRangeFrom()+"' AND '"+l.getLogDateRangeTo()+"');";
			}
			else if (l.getLogCategory().equals("New Key"))
			{
				query="select * from log where activity='New Key' AND user='"+currentUser+"' AND (date BETWEEN '"+l.getLogDateRangeFrom()+"' AND '"+l.getLogDateRangeTo()+"');";
			}
			else if (l.getLogCategory().equals("Item"))
			{
				query="select * from log where activity='Update' OR activity='Remove Item' OR activity='Add Item' AND user='"+currentUser+"' AND (date BETWEEN '"+l.getLogDateRangeFrom()+"' AND '"+l.getLogDateRangeTo()+"');";
			}
			else if (l.getLogCategory().equals("Initiated Transfers"))
			{
				query="select * from log where activity='Initiate Transfer' AND user='"+currentUser+"' AND (date BETWEEN '"+l.getLogDateRangeFrom()+"' AND '"+l.getLogDateRangeTo()+"');";
			}
			else if (l.getLogCategory().equals("Completed Transfer"))
			{
				query="select * from log where activity='Transfer Complete' AND user='"+currentUser+"' AND (date BETWEEN '"+l.getLogDateRangeFrom()+"' AND '"+l.getLogDateRangeTo()+"');";
			}
			else if (l.getLogCategory().equals("Technician"))
			{
				query="select * from log where activity='Create Technician' OR activity='Update Technician' OR activity='Remove Technician' AND user='"+currentUser+"' AND (date BETWEEN '"+l.getLogDateRangeFrom()+"' AND '"+l.getLogDateRangeTo()+"');";
			}
			else if (l.getLogCategory().equals("Category"))
			{
				query="select * from log where activity='Add Category' OR activity='Update Category' OR activity='Remove Category' AND user='"+currentUser+"' AND (date BETWEEN '"+l.getLogDateRangeFrom()+"' AND '"+l.getLogDateRangeTo()+"');";
			}
			else if (l.getLogCategory().equals("Sub Category"))
			{
				query="select * from log where activity='Add SubCategory' OR activity='Update SubCategory' OR activity='Remove SubCategory' AND user='"+currentUser+"' AND (date BETWEEN '"+l.getLogDateRangeFrom()+"' AND '"+l.getLogDateRangeTo()+"');";
			}
			else if (l.getLogCategory().equals("Brand"))
			{
				query="select * from log where activity='Add Brand' OR activity='Update Brand' OR activity='Remove Brand' AND user='"+currentUser+"' AND (date BETWEEN '"+l.getLogDateRangeFrom()+"' AND '"+l.getLogDateRangeTo()+"');";
			}
			else if (l.getLogCategory().equals("Model"))
			{
				query="select * from log where activity='Add Model' OR activity='Update Model' OR activity='Remove Model' AND user='"+currentUser+"' AND (date BETWEEN '"+l.getLogDateRangeFrom()+"' AND '"+l.getLogDateRangeTo()+"');";
			}
			else if (l.getLogCategory().equals("Reason"))
			{
				query="select * from log where activity='Add Reason' OR activity='Update Reason' OR activity='Remove Reason' AND user='"+currentUser+"' AND (date BETWEEN '"+l.getLogDateRangeFrom()+"' AND '"+l.getLogDateRangeTo()+"');";
			}
	     if (currentUser.equals(""))
	     {
	    	 query.replaceFirst("AND user='"+currentUser+"'", "");
	     }
	     System.out.println(query);
	     PreparedStatement preparedStatement = con.prepareStatement(query);	
	     ResultSet rs2 = preparedStatement.executeQuery();
	     while (rs2.next())
	     {
	    	 LogObject logObject=new LogObject();
	    	 if (l.getLogCategory().equals("SignIn/SignOut Activity"))
				{
	    		 logObject.setUser(rs2.getString(1));
	    		 logObject.setDate(rs2.getString(2));
	    		 logObject.setLogDateRangeFrom(rs2.getString(3));
	    		 logObject.setLogDateRangeTo(rs2.getString(4));
		    	}
	    	 else
	    	 {
	    		 logObject.setActivity(rs2.getString("activity"));
		    	 logObject.setBrand(rs2.getString("brand"));
		    	 logObject.setCategory(rs2.getString("category"));
		    	 logObject.setSubCategory(rs2.getString("subcategory"));
		    	 logObject.setDate(rs2.getString("date"));
		    	 logObject.setShipperName(rs2.getString("shipperName"));
		    	 logObject.setInvFrom(rs2.getString("invFrom"));
		    	 logObject.setInvTo(rs2.getString("invTo"));
		    	 String query1="";
		    	 if (rs2.getString("itemCode")==null || rs2.getString("itemCode").equals("null") || rs2.getString("itemCode").equals(""))
		    		 query1="select sku,itemCode from inventory where brand='"+rs2.getString("brand")+"' and category='"+rs2.getString("category")+"' and subcategory='"+rs2.getString("subcategory")+"' and invName='"+rs2.getString("invFrom")+"';";
		    	 else
		    		 query1="select sku,itemCode from inventory where itemCode='"+rs2.getString("itemCode")+"';"; 
		    	 PreparedStatement preparedStatement2 = con.prepareStatement(query1);	
			     ResultSet rs = preparedStatement2.executeQuery();
			     String sku="";
			     String itemCode="";
			     while (rs.next())
			     {
			    	 sku=rs.getString("sku");
			    	 itemCode=rs.getString("itemCode");
			     }
			     if (sku.equals(""))
			     {
			    	 logObject.setSku(rs2.getString("sku")); 
			    	 logObject.setItemCode(rs2.getString("itemCode")); 
			     }
			     else
			     {
			    	 logObject.setSku(sku);
				     logObject.setItemCode(itemCode);
				 }
			     if (rs2.getString("brand")==null || rs2.getString("brand").equals("null") || rs2.getString("brand").equals(""))
			     {
			    	 query1="select brand,category,subcategory,itemCode from inventory where sku='"+rs2.getString("sku")+"';"; 
			    	 PreparedStatement preparedStatement3 = con.prepareStatement(query1);	
				     ResultSet rs3 = preparedStatement3.executeQuery();
				     while (rs3.next())
				     {
				    	 logObject.setBrand(rs3.getString("brand"));
				    	 logObject.setCategory(rs3.getString("category"));
				    	 logObject.setSubCategory(rs3.getString("subcategory"));
				    	 logObject.setItemCode(rs3.getString("itemCode"));
				     } 
			     }
			     logObject.setKeyGenerationID(rs2.getString("keyGenerationID"));
		    	 logObject.setNumber(rs2.getString("nOofitems"));
		    	 logObject.setPart1(rs2.getString("Part1"));
		    	 logObject.setModel(rs2.getString("model"));
		    	 logObject.setReason(rs2.getString("reason"));
		    	 logObject.setTechnician(rs2.getString("technician"));
		    	 logObject.setTransferID(rs2.getString("transferID"));
		    	 logObject.setUser(rs2.getString("user"));
		     } 
	    	 result.add(logObject);	  
	    }
	     con.close();
	    }
		catch(Exception e)
		{
		e.printStackTrace();	
		}
		 return result;
		}
	
	
	public ArrayList<String> getInvList()
	{
		
		ArrayList<String>result1=new ArrayList<String>();
		
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		      String query = "select inventoryName from invtable ;";
			     PreparedStatement preparedStatement = con.prepareStatement(query);	
			     ResultSet rs = preparedStatement.executeQuery();
			     while (rs.next())
			     {
			    	 result1.add(rs.getString("inventoryName"));
			     }
			    con.close();
				}
		  catch(Exception e)
		  {
			 e.printStackTrace();
			
		  }
		return result1;
	}
	
	public ArrayList<String> getUserList()
	{
		
		ArrayList<String>result1=new ArrayList<String>();
		
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		      String query = "select LoginID from loginprofile ;";
			     PreparedStatement preparedStatement = con.prepareStatement(query);	
			     ResultSet rs = preparedStatement.executeQuery();
			     while (rs.next())
			     {
			    	 result1.add(rs.getString("LoginID"));
			     }
			    con.close();
				}
		  catch(Exception e)
		  {
			 e.printStackTrace();
			
		  }
		return result1;
	}
	public Multimap<String, String>getLoginIDList1()
	{
		Multimap<String, String> multimap = ArrayListMultimap.create();
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		      String query = "select LoginID,accessList from loginprofile;";
			     PreparedStatement preparedStatement = con.prepareStatement(query);	
			     ResultSet rs = preparedStatement.executeQuery();
			     while (rs.next())
			     {
			    	 ArrayList<String>invList=new ArrayList<String>(Arrays.asList(rs.getString("accessList").replace("[", "").replace("]", "").split(",")));
			    	 for (int i=0;i<invList.size();i++)
			    	 {
			    		 multimap.put(rs.getString("LoginID"), invList.get(i)) ;	 
			    	 }
			    }
		         con.close();
				}
		  catch(Exception e)
		  {
			 e.printStackTrace();
			
		  }
		
		return multimap;
	
		

	}
	
	
	public ArrayList<LogObject> getDiscrepancies(LogObject logObject1)
	{
		
		ArrayList<LogObject>result1=new ArrayList<LogObject>();
		
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		      String query = "select * from descrepency where employee='"+logObject1.getUser()+"' AND invName='"+logObject1.getInvFrom()+"' AND status='INITIATED';";
		    
			     PreparedStatement preparedStatement = con.prepareStatement(query);	
			     ResultSet rs2 = preparedStatement.executeQuery();
			     while (rs2.next())
			     {
			    	 LogObject logObject=new LogObject();
			    	 logObject.setBrand(rs2.getString("brand"));
			    	 logObject.setModel(rs2.getString("model"));
			    	 logObject.setCategory(rs2.getString("category"));
			    	 logObject.setSubCategory(rs2.getString("subcategory"));
			    	 logObject.setDate(rs2.getString("date"));
			    	 logObject.setInvFrom(rs2.getString("invName"));
			    	 logObject.setNumber(rs2.getString("invNoOfItems"));
			    	 logObject.setPart1(rs2.getString("userNoOfItems"));
			    	 logObject.setSku(rs2.getString("sku"));
			    	 logObject.setItemCode(rs2.getString("itemCode"));
			    	 logObject.setUser(rs2.getString("employee"));
			    	 result1.add(logObject);
			    }
			    con.close();
				}
		  catch(Exception e)
		  {
			 e.printStackTrace();
			
		  }
		return result1;
	}
	
	
	public String discrepanciesCorrection(DiscrepanciesObject discrepanciesObject)
	{
		 String result="";
		
		 int difference=Integer.valueOf(discrepanciesObject.getInvNoOfItems())-Integer.valueOf(discrepanciesObject.getUserNoOfItems());
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
	     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
	     String query = "select noOfItems from inventory where itemCode='"+discrepanciesObject.getItemCode()+"';";
	     String presentNoOfItems="";
	     PreparedStatement preparedStatement = con.prepareStatement(query);	
	     ResultSet rs2 = preparedStatement.executeQuery();
	     while (rs2.next())
	     {
	    	 presentNoOfItems=rs2.getString("noOfItems");
	    }
	     int correctedValue=Integer.valueOf(presentNoOfItems)-difference;
		 String query2="update descrepency set status='RESOLVED' where sku='"+discrepanciesObject.getSku()+"'  AND invName='"+discrepanciesObject.getInvName()+"' AND brand='"+discrepanciesObject.getBrand()+"' AND itemCode='"+discrepanciesObject.getItemCode()+"' AND employee='"+discrepanciesObject.getUser()+"';";
		 PreparedStatement pst = con.prepareStatement(query2);
	     pst.executeUpdate(query2);
	     String query4="update inventory set noOfItems='"+correctedValue+"' where itemCode='"+discrepanciesObject.getItemCode()+"';";
	     PreparedStatement pst4 = con.prepareStatement(query4);
	     pst4.executeUpdate(query4);
	     result="Operation Successful";
		 }
		 catch(Exception e)
		 {
			 result="Error Occured";
			 result=result+"<br>"+e.toString();
			 e.printStackTrace();
		 }
		return result;
		
	}
	
	public String assignModelTrimItem(ArrayList<Item>assignmentList)
	{
		String brandList="";
		String modelList="";
		String TrimList="";
		String fromYearList="";
		String toYearList="";
		for (int i=0;i<assignmentList.size();i++)
		{
			if (i==(assignmentList.size()-1))
			{
				brandList=brandList+assignmentList.get(i).getBrand();
				modelList=modelList+assignmentList.get(i).getModel();
				TrimList=TrimList+assignmentList.get(i).getTrim();
				fromYearList=fromYearList+assignmentList.get(i).getFromYear();
				toYearList=toYearList+assignmentList.get(i).getToYear();
			}
			else
			{
				brandList=brandList+assignmentList.get(i).getBrand()+",";
				modelList=modelList+assignmentList.get(i).getModel()+",";
				TrimList=TrimList+assignmentList.get(i).getTrim()+",";
				fromYearList=fromYearList+assignmentList.get(i).getFromYear()+",";
				toYearList=toYearList+assignmentList.get(i).getToYear()+",";
			}
			
		}
		 String result="";
		 try
		 {
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
	     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
	     String query2="update inventory set brand ='"+brandList+"' , model ='"+modelList+"' ,trim ='"+TrimList+"' ,fromYear ='"+fromYearList+"' ,toYear ='"+toYearList+"'  where sku='"+assignmentList.get(0).getSku()+"'  AND brand='null';";
		 PreparedStatement pst = con.prepareStatement(query2);
	     pst.executeUpdate(query2);
	     result="Assignment Complete";
		 }
		 catch(Exception e)
		 {
			 result="Error Occured";
			 result=result+"<br>"+e.toString();
			 e.printStackTrace();
		 }
		return result;
		
	}
	public Item getItemInfo(Item object)
	{
		
		
		Item logObject=new Item();
		try{
			
		      Class.forName("com.mysql.jdbc.Driver").newInstance();
		      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		      String query =""; 
		      if (object.getSku().equals(""))
		      {
		    	if (object.getItemCode().equals(""))
		    	{
		    		query = "select * from inventory where invName='"+object.getInvName()+"' AND brand='"+object.getSku()+"' AND category='"+object.getCategoryName()+"' AND subCategory='"+object.getSubCategoryName()+"' AND model='"+object.getModel()+"';";
		    	}
		    	else
		    		  query = "select * from inventory where itemCode='"+object.getItemCode()+"';";
		      }
		      else
		      query = "select * from inventory where invName='"+object.getInvName()+"' AND sku='"+object.getSku()+"';";
		    
			     PreparedStatement preparedStatement = con.prepareStatement(query);	
			     ResultSet rs2 = preparedStatement.executeQuery();
			     while (rs2.next())
			     {
			    	 
			    	 logObject.setBrand(rs2.getString("brand"));
			    	 logObject.setModel(rs2.getString("model"));
			    	 logObject.setCategoryName(rs2.getString("category"));
			    	 logObject.setSubCategoryName(rs2.getString("subcategory"));
			    	 logObject.setInvName(rs2.getString("invName"));
			    	 logObject.setNoOfItems(rs2.getString("noOfItems"));
			    	 logObject.setSku(rs2.getString("sku"));
			    	 logObject.setItemCode(rs2.getString("itemCode"));
			    }
			    con.close();
				}
		  catch(Exception e)
		  {
			 e.printStackTrace();
			
		  }
		return logObject;
	}




public ArrayList<Item> getUnassignedItems()
{
	
	
	ArrayList<Item>result1=new ArrayList<Item>();
	
	try{
		
	      Class.forName("com.mysql.jdbc.Driver").newInstance();
	      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
	      String query =""; 
	      query = "select * from inventory where itemCode='null' and brand='null';";
	       PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs2 = preparedStatement.executeQuery();
		     while (rs2.next())
		     {
		    	 Item result=new Item();
		    	 result.setCategoryName(rs2.getString("category"));
		    	 result.setSubCategoryName(rs2.getString("subCategory"));
		    	 result.setSku(rs2.getString("sku"));
		    	 result.setDescription(rs2.getString("description"));
		    	 result.setNoOfButton(rs2.getString("noOfButtons"));
		    	 result.setiC(rs2.getString("ic"));
		    	 result.setBatteryPartNumber("batteryPartNumber");
		    	 result1.add(result);
		     
		     }
		    con.close();
			}
	  catch(Exception e)
	  {
		 e.printStackTrace();
		
	  }
	return result1;
}


public ArrayList<Item> getUnassignedInventoryItems()
{
	
	
	ArrayList<Item>result1=new ArrayList<Item>();
	
	try{
		
	      Class.forName("com.mysql.jdbc.Driver").newInstance();
	      Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
	      String query =""; 
	      query = "select * from inventory where itemCode='null' ;";
	       PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs2 = preparedStatement.executeQuery();
		     while (rs2.next())
		     {
		    	 Item result=new Item();
		    	 result.setCategoryName(rs2.getString("category"));
		    	 result.setSubCategoryName(rs2.getString("subCategory"));
		    	 result.setBrand(rs2.getString("brand"));
		    	 result.setModel(rs2.getString("model"));
		    	 result.setTrim(rs2.getString("trim"));
		    	 result.setFromYear(rs2.getString("fromYear"));
		    	 result.setToYear(rs2.getString("toYear"));;
		    	 result.setNoOfItems(rs2.getString("noOfItems"));
		    	 result.setSku(rs2.getString("sku"));
		    	 result.setDescription(rs2.getString("description"));
		    	 result.setNoOfButton(rs2.getString("noOfButtons"));
		    	 result.setiC(rs2.getString("ic"));
		    	 result.setBatteryPartNumber("batteryPartNumber");
		    	 result1.add(result);
		     
		     }
		    con.close();
			}
	  catch(Exception e)
	  {
		 e.printStackTrace();
		
	  }
	return result1;
}
}
