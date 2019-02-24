package net.codejava.upload;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;

import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.codec.binary.Base64;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class retrive
 */
@WebServlet("/UpdateServlet")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	Connection con = null;
	String message =null;
    /**
     * @see HttpServlet#HttpServlet()
     */private String dbURL = "jdbc:mysql://localhost:3306/AppDB";
 	private String dbUser = "root";
 	private String dbPass = "";
    public UpdateServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub

		//String option=request.getParameter("context");
		//String root=request.getParameter("check");
		//String type=request.getParameter("option");
		//System.out.println("hai"+root);
		try
	    {
	     String context = request.getParameter("context") ;
	     String check=request.getParameter("check");
		 String option=request.getParameter("option");
	     PrintWriter writer =  response.getWriter();
	     String driverName = "com.mysql.jdbc.Driver";
		  String url = "jdbc:mysql://localhost:3306/";
		  String dbName = "AppDB";
		  String userName = "root";
		  //String check=new String(Base64.decodeBase64(request.getParameter("name")));
	//	System.out.println("hai"+check);
		  int n=check.length(); 
	List<String> ListArr=new ArrayList<String>();
	String temp="",haveit="";
	for(int i=0;i<n;i++){
		
		if(check.charAt(i)=='/'){
			//System.out.println("true");
			ListArr.add(temp);
			temp="";
		}else{
			temp+=check.charAt(i);
			haveit+=check.charAt(i);
		}
	}check=haveit;
	InputStream is = new ByteArrayInputStream(context.getBytes("UTF-8"));
		 // System.out.println("check"+check+haveit);	  
		     String password = "";
		       
		 	DriverManager.registerDriver(new com.mysql.jdbc.Driver());
		      con = DriverManager.getConnection(url+dbName,userName,password);
		        //writer.println(context);
		    	String sql = "update contacts set photo=? where root=? and name=? ";
	//	pre1 = con.prepareStatement("update contacts set photo=? where root='"+check+"' and name='"+option+"' "); // where size='233195'    where photo<>'folder'
		    	PreparedStatement pre1=(com.mysql.jdbc.PreparedStatement)con.prepareStatement(sql);
		    	if (is != null) {
			// fetches input stream of the upload file for the blob column
			pre1.setBlob(1, is);
			pre1.setString(2, check);
			pre1.setString(3, option);
		}
		//pre1.setString(1,"fsdngx");
			int row=pre1.executeUpdate();
		if (row > 0) {
			//message = "File uploaded and saved into database";
		}
		// response.flushBuffer();  // pre1.close();
	    // writer.close();
	    }
		 catch (SQLException ex) {
				message = "ERROR: " + ex.getMessage();
				ex.printStackTrace();
			}finally {
				if (con != null) {
					// closes the database connection
					try {
						con.close();
					} catch (SQLException ex) {
						ex.printStackTrace();
					}
				}
				// sets the message in request scope
				request.setAttribute("Message", message);
				
				// forwards to the message page
				getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
			}
	}
}

















/*

//
	  System.out.println("check"+check+haveit);	  
	     
	  response.setContentType("text/html");  
      response.addHeader("Content-Disposition","filename=logo");
  
pre1 = con.prepareStatement("select name from contacts where root='"+check+"' and name='"+option+"' "); // where size='233195'    where photo<>'folder'
	ResultSet rs1=pre1.executeQuery();
	out.println(rs1.getString(1));
		response.flushBuffer(); rs1.close();  pre1.close();
		  con.close();
	out.println("hai");
		  }
		 catch (Exception e){
		         out.println(e.getMessage());
		        }
	}

}
}
*/