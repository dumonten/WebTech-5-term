<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<style>
main {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
}
    
.auth-form {
	display: flex;
	flex-direction: column;
	justify-content: center;
	align-items: center;
	padding: 10px; 
	margin: 0px 0px 30px 0px; 
	width: 25%;
	box-sizing: border-box;
	border: 1px solid #1976d2;
	border-radius: 4px;
}
</style>

<tags:header_wrap title="authorizationTitle">
    <h1><fmt:message key="authorizationPage"/></h1>
    <form action="Controller" method="post" class="auth-form">
    	<input type="hidden" name="command" value=authorization />
        <label for="login"><fmt:message key="login"/>:</label>
        <input type="text" name="login" id="login" required><br>
        <label for="password"><fmt:message key="password"/>:</label>
        <input type="password" name="password" id="password" required><br>
        <input type="submit" value="<fmt:message key="authorize"/>">
    </form>
    <c:if test="${not empty failedAuthorization}">
        <p style="color: red;">${failedAuthorization}</p>
    </c:if>
	<style>
    body {
    		color: white;
		    font-family: Times New Roman, serif;
		    overflow: hidden;
		    background-color: #141414;
		    padding: 0 20px;
	}
    </style>
</tags:header_wrap>