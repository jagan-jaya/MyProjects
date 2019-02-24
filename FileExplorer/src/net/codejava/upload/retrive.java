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
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.Statement;

/**
 * Servlet implementation class retrive
 */
@WebServlet("/retriv")
public class retrive extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public retrive() {
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
try{
		  out.println("Retrieve Image Example!");
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
	
		 // System.out.println("check"+check+haveit);	  
		     String password = "";
		       Connection con = null;
		       Class.forName(driverName);
		      con = DriverManager.getConnection(url+dbName,userName,password);
		        Statement st =(Statement) con.createStatement();
		        PreparedStatement pre1;
	if(option.equals("folder"))	    pre1 = con.prepareStatement("select name,doctype from contacts where root='"+check+"' "); // where size='233195'    where photo<>'folder'
	else	pre1 = con.prepareStatement("select doctype,photo from contacts where root='"+check+"' and name='"+option+"' "); // where size='233195'    where photo<>'folder'
		ResultSet rs1=pre1.executeQuery();
		
		 response.reset();
		 response.setContentType("text/html");  
         response.addHeader("Content-Disposition","filename=logo");
         n=ListArr.size();
         temp="";haveit="";
         out.println("<h1>");
         for(int i=0;i<n;i++){
        	 temp=ListArr.get(i);
        	 if(temp=="") break;
        	 haveit+=temp;
             haveit+="/";
        	 System.out.print(temp+" "+haveit+" "+i);
        	 out.print("<span onclick=\"check('"+haveit+"','folder')\">"+temp+'/'+"</span>");
         }
   
         if(option.equals("folder")){
        	 String print="<h2>No files found</h2> ";
        	 out.println("</h1>");
        	 boolean flag=true;
		 while(rs1.next()){		if(flag){ print=""; flag=false;	}
			 String folder=rs1.getString(1);
			 String s=rs1.getString(2);
			 
              if(s.equals("folder"))	print+="<figure style=\"float:left;\"><img class=\"folder\" onclick=\"check('"+haveit+folder+'/'+"','folder')\" src=\"folder.ico\"/><figcaption>"+folder+"	    <img class=\"file\" onclick=\"del('"+haveit+folder+'/'+"','folder')\" src=\"delete-16.ico\"/></figcaption></figure>";
             else if(s.contains("image")){
            	 print+="<figure style=\"float:left;\"><img class=\"file\" onclick=\"check('"+haveit+'/'+"','"+folder+"')\" src=\"jpg-64.ico\"/><figcaption>"+folder+"<img class=\"file\" onclick=\"del('"+haveit+'/'+"','"+folder+"')\" src=\"delete-16.ico\"/></figcaption></figure>";  
            	 }    //out.println(b64);
		 else 
        	 print+="<figure style=\"float:left;\"><img class=\"file\" onclick=\"check('"+haveit+'/'+"','"+folder+"')\" src=\"file.ico\"/><figcaption>"+folder+"<img class=\"file\" onclick=\"del('"+haveit+'/'+"','"+folder+"')\" src=\"delete-16.ico\"/></figcaption></figure>";  
              //out.println("<p>h<br></p>");
              }    out.println(print);
	 }
         else{
        	 if(rs1.next()){
        	 InputStream in = rs1.getBinaryStream(2);
             byte arr[] = getBytes(in);
             String b64 = Base64.encodeBase64String(arr);
             String s=rs1.getString(1);
             out.println(option);
             out.println("</h1>");
             if(s.contains("image")){
            	 out.println("<figure style=\"float:left;\"><img style=\"float:left;\" class=\"file\" onclick=\"check('"+haveit+"','folder')\" src=\"close-window-32.ico\"/><img class=\"file\"   src=\"data:image/jpg;base64,"+b64+"\"/></figcaption>"); //style=\"width:100px; height:100px;\"
            	              }
             else if(s.contains("audio")){
            	 out.println("<audio controls><source class=\"file\"   src=\"data:audio/mpeg;base64,"+b64+"\"></audio><span id=\""+haveit+"\">"+haveit+"</span>"); //style=\"width:100px; height:100px;\"
             }
             else{
            	 String ss=new String(Base64.decodeBase64(b64));
            	 out.println("<figure style=\"float:left;\"><img style=\"float:left;\" class=\"file\" onclick=\"check('"+haveit+"','folder')\" src=\"close-window-32.ico\"/><form action=\"UpdateServlet\"><input type=\"text\" style=\"display:none;\" name=\"check\" value='"+check+"'><input type=\"text\" style=\"display:none;\" name=\"option\" value='"+option+"'><textarea name=\"context\" cols=\"50\" rows=\"30\">"+ss+"</textarea><button type=\"submit\"class=\"btn btn-success\" >Edit And Save</button></form></figcaption>");
            	 
           
             }
            
         }
         }
         response.flushBuffer(); rs1.close();  pre1.close();
		  con.close();
		  }
		 catch (Exception e){
		         out.println(e.getMessage());
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
		          InputStream sImage1+;  
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