<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" 
	"http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>File Upload to Database Demo</title>
</head>
<body><style>
.btn {
    background-image: url('add-folder-64.ico');
    width: 64px;
    height: 64px;
    border: 0;
    cursor: pointer;
}</style><script>function check(name){
	var xhr = new XMLHttpRequest();
	var name="master";
	xhr.open("POST","retriv", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	//xhr.overrideMimeType('text/plain; charset=x-user-defined');
	xhr.send("name="+name);
	xhr.onreadystatechange = function() {
		if(this.readyState == 4 ) {
			if ((xhr.status == 200) || (xhr.status == 0)){
                var image = document.getElementById("asdf")
                image.innerHTML =  this.responseText;
            }else{
                alert("Something misconfiguration : " + 
                    "\nError Code : " + xhr.status + 
                    "\nError Message : " + xhr.responseText);
            } 
			
		}
	};
	doevent();
	
}</script>
	<center>
		<h1>File Upload to Database Demo</h1><h1 id="currentfolder">master</h1>
		<form method="post" action="uploadServlet" enctype="multipart/form-data">
			<table border="0">
				<tr>
					<td>First Name: </td>
					<td><input type="text" name="firstName" size="50"/></td>
					<input type="text" name="content" value="master">
				</tr>
				<tr>
					<td>Last Name: </td>
					<td><input type="text" name="lastName" size="50"/></td>
				</tr>
				<tr>
					<td>Portrait Photo: </td><td><input type="text" name="folder"/><input type="submit" class="btn" name="option" value="folder"></td>
					<td><input type="file" name="photo" size="50"/></td>
				</tr>
				<tr><td><input type="submit" name="option" value="view" /></td>
					<td >
						<input type="submit"  name="option" value="Save">
					</td>
				</tr>
			</table>
		</form>
	</center>
</body>
</html>