<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ tag isELIgnored="false" %>
<%@ attribute name="title" required="true" %>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<!DOCTYPE html>
<html>
	<head>
		<meta charset="utf-8"/>
	    <title><fmt:message key="${title}"/></title>
	    <style>
       
		header {
		    display: flex;
		    justify-content: flex-start;
		    align-items: center;
		    flex-wrap: wrap;
		}
		
		h1 {
	    	text-align: center;
	    	box-sizing: border-box;
	    	padding: 20px;
	    	margin: 0;
		}
		
		ul {	
            list-style-type: none;
            padding: 0;
            margin: 0;
        }

        li {
        	display: inline-block;
            margin: 10px;
        }
		
		a {
	  	  	padding: 10px 20px;
		    font-size: 16px;
		    background-color: #363636;
		    color: #a4afb8;
		    border: none;
		    cursor: pointer;
		    text-decoration: none; 
		}
		
		a:hover {
		    background-color: #a4afb8;
		    color: #363636;
		}
		
		p {
            font-size: 15px;
        }
		
		input[type="text"], input[type="password"] {
		    font-size: 16px;
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
		
		main {
			box-sizing: border-box;
	    	border: 1px solid white;
	    	background-color: #1c1c1c;
		}
    </style>
	</head>
	<body>
		<header>
	        <ul>
	        	<li><a href="?changeLocale=True"><fmt:message key="changeLocale"/></a></li>
				<li>
					<form action="Controller" method="post">
					    <input type="hidden" name="command" value="to_main_page" />
					    <input type="submit" value="<fmt:message key="mainTitle"/>">
					</form>
				</li>
			</ul>
			<c:choose>
				<c:when test="${empty sessionScope.user}">
					<ul>
					    <li>
					    	<form action="Controller" method="post">
							    <input type="hidden" name="command" value="to_registration_page" />
							    <input type="submit" value="<fmt:message key="registrationTitle"/>">
							</form>
					    </li>
				        <li>
				        	<form action="Controller" method="post">
							    <input type="hidden" name="command" value="to_authorization_page" />
							    <input type="submit" value="<fmt:message key="authorizationTitle"/>">
							</form>
				        </li>
				    </ul>		    
				</c:when>
				<c:otherwise>
					<ul>
				        <li>
				        	<form action="Controller" method="post">
							    <input type="hidden" name="command" value="logout" />
							    <input type="submit" value="<fmt:message key="logoutTitle"/>">
							</form>
				        </li>
				    </ul>	
				</c:otherwise>
			</c:choose>
		</header>
		<main>
		    <jsp:doBody/>
		</main>
	</body>
</html>