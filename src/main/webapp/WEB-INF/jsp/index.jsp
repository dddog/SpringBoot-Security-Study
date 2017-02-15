<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>

<sec:authorize access="isAnonymous()">
	<a href="/login">로그인</a><br>
	<a href="/registerForm">회원가입</a>
</sec:authorize>
<sec:authorize access="isAuthenticated()">
	Logged in user: <span> <sec:authentication property="name"/> </span> |					
    Roles: <span> <sec:authentication property="principal.authorities"/> </span>
    <br>
    <br>
    <a href="/user/hello">USER Page로 가기</a><br>
    <a href="/admin/hello">ADMIN Page로 가기</a>
    
    
	<c:url var="logoutUrl" value="/logout"/>
	<form class="form-inline" action="${logoutUrl}" method="post">
	  <input type="submit" value="Log out" />
	  <input type="hidden" name="${_csrf.parameterName}" value="${_csrf.token}"/>
	</form>
</sec:authorize>
</body>
</html>