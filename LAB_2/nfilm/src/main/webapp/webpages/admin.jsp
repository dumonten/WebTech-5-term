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

<tags:header_wrap title="adminTitle">
        <h1><fmt:message key="adminPage"/></h1>
        
        <div class="tableFrame">
        	<h2><fmt:message key="filmList"/></h2>
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
			    <c:forEach items="${filmList}" varStatus="status" var="film">
			        <tr>
						<td>${film.getTitle()}</td>
			            <td>${film.getGenre()}</td>
			            <td>${film.getDuration()}</td>
			            <td>$ ${film.getPrice()}</td>
			            <td>${film.getReleaseYear()}</td>
			            <td>${film.getRating()}</td>
			            <td>
			                <form action="Controller" method="post">
			                    <input type="hidden" name="command" value="delete_film" />
			                    <input type="hidden" name="idFilm" value="${film.getId()}" />
			                    <input type="hidden" name="page" value="${page}" />
			                    <input type="hidden" name="pageUser" value="${pageUser}" />
			                    <input type="submit" value="<fmt:message key="delete"/>">
			                </form>
			            </td>
			            <td>
			                <form action="Controller" method="post">
			                    <input type="hidden" name="command" value="show_edit_film_form" />
			                    <input type="hidden" name="idFilm" value="${status.index}" />
			                    <input type="hidden" name="page" value="${page}" />
			                    <input type="hidden" name="pageUser" value="${pageUser}" />
			                    <input type="submit" value="<fmt:message key="edit"/>">
			                </form>
			            </td>
			        </tr>
			    </c:forEach>
			    </tbody>
			</table>
		</div>

		<c:if test="${not empty editIdFilm}">
			<div class="addChangeFrame">
				<h2><fmt:message key="editFilm"/></h2>
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="edit_film">
					<input type="hidden" name="idFilm" value="${filmList[editIdFilm].getId()}" />
					<input type="hidden" name="page" value="${page}" />
					<input type="hidden" name="pageUser" value="${pageUser}" />
		            
		            <label for="title"><fmt:message key="filmTitle"/>:</label>
	            	<input type="text" name="title" value="${filmList[editIdFilm].getTitle()}" /><br/>
	               	
	               	<label for="description"><fmt:message key="filmDescription"/>:</label>
	                <textarea id="description" name="description">${filmList[editIdFilm].getDescription()}</textarea><br/>
	                
	                <label for="genre"><fmt:message key="filmGenre"/>:</label>
	                <input type="text" name="genre" value="${filmList[editIdFilm].getGenre()}" /><br/>
	                
	                <label for="duration"><fmt:message key="filmDuration"/>:</label>
	                <input type="text" name="duration" value="${filmList[editIdFilm].getDuration()}" /><br/>
	                
	                <label for="director"><fmt:message key="filmDirector"/>:</label>
	                <input type="text" name="director" value="${filmList[editIdFilm].getDirector()}" /><br/>
	                
	                <label for="releaseYear"><fmt:message key="filmReleaseYear"/>:</label>
	                <input type="text" name="releaseYear" value="${filmList[editIdFilm].getReleaseYear()}" /><br/>
	                
	                <label for="language"><fmt:message key="filmLanguage"/>:</label>
	                <input type="text" name="language" value="${filmList[editIdFilm].getLanguage()}" /><br/>
	                
	                <label for="country"><fmt:message key="filmCountry"/>:</label>
	                <input type="text" name="country" value="${filmList[editIdFilm].getCountry()}" /><br/>
	                
	                <label for="imageURL"><fmt:message key="filmImageURL"/>:</label>
	                <input type="text" name="imageURL" value="${filmList[editIdFilm].getImageURL()}" /><br/>
	                
	                <label for="filmPrice"><fmt:message key="filmPrice"/>:</label>
	                <input type="number" name="filmPrice" step="any" value="${filmList[editIdFilm].getPrice()}" /><br/>
	                
	                <input type="submit" value="<fmt:message key="apply"/>" />
	           </form>
			</div>
		</c:if>
		
		<div class="buttons">
			<c:if test="${not (page eq 1)}">
		    	<form action="Controller" method="post">
					<input type="hidden" name="command" value="to_admin_page" />
					<input type="hidden" name="page" value="${page - 1}" />
					<input type="hidden" name="pageUser" value="${pageUser}" />
					<input type="submit" value="<fmt:message key="toPreviousPage"/>">
				</form>
		    </c:if>
		    
		    <c:if test="${not ((page * numberOfFilmsPerPage) ge filmAmount)}">
		    	<form action="Controller" method="post">
					<input type="hidden" name="command" value="to_admin_page" />
					<input type="hidden" name="page" value="${page + 1}" />
					<input type="hidden" name="pageUser" value="${pageUser}" />
					<input type="submit" value="<fmt:message key="toNextPage"/>">
				</form>
		    </c:if>
		</div>
		
		<div class="addChangeFrame">
	        <h2><fmt:message key="addFilm"/></h2>
	        <form action="Controller" method="post">
	            <input type="hidden" name="command" value="add_film">
	            <input type="hidden" name="page" value="${page}" />
	            <input type="hidden" name="pageUser" value="${pageUser}" />
				
				<label for="title"><fmt:message key="filmTitle"/>:</label>
	           	<input type="text" name="title" value="" /><br/>
	           	
	           	<label for="description"><fmt:message key="filmDescription"/>:</label>
	            <textarea id="description" name="description"></textarea><br/>
	            
	            <label for="genre"><fmt:message key="filmGenre"/>:</label>
	            <input type="text" name="genre" value="" /><br/>
	            
	            <label for="duration"><fmt:message key="filmDuration"/>:</label>
	            <input type="text" name="duration" value="" /><br/>
	            
	            <label for="director"><fmt:message key="filmDirector"/>:</label>
	            <input type="text" name="director" value="" /><br/>
	            
	            <label for="releaseYear"><fmt:message key="filmReleaseYear"/>:</label>
	            <input type="number" name="releaseYear" min="1900" max="2099" value="2023"><br/>
	            
	            <label for="language"><fmt:message key="filmLanguage"/>:</label>
	            <input type="text" name="language" value="" /><br/>
	            
	            <label for="country"><fmt:message key="filmCountry"/>:</label>
	            <input type="text" name="country" value="" /><br/>
				
				<label for="imageURL"><fmt:message key="filmImageURL"/>:</label>
				<input type="text" name="imageURL" value="" /><br/>
	            
	            <label for="price"><fmt:message key="filmPrice"/>:</label>
	            <input type="number" name="price" step="any" value="0" /><br/>
	            
	            <input type="submit" value="<fmt:message key="add"/>" />
	        </form>
        </div>
        
        <div class="tableFrame">
	        <h2><fmt:message key="userList"/></h2>
	        <table>
			    <thead>
			        <tr>
			            <th><fmt:message key="username"/></th>
			            <th><fmt:message key="userSaleRate"/></th>
			            <th></th>
			        </tr>
			    </thead>
			    <tbody>
			    <c:forEach items="${userList}" varStatus="status" var="user">
			        <tr>
			            <td>${user.getLogin()}</td>
			            <td>${user.getSaleRate()}</td>
			            <td>
			                <form action="Controller" method="post">
			                    <input type="hidden" name="command" value="show_edit_sale_rate_form" />
			                    <input type="hidden" name="idUser" value="${status.index}" />
			                    <input type="hidden" name="page" value="${page}" />
			                    <input type="hidden" name="pageUser" value="${pageUser}" />
			                    <input type="submit" value="<fmt:message key="editSaleRate"/>">
			                </form>
			            </td>
			        </tr>
			    </c:forEach>
			    </tbody>
			</table>
		</div>
		
		<div class="addChangeFrame">
			<c:if test="${not empty editIdUser}">
				<form action="Controller" method="post">
					<input type="hidden" name="command" value="edit_sale_rate">
					<input type="hidden" name="idUser" value="${userList[editIdUser].getId()}" />
					<input type="hidden" name="page" value="${page}" />
					<input type="hidden" name="pageUser" value="${pageUser}" />
	            	<label for="sale"><fmt:message key="username"/>: ${userList[editIdUser].getLogin()}<br /><fmt:message key="userSaleRate"/>:</label>
	            	<input type="number" step="any" max="1" min="0" name="saleRate" value="${userList[editIdUser].getSaleRate()}" /><br/>
	               
	                <input type="submit" value="<fmt:message key="apply"/>" />
	            </form>
			</c:if>
		</div>
		
		<div class="buttons">
			<c:if test="${not (pageUser eq 1)}">
		    	<form action="Controller" method="post" style="margin: 10px;">
					<input type="hidden" name="command" value="to_admin" />
					<input type="hidden" name="page" value="${page}" />
					<input type="hidden" name="pageUser" value="${pageUser - 1}" />
					<input type="submit" value="<fmt:message key="toPreviousPage"/>">
				</form>
		    </c:if>
		    <c:if test="${not ((pageUser * numberOfUsersPerPage) ge userAmount)}">
		    	<form action="Controller" method="post" style="margin: 10px;">
					<input type="hidden" name="command" value="to_admin" />
					<input type="hidden" name="page" value="${page}" />
					<input type="hidden" name="pageUser" value="${pageUser + 1}" />
					<input type="submit" value="<fmt:message key="toNextPage"/>">
				</form>
		    </c:if>
		</div>
    <style>
    body {
    	color: white;
	    flex-direction: column;
	    font-family: Times New Roman, serif;
	    background-color: #141414;
	    padding: 0 20px;
	}
    </style>
</tags:header_wrap>