<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="bootstra.css" />
<title>Insert title here</title>
</head>
<body ><script> var currentfolder="master/"; </script><!--<style>
.btn {
    background-image: url('add-folder-64.ico');
    width: 64px;
    height: 64px;
    border: 0;
    cursor: pointer;
}</style>
	<center>
		<h1>File Upload to Database Demo</h1>
		<form method="post" action="uploadServlet" enctype="multipart/form-data">
			<table border="0">
				
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
	</center>-->
<script>
function closeall(){
	document.getElementById("addfile").style.display="none";
	document.getElementById("addfolder").style.display="none";
	return true;
}
function encode64(inputStr){
    var b64 = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789+/=";
    var outputStr = "";
    var i = 0;
    
    while (i> 2){
       var enc2 = ((byte1 & 3) << 4) | (byte2 >> 4);
       
       var enc3, enc4;
       if (isNaN(byte2)){
         enc3 = enc4 = 64;
       } else{
         enc3 = ((byte2 & 15) << 2) | (byte3 >> 6);
         if (isNaN(byte3)){
            enc4 = 64;
         } else {
             enc4 = byte3 & 63;
         }
       }
       outputStr +=  b64.charAt(enc1) + b64.charAt(enc2) + b64.charAt(enc3) + b64.charAt(enc4);
    } 
    return outputStr;
 } 
 function doevent(){
	 var sample=document.getElementById("jagan");
	 var files=document.getElementsByClassName("file");
	 var folders=document.getElementsByClassName("folder");
	 var image = document.getElementById("test");//.innerHTML="documents-64.ico";
	 image.innerHTML=" "+files.length+" "+folders.length;
	 for(var i = 0; i < files.length; i++) {
			files[i].addEventListener("click", function() {
				
				check(this.getAttribute("id"));
			});
		}
	 for(var i = 0; i < folders.length; i++) {
		 
		 image.innerHTML="hai";
			folders[i].addEventListener("click", function() {
				image.innerHTML="abcd";
				//check('Screenshot (8).png');
			});
		}
 }
 function check(name,option){
		
		var xhr = new XMLHttpRequest();
		currentfolder=name;	
		//var name="master";
		xhr.open("POST","retriv", true);
		xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
		//xhr.overrideMimeType('text/plain; charset=x-user-defined');
		xhr.send("name="+name+"&option="+option);
		xhr.onreadystatechange = function() {
			if(this.readyState == 4 ) {
				if ((xhr.status == 200) || (xhr.status == 0)){
	                var image = document.getElementById("master")
	                image.innerHTML =  this.responseText;
	            }else{
	                alert("Something misconfiguration : " + 
	                    "\nError Code : " + xhr.status + 
	                    "\nError Message : " + xhr.responseText);
	            } 
				
			}
		};
		//location.reload(true);
		closeall();
		return false;
		
	}
function del(name,option){
	if(confirm("Want to delete?")) {
	var xhr = new XMLHttpRequest();
	currentfolder=name;	
	//var name="master";
	xhr.open("POST","delete", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	//xhr.overrideMimeType('text/plain; charset=x-user-defined');
	xhr.send("name="+name+"&option="+option);
	//location.reload(true);
	//closeall();
	alert("successfully deleted");
	check('master/','folder');
	}
	
}
function uploadfolder(){
	changevalue();
	var photo=document.getElementById("folderphoto").value;
	var content=document.getElementById("newfldrval").value;
	var folder=document.getElementById("foldername").value;
	var xhr = new XMLHttpRequest();
	currentfolder=name;	
	//var name="master";
	xhr.open("POST","uploadServlet", true);
	xhr.setRequestHeader("Content-type", "application/x-www-form-urlencoded");
	//xhr.overrideMimeType('text/plain; charset=x-user-defined');
	xhr.send("option="+folder+"&content="+content+"&photo="+photo);
	/*xhr.onreadystatechange = function() {
		if(this.readyState == 4 ) {
			if ((xhr.status == 200) || (xhr.status == 0)){
                var image = document.getElementById("master")
                image.innerHTML =  this.responseText;
            }else{
                alert("Something misconfiguration : " + 
                    "\nError Code : " + xhr.status + 
                    "\nError Message : " + xhr.responseText);
            } 
			
		}
	};
	//location.reload(true);
	closeall();
	*/return false;
	
}
function addfolder(){
	document.getElementById("addfile").style.display="none";
	document.getElementById("addfolder").style.display="block";
	document.getElementById("displayfolder").innerHTML=currentfolder;
}function addfile(){
	document.getElementById("addfolder").style.display="none";
	document.getElementById("addfile").style.display="block";
}
function changevalue(){
	document.getElementById("newfldrval").value=currentfolder;
	document.getElementById("newfileval").value=currentfolder;
	document.getElementById("displayfolder").innerHTML=currentfolder;
}
window.addEventListener("load",check('master/','folder'));
</script>
<table border="1">
  <!-- <tr><th>DISPLAYING IMAGE</th> </tr> -->
  <tr><td><div ><center>
		<h1>Github</h1><span id="displayfolder">master</span>
		<form method="post" action="uploadServlet" onsubmit="changevalue()" enctype="multipart/form-data">
			<div id="addfolder" style="display:none;">
				<table border="1">
				<tr>
					<th colspan="2">Enter Folder Details<th>
				</tr>
				<tr>
				    <td style="display:none"><input id="folderphoto" type="file" name="photo" size="50"  /></td>
				    <td style="display:none;"><input type="text" id="newfldrval"  name="content"  /></td>
					<td><input id="foldername" type="text" name="folder" /><input type="submit"  name="option" value="Save Folder"></td>
						 
				</tr>
				</table>
			</div></form>
		<form method="post"  action="uploadServlet" onsubmit="changevalue()" enctype="multipart/form-data">
			<div id="addfile" style="display:none;">
				<table border="1">
					<tr>
						<td><input id="filephoto" type="file" name="photo" size="50"/></td>
						<td style="display:none;"><input type="text" id="newfileval"  name="content"  /></td>
						<td><input id="filename" type="submit"  name="option" value="Save">
						</td>
					</tr>
				</table>
			</div>
		</form>
	</center></div >
	
	</td><td><div id="master"></div></td></tr>
  <!--  <tr><td><p id="message">hi</p></td></tr>-->
  <tr><td>
  <!--  <textarea id="abc" ></textarea>-->
  <figure style="float:left;"><img src="add-folder-64.ico" onclick="addfolder()" /><figcaption>Add Folder</figcaption></figure>
  <figure style="float:left;"><img src="add-file-64.ico" onclick="addfile()" /><figcaption></figcaption>Add File</figure></td>
  <!-- <td>
  <input type="button" onclick="check('master/','folder')" value="click me"/>
</td> -->
<!-- <img class ="folder" id="master" src="documents-64.ico" /><textarea id="readme" class="file"></textarea>-->
 </tr>
 </table>
</body>
</html>