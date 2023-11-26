<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<style>
table {
    color: #83a7b1;
    width: 100%;
    border-collapse: collapse;
}

thead {
    background-color: #223443;
}

th {
    padding: 10px;
    text-align: left;
    font-weight: bold;
}

td {
    padding: 10px;
}

tbody td:first-child {
    text-align: center;
}

.buttons {
  display: flex;
  flex-direction: row;
  justify-content: flex-end;
  margin: 10px 20px;
}

.buttons form:not(:last-child) {
  margin-right: 10px;
}

.tableFrame {
	padding: 0px 0px 30px 0px; 
	border: 1px solid #1976d2;
	border-radius: 4px;
}

.addChangeFrame {
    display: flex;
  	flex-direction: column; 
  	justify-content: center;
    align-items: flex-start;
    border: 1px solid #1976d2;
	border-radius: 4px;
    width: 100%;
}

.addChangeFrame form {
    margin: 10px;
}

.addChangeFrame label {
    display: block;
}

.addChangeFrame input,
.addChangeFrame textarea {
    margin-bottom: 10px;
}
</style>

<tags:header_wrap title="userTitle">
	<div class="buttons">
	    <form action="Controller" method="post">
			<input type="hidden" name="command" value="to_cart_page" />
			<input type="submit" value="<fmt:message key="toCartPage"/>">
		</form>
		<form action="Controller" method="post">
			<input type="hidden" name="command" value="to_favorites_page" />
			<input type="submit" value="<fmt:message key="toFavoritesPage"/>">
		</form>
	</div>
	<h1><fmt:message key="filmList"/></h1>
    <div class="tableFrame">
	    <table>
	    <thead>
	        <tr>
	            <th ><fmt:message key="filmTitle"/></th>
	            <th ><fmt:message key="filmGenre"/></th>
	            <th ><fmt:message key="filmDuration"/></th>
	            <th ><fmt:message key="filmPrice"/></th>
	            <th ><fmt:message key="filmReleaseYear"/></th>
	            <th ><fmt:message key="filmRating"/></th>
	            <th ></th>
	        </tr>
	    </thead>
	    <tbody>
	    <c:forEach items="${filmList}" var="film">
	        <tr>
	            <td >${film.getTitle()}</td>
	            <td >${film.getGenre()}</td>
	            <td >${film.getDuration()}</td>
	            <td >$ ${film.getPrice()}</td>
	            <td >${film.getReleaseYear()}</td>
	            <td >${film.getRating()}</td>
	            <td >
	                <form action="Controller" method="post">
	                    <input type="hidden" name="command" value="to_film_page" />
	                    <input type="hidden" name="idFilm" value="${film.getId()}" />
	                    <input type="submit" value="<fmt:message key="details"/>">
	                </form>
	            </td>
	        </tr>
	    </c:forEach>
	    </tbody>
		</table>
	</div>
    
    <div class="buttons">
	    <c:if test="${not (page eq 1)}">
	    	<form action="Controller" method="post">
				<input type="hidden" name="command" value="to_user_page" />
				<input type="hidden" name="page" value="${page - 1}" />
				<input type="submit" value="<fmt:message key="toPreviousPage"/>">
			</form>
	    </c:if>
	    <c:if test="${not ((page * numberOfFilmsPerPage) ge filmAmount)}">
	    	<form action="Controller" method="post">
				<input type="hidden" name="command" value="to_user_page" />
				<input type="hidden" name="page" value="${page + 1}" />
				<input type="submit" value="<fmt:message key="toNextPage"/>">
			</form>
	    </c:if>
	    <br/>
	    <c:if test="${sessionScope.user.isAdmin() eq true}">
		    <form action="Controller" method="post">
				<input type="hidden" name="command" value="to_admin_page" />
				<input type="hidden" name="page" value="1" />
				<input type="hidden" name="pageUser" value="1" />
				<input type="submit" value="<fmt:message key="toAdminPage"/>">
			</form>
	    </c:if>
    </div>

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