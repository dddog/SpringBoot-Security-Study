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
<c:url value="/login" var="loginUrl"/>
<form action="${loginUrl}" method="post">     
	<c:if test="${param.error != null}">      
		<p>
			Invalid username and password.
		</p>
	</c:if>
	<c:if test="${param.logout != null}">     
		<p>
			You have been logged out.
		</p>
	</c:if>
	<p>
		<label for="username">아이디</label>
		<input type="text" id="userid" name="username"/>
	</p>
	<p>
		<label for="password">비밀번호</label>
		<input type="password" id="password" name="password"/>	
	</p>
	<p>
		Remember Me : <input type="checkbox" name="remember-me" value="true" />
	</p>
	<%-- <input type="hidden"                        
		name="${_csrf.parameterName}"
		value="${_csrf.token}"/> --%>
	<sec:csrfInput />
	<button type="submit" class="btn">Log in</button>
</form>
</body>
</html>