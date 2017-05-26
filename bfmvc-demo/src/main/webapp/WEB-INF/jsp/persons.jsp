<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:set var="BASE" value="${pageContext.request.contextPath}"/>
<!DOCTYPE html>
<html>
<head>
    <title>bfmvc-demo</title>
    <link rel="stylesheet" href="asset/css/bootstrap.min.css">
    <script src="asset/js/jquery-2.2.3.min.js"></script>
    <script src="asset/js/bootstrap.min.js"></script>
</head>
<body>
<div>
    <div class="center-block text-center">
        <h1>客户信息</h1>
    </div>
    <table class="table table-bordered">
        <tr>
            <th>客户名称</th>
            <th>手机号</th>
            <th>邮箱地址</th>
        </tr>
        <c:forEach var="person" items="${persons}">
            <tr>
                <td>${person.name}</td>
                <td>${person.telephone}</td>
                <td>${person.email}</td>
            </tr>
        </c:forEach>
    </table>
</div>

</body>
</html>
