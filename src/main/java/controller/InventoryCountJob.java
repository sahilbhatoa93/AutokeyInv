package controller;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.LinkedHashSet;
import java.util.Set;

import org.quartz.Job;
import org.quartz.JobExecutionContext;
import org.quartz.JobExecutionException;

import com.mysql.jdbc.Connection;

import dao.SendHTMLEmail;
import model.Item;

public class InventoryCountJob implements Job{
	

	private static String url;
	private static String dbName;
	private static String usr;
	private static String password;
	
	public InventoryCountJob(String dbName,String password,String url,String usr)
	{
	this.dbName=dbName;
	this.password=password;
	this.url="jdbc:mysql://"+url+"/";
	this.usr=usr;
		
		
	}
	
	public InventoryCountJob()
	{
		
		
	}
	
	
	 
	
	public void execute(JobExecutionContext context) throws JobExecutionException {
	    
		try{
			 String schedulerName=context.getJobDetail().getKey().getName();
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     ArrayList<String>skuList=new ArrayList<String>();
		     Set<String> InvNameList = new LinkedHashSet<>();
		    
		
		     ArrayList<String>inventoryLimitList=new ArrayList<String>();
		     String query = "select invName,restocklimit,sku from inventory";
		     PreparedStatement preparedStatement1 = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement1.executeQuery();
		     while (rs.next())
		     {
		    	 inventoryLimitList.add(rs.getString(2)); 
		    	 skuList.add(rs.getString(1)+"&&"+rs.getString(3)); 
		    	
		     }
		     
		     ArrayList<String>checkInvList=null;
		     ArrayList<String>senderList = null;
		     String query4 = "select * from alerttable where AlertName='"+schedulerName+"'";
		     PreparedStatement preparedStatement4 = con.prepareStatement(query4);	
		     ResultSet rs4 = preparedStatement4.executeQuery();
		     while (rs4.next())
		     {
		    	  checkInvList=new ArrayList<String>(Arrays.asList(rs4.getString("invName").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").split(",")));
		    	  senderList=new ArrayList<String>(Arrays.asList(rs4.getString("userName").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").split(",")));
		     }
		     HashMap<String, ArrayList<String>>secondaryData=new HashMap<String, ArrayList<String>>();
		     int counter=0;
		     String prevINvName="";
		     ArrayList<String>itemCodeList=new ArrayList<String>();
		     ArrayList<String>remainingNumberList=new ArrayList<String>();
		     ArrayList<String>categoryList=new ArrayList<String>();
		     ArrayList<String>subCategoryList=new ArrayList<String>();
		     ArrayList<String>modelList=new ArrayList<String>();
		     ArrayList<String>skuList1=new ArrayList<String>();
		     
		     for (int i=0;i<skuList.size();i++)
		     {
		    	 String currentinvName=skuList.get(i).split("&&")[0];
		    	 if (prevINvName.equals("") || prevINvName.equals(currentinvName))
		    	 {
		    		//Do Nothing 
		    	 }
		    	 else
		    	 {
		    		 itemCodeList=new ArrayList<String>();
				     remainingNumberList=new ArrayList<String>();
				     categoryList=new ArrayList<String>();
				     subCategoryList=new ArrayList<String>();
				     skuList1=new ArrayList<String>();
		    	 }
		    	 prevINvName=currentinvName;
		    	 String currentsku=skuList.get(i).split("&&")[1];
		    	 String query2 = "select itemCode,category,noOfItems,subCategory,invName,sku,model from inventory where noOfItems < "+inventoryLimitList.get(i)+" and sku='"+currentsku+"' and invName='"+currentinvName+"';";
		    	 PreparedStatement preparedStatement2 = con.prepareStatement(query2);	
			     ResultSet rs2 = preparedStatement2.executeQuery();
			     
			     while (rs2.next())
			     {
			    	 counter++;
			    	 itemCodeList.add(rs2.getString(1)); 
			    	 categoryList.add(rs2.getString(2));
			    	 remainingNumberList.add(rs2.getString(3));
			    	 subCategoryList.add(rs2.getString(4));
			    	 InvNameList.add(rs2.getString(5));
			    	 skuList1.add(rs2.getString(6));
			    	 modelList.add(rs2.getString(7));
			     }
			     secondaryData.put(skuList.get(i).split("&&")[0]+" code", itemCodeList);
			     secondaryData.put(skuList.get(i).split("&&")[0]+" number", remainingNumberList);
			     secondaryData.put(skuList.get(i).split("&&")[0]+" category", categoryList);
			     secondaryData.put(skuList.get(i).split("&&")[0]+" sub category", subCategoryList);
			     secondaryData.put(skuList.get(i).split("&&")[0]+" sku", skuList1);
			     secondaryData.put(skuList.get(i).split("&&")[0]+" model", modelList);
			     
		     }
			   if (counter!=0)
		     { 
		      ArrayList<String>InvNameList1=new ArrayList<String>();
		      InvNameList1.addAll(InvNameList);
		     SendHTMLEmail s=new SendHTMLEmail();
				s.sendEmail(InvNameList1,secondaryData,senderList,checkInvList); 
		     }
		
		     con.close();
		     }
		catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		
		
		
		
		
	}
	
public ArrayList<Item> execute(String loginID) {
		ArrayList<Item> result=new ArrayList<Item>();
	 	Item item=new Item();
		try{
			 Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
		     ArrayList<String>skuList=new ArrayList<String>();
		    
		     ArrayList<String>inventoryLimitList=new ArrayList<String>();
		     String query = "select invName,restocklimit,sku from inventory";
		     PreparedStatement preparedStatement1 = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement1.executeQuery();
		     while (rs.next())
		     {
		    	 inventoryLimitList.add(rs.getString(2)); 
		    	 skuList.add(rs.getString(1)+"&&"+rs.getString(3));
		    	
		    	
		     }
		     
		     ArrayList<String>checkInvList=null;
		     String query4 = "select accessList from loginprofile where loginid='"+loginID+"'";
		     PreparedStatement preparedStatement4 = con.prepareStatement(query4);	
		     ResultSet rs4 = preparedStatement4.executeQuery();
		     while (rs4.next())
		     {
		    	  checkInvList=new ArrayList<String>(Arrays.asList(rs4.getString("accessList").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").split(",")));
		     }
		     String prevINvName="";
		     for (int i=0;i<skuList.size();i++)
		     {
		    	 String currentinvName=skuList.get(i).split("&&")[0];
		    	 if (checkInvList.contains(currentinvName))
		    	 {
		    	 if (prevINvName.equals("") || prevINvName.equals(currentinvName))
		    	 {
		    		//Do Nothing 
		    	 }
		    	 else
		    	 {
		    		 item=new Item();
		    	 }
		    	 prevINvName=currentinvName;
		    	 String currentsku=skuList.get(i).split("&&")[1];
		    	 
		    	 String query2 = "select * from inventory where noOfItems < "+inventoryLimitList.get(i)+" and sku='"+currentsku+"' and invName='"+currentinvName+"';";
		    	 PreparedStatement preparedStatement2 = con.prepareStatement(query2);	
			     ResultSet rs2 = preparedStatement2.executeQuery();
			     while (rs2.next())
			     {
			    	 item=new Item();
			    	 ArrayList<String>positionArray=getItemPosition(rs2.getString("itemCode"),rs2.getString("invName"));
			    	 item.setRack(positionArray.get(0));
			    	 item.setShelf(positionArray.get(1));
			    	 item.setColumn(positionArray.get(2));
			    	 item.setCompartment(positionArray.get(3));
			    	 item.setItemCode(rs2.getString("itemCode"));
			    	 item.setCategoryName(rs2.getString("category"));
			    	 item.setNoOfItems(rs2.getString("noOfItems"));
			    	 item.setSubCategoryName(rs2.getString("subCategory"));
			    	 item.setInvName(rs2.getString("invName"));
			    	 item.setSku(rs2.getString("sku"));
			    	 item.setBrand(rs2.getString("brand"));
			    	 item.setDescription(rs2.getString("description"));
			    	 item.setFfcId(rs2.getString("fccId"));
			    	 item.setiC(rs2.getString("ic"));
			    	 item.setNoOfButton(rs2.getString("noOfButtons"));
			    	 item.setButtonConfiguration(rs2.getString("buttonConfiguration"));
			    	 item.setBatteryPartNumber(rs2.getString("batteryPartNUmber"));
			    	 item.setRestockLimit(rs2.getString("restocklimit"));
			    	 item.setModel(rs2.getString("model"));
			    	 item.setTrim(rs2.getString("trim"));
			    	 result.add(item);
			    	 	 
			     }
			     }
			     
		     }
			 con.close();
		     }
		catch(Exception e)
		  {
			 e.printStackTrace();
		  }
		
		return result;
	}

public  ArrayList<Item> getReorderData(String loginID) {
	ArrayList<Item> result=new ArrayList<Item>();
	   HashMap<String, Item>hashMap=new HashMap<String, Item>();
 	Item item=new Item();
	try{
		 Class.forName("com.mysql.jdbc.Driver").newInstance();
	     Connection con = (Connection)DriverManager.getConnection(url+dbName,usr,password);
	     ArrayList<String>skuList=new ArrayList<String>();
	    
	     ArrayList<String>inventoryLimitList=new ArrayList<String>();
	     String query = "select invName,restocklimit,sku from inventory";
	     PreparedStatement preparedStatement1 = con.prepareStatement(query);	
	     ResultSet rs = preparedStatement1.executeQuery();
	     while (rs.next())
	     {
	    	 inventoryLimitList.add(rs.getString(2)); 
	    	 skuList.add(rs.getString(1)+"&&"+rs.getString(3));
	    	
	    	
	     }
	     
	     ArrayList<String>checkInvList=null;
	     String query4 = "select accessList from loginprofile where loginid='"+loginID+"'";
	     PreparedStatement preparedStatement4 = con.prepareStatement(query4);	
	     ResultSet rs4 = preparedStatement4.executeQuery();
	     while (rs4.next())
	     {
	    	  checkInvList=new ArrayList<String>(Arrays.asList(rs4.getString("accessList").replaceAll("\\[", "").replaceAll("\\]", "").replaceAll(" ", "").split(",")));
	     }
	     String prevINvName="";
	     for (int i=0;i<skuList.size();i++)
	     {
	    	 String currentinvName=skuList.get(i).split("&&")[0];
	    	 if (checkInvList.contains(currentinvName))
	    	 {
	    	 if (prevINvName.equals("") || prevINvName.equals(currentinvName))
	    	 {
	    		//Do Nothing 
	    	 }
	    	 else
	    	 {
	    		 item=new Item();
	    	 }
	    	 prevINvName=currentinvName;
	    	 String currentsku=skuList.get(i).split("&&")[1];
	    	 
	    	 String query2 = "select * from inventory where noOfItems < "+inventoryLimitList.get(i)+" and sku='"+currentsku+"' and invName='"+currentinvName+"';";
	    	 PreparedStatement preparedStatement2 = con.prepareStatement(query2);	
		     ResultSet rs2 = preparedStatement2.executeQuery();
		  
		     while (rs2.next())
		     {
		    	 item=new Item();
		    	 ArrayList<String>positionArray=getItemPosition(rs2.getString("itemCode"),rs2.getString("invName"));
		    	 item.setRack(positionArray.get(0));
		    	 item.setShelf(positionArray.get(1));
		    	 item.setColumn(positionArray.get(2));
		    	 item.setCompartment(positionArray.get(3));
		    	 item.setItemCode(rs2.getString("itemCode"));
		    	 item.setCategoryName(rs2.getString("category"));
		    	 int itemToOrder=Integer.valueOf(inventoryLimitList.get(i))-Integer.valueOf(rs2.getString("noOfItems"));
		    	 item.setNoOfItems(String.valueOf(itemToOrder));
		    	 item.setSubCategoryName(rs2.getString("subCategory"));
		    	 item.setInvName(rs2.getString("invName"));
		    	 item.setSku(rs2.getString("sku"));
		    	 item.setBrand(rs2.getString("brand"));
		    	 item.setDescription(rs2.getString("description"));
		    	 item.setFfcId(rs2.getString("fccId"));
		    	 item.setiC(rs2.getString("ic"));
		    	 item.setNoOfButton(rs2.getString("noOfButtons"));
		    	 item.setButtonConfiguration(rs2.getString("buttonConfiguration"));
		    	 item.setBatteryPartNumber(rs2.getString("batteryPartNUmber"));
		    	 item.setRestockLimit(rs2.getString("restocklimit"));
		    	 item.setModel(rs2.getString("model"));
		    	 if (hashMap.containsKey(rs2.getString("sku")))
		    	 {
		    		 String number=hashMap.get(rs2.getString("sku")).getNoOfItems();
		    		 int cumulativeNumber= Integer.valueOf(number)+Integer.valueOf(item.getNoOfItems());
		    		 item.setNoOfItems(String.valueOf(cumulativeNumber));
		    		 hashMap.put(rs2.getString("sku"), item);
		    		 
		    	 }
		    	 else
		    	 {
		    		 hashMap.put(rs2.getString("sku"), item);
		    	 }
		     }
		     }
		     
	     }
		 con.close();
	     }
	catch(Exception e)
	  {
		 e.printStackTrace();
	  }
	
	for (int i=0;i<hashMap.size();i++)
	{
		String key=(String)hashMap.keySet().toArray()[i];
		result.add(hashMap.get(key));
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
	
}
