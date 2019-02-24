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
@WebServlet("/delete")
public class RetriveFile extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public RetriveFile() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try(PrintWriter out = response.getWriter()){
		    //PrintWriter out=response.getWriter();
			
			String message = null;
			Connection con = null;
try{
		  
		  String driverName = "com.mysql.jdbc.Driver";
		  String url = "jdbc:mysql://localhost:3306/";
		  String dbName = "AppDB";
		  String userName = "root";
		  //String check=new String(Base64.decodeBase64(request.getParameter("name")));
		  String check=request.getParameter("name");
		  String option=request.getParameter("option");
		  //System.out.println("hai"+option);
		  int n=check.length(); 
	List<String> ListArr=new ArrayList<String>();
	String temp="",haveit="";
	for(int i=0;i<n-1;i++){
		
		if(check.charAt(i)=='/'){
			//System.out.println("true");
			ListArr.add(temp);
			temp="";
		}else{
			temp+=check.charAt(i);
			haveit+=check.charAt(i);
		}
	}check=haveit;
	
		  //System.out.println("check"+check+haveit);	  
		     String password = "";
		       
		       
		      con = DriverManager.getConnection(url+dbName,userName,password);
		        Statement st =(Statement) con.createStatement();
		        PreparedStatement pre1,pre2;
	if(option.equals("folder"))	{
		pre1 = con.prepareStatement("delete from contacts where root='"+check+"' "); // where size='233195'    where photo<>'folder'
		pre2 = con.prepareStatement("delete from contacts where name='"+temp+"' ");
		pre2.executeUpdate();
	}
	else	pre1 = con.prepareStatement("delete from contacts where root='"+check+"' and name='"+option+"' "); // where size='233195'    where photo<>'folder'
		int row = pre1.executeUpdate();
		//System.out.println("before"+check+" "+option+"deleted");
		if (row > 0) {
			//System.out.println(check+" "+option+"deleted");
			message = "File uploaded and saved into database";
		}
         response.flushBuffer();   pre1.close();
		  }catch (SQLException ex) {
				message = "ERROR: " + ex.getMessage();
				ex.printStackTrace();
			} finally {
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
	}public static byte[] getBytes(InputStream is) throws IOException {

	    int len;
	    int size = 1024;
	    byte[] buf;

	    if (is instanceof ByteArrayInputStream) {
	      size = is.available();
	      buf = new byte[size];
	      len = is.read(buf, 0, size);
	    } else {
	      ByteArrayOutputStream bos = new ByteArrayOutputStream();
	      buf = new byte[size];
	      while ((len = is.read(buf, 0, size)) != -1)
	        bos.write(buf, 0, len);
	      buf = bos.toByteArray();
	    }
	    return buf;
	  }

}
//response.reset();
		 /*if(rs1.next())   
		 {byte[] bytearray1 = new byte[4096];  
		           int size1=0;  
		          InputStream sImage1;  
		            sImage1 = rs1.getBinaryStream(2);  
		              response.reset();
		              
		            String s=rs1.getString(1);
		            System.out.println(s);
		           response.setContentType(s);  
		           response.addHeader("Content-Disposition","filename=logo");  
		           while((size1=sImage1.read(bytearray1))!= -1 )  
		             {  
		               response.getOutputStream().write(bytearray1,0,size1);  
		             }  
		           sImage1.close();
		           //response.flushBuffer();   rs1.close();    pre1.close();
		          
		        }*/
