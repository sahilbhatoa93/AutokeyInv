package dao;

import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

//File Name SendHTMLEmail.java

import java.util.*;
import javax.mail.*;
import javax.mail.internet.*;

import org.springframework.beans.factory.annotation.Value;

import com.mysql.jdbc.Connection;

import model.Alert;

	public class SendHTMLEmail {
		
		
		
		private static String senderEmail;
		
		private static String senderPassword;
		
		public SendHTMLEmail(String senderEmail,String senderPassword)
		{
		this.senderEmail=senderEmail;
		this.senderPassword=senderPassword;
		
		}
		
		public SendHTMLEmail()
		{
			
			
		}
		
		
		
		
		public void sendEmail( ArrayList<String>inventoryNameList,HashMap<String, ArrayList<String>> secondaryData,ArrayList<String>senderList,ArrayList<String>checkInvList) {
			Properties props = new Properties();
			props.put("mail.smtp.host", "smtp.gmail.com");
			props.put("mail.smtp.socketFactory.port", "465");
			props.put("mail.smtp.socketFactory.class",
					"javax.net.ssl.SSLSocketFactory");
			props.put("mail.smtp.auth", "true");
			props.put("mail.smtp.port", "465");

			Session session = Session.getDefaultInstance(props,
				new javax.mail.Authenticator() {
					protected PasswordAuthentication getPasswordAuthentication() {
						return new PasswordAuthentication(senderEmail,senderPassword);
					}
				});

			try {
				 
				for (int s=0;s<senderList.size();s++)
				{
				String receiverEmail=getSenderEmail(senderList.get(s));	
				Message message = new MimeMessage(session);
				message.setFrom(new InternetAddress(senderEmail));
				message.setRecipients(Message.RecipientType.TO,
						InternetAddress.parse(receiverEmail));
				message.setSubject("Inventory Restock Update");
				String emailMessage="Hi,"+
				 "\n\n The following items need to be restocked :-";
				inventoryNameList.retainAll(checkInvList);
				for (int i=0;i<inventoryNameList.size();i++)
				{
					emailMessage=emailMessage+
							 "\n\n\n\n"+inventoryNameList.get(i);
					emailMessage=emailMessage+
							"\nSKU         Item Code         Category          Sub Category          Model           Number Of Items Left";
					
					for (int j=0;j<secondaryData.get(inventoryNameList.get(i)+" code").size();j++)
					{
						emailMessage=emailMessage+
								 "\n\n"+secondaryData.get(inventoryNameList.get(i)+" sku").get(j)+"                   "+secondaryData.get(inventoryNameList.get(i)+" code").get(j)+"                   "+secondaryData.get(inventoryNameList.get(i)+
										 " category").get(j)+ "                "+secondaryData.get(inventoryNameList.get(i)+" sub category").get(j)+ "                "+secondaryData.get(inventoryNameList.get(i)+" model").get(j)+
								 "                   "+secondaryData.get(inventoryNameList.get(i)+" number").get(j);
						
					}
				}
				message.setText(emailMessage);
				Transport.send(message);
				}
				
			} catch (MessagingException e) {
				throw new RuntimeException(e);
			}
				
		}
		
		
		public String getSenderEmail(String id)
		{
		String receiverEmail="";	
		try{
			
		     Class.forName("com.mysql.jdbc.Driver").newInstance();
		     Connection con = (Connection)DriverManager.getConnection("jdbc:mysql://localhost/autokey_inventory","root","sahil");
		     String query = "select email from loginprofile where LoginID='"+id+"';"; 
		     PreparedStatement preparedStatement = con.prepareStatement(query);	
		     ResultSet rs = preparedStatement.executeQuery();
		     while (rs.next())
		     {
		    	 receiverEmail=rs.getString("email");
		     }
		    
			}
		  catch(Exception e)
		  {
			 e.printStackTrace();
		  }
			
			
			return receiverEmail;
		}
		
		
	}
