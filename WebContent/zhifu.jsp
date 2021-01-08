<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="java.util.Random;" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Insert title here</title>
</head>
<body>
<form action="PayServlet" method="get">
<span>小米手机</span><br>
<span><%=new Random().nextInt(100000)
%></span><br>
<span>${pirce}</span><br>
<input type="hidden" name="pirce" value="${pirce}">
<input type="submit" value="提交">
</form>
</body>
</html>