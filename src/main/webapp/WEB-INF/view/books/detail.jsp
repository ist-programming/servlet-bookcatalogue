<%@page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="t" tagdir="/WEB-INF/tags" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<t:mainLayout title="Book Detail">
    <div class="book-page">
        <h1 class="book-name">${book.getTitle()}</h1>
        <div class="book-field">
            <span class="field-name">Year:</span>
            <span class="field-value">${book.getIssued()}</span>
        </div>
<%--        <div class="book-description">Some description for the first book</div>--%>
    </div>
</t:mainLayout>