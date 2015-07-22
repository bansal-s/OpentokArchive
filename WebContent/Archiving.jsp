<%@ page import="com.opentok.OpenTok"%>
<%@ page import="com.opentok.SessionProperties"%>
<%@ page import="com.opentok.MediaMode"%>
<%@ page import="com.opentok.Session"%>
<%@ page import="com.opentok.TokenOptions"%>
<%@ page import="com.opentok.Role"%>
<%@ page import="com.opentok.Archive"%>

<%-- 
<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%> --%>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Archiving</title>
<script src="//static.opentok.com/v2/js/opentok.min.js"></script>

<script src="<%=request.getContextPath()%>/jquery-1.11.3.min.js"></script>

</head>
<body background="callrooms.jpg"></body>
<body>
	<%!
 String sessionID = "";
 String apikey = "";
 String apiSecret = "";
 String token = "";
 String session = "";
 String publisher="";
 
 %>
	<script type="text/javascript">

 function publish(apiKey,sessionId,token,apiSecret,actionName){


if(actionName == 'Publish')	 {
 session = OT.initSession(apiKey,sessionId);
 session.connect(token, function(error) {
   publisher = OT.initPublisher('camera');
   session.publish(publisher);
 });
   }else if(actionName == 'Stoppub')	 {
	   
	     session.unpublish(publisher);

	     }
   else if(actionName =='Archive'){

 $.ajax({
	    url: 'Archive?apikey='+apiKey+'&sessionID='+sessionId+'&apiSecret='+apiSecret,
	    type: 'GET',
	    contentType: 'text/plain',
	    success: function(archiveIds) {
		    document.getElementById("archiveId").value=archiveIds;
		   
	    }
	});
      }else if(actionName =='Stop'){

	var archiveId = document.getElementById("archiveId").value;

	
	 $.ajax({
		    url: 'Stop?archiveId='+archiveId+'&apikey='+apiKey+'&apiSecret='+apiSecret,
		    type: 'GET',
		    contentType: 'text/plain',
		    success: function(data) {
			  
		    }
		});
	}
 }

 </script>

	<% 

if(request.getAttribute("apikey")!=null)
apikey = request.getAttribute("apikey").toString();

if(request.getAttribute("sessionID")!=null)
sessionID =request.getAttribute("sessionID").toString();

if(request.getAttribute("token")!=null)
	token =request.getAttribute("token").toString();

if(request.getAttribute("apiSecret")!=null)
	apiSecret =request.getAttribute("apiSecret").toString();

%>


	<form action="Archive" name="myForm" id="myForm">
		<input type="hidden" name="apikey" id="apikey" value="<%=apikey%>" />
		<input type="hidden" name="apiSecret" id="apiSecret"
			value="<%=apiSecret%>" /> <input type="hidden" name="sessionID"
			id="sessionID" value="<%=sessionID%>" /> <input type="hidden"
			name="archiveId" id="archiveId" value="" />


		<button type="button" name="Archive" id="archive" 
			onclick="this.disabled=true;document.getElementById('pub').disabled=true;document.getElementById('stoppub').disabled=true;document.getElementById('stoparchive').disabled=false;publish('<%=apikey%>','<%=sessionID%>','<%=token%>','<%=apiSecret%>','Archive')"disabled>Start
			Archive</button>
		<button type="button" name="Publish" id="pub" 
			onclick="this.disabled=true;document.getElementById('archive').disabled=false;document.getElementById('stoppub').disabled=false;document.getElementById('stoparchive').disabled=true;publish('<%=apikey%>','<%=sessionID%>','<%=token%>','<%=apiSecret%>','Publish')">Start
			Publish</button>
		<button type="button" name="Stop" id="stoparchive" 
			onclick="this.disabled=true;document.getElementById('archive').disabled=false;document.getElementById('stoppub').disabled=false;document.getElementById('pub').disabled=true;publish('<%=apikey%>','<%=sessionID%>','<%=token%>','<%=apiSecret%>','Stop')"disabled>Stop
			Archive</button>
		<button type="button" name="Stoppub" id="stoppub"
			onclick="this.disabled=true;document.getElementById('pub').disabled=false;document.getElementById('archive').disabled=true;document.getElementById('stoparchive').disabled=true;publish('<%=apikey%>','<%=sessionID%>','<%=token%>','<%=apiSecret%>','Stoppub')"disabled>Stop
			publish</button>

		<div id="camera"></div>
	</form>
</body>
</html>