package controller;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Properties;

import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.apache.log4j.Priority;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.google.common.collect.Multimap;
import dao.DB;
import dao.SendHTMLEmail;
import dao.TestPrint;
import model.Alert;
import model.DiscrepanciesObject;
import model.Inventory;
import model.Item;
import model.Key;
import model.LogObject;
import model.Login;
import model.SearchObject;
import model.TransferObject;


@Controller
public class BaseController {
	

	 public static String PARAM_BASE_URL = "baseURL";
	 private String currentUser="";
	 //get base URL
	    public String getBaseURL(HttpServletRequest request){
	        return request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort() + request.getContextPath();
	    }
	private DB dB;
	private ArrayList<TransferObject>groupTransferList=new ArrayList<TransferObject>();
	
/*	@Value("${ip.address}")
	public  String url;
	
	@Value("${DB.name}")
	public  String dbName;
	
	@Value("${admin.username}")
	public  String usr;
	
	@Value("${admin.password}")
	public  String password;
	
	@Value("${sender.email}")
	public  String senderEmail;
	
	@Value("${sender.password}")
	public  String senderPassword;
	*/
	
	public  String url;
	
	
	public  String dbName;
	
	
	public  String usr;
	
	
	public  String password;
	
	
	public  String senderEmail;
	
	
	public  String senderPassword;
	
	
	@SuppressWarnings("deprecation")
	@RequestMapping("/login")
	public ModelAndView loginpage(Login l)
	{
		try {
			 Properties prop = new Properties();
			 InputStream input = new FileInputStream("/propertiesFileFolder/AutoKeyinventorySoftwareProperties.properties");
			// InputStream input = new FileInputStream("E:/AutoKeyinventorySoftwareProperties.properties");
			 prop.load(input);   
			 url= prop.getProperty("ip.address");
			 dbName =prop.getProperty("DB.name");
			 usr=prop.getProperty("admin.username");
			 password =prop.getProperty("admin.password");
			 senderEmail=prop.getProperty("sender.email");
			 senderPassword =prop.getProperty("sender.password");
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		final Logger logger = Logger.getLogger(BaseController.class);	
		new DB( dbName, password, url, usr);
		new SendHTMLEmail(senderEmail,senderPassword);
		new InventoryCountJob( dbName, password, url, usr);
		new CycleCountResetJob( dbName, password, url, usr);
		logger.log(Priority.INFO, "Properties File Loaded with properties:-");
		logger.log(Priority.INFO, "url="+url);
		logger.log(Priority.INFO, "dbName="+dbName);
		logger.log(Priority.INFO, "userName For DB="+usr);
		logger.log(Priority.INFO, "Password For DB="+password);
		logger.log(Priority.INFO, "Scheduler Email="+senderEmail);
		logger.log(Priority.INFO, "Scheduler Email Password="+senderPassword);
		dB=new DB();
		dB.startSchedulers();
		return new ModelAndView("login");
	}
	
	
	@RequestMapping("/makenewkey")
	public ModelAndView openMakeNewkeyPage()
	{
		DB db=new DB();
		ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
		ArrayList<HashMap<String, String>>locationMap=db.getLocationMap();
		Multimap<String, String>categoryMap=db.getCategoryMap();
		ModelAndView modelAndView=new ModelAndView("makenewkey");
		modelAndView.addObject("senderInventoryList",list.get(4));
		ArrayList<String>receiverInventoryList=new ArrayList<String>();
		receiverInventoryList.addAll(list.get(4));
		receiverInventoryList.remove("HEADQUATERS");
		modelAndView.addObject("receiverInventoryList",receiverInventoryList);
		modelAndView.addObject("subCategoryList",list.get(0));
		modelAndView.addObject("categoryList",list.get(1));
		modelAndView.addObject("brandList",list.get(2));
		String locationMapString=locationMap.get(1).toString().replaceAll("=", ":").replaceAll(" ", "");
		modelAndView.addObject("locationMap",locationMapString);
		String categoryMapString=categoryMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		modelAndView.addObject("categoryMap",categoryMapString);
		Multimap<String, String>brandMap=db.getBrandMap();
		String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		modelAndView.addObject("brandMap",brandMapString);
		Multimap<String, String>modelMap=db.getmodelMap();
		String modelMapString=modelMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		modelAndView.addObject("modelMap",modelMapString);
		ArrayList<String>transponderList=db.getTransponderList();
		String transponderListString=transponderList.toString().replaceAll("\\[", "'").replaceAll("\\]", "'");
		modelAndView.addObject("transponderList",transponderListString);
		return modelAndView;
	}
	
	 
	 
	@RequestMapping("/loginRequest")
	public ModelAndView login(Login l,HttpServletRequest request, HttpServletResponse response )
	{
		try {
			request.getRequestDispatcher("login").include(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} 
		dB=new DB();
		currentUser=l.getLoginID();
		ArrayList<String>result=dB.authorizeCredentials(l);
		
		if (result.get(0).equals("1"))
		{
			if (result.get(1).equals("1"))
			{
				if (result.get(2).equals("1"))
				{
					if (l.getRole().equalsIgnoreCase("admin"))
					{
						 HttpSession session=request.getSession();  
					      session.setAttribute("name",l.getLoginID());  
						try {
							request.getRequestDispatcher("adminoptions").include(request, response);
						} catch (ServletException | IOException e) {
							// TODO Auto-generated catch block
							e.printStackTrace();
						} 
						return new ModelAndView("adminoptions");		
					}
					else
					{
						  HttpSession session=request.getSession();  
					      session.setAttribute("name",l.getLoginID());  
					      currentUser=l.getLoginID();
					      if (dB.cycleCompletionCheck(currentUser))
					      {
					    	  try {
									request.getRequestDispatcher("employeeoptions").include(request, response);
								} catch (ServletException | IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
						      
							return new ModelAndView("employeeoptions","name",currentUser); 
					    	  
					      }
					      else
					      {
					    	  ArrayList<Item>dataList=dB.createCycleCountInventoryList(currentUser);
					    	  try {
									request.getRequestDispatcher("employeeoptions").include(request, response);
								} catch (ServletException | IOException e) {
									// TODO Auto-generated catch block
									e.printStackTrace();
								} 
						      if (dataList.size()==0)
						    	return new ModelAndView("employeeoptions","name",currentUser);
						      else
					    	  return new ModelAndView("cycleCount","dataList",dataList);
					      } 
					    
							
					      
						
					}
							
				}
				else
					return new ModelAndView("error");
			}
		else
		{
			return new ModelAndView("error");
		}
		
	}
		else
			return new ModelAndView("error");
	
	}
	private Key generatedKey;
	@RequestMapping("/newkeyentry")
	public ModelAndView newKeyEntryRequest(Key k,@RequestParam String result)
	{
		
		dB=new DB();
		String result1=dB.decrementItemNumber(result,k,currentUser);
		if (result1.equals("Operation Failed"))
			return new ModelAndView("result","result",result1);
		else
		{
			generatedKey=k;
			return new ModelAndView("keyChoice");
		}
	}
	
	
	
	@RequestMapping("/keyChoiceRequest")
	public ModelAndView keyChoiceRequest(@RequestParam String choice)
	{
		
		if (choice.equals("For Sale"))
		{
			return new ModelAndView("result","result","Operation Complete");
		}
		else
		{
			return new ModelAndView("newKeyInvAssign","invName",generatedKey.getInvCode());
		}
	}
	
	@RequestMapping("/newKeyInvAssignRequest")
	public ModelAndView newKeyInvAssignRequest(Key k)
	{
		DB db=new DB();
		String result=db.newKeyInvAssign(k,currentUser);
		return new ModelAndView("result","result",result);
	}
	
	
	@RequestMapping("/searchforkey")
	public ModelAndView searchEntry()
	{
		DB db=new DB();
		ArrayList<String>invList=db.getInvList();
			return new ModelAndView("search","invList",invList);
	
	}
	
	@RequestMapping("/searchResultTable")
	public ModelAndView searchResults(Item i)
	{
		dB=new DB();
		
		ArrayList<Item> searchResult=dB.searchKey(i,currentUser);
		if (searchResult.size()==0)
		{
			return new ModelAndView("search");
		}
		else
		{
				return new ModelAndView("searchresulttable","searchResult",searchResult.get(0));
				}
	}
	Item itemObject;
	
	@RequestMapping(value="/imageServlet")
	public void imageServlet(HttpServletRequest request, HttpServletResponse response)
	{
		 response.setHeader("expires", "0");
		response.setContentType("image/jpg");
		try {
		OutputStream output = response.getOutputStream();
			
			output.write(itemObject.getImageURLByteArray());
			output.flush();
			output.close();
		} catch (IOException | NullPointerException s) {
			// TODO Auto-generated catch block
			System.out.println("IO Exception or No Image File");
		}
       
		
	}

	@RequestMapping("/updatesearch")
	public ModelAndView updatesearch()
	{
		DB db=new DB();
		ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
		ModelAndView modelAndView=new ModelAndView("updatesearch");
		modelAndView.addObject("senderInventoryList",list.get(4));
		return modelAndView;
	}
	
	
	@RequestMapping("/updatesearchadmin")
	public ModelAndView updatesearchadmin()
	{
		DB db=new DB();
		ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
		ModelAndView modelAndView=new ModelAndView("updatesearchadmin");
		modelAndView.addObject("senderInventoryList",list.get(4));
		return modelAndView;
	}
	
	@RequestMapping("/updatesearchResultTable")
	public ModelAndView updatesearchResults(Item object)
	{
		DB db=new DB();
		if (object.getBrand()==null )
			object.setBrand("");
		 if (object.getCategoryName()==null)
			object.setCategoryName("");
		 if (object.getSubCategoryName()==null)
			object.setSubCategoryName("");
		 if (object.getSku()==null)
			object.setSku("");
	     if (object.getDescription()==null)
			object.setDescription("");
	     if (object.getItemCode()==null)
			object.setItemCode("");
	     if (object.getInvName()==null)
				object.setInvName("");
		 
		ArrayList<Item> updatesearchresulttable=db.searchKey(object,currentUser);
		
		if (updatesearchresulttable.size()==0)
		{
			return new ModelAndView("result","result","No Result Found");
		}
		else
		{
			
			itemObject =new Item();
			itemObject.setImageURLByteArray(updatesearchresulttable.get(0).getImageURLByteArray());
		    ModelAndView mav=new ModelAndView("updatesearchresulttable","updatesearchresulttable",updatesearchresulttable.get(0));
		    ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
			Multimap<String, String>categoryMap=db.getCategoryMap();
			String categoryMapString=categoryMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			mav.addObject("categoryMap",categoryMapString);
			mav.addObject("dropdownbrandList",list.get(2));
			mav.addObject("categoryList",list.get(1));
			Multimap<String, String>brandMap=db.getBrandMap();
			String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			mav.addObject("brandMap",brandMapString);
			mav.addObject("selectedbrand",updatesearchresulttable.get(0).getBrand());
			mav.addObject("selectedCategory",updatesearchresulttable.get(0).getCategoryName());
		
		return mav;
		}
	}
	@RequestMapping("/updatesearchResultTableadmin")
	public ModelAndView updatesearchResultTableadmin(Item object)
	{
		DB db=new DB();
		if (object.getBrand()==null )
			object.setBrand("");
		 if (object.getCategoryName()==null)
			object.setCategoryName("");
		 if (object.getSubCategoryName()==null)
			object.setSubCategoryName("");
		 if (object.getSku()==null)
			object.setSku("");
	     if (object.getDescription()==null)
			object.setDescription("");
	     if (object.getItemCode()==null)
			object.setItemCode("");
	     if (object.getInvName()==null)
				object.setInvName("");
		 
		ArrayList<Item> updatesearchresulttable=db.searchKey(object,currentUser);
		
		if (updatesearchresulttable.size()==0)
		{
			return new ModelAndView("resultadmin","result","No Result Found");
		}
		else
		{
			
			itemObject=new Item();
			itemObject=updatesearchresulttable.get(0);
			itemObject.setImageURLByteArray(updatesearchresulttable.get(0).getImageURLByteArray());
		    ModelAndView mav=new ModelAndView("updatesearchresulttableadmin","updatesearchresulttable",updatesearchresulttable.get(0));
		    ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
			Multimap<String, String>categoryMap=db.getCategoryMap();
			String categoryMapString=categoryMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			mav.addObject("categoryMap",categoryMapString);
			
			ArrayList<String>brandList=new ArrayList<String>(Arrays.asList(itemObject.getBrand().split(",")));
			ArrayList<String>modelList=new ArrayList<String>(Arrays.asList(itemObject.getModel().split(",")));
			ArrayList<String>trimList=new ArrayList<String>(Arrays.asList(itemObject.getTrim().split(",")));
			ArrayList<String>fromYearList=new ArrayList<String>(Arrays.asList(itemObject.getFromYear().split(",")));
			ArrayList<String>toYearList=new ArrayList<String>(Arrays.asList(itemObject.getToYear().split(",")));
			mav.addObject("loopCount",brandList.size()-1);
			mav.addObject("brandList", brandList);
			mav.addObject("modelList",modelList);
			mav.addObject("trimList",trimList);
			mav.addObject("fromYearList",fromYearList);
			mav.addObject("toYearList",toYearList);
			mav.addObject("dropdownbrandList",list.get(2));
			mav.addObject("categoryList",list.get(1));
			Multimap<String, String>brandMap=db.getBrandMap();
			String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			mav.addObject("brandMap",brandMapString);
			mav.addObject("selectedbrand",updatesearchresulttable.get(0).getBrand());
			mav.addObject("selectedCategory",updatesearchresulttable.get(0).getCategoryName());
			mav.addObject("SearchObject", updatesearchresulttable.get(0));
			Multimap<String, String>modelMap=db.getmodelMap();
			String modelMapString=modelMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			mav.addObject("modelMap",modelMapString);
			ArrayList<String>transponderList=db.getTransponderList();
			String transponderListString=transponderList.toString().replaceAll("\\[", "'").replaceAll("\\]", "'");
			mav.addObject("transponderList",transponderListString);
		return mav;
		}
	}
	@RequestMapping(value="/updateimageServlet")
	public void updateimageServlet(HttpServletRequest request, HttpServletResponse response)
	{
		 response.setHeader("expires", "0");
		response.setContentType("image/jpg");
		try {
		OutputStream output = response.getOutputStream();
		
			output.write(itemObject.getImageURLByteArray());
			output.flush();
			output.close();
		} catch (IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NullPointerException e ) {
			// TODO Auto-generated catch block
			System.out.println("No Image Found");
		}
	}
	@RequestMapping(value="/updateKeyInfoForm")
	public ModelAndView updateKeyInfoForm(Item item,@RequestParam String result,@RequestParam String buttonName)
	{
		
		ModelAndView modelAndView=null;
		DB db=new DB();
	
		if (buttonName.equals("assignMoreButton"))
		{
			ArrayList<String>itemArray=new ArrayList<String>(Arrays.asList(result.split("&&&")));
			Item item1=new Item();
			item1.setBrand(itemArray.get(0));
			item1.setModel(itemArray.get(1));
			item1.setTrim(itemArray.get(2));
			item1.setFromYear(itemArray.get(3));
			item1.setToYear(itemArray.get(4));
			assignmentList.add(item1);
			Multimap<String, String>categoryMap=db.getCategoryMap();
			String categoryMapString=categoryMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView=new ModelAndView("updatesearchresulttableadmin");
			modelAndView.addObject("updatesearchresulttable",itemObject);
			ArrayList<String>brandList=new ArrayList<String>(Arrays.asList(itemObject.getBrand().split(",")));
			ArrayList<String>modelList=new ArrayList<String>(Arrays.asList(itemObject.getModel().split(",")));
			ArrayList<String>trimList=new ArrayList<String>(Arrays.asList(itemObject.getTrim().split(",")));
			ArrayList<String>fromYearList=new ArrayList<String>(Arrays.asList(itemObject.getFromYear().split(",")));
			ArrayList<String>toYearList=new ArrayList<String>(Arrays.asList(itemObject.getToYear().split(",")));
			for (int i=0;i<assignmentList.size();i++)
			{
				brandList.add(assignmentList.get(i).getBrand());
				modelList.add(assignmentList.get(i).getModel());
				trimList.add(assignmentList.get(i).getTrim());
				fromYearList.add(assignmentList.get(i).getFromYear());
				toYearList.add(assignmentList.get(i).getToYear());
			}
			modelAndView.addObject("loopCount",brandList.size());
			modelAndView.addObject("brandList", brandList);
			modelAndView.addObject("modelList",modelList);
			modelAndView.addObject("trimList",trimList);
			modelAndView.addObject("fromYearList",fromYearList);
			modelAndView.addObject("toYearList",toYearList);
			modelAndView.addObject("categoryMap",categoryMapString);
			Multimap<String, String>brandMap=db.getBrandMap();
			Multimap<String, String>modelMap=db.getmodelMap();
			String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView.addObject("brandMap",brandMapString);
			String modelMapString=modelMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView.addObject("modelMap",modelMapString);

			ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
			modelAndView.addObject("dropdownbrandList", list.get(2));
			modelAndView.addObject("categoryList",list.get(1));
			ArrayList<String>transponderList=db.getTransponderList();
			String transponderListString=transponderList.toString().replaceAll("\\[", "'").replaceAll("\\]", "'");
			modelAndView.addObject("transponderList",transponderListString);
			
		}
		else
		{
			ArrayList<String>brandArray=new ArrayList<String>(Arrays.asList(result.split("###")));
			ArrayList<String>brand=new ArrayList<String>();
			ArrayList<String>model=new ArrayList<String>();
			ArrayList<String>trim=new ArrayList<String>();
			ArrayList<String>fromYear=new ArrayList<String>();
			ArrayList<String>toYear=new ArrayList<String>();
			for (int i=0;i<brandArray.size();i++)
			{
				if (!(brandArray.get(i).equals("")))
				{
					
					String row=brandArray.get(i).replaceFirst("&&&", "");
					ArrayList<String>info=new ArrayList<String>(Arrays.asList(row.split("&&&")));
					if (info.size()>0)
					{
						brand.add(info.get(0).trim());
						model.add(info.get(1).trim());
						trim.add(info.get(2).trim());
						fromYear.add(info.get(3).trim());
						toYear.add(info.get(4).trim());	
					}
						
				}
				
				
			}
			String brandString=brand.toString().replaceAll("\\[", "").replaceAll("\\]", "");
			String modelString=model.toString().replaceAll("\\[", "").replaceAll("\\]", "");
			String trimString=trim.toString().replaceAll("\\[", "").replaceAll("\\]", "");
			String fromYearString=fromYear.toString().replaceAll("\\[", "").replaceAll("\\]", "");
			String toYearString=toYear.toString().replaceAll("\\[", "").replaceAll("\\]", "");
			item.setBrand(brandString);
			item.setModel(modelString);
			item.setTrim(trimString);
			item.setFromYear(fromYearString);
			item.setToYear(toYearString);
			
			String finalResult=db.updateItem(item,currentUser);
			assignmentList.clear();
			modelAndView= new ModelAndView("adminoptions","finalResult",finalResult);
		}
		return modelAndView;
		
	}
	
	
	@RequestMapping(value="/updateKeyInfoFormEmployee")
	public ModelAndView updateKeyInfoFormEmployee(Item item)
	{
		
		dB=new DB();
		String result=dB.employeeUpdateItem(item,currentUser);
		return new ModelAndView("result","result",result);
		
	}
	
	@RequestMapping(value="/addnewitem")
	public ModelAndView addNewItems()
	{	
		DB db=new DB();
		ModelAndView mav=new ModelAndView("addnewitem");
		ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
		Multimap<String, String>categoryMap=db.getCategoryMap();
		String categoryMapString=categoryMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		mav.addObject("categoryMap",categoryMapString);
		mav.addObject("brandList",list.get(2));
		mav.addObject("senderInventoryList",list.get(4));
		mav.addObject("categoryList",list.get(1));
		Multimap<String, String>brandMap=db.getBrandMap();
		ArrayList<String>transponderList=db.getTransponderList();
		String transponderListString=transponderList.toString().replaceAll("\\[", "'").replaceAll("\\]", "'");
		mav.addObject("transponderList",transponderListString);
		String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		mav.addObject("brandMap",brandMapString);
		return mav;
	}
	
	@RequestMapping(value="/addNewItemform")
	public ModelAndView addNewItemsRequest(Item SearchObject)
	{
		MultipartFile multipartFile = SearchObject.getImageURL();
		    if (multipartFile == null) {
	            System.out.println("Image not valid");
		    	}
		dB=new DB();
		String result=dB.addItem(SearchObject,currentUser);
		return new ModelAndView("resultadmin","result",result);
		
	}
	
	@RequestMapping(value="/addnewcompartment")
	public ModelAndView addnewcompartment()
	{
		return new ModelAndView("addnewcompartment");
	}
	
	@RequestMapping(value="/searchbyitemCode")
	public ModelAndView searchbyitemCode()
	{
		return new ModelAndView("searchbyitemCode");
	}
	
	@RequestMapping(value="/logout")
	public ModelAndView logout(HttpServletRequest request, HttpServletResponse response)
	{
		 try {
			request.getRequestDispatcher("employeeoptions").include(request, response);
		} catch (ServletException | IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}  
         HttpSession session=request.getSession();  
         session.invalidate(); 
         DB db=new DB();
         db.takeLog(currentUser, "SignOut", null);
		return new ModelAndView("login");
	}
	@RequestMapping(value="/addNewCompartmentform")
	public ModelAndView addNewCompartmentform(SearchObject SearchObject)
	{
		/* Barcode128 c=new Barcode128();
         c.setCodeType(Barcode128.CODE128);
         c.setCode(location.getCode()+list.get(15));
         Image imageEAN = c.createImageWithBarcode(byte1, null, null);*/
		return new ModelAndView("addnewcompartment");
	}
	
	
	@RequestMapping(value="/receiveshipments")
	public ModelAndView receiveshipments()
	{
		DB db=new DB();
		ModelAndView modelAndView=new ModelAndView("receiveshipments");
		 ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
			Multimap<String, String>categoryMap=db.getCategoryMap();
			modelAndView.addObject("senderInventoryList",list.get(4));
			modelAndView.addObject("categoryList",list.get(1));
			modelAndView.addObject("brandList",list.get(2));
			Multimap<String, String>brandMap=db.getBrandMap();
			String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView.addObject("brandMap",brandMapString);
			String categoryMapString=categoryMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView.addObject("categoryMap",categoryMapString);
			modelAndView.addObject("shipperList",db.getShipperList());
			String skuList=db.getSkuListJSArray();
			modelAndView.addObject("skuList",skuList);
			return modelAndView;
	}
	@RequestMapping(value="/updatePassword")
	public ModelAndView updatePassword()
	{
		
		return new ModelAndView("updatePassword");
	}
	@RequestMapping(value="/updatePasswordRequest")
	public ModelAndView updatePasswordRequest(Login login)
	{
		login.setLoginID(currentUser);
		dB=new DB();
		if(dB.changePassword(login,currentUser))
		return new ModelAndView("employeeoptions");
		else
			return new ModelAndView("error");
	}

	
	@RequestMapping(value="/addTechnician")
	public ModelAndView addTechnician()
	{
		DB db=new DB();
		ArrayList<String>invList=db.getInvList();
			return new ModelAndView("addTechnician","invList",invList);
	}
	@RequestMapping(value="/addTechnicianRequest")
	public ModelAndView addTechnicianRequest(Login login)
	{
		DB dB=new DB();
		String result=dB.addTechnician(login,currentUser);
			return new ModelAndView("adminoptions");
	}@RequestMapping(value="/updateTechnician")
	public ModelAndView updateTechnician()
	{
		DB dB=new DB();
		ArrayList<String>userList=dB.getUserList();
		ModelAndView modelAndView=new ModelAndView("searchTechnician");
		modelAndView.addObject("userList",userList);
		return modelAndView;
			
	}
	@RequestMapping(value="/searchTechnicianRequest")
	public ModelAndView searchTechnicianRequest(Login login)
	{
		DB dB=new DB();
		Login resultlogin=dB.searchTechnician(login);
		ArrayList<String>invList=dB.getInvList();
		ArrayList<String>invList2=dB.getInvList();
		ArrayList<String>roleList=new ArrayList<String>();
		roleList.add("ADMIN");roleList.add("EMPLOYEE");
		ModelAndView modelAndView=new ModelAndView("updateTechnician");
		modelAndView.addObject("resultlogin",resultlogin);
		modelAndView.addObject("invList",invList);
		modelAndView.addObject("roleList",roleList);
		modelAndView.addObject("selectedRole",resultlogin.getRole());
		invList2.removeAll(resultlogin.getAccessInvList());
		modelAndView.addObject("remainingInvList",invList2);
		modelAndView.addObject("selectedInvList",resultlogin.getAccessInvList());
		return modelAndView;
	}
	
	@RequestMapping(value="/updateTechnicianRequest")
	public ModelAndView updateTechnicianRequest(Login login)
	{
		DB dB=new DB();
		String result=dB.updateTechnician(login,currentUser);
			return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/removeTechnician")
	public ModelAndView removeTechnician()
	{
		DB dB=new DB();
		ArrayList<String>userList=dB.getUserList();
		ModelAndView modelAndView=new ModelAndView("searchRemoveTechnician");
		modelAndView.addObject("userList",userList);
		return modelAndView;
	}
	@RequestMapping(value="/RemoveTechnicianRequest")
	public ModelAndView RemoveTechnicianRequest(Login login)
	{
		DB dB=new DB();
		String result=dB.removeTechnician(login,currentUser);
			return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/deleteItem")
	public ModelAndView deleteItem()
	{
		DB db=new DB();
		ArrayList<String>invList=db.getInvList();
			return new ModelAndView("deletesearch","invList",invList);
	}
	@RequestMapping(value="/deleteItemRequest")
	public ModelAndView deleteItem(Item item)
	{
		dB=new DB();
		String result=dB.deleteItem(item,currentUser);
			return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/createAlert")
	public ModelAndView createAlert()
	{
			return new ModelAndView("createAlert");
	}
	@RequestMapping(value="/createInventory")
	public ModelAndView createInventory()
	{
			return new ModelAndView("createInventory");
	}
	@RequestMapping(value="/createInventoryRequest")
	public ModelAndView createInventoryRequest(Inventory inventory)
	{
		DB db=new DB();
		db.createInventory(inventory, currentUser);
			return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/updateInventory")
	public ModelAndView updateInventory()
	{
		
		DB db=new DB();
		ArrayList<String>inventoryList=db.getCategoryAndSubCategoryList(currentUser).get(4);
		return new ModelAndView("updateInventory","inventoryList",inventoryList);
	}
	@RequestMapping(value="/updateInventoryRequest")
	public ModelAndView updateInventoryRequest(Inventory inventory)
	{
		DB db=new DB();
			db.updateInventory(inventory, currentUser);
			return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/updateInventorysearchRequest")
	public ModelAndView updateInventorysearchRequest(Inventory inv)
	{
		DB db=new DB();
		Inventory inventory=db.searchInventory(inv);
			return new ModelAndView("updateInventoryTable","inventory",inventory);
	}
	@RequestMapping(value="/removeInventory")
	public ModelAndView removeInventory()
	{
		DB db=new DB();
		ArrayList<String>inventoryList=db.getCategoryAndSubCategoryList(currentUser).get(4);
		return new ModelAndView("removeInventory","inventoryList",inventoryList);
			
	}
	@RequestMapping(value="/removeInventoryRequest")
	public ModelAndView removeInventoryRequest(Inventory inventory)
	{
		DB db=new DB();
			db.removeInventory(inventory,currentUser);
			return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/Reschedule")
	public ModelAndView Reschedule()
	{
		
		return new ModelAndView("adminoptions");
	   
	}
	@RequestMapping(value="/searchbybrand")
	public ModelAndView searchbybrand()
	{
		DB db=new DB();
		ArrayList<String>brandList=db.getCategoryAndSubCategoryList(currentUser).get(2);
		Multimap<String, String>brandMap=db.getBrandMap();
		String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		ModelAndView modelAndView=new  ModelAndView("searchbybrand");
		modelAndView.addObject("brandMap",brandMapString);
		modelAndView.addObject("brandList",brandList);
		return modelAndView;
	}
	@RequestMapping(value="/brandSearchResultTable")
	public ModelAndView brandSearchResultTable(Item object)
	{
		DB db=new DB();
		object.setSku("");
		object.setSubCategoryName("");
		object.setCategoryName("");
		object.setDescription("");
		object.setItemCode("");
		ArrayList<Item>dataList=db.searchKey(object,currentUser);
		if (dataList.size()==0)
		{
			return new ModelAndView("result","result","No Result Found");
		}
		else
			return new ModelAndView("searchresulttable","dataList",dataList);
		
	   
	}
	@RequestMapping(value="/searchbysubcategory")
	public ModelAndView searchbysubcategory()
	{
		DB db=new DB();
		ArrayList<String>categoryList=db.getCategoryAndSubCategoryList(currentUser).get(1);
		Multimap<String, String>categoryMap=db.getCategoryMap();
		String categoryMapString=categoryMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		ModelAndView modelAndView=new  ModelAndView("searchbysubcategory");
		modelAndView.addObject("categoryMap",categoryMapString);
		modelAndView.addObject("categoryList",categoryList);
		return modelAndView;
	}
	@RequestMapping(value="/subCategorySearchResultTable")
	public ModelAndView subCategorySearchResultTable(Item object)
	{
		object.setBrand("");
		object.setSku("");
		object.setDescription("");
		object.setItemCode("");
		DB db=new DB();
		ArrayList<Item>dataList=db.searchKey(object,currentUser);
		if (dataList.size()==0)
		{
			return new ModelAndView("result","result","No Result Found");
		}
		else
			return new ModelAndView("searchresulttable","dataList",dataList);
	}
	@RequestMapping(value="/searchbycategory")
	public ModelAndView searchbycategory()
	{
		DB db=new DB();
		ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
		ModelAndView modelAndView=new ModelAndView("searchbycategory");
		modelAndView.addObject("categoryList",list.get(1));
		return modelAndView;
	}
	@RequestMapping(value="/categorySearchResultTable")
	public ModelAndView categorySearchResultTable(Item object)
	{
		object.setSku("");
		object.setSubCategoryName("");
		object.setBrand("");
		object.setDescription("");
		object.setItemCode("");
		DB db=new DB();
		ArrayList<Item>dataList=db.searchKey(object,currentUser);
		return new ModelAndView("searchresulttable","dataList",dataList);
	   
	}
	@RequestMapping(value="/searchbydescription")
	public ModelAndView searchbydescription()
	{
		
		return new ModelAndView("searchbydescription");
	   
	}
	@RequestMapping(value="/descriptionSearchResultTable")
	public ModelAndView descriptionSearchResultTable(Item object)
	{
		object.setSku("");
		object.setSubCategoryName("");
		object.setBrand("");
		object.setCategoryName("");
		object.setItemCode("");
		DB db=new DB();
		ArrayList<Item>dataList=db.searchKey(object,currentUser);
		if (dataList.size()==0)
		{
			return new ModelAndView("result","result","No Result Found");
		}
		else
			return new ModelAndView("searchresulttable","dataList",dataList);
	}
	
	@RequestMapping(value="/itemCodeSearchResultTable")
	public ModelAndView itemCodeSearchResultTable(Item object)
	{
		object.setSku("");
		object.setSubCategoryName("");
		object.setBrand("");
		object.setCategoryName("");
		object.setSubCategoryName("");
		object.setDescription("");
		DB db=new DB();
		ArrayList<Item>dataList=db.searchKey(object,currentUser);
		if (dataList.size()==0)
		{
			return new ModelAndView("result","result","No Result Found");
		}
		else
			return new ModelAndView("searchresulttable","dataList",dataList);
	}
	
	
	@RequestMapping(value="/searchbysku")
	public ModelAndView searchbysku()
	{
		DB db=new DB();
		String skuList=db.getSkuListJSArray();
		return new ModelAndView("searchbysku","skuList",skuList);
	   
	}
	@RequestMapping(value="/skuSearchResultTable")
	public ModelAndView skuSearchResultTable(Item object)
	{
		object.setSubCategoryName("");
		object.setBrand("");
		object.setCategoryName("");
		object.setDescription("");
		object.setItemCode("");
		object.setInvName("");
		DB db=new DB();
		ArrayList<Item>dataList=db.searchKey(object,currentUser);
		if (dataList.size()==0)
		{
			return new ModelAndView("result","result","No Result Found");
		}
		else
			return new ModelAndView("searchresulttable","dataList",dataList);
	   
	}
	@RequestMapping(value="/transferStocksIn")
	public ModelAndView transferStocksIn()
	{
		return new ModelAndView("searchtransferId");
	}
	@RequestMapping(value="/searchtransferId")
	public ModelAndView searchtransferId(TransferObject object)
	{
		DB db=new DB();
		ArrayList<TransferObject> result=db.searchTransferID(object);
		ModelAndView modelAndView;
		if (result.size()!=0)
		{
			 modelAndView=new ModelAndView("transferStocksIn");
			ArrayList<String> reasonList=db.getReasonList();
			modelAndView.addObject("TransferObject",result);
			modelAndView.addObject("reasonList",reasonList);
				
		}
		else
			 modelAndView=new ModelAndView("employeeoptions");
		return modelAndView;
	   
	}
	
	@RequestMapping(value="/stockTransferRequest")
	public ModelAndView stockTransferRequest(TransferObject transferObject,@RequestParam String result)
	{Boolean validationBoolean=false;
		groupTransferList.clear();
		String result2="";
		ArrayList<TransferObject>transferList=new ArrayList<TransferObject>();
		if (!(result.equals("singleTransfer")))
		{
			ArrayList<String>itemArray=new ArrayList<String>(Arrays.asList(result.split("###")));
			for (int i=0;i<itemArray.size();i++)
			{
				TransferObject object=new TransferObject();
				String testString=itemArray.get(i).replaceFirst("&&&", "");
					ArrayList<String>objectString=new ArrayList<String>(Arrays.asList(testString.split("&&&")));
						
						object.setBrand(objectString.get(3));
						object.setCategoryName(objectString.get(4));
						object.setSubCategoryName(objectString.get(5));
						object.setInvCodeFrom(objectString.get(1));
						object.setInvCodeTo(objectString.get(2));
						object.setSku(objectString.get(0));
						object.setModel(objectString.get(6));
						object.setTransferAmount(objectString.get(7));
						object.setReason(objectString.get(8));
						transferList.add(object);
						
			}
			result2=dB.initiateTransferStocks(transferList,currentUser);
			return new ModelAndView("showTransferID","result",result2);
		}
		else
		{
			dB=new DB();
			
			String validationReason="";
			TransferObject result1;
			DB db=new DB();
			if (!(transferObject.getBrand()==null) && !(transferObject.getCategoryName()==null) && !(transferObject.getSubCategoryName()==null) && !(transferObject.getModel()==null))
			{
				result1=db.getTransferOutData(transferObject,"1");
				if (!(result1.getSku().equals("")))
				{
					validationBoolean=true;
				}
				else
					validationReason="Incorrect Brand/Model/Category/SubCategory combination provided";	
					
				
			}
			else if (!(transferObject.getItemCodeFrom().equals("")) && (!(transferObject.getItemCodeTo().equals(""))))
			{
				result1=db.getTransferOutData(transferObject,"2");
				if (!(result1.getSku().equals("")))
				{
					validationBoolean=true;
				}
				else
					validationReason="Incorrect ItemCode provided";
					
			}
			else if (!(transferObject.getSku()==null))
			{
				result1=db.getTransferOutData(transferObject,"3");
				if (!(result1.getBrand().equals("")))
				{
					validationBoolean=true;
				}
				else
					validationReason="Incorrect SKU provided";
			}
			transferList.add(transferObject);
			 if (validationBoolean)
				 return new ModelAndView("showTransferID","result",result2);
			 else
				 return new ModelAndView("tryAgain","validationReason",validationReason);
		}
		
		
		
	}
	@RequestMapping(value="/doTransferRequest")
	public ModelAndView doTransferRequest(TransferObject transferObject,@RequestParam String result)
	{
		
		DB dB=new DB();
		String result1="";
		ArrayList<String>itemArray=new ArrayList<String>(Arrays.asList(result.split("###")));
		for (int i=0;i<itemArray.size();i++)
		{
			TransferObject object=new TransferObject();
				String testString=itemArray.get(i).replaceFirst("&&&", "");
				testString=testString.replaceFirst("&&&&&&", "&&&");
				System.out.println(testString);
				ArrayList<String>objectString=new ArrayList<String>(Arrays.asList(testString.split("&&&")));
					if (!(objectString.get(0).equals("")))
					{
						String transferID=objectString.get(0);
						object.setBrand(objectString.get(4));
						object.setCategoryName(objectString.get(5));
						object.setSubCategoryName(objectString.get(6));
						object.setInvCodeFrom(objectString.get(2));
						object.setInvCodeTo(objectString.get(3));
						object.setSku(objectString.get(1));
						object.setTransferAmount(objectString.get(14));
						object.setReason(objectString.get(13).trim());
						result1=dB.doTransferStocks(object,transferID,currentUser);			
					}
			
		}
		
		return new ModelAndView("TransferResult","result",result1);
	}
	@RequestMapping(value="/pendingStockTransfer")
	public ModelAndView showPendingStockTransfer()
	{
		DB db=new DB();
		ArrayList<TransferObject>dataList=db.showPendingTransfer(currentUser);
		return new ModelAndView("pendingStockTransfer","dataList",dataList);
		
	}
	@RequestMapping(value="/recieveshipmentform")
	public ModelAndView recieveshipmentform(TransferObject object)
	{
		DB db=new DB();
		db.receiveShipmentRequest(object,currentUser);
		return new ModelAndView("employeeoptions");
		
	}
	
	@RequestMapping(value="/addCategory")
	public ModelAndView addNewCategory()
	{
		return new ModelAndView("addCategory");
	}
	@RequestMapping(value="/addNewCategoryform")
	public ModelAndView addNewCategoryform(Item object)
	{
		DB db=new DB();
		db.addNewCategoryRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/searchCategory")
	public ModelAndView searchCategory()
	{
		DB db=new DB();
		ArrayList<String>categoryList=db.getCategoryAndSubCategoryList(currentUser).get(1);
		return new ModelAndView("searchCategory","categoryList",categoryList);
	}
	@RequestMapping(value="/searchCategoryTable")
	public ModelAndView searchCategoryTable(Item object)
	{
		DB db=new DB();
		Item item=db.searchCategoryRequest(object);
		return new ModelAndView("searchcategorytable","item",item);
	}
	@RequestMapping(value="/updateCategoryRequest")
	public ModelAndView updateCategoryRequest(Item object)
	{
		DB db=new DB();
		db.updateCategoryRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/searchDeleteCategoryTable")
	public ModelAndView searchDeleteCategoryTable(Item object)
	{
		DB db=new DB();
		ArrayList<String>categoryList=db.getCategoryAndSubCategoryList(currentUser).get(1);
		return new ModelAndView("searchDeleteCategory","categoryList",categoryList);
	}
	@RequestMapping(value="/searchDeleteTable")
	public ModelAndView searchDeleteTable(Item object)
	{
		DB db=new DB();
		db.deleteCategoryRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	
	
////////////////////////////
	
	
	@RequestMapping(value="/addTransponder")
	public ModelAndView addNewTransponder()
	{
		DB db=new DB();
		ArrayList<String>categoryList=db.getCategoryAndSubCategoryList(currentUser).get(1);
		Multimap<String, String>categoryMap=db.getCategoryMap();
		String categoryMapString=categoryMap.toString().replaceAll("=",":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		ModelAndView modelAndView=new  ModelAndView("addTransponder");
		modelAndView.addObject("categoryMap",categoryMapString);
		modelAndView.addObject("categoryList",categoryList);
	return modelAndView;
	}
	@RequestMapping(value="/addNewTransponderform")
	public ModelAndView addNewTransponderform(Item object)
	{
		DB db=new DB();
		db.addNewTransponderRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/searchTransponder")
	public ModelAndView searchTransponder()
	{
		DB db=new DB();
		ArrayList<String>categoryList=db.getCategoryAndSubCategoryList(currentUser).get(1);
		Multimap<String, String>categoryMap=db.getCategoryMap();
		String categoryMapString=categoryMap.toString().replaceAll("=",":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		ModelAndView modelAndView=new  ModelAndView("searchTransponder");
		Multimap<String, String>subCategoryMap=db.getSubCategoryMap();
		String subCategoryMapString=subCategoryMap.toString().replaceAll("=",":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		modelAndView.addObject("subCategoryMap",subCategoryMapString);
		modelAndView.addObject("categoryMap",categoryMapString);
		modelAndView.addObject("categoryList",categoryList);
	
		return modelAndView;
	}
	@RequestMapping(value="/searchTransponderTable")
	public ModelAndView searchTransponderTable(Item object)
	{
		DB db=new DB();
		Item item=db.searchTransponderRequest(object);
		return new ModelAndView("searchTransponderTable","item",item);
	}
	@RequestMapping(value="/updateTransponderRequest")
	public ModelAndView updateTransponderRequest(Item object)
	{
		DB db=new DB();
		db.updateTransponderRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/searchDeleteTransponder")
	public ModelAndView searchDeleteTransponderTable(Item object)
	{
		DB db=new DB();
		ArrayList<String>categoryList=db.getCategoryAndSubCategoryList(currentUser).get(1);
		Multimap<String, String>categoryMap=db.getCategoryMap();
		String categoryMapString=categoryMap.toString().replaceAll("=",":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		ModelAndView modelAndView=new  ModelAndView("searchDeleteTransponder");
		Multimap<String, String>subCategoryMap=db.getSubCategoryMap();
		String subCategoryMapString=subCategoryMap.toString().replaceAll("=",":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		modelAndView.addObject("subCategoryMap",subCategoryMapString);
		modelAndView.addObject("categoryMap",categoryMapString);
		modelAndView.addObject("categoryList",categoryList);
		return modelAndView;
		
		
	}
	@RequestMapping(value="/searchDeleteTableTranponder")
	public ModelAndView searchDeleteTableTransponder(Item object)
	{
		DB db=new DB();
		db.deleteTransponderRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	
	@RequestMapping(value="/addReason")
	public ModelAndView addNewReason()
	{
		return new ModelAndView("addReason");
	}
	@RequestMapping(value="/addNewReasonform")
	public ModelAndView addNewReasonform(Item object)
	{
		DB db=new DB();
		db.addNewReasonRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/searchReason")
	public ModelAndView searchReason()
	{
		DB db=new DB();
		ArrayList<String>reasonList=db.getCategoryAndSubCategoryList(currentUser).get(6);
		return new ModelAndView("searchReason","reasonList",reasonList);
	}
	@RequestMapping(value="/searchReasonTable")
	public ModelAndView searchReasonTable(Item object)
	{
		DB db=new DB();
		Item item=db.searchReasonRequest(object);
		return new ModelAndView("searchReasonTable","item",item);
	}
	@RequestMapping(value="/updateReasonRequest")
	public ModelAndView updateReasonRequest(Item object)
	{
		DB db=new DB();
		db.updateReasonRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/searchDeleteReasonTable")
	public ModelAndView searchDeleteReasonTable(Item object)
	{
		DB db=new DB();
		ArrayList<String>reasonList=db.getCategoryAndSubCategoryList(currentUser).get(6);
		return new ModelAndView("searchDeleteReason","reasonList",reasonList);
	}
	@RequestMapping(value="/searchDeleteReason")
	public ModelAndView searchDeleteReason(Item object)
	{
		DB db=new DB();
		db.deleteReasonRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	
///////////////////////////	
	@RequestMapping(value="/addSubCategory")
	public ModelAndView addNewSubCategory()
	{
		DB db=new DB();
		ArrayList<String>categoryList=db.getCategoryAndSubCategoryList(currentUser).get(1);
		return new ModelAndView("addSubCategory","categoryList",categoryList);
	}
	@RequestMapping(value="/addNewSubCategoryform")
	public ModelAndView addNewSubCategoryform(Item object)
	{
		DB db=new DB();
		db.addNewSubCategoryRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/searchSubCategory")
	public ModelAndView searchSubCategory()
	{
		DB db=new DB();
		ArrayList<String>categoryList=db.getCategoryAndSubCategoryList(currentUser).get(1);
		Multimap<String, String>categoryMap=db.getCategoryMap();
		String categoryMapString=categoryMap.toString().replaceAll("=",":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		ModelAndView modelAndView=new  ModelAndView("searchSubCategory");
		modelAndView.addObject("categoryMap",categoryMapString);
		modelAndView.addObject("categoryList",categoryList);
		return modelAndView;
	}
	@RequestMapping(value="/searchSubCategoryTable")
	public ModelAndView searchSubCategoryTable(Item object)
	{
		DB db=new DB();
		
		Item item=db.searchSubCategoryRequest(object);
		return new ModelAndView("searchsubcategorytable","item",item);
		
	}
	@RequestMapping(value="/updateSubCategoryRequest")
	public ModelAndView updateSubCategoryRequest(Item object)
	{
		DB db=new DB();
		db.updateSubCategoryRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/searchDeleteSubCategoryTable")
	public ModelAndView searchDeleteSubCategoryTable(Item object)
	{
		DB db=new DB();
		ArrayList<String>categoryList=db.getCategoryAndSubCategoryList(currentUser).get(1);
		Multimap<String, String>categoryMap=db.getCategoryMap();
		String categoryMapString=categoryMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		ModelAndView modelAndView=new  ModelAndView("searchDeleteSubCategory");
		modelAndView.addObject("categoryMap",categoryMapString);
		modelAndView.addObject("categoryList",categoryList);
		return modelAndView;
		
	}
	@RequestMapping(value="/searchSubDeleteTable")
	public ModelAndView searchSubDeleteTable(Item object)
	{
		DB db=new DB();
		db.deleteSubCategoryRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	
	
	@RequestMapping(value="/addBrand")
	public ModelAndView addBrand()
	{
		
		return new ModelAndView("addBrand");
	}
	
	@RequestMapping(value="/addNewBrandRequest")
	public ModelAndView addNewBrandform(Item object)
	{
		DB db=new DB();
		db.addNewBrandRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/searchBrand")
	public ModelAndView searchBrand()
	{
		DB db=new DB();
		ArrayList<String>brandList=db.getCategoryAndSubCategoryList(currentUser).get(2);
		return new ModelAndView("searchBrand","brandList",brandList);
	}
	@RequestMapping(value="/searchBrandTable")
	public ModelAndView searchBrandTable(Item object)
	{
		DB db=new DB();
		Item item=db.searchBrandRequest(object,currentUser);
		return new ModelAndView("searchBrandtable","item",item);
		
	}
	@RequestMapping(value="/updateBrandRequest")
	public ModelAndView updateBrandRequest(Item object)
	{
		DB db=new DB();
		db.updateBrandRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/searchDeleteBrand")
	public ModelAndView searchDeleteBrandTable(Item object)
	{
		DB db=new DB();
		ArrayList<String>brandList=db.getCategoryAndSubCategoryList(currentUser).get(2);
		return new ModelAndView("searchDeleteBrand","brandList",brandList);
	}
	@RequestMapping(value="/searchBrandDeleteTable")
	public ModelAndView searchBrandDeleteTable(Item object)
	{
		DB db=new DB();
		db.deleteBrandRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	
	///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value="/addModel")
	public ModelAndView addModel()
	{
		DB db=new DB();
		ArrayList<String>brandList=db.getCategoryAndSubCategoryList(currentUser).get(2);
		return new ModelAndView("addModel","brandList",brandList);
	}
	
	@RequestMapping(value="/addNewModelRequest")
	public ModelAndView addNewModelform(Item object)
	{
		DB db=new DB();
		db.addNewModelRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/searchModel")
	public ModelAndView searchModel()
	{
		DB db=new DB();
		ArrayList<String>brandList=db.getCategoryAndSubCategoryList(currentUser).get(2);
		Multimap<String, String>brandMap=db.getBrandMap();
		String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		ModelAndView modelAndView=new  ModelAndView("searchModel");
		modelAndView.addObject("brandMap",brandMapString);
		modelAndView.addObject("brandList",brandList);
		return modelAndView;
	}
	@RequestMapping(value="/searchModelTable")
	public ModelAndView searchModelTable(Item object)
	{
		DB db=new DB();
		Item item=db.searchModelRequest(object,currentUser);
		return new ModelAndView("searchModelTable","item",item);
		
	}
	@RequestMapping(value="/updateModelRequest")
	public ModelAndView updateModelRequest(Item object)
	{
		DB db=new DB();
		db.updateModelRequest(object,currentUser,object.getItemCode());
		return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/searchDeleteModel")
	public ModelAndView searchDeleteModelTable(Item object)
	{
		DB db=new DB();
		ArrayList<String>brandList=db.getCategoryAndSubCategoryList(currentUser).get(2);
		Multimap<String, String>brandMap=db.getBrandMap();
		String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		ModelAndView modelAndView=new  ModelAndView("searchDeleteModel");
		modelAndView.addObject("brandMap",brandMapString);
		modelAndView.addObject("brandList",brandList);
		return modelAndView;
	}
	@RequestMapping(value="/searchModelDeleteTable")
	public ModelAndView searchModelDeleteTable(Item object)
	{
		DB db=new DB();
		db.deleteModelRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	
	
///////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////////
	@RequestMapping(value="/addTrim")
	public ModelAndView addTrim()
	{
		DB db=new DB();
		ArrayList<String>brandList=db.getCategoryAndSubCategoryList(currentUser).get(2);
		ModelAndView modelAndView= new ModelAndView("addTrim");
		Multimap<String, String>brandMap=db.getBrandMap();
		String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		modelAndView.addObject("brandList",brandList);
		modelAndView.addObject("brandMap",brandMapString);
		return modelAndView;
	}
	
	@RequestMapping(value="/addNewTrimRequest")
	public ModelAndView addNewTrimform(Item object)
	{
		DB db=new DB();
		db.addNewTrimRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/searchTrim")
	public ModelAndView searchTrim()
	{
		DB db=new DB();
		ArrayList<String>brandList=db.getCategoryAndSubCategoryList(currentUser).get(2);
		Multimap<String, String>brandMap=db.getBrandMap();
		Multimap<String, String>modelMap=db.getmodelMap();
		String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		ModelAndView modelAndView=new  ModelAndView("searchTrim");
		modelAndView.addObject("brandMap",brandMapString);
		modelAndView.addObject("brandList",brandList);
		String modelMapString=modelMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		modelAndView.addObject("modelMap",modelMapString);
		return modelAndView;
	}
	@RequestMapping(value="/searchTrimTable")
	public ModelAndView searchTrimTable(Item object)
	{
		DB db=new DB();
		Item item=db.searchTrimRequest(object,currentUser);
		return new ModelAndView("searchTrimTable","item",item);
		
	}
	@RequestMapping(value="/updateTrimRequest")
	public ModelAndView updateTrimRequest(Item object)
	{
		DB db=new DB();
		db.updateTrimRequest(object,currentUser,object.getItemCode());
		return new ModelAndView("adminoptions");
	}
	@RequestMapping(value="/searchDeleteTrim")
	public ModelAndView searchDeleteTrimTable(Item object)
	{
		DB db=new DB();
		ArrayList<String>brandList=db.getCategoryAndSubCategoryList(currentUser).get(2);
		Multimap<String, String>brandMap=db.getBrandMap();
		Multimap<String, String>modelMap=db.getmodelMap();
		String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		ModelAndView modelAndView=new  ModelAndView("searchDeleteTrim");
		modelAndView.addObject("brandMap",brandMapString);
		modelAndView.addObject("brandList",brandList);
		String modelMapString=modelMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		modelAndView.addObject("modelMap",modelMapString);
		return modelAndView;
	}
	@RequestMapping(value="/searchTrimDeleteTable")
	public ModelAndView searchTrimDeleteTable(Item object)
	{
		DB db=new DB();
		db.deleteTrimRequest(object,currentUser);
		return new ModelAndView("adminoptions");
	}
	
	
	@RequestMapping(value="/addRole")
	public ModelAndView addRole()
	{
	
		return new ModelAndView("addRole");
	}
	@RequestMapping(value="/createAlerts")
	public ModelAndView createAlerts()
	{
		DB db=new DB();
		ArrayList<String>invList=db.getCategoryAndSubCategoryList(currentUser).get(4);
		ArrayList<String>loginIDList=db.getCategoryAndSubCategoryList(currentUser).get(7);
		ModelAndView modelAndView=new  ModelAndView("createAlerts");
		modelAndView.addObject("allInvList",invList);
		modelAndView.addObject("loginIDList",loginIDList);
		return modelAndView;
	}
	@RequestMapping(value="/addAlertRequest")
	public ModelAndView addAlertRequest(Alert alert)
	{
		DB db=new DB();
		db.createAlert(alert);
		return new ModelAndView("adminoptions"); 
		
	}
	
	@RequestMapping(value="/adminoptions")
	public ModelAndView adminoptions()
	{
		return new ModelAndView("adminoptions"); 
		
	}
	@RequestMapping(value="/updateAlert")
	public ModelAndView updateAlert()
	{
		DB db=new DB();
		ArrayList<String>alertList=db.getCategoryAndSubCategoryList(currentUser).get(8);
		return new ModelAndView("updateAlert","alertList",alertList); 
		
	}
	@RequestMapping(value="/updatesearchAlertRequest")
	public ModelAndView updatesearchAlertRequest(Alert alert)
	{
		DB db=new DB();
		Alert result=db.searchAlert(alert);
		ArrayList<String>invList=db.getInvList();
		ArrayList<String>userList=db.getLoginIDList();
		ArrayList<String>invList2=db.getInvList();
		ArrayList<String>userList2=db.getLoginIDList();
		ModelAndView modelAndView;
		if (result==null)
		{
			modelAndView=new ModelAndView("adminoptions"); 
		}
		else
		{
			
			modelAndView= new ModelAndView("searchResultAlert","result",result);
			modelAndView.addObject("userList", userList);
			modelAndView.addObject("invList", invList);
			invList2.removeAll(result.getInvName());
			userList2.removeAll( result.getUserName());
			modelAndView.addObject("remainingUserList", userList2);
			modelAndView.addObject("remainingInvList", invList2);
			modelAndView.addObject("selectedUserList", result.getUserName());
			modelAndView.addObject("selectedInvList", result.getInvName());
		}
			 
		return modelAndView;
	}
	
	@RequestMapping(value="/updateAlertRequest")
	public ModelAndView updateAlertRequest(Alert alert)
	{
			DB db=new DB();
			db.updateAlert(alert);
			return new ModelAndView("adminoptions");

	}
	@RequestMapping(value="/removeAlerts")
	public ModelAndView removeAlerts()
	{
		DB db=new DB();
		ArrayList<String>alertList=db.getCategoryAndSubCategoryList(currentUser).get(8);
		return new ModelAndView("removeAlerts","alertList",alertList); 
		
	}
	@RequestMapping(value="/removeAlertsRequest")
	public ModelAndView removeAlertsRequest(Alert alert)
	{
		DB db=new DB();
		db.removeAlert(alert);
		return new ModelAndView("adminoptions"); 
		
	}
	@RequestMapping(value="/inventoryAlert")
	public ModelAndView inventoryAlert()
	{
		DB db=new DB();
		ArrayList<Item>dataList=db.createRestockIInventoryList(currentUser);
		ModelAndView modelAndView;
		if (dataList.size()==0)
		{
			 modelAndView=new ModelAndView("result");
			 modelAndView.addObject("result","No Item is understocked");
		}
		else
		{
			 modelAndView=new ModelAndView("inventoryAlertTable");
		}
		modelAndView.addObject("dataList",dataList);
		modelAndView.addObject("selectedReason","No Reason");
		modelAndView.addObject("reasonList",db.getReasonList());
			
	
		return modelAndView;
	}
	
	@RequestMapping(value="/restockformRequest")
	public ModelAndView restockformRequest(@RequestParam String result)
	{
		ArrayList<TransferObject>groupTransferList=new ArrayList<TransferObject>();
		ArrayList<String>itemArray=new ArrayList<String>(Arrays.asList(result.split("###")));
		for (int i=0;i<itemArray.size();i++)
			{
			TransferObject transferObject=new TransferObject();
				String testString=itemArray.get(i).replaceFirst("&&&&&&", "");
					ArrayList<String>objectString=new ArrayList<String>(Arrays.asList(testString.split("&&&")));
						if (objectString.size()>1)
						{
							transferObject.setSku(objectString.get(0));
							transferObject.setInvCodeTo(objectString.get(2));
							transferObject.setBrand(objectString.get(3));
							transferObject.setCategoryName(objectString.get(4));
							transferObject.setSubCategoryName(objectString.get(5));
							transferObject.setTransferAmount(objectString.get(16));
							transferObject.setReason(objectString.get(17));
							transferObject.setModel(objectString.get(6));
							groupTransferList.add(transferObject);
						}
			}
		DB db=new DB();
		 ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
		ModelAndView m=new ModelAndView("reviewGroupTransfer2");	
		m.addObject("senderInventoryList",list.get(4));
		m.addObject("groupTransferList",groupTransferList);
				return m;
			
	}
	@RequestMapping(value="/transferChoice")
	public ModelAndView transferChoice()
	{
		return new ModelAndView("transferChoice");
	}
	
	@RequestMapping(value="/transferChoiceRequest")
	public ModelAndView transferChoiceRequest(@RequestParam String transferChoice){
		ModelAndView modelAndView;
	    if( transferChoice.equals("Group Transfer") ){
	    	
	    	 modelAndView=new ModelAndView("initiateGroupTransfer");
		}
	    else 
	    {
	    	 modelAndView=new ModelAndView("transferStocksOut");
		 }
	    DB db=new DB();
	    ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
		ArrayList<HashMap<String, String>>locationMap=db.getLocationMap();
		Multimap<String, String>categoryMap=db.getCategoryMap();
		modelAndView.addObject("senderInventoryList",list.get(4));
		ArrayList<String>receiverInventoryList=new ArrayList<String>();
		receiverInventoryList.addAll(list.get(4));
		receiverInventoryList.remove("HEADQUATERS");
		modelAndView.addObject("receiverInventoryList",receiverInventoryList);
		modelAndView.addObject("subCategoryList",list.get(0));
		modelAndView.addObject("categoryList",list.get(1));
		modelAndView.addObject("brandList",list.get(2));
		String skuList=list.get(5).toString().replaceAll("\\[", "\\['").replaceAll(",", "','").replaceAll("\\]", "'\\]");
		modelAndView.addObject("skuList",skuList);
		String locationMapString=locationMap.get(1).toString().replaceAll("=", ":").replaceAll(" ", "");
		modelAndView.addObject("locationMap",locationMapString);
		String categoryMapString=categoryMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		modelAndView.addObject("categoryMap",categoryMapString);
		String inventoryMapString=locationMap.get(0).toString().replaceAll("=", ":'").replaceAll(",","',");
		inventoryMapString=inventoryMapString.substring(0, inventoryMapString.length()-1)+"'"+inventoryMapString.substring(inventoryMapString.length()-1, inventoryMapString.length());
		modelAndView.addObject("inventoryMapString",inventoryMapString);
		Multimap<String, String>brandMap=db.getBrandMap();
		String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		modelAndView.addObject("brandMap",brandMapString);
		return modelAndView;

	} 
	
	

@RequestMapping(value="/printLabelInvAlertTable")
@ResponseBody
public String printLabel( HttpServletRequest request, 
        HttpServletResponse response,@RequestParam String data)
{
	String result="";
	ArrayList<Item>itemData=null;
	try
	{

	itemData=new ArrayList<Item>();
	ArrayList<String>itemArray=new ArrayList<String>(Arrays.asList(data.split("###")));
	
	for (int i=0;i<itemArray.size();i++)
		{
			Item object=new Item();
			if (itemArray.get(i).startsWith("&&&No Info Provided&&&"))
			{
				itemArray.set(i, itemArray.get(i).replaceFirst("&&&No Info Provided&&&", ""));	
			}
			ArrayList<String>objectString=new ArrayList<String>(Arrays.asList(itemArray.get(i).split("&&&")));
	
			if (objectString.size()>1)
					{
						
							object.setInvName(objectString.get(2));
							object.setSku(objectString.get(0));
							object.setBrand(objectString.get(3));
							object.setModel(objectString.get(6));
							object.setDescription(objectString.get(7));
							object.setItemCode(objectString.get(1));
							object.setNoOfItems(objectString.get(16));
							object.setReason(objectString.get(17));
								
					
						itemData.add(object);
					}
		}
	}
	catch (Exception e) {
		// TODO Auto-generated catch block
 		result=result+" "+e.toString();
		e.printStackTrace();
	}
//	LabelPrint lP=new LabelPrint();
	TestPrint print=new  TestPrint();
	 ServletContext context = request.getServletContext();
     String appPath = context.getRealPath("");
	result=print.executePrint(itemData,appPath);
	//String result2=lP.executePrint(itemData);
	 return result;
	}

	
	@RequestMapping(value="/printLabel")
	@ResponseBody
	public String  printLabelInvAlertTable( HttpServletRequest request, 
            HttpServletResponse response, @RequestParam String data)
	{
		String result="";
		ArrayList<Item>itemData=null;
		try
		{
		itemData=new ArrayList<Item>();
		ArrayList<String>itemArray=new ArrayList<String>(Arrays.asList(data.split("###")));
		
		for (int i=0;i<itemArray.size();i++)
			{
				Item object=new Item();
				
				ArrayList<String>objectString=new ArrayList<String>(Arrays.asList(itemArray.get(i).split("&&&")));
		
				if (objectString.size()>1)
						{
							
								DB db=new DB();
								object.setSku(objectString.get(0));
								object.setItemCode(objectString.get(1));
								object.setInvName(objectString.get(2));
								object.setBrand(objectString.get(3));
								object.setModel(objectString.get(4));
								object.setCategoryName(objectString.get(5));
								object.setSubCategoryName(objectString.get(7));
								object.setNoOfItems(objectString.get(8));
								object=db.getItemInfo(object);
								itemData.add(object);
						}
			}
		}
		catch (Exception e) {
			// TODO Auto-generated catch block
	 		result=result+" "+e.toString();
			e.printStackTrace();
		}
	//	LabelPrint lP=new LabelPrint();
		TestPrint print=new  TestPrint();
		  // get absolute path of the application
        ServletContext context = request.getServletContext();
        String appPath = context.getRealPath("");
		result=print.executePrint(itemData,appPath);
	//	String result2=lP.executePrint(itemData);
		return result;
	
		}
	
	
	

	@RequestMapping(value="/printLabels")
	public ModelAndView printLabels()
	{
		DB db=new DB();
		ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
		ModelAndView modelAndView=new ModelAndView("printLabelSearch");
		modelAndView.addObject("senderInventoryList",list.get(4));
		return modelAndView;
	}
	
	
	@RequestMapping(value="/printIndividualLabel")
	@ResponseBody
	public String  printIndividualLabel( HttpServletRequest request, 
            HttpServletResponse response,@RequestParam String data)
	{
		
		ArrayList<String>objectString=new ArrayList<String>(Arrays.asList(data.split("&&&")));
		Item item=new Item();
		if (objectString.get(1)==null || objectString.get(1).equals("null"))
			item.setSku("");
		else
		item.setSku(objectString.get(1));
		item.setItemCode(objectString.get(0));
		item.setInvName(objectString.get(2));
		item.setBrand("");
		item.setCategoryName("");
		item.setSubCategoryName("");
		item.setDescription("");
		String result="";
		DB db=new DB();
		ArrayList<Item> itemData=db.searchKey(item,currentUser);
		if (itemData.size()==0)
		{
			result="No Item Found";
		}
		else
		{
			TestPrint print=new  TestPrint();
			// get absolute path of the application
	        ServletContext context = request.getServletContext();
	        String appPath = context.getRealPath("");
			result=print.executePrint(itemData,appPath);
			
		}
			return result;
	
		}
	
	
	
	  @RequestMapping(value="/startDownload",method = RequestMethod.GET)
	    public void doDownload(HttpServletRequest request,
	            HttpServletResponse response) throws IOException {
	 
	        // get absolute path of the application
	        ServletContext context = request.getServletContext();
	        String appPath = context.getRealPath("");
	      //  System.out.println("appPath = " + appPath);
	 
	        // construct the complete absolute path of the file
	        
	        String fullPath = appPath + "Label.pdf";      
	      //  System.out.println(fullPath);
	        File downloadFile = new File(fullPath);
	        FileInputStream inputStream = new FileInputStream(downloadFile);
	         
	        // get MIME type of the file
	        String mimeType = context.getMimeType(fullPath);
	        if (mimeType == null) {
	            // set to binary type if MIME mapping not found
	            mimeType = "application/octet-stream";
	        }
	        //System.out.println("MIME type: " + mimeType);
	 
	        // set content attributes for the response
	        response.setContentType(mimeType);
	        response.setContentLength((int) downloadFile.length());
	 
	        // set headers for the response
	        String headerKey = "Content-Disposition";
	        String headerValue = String.format("attachment; filename=\"%s\"",
	                downloadFile.getName());
	        response.setHeader(headerKey, headerValue);
	 
	        // get output stream of the response
	        OutputStream outStream = response.getOutputStream();
	         int BUFFER_SIZE = 4096;
	        byte[] buffer = new byte[BUFFER_SIZE];
	        int bytesRead = -1;
	 
	        // write bytes read from the input stream into the output stream
	        while ((bytesRead = inputStream.read(buffer)) != -1) {
	            outStream.write(buffer, 0, bytesRead);
	        }
	 
	        inputStream.close();
	        outStream.close();
	 
	    }
	@RequestMapping(value="/groupTransferRequest")
	public ModelAndView groupTransferRequest(@RequestParam String submitButton,TransferObject object)
	{
		String validationReason="";
		Boolean validationBoolean=false;
		DB db=new DB();
		TransferObject result=new TransferObject();
		if (!(object.getBrand()==null) && !(object.getCategoryName()==null) && !(object.getSubCategoryName()==null) && !(object.getModel()==null))
		{
			result=db.getTransferOutData(object,"1");
			if (!(result.getSku().equals("")))
			{
				validationBoolean=true;
			}
			else
				validationReason="Incorrect Brand/Model/Category/SubCategory combination provided";	
			
		}
		else if (!(object.getItemCodeFrom().equals("")) && (!(object.getItemCodeTo().equals(""))))
		{
			result=db.getTransferOutData(object,"2");
			if (!(result.getSku().equals("")))
			{
				validationBoolean=true;
			}
			else
				validationReason="Incorrect ItemCode provided";
				
		}
		else if (!(object.getSku()==null))
		{
			result=db.getTransferOutData(object,"3");
			if (!(result.getBrand().equals("")))
			{
				validationBoolean=true;
			}
			else
				validationReason="Incorrect SKU provided";
		}
		groupTransferList.add(result);
		if (submitButton.equals("Review Transfer List"))
			{
				if (validationBoolean)
				return new ModelAndView("reviewGroupTransfer","groupTransferList",groupTransferList);
				else
				return new	ModelAndView("tryAgain","validationReason",validationReason);
			}
		else
			{
			ModelAndView modelAndView=new ModelAndView("initiateGroupTransfer");
			 
			 ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
				ArrayList<HashMap<String, String>>locationMap=db.getLocationMap();
				Multimap<String, String>categoryMap=db.getCategoryMap();
				modelAndView.addObject("senderInventoryList",list.get(4));
				ArrayList<String>receiverInventoryList=new ArrayList<String>();
				receiverInventoryList.addAll(list.get(4));
				receiverInventoryList.remove("HEADQUATERS");
				modelAndView.addObject("receiverInventoryList",receiverInventoryList);
				modelAndView.addObject("subCategoryList",list.get(0));
				modelAndView.addObject("categoryList",list.get(1));
				modelAndView.addObject("brandList",list.get(2));
				String skuList=list.get(5).toString().replaceAll("\\[", "\\['").replaceAll(",", "','").replaceAll("\\]", "'\\]");
				modelAndView.addObject("skuList",skuList);
				String locationMapString=locationMap.get(1).toString().replaceAll("=", ":").replaceAll(" ", "");
				modelAndView.addObject("locationMap",locationMapString);
				String categoryMapString=categoryMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
				modelAndView.addObject("categoryMap",categoryMapString);
				String inventoryMapString=locationMap.get(0).toString().replaceAll("=", ":'").replaceAll(",","',");
				inventoryMapString=inventoryMapString.substring(0, inventoryMapString.length()-1)+"'"+inventoryMapString.substring(inventoryMapString.length()-1, inventoryMapString.length());
				modelAndView.addObject("inventoryMapString",inventoryMapString);
				if (validationBoolean)
					return modelAndView;
					else
					return new	ModelAndView("tryAgain");

			
			}
		
	}
	@RequestMapping(value="/cycleCount")
	public ModelAndView openCycleCountPage()
	{
		
		DB db=new DB();
		
		ArrayList<Item>dataList=db.createCycleCountInventoryList(currentUser);
		if (dataList.size()==0)
			return new ModelAndView("employeeoptions");
		else
		return new ModelAndView("cycleCount","dataList",dataList);
		
	}
	@RequestMapping(value="/cycleCountRequest")
	public ModelAndView CycleCountPageRequest(@RequestParam String result)
	{
		HashMap<String,Item>cycleCountList=new HashMap<String,Item>();
		ArrayList<String>itemArray=new ArrayList<String>(Arrays.asList(result.split("###")));
		for (int i=0;i<itemArray.size();i++)
			{
				Item object=new Item();
				String testString=itemArray.get(i).replaceFirst("&&&", "");
					ArrayList<String>objectString=new ArrayList<String>(Arrays.asList(testString.split("&&&")));
						if (objectString.size()>1)
						{
							object.setInvName(objectString.get(1));
							object.setSku(objectString.get(0));
							object.setNoOfItems(objectString.get(11));
							cycleCountList.put(objectString.get(1)+objectString.get(0),object);	
						}
			}
		DB db=new DB();
		db.doCycleCount(cycleCountList,currentUser);
		
		return new ModelAndView("employeeoptions");
	}
	
	
	@RequestMapping(value="/cancelTransferRequest")
	public ModelAndView cancelTransferRequest()
	{
		groupTransferList.clear();
		return new ModelAndView("employeeoptions");
	}
	
	@RequestMapping(value="/assignInventory")
	public ModelAndView assignInventory()
	{
		DB db=new DB();
		String skuList=db.getSkuListJSArray();
		ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
		ModelAndView modelAndView=new ModelAndView("assignInventory");
		modelAndView.addObject("brandList",list.get(2));
		modelAndView.addObject("skuList",skuList);
		Multimap<String, String>brandMap=db.getBrandMap();
		String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		modelAndView.addObject("brandMap",brandMapString);
		Multimap<String, String>modelMap=db.getmodelMap();
		String modelMapString=modelMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		modelAndView.addObject("modelMap",modelMapString);
		return modelAndView;
	}
	
	
	@RequestMapping(value="/assignInventoryRequest")
	public ModelAndView searchItemRequest(Item item)
	{
		DB db=new DB();
		
		Item result=db.searchUnassignedItem(item);
		ModelAndView modelAndView=null;
		if (result.getBrand()==null)
		{
			modelAndView=new ModelAndView("resultadmin");
			modelAndView.addObject("result","No Item Found");
		}	
		else
		{
			modelAndView=new ModelAndView("showUnassignedItem");
			modelAndView.addObject("result",result);
			ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
			modelAndView.addObject("senderInventoryList",list.get(4));
			}
		return modelAndView;
	}
	
	@RequestMapping(value="/itemAssignmentFormRequest")
	public ModelAndView assignInventoryRequest(Item item)
	{
		DB db=new DB();
		Item item2=new Item();
		item2=item;
		String result1=db.assignInventoryToItem(item2,currentUser);
		ModelAndView modelAndView=new ModelAndView("resultadmin");
		modelAndView.addObject("result",result1);
		return modelAndView;
	}
	
	@RequestMapping(value="/seediscrepancies")
	public ModelAndView seediscrepancies()
	{
		DB dB=new DB();
		 Multimap<String, String>loginIDList=dB.getLoginIDList1();
		 String loginIDListString=loginIDList.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
		 ArrayList<String>loginIDList1=dB.getLoginIDList();
		ModelAndView modelAndView=new ModelAndView("seediscrepancies");
		modelAndView.addObject("loginIDListString",loginIDListString);
		modelAndView.addObject("loginIDList1",loginIDList1);
		return modelAndView;
	}
	
	
	
	@RequestMapping(value="/seediscrepanciesRequest")
	public ModelAndView seediscrepanciesRequest(LogObject logObject)
	{
		
		ModelAndView modelAndView=new ModelAndView("showdiscrepancies");
		DB db=new DB();
		ArrayList<LogObject> discrepanciesObjectList=db.getDiscrepancies(logObject);
		modelAndView.addObject("dataList",discrepanciesObjectList);
		return modelAndView;
	}
	@RequestMapping(value="/seeLog")
	public ModelAndView seeLog()
	{
		
		DB dB=new DB();
		ArrayList<String>loginIDList=dB.getLoginIDList();
		ArrayList<String>logCategoryList=new ArrayList<String>();
		logCategoryList.add("SignIn/SignOut Activity");
		logCategoryList.add("Password Change Activity");
		logCategoryList.add("New Key");
		logCategoryList.add("Item");
		logCategoryList.add("Initiated Transfers");
		logCategoryList.add("Completed Transfer");
		logCategoryList.add("Recieve Shipment");
		logCategoryList.add("Technician");
		logCategoryList.add("Category");
		logCategoryList.add("Sub Category");
		logCategoryList.add("Brand");
		logCategoryList.add("Reason");
		logCategoryList.add("Model");
		ModelAndView modelAndView=new ModelAndView("seeLog");
		modelAndView.addObject("loginIDList", loginIDList);
		modelAndView.addObject("logCategoryList", logCategoryList);
		return modelAndView;
	}
	
	@RequestMapping(value="/searchUserlogs")
	public ModelAndView searchUserlogs(Login l)
	{
		DB db=new DB();
	
		ModelAndView modelAndView;
		ArrayList<LogObject> logObjectList=db.getLog(l,l.getLoginID());
		if (logObjectList.size()==0)
			modelAndView=new ModelAndView("adminoptions","result","No Log Info Found");	
		else
		{
			modelAndView=new ModelAndView("showLogs");
			modelAndView.addObject("dataList",logObjectList);
			modelAndView.addObject("condition",l.getLogCategory());
			HashMap<String, ArrayList<String>>hashmap=new HashMap<String, ArrayList<String>>();
			if (l.getLogCategory().equals("SignIn/SignOut Activity"))
			{
				
				for (int i=0;i<logObjectList.size();i++)
				{
					ArrayList<String>values=new ArrayList<String>();
					values.add(logObjectList.get(i).getLogDateRangeFrom().split(" ")[1]);
					values.add(logObjectList.get(i).getLogDateRangeTo().split(" ")[1]);
					values.add(logObjectList.get(i).getUser());
					hashmap.put(logObjectList.get(i).getDate().split(" ")[0],values );
				}
				modelAndView.addObject("attendanceMap",hashmap);
			}	
		}	
		
		return modelAndView;
	}
	
	
	
	@RequestMapping(value="/discrepanciesFormRequest")
	public ModelAndView discrepanciesFormRequest(@RequestParam String result)
	{
		DB db=new DB();
		String testString=result.replaceFirst("&&&", "");
		ArrayList<String>itemArray=new ArrayList<String>(Arrays.asList(testString.split("&&&")));
		DiscrepanciesObject discrepanciesObject=new DiscrepanciesObject();
		discrepanciesObject.setSku(itemArray.get(0));
		discrepanciesObject.setInvName(itemArray.get(1));
		discrepanciesObject.setInvNoOfItems(itemArray.get(7));
		discrepanciesObject.setUserNoOfItems(itemArray.get(8));
		discrepanciesObject.setUser(itemArray.get(9));
		discrepanciesObject.setBrand(itemArray.get(3));
		discrepanciesObject.setItemCode(itemArray.get(2));
		String finalResult=db.discrepanciesCorrection(discrepanciesObject);
		return new ModelAndView("adminoptions","finalResult",finalResult);
		
	}
	
	@RequestMapping(value="/getReorderInfo")
	public ModelAndView getReorderInfo()
	{
		InventoryCountJob db=new InventoryCountJob();
		ArrayList<Item>result=db.getReorderData(currentUser);
		 System.out.println(result);
		if (result.size()==0)
			return new ModelAndView("adminoptions","finalResult","No Item Is UnderStocked");
		else
		return new ModelAndView("showRestockInfo","result",result);
	}
	
	
	
	
	@RequestMapping(value="/assignModelTrimSearch")
	public ModelAndView assignModelTrimSearch()
	{
		DB db=new DB();
		String skuList=db.getSkuListJSArray();
		ModelAndView modelAndView=new ModelAndView("assignModelTrimSearch");
		modelAndView.addObject("skuList",skuList);
			return modelAndView;
	
	}
	
	
	@RequestMapping(value="/assignModelTrimRequest")
	public ModelAndView assignModelTrimRequest(Item item)
	{
		DB db=new DB();
		Item result=db.searchUnassignedModelTrimItem(item);
		ModelAndView modelAndView=null;
		if (result.getSku()==null)
		{
			modelAndView=new ModelAndView("resultadmin");
			modelAndView.addObject("result","No Item Found Or Item has already assigned Brand(s)");
		}	
		else
		{
			modelAndView=new ModelAndView("showUnassignedBrandTrimItem");
			ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
			modelAndView.addObject("brandList",list.get(2));
			Multimap<String, String>brandMap=db.getBrandMap();
			String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView.addObject("brandMap",brandMapString);
			Multimap<String, String>modelMap=db.getmodelMap();
			String modelMapString=modelMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView.addObject("modelMap",modelMapString);
			modelAndView.addObject("result",result);
			}
		return modelAndView;
	
	}
	
	private ArrayList<Item>assignmentList=new ArrayList<Item>();
	@RequestMapping(value="/trimModelItemAssignmentFormRequest")
	public ModelAndView trimModelItemAssignmentFormRequest(Item item,@RequestParam String result,@RequestParam String buttonName)
	{
		ModelAndView modelAndView=null;
		DB db=new DB();
		if (buttonName.equals("assignMoreButton"))
		{
			Item resultItem=db.searchUnassignedModelTrimItem(item);
			System.out.println(item);
			assignmentList.add(item);
			modelAndView=new ModelAndView("showUnassignedBrandTrimItem");
			ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
			modelAndView.addObject("brandList",list.get(2));
			Multimap<String, String>brandMap=db.getBrandMap();
			String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView.addObject("brandMap",brandMapString);
			Multimap<String, String>modelMap=db.getmodelMap();
			String modelMapString=modelMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView.addObject("modelMap",modelMapString);
			modelAndView.addObject("result",resultItem);
			modelAndView.addObject("dataList",assignmentList);
			
		}
		else
		{
			if (assignmentList.size()==0)
					assignmentList.add(item);
			String finalResult=db.assignModelTrimItem(assignmentList);
			assignmentList.clear();
			modelAndView= new ModelAndView("adminoptions","finalResult",finalResult);
		}
		return modelAndView;
	

	}
	/*@RequestMapping(value="/printTransferLabel")
	public String printTransferLabel(@RequestParam String data,HttpServletRequest request, HttpServletResponse response )
	{
		TestPrint print=new  TestPrint();
		 ServletContext context = request.getServletContext();
	     String appPath = context.getRealPath("");
		String result=print.executeLabelPrint(data,appPath);
		return result;
		
	}*/
	
	@RequestMapping(value="/showUnassignedItems")
	public ModelAndView showUnassignedItems()
	{
		DB db=new DB();
		ArrayList<Item>unassignedList=db.getUnassignedItems();
		ModelAndView modelAndView=new ModelAndView("showUnassignedItems");
		modelAndView.addObject("unassignedList",unassignedList);
			return modelAndView;
	
	}
	
	@RequestMapping(value="/showUnassignedInventoryItems")
	public ModelAndView showUnassignedInventoryItems()
	{
		DB db=new DB();
		ArrayList<Item>unassignedList=db.getUnassignedInventoryItems();
		ModelAndView modelAndView=new ModelAndView("showUnassignedInventoryItems");
		modelAndView.addObject("unassignedList",unassignedList);
			return modelAndView;
	
	}
	
	@RequestMapping(value="/updateUnassignedItem")
	public ModelAndView updateUnassignedItem(Item item)
	{
		DB db=new DB();
		ModelAndView modelAndView=new ModelAndView("updateModelTrimSearch");
		String skuList=db.getSkuListJSArray();
		modelAndView.addObject("skuList",skuList);
		return modelAndView;
	
	}
	
	
	@RequestMapping(value="/updateAssignedBrandTrimItem")
	public ModelAndView updateAssignedBrandTrimItem(Item item)
	{
		DB db=new DB();
		ModelAndView modelAndView=new ModelAndView("updateBrandTrimSearch");
		String skuList=db.getSkuListJSArray();
		modelAndView.addObject("skuList",skuList);
		return modelAndView;
	
	}
	
	@RequestMapping(value="/updateAssignedBrandTrimItemSearchRequest")
	public ModelAndView updateAssignedBrandTrimItemSearchRequest(Item item)
	{
		DB db=new DB();
		Item assignedBrandTrimItem=db.getBrandTrimDetials(item);
		
		ModelAndView modelAndView=null;
		if (assignedBrandTrimItem.getSku()==null)
		{
			modelAndView=new ModelAndView("resultadmin","result","No Item Found");
		}
		else
		{
			unassignedItem1=new Item();unassignedItem1=assignedBrandTrimItem;
			unassignedItem1.setImageURLByteArray(assignedBrandTrimItem.getImageURLByteArray());
			ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
			Multimap<String, String>categoryMap=db.getCategoryMap();
			String categoryMapString=categoryMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView=new ModelAndView("updateAssignedBrandTrimItem");
			modelAndView.addObject("unassignedItem",assignedBrandTrimItem);
			modelAndView.addObject("loopCount",new ArrayList<String>(Arrays.asList(assignedBrandTrimItem.getBrand().split(","))).size());
			modelAndView.addObject("brandList", new ArrayList<String>(Arrays.asList(assignedBrandTrimItem.getBrand().split(","))));
			modelAndView.addObject("modelList",new ArrayList<String>(Arrays.asList(assignedBrandTrimItem.getModel().split(","))));
			modelAndView.addObject("trimList",new ArrayList<String>(Arrays.asList(assignedBrandTrimItem.getTrim().split(","))));
			modelAndView.addObject("fromYearList",new ArrayList<String>(Arrays.asList(assignedBrandTrimItem.getFromYear().split(","))));
			modelAndView.addObject("toYearList",new ArrayList<String>(Arrays.asList(assignedBrandTrimItem.getToYear().split(","))));
			modelAndView.addObject("categoryMap",categoryMapString);
			Multimap<String, String>brandMap=db.getBrandMap();
			Multimap<String, String>modelMap=db.getmodelMap();
			String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView.addObject("brandMap",brandMapString);
			String modelMapString=modelMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView.addObject("modelMap",modelMapString);
			modelAndView.addObject("dropdownbrandList", list.get(2));
			modelAndView.addObject("categoryList",list.get(1));
			ArrayList<String>transponderList=db.getTransponderList();
			String transponderListString=transponderList.toString().replaceAll("\\[", "'").replaceAll("\\]", "'");
			modelAndView.addObject("transponderList",transponderListString);
		}
				return modelAndView;
	
	}
	
	
	
	@RequestMapping(value="/updateAssignedBrandTrimItemRequest")
	public ModelAndView updateAssignedBrandTrimItemRequest(Item item,@RequestParam String result,@RequestParam String buttonName)
	{
		
		ModelAndView modelAndView=null;
		DB db=new DB();
	
		if (buttonName.equals("assignMoreButton"))
		{
			ArrayList<String>itemArray=new ArrayList<String>(Arrays.asList(result.split("&&&")));
			Item item1=new Item();
			item1.setBrand(itemArray.get(0));
			item1.setModel(itemArray.get(1));
			item1.setTrim(itemArray.get(2));
			item1.setFromYear(itemArray.get(3));
			item1.setToYear(itemArray.get(4));
			assignmentList.add(item1);
			Multimap<String, String>categoryMap=db.getCategoryMap();
			String categoryMapString=categoryMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView=new ModelAndView("updateAssignedBrandTrimItem");
			modelAndView.addObject("unassignedItem",unassignedItem1);
			ArrayList<String>brandList=new ArrayList<String>(Arrays.asList(unassignedItem1.getBrand().split(",")));
			ArrayList<String>modelList=new ArrayList<String>(Arrays.asList(unassignedItem1.getModel().split(",")));
			ArrayList<String>trimList=new ArrayList<String>(Arrays.asList(unassignedItem1.getTrim().split(",")));
			ArrayList<String>fromYearList=new ArrayList<String>(Arrays.asList(unassignedItem1.getFromYear().split(",")));
			ArrayList<String>toYearList=new ArrayList<String>(Arrays.asList(unassignedItem1.getToYear().split(",")));
			for (int i=0;i<assignmentList.size();i++)
			{
				brandList.add(assignmentList.get(i).getBrand());
				modelList.add(assignmentList.get(i).getModel());
				trimList.add(assignmentList.get(i).getTrim());
				fromYearList.add(assignmentList.get(i).getFromYear());
				toYearList.add(assignmentList.get(i).getToYear());
			}
			modelAndView.addObject("loopCount",brandList.size());
			modelAndView.addObject("brandList", brandList);
			modelAndView.addObject("modelList",modelList);
			modelAndView.addObject("trimList",trimList);
			modelAndView.addObject("fromYearList",fromYearList);
			modelAndView.addObject("toYearList",toYearList);
			modelAndView.addObject("categoryMap",categoryMapString);
			Multimap<String, String>brandMap=db.getBrandMap();
			Multimap<String, String>modelMap=db.getmodelMap();
			String brandMapString=brandMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView.addObject("brandMap",brandMapString);
			String modelMapString=modelMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView.addObject("modelMap",modelMapString);

			ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
			modelAndView.addObject("dropdownbrandList", list.get(2));
			modelAndView.addObject("categoryList",list.get(1));
			ArrayList<String>transponderList=db.getTransponderList();
			String transponderListString=transponderList.toString().replaceAll("\\[", "'").replaceAll("\\]", "'");
			modelAndView.addObject("transponderList",transponderListString);
			
		}
		else
		{
			ArrayList<String>brandArray=new ArrayList<String>(Arrays.asList(result.split("###")));
			ArrayList<String>brand=new ArrayList<String>();
			ArrayList<String>model=new ArrayList<String>();
			ArrayList<String>trim=new ArrayList<String>();
			ArrayList<String>fromYear=new ArrayList<String>();
			ArrayList<String>toYear=new ArrayList<String>();
			for (int i=0;i<brandArray.size();i++)
			{
				if (!(brandArray.get(i).equals("")))
				{
					String row=brandArray.get(i).replaceFirst("&&&", "");
					ArrayList<String>info=new ArrayList<String>(Arrays.asList(row.split("&&&")));
					if (info.size()>0)
					{
						brand.add(info.get(0).trim());
						model.add(info.get(1).trim());
						trim.add(info.get(2).trim());
						fromYear.add(info.get(3).trim());
						toYear.add(info.get(4).trim());	
					}
						
				}
				
				
			}
			String brandString=brand.toString().replaceAll("\\[", "").replaceAll("\\]", "");
			String modelString=model.toString().replaceAll("\\[", "").replaceAll("\\]", "");
			String trimString=trim.toString().replaceAll("\\[", "").replaceAll("\\]", "");
			String fromYearString=fromYear.toString().replaceAll("\\[", "").replaceAll("\\]", "");
			String toYearString=toYear.toString().replaceAll("\\[", "").replaceAll("\\]", "");
			item.setBrand(brandString);
			item.setModel(modelString);
			item.setTrim(trimString);
			item.setFromYear(fromYearString);
			item.setToYear(toYearString);
			
			String finalResult=db.updateAssignBrandTrimItem(item,currentUser);
			assignmentList.clear();
			modelAndView= new ModelAndView("adminoptions","finalResult",finalResult);
		}
		return modelAndView;
	
	}
	@RequestMapping(value="/updateUnassignedItemSearchRequest")
	public ModelAndView updateUnassignedItemSearchRequest(Item item)
	{
		DB db=new DB();
		Item unassignedItem=db.getUnassignedItem(item);
		ModelAndView modelAndView=null;
		if (unassignedItem.getSku()==null)
		{
			modelAndView=new ModelAndView("resultadmin","result","No Item Found");
		}
		else
		{
			unassignedItem1=new Item();
			unassignedItem1.setImageURLByteArray(unassignedItem.getImageURLByteArray());
			Multimap<String, String>categoryMap=db.getCategoryMap();
			String categoryMapString=categoryMap.toString().replaceAll("=", ":").replaceAll("\\[", "\\['").replaceAll("\\]", "'\\]");
			modelAndView=new ModelAndView("updateUnassignedItem");
			modelAndView.addObject("unassignedItem",unassignedItem);
			modelAndView.addObject("categoryMap",categoryMapString);
			ArrayList<ArrayList<String>>list=db.getCategoryAndSubCategoryList(currentUser);
			modelAndView.addObject("categoryList",list.get(1));
			ArrayList<String>transponderList=db.getTransponderList();
			String transponderListString=transponderList.toString().replaceAll("\\[", "'").replaceAll("\\]", "'");
			modelAndView.addObject("transponderList",transponderListString);
		}
				return modelAndView;
	
	}
	
	
	@RequestMapping(value="/updateUnassignedItemRequest")
	public ModelAndView updateUnassignedItemRequest(Item item)
	{
		DB db=new DB();
		String result1=db.updateUnassignedItem(item,currentUser);
		ModelAndView modelAndView=new ModelAndView("resultadmin");
		modelAndView.addObject("result",result1);
			return modelAndView;
	
	}
	
	private Item unassignedItem1;
	
	@RequestMapping(value="/updateimageServletUnassignedItem")
	public void updateimageServletUnassignedItem(HttpServletRequest request, HttpServletResponse response)
	{
		 response.setHeader("expires", "0");
		response.setContentType("image/jpg");
		try {
		OutputStream output = response.getOutputStream();
		
			output.write(unassignedItem1.getImageURLByteArray());
			output.flush();
			output.close();
		} catch (IOException e ) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		catch (NullPointerException e ) {
			// TODO Auto-generated catch block
			System.out.println("No Image Found1");
		}
	}
	
	
	@RequestMapping(value="/changeLocationSearch")
	public ModelAndView changeLocationSearch()
	{
		DB db=new DB();
		ArrayList<String>senderInventoryList=db.getCategoryAndSubCategoryList(currentUser).get(4);
		ModelAndView modelAndView=new ModelAndView("changeLocationSearch","senderInventoryList",senderInventoryList);
		
			return modelAndView;
	
	}
	
	
	@RequestMapping(value="/changeLocationSearchRequest")
	public ModelAndView changeLocationSearchRequest(Item object)
	{
		DB db=new DB();
		
		object.setSubCategoryName("");
		object.setBrand("");
		object.setCategoryName("");
		object.setSubCategoryName("");
		object.setDescription("");
		ArrayList<String>data=db.changeLocationSearch(object);
		if (data.size()==0)
		{
			return new ModelAndView("resultadmin","result","No Result Found");
		}
		else
			return new ModelAndView("changeLocationSearchTable","data",data);
	}
	
	
	@RequestMapping(value="/changeLocationRequest")
	public ModelAndView changeLocationRequest(Item object)
	{
		DB db=new DB();
	String result=db.changeLocationrequest(object);
	return new ModelAndView("resultadmin","result",result);	
	}
	
}
