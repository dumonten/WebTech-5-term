<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<tags:header_wrap title="mainTitle">
	<h1><fmt:message key="mainMessage"/></h1>
	<style>
	body {
    	color: white;
	    flex-direction: column;
	    font-family: Times New Roman, serif;
	    overflow: hidden;
	    background-color: #141414;
	    padding: 0 20px;
	}
    </style>
</tags:header_wrap>