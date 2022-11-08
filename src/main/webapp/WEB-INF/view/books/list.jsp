<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Book list">
    <div class="text-info">Books in DB: ${bookCount}</div>
    <div class="books-list">
        <c:forEach items="${books}" var="book">
            <div class="book-card">
                <div class="book-header">
                    <h3 class="book-name"><a href="<c:url value="/book/detail?id=${book.id}"/>">${book.getTitle()}</a></h3>
                    <span class="book-info">${book.getIssued()}</span>
<%--                    <div class="book-action-icon"><a href="/book/delete/1" class="text-danger"><i class="glyphicon glyphicon-remove"></i></a></div>--%>
<%--                    <div class="book-action-icon"><a href="/book/edit/1" class="text-success"><i class="glyphicon glyphicon-edit"></i></a></div>--%>
                    <div class="clearfix"></div>
                </div>
<%--                <div class="book-description">Some description for the first book</div>--%>
            </div>
        </c:forEach>
    </div>
</t:mainLayout>