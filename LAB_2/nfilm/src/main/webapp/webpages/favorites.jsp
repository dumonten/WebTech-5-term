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
</style>


<tags:header_wrap title="favoritesTitle">	
        <h1><fmt:message key="favoritesPage"/></h1>
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
						<td>${film.getTitle()}</td>
				        <td>${film.getGenre()}</td>
				        <td>${film.getDuration()}</td>
				        <td>$ ${film.getPrice()}</td>
				        <td>${film.getReleaseYear()}</td>
				        <td>${film.getRating()}</td>
			            <td>
				            <form action="Controller" method="post">
				            <input type="hidden" name="command" value="to_film_page" />
								<input type="hidden" name="idFilm" value="${film.getId()}" />
				                <input type="submit" value="<fmt:message key="details"/>">
							</form>
			            </td>
			            <td>
			                <form action="Controller" method="post">
			                    <input type="hidden" name="command" value="delete_from_favorites" />
			                    <input type="hidden" name="idFilm" value="${film.getId()}" />
			                    <input type="submit" value="<fmt:message key="delete"/>">
			                </form>
			            </td>
			        </tr>
			    </c:forEach>
			    </tbody>
			</table>
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