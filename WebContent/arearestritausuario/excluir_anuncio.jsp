<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
	<link rel="stylesheet" type="text/css" href="../css/style980.css" />
	<link rel="stylesheet" type="text/css" href="../css/style800.css" />
	<link rel="stylesheet" type="text/css" href="../css/style700.css" />
	<link rel="stylesheet" type="text/css" href="../css/style600.css" />
	<link rel="stylesheet" type="text/css" href="../css/style500.css" />
	<link rel="stylesheet" type="text/css" href="../css/style400.css" />
	<link rel="stylesheet" type="text/css" href="../css/jquery.fancybox-1.3.4.css" media="screen" />
	<!--[if IE]>
	<link href="css/style_ie.css" rel="stylesheet" type="text/css">
	<![endif]-->
	<script type="text/javascript" src="../js/jquery-1.8.3.min.js"></script>
	<script type="text/javascript" src="../js/jquery.easing.1.3.js"></script>
	<script type="text/javascript" src="../js/jquery.bxslider.js"></script>
	<script type="text/javascript" src="../js/jquery.mousewheel.js"></script>
	<script type="text/javascript" src="../js/jquery.selectik.js"></script>
	<script type="text/javascript" src="../js/jquery.mousewheel-3.0.4.pack.js"></script>
	<script type="text/javascript" src="../js/jquery.fancybox-1.3.4.pack.js"></script>
	<script type="text/javascript" src="../js/jquery.countdown.js"></script>
	<script type="text/javascript" src="../js/jquery.checkbox.js"></script>
	<script type="text/javascript" src="../js/js.js"></script>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title></title>
</head>
<body>
<div class="message">

<p>Tem certeza que deseja ecluir o anúncio?</p>

</div>
<% 

long id_anuncio = Long.parseLong(request.getParameter("id_anuncio"));

%>
<form action="../ServAnuncio" method="POST">
<input type="hidden" name="STEP" value="11" />
<input type="hidden" name="id_anuncio" value="<%=id_anuncio %>" />
<input type="submit" value="Confirmar">
</form>
<input type="button"  value="Cancelar">
</body>
</html>