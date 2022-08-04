package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;
import java.sql.ResultSet;
import entity.Bill;
import entity.OrderItems;
import entity.staffs;
import entity.orderDetail;
import entity.Order;
import entity.Customers;
import entity.Products;
import java.util.Vector;

public final class admin_jsp extends org.apache.jasper.runtime.HttpJspBase
    implements org.apache.jasper.runtime.JspSourceDependent {

  private static final JspFactory _jspxFactory = JspFactory.getDefaultFactory();

  private static java.util.List<String> _jspx_dependants;

  private org.glassfish.jsp.api.ResourceInjector _jspx_resourceInjector;

  public java.util.List<String> getDependants() {
    return _jspx_dependants;
  }

  public void _jspService(HttpServletRequest request, HttpServletResponse response)
        throws java.io.IOException, ServletException {

    PageContext pageContext = null;
    HttpSession session = null;
    ServletContext application = null;
    ServletConfig config = null;
    JspWriter out = null;
    Object page = this;
    JspWriter _jspx_out = null;
    PageContext _jspx_page_context = null;

    try {
      response.setContentType("text/html;charset=UTF-8");
      pageContext = _jspxFactory.getPageContext(this, request, response,
      			null, true, 8192, true);
      _jspx_page_context = pageContext;
      application = pageContext.getServletContext();
      config = pageContext.getServletConfig();
      session = pageContext.getSession();
      out = pageContext.getOut();
      _jspx_out = out;
      _jspx_resourceInjector = (org.glassfish.jsp.api.ResourceInjector) application.getAttribute("com.sun.appserv.jsp.resource.injector");

      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("\n");
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"utf-8\" />\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1, shrink-to-fit=no\" />\n");
      out.write("        <meta name=\"description\" content=\"\" />\n");
      out.write("        <meta name=\"author\" content=\"\" />\n");
      out.write("        <title>Admin Controller</title>\n");
      out.write("        <!-- Favicon-->\n");
      out.write("        <link rel=\"icon\" type=\"image/x-icon\" href=\"assets/favicon.ico\" />\n");
      out.write("        <!-- Bootstrap icons-->\n");
      out.write("        <link href=\"https://cdn.jsdelivr.net/npm/bootstrap-icons@1.5.0/font/bootstrap-icons.css\" rel=\"stylesheet\" />\n");
      out.write("        <!-- Core theme CSS (includes Bootstrap)-->\n");
      out.write("        <link href=\"css/styles.css\" rel=\"stylesheet\" />\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        ");

            String fullName = null;
            int rollNumber = 0;
            double sum = 0;
            java.util.Enumeration em = session.getAttributeNames();
            while (em.hasMoreElements()) {
                String key = em.nextElement().toString();
                if (key.equals("admin")) {
                    staffs staff = (staffs) session.getAttribute(key);
                    fullName = staff.getFirstName() + staff.getLastName();
                    rollNumber = staff.getStaffID();
                }
            }
            ResultSet rsOrderStatus = (ResultSet) request.getAttribute("rsOrderStatus");
            Vector<Order> vector1 = (Vector<Order>) request.getAttribute("vectorOrder");
            Vector<Products> vector = (Vector<Products>) request.getAttribute("productList");
            Vector<Customers> vectorCus = (Vector<Customers>) request.getAttribute("customerList");
            String status[] = {"New", "Process", "Done", "Null"};
            Vector<Bill> vectorBill = (Vector<Bill>) request.getAttribute("Bill");
            String message = (String) request.getAttribute("message");
        
      out.write("\n");
      out.write("        <!-- Navigation-->\n");
      out.write("        <nav class=\"navbar navbar-expand-lg navbar-light bg-light\">\n");
      out.write("            <div class=\"container px-4 px-lg-5\">\n");
      out.write("                <a class=\"navbar-brand\" href=\"#!\" style=\"border: 1px solid blue; padding: 20px; border-radius: 100%; background-color: blue; color: white\">Shop QQ</a>\n");
      out.write("                <button class=\"navbar-toggler\" type=\"button\" data-bs-toggle=\"collapse\" data-bs-target=\"#navbarSupportedContent\" aria-controls=\"navbarSupportedContent\" aria-expanded=\"false\" aria-label=\"Toggle navigation\"><span class=\"navbar-toggler-icon\"></span></button>\n");
      out.write("                <div class=\"collapse navbar-collapse\" id=\"navbarSupportedContent\">\n");
      out.write("                    <ul class=\"navbar-nav me-auto mb-2 mb-lg-0 ms-lg-5\">\n");
      out.write("                        <li class=\"nav-item\"><a class=\"nav-link active\" aria-current=\"page\" href=\"#!\">Home</a></li>\n");
      out.write("                        <li class=\"nav-item dropdown\">\n");
      out.write("                            <a class=\"nav-link dropdown-toggle\" id=\"navbarDropdown\" href=\"#\" role=\"button\" data-bs-toggle=\"dropdown\" aria-expanded=\"false\">Controller</a>\n");
      out.write("                            <ul class=\"dropdown-menu\" aria-labelledby=\"navbarDropdown\">\n");
      out.write("                                <li><a class=\"dropdown-item\" href=\"#!\">View Shop</a></li>\n");
      out.write("                                <li><a class=\"dropdown-item\" href=\"ControllerStore\">View Store</a></li>\n");
      out.write("                                <li><a class=\"dropdown-item\" href=\"ControllerStocks\">Controller Stock</a></li>\n");
      out.write("                                <li>\n");
      out.write("                                    <hr class=\"dropdown-divider\" />\n");
      out.write("                                </li>\n");
      out.write("                                <li><a class=\"dropdown-item\" style=\"color: #0c63e4\" href=\"ControllerAdmin?s=history\">History Manager</a></li>\n");
      out.write("                            </ul>\n");
      out.write("                        </li>\n");
      out.write("                    </ul>\n");
      out.write("\n");
      out.write("                    <nav class=\"d-flex my-auto mx-auto\">\n");
      out.write("                        <h6>RollNumber: ");
      out.print(rollNumber);
      out.write("</h6>\n");
      out.write("                    </nav>\n");
      out.write("\n");
      out.write("                    <nav class=\"d-flex my-auto mx-auto\">\n");
      out.write("                        <h6>FullName: ");
      out.print(fullName);
      out.write("</h6>\n");
      out.write("                    </nav>\n");
      out.write("\n");
      out.write("                    <nav class=\"d-flex my-auto mx-auto\">\n");
      out.write("                        <h6>Welcome, admin</h6>\n");
      out.write("                    </nav>\n");
      out.write("\n");
      out.write("                    <nav class=\"navbar\">\n");
      out.write("                        <form class=\"container-fluid justify-content-start\" action=\"ControllerAdmin\">\n");
      out.write("                            <input type=\"hidden\" name=\"s\" value=\"logout\">\n");
      out.write("                            <button class=\"btn btn-outline-success me-2\" type=\"submit\">LogOut</button>\n");
      out.write("                        </form>\n");
      out.write("                    </nav>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </nav>\n");
      out.write("        <!-- Header-->\n");
      out.write("        <header class=\"py-5\" style=\"background-color: rgb(11, 122, 98)\">\n");
      out.write("            <div class=\"container px-4 px-lg-5 my-5\">\n");
      out.write("                <div class=\"text-center text-white\">\n");
      out.write("                    <h1 class=\"display-4 fw-bolder\">Shop QQ</h1>\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </header>\n");
      out.write("        <!-- Section-->\n");
      out.write("\n");
      out.write("        <div>\n");
      out.write("            <div class=\"row\" class=\"d-flex my-auto\">\n");
      out.write("                <div class=\"col-md-2\" style=\"border-right: 1px solid black\">\n");
      out.write("                    <h3 style=\"text-align: center\">Menu</h3>\n");
      out.write("                    <div class=\"list-group\" style=\"margin-top: 20%; margin-bottom: 20%\">\n");
      out.write("                        <a href=\"#\" class=\"list-group-item list-group-item-action active\" aria-current=\"true\">\n");
      out.write("                            MANAGER\n");
      out.write("                        </a>\n");
      out.write("                        <a href=\"ControllerAdmin?s=productManager\" class=\"list-group-item list-group-item-action\">Product Manager</a>\n");
      out.write("                        <a href=\"ControllerAdmin?s=customerManager\" class=\"list-group-item list-group-item-action\">Customer Manager</a>\n");
      out.write("                        <a href=\"ControllerAdmin?s=orderManager\" class=\"list-group-item list-group-item-action\">Bill Manager</a>\n");
      out.write("\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <div class=\"col-md-10\">\n");
      out.write("                    <h3 style=\"text-align: center\">Controller</h3>\n");
      out.write("\n");
      out.write("                    ");
if (vector != null) {
      out.write("\n");
      out.write("                    <p><button style=\"margin-left: 2%\" type=\"button\" class=\"btn btn-outline-success\"><a style=\"text-decoration: none; color: blue\" href=\"insertProduct.jsp\">Add New Product</a></button></p>\n");
      out.write("                    <p>\n");
      out.write("                    <form class=\"d-flex\" action=\"ControllerProduct\" method=\"post\">\n");
      out.write("                        <input type=\"hidden\" name=\"s\" value=\"searchByID\">\n");
      out.write("                        <input style=\"margin-left: 2%;max-width: 200px\" class=\"form-control me-2\" type=\"search\" placeholder=\"Order ID\" name=\"search\" aria-label=\"Search\">\n");
      out.write("                        <button class=\"btn btn-outline-success\" type=\"submit\">Search ID</button>\n");
      out.write("                    </form>\n");
      out.write("                    </p>\n");
      out.write("                    <p>\n");
      out.write("                    <form class=\"d-flex\" action=\"ControllerProduct\" method=\"post\">\n");
      out.write("                        <input type=\"hidden\" name=\"s\" value=\"searchByName\">\n");
      out.write("                        <input style=\"margin-left: 2%;max-width: 200px\" class=\"form-control me-2\" type=\"search\" placeholder=\"Customer Name\" name=\"search\" aria-label=\"Search\">\n");
      out.write("                        <button class=\"btn btn-outline-success\" type=\"submit\">Search Name</button>\n");
      out.write("                    </form>\n");
      out.write("                    </p>\n");
      out.write("                    <p>\n");
      out.write("                    <form class=\"d-flex\" action=\"ControllerProduct\" method=\"post\">\n");
      out.write("                        <input type=\"hidden\" name=\"s\" value=\"searchByPrice\">\n");
      out.write("                        <input style=\"margin-left: 2%;max-width: 150px\" class=\"form-control \" type=\"search\" placeholder=\"Price from\" name=\"priceFrom\" aria-label=\"Search\">\n");
      out.write("                        <input style=\"margin-left: 1%;max-width: 150px\" class=\"form-control \" type=\"search\" placeholder=\"Price to\" name=\"priceTo\" aria-label=\"Search\">\n");
      out.write("                        <button class=\"btn btn-outline-success\" type=\"submit\">Search Price</button>\n");
      out.write("                    </form>\n");
      out.write("                    </p>\n");
      out.write("\n");
      out.write("                    ");
if (message != null) {
      out.write("\n");
      out.write("                    <p style=\"color: red; text-align: center\">");
      out.print(message);
      out.write("</p>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                    <table class=\"table table-striped table-bordered border-dark\" style=\"margin-left: 2%\">\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th scope=\"col\">product_id</th>\n");
      out.write("                                <th scope=\"col\">product_name</th>\n");
      out.write("                                <th scope=\"col\">model_year</th>\n");
      out.write("                                <th scope=\"col\">list_price</th>\n");
      out.write("                                <th scope=\"col\">brand_name</th>\n");
      out.write("                                <th scope=\"col\">category_name</th>\n");
      out.write("                                <th scope=\"col\">update</th>\n");
      out.write("                                <th scope=\"col\">delete</th>\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("                        <tbody>\n");
      out.write("                            ");
 for (Products p : vector) {
      out.write("\n");
      out.write("                            <tr>\n");
      out.write("                                <th scope=\"row\">");
      out.print(p.getProductID());
      out.write("</th>\n");
      out.write("                                <td>");
      out.print(p.getProductName());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(p.getModelYear());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(p.getListPrice());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(p.getBrandName());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(p.getCategoryName());
      out.write("</td>\n");
      out.write("                                <td> <button type=\"button\" class=\"btn btn-success\"><a style=\"text-decoration: none; color: white\" href=\"ControllerProduct?s=updateProduct&productID=");
      out.print(p.getProductID());
      out.write("\">Update</a> </button></td>\n");
      out.write("                                <td><button type=\"button\" class=\"btn btn-danger\"><a style=\"text-decoration: none; color: white\" href=\"ControllerProduct?s=deleteProduct&productID=");
      out.print(p.getProductID());
      out.write("\" >Delete</a></button></td>\n");
      out.write("                            </tr>\n");
      out.write("                            ");
}
      out.write("\n");
      out.write("                        </tbody>\n");
      out.write("                    </table>\n");
      out.write("                    ");
} else if (vectorCus != null) {
      out.write("\n");
      out.write("                    <p><button style=\"margin-left: 2%\" type=\"button\" class=\"btn btn-outline-success\"><a style=\"text-decoration: none; color: blue\" href=\"ControllerCustomer?s=register\">Create New Customer</a></button></p>\n");
      out.write("                    <p>\n");
      out.write("                    <form class=\"d-flex\" action=\"ControllerCustomer\" method=\"post\">\n");
      out.write("                        <input type=\"hidden\" name=\"s\" value=\"searchByID\">\n");
      out.write("                        <input style=\"margin-left: 2%;max-width: 200px\" class=\"form-control me-2\" type=\"search\" placeholder=\"Customer ID\" name=\"search\" aria-label=\"Search\">\n");
      out.write("                        <button class=\"btn btn-outline-success\" type=\"submit\">Search ID</button>\n");
      out.write("                    </form>\n");
      out.write("                    </p>\n");
      out.write("                    <p>\n");
      out.write("                    <form class=\"d-flex\" action=\"ControllerCustomer\" method=\"post\">\n");
      out.write("                        <input type=\"hidden\" name=\"s\" value=\"searchByName\">\n");
      out.write("                        <input style=\"margin-left: 2%;max-width: 200px\" class=\"form-control me-2\" type=\"search\" placeholder=\"Customer Name\" name=\"search\" aria-label=\"Search\">\n");
      out.write("                        <button class=\"btn btn-outline-success\" type=\"submit\">Search Name</button>\n");
      out.write("                    </form>\n");
      out.write("                    </p>\n");
      out.write("\n");
      out.write("                    ");
if (message != null) {
      out.write("\n");
      out.write("                    <p style=\"color: red; text-align: center\">");
      out.print(message);
      out.write("</p>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                    <table class=\"table table-striped table-bordered border-dark\" >\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th scope=\"col\">customer_id</th>\n");
      out.write("                                <th scope=\"col\">first_name</th>\n");
      out.write("                                <th scope=\"col\">last_name</th>\n");
      out.write("                                <th scope=\"col\">phone</th>\n");
      out.write("                                <th scope=\"col\">email</th>\n");
      out.write("                                <th scope=\"col\">street</th>\n");
      out.write("                                <th scope=\"col\">city</th>\n");
      out.write("                                <th scope=\"col\">state</th>\n");
      out.write("                                <th scope=\"col\">zip_code</th>\n");
      out.write("                                <th scope=\"col\">update</th>\n");
      out.write("                                <th scope=\"col\">delete</th>\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("                        <tbody>\n");
      out.write("                            ");
 for (Customers c : vectorCus) {
      out.write("\n");
      out.write("                            <tr>\n");
      out.write("                                <td scope=\"row\">");
      out.print(c.getCustomerID());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(c.getFirstName());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(c.getLastName());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(c.getPhone());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(c.getEmail());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(c.getStreet());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(c.getCity());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(c.getState());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(c.getZipCode());
      out.write("</td>\n");
      out.write("                                <td> <button type=\"button\" class=\"btn btn-success\"><a style=\"text-decoration: none; color: white\" href=\"ControllerCustomer?s=updateCustomer&customerID=");
      out.print(c.getCustomerID());
      out.write("\">Update</a> </button></td>\n");
      out.write("                                <td><button type=\"button\" class=\"btn btn-danger\"><a style=\"text-decoration: none; color: white\" href=\"ControllerCustomer?s=deleteCustomer&customerID=");
      out.print(c.getCustomerID());
      out.write("\" >Delete</a></button></td>\n");
      out.write("                            </tr>\n");
      out.write("                            ");
}
      out.write("\n");
      out.write("                        </tbody>\n");
      out.write("                    </table>\n");
      out.write("                    ");
} else if (vectorBill != null) {
      out.write("\n");
      out.write("\n");
      out.write("                    <p>\n");
      out.write("                    <form class=\"d-flex\" action=\"ControllerOrder\" method=\"post\">\n");
      out.write("                        <input type=\"hidden\" name=\"s\" value=\"searchByID\">\n");
      out.write("                        <input style=\"margin-left: 2%;max-width: 200px\" class=\"form-control me-2\" type=\"search\" placeholder=\"Order ID\" name=\"search\" aria-label=\"Search\">\n");
      out.write("                        <button class=\"btn btn-outline-success\" type=\"submit\">Search ID</button>\n");
      out.write("                    </form>\n");
      out.write("                    </p>\n");
      out.write("                    <p>\n");
      out.write("                    <form class=\"d-flex\" action=\"ControllerOrder\" method=\"post\">\n");
      out.write("                        <input type=\"hidden\" name=\"s\" value=\"searchByName\">\n");
      out.write("                        <input style=\"margin-left: 2%;max-width: 200px\" class=\"form-control me-2\" type=\"search\" placeholder=\"Customer Name\" name=\"search\" aria-label=\"Search\">\n");
      out.write("                        <button class=\"btn btn-outline-success\" type=\"submit\">Search Name</button>\n");
      out.write("                    </form>\n");
      out.write("                    </p>\n");
      out.write("\n");
      out.write("                    <form action=\"ControllerAdmin\" method=\"Post\">\n");
      out.write("                        <input type=\"hidden\" name=\"s\" value=\"searchByOrderStatus\">\n");
      out.write("                        <select name=\"orderStatus\">\n");
      out.write("                            <option value=\"1\">New</option>\n");
      out.write("                            <option value=\"2\">Process</option>\n");
      out.write("                            <option value=\"3\">Done</option>\n");
      out.write("                            <option value=\"4\">Null</option>\n");
      out.write("                        </select>\n");
      out.write("                        <input type=\"submit\" name=\"submit\" value=\"search\">\n");
      out.write("                    </form>\n");
      out.write("                    <p>\n");
      out.write("                        New : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${new}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" ; process : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${process}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" ; done : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${done}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write(" ; null : ");
      out.write((java.lang.String) org.apache.jasper.runtime.PageContextImpl.evaluateExpression("${null1}", java.lang.String.class, (PageContext)_jspx_page_context, null));
      out.write("\n");
      out.write("                    </p>\n");
      out.write("                    <table class=\"table table-striped table-bordered border-dark\">\n");
      out.write("                        <thead>\n");
      out.write("                            <tr>\n");
      out.write("                                <th scope=\"col\">Bill ID</th>\n");
      out.write("                                <th scope=\"col\">Customer Name</th>\n");
      out.write("                                <th scope=\"col\">Date</th>\n");
      out.write("                                <th scope=\"col\">Total</th>\n");
      out.write("                                <th scope=\"col\">Order Status</th>\n");
      out.write("                                <th scope=\"col\">view</th>\n");
      out.write("                                <th scope=\"col\">delete</th>\n");
      out.write("                            </tr>\n");
      out.write("                        </thead>\n");
      out.write("                        <tbody>\n");
      out.write("\n");
      out.write("                            ");
 for (Bill b : vectorBill) {
      out.write("\n");
      out.write("                        <form action=\"ControllerOrder\" method=\"post\">\n");
      out.write("                            <input type=\"hidden\" name=\"s\" value=\"updateStatus\">\n");
      out.write("                            <input type=\"hidden\" name=\"orderID\" value=\"");
      out.print(b.getBillID());
      out.write("\">\n");
      out.write("                            <tr>\n");
      out.write("                                <td scope=\"row\">");
      out.print(b.getBillID());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(b.getCustomerName());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(b.getDate());
      out.write("</td>\n");
      out.write("                                <td>");
      out.print(b.getTotal());
      out.write("</td>\n");
      out.write("                                <!--<td><select name=\"orderStatus\" onchange=\"this.form.submit()\">\n");
      out.write("                                ");
while (rsOrderStatus.next()) {
      out.write("\n");
      out.write("                                <option value=\"");
      out.print(rsOrderStatus.getInt(1));
      out.write('"');
      out.write(' ');
      out.print((rsOrderStatus.getInt(1) == b.getStatus() ? "selected" : ""));
      out.write('>');
      out.print(status[rsOrderStatus.getInt(1) - 1]);
      out.write("</option>\n");
      out.write("                                ");
}
      out.write("\n");
      out.write("                            </select></td> -->\n");
      out.write("                                <td><select name=\"orderStatus\">\n");
      out.write("                                        ");
for (Order o : vector1) {
      out.write("\n");
      out.write("                                        <option value=\"");
      out.print(o.getOrderStatus());
      out.write('"');
      out.write(' ');
      out.print((o.getOrderStatus() == b.getStatus() ? "selected" : ""));
      out.write('>');
      out.write(' ');
      out.print(status[o.getOrderStatus() - 1]);
      out.write("</option>\n");
      out.write("                                        ");
}
      out.write("\n");
      out.write("                                    </select></td>\n");
      out.write("\n");
      out.write("\n");
      out.write("                                <td><button type=\"button\" class=\"btn btn-primary\"><a style=\"text-decoration: none; color: white\" href=\"ControllerOrder?s=orderDetail&orderID=");
      out.print(b.getBillID());
      out.write("\">detail</a></button></td>\n");
      out.write("                                ");
if (b.getStatus() == 4) {
      out.write("\n");
      out.write("                                <td><a href=\"ControllerAdmin?s=deletOrder&id=");
      out.print(b.getBillID());
      out.write("\">delete</a></td>\n");
      out.write("                                ");
} else {
      out.write("\n");
      out.write("                                <td></td>\n");
      out.write("                                ");
}
      out.write("\n");
      out.write("                            </tr>\n");
      out.write("                        </form>\n");
      out.write("                        ");
}
      out.write("\n");
      out.write("\n");
      out.write("                        </tbody>\n");
      out.write("                    </table>\n");
      out.write("                    ");
}
      out.write("\n");
      out.write("                </div>\n");
      out.write("            </div>\n");
      out.write("        </div>\n");
      out.write("        <!-- Footer-->\n");
      out.write("        <footer class=\"py-5 bg-dark\">\n");
      out.write("            <div class=\"container\">\n");
      out.write("                <p class=\"m-0 text-center text-white\">Project Individua PRJ301</p>\n");
      out.write("            </div>\n");
      out.write("        </footer>\n");
      out.write("        <!-- Bootstrap core JS-->\n");
      out.write("        <script src=\"https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js\"></script>\n");
      out.write("        <!-- Core theme JS-->\n");
      out.write("        <script src=\"js/scripts.js\"></script>\n");
      out.write("    </body>\n");
      out.write("\n");
      out.write("</html>");
    } catch (Throwable t) {
      if (!(t instanceof SkipPageException)){
        out = _jspx_out;
        if (out != null && out.getBufferSize() != 0)
          out.clearBuffer();
        if (_jspx_page_context != null) _jspx_page_context.handlePageException(t);
        else throw new ServletException(t);
      }
    } finally {
      _jspxFactory.releasePageContext(_jspx_page_context);
    }
  }
}
