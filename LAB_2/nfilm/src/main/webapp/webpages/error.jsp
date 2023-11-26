<%@ page isErrorPage="true" language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html>
	<head>
	    <title><fmt:message key="error"/></title>
	    <style>	
	        h1 {
	            color: red;
	        }
	        
	        p {
	            font-size: 18px;
	            margin-bottom: 10px;
	            color: red;
	        }
	        
	        main {
	          display: flex;
			  flex-direction: row;
			  justify-content: flex-start;
			  margin: 10px 20px;
			  align-items: center;
			}
	
			input[type="submit"] {
				padding: 10px 20px;
	            font-size: 16px;
	            background-color: #363636;
	            color: #a4afb8;
	            font-family: Times New Roman, serif;
	            border: none;
	            cursor: pointer;
	        }
	          
	        input[type="submit"]:hover {
			    background-color: #a4afb8;
			    color: #363636;
			}
			
			body {
	    		color: white;
			    font-family: Times New Roman, serif;
			    overflow: hidden;
			    background-color: #141414;
			    padding: 0 20px;
	        }
	    </style>
	</head>
	<body>
        <h1><fmt:message key="errorOccured"/></h1>
        <p>${errorMessage}</p>
        <form action="Controller" method="post">
			<input type="hidden" name="command" value="to_main_page" />
			<input type="submit" value="<fmt:message key="toMainPage"/>">
		</form>
	</body>
</html>