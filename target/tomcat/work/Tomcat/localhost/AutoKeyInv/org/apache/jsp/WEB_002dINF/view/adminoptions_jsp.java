/*
 * Generated by the Jasper component of Apache Tomcat
 * Version: Apache Tomcat/7.0.37
 * Generated at: 2016-12-27 13:54:46 UTC
 * Note: The last modified time of this file was set to
 *       the last modified time of the source file after
 *       generation to assist with modification tracking.
 */
package org.apache.jsp.WEB_002dINF.view;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class adminoptions_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final javax.servlet.jsp.JspFactory _jspxFactory =
          javax.servlet.jsp.JspFactory.getDefaultFactory();

  private static java.util.Map<java.lang.String,java.lang.Long> _jspx_dependants;

  private javax.el.ExpressionFactory _el_expressionfactory;
  private org.apache.tomcat.InstanceManager _jsp_instancemanager;

  public java.util.Map<java.lang.String,java.lang.Long> getDependants() {
    return _jspx_dependants;
  }

  public void _jspInit() {
    _el_expressionfactory = _jspxFactory.getJspApplicationContext(getServletConfig().getServletContext()).getExpressionFactory();
    _jsp_instancemanager = org.apache.jasper.runtime.InstanceManagerFactory.getInstanceManager(getServletConfig());
  }

  public void _jspDestroy() {
  }

  public void _jspService(final javax.servlet.http.HttpServletRequest request, final javax.servlet.http.HttpServletResponse response)
        throws java.io.IOException, javax.servlet.ServletException {

    final javax.servlet.jsp.PageContext pageContext;
    javax.servlet.http.HttpSession session = null;
    final javax.servlet.ServletContext application;
    final javax.servlet.ServletConfig config;
    javax.servlet.jsp.JspWriter out = null;
    final java.lang.Object page = this;
    javax.servlet.jsp.JspWriter _jspx_out = null;
    javax.servlet.jsp.PageContext _jspx_page_context = null;


    try {
      response.setContentType("text/html");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;

      out.write("<!DOCTYPE html>\r\n");
      out.write("<html>\r\n");
      out.write("<head>\r\n");
      out.write("   <title>Admin Options</title>\r\n");
      out.write("   <link href=\"js/bootstrap.min.css\" rel=\"stylesheet\">\r\n");
      out.write("   <script src=\"js/jquery-1.9.1.js\"></script>\r\n");
      out.write("   <script src=\"js/bootstrap.min.js\"></script>\r\n");
      out.write("</head>\r\n");
      out.write("<style>\r\n");
      out.write("li a, .dropbtn {\r\n");
      out.write("    display: inline-block;\r\n");
      out.write("    color: black;\r\n");
      out.write("    text-align: center;\r\n");
      out.write("    padding: 14px 16px;\r\n");
      out.write("    text-decoration: none;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("li a:hover, .dropdown:hover .dropbtn {\r\n");
      out.write("    background-color: red;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write("li.dropdown {\r\n");
      out.write("    display: inline-block;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown-content {\r\n");
      out.write("    display: none;\r\n");
      out.write("    position: absolute;\r\n");
      out.write("    background-color: #f9f9f9;\r\n");
      out.write("    min-width: 160px;\r\n");
      out.write("    box-shadow: 0px 8px 16px 0px rgba(0,0,0,0.2);\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown-content a {\r\n");
      out.write("    color: black;\r\n");
      out.write("    padding: 12px 16px;\r\n");
      out.write("    text-decoration: none;\r\n");
      out.write("    display: block;\r\n");
      out.write("    text-align: left;\r\n");
      out.write("}\r\n");
      out.write("\r\n");
      out.write(".dropdown-content a:hover {background-color: #f1f1f1}\r\n");
      out.write("\r\n");
      out.write(".dropdown:hover .dropdown-content {\r\n");
      out.write("    display: block;\r\n");
      out.write("}</style>\r\n");
      out.write("\r\n");
      out.write("<body>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<ul class=\"nav nav-tabs\">\r\n");
      out.write("   <li class=\"active\"><a href=\"#\">Admin Options\t</a></li>\r\n");
      out.write("   <li class=\"dropdown\">\r\n");
      out.write("      \r\n");
      out.write("      \r\n");
      out.write("         ");
      out.write("\r\n");
      out.write("         <li class=\"dropdown\">\r\n");
      out.write("  \t\t <a href=\"#\" class=\"dropbtn\">User</a>\r\n");
      out.write("    \t<div class=\"dropdown-content\">\r\n");
      out.write("     \t \t<a href=\"addTechnician\">Add</a>\r\n");
      out.write("     \t\t <a href=\"updateTechnician\">Update</a>\r\n");
      out.write("      \t\t<a href=\"removeTechnician\">Remove</a>\r\n");
      out.write("    \t</div>\r\n");
      out.write(" \t\t </li>\r\n");
      out.write("         <li class=\"divider\"></li>\r\n");
      out.write("          <li class=\"dropdown\">\r\n");
      out.write("  \t\t <a href=\"#\" class=\"dropbtn\">Category</a>\r\n");
      out.write("    \t<div class=\"dropdown-content\">\r\n");
      out.write("     \t \t<a href=\"addCategory\">Add</a>\r\n");
      out.write("     \t\t <a href=\"searchCategory\">Update</a>\r\n");
      out.write("      \t\t<a href=\"searchDeleteCategoryTable\">Remove</a>\r\n");
      out.write("    \t</div>\r\n");
      out.write(" \t\t </li>\r\n");
      out.write("         <li class=\"divider\"></li>\r\n");
      out.write("          <li class=\"dropdown\">\r\n");
      out.write("  \t\t <a href=\"#\" class=\"dropbtn\">Sub Category</a>\r\n");
      out.write("    \t<div class=\"dropdown-content\">\r\n");
      out.write("     \t \t<a href=\"addSubCategory\">Add</a>\r\n");
      out.write("     \t\t <a href=\"searchSubCategory\">Update</a>\r\n");
      out.write("      \t\t<a href=\"searchDeleteSubCategoryTable\">Remove</a>\r\n");
      out.write("    \t</div>\r\n");
      out.write(" \t\t </li>\r\n");
      out.write("         <li class=\"divider\"></li>\r\n");
      out.write("          <li class=\"dropdown\">\r\n");
      out.write("  \t\t <a href=\"#\" class=\"dropbtn\">Brand</a>\r\n");
      out.write("    \t<div class=\"dropdown-content\">\r\n");
      out.write("     \t \t<a href=\"addBrand\">Add</a>\r\n");
      out.write("     \t\t <a href=\"searchBrand\">Update</a>\r\n");
      out.write("      \t\t<a href=\"searchDeleteBrand\">Remove</a>\r\n");
      out.write("    \t</div>\r\n");
      out.write(" \t\t </li>\r\n");
      out.write(" \t\t  <li class=\"divider\"></li>\r\n");
      out.write("          <li class=\"dropdown\">\r\n");
      out.write("  \t\t <a href=\"#\" class=\"dropbtn\">Model</a>\r\n");
      out.write("    \t<div class=\"dropdown-content\">\r\n");
      out.write("     \t \t<a href=\"addModel\">Add</a>\r\n");
      out.write("     \t\t <a href=\"searchModel\">Update</a>\r\n");
      out.write("      \t\t<a href=\"searchDeleteModel\">Remove</a>\r\n");
      out.write("    \t</div>\r\n");
      out.write(" \t\t </li>\r\n");
      out.write(" \t\t  <li class=\"divider\"></li>\r\n");
      out.write("          <li class=\"dropdown\">\r\n");
      out.write("  \t\t <a href=\"#\" class=\"dropbtn\">Trim</a>\r\n");
      out.write("    \t<div class=\"dropdown-content\">\r\n");
      out.write("     \t \t<a href=\"addTrim\">Add</a>\r\n");
      out.write("     \t\t <a href=\"searchTrim\">Update</a>\r\n");
      out.write("      \t\t<a href=\"searchDeleteTrim\">Remove</a>\r\n");
      out.write("    \t</div>\r\n");
      out.write(" \t\t </li>\r\n");
      out.write("         <li class=\"divider\"></li>\r\n");
      out.write("          <li class=\"dropdown\">\r\n");
      out.write("  \t\t <a href=\"#\" class=\"dropbtn\">Item</a>\r\n");
      out.write("    \t<div class=\"dropdown-content\">\r\n");
      out.write("     \t \t <a href=\"addnewitem\">Add New Item</a>\r\n");
      out.write("     \t \t <a href=\"assignModelTrimSearch\">Assign Model & Trim</a>\r\n");
      out.write("     \t\t<a href=\"updatesearchadmin\">Update Item</a>\r\n");
      out.write("      \t\t<a href=\"deleteItem\">Delete Item</a>\r\n");
      out.write("      \t\t<a href=\"changeLocation\">Change Item Location</a>\r\n");
      out.write("      \t\t<a href=\"showUnassignedItems\">See Unassigned Items</a>\r\n");
      out.write("    \t</div>\r\n");
      out.write(" \t\t </li>\r\n");
      out.write("         <li class=\"divider\"></li>\r\n");
      out.write("          <li class=\"dropdown\">\r\n");
      out.write("  \t\t <a href=\"#\" class=\"dropbtn\">Inventory</a>\r\n");
      out.write("    \t<div class=\"dropdown-content\">\r\n");
      out.write("     \t \t<a href=\"createInventory\">Create</a>\r\n");
      out.write("     \t\t <a href=\"updateInventory\">Update</a>\r\n");
      out.write("      \t\t<a href=\"removeInventory\">Remove</a>\r\n");
      out.write("    \t</div>\r\n");
      out.write(" \t\t </li>\r\n");
      out.write(" \t\t  <li class=\"divider\"></li>\r\n");
      out.write("          <li class=\"dropdown\">\r\n");
      out.write("  \t\t <a href=\"#\" class=\"dropbtn\">Reason</a>\r\n");
      out.write("    \t<div class=\"dropdown-content\">\r\n");
      out.write("     \t \t<a href=\"addReason\">Create</a>\r\n");
      out.write("     \t\t <a href=\"searchReason\">Update</a>\r\n");
      out.write("      \t\t<a href=\"searchDeleteReasonTable\">Remove</a>\r\n");
      out.write("    \t</div>\r\n");
      out.write(" \t\t </li>\r\n");
      out.write(" \t\t  <li class=\"divider\"></li>\r\n");
      out.write("          <li class=\"dropdown\">\r\n");
      out.write("  \t\t <a href=\"#\" class=\"dropbtn\">Alerts</a>\r\n");
      out.write("    \t<div class=\"dropdown-content\">\r\n");
      out.write("     \t \t<a href=\"createAlerts\">Create</a>\r\n");
      out.write("     \t\t <a href=\"updateAlert\">Update</a>\r\n");
      out.write("      \t\t<a href=\"removeAlerts\">Remove</a>\r\n");
      out.write("    \t</div>\r\n");
      out.write(" \t\t </li>\r\n");
      out.write(" \t\t \r\n");
      out.write("            \r\n");
      out.write("              <li class=\"divider\"></li>\r\n");
      out.write("         <li><a href=\"seeLog\">Logs</a></li>\r\n");
      out.write("         <li class=\"divider\"></li>\r\n");
      out.write("         <li><a href=\"seediscrepancies\">Discrepancies</a></li>\r\n");
      out.write("              <li class=\"divider\"></li>\r\n");
      out.write("         <li><a href=\"getReorderInfo\">Reorder Info</a></li>\r\n");
      out.write("              <li class=\"divider\"></li>\r\n");
      out.write("         <li><a href=\"logout\">LogOut</a></li>\r\n");
      out.write("   \r\n");
      out.write("</ul>\r\n");
      out.write("\r\n");
      out.write("\r\n");
      out.write("<h3>");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.proprietaryEvaluate("${finalResult}", java.lang.String.class, (javax.servlet.jsp.PageContext)_jspx_page_context, null, false));
      out.write("</h3>\r\n");
      out.write("\r\n");
      out.write("</body>\r\n");
      out.write("</html>\r\n");
    } catch (java.lang.Throwable t) {
      if (!(t instanceof javax.servlet.jsp.SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          try { out.clearBuffer(); } catch (java.io.IOException e) {}
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
