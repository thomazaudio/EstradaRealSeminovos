<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import="util.Transform"%>
      <%@ page import="java.util.ArrayList"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title></title>
</head>
<body>

<%

long cod = Long.parseLong(request.getParameter("cod"));

String sql =  new Transform("/home/thomaz/Empresa/Modelos de carros em html/arq.txt").getSQLInsertModeloCarro(cod);

out.write(sql);

%>

</body>
</html>