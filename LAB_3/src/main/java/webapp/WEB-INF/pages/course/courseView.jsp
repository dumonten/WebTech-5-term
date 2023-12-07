<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link rel="stylesheet" href="${pageContext.request.contextPath}/css/courseView.css" />
<fmt:setLocale value="${sessionScope.lang}"/>
<fmt:setBundle basename="messages"/>
<!DOCTYPE html>
<html>
<head>
    <title><fmt:message key="course_description_label" /></title>
</head>
<body>
    <h1><fmt:message key="course_description_label" /></h1>
    <table>
        <tr>
            <th><fmt:message key="course_name" /></th>
            <td>${course.name}</td>
        </tr>
        <tr>
            <th><fmt:message key="price" /></th>
            <td>${course.price}</td>
        </tr>
        <tr>
            <th><fmt:message key="author" /></th>
            <td>${course.author}</td>
        </tr>
        <tr>
            <th><fmt:message key="description" /></th>
            <td>${course.description}</td>
        </tr>
        <tr>
            <th><fmt:message key="main_tech" /></th>
            <td>${course.mainTech}</td>
        </tr>
    </table>
</body>
</html>