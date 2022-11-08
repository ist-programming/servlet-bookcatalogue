<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<nav class="navbar navbar-default">
  <div class="container-fluid">
    <div class="collapse navbar-collapse" id="bs-example-navbar-collapse-1">
      <ul class="nav navbar-nav">
        <li><a href="<c:url value="/book/list"/>">Home</a></li>
        <c:if test="${user != null}">
          <c:if test="${user.getRole() == 'admin'}">
            <li><a href="<c:url value="/book/create"/>">New Book</a></li>
          </c:if>
          <li><a class="nav-link disabled">${user.getUsername()}</a></li>
        </c:if>
        <c:if test="${user == null}">
          <li><a href="<c:url value="/signin"/>">Sign in</a></li>
        </c:if>
        <%--                    <li><a href="/book/new">New Book</a></li>--%>
        <%--                    <li><a href="/user/list">Users list</a></li>--%>
        <%--    --%>
        <%--    --%>
        <%--                    <li><a href="/register">Register</a></li>--%>
        <%--                    <li><a href="/login">Log in</a></li>--%>
        <%--    --%>
        <%--                    <li><a href="/about">About</a></li>--%>
      </ul>
    </div>
  </div>
</nav>
