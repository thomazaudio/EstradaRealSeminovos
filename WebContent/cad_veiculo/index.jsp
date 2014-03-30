<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>

<form action="../ServAnuncio" method="POST">

<input type="hidden" name="STEP" value="0">
<input type="hidden" name="tipo_veiculo" value="1">
<input type="submit" value="Carro">
</form>

<form action="../ServAnuncio" method="POST">
<input type="hidden" name="STEP" value="0">
<input type="hidden" name="tipo_veiculo" value="2">
<input type="submit" value="Moto">
</form>

</body>
</html>