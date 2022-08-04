package org.apache.jsp;

import javax.servlet.*;
import javax.servlet.http.*;
import javax.servlet.jsp.*;

public final class login_jsp extends org.apache.jasper.runtime.HttpJspBase
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
      out.write("<!DOCTYPE html>\n");
      out.write("<html lang=\"en\">\n");
      out.write("\n");
      out.write("    <head>\n");
      out.write("        <meta charset=\"UTF-8\">\n");
      out.write("        <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\n");
      out.write("        <meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/reset.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"css/app.css\">\n");
      out.write("        <link rel=\"stylesheet\" href=\"https://pro.fontawesome.com/releases/v5.10.0/css/all.css\">\n");
      out.write("        <title>Form login</title>\n");
      out.write("    </head>\n");
      out.write("\n");
      out.write("    <body>\n");
      out.write("        ");

            String error = (String)request.getAttribute("error");
        
      out.write("\n");
      out.write("        <div id=\"wrapper\">\n");
      out.write("            <form action=\"ControllerLogin\" id=\"form-login\" method=\"POST\">\n");
      out.write("                <h1 class=\"form-heading\">Form Login Shop</h1>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <i class=\"far fa-user\"></i>\n");
      out.write("                    <input type=\"text\" class=\"form-input\" name=\"userName\" placeholder=\"Tên đăng nhập\">\n");
      out.write("                </div>\n");
      out.write("                <div class=\"form-group\">\n");
      out.write("                    <i class=\"fas fa-key\"></i>\n");
      out.write("                    <input type=\"password\" class=\"form-input\" name=\"passWord\" placeholder=\"Mật khẩu\">\n");
      out.write("                    <div id=\"eye\">\n");
      out.write("                        <i class=\"far fa-eye\"></i>\n");
      out.write("                    </div>\n");
      out.write("                </div>\n");
      out.write("                <input type=\"submit\" value=\"login\" class=\"form-submit\">\n");
      out.write("                <p style=\"color: white\">You don't have account ? <a href=\"ControllerCustomer?s=register\">Create Account</a></p>\n");
      out.write("                <p><a style=\"color: blue\" href=\"ChangePassword.jsp\">Change Password</a></p>\n");
      out.write("                ");
if (error != null) {
      out.write("\n");
      out.write("                <p style=\"color: red\">");
      out.print(error);
      out.write("</p>\n");
      out.write("                ");
}
      out.write("\n");
      out.write("            </form>\n");
      out.write("\n");
      out.write("        </div>\n");
      out.write("\n");
      out.write("    </body>\n");
      out.write("    <script src=\"https://code.jquery.com/jquery-3.6.0.js\"></script>\n");
      out.write("    <script src=\"js/app.js\"></script>\n");
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
