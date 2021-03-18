<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>

<!DOCTYPE html>
<html lang="en">
<html>
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
    <meta charset="utf-8">
    <title>Create User</title>
</head>
<body>
    <div align="center" style="margin-top: 50px;">
        <h2>Welcome !</h2>
        <form:form method="POST" modelAttribute="userForm">
            <h2>Create a user</h2>
            <spring:bind path="firstName">
                <div>
                    <form:input type="text" path="firstName" placeholder="First Name" autofocus="true"></form:input>
                </div>
            </spring:bind>
            <spring:bind path="lastName">
                <div>
                    <form:input type="text" path="lastName" placeholder="Last Name" autofocus="true"></form:input>
                </div>
            </spring:bind>
            <spring:bind path="email">
                <div>
                    <form:input type="text" path="email" placeholder="Email" autofocus="true"></form:input>
                </div>
            </spring:bind>
            <spring:bind path="userName">
                <div>
                    <form:input type="text" path="userName" placeholder="Username" autofocus="true"></form:input>
                </div>
            </spring:bind>
            <spring:bind path="pass">
                <div>
                    <form:input type="password" path="pass" placeholder="Password"></form:input>
                </div>
            </spring:bind>
            <button type="submit">Submit</button>
        </form:form>
    </div>
</body>
</html>