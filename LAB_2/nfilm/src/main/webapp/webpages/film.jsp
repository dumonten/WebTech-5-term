<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="tags" tagdir="/WEB-INF/tags"%>

<fmt:setLocale value="${sessionScope.lang}" />
<fmt:setBundle basename="messages" />

<style>

main {
	padding: 10px 10px; 
}

.film-section {
	display: flex;
    align-items: center;
}

.film-section img {
    align-self: flex-start;
}

.film-info {
    margin-left: 20px;
    display: flex;
	flex-direction: column; 
  	justify-content: center;
    align-items: flex-start;
}

.film-info h3,
.film-info p {
    text-align: center;
}

.reviews-section {
	margin-bottom: 20px;
}
.review-details {
	margin-bottom: 10px;
}
.review-actions {
	list-style: none;
	padding: 0;
	display: flex;
	justify-content: center;
}
.review-actions li {
	margin: 0 5px;
}
.comment-actions {
	margin-top: 10px;
}
.add-review {
	margin-top: 20px;
	text-align: center;
}
.make-order-section {
	margin-top: 20px;
	text-align: center;
}

</style>

<tags:header_wrap title="filmTitle">
	<h1><fmt:message key="filmPage"/></h1>
    <div class="film-section">
        <img src="${film.getImageURL()}" width="300" height="500" />
		<div class="film-info">
			<h2><fmt:message key="filmTitle"/>: <c:out value="${film.getTitle()}" /></h2>
	        <p><fmt:message key="filmGenre"/>: <c:out value="${film.getGenre()}" /></p>
	        <p><fmt:message key="filmDuration"/>: <c:out value="${film.getDuration()}" /></p>
	        <p><fmt:message key="filmDirector"/>: <c:out value="${film.getDirector()}" /></p>
	        <p><fmt:message key="filmReleaseYear"/>: <c:out value="${film.getReleaseYear()}" /></p>
	        <p><fmt:message key="filmLanguage"/>: <c:out value="${film.getLanguage()}" /></p>
	        <p><fmt:message key="filmCountry"/>: <c:out value="${film.getCountry()}" /></p>
	        <p><fmt:message key="filmPrice"/>: <c:out value="$ ${film.getPrice()}" /></p>
	        <p><fmt:message key="filmRating"/>: <c:out value="${film.getRating()}" /></p>
	        <h3><fmt:message key="filmDescription"/>:</h3>
	        <p><c:out value="${film.getDescription()}" /></p>
        </div>
    </div>
    
    <div class="reviews-section">
        <h3><fmt:message key="reviews"/>:</h3>
        <c:forEach items="${reviewList}" var="review">
            <div class="review-details">
                <p><fmt:message key="username"/>: <c:out value="${review.getUsername()}" /></p>
                <p><fmt:message key="rating"/>: <c:out value="${review.getRating()}" /></p>
                <p><fmt:message key="review"/>: <c:out value="${review.getDescription()}" /></p>
            </div>
        
            <c:if test="${review.getUsername() eq sessionScope.user.getLogin()}">
                <div class="comment-actions">
                    <form action="Controller" method="post" style="margin: 10px;">
                        <input type="hidden" name="command" value="show_edit_review_form">
                        <input type="hidden" name="idFilm" value="${film.getId()}" />
                        <input type="hidden" name="idReview" value="${review.getId()}" />
                        <input type="submit" value="<fmt:message key="edit"/>" />
                    </form>
                    
                    <c:if test="${not empty editIdReview and editIdReview eq review.getId()}">
                        <form action="Controller" method="post" style="margin: 10px;">
                            <input type="hidden" name="command" value="edit_review">
                            <input type="hidden" name="idFilm" value="${film.getId()}" />
                            <input type="hidden" name="idReview" value="${review.getId()}" />
                            
                            <label for="rating"><fmt:message key="rating"/>:</label>
                            <input type="number" id="rating" name="rating" min="1" max="5" value="${review.getRating()}" /><br/>
                            
                            <label for="description"><fmt:message key="review"/>:</label>
                            <textarea id="review" name="description">${review.getDescription()}</textarea><br/>
                            
                            <input type="submit" value="<fmt:message key="apply"/>" />
                        </form>
                    </c:if>
                    
                    <form action="Controller" method="post" style="margin: 10px;">
                        <input type="hidden" name="command" value="delete_review">
                        <input type="hidden" name="idFilm" value="${film.getId()}" />
                        <input type="hidden" name="idReview" value="${review.getId()}" />
                        <input type="submit" value="<fmt:message key="delete"/>" />
                    </form>
                </div>
            </c:if>        
            <hr/>
        </c:forEach>
    </div>
    
    <c:if test="${not empty sessionScope.user}">
        <div class="add-review">
            <h3><fmt:message key="addReview"/>:</h3>
            <form action="Controller" method="post">
                <input type="hidden" name="command" value="add_review">
                <input type="hidden" name="idFilm" value="${film.getId()}" />
                <label for="rating"><fmt:message key="rating"/>:</label>
                <input type="number" id="rating" name="rating" min="1" max="5" /><br/>
                <label for="description"><fmt:message key="review"/>:</label>
                <textarea id="review" name="description"></textarea><br/>
                <input type="submit" value="<fmt:message key="add"/>" />
            </form>
        </div>
    </c:if>
    
    <div class="make-order-section">
    	<c:choose>
    		<c:when test="${filmStatus eq -1}">
		        <h3><fmt:message key="order"/>:</h3>
		        <form action="Controller" method="post">
		            <input type="hidden" name="command" value="add_to_cart">
		            <input type="hidden" name="idFilm" value="${film.getId()}" />
		            <input type="submit" value="<fmt:message key="makeOrder"/>" />
		        </form>
	        </c:when>
	        <c:otherwise>
	        	<c:if test="${filmStatus eq 0}">
	        		<h3><fmt:message key="filmInCart"/></h3>
	        	</c:if>
	        	<c:if test="${filmStatus eq 1}">
	        		<h3><fmt:message key="filmPurchased"/></h3>
	        	</c:if>
	        </c:otherwise>
	    </c:choose>
    </div>
    
    <div>
    	<c:choose>
    		<c:when test="${isFilmInFavorites eq 0}">
		        <form action="Controller" method="post">
		            <input type="hidden" name="command" value="add_to_favorites">
		            <input type="hidden" name="idFilm" value="${film.getId()}" />
		            <input type="submit" value="<fmt:message key="feature"/>" />
		        </form>
	        </c:when>
	        <c:otherwise>
	        	<h3><fmt:message key="filmIsFeatured"/></h3>
	        </c:otherwise>
	    </c:choose>
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