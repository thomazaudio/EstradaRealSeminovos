<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>

<title>Novo Anúncio</title>
	<link rel="stylesheet" type="text/css" href="../css/style.css" />
	<link rel="stylesheet" type="text/css" href="../css/style980.css" />
	<link rel="stylesheet" type="text/css" href="../css/style800.css" />
	<link rel="stylesheet" type="text/css" href="../css/style700.css" />
	<link rel="stylesheet" type="text/css" href="../css/style600.css" />
	<link rel="stylesheet" type="text/css" href="../css/style500.css" />
	<link rel="stylesheet" type="text/css" href="../css/style400.css" />
	<link rel="stylesheet" type="text/css" href="../css/jquery.fancybox-1.3.4.css" media="screen" />
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">

</head>
<body>

<jsp:include page="../simple_header.jsp"></jsp:include>


<div id="content">
<div class="content">

<div class="steps">
		<a href="#"><span>1. Tipo de Veículo</span></a><a href="#"><span>2. Plano</span></a><a href="#"><span>3. Cadastro das Informações</span></a><span>4. Escolha de Imagens</span>
	
	</div>
	
	
<h2><strong>Qual tipo de veículo você deseja anunciar?</strong></h2>
</div>

	
	
	
	<br></br>
	<div class="content_z conteiner-hv2">
	
	
	
	<div class="container-top clearfix">







   
<img align="left" src="../images/carro.png" alt="" /> 
<form action="../ServAnuncio" method="POST">



<input type="hidden" name="STEP" value="0">
<input type="hidden" name="tipo_veiculo" value="1">
<input align="middle" class="btn blue" type="submit" value="Anunciar Carro">
</form>








</div>
</div>


<br></br>
<div class="content_z conteiner-hv2">
	
	
	
	<div class="container-top clearfix">

<img align="left" src="../images/moto.png" alt="" /> 





   







<form action="../ServAnuncio" method="POST">
<input type="hidden" name="STEP" value="0">
<input type="hidden" name="tipo_veiculo" value="2">
<input  class="btn blue" type="submit" value="Anunciar Moto">
</form>


</div>


</div>
</div>

</body>
</html>