package net.codejava.upload;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import com.mysql.jdbc.Blob;
import com.mysql.jdbc.Statement;

@WebServlet("/uploadServlet")
@MultipartConfig(maxFileSize = 16177215)	// upload file's size up to 16MB
public class FileUploadDBServlet extends HttpServlet {
	
	// database connection settings
	private String dbURL = "jdbc:mysql://localhost:3306/AppDB";
	private String dbUser = "root";
	private String dbPass = "";
	
	protected void doPost(HttpServletRequest request,
			HttpServletResponse response) throws ServletException, IOException {
		// gets values of text fields
		
		String option=request.getParameter("option");
		String root=request.getParameter("content");
		String type=request.getParameter("photo");
		int n=root.length(); 
		List<String> ListArr=new ArrayList<String>();
		String temp="",haveit="";
		for(int i=0;i<n;i++){
			
			if(root.charAt(i)=='/'){
				ListArr.add(temp);
				temp="";
			}
			else{
				temp+=root.charAt(i);
				haveit+=root.charAt(i);
			}
		}root=haveit;
		//System.out.println("root"+haveit);
		//System.out.println(option);
		if(option.equals("view")){
			retrieveFile_fromDB();
		}
		else{
			InputStream inputStream = null;	// input stream of the upload file
		
		String name=null,size=null,doctype=null,folder=request.getParameter("folder");
		// obtains the upload file part in this multipart request
		Part filePart = request.getPart("photo");
		if (filePart != null) {
			// prints out some information for debugging
			//System.out.println(filePart.getName());
			//System.out.println(filePart.getSize());
			//System.out.println(filePart.getContentType());
			Collection<String> sss=filePart.getHeaders("content-disposition");
			String a="";
			for(String s: sss)	a+=s;
			//System.out.println(a);
			String nameoffile="";
			for(int i=35;i<a.length()-1;i++) nameoffile+=a.charAt(i);
	            //System.out.println(nameoffile);                  
	        name=nameoffile;
	        //name = filePart.getName();
			 size = Long.toString(filePart.getSize());
			 doctype = filePart.getContentType();
			 
			// obtains input stream of the upload file
			inputStream = filePart.getInputStream();
		}
		
		Connection conn = null;	// connection to the database
		String message = null;	// message will be sent back to client
		
		try {
			// connects to the database
			//System.out.println("dsfds");
			DriverManager.registerDriver(new com.mysql.jdbc.Driver());
			conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
			// constructs SQL statement
			/*boolean flag=false;
			if(option.equals("folder")){
				message=option+folder;
				String sql = "select name from contacts where doctype='folder' and name='"+folder+"' ";
				PreparedStatement statement = conn.prepareStatement(sql);
				ResultSet rs = statement.executeQuery();
				if (rs.next()) {
					System.out.println("hi");
					message = "same folder already exists";
				}
			}*/
				//if(flag){
				//System.out.println("hai"+root);
			String sql = "INSERT INTO contacts (name, size,doctype, photo,root) values (?, ?, ?,?,?)";
			PreparedStatement statement = conn.prepareStatement(sql);
			if(option.equals("Save Folder")) statement.setString(1, folder) ; else statement.setString(1, name);
			statement.setString(2, size);
			statement.setString(5, root);
			if(option.equals("Save Folder")) statement.setString(3, "folder"); else statement.setString(3, doctype);
			if (inputStream != null) {
				// fetches input stream of the upload file for the blob column
				statement.setBlob(4, inputStream);
				//System.out.println("y this is happening");
			}
			
			// sends the statement to the database server
			int row = statement.executeUpdate();
			if (row > 0) {
				message = "File uploaded and saved into database";
			}
				//}
		
		} catch (SQLException ex) {
			message = "ERROR: " + ex.getMessage();
			ex.printStackTrace();
		} finally {
			if (conn != null) {
				// closes the database connection
				try {
					conn.close();
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
	}public void retrieveFile_fromDB()
	{
			
		Connection conn=null;
	     try {
	    	 DriverManager.registerDriver(new com.mysql.jdbc.Driver());
				conn = DriverManager.getConnection(dbURL, dbUser, dbPass);
	         Statement stmt = (Statement) conn.createStatement();
	         ResultSet res = stmt.executeQuery("SELECT doctype,photo FROM contacts ");
	           
	         int i=0;
	         while (res.next()) {
	        	 /*FileOutputStream fos = new FileOutputStream("C:\\Users\\GOKUL\\Desktop\\file.png");
	        	 byte [] bs=res.getBytes("photo");
	        	    fos.write(bs);
	        	    //System.out.println(fos);
	             fos.flush();*/
	        	 	String type=res.getString(1);
	 				InputStream in = res.getBinaryStream(2);
					OutputStream f = new FileOutputStream(new File("C:\\Users\\GOKUL\\Desktop\\Photos\\test\\file"+i+".jpg"));
					i++;
					int c = 0;
					while ((c = in.read()) > -1) {
						f.write(c);
					}
					f.close();
					in.close();

	         }
	     } catch (IOException e) {
	         e.getMessage (); e.printStackTrace(); 
	         System.out.println(e); 
	     } catch (SQLException e) {
	         e.getMessage (); e.printStackTrace(); 
	         System.out.println(e); 
	     }
	}
}